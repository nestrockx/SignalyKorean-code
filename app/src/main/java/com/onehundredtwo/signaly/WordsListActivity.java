package com.onehundredtwo.signaly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.onehundredtwo.signaly.adapters.WordsExpandableListAdapter;
import com.onehundredtwo.signaly.adapters.WordsExpandableListData;
import com.onehundredtwo.signaly.generators.WordsArrayGenerator;
import com.onehundredtwo.signaly.global.AdCounter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class WordsListActivity extends AppCompatActivity {

    private int wordsPosition;
    private String wordsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);
        getWindow().setEnterTransition(null);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                wordsPosition = 0;
                wordsTitle = null;
            } else {
                wordsPosition = extras.getInt("wordsPosition");
                wordsTitle = extras.getString("wordsTitle");
            }
        } else {
            wordsPosition = (int) savedInstanceState.getSerializable("wordsPosition");
            wordsTitle = (String) savedInstanceState.getSerializable("wordsTitle");
        }

        this.setTitle(wordsTitle);


        ExpandableListView expandableListView = findViewById(R.id.wordsExpandableListView);
        LinkedHashMap<String, List<String>> expandableListDetails = WordsExpandableListData.getData(this,
                WordsArrayGenerator.getTranslated(wordsPosition), WordsArrayGenerator.getTitles(wordsPosition));
        List<String> expandableListGroupTitle = new ArrayList<>(expandableListDetails.keySet());
        ExpandableListAdapter expandableListAdapter = new WordsExpandableListAdapter(this,
                expandableListGroupTitle, expandableListDetails, wordsPosition);
        expandableListView.setAdapter(expandableListAdapter);

        for(int i = 0; i < expandableListAdapter.getGroupCount(); i++)
            expandableListView.expandGroup(i);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (!parent.isGroupExpanded(groupPosition)) {
                    parent.expandGroup(groupPosition);
                } else {
                    parent.collapseGroup(groupPosition);
                }
                parent.setSelectedGroup(groupPosition);
                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getApplicationContext(), PronunciationActivity.class);
                intent.putExtra("groupNumber", groupPosition);
                intent.putExtra("childNumber", childPosition);
                intent.putExtra("wordsPosition", wordsPosition);
                intent.putExtra("wordsTitle", wordsTitle);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                        WordsListActivity.this).toBundle());
                return true;
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("wordsTitle", wordsTitle);
        outState.putInt("wordsPosition", wordsPosition);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (false) {

        }
        else {
            Intent intent = new Intent(WordsListActivity.this, MainActivity.class);
            intent.putExtra("action", 1);
            intent.putExtra("showAd", true);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(WordsListActivity.this).toBundle());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(WordsListActivity.this, MainActivity.class);
        intent.putExtra("action", 1);
        intent.putExtra("showAd", true);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(WordsListActivity.this).toBundle());
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