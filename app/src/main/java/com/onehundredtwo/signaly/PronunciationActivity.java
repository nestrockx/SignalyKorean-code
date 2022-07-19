package com.onehundredtwo.signaly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.onehundredtwo.signaly.generators.WordsArrayGenerator;
import com.onehundredtwo.signaly.global.AdCounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class PronunciationActivity extends AppCompatActivity implements RecognitionListener {

    private boolean speechError = false;
    private boolean testMode;
    private int childPosition;
    private int wordsPosition;
    private int groupPosition;

    private int groupCount;
    private ImageView next;
    private ImageView previous;
    private ImageView soundAnimate;

    private int slideNumber = 0;
    private Menu menu;
    private Boolean transliterationOn = false;

    private TextView toReadTextView;
    private TextView translatedTextView;
    private TextView percentTextView;
    private TextView slowerTextView;
    private TextView clickAndSayTextView;

    private SpeechRecognizer sr;
    private Intent intent;
    private ArrayList<String> koreanSentencesArray;
    private ArrayList<String> matchSentencesArray;
    private ArrayList<String> translatedSentencesArray;
    private ArrayList<String> transliteratedSentencesArray;
    private ImageView soundx1;
    private ImageView soundx05;
    private ImageView mic;
    private boolean isListening = false;


    ProgressBar progressBar;
    ProgressBar yourProgress;
    TextToSpeech textToSpeech;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void update(int x) {
        if(transliterationOn) {
            toReadTextView.setTextSize(36);
            toReadTextView.setText(transliteratedSentencesArray.get(x));
        }
        else {
            if (testMode) {
                toReadTextView.setTextSize(36);
                toReadTextView.setText(translatedSentencesArray.get(slideNumber));
            }
            else {
                toReadTextView.setTextSize(48);
                toReadTextView.setText(koreanSentencesArray.get(x));
            }
        }
        translatedTextView.setText(translatedSentencesArray.get(x));
    }

    private void initTextToSpeech() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.KOREAN);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronunciation);
        getWindow().setEnterTransition(null);


        String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO};
        ActivityCompat.requestPermissions(this, permissions, 1);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                groupPosition = 0;
                childPosition = 0;
                wordsPosition = 0;
                testMode = false;
            } else {
                groupPosition = extras.getInt("groupNumber");
                childPosition = extras.getInt("childNumber");
                wordsPosition = extras.getInt("wordsPosition");
                testMode = extras.getBoolean("testMode");
            }
        } else {
            groupPosition = (int) savedInstanceState.getSerializable("groupNumber");
            childPosition = (int) savedInstanceState.getSerializable("childNumber");
            wordsPosition = (int) savedInstanceState.getSerializable("wordsPosition");
            testMode = (boolean) savedInstanceState.getSerializable("testMode");
        }





        slideNumber = childPosition;
        koreanSentencesArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(
                WordsArrayGenerator.getKorean(wordsPosition)[groupPosition])));
        matchSentencesArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(
                WordsArrayGenerator.getMatches(wordsPosition)[groupPosition])));
        transliteratedSentencesArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(
                WordsArrayGenerator.getTransliterated(wordsPosition)[groupPosition])));
        translatedSentencesArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(
                WordsArrayGenerator.getTranslated(wordsPosition)[groupPosition])));
        groupCount = koreanSentencesArray.size();
        this.setTitle(WordsArrayGenerator.getLabels(wordsPosition));


        yourProgress = findViewById(R.id.yourprogress);
        yourProgress.setVisibility(View.VISIBLE);
        yourProgress.setMax(koreanSentencesArray.size() - 1);
        yourProgress.setProgress(slideNumber);


        soundAnimate = findViewById(R.id.soundAnimate);
        soundAnimate.setVisibility(View.INVISIBLE);



        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setMax(10);


        clickAndSayTextView = findViewById(R.id.clickAndSayTextView);
        percentTextView = findViewById(R.id.percent);
        slowerTextView = findViewById(R.id.slowerTextView);
        toReadTextView = findViewById(R.id.text);
        translatedTextView = findViewById(R.id.translated);
        soundx1 = findViewById(R.id.soundx1);
        soundx05 = findViewById(R.id.soundx05);
        mic = findViewById(R.id.mic);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        if(slideNumber == 0) {
            previous.setVisibility(View.INVISIBLE);
        }
        if(slideNumber == groupCount - 1) {
            next.setVisibility(View.INVISIBLE);
        }

        // Intent to listen to user vocal input and return the result to the same activity.
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Use a language model based on free-form speech recognition.
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);


        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(this);


        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechError = false;
                if (!isListening) {
                    sr.startListening(intent);
                }
                else {
                    sr.stopListening();
                }
            }
        });

        if(testMode) {
            soundx1.setVisibility(View.INVISIBLE);
            soundx05.setVisibility(View.INVISIBLE);
            slowerTextView.setVisibility(View.INVISIBLE);
            translatedTextView.setVisibility(View.INVISIBLE);
        }
        soundx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.setSpeechRate(1.0f);
                textToSpeech.speak(koreanSentencesArray.get(slideNumber), TextToSpeech.QUEUE_FLUSH,
                        null, null);
            }
        });

        soundx05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.setSpeechRate(0.3f);
                textToSpeech.speak(koreanSentencesArray.get(slideNumber), TextToSpeech.QUEUE_FLUSH,
                        null, null);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(slideNumber + 1 < koreanSentencesArray.size() ? ++slideNumber : koreanSentencesArray.size() - 1);
                yourProgress.setProgress(slideNumber);
                percentTextView.setText("0%");
                percentTextView.setVisibility(View.INVISIBLE);
                if(slideNumber == groupCount - 1) {
                    next.setVisibility(View.INVISIBLE);
                }
                if (previous.getVisibility() == View.INVISIBLE) {
                    previous.setVisibility(View.VISIBLE);
                }
                clickAndSayTextView.setTextColor(Color.WHITE);
                clickAndSayTextView.setText(R.string.pronunciation_mic_label);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(slideNumber - 1 >= 0 ? --slideNumber : 0);
                yourProgress.setProgress(slideNumber);
                percentTextView.setText("0%");
                percentTextView.setVisibility(View.INVISIBLE);
                if(slideNumber == 0) {
                    previous.setVisibility(View.INVISIBLE);
                }
                if(next.getVisibility() == View.INVISIBLE) {
                    next.setVisibility(View.VISIBLE);
                }
                clickAndSayTextView.setTextColor(Color.WHITE);
                clickAndSayTextView.setText(R.string.pronunciation_mic_label);
            }
        });

        update(slideNumber);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("groupNumber", groupPosition);
        outState.putInt("childNumber", childPosition);
        outState.putInt("wordsPosition", wordsPosition);
        outState.putBoolean("testMode", testMode);

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!testMode) {
            this.menu = menu;
            getMenuInflater().inflate(R.menu.menu_pronunciation, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(!testMode) {
            if (id == R.id.action_transliteration) {
                if (!transliterationOn) {
                    menu.getItem(0).setIcon(ContextCompat.getDrawable(this,
                            R.drawable.ic_transliteration_on));
                    toReadTextView.setTextSize(36);
                    toReadTextView.setText(transliteratedSentencesArray.get(slideNumber));
                } else {
                    menu.getItem(0).setIcon(ContextCompat.getDrawable(this,
                            R.drawable.ic_transliteration_off));
                    toReadTextView.setTextSize(48);
                    toReadTextView.setText(koreanSentencesArray.get(slideNumber));
                }
                transliterationOn = !transliterationOn;
                return true;
            }
        }

        if (id == android.R.id.home) {
            showAdAndClose();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showAdAndClose();
    }


    private void showAdAndClose() {
            finish();
    }






    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech = null;
        }
    }

    @Override
    public void onResume() {
        if(textToSpeech == null) {
            initTextToSpeech();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }



    @Override
    public void onReadyForSpeech(Bundle params) {
        if (!speechError) {
            mic.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_micon));
            soundAnimate.setVisibility(View.VISIBLE);
            soundx1.setVisibility(View.INVISIBLE);
            soundx05.setVisibility(View.INVISIBLE);
            slowerTextView.setVisibility(View.INVISIBLE);
            isListening = true;
        } else {
            sr.cancel();
        }
    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i("DB", Float.toString(rmsdB));

        soundAnimate.getLayoutParams().height = Math.max(Math.min((int)rmsdB * 60, HangulWritingActivity.dpToPx(200)), 1);
        soundAnimate.setScaleType(ImageView.ScaleType.FIT_XY);
        soundAnimate.requestLayout();
    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    private void speechEnd() {
        mic.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_micoff));
        soundAnimate.setVisibility(View.INVISIBLE);
        soundx1.setVisibility(View.VISIBLE);
        soundx05.setVisibility(View.VISIBLE);
        slowerTextView.setVisibility(View.VISIBLE);
        isListening = false;
    }

    @Override
    public void onEndOfSpeech() {
        speechEnd();
    }

    @Override
    public void onError(int error) {

        speechError = true;

        switch (error) {
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                Toast.makeText(this, getString(R.string.error_network_timeout), Toast.LENGTH_SHORT).show();
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_SHORT).show();
                break;
            case SpeechRecognizer.ERROR_AUDIO:
                Toast.makeText(this, getString(R.string.error_audio), Toast.LENGTH_SHORT).show();
                break;
            case SpeechRecognizer.ERROR_SERVER:
                Toast.makeText(this, getString(R.string.error_server), Toast.LENGTH_SHORT).show();
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                Toast.makeText(this, getString(R.string.error_client), Toast.LENGTH_SHORT).show();
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                Toast.makeText(this, getString(R.string.error_speech_timeout), Toast.LENGTH_SHORT).show();
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                Toast.makeText(this, getString(R.string.error_recognizer_busy), Toast.LENGTH_SHORT).show();
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                Toast.makeText(this, getString(R.string.error_insufficient_permissions), Toast.LENGTH_SHORT).show();
                break;
        }
        speechEnd();
    }

    private boolean matchSentence(String[] words, String result) {
        for (String x: words) {
            if(x.equals(result)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onResults(Bundle results) {
        float [] confScores = results.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        assert matches != null;
        assert confScores != null;
        for (float x : confScores) {
            Log.i("conf", Float.toString(x));
        }
        for (String x : matches) {
            Log.i("SENTENCES", x);
        }

        //percentTextView.setText(Float.toString(confScores[0]));
        String percentage = String.format(Locale.US, "%f", confScores[0] * 100).split("\\.")[0].concat("%");
        if (percentage.equals("92%") && matchSentence(matchSentencesArray.get(slideNumber).split(";"), matches.get(0))) {
            clickAndSayTextView.setText("100%");
            textToSpeech.setSpeechRate(1.0f);
            textToSpeech.speak("고생했어요", TextToSpeech.QUEUE_FLUSH, null, null);
            percentTextView.setTextColor(Color.parseColor("#00A51B"));
            percentTextView.setText(matchSentencesArray.get(slideNumber).split(";")[0]);
        }
        else if (matchSentence(matchSentencesArray.get(slideNumber).split(";"), matches.get(0))) {
            clickAndSayTextView.setText(percentage);
            percentTextView.setTextColor(Color.parseColor("#00A51B"));
            percentTextView.setText(matchSentencesArray.get(slideNumber).split(";")[0]);
        }
        else{
            clickAndSayTextView.setText("0%");
            percentTextView.setTextColor(Color.parseColor("#B10000"));
            percentTextView.setText(matches.get(0));
        }
        percentTextView.setVisibility(View.VISIBLE);



    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted. Continue the action or workflow
                // in your app.
            } else {

                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Hej!");
                alertDialog.setMessage("Potrzebujemy dostępu do mikrofonu do poprawnego działania.\n" +
                        "Aplikacja korzysta z usługi rozpoznawania mowy od Google.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                            }
                        });
                alertDialog.show();


            }
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(updateBaseContextLocale(base));
    }

    private Context updateBaseContextLocale(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String language = prefs.getString("language", context.getResources().getString(R.string.default_language));
        assert language != null;
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        return updateResourcesLocale(context, locale);
    }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    private Context updateResourcesLocale(Context context, Locale locale) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }








}