package com.onehundredtwo.signaly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {

    private int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(null);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                action = 0;
            } else {
                action = extras.getInt("action");
            }
        } else {
            action = (int) savedInstanceState.getSerializable("action");
        }

        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
        i.putExtra("action", action);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this).toBundle());
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("action", action);

        super.onSaveInstanceState(outState);
    }


}