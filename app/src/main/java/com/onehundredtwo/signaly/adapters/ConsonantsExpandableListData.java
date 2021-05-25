package com.onehundredtwo.signaly.adapters;

import android.content.Context;


import com.onehundredtwo.signaly.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class ConsonantsExpandableListData {

    public static LinkedHashMap<String, List<String>> getData(Context context) {
        LinkedHashMap<String, List<String>> expandableListDetails = new LinkedHashMap<>();

        String[] consonantsSingle = context.getResources().getStringArray(R.array.consonants_single);
        String[] consonantsDouble = context.getResources().getStringArray(R.array.consonants_double);

        List<String> singleConsonants = new ArrayList<>(Arrays.asList(consonantsSingle));
        List<String> doubleConsonants = new ArrayList<>(Arrays.asList(consonantsDouble));


        expandableListDetails.put(context.getResources().getString(R.string.single), singleConsonants);
        expandableListDetails.put(context.getResources().getString(R.string.doubleStr), doubleConsonants);



        return expandableListDetails;
    }

}
