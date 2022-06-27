package com.onehundredtwo.signaly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.preference.PreferenceManager;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.onehundredtwo.signaly.global.AdCounter;
import com.onehundredtwo.signaly.paint.HangulWritingView;

import java.util.Locale;

public class HangulWritingActivity extends AppCompatActivity {

    private Menu menu;

    private int groupPosition = 0;
    private int childPosition = 0;
    private String letterType;
    boolean testMode;
    private String[] syllablesID;
    private String[] syllables;

    private int groupCount = 0;
    private TextView letterToWriteTextView;
    ImageView nextImageView;
    ImageView previousImageView;

    private ProgressBar yourProgress;

    private HangulWritingView hangulWritingView;

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangul_writing_learn);
        getWindow().setEnterTransition(null);


        letterToWriteTextView = findViewById(R.id.characterToWriteTextView);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                groupPosition = 0;
                childPosition = 0;
                letterType = null;
                testMode = false;
            } else {
                groupPosition = extras.getInt("groupNumber");
                childPosition = extras.getInt("childNumber");
                letterType = extras.getString("letterType");
                testMode = extras.getBoolean("testMode");
            }
        } else {
            groupPosition = (int) savedInstanceState.getSerializable("groupNumber");
            childPosition = (int) savedInstanceState.getSerializable("childNumber");
            letterType = (String) savedInstanceState.getSerializable("letterType");
            testMode = (boolean) savedInstanceState.getSerializable("testMode");
        }

        assert letterType != null;
        switch (letterType) {
            case "consonants":
                if (groupPosition == 0) {
                    syllables = getResources().getStringArray(R.array.consonants_single);
                    syllablesID = getResources().getStringArray(R.array.consonants_single_ID);
                } else {
                    syllables = getResources().getStringArray(R.array.consonants_double);
                    syllablesID = getResources().getStringArray(R.array.consonants_double_ID);
                }
                break;
            case "vowels":
                if (groupPosition == 0) {
                    syllables = getResources().getStringArray(R.array.vowels_single);
                    syllablesID = getResources().getStringArray(R.array.vowels_single_ID);
                } else {
                    syllables = getResources().getStringArray(R.array.vowels_complex);
                    syllablesID = getResources().getStringArray(R.array.vowels_complex_ID);
                }
                break;
            case "syllables":
                if (groupPosition == 0) {
                    syllables = getResources().getStringArray(R.array.syllables1);
                    syllablesID = getResources().getStringArray(R.array.syllables1_ID);
                } else {
                    syllables = getResources().getStringArray(R.array.syllables2);
                    syllablesID = getResources().getStringArray(R.array.syllables2_ID);
                }
                break;
        }

        Log.i("groupNumber", Integer.toString(groupPosition));
        Log.i("childNumber", Integer.toString(childPosition));
        Log.i("letterType", letterType);

        yourProgress = findViewById(R.id.yourprogress);
        yourProgress.setVisibility(View.VISIBLE);


        groupCount = syllables.length;
        yourProgress.setMax(groupCount);
        yourProgress.setProgress(childPosition + 1);
        letterToWriteTextView.setText(syllables[childPosition].split(";")[1]);




        hangulWritingView = findViewById(R.id.hangulWritingView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        if(metrics.heightPixels > metrics.widthPixels) {
            hangulWritingView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    metrics.widthPixels - dpToPx(48), metrics.widthPixels - dpToPx(48)));
        }
        else {
            hangulWritingView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    metrics.heightPixels - dpToPx(88), metrics.heightPixels - dpToPx(88)));
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(hangulWritingView.getId(), ConstraintSet.BOTTOM,
                constraintLayout.getId(), ConstraintSet.BOTTOM, 24);
        constraintSet.connect(hangulWritingView.getId(), ConstraintSet.TOP,
                constraintLayout.getId(), ConstraintSet.TOP, 24);
        constraintSet.connect(hangulWritingView.getId(), ConstraintSet.LEFT,
                constraintLayout.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(hangulWritingView.getId(), ConstraintSet.RIGHT,
                constraintLayout.getId(), ConstraintSet.RIGHT, 0);
        constraintSet.setVerticalBias(hangulWritingView.getId(), 0.9f);
        constraintSet.setHorizontalBias(hangulWritingView.getId(), 0.5f);

        constraintSet.applyTo(constraintLayout);

        if(metrics.heightPixels > metrics.widthPixels) {
            hangulWritingView.init(metrics.widthPixels - dpToPx(48),
                    metrics.widthPixels - dpToPx(48), syllablesID[childPosition], testMode);
        }
        else {
            hangulWritingView.init(metrics.heightPixels - dpToPx(88),
                    metrics.heightPixels - dpToPx(88), syllablesID[childPosition], testMode);
        }



        nextImageView = findViewById(R.id.nextConsonant);
        previousImageView = findViewById(R.id.previousConsonant);

        if(childPosition == groupCount - 1) {
            nextImageView.setVisibility(View.INVISIBLE);
        }
        if(childPosition == 0) {
            previousImageView.setVisibility(View.INVISIBLE);
        }

        nextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childPosition + 1 != groupCount) {
                    hangulWritingView.setKoreanLetter(syllablesID[++childPosition]);

                }
                if(childPosition == groupCount - 1) {
                    nextImageView.setVisibility(View.INVISIBLE);
                }
                yourProgress.setProgress(childPosition + 1);

                letterToWriteTextView.setText(syllables[childPosition].split(";")[1]);


                if (previousImageView.getVisibility() == View.INVISIBLE) {
                    previousImageView.setVisibility(View.VISIBLE);
                }

                hangulWritingView.hideHint();
            }
        });


        previousImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childPosition - 1 != -1) {
                    hangulWritingView.setKoreanLetter(syllablesID[--childPosition]);
                }
                if(childPosition == 0) {
                    previousImageView.setVisibility(View.INVISIBLE);
                }
                yourProgress.setProgress(childPosition + 1);

                letterToWriteTextView.setText(syllables[childPosition].split(";")[1]);


                if(nextImageView.getVisibility() == View.INVISIBLE) {
                    nextImageView.setVisibility(View.VISIBLE);
                }

                hangulWritingView.hideHint();
            }
        });

        ImageView clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hangulWritingView.clear();
            }
        });

        ImageView speakButton = findViewById(R.id.speakButton);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hangulWritingView.speak(syllables[childPosition].split(";")[0]);

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("groupNumber", groupPosition);
        outState.putInt("childNumber", childPosition);
        outState.putString("letterType", letterType);
        outState.putBoolean("testMode", testMode);

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(testMode) {
            this.menu = menu;
            getMenuInflater().inflate(R.menu.menu_hint_for_hangul_writing, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(testMode) {

            if (id == R.id.action_transliteration) {
                hangulWritingView.showHint();
                return true;
            }
        }
        if (id == android.R.id.home) {
            //showAdAndClose();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //showAdAndClose();
    }


    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
        hangulWritingView.stopSpeaking();
    }

    @Override
    public void onResume() {
        hangulWritingView.initSpeaking();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        hangulWritingView.stopSpeaking();
        super.onDestroy();
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