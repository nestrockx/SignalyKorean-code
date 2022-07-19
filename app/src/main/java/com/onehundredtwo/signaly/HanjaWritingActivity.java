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
import com.onehundredtwo.signaly.paint.HanjaWritingView;

import java.util.Locale;

public class HanjaWritingActivity extends AppCompatActivity {

    private Menu menu;

    private int groupPosition = 0;
    private int childPosition = 0;
    boolean testMode;

    private int groupCount = 0;
    private TextView characterToWriteTextView;
    private TextView characterToWriteTranslationTextView;
    ImageView nextImageView;
    ImageView previousImageView;

    String[] hanjaArray;


    private ProgressBar yourProgress;

    private HanjaWritingView hanjaWritingView;

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanja_writing_learn);
        getWindow().setEnterTransition(null);


        AdRequest adRequest = new AdRequest.Builder().build();


        characterToWriteTextView = findViewById(R.id.characterToWriteTextView);
        characterToWriteTranslationTextView = findViewById(R.id.characterToWriteTranslation);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                groupPosition = 0;
                childPosition = 0;
                testMode = false;
            } else {
                groupPosition = extras.getInt("groupNumber");
                childPosition = extras.getInt("childNumber");
                testMode = extras.getBoolean("testMode");
            }
        } else {
            groupPosition = (int) savedInstanceState.getSerializable("groupNumber");
            childPosition = (int) savedInstanceState.getSerializable("childNumber");
            testMode = (boolean) savedInstanceState.getSerializable("testMode");
        }

        Log.i("groupNumber", Integer.toString(groupPosition));
        Log.i("childNumber", Integer.toString(childPosition));

        yourProgress = findViewById(R.id.yourprogress);
        yourProgress.setVisibility(View.VISIBLE);


        if (groupPosition == 0) {
            hanjaArray = getResources().getStringArray(R.array.hanja1);
        }
        else if (groupPosition == 1) {
            hanjaArray = getResources().getStringArray(R.array.hanja2);
        }
        else {
            hanjaArray = getResources().getStringArray(R.array.hanja3);
        }


        groupCount = hanjaArray.length;
        yourProgress.setMax(groupCount);
        yourProgress.setProgress(childPosition + 1);
        characterToWriteTextView.setText(hanjaArray[childPosition].split(";")[1]);
        characterToWriteTranslationTextView.setText(hanjaArray[childPosition].split(";")[2]);




        hanjaWritingView = findViewById(R.id.hangulWritingView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        hanjaWritingView.setLayoutParams(new ConstraintLayout.LayoutParams(metrics.widthPixels - dpToPx(48), metrics.widthPixels  - dpToPx(48)));

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(hanjaWritingView.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, 24);
        constraintSet.connect(hanjaWritingView.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 24);
        constraintSet.connect(hanjaWritingView.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(hanjaWritingView.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 0);
        constraintSet.setVerticalBias(hanjaWritingView.getId(), 0.9f);

        constraintSet.applyTo(constraintLayout);


        hanjaWritingView.init(metrics.widthPixels  - dpToPx(48),
                metrics.widthPixels  - dpToPx(48), groupPosition, childPosition, testMode);



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
                    hanjaWritingView.setChineseCharacter(groupPosition, ++childPosition);
                }
                if(childPosition == groupCount - 1) {
                    nextImageView.setVisibility(View.INVISIBLE);
                }
                yourProgress.setProgress(childPosition + 1);



                characterToWriteTextView.setText(hanjaArray[childPosition].split(";")[1]);
                characterToWriteTranslationTextView.setText(hanjaArray[childPosition].split(";")[2]);



                if (previousImageView.getVisibility() == View.INVISIBLE) {
                    previousImageView.setVisibility(View.VISIBLE);
                }

                hanjaWritingView.hideHint();
            }
        });


        previousImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childPosition - 1 != -1) {
                    hanjaWritingView.setChineseCharacter(groupPosition, --childPosition);
                }
                if(childPosition == 0) {
                    previousImageView.setVisibility(View.INVISIBLE);
                }
                yourProgress.setProgress(childPosition + 1);



                characterToWriteTextView.setText(hanjaArray[childPosition].split(";")[1]);
                characterToWriteTranslationTextView.setText(hanjaArray[childPosition].split(";")[2]);



                if(nextImageView.getVisibility() == View.INVISIBLE) {
                    nextImageView.setVisibility(View.VISIBLE);
                }

                hanjaWritingView.hideHint();
            }
        });

        ImageView clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hanjaWritingView.clear();
            }
        });

        ImageView speakButton = findViewById(R.id.speakButton);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hanjaWritingView.speak(hanjaArray[childPosition].split(";")[1]);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("groupNumber", groupPosition);
        outState.putInt("childNumber", childPosition);
        outState.putBoolean("testMode", testMode);

        super.onSaveInstanceState(outState);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(testMode) {
            this.menu = menu;
            getMenuInflater().inflate(R.menu.menu_hint_for_hanja_writing, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(testMode) {

            if (id == R.id.action_transliteration) {
                hanjaWritingView.showHint();
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
        hanjaWritingView.stopSpeaking();
    }

    @Override
    public void onResume() {
        hanjaWritingView.initSpeaking();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        hanjaWritingView.stopSpeaking();
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