package com.onehundredtwo.signaly.adapters;

import android.content.Context;

import com.onehundredtwo.signaly.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class VowelsExpandableListData {

    public static LinkedHashMap<String, List<String>> getData(Context context) {
        LinkedHashMap<String, List<String>> expandableListDetails = new LinkedHashMap<>();

        String[] vowelsSingle = context.getResources().getStringArray(R.array.vowels_single);
        String[] vowelsComplex = context.getResources().getStringArray(R.array.vowels_complex);

        List<String> singleVowels = new ArrayList<>(Arrays.asList(vowelsSingle));
        List<String> complexVowels = new ArrayList<>(Arrays.asList(vowelsComplex));


        expandableListDetails.put(context.getResources().getString(R.string.single), singleVowels);
        expandableListDetails.put(context.getResources().getString(R.string.complex), complexVowels);



        return expandableListDetails;
    }

}
