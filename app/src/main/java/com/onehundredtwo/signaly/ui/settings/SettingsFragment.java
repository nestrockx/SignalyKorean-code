package com.onehundredtwo.signaly.ui.settings;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;


import com.onehundredtwo.signaly.AboutActivity;
import com.onehundredtwo.signaly.MainActivity;
import com.onehundredtwo.signaly.R;
import com.onehundredtwo.signaly.SplashScreenActivity;

import java.util.Objects;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();
        if (key.equals("about")) {
            Intent intent = new Intent(getContext(), AboutActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            requireActivity().finish();
        }
        return super.onPreferenceTreeClick(preference);
    }
}