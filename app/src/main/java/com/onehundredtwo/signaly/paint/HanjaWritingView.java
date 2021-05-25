package com.onehundredtwo.signaly.paint;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.onehundredtwo.signaly.R;
import com.sdsmdg.harjot.vectormaster.VectorMasterDrawable;
import com.sdsmdg.harjot.vectormaster.models.GroupModel;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HanjaWritingView extends View {

    HanjaCharacter hanjaCharacter;

    boolean testMode = false;
    boolean hintOn = false;

    private TextToSpeech textToSpeech;

    private ValueAnimator valueStrokeAnimator;
    private ValueAnimator valueSuccessAnimator;
    private ValueAnimator valueMistakeAnimator;

    //TODO markerprogress
    private float[] markerProgress = new float[30];
    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private PathMeasure pathMeasure = new PathMeasure();

    private PointF pointF = new PointF();
    private boolean setup = false;
    public static final int BITMAP_DIMENSION = 400;

    private Matrix transformMat = new Matrix();
    private Matrix inverseTransformMat = new Matrix();

    private int width;
    private int height;

    public static int BRUSH_SIZE = 10;
    public static final int DEFAULT_COLOR = Color.WHITE;
    public static final int DEFAULT_BG_COLOR = Color.parseColor("#131717");
    private static final float TOUCH_TOLERANCE = 4;
    private float mX, mY;
    private Path mPath;

    private Paint mainPaint;
    private Paint fingerPathPaint;
    private Paint sketchPaint;
    private Paint gridPaint;


    private ArrayList<FingerPath> paths = new ArrayList<>();
    private int currentColor;
    private int backgroundColor = DEFAULT_BG_COLOR;
    private int strokeWidth;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);

    public HanjaWritingView(Context context) {
        super(context);
    }

    public HanjaWritingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public HanjaWritingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initTextToSpeech() {
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.KOREAN);
                }
            }
        });
    }

    public void init(int width, int height, int groupNumber, int childNumber, boolean testMode) {

        this.testMode = testMode;

        valueMistakeAnimator = ValueAnimator.ofArgb(Color.parseColor("#FFB60000"),
                Color.parseColor("#00B60000"));

        int vMistakeDuration = 500;
        valueMistakeAnimator.setDuration(vMistakeDuration);
        valueMistakeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (Integer) animation.getAnimatedValue();
                fingerPathPaint.setColor(color);
                invalidate();
                if (color == Color.parseColor("#00B60000")) {
                    fingerPathPaint.setColor(DEFAULT_COLOR);
                    fingerPathPaint.setAlpha(0xff);
                    paths.clear();
                }
            }
        });

        valueSuccessAnimator = ValueAnimator.ofArgb(DEFAULT_COLOR,
                Color.parseColor("#009B19"), DEFAULT_COLOR);
        int vSuccessDuration = 1000; //in millis
        valueSuccessAnimator.setDuration(vSuccessDuration);
        valueSuccessAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mainPaint.setColor((Integer) animation.getAnimatedValue());
                invalidate();
            }
        });

        valueStrokeAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        int mDuration = 2000; //in millis
        valueStrokeAnimator.setDuration(mDuration);
        valueStrokeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                markerProgress[hanjaCharacter.getIndex()] = (float)animation.getAnimatedValue();
                invalidate();
            }
        });


        ///
        initPaints();
        initHanja(groupNumber);
        setChineseCharacter(groupNumber, childNumber);
        initTextToSpeech();
        ///



        this.width = width;
        this.height = height;
        mBitmap = Bitmap.createBitmap(BITMAP_DIMENSION, BITMAP_DIMENSION, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        currentColor = DEFAULT_COLOR;
        strokeWidth = BRUSH_SIZE;

    }

    public void setChineseCharacter(int groupNumber, int childNumber) {

        clear();
        valueStrokeAnimator.cancel();

        hanjaCharacter = chineseCharactersList1.get(childNumber);



    }

    public void speak(String toSpeak) {
        Log.i("TOSPEAK", toSpeak);
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void initSpeaking() {
        if(textToSpeech == null) {
            initTextToSpeech();
        }
    }

    public void stopSpeaking() {
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech = null;
        }
    }

    public void clear() {
        valueStrokeAnimator.cancel();
        if(hanjaCharacter != null) {
            hanjaCharacter.reset();
        }
        backgroundColor = DEFAULT_BG_COLOR;
        //paths.clear();
        invalidate();
    }

    public void showHint() {
        hintOn = true;
        invalidate();
    }

    public void hideHint() {
        hintOn = false;
        invalidate();
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        mCanvas.drawColor(backgroundColor);
        if(!setup) {
            setupScaleMatrices();
        }

        mCanvas.drawLine((float)BITMAP_DIMENSION / 2, 10.0f,
                (float)BITMAP_DIMENSION / 2, BITMAP_DIMENSION - 10.0f, gridPaint);
        mCanvas.drawLine(10.0f, (float)BITMAP_DIMENSION / 2,
                BITMAP_DIMENSION - 10.0f, (float)BITMAP_DIMENSION / 2, gridPaint);

        if(!hanjaCharacter.isFinished()) {

            if (!testMode || hintOn) {
                mCanvas.drawPath(hanjaCharacter.getCurrPath(), sketchPaint);

                pathMeasure.setPath(hanjaCharacter.getCurrPath(), false);
                pathMeasure.getPosTan(markerProgress[hanjaCharacter.getIndex()]
                        * pathMeasure.getLength(), pos, tan);
                mCanvas.drawPoint(pos[0], pos[1], mainPaint);

                if (!valueStrokeAnimator.isRunning()) {
                    valueStrokeAnimator.start();
                }
            }

            if (hanjaCharacter.isMatched()) {
                if (!hanjaCharacter.nextIndex()) {
                    valueSuccessAnimator.start();
                }
                hintOn = false;
                valueStrokeAnimator.cancel();
                invalidate();
            }

            for (FingerPath fp : paths) {
                mCanvas.drawPath(fp.path, fingerPathPaint);
            }

        }

        if(hanjaCharacter.isFinished()) {
            for (int i = 0; i < hanjaCharacter.getIndex() + 1; i++) {
                mCanvas.drawPath(hanjaCharacter.getPaths().get(i), mainPaint);
            }
        }
        else {
            for (int i = 0; i < hanjaCharacter.getIndex(); i++) {
                mCanvas.drawPath(hanjaCharacter.getPaths().get(i), mainPaint);
            }
        }



        canvas.drawBitmap(mBitmap, transformMat, mBitmapPaint);
        canvas.restore();


    }

    private void touchStart(float x, float y) {

        if(valueMistakeAnimator.isRunning()) {
            valueMistakeAnimator.cancel();
            fingerPathPaint.setColor(DEFAULT_COLOR);
            fingerPathPaint.setAlpha(0xff);
            paths.clear();
        }

        mPath = new Path();
        FingerPath fp = new FingerPath(currentColor, strokeWidth, mPath);
        paths.add(fp);

        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        if(hanjaCharacter.matchStart(mX, mY, 70.0f)){
            Log.i("IDONT_KNOW", "MATCHED_START");
        }
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }

        if(hanjaCharacter.matchControlPoint(mX, mY, 60.0f)) {
            Log.i("IDONT_KNOW", "MATCHED_CONTROL_POINT");
        }

    }

    private void touchUp() {
        if(hanjaCharacter.isMatchedStart() && hanjaCharacter.isMatchedControlPoint()) {
            if (hanjaCharacter.matchEnd(mX, mY, 70.0f)) {
                Log.i("IDONT_KNOW", "MATCHED_END");
            }
            else {
                hanjaCharacter.matchReset();
            }
        }
        mPath.lineTo(mX, mY);
        //TODO
        if (hanjaCharacter.isMatched()) {
            paths.clear();
        }
        else {
            valueMistakeAnimator.start();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                getBitmapCoords(x, y, pointF);
                Log.i("STARTING POINT", Float.toString(pointF.x) + " : " + Float.toString(pointF.y));
                touchStart(pointF.x, pointF.y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                getBitmapCoords(x, y, pointF);
                touchMove(pointF.x, pointF.y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                touchUp();
                invalidate();
                break;
        }

        return true;
    }

    private void setupScaleMatrices() {

        // View size.
        float width = this.width;
        float height = this.height;
        float scaleW = width / BITMAP_DIMENSION;
        float scaleH = height / BITMAP_DIMENSION;

        float scale = scaleW;
        if (scale > scaleH) {
            scale = scaleH;
        }

        // Translation to center bitmap in view after it is scaled up.
        float centerX = BITMAP_DIMENSION * scale / 2;
        float centerY = BITMAP_DIMENSION * scale / 2;
        float dx = width / 2 - centerX;
        float dy = height / 2 - centerY;

        transformMat.setScale(scale, scale);
        transformMat.postTranslate(dx, dy);
        transformMat.invert(inverseTransformMat);
        setup = true;
    }

    public void getBitmapCoords(float x, float y, PointF out) {
        float points[] = new float[2];
        points[0] = x;
        points[1] = y;
        inverseTransformMat.mapPoints(points);
        out.x = points[0];
        out.y = points[1];
    }

    private void initPaints() {
        fingerPathPaint = new Paint();
        fingerPathPaint.setAntiAlias(true);
        fingerPathPaint.setDither(true);
        fingerPathPaint.setColor(DEFAULT_COLOR);
        fingerPathPaint.setStyle(Paint.Style.STROKE);
        fingerPathPaint.setStrokeJoin(Paint.Join.ROUND);
        fingerPathPaint.setStrokeCap(Paint.Cap.ROUND);
        fingerPathPaint.setXfermode(null);
        fingerPathPaint.setAlpha(0xff);
        fingerPathPaint.setStrokeWidth(BRUSH_SIZE);

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setDither(true);
        mainPaint.setColor(DEFAULT_COLOR);
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setStrokeJoin(Paint.Join.ROUND);
        mainPaint.setStrokeCap(Paint.Cap.ROUND);
        mainPaint.setXfermode(null);
        mainPaint.setAlpha(0xff);
        mainPaint.setStrokeWidth(BRUSH_SIZE);

        sketchPaint = new Paint();
        sketchPaint.setAntiAlias(true);
        sketchPaint.setDither(true);
        sketchPaint.setColor(Color.GRAY);
        sketchPaint.setStyle(Paint.Style.STROKE);
        sketchPaint.setStrokeJoin(Paint.Join.ROUND);
        sketchPaint.setStrokeCap(Paint.Cap.ROUND);
        sketchPaint.setXfermode(null);
        sketchPaint.setAlpha(0xff);
        sketchPaint.setStrokeWidth(5.0f);

        gridPaint = new Paint();
        gridPaint.setAntiAlias(true);
        gridPaint.setDither(true);
        gridPaint.setColor(Color.parseColor("#666666"));
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeJoin(Paint.Join.ROUND);
        gridPaint.setStrokeCap(Paint.Cap.ROUND);
        gridPaint.setXfermode(null);
        gridPaint.setAlpha(0xff);
        gridPaint.setStrokeWidth(1.0f);
    }

    private List<HanjaCharacter> chineseCharactersList1;

    private void initHanja(int groupNumber) {

        List<String> chineseCharactersOrderUnicode = new ArrayList<>();
        String[] hanjaStringArray;
        if (groupNumber == 0) {
            hanjaStringArray = getResources().getStringArray(R.array.hanja1);
        }
        else if (groupNumber == 1) {
            hanjaStringArray = getResources().getStringArray(R.array.hanja2);
        }
        else {
            hanjaStringArray = getResources().getStringArray(R.array.hanja3);
        }

        for (String str: hanjaStringArray) {
            chineseCharactersOrderUnicode.add(Integer.toHexString(str.split(" ")[0].charAt(0) | 0x10000).substring(1));

        }


        //Log.i("UNICODE", Integer.toHexString('大' | 0x10000).substring(1));

        List<Path> chineseCharacterStrokeList = new ArrayList<>();
        chineseCharactersList1 = new ArrayList<>();

        for (int i = 0; i < chineseCharactersOrderUnicode.size(); i++) {
            //Log.i("dsd", chineseCharactersOrderUnicode.get(i));
            Log.i("UNICODE", "ic_0" + chineseCharactersOrderUnicode.get(i) + " " +
                    "drawable" + " " + getContext().getPackageName());
            VectorMasterDrawable vectorMasterDrawable = new VectorMasterDrawable(getContext(),
                    getResources().getIdentifier("ic_0" + chineseCharactersOrderUnicode.get(i),
                            "drawable", getContext().getPackageName()));
            //VectorMasterDrawable vectorMasterDrawable = new VectorMasterDrawable(getContext(), R.drawable.ic_05e74);
            GroupModel outline = vectorMasterDrawable.getGroupModelByName("Hanzi");
            for (PathModel model : outline.getPathModels()) {
                chineseCharacterStrokeList.add(model.getPath());
            }
            chineseCharactersList1.add(new HanjaCharacter(chineseCharacterStrokeList));
            chineseCharacterStrokeList.clear();
        }


    }

}
