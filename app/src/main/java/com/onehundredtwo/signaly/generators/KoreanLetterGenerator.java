package com.onehundredtwo.signaly.generators;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;

import com.onehundredtwo.signaly.paint.KoreanSyllable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.onehundredtwo.signaly.paint.HangulWritingView.BITMAP_DIMENSION;

public class KoreanLetterGenerator {

    public static KoreanSyllable get(String letter) {

        HashMap<String, Path[]> list = new HashMap<>();

        Path[] koreanA = new Path[2];
        Path[] koreanYA = new Path[3];
        Path[] koreanEO = new Path[2];
        Path[] koreanYEO = new Path[3];
        Path[] koreanO = new Path[2];
        Path[] koreanYO = new Path[3];
        Path[] koreanU = new Path[2];
        Path[] koreanYU = new Path[3];
        Path[] koreanEU = new Path[1];
        Path[] koreanI = new Path[1];


        Path[] koreanAE = new Path[3];
        Path[] koreanYAE = new Path[4];
        Path[] koreanE = new Path[3];
        Path[] koreanYE = new Path[4];
        Path[] koreanWA = new Path[4];
        Path[] koreanWAE = new Path[5];
        Path[] koreanOE = new Path[3];
        Path[] koreanWEO = new Path[4];
        Path[] koreanWE = new Path[5];
        Path[] koreanWI = new Path[3];
        Path[] koreanUI = new Path[2];


        Path[] koreanG = new Path[1];
        Path[] koreanG_con = new Path[1];
        Path[] koreanN = new Path[1];
        Path[] koreanD = new Path[2];
        Path[] koreanR = new Path[3];
        Path[] koreanM = new Path[3];
        Path[] koreanB = new Path[4];
        Path[] koreanS = new Path[2];
        Path[] koreanNG = new Path[1];
        Path[] koreanJ = new Path[2];
        Path[] koreanCH = new Path[3];
        Path[] koreanK = new Path[2];
        Path[] koreanT = new Path[3];
        Path[] koreanP = new Path[4];
        Path[] koreanH = new Path[3];


        Path[] koreanKK = new Path[2];
        Path[] koreanTT = new Path[4];
        Path[] koreanPP = new Path[8];
        Path[] koreanSS = new Path[4];
        Path[] koreanJJ = new Path[4];

        //vowels {


        koreanA[0] = new Path();
        koreanA[0].moveTo(0.0f, -0.5f);
        koreanA[0].lineTo(0.0f, 0.5f);
        koreanA[1] = new Path();
        koreanA[1].moveTo(0.0f, -0.05f);
        koreanA[1].lineTo(0.2f, -0.05f);
        list.put("A", koreanA);

        koreanYA[0] = new Path();
        koreanYA[0].moveTo(0.0f, -0.5f);
        koreanYA[0].lineTo(0.0f, 0.5f);
        koreanYA[1] = new Path();
        koreanYA[1].moveTo(0.0f, -0.15f);
        koreanYA[1].lineTo(0.2f, -0.15f);
        koreanYA[2] = new Path();
        koreanYA[2].moveTo(0.0f, 0.05f);
        koreanYA[2].lineTo(0.2f, 0.05f);
        list.put("YA", koreanYA);

        koreanEO[0] = new Path();
        koreanEO[0].moveTo(-0.2f, -0.05f);
        koreanEO[0].lineTo(0.0f, -0.05f);
        koreanEO[1] = new Path();
        koreanEO[1].moveTo(0.0f, -0.5f);
        koreanEO[1].lineTo(0.0f, 0.5f);
        list.put("EO", koreanEO);

        koreanYEO[0] = new Path();
        koreanYEO[0].moveTo(-0.2f, -0.15f);
        koreanYEO[0].lineTo(0.0f, -0.15f);
        koreanYEO[1] = new Path();
        koreanYEO[1].moveTo(-0.2f, 0.05f);
        koreanYEO[1].lineTo(0.0f, 0.05f);
        koreanYEO[2] = new Path();
        koreanYEO[2].moveTo(0.0f, -0.5f);
        koreanYEO[2].lineTo(0.0f, 0.5f);
        list.put("YEO", koreanYEO);

        koreanO[0] = new Path();
        koreanO[0].moveTo(0.0f, -0.12f);
        koreanO[0].lineTo(0.0f, 0.125f);
        koreanO[1] = new Path();
        koreanO[1].moveTo(-0.4f, 0.125f);
        koreanO[1].lineTo(0.4f, 0.125f);
        list.put("O", koreanO);

        koreanYO[0] = new Path();
        koreanYO[0].moveTo(-0.125f, -0.12f);
        koreanYO[0].lineTo(-0.125f, 0.125f);
        koreanYO[1] = new Path();
        koreanYO[1].moveTo(0.125f, -0.12f);
        koreanYO[1].lineTo(0.125f, 0.125f);
        koreanYO[2] = new Path();
        koreanYO[2].moveTo(-0.4f, 0.125f);
        koreanYO[2].lineTo(0.4f, 0.125f);
        list.put("YO", koreanYO);

        koreanU[0] = new Path();
        koreanU[0].moveTo(-0.4f, 0.0f);
        koreanU[0].lineTo(0.4f, 0.0f);
        koreanU[1] = new Path();
        koreanU[1].moveTo(0.0f, 0.0f);
        koreanU[1].lineTo(0.0f, 0.3f);
        list.put("U", koreanU);

        koreanYU[0] = new Path();
        koreanYU[0].moveTo(-0.4f, 0.0f);
        koreanYU[0].lineTo(0.4f, 0.0f);
        koreanYU[1] = new Path();
        koreanYU[1].moveTo(-0.125f, 0.0f);
        koreanYU[1].lineTo(-0.125f, 0.3f);
        koreanYU[2] = new Path();
        koreanYU[2].moveTo(0.125f, 0.0f);
        koreanYU[2].lineTo(0.125f, 0.3f);
        list.put("YU", koreanYU);

        koreanEU[0] = new Path();
        koreanEU[0].moveTo(-0.4f, 0.0f);
        koreanEU[0].lineTo(0.4f, 0.0f);
        list.put("EU", koreanEU);

        koreanI[0] = new Path();
        koreanI[0].moveTo(0.0f, -0.5f);
        koreanI[0].lineTo(0.0f, 0.5f);
        list.put("I", koreanI);

        //}

        //vowelCombinations {
        koreanAE[0] = new Path();
        koreanAE[0].moveTo(-0.1f, -0.45f);
        koreanAE[0].lineTo(-0.1f, 0.45f);
        koreanAE[1] = new Path();
        koreanAE[1].moveTo(-0.1f, -0.05f);
        koreanAE[1].lineTo(0.1f, -0.05f);
        koreanAE[2] = new Path();
        koreanAE[2].moveTo(0.1f, -0.5f);
        koreanAE[2].lineTo(0.1f, 0.5f);
        list.put("AE", koreanAE);

        koreanYAE[0] = new Path();
        koreanYAE[0].moveTo(-0.1f, -0.45f);
        koreanYAE[0].lineTo(-0.1f, 0.45f);
        koreanYAE[1] = new Path();
        koreanYAE[1].moveTo(-0.1f, -0.15f);
        koreanYAE[1].lineTo(0.1f, -0.15f);
        koreanYAE[2] = new Path();
        koreanYAE[2].moveTo(-0.1f, 0.05f);
        koreanYAE[2].lineTo(0.1f, 0.05f);
        koreanYAE[3] = new Path();
        koreanYAE[3].moveTo(0.1f, -0.5f);
        koreanYAE[3].lineTo(0.1f, 0.5f);
        list.put("YAE", koreanYAE);

        koreanE[0] = new Path();
        koreanE[0].moveTo(-0.15f, -0.05f);
        koreanE[0].lineTo(0.0f, -0.05f);
        koreanE[1] = new Path();
        koreanE[1].moveTo(0.0f, -0.45f);
        koreanE[1].lineTo(0.0f, 0.45f);
        koreanE[2] = new Path();
        koreanE[2].moveTo(0.15f, -0.5f);
        koreanE[2].lineTo(0.15f, 0.5f);
        list.put("E", koreanE);

        koreanYE[0] = new Path();
        koreanYE[0].moveTo(-0.15f, -0.15f);
        koreanYE[0].lineTo(0.0f, -0.15f);
        koreanYE[1] = new Path();
        koreanYE[1].moveTo(-0.15f, 0.05f);
        koreanYE[1].lineTo(0.0f, 0.05f);
        koreanYE[2] = new Path();
        koreanYE[2].moveTo(0.0f, -0.45f);
        koreanYE[2].lineTo(0.0f, 0.45f);
        koreanYE[3] = new Path();
        koreanYE[3].moveTo(0.15f, -0.5f);
        koreanYE[3].lineTo(0.15f, 0.5f);
        list.put("YE", koreanYE);

        koreanWA[0] = new Path();
        koreanWA[0].moveTo(-0.07f, 0.0f);
        koreanWA[0].lineTo(-0.07f, 0.2f);
        koreanWA[1] = new Path();
        koreanWA[1].moveTo(-0.3f, 0.2f);
        koreanWA[1].lineTo(0.15f, 0.2f);
        koreanWA[2] = new Path();
        koreanWA[2].moveTo(0.2f, -0.45f);
        koreanWA[2].lineTo(0.2f, 0.45f);
        koreanWA[3] = new Path();
        koreanWA[3].moveTo(0.2f, -0.05f);
        koreanWA[3].lineTo(0.3f, -0.05f);
        list.put("WA", koreanWA);

        koreanWAE[0] = new Path();
        koreanWAE[0].moveTo(-0.07f, 0.0f);
        koreanWAE[0].lineTo(-0.07f, 0.2f);
        koreanWAE[1] = new Path();
        koreanWAE[1].moveTo(-0.3f, 0.2f);
        koreanWAE[1].lineTo(0.15f, 0.2f);
        koreanWAE[2] = new Path();
        koreanWAE[2].moveTo(0.2f, -0.45f);
        koreanWAE[2].lineTo(0.2f, 0.45f);
        koreanWAE[3] = new Path();
        koreanWAE[3].moveTo(0.2f, -0.05f);
        koreanWAE[3].lineTo(0.3f, -0.05f);
        koreanWAE[4] = new Path();
        koreanWAE[4].moveTo(0.3f, -0.5f);
        koreanWAE[4].lineTo(0.3f, 0.5f);
        list.put("WAE", koreanWAE);

        koreanOE[0] = new Path();
        koreanOE[0].moveTo(-0.02f, 0.0f);
        koreanOE[0].lineTo(-0.02f, 0.2f);
        koreanOE[1] = new Path();
        koreanOE[1].moveTo(-0.3f, 0.2f);
        koreanOE[1].lineTo(0.25f, 0.2f);
        koreanOE[2] = new Path();
        koreanOE[2].moveTo(0.3f, -0.5f);
        koreanOE[2].lineTo(0.3f, 0.5f);
        list.put("OE", koreanOE);

        koreanWEO[0] = new Path();
        koreanWEO[0].moveTo(-0.3f, 0.2f);
        koreanWEO[0].lineTo(0.25f, 0.2f);
        koreanWEO[1] = new Path();
        koreanWEO[1].moveTo(-0.02f, 0.2f);
        koreanWEO[1].lineTo(-0.02f, 0.45f);
        koreanWEO[2] = new Path();
        koreanWEO[2].moveTo(0.05f, 0.3f);
        koreanWEO[2].lineTo(0.3f, 0.3f);
        koreanWEO[3] = new Path();
        koreanWEO[3].moveTo(0.3f, -0.5f);
        koreanWEO[3].lineTo(0.3f, 0.5f);
        list.put("WEO", koreanWEO);

        koreanWE[0] = new Path();
        koreanWE[0].moveTo(-0.3f, 0.2f);
        koreanWE[0].lineTo(0.15f, 0.2f);
        koreanWE[1] = new Path();
        koreanWE[1].moveTo(-0.07f, 0.2f);
        koreanWE[1].lineTo(-0.07f, 0.45f);
        koreanWE[2] = new Path();
        koreanWE[2].moveTo(0.0f, 0.3f);
        koreanWE[2].lineTo(0.2f, 0.3f);
        koreanWE[3] = new Path();
        koreanWE[3].moveTo(0.2f, -0.45f);
        koreanWE[3].lineTo(0.2f, 0.45f);
        koreanWE[4] = new Path();
        koreanWE[4].moveTo(0.3f, -0.5f);
        koreanWE[4].lineTo(0.3f, 0.5f);
        list.put("WE", koreanWE);

        koreanWI[0] = new Path();
        koreanWI[0].moveTo(-0.3f, 0.2f);
        koreanWI[0].lineTo(0.25f, 0.2f);
        koreanWI[1] = new Path();
        koreanWI[1].moveTo(-0.02f, 0.2f);
        koreanWI[1].lineTo(-0.02f, 0.45f);
        koreanWI[2] = new Path();
        koreanWI[2].moveTo(0.3f, -0.5f);
        koreanWI[2].lineTo(0.3f, 0.5f);
        list.put("WI", koreanWI);

        koreanUI[0] = new Path();
        koreanUI[0].moveTo(-0.3f, 0.2f);
        koreanUI[0].lineTo(0.25f, 0.2f);
        koreanUI[1] = new Path();
        koreanUI[1].moveTo(0.3f, -0.5f);
        koreanUI[1].lineTo(0.3f, 0.5f);
        list.put("UI", koreanUI);

        //}


        //consonants {

        koreanG[0] = new Path();
        koreanG[0].moveTo(-0.4f, -0.3f);
        koreanG[0].lineTo(0.4f, -0.3f);
        koreanG[0].lineTo(0.4f, 0.3f);
        list.put("G", koreanG);

        koreanG_con[0] = new Path();
        koreanG_con[0].moveTo(-0.4f, -0.3f);
        koreanG_con[0].lineTo(0.4f, -0.3f);
        koreanG_con[0].quadTo(0.4f, 0.15f, -0.4f, 0.3f);
        list.put("G_con", koreanG_con);

        koreanN[0] = new Path();
        koreanN[0].moveTo(-0.4f, -0.3f);
        koreanN[0].lineTo(-0.4f, 0.3f);
        koreanN[0].lineTo(0.4f, 0.3f);
        list.put("N", koreanN);

        koreanD[0] = new Path();
        koreanD[0].moveTo(-0.4f, -0.3f);
        koreanD[0].lineTo(0.4f, -0.3f);
        koreanD[1] = new Path();
        koreanD[1].moveTo(-0.4f, -0.3f);
        koreanD[1].lineTo(-0.4f, 0.3f);
        koreanD[1].lineTo(0.4f, 0.3f);
        list.put("D", koreanD);

        koreanR[0] = new Path();
        koreanR[0].moveTo(-0.4f, -0.3f);
        koreanR[0].lineTo(0.4f, -0.3f);
        koreanR[0].lineTo(0.4f, 0.0f);
        koreanR[1] = new Path();
        koreanR[1].moveTo(-0.4f, 0.0f);
        koreanR[1].lineTo(0.4f, 0.0f);
        koreanR[2] = new Path();
        koreanR[2].moveTo(-0.4f, 0.0f);
        koreanR[2].lineTo(-0.4f, 0.3f);
        koreanR[2].lineTo(0.4f, 0.3f);
        list.put("R", koreanR);

        koreanM[0] = new Path();
        koreanM[0].moveTo(-0.4f, -0.3f);
        koreanM[0].lineTo(-0.4f, 0.3f);
        koreanM[1] = new Path();
        koreanM[1].moveTo(-0.4f, -0.3f);
        koreanM[1].lineTo(0.4f, -0.3f);
        koreanM[1].lineTo(0.4f, 0.3f);
        koreanM[2] = new Path();
        koreanM[2].moveTo(-0.4f, 0.3f);
        koreanM[2].lineTo(0.4f, 0.3f);
        list.put("M", koreanM);

        koreanB[0] = new Path();
        koreanB[0].moveTo(-0.4f, -0.3f);
        koreanB[0].lineTo(-0.4f, 0.3f);
        koreanB[1] = new Path();
        koreanB[1].moveTo(0.4f, -0.3f);
        koreanB[1].lineTo(0.4f, 0.3f);
        koreanB[2] = new Path();
        koreanB[2].moveTo(-0.4f, 0.0f);
        koreanB[2].lineTo(0.4f, 0.0f);
        koreanB[3] = new Path();
        koreanB[3].moveTo(-0.4f, 0.3f);
        koreanB[3].lineTo(0.4f, 0.3f);
        list.put("B", koreanB);

        koreanS[0] = new Path();
        koreanS[0].moveTo(0.0f, -0.3f);
        koreanS[0].quadTo( 0.0f, 0.2f, -0.4f, 0.3f);
        koreanS[1] = new Path();
        koreanS[1].moveTo(0.0f, -0.3f);
        koreanS[1].quadTo(0.0f, 0.2f, 0.4f, 0.3f);
        list.put("S", koreanS);

        koreanNG[0] = new Path();
        koreanNG[0].addOval(-0.3f, -0.4f, 0.3f, 0.4f, Path.Direction.CCW);
        list.put("NG", koreanNG);

        koreanJ[0] = new Path();
        koreanJ[0].moveTo(-0.3f, -0.3f);
        koreanJ[0].lineTo(0.3f, -0.3f);
        koreanJ[0].quadTo(0.2f, 0.1f, -0.4f, 0.3f);
        koreanJ[1] = new Path();
        koreanJ[1].moveTo(0.1f, 0.05f);
        koreanJ[1].lineTo(0.4f, 0.3f);
        list.put("J", koreanJ);

        koreanCH[0] = new Path();
        koreanCH[0].moveTo(-0.2f, -0.3f);
        koreanCH[0].lineTo(0.2f, -0.3f);
        koreanCH[1] = new Path();
        koreanCH[1].moveTo(-0.3f, -0.2f);
        koreanCH[1].lineTo(0.3f, -0.2f);
        koreanCH[1].quadTo(0.2f, 0.05f, -0.4f, 0.3f);
        koreanCH[2] = new Path();
        koreanCH[2].moveTo(0.1f, 0.05f);
        koreanCH[2].lineTo(0.4f, 0.3f);
        list.put("CH", koreanCH);

        koreanK[0] = new Path();
        koreanK[0].moveTo(-0.4f, -0.3f);
        koreanK[0].lineTo(0.4f, -0.3f);
        koreanK[0].lineTo(0.4f, 0.3f);
        koreanK[1] = new Path();
        koreanK[1].moveTo(-0.4f, 0.0f);
        koreanK[1].lineTo(0.4f, 0.0f);
        list.put("K", koreanK);

        koreanT[0] = new Path();
        koreanT[0].moveTo(-0.4f, -0.3f);
        koreanT[0].lineTo(0.4f, -0.3f);
        koreanT[1] = new Path();
        koreanT[1].moveTo(-0.4f, 0.0f);
        koreanT[1].lineTo(0.4f, 0.0f);
        koreanT[2] = new Path();
        koreanT[2].moveTo(-0.4f, -0.3f);
        koreanT[2].lineTo(-0.4f, 0.3f);
        koreanT[2].lineTo(0.4f, 0.3f);
        list.put("T", koreanT);

        koreanP[0] = new Path();
        koreanP[0].moveTo(-0.35f, -0.3f);
        koreanP[0].lineTo(0.35f, -0.3f);
        koreanP[1] = new Path();
        koreanP[1].moveTo(-0.15f, -0.3f);
        koreanP[1].lineTo(-0.15f, 0.3f);
        koreanP[2] = new Path();
        koreanP[2].moveTo(0.15f, -0.3f);
        koreanP[2].lineTo(0.15f, 0.3f);
        koreanP[3] = new Path();
        koreanP[3].moveTo(-0.4f, 0.3f);
        koreanP[3].lineTo(0.4f, 0.3f);
        list.put("P", koreanP);

        koreanH[0] = new Path();
        koreanH[0].moveTo(-0.2f, -0.3f);
        koreanH[0].lineTo(0.2f, -0.3f);
        koreanH[1] = new Path();
        koreanH[1].moveTo(-0.4f, -0.2f);
        koreanH[1].lineTo(0.4f, -0.2f);
        koreanH[2] = new Path();
        koreanH[2].addOval(-0.2f, -0.15f, 0.2f, 0.35f, Path.Direction.CCW);
        list.put("H", koreanH);

        //}

        //consonants double {

        koreanKK[0] = new Path();
        koreanKK[0].moveTo(-0.4f, -0.2f);
        koreanKK[0].lineTo(-0.05f, -0.2f);
        koreanKK[0].quadTo(-0.05f, 0.15f, -0.1f, 0.2f);
        koreanKK[1] = new Path();
        koreanKK[1].moveTo(0.05f, -0.2f);
        koreanKK[1].lineTo(0.4f, -0.2f);
        koreanKK[1].quadTo (0.4f, 0.15f, 0.35f, 0.2f);
        list.put("KK", koreanKK);

        koreanTT[0] = new Path();
        koreanTT[0].moveTo(-0.4f, -0.2f);
        koreanTT[0].lineTo(-0.05f, -0.2f);
        koreanTT[1] = new Path();
        koreanTT[1].moveTo(-0.4f, -0.2f);
        koreanTT[1].lineTo(-0.4f, 0.2f);
        koreanTT[1].lineTo(-0.05f, 0.2f);
        koreanTT[2] = new Path();
        koreanTT[2].moveTo(0.05f, -0.2f);
        koreanTT[2].lineTo(0.4f, -0.2f);
        koreanTT[3] = new Path();
        koreanTT[3].moveTo(0.05f, -0.2f);
        koreanTT[3].lineTo(0.05f, 0.2f);
        koreanTT[3].lineTo(0.4f, 0.2f);
        list.put("TT", koreanTT);

        koreanPP[0] = new Path();
        koreanPP[0].moveTo(-0.4f, -0.2f);
        koreanPP[0].lineTo(-0.4f, 0.2f);
        koreanPP[1] = new Path();
        koreanPP[1].moveTo(-0.05f, -0.2f);
        koreanPP[1].lineTo(-0.05f, 0.2f);
        koreanPP[2] = new Path();
        koreanPP[2].moveTo(-0.4f, 0.0f);
        koreanPP[2].lineTo(-0.05f, 0.0f);
        koreanPP[3] = new Path();
        koreanPP[3].moveTo(-0.4f, 0.2f);
        koreanPP[3].lineTo(-0.05f, 0.2f);
        koreanPP[4] = new Path();
        koreanPP[4].moveTo(0.05f, -0.2f);
        koreanPP[4].lineTo(0.05f, 0.2f);
        koreanPP[5] = new Path();
        koreanPP[5].moveTo(0.4f, -0.2f);
        koreanPP[5].lineTo(0.4f, 0.2f);
        koreanPP[6] = new Path();
        koreanPP[6].moveTo(0.05f, 0.0f);
        koreanPP[6].lineTo(0.4f, 0.0f);
        koreanPP[7] = new Path();
        koreanPP[7].moveTo(0.05f, 0.2f);
        koreanPP[7].lineTo(0.4f, 0.2f);
        list.put("PP", koreanPP);

        koreanSS[0] = new Path();
        koreanSS[0].moveTo(-0.2f, -0.2f);
        koreanSS[0].quadTo(-0.2f, 0.1f, -0.4f, 0.2f);
        koreanSS[1] = new Path();
        koreanSS[1].moveTo(-0.2f, -0.2f);
        koreanSS[1].quadTo(-0.2f, 0.1f, -0.02f, 0.2f);
        koreanSS[2] = new Path();
        koreanSS[2].moveTo(0.2f, -0.2f);
        koreanSS[2].quadTo(0.2f, 0.1f, 0.02f, 0.2f);
        koreanSS[3] = new Path();
        koreanSS[3].moveTo(0.2f, -0.2f);
        koreanSS[3].quadTo(0.2f, 0.1f, 0.4f, 0.2f);
        list.put("SS", koreanSS);

        koreanJJ[0] = new Path();
        koreanJJ[0].moveTo(-0.35f, -0.2f);
        koreanJJ[0].lineTo(-0.05f, -0.2f);
        koreanJJ[0].quadTo(-0.2f, 0.1f, -0.4f, 0.2f);
        koreanJJ[1] = new Path();
        koreanJJ[1].moveTo(-0.2f, 0.05f);
        koreanJJ[1].lineTo(-0.02f, 0.2f);
        koreanJJ[2] = new Path();
        koreanJJ[2].moveTo(0.05f, -0.2f);
        koreanJJ[2].lineTo(0.35f, -0.2f);
        koreanJJ[2].quadTo(0.2f, 0.1f, 0.02f, 0.2f);
        koreanJJ[3] = new Path();
        koreanJJ[3].moveTo(0.2f, 0.05f);
        koreanJJ[3].lineTo(0.4f, 0.2f);
        list.put("JJ", koreanJJ);
        //}


        Matrix matrix = new Matrix();
        RectF bounds = new RectF();
        koreanH[2].computeBounds(bounds, true);
        matrix.postRotate(-90.0f, bounds.centerX(), bounds.centerY());
        koreanH[2].transform(matrix);
        Matrix matrix1 = new Matrix();
        RectF bounds1 = new RectF();
        koreanNG[0].computeBounds(bounds1, true);
        matrix1.postRotate(-90.0f, bounds1.centerX(), bounds1.centerY());
        koreanNG[0].transform(matrix1);


        String[] consonants = {"G", "G_con", "N", "D", "R", "M", "B", "S", "NG", "J", "CH", "K", "T", "P",
                "H", "KK", "TT", "PP", "SS", "JJ"};
        String[] vowelsUnder = {"O", "YO", "U", "YU", "EU"};
        String[] vowelsNextTo = {"A", "YA", "I", "EO", "YEO"};
        String[] vowelsComplexNextTo = {"AE", "YAE", "E", "YE"};
        String[] vowelsComplexUnder = {"WA", "WAE", "OE", "WEO", "WE", "WI", "UI"};




        String[] letters = letter.split(";");
        List<Path> paths = new ArrayList<>();

        StringBuilder order = new StringBuilder();
        //order.append(letters.length).append(" ");


        for(String x : letters) {
            if (find(x, consonants)) {
                order.append("0");
            } else if (find(x, vowelsUnder)) {
                order.append("1");
            } else if (find(x, vowelsNextTo)) {
                order.append("2");
            } else if (find(x, vowelsComplexNextTo)) {
                order.append("3");
            } else if (find(x, vowelsComplexUnder)) {
                order.append("4");
            }
        }
        Log.i("order", order.toString());


        if(order.toString().length() == 1) {
            Matrix transformMatrix = new Matrix();
            transformMatrix.setScale(BITMAP_DIMENSION - 100, BITMAP_DIMENSION - 100);
            for (Path x : Objects.requireNonNull(list.get(letter))) {
                x.transform(transformMatrix);
            }
            transformMatrix.setTranslate(BITMAP_DIMENSION / 2.0f, BITMAP_DIMENSION / 2.0f);
            for (Path x : Objects.requireNonNull(list.get(letter))) {
                x.transform(transformMatrix);
            }
            return new KoreanSyllable(Objects.requireNonNull(list.get(letter)));
        }
        else if(order.toString().equals("01")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.6f, 0.55f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.0f, -0.15f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.9f, 0.9f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.0f, 0.15f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }
        else if(order.toString().equals("02")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.5f, 0.7f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.2f, -0.1f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.9f, 0.9f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.2f, 0.0f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }
        else if(order.toString().equals("03")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.5f, 0.7f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.2f, -0.1f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.9f, 0.9f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.15f, 0.0f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }
        else if(order.toString().equals("04")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.4f, 0.5f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.12f, -0.15f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(1.0f, 0.8f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.05f, 0.0f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }
        else if(order.toString().equals("010")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.6f, 0.55f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.0f, -0.25f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.7f, 0.7f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.0f, -0.05f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 2) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.6f, 0.4f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.0f, 0.25f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }
        else if(order.toString().equals("020")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.5f, 0.6f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.15f, -0.1f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.7f, 0.45f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.2f, -0.1f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 2) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.5f, 0.3f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.0f, 0.25f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }
        else if(order.toString().equals("030")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.4f, 0.6f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.15f, -0.1f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.7f, 0.45f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.15f, -0.1f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 2) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.5f, 0.3f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.0f, 0.25f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }
        else if(order.toString().equals("040")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.4f, 0.4f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.12f, -0.25f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.8f, 0.55f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.05f, -0.15f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 2) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.5f, 0.3f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.0f, 0.3f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }
        else if(order.toString().equals("040")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.4f, 0.4f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.12f, -0.25f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.8f, 0.55f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.05f, -0.15f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 2) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.5f, 0.3f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.0f, 0.3f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }
        else if(order.toString().equals("0200")) {
            int index = 0;
            for (String x : letters) {
                if(index == 0) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.5f, 0.6f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.15f, -0.1f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 1) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.7f, 0.45f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.2f, -0.1f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 2) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.3f, 0.3f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(-0.23f, 0.25f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                if(index == 3) {
                    Matrix transformMatrix = new Matrix();
                    transformMatrix.setScale(0.3f, 0.3f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                    transformMatrix.setTranslate(0.08f, 0.25f);
                    for (Path y : Objects.requireNonNull(list.get(x))) {
                        y.transform(transformMatrix);
                    }
                }
                paths.addAll(Arrays.asList(Objects.requireNonNull(list.get(x))));
                index++;
            }
        }




        Path[] pathsArray = new Path[paths.size()];
        paths.toArray(pathsArray);

        Matrix transformMatrix = new Matrix();
        transformMatrix.setScale(BITMAP_DIMENSION - 100, BITMAP_DIMENSION - 100);
        for (Path x : pathsArray) {
            x.transform(transformMatrix);
        }
        transformMatrix.setTranslate(BITMAP_DIMENSION / 2.0f, BITMAP_DIMENSION / 2.0f);
        for (Path x : pathsArray) {
            x.transform(transformMatrix);
        }



        return new KoreanSyllable(Objects.requireNonNull(pathsArray));
    }

    private static boolean find(String letter, String[] letterType) {
        for (String x : letterType) {
            if (x.equals(letter)) {
                return true;
            }
        }
        return false;
    }




}
