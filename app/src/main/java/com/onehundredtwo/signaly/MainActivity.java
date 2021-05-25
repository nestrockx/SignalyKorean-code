package com.onehundredtwo.signaly;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {


    private SharedPreferences prefs;
    SharedPreferences.OnSharedPreferenceChangeListener listener;

    private int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setEnterTransition(null);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        int[] actions = {R.id.action_to_writingFragment, R.id.action_to_pronunciationFragment, R.id.action_to_settingsFragment};


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                action = 0;
            } else {
                action = extras.getInt("action");
            }
        } else {
            action = (int) savedInstanceState.getSerializable("action");
        }


        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.fragment_writing, true)
                .build();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_writing, R.id.navigation_pronunciation, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        try {
            navController.navigate(actions[action], new Bundle(), navOptions);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("language", getResources().getString(R.string.default_language));
        editor.apply();

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("language")) {
                    Log.i("herbata", Objects.requireNonNull(sharedPreferences.getAll().get(key)).toString());
                    Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                    intent.putExtra("action", 2);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                    finish();
                }
            }
        };

        prefs.registerOnSharedPreferenceChangeListener(listener);

        //prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //setLocale(new Locale(Objects.requireNonNull(prefs.getString("language", "en"))));
    }





    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("action", action);

        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs.registerOnSharedPreferenceChangeListener(listener);
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