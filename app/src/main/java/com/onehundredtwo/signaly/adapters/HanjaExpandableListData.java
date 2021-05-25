package com.onehundredtwo.signaly.adapters;

import android.content.Context;

import com.onehundredtwo.signaly.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class HanjaExpandableListData {

    public static LinkedHashMap<String, List<String>> getData(Context context) {
        LinkedHashMap<String, List<String>> expandableListDetails = new LinkedHashMap<>();

        String[] hanja1 = context.getResources().getStringArray(R.array.hanja1);
        String[] hanja2 = context.getResources().getStringArray(R.array.hanja2);
        String[] hanja3 = context.getResources().getStringArray(R.array.hanja3);

        List<String> hanja1List = new ArrayList<>(Arrays.asList(hanja1));
        List<String> hanja2List = new ArrayList<>(Arrays.asList(hanja2));
        List<String> hanja3List = new ArrayList<>(Arrays.asList(hanja3));
        expandableListDetails.put(context.getResources().getString(R.string.hanja1), hanja1List);
        expandableListDetails.put(context.getResources().getString(R.string.hanja2), hanja2List);
        expandableListDetails.put(context.getResources().getString(R.string.hanja3), hanja3List);

        return expandableListDetails;
    }

}
