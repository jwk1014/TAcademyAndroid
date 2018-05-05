package com.example.dongja94.sampleoffscreencanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dongja94 on 2015-10-15.
 */
public class PaintView extends View {

    Bitmap mOffBitmap;
    Canvas mOffCanvas;
    Paint mPaint, mOffPaint;

    public PaintView(Context context) {
        super(context);
        init();
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mOffPaint = new Paint();
        mOffPaint.setColor(Color.RED);
        mOffPaint.setStrokeWidth(3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (mOffBitmap == null) {
            mOffBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            mOffCanvas = new Canvas(mOffBitmap);
        }

        if (mOffBitmap.getWidth() != width || mOffBitmap.getHeight() != height) {
            Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            c.drawBitmap(mOffBitmap, 0, 0, mOffPaint);

            mOffBitmap.recycle();

            mOffBitmap = b;
            mOffCanvas = c;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mOffBitmap, 0, 0, mPaint);
    }

    float oldX, oldY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch(action) {
            case MotionEvent.ACTION_DOWN :
                oldX = event.getX();
                oldY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE :
                float x = event.getX();
                float y = event.getY();
                mOffCanvas.drawLine(oldX, oldY, x, y, mOffPaint);
                invalidate();
                oldX = x;
                oldY = y;
                return true;
            case MotionEvent.ACTION_UP :
                return true;
        }
        return super.onTouchEvent(event);
    }
}
