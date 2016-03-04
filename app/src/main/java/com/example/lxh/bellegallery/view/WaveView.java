package com.example.lxh.bellegallery.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lxh on 2016/3/4.
 */
public class WaveView extends LinearLayout {

    private Path mRevealPath;

    private float mRadius;

    private float startX;

    private float startY;

    private boolean isClip;


    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mRevealPath = new Path();
    }

    public void setRevealRadius(float radius) {
        this.mRadius = radius;
        this.invalidate();
    }

    public float getRevealRadius() {
        return this.mRadius;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public void setIsClip(boolean isClip) {
        this.isClip = isClip;
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (!isClip) {
            return super.drawChild(canvas, child, drawingTime);
        } else {
            int state = canvas.save();
            mRevealPath.reset();
            mRevealPath.addCircle(startX, startY, mRadius, Path.Direction.CW);
            canvas.clipPath(mRevealPath);
            boolean isInvalided = super.drawChild(canvas, child, drawingTime);
            canvas.restoreToCount(state);
            return isInvalided;
        }
    }

}
