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

import com.onehundredtwo.signaly.adapters.CharactersExpandableListAdapter;
import com.onehundredtwo.signaly.adapters.HanjaExpandableListData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class HanjaListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanja_list);
        getWindow().setEnterTransition(null);

        ExpandableListView expandableListView = findViewById(R.id.hanjaExpandableListView);
        LinkedHashMap<String, List<String>> expandableListDetails = HanjaExpandableListData.getData(this);
        List<String> expandableListGroupTitle = new ArrayList<>(expandableListDetails.keySet());
        ExpandableListAdapter expandableListAdapter = new CharactersExpandableListAdapter(this, expandableListGroupTitle, expandableListDetails, "hanja");
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
                Intent intent = new Intent(getApplicationContext(), HanjaWritingActivity.class);
                intent.putExtra("groupNumber", groupPosition);
                intent.putExtra("childNumber", childPosition);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(HanjaListActivity.this).toBundle());
                return true;
            }
        });



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
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        finish();
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