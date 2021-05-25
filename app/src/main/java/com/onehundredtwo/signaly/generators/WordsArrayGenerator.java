package com.onehundredtwo.signaly.generators;

import com.onehundredtwo.signaly.R;

public class WordsArrayGenerator {

    public static int getLabels(int x) {
        int[] arrays = { R.string.greetings, R.string.countries, R.string.numbers,  R.string.family, R.string.colors, R.string.animals, R.string.food};
        return arrays[x];
    }

    public static int getTitles(int x) {
        int[] arrays = { R.array.titled_words_1_array, R.array.titled_words_2_array,
                R.array.titled_words_3_array, R.array.titled_words_4_array,
                R.array.titled_words_5_array, R.array.titled_words_6_array,
                R.array.titled_words_7_array
        };
        return arrays[x];
    }

    public static int[] getKorean(int x) {
        int[][] arrays = {
                { R.array.korean_words_1_array },
                { R.array.korean_words_2_array },
                { R.array.korean_words_3_array, R.array.korean_words_3_1_array },
                { R.array.korean_words_4_array },
                { R.array.korean_words_5_array },
                { R.array.korean_words_6_array },
                { R.array.korean_words_7_array }
        };
        return arrays[x];
    }

    public static int[] getMatches(int x) {
        int[][] arrays = {
                { R.array.match_words_1_array },
                { R.array.match_words_2_array },
                { R.array.match_words_3_array, R.array.match_words_3_1_array },
                { R.array.match_words_4_array },
                { R.array.match_words_5_array },
                { R.array.match_words_6_array },
                { R.array.match_words_7_array }
        };
        return arrays[x];
    }

    public static int[] getTransliterated(int x) {
        int[][] arrays = {
                { R.array.transliterated_words_1_array },
                { R.array.transliterated_words_2_array },
                { R.array.transliterated_words_3_array, R.array.transliterated_words_3_1_array },
                { R.array.transliterated_words_4_array },
                { R.array.transliterated_words_5_array },
                { R.array.transliterated_words_6_array },
                { R.array.transliterated_words_7_array }
            };
        return arrays[x];
    }

    public static int[] getTranslated(int x) {
        int[][] arrays = {
                { R.array.translated_words_1_array },
                { R.array.translated_words_2_array },
                { R.array.translated_words_3_array, R.array.translated_words_3_1_array },
                { R.array.translated_words_4_array },
                { R.array.translated_words_5_array },
                { R.array.translated_words_6_array },
                { R.array.translated_words_7_array }
            };
        return arrays[x];
    }
}
