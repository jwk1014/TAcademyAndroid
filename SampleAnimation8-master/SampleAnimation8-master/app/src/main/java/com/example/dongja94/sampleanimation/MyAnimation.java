package com.example.dongja94.sampleanimation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by dongja94 on 2015-10-16.
 */
public class MyAnimation extends Animation {
    int width, height;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.width = parentWidth - width;
        this.height = parentHeight - height;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float x = interpolatedTime * width;
        float y = interpolatedTime * interpolatedTime * height;
        t.getMatrix().setTranslate(x, y);
    }
}
