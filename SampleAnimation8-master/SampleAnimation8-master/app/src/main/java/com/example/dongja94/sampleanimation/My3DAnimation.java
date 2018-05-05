package com.example.dongja94.sampleanimation;

import android.graphics.Camera;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by dongja94 on 2015-10-16.
 */
public class My3DAnimation extends Animation {

    Camera mCamera;

    public My3DAnimation() {
        mCamera = new Camera();
    }

    int centerX, centerY;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX = width / 2;
        centerY = height / 2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
//        super.applyTransformation(interpolatedTime, t);
        mCamera.save();

        mCamera.rotateY(45 * interpolatedTime);

        mCamera.getMatrix(t.getMatrix());

        mCamera.restore();

        t.getMatrix().preTranslate(-centerX, -centerY);
        t.getMatrix().postTranslate(centerX, centerY);
    }
}
