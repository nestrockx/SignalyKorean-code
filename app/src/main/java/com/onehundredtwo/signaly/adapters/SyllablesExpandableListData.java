package com.onehundredtwo.signaly.adapters;

import android.content.Context;

import com.onehundredtwo.signaly.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class SyllablesExpandableListData {

    public static LinkedHashMap<String, List<String>> getData(Context context) {
        LinkedHashMap<String, List<String>> expandableListDetails = new LinkedHashMap<>();

        String[] syllables1 = context.getResources().getStringArray(R.array.syllables1);
        String[] syllables2 = context.getResources().getStringArray(R.array.syllables2);

        List<String> syllablesList1 = new ArrayList<>(Arrays.asList(syllables1));
        List<String> syllablesList2 = new ArrayList<>(Arrays.asList(syllables2));


        expandableListDetails.put(context.getResources().getString(R.string.hanja1), syllablesList1);
        expandableListDetails.put(context.getResources().getString(R.string.hanja2), syllablesList2);



        return expandableListDetails;
    }




}
