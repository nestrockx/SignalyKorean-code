package com.onehundredtwo.signaly.adapters;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class WordsExpandableListData {

    //TODO int[] titleIDs
    public static LinkedHashMap<String, List<String>> getData(Context context, int[] stringArrayIDs, int titlesID) {
        LinkedHashMap<String, List<String>> expandableListDetails = new LinkedHashMap<>();


        for (int index = 0; index < stringArrayIDs.length; index++) {
            String[] sentences = context.getResources().getStringArray(stringArrayIDs[index]);
            List<String> sentencesList = new ArrayList<>(Arrays.asList(sentences));
            expandableListDetails.put(context.getResources().getStringArray(titlesID)[index], sentencesList);
        }

        //expandableListDetails.put(context.getResources().getString(R.string.korean_numbers), sentences1List);

        return expandableListDetails;

    }
}
