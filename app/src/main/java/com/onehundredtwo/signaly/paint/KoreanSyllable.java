package com.onehundredtwo.signaly.paint;

import android.graphics.Path;
import android.graphics.PathMeasure;

import java.util.Arrays;

public class KoreanSyllable {

    private int index = 0;
    protected Path[] path;
    private int size = 0;
    private PathMeasure pathMeasure = new PathMeasure();
    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private boolean[] matchStart;
    private boolean[] matchControlPoint;
    private boolean[] matchEnd;
    private boolean finished = false;


    public KoreanSyllable(Path[] path) {
        this.path = path;
        this.size = path.length;
        matchStart = new boolean[this.size];
        matchControlPoint = new boolean[this.size];
        matchEnd = new boolean[this.size];
        Arrays.fill(matchStart, false);
        Arrays.fill(matchControlPoint, false);
        Arrays.fill(matchEnd, false);
    }

    public boolean nextIndex() {
        if(index < size - 1) {
            index++;
            return true;
        }
        finished = true;
        return false;
    }

    public boolean prevIndex() {
        if(index > 0) {
            index--;
            return true;
        }
        return false;
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return size;
    }

    public Path[] getPaths() {
        return path;
    }

    public Path getCurrPath() {
        return path[index];
    }

    public boolean matchStart(float x, float y, float r) {
        pathMeasure.setPath(this.path[index], false);
        pathMeasure.getPosTan(0.0f, pos, tan);
        if (Math.pow(x - pos[0], 2) + Math.pow(y - pos[1], 2) <= Math.pow(r, 2)) {
            matchStart[index] = true;
            return true;
        }
        return false;
    }

    public boolean matchControlPoint(float x, float y, float r) {
        pathMeasure.setPath(this.path[index], false);
        pathMeasure.getPosTan(pathMeasure.getLength()/2, pos, tan);
        if (Math.pow(x - pos[0], 2) + Math.pow(y - pos[1], 2) <= Math.pow(r, 2)) {
            matchControlPoint[index] = true;
            return true;
        }
        return false;
    }

    public boolean matchEnd(float x, float y, float r) {
        pathMeasure.setPath(this.path[index], false);
        pathMeasure.getPosTan(pathMeasure.getLength(), pos, tan);
        if (Math.pow(x - pos[0], 2) + Math.pow(y - pos[1], 2) <= Math.pow(r, 2)) {
            matchEnd[index] = true;
            return true;
        }
        return false;
    }

    public void matchReset() {
        matchStart[index] = false;
        matchControlPoint[index] = false;
        matchEnd[index] = false;
    }

    public boolean isMatchedStart() {
        return matchStart[index];
    }

    public boolean isMatchedControlPoint() {
        return matchControlPoint[index];
    }

    public boolean isMatched() {
        return matchStart[index] && matchEnd[index];
    }

    public boolean isFinished() {
        return finished;
    }

    public void reset() {
        Arrays.fill(matchStart, false);
        Arrays.fill(matchControlPoint, false);
        Arrays.fill(matchEnd, false);
        index = 0;
        finished = false;
    }



}
