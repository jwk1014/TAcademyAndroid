package com.example.dongja94.sampleoffscreencanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-15.
 */
public class PathPaintView extends View {
    public static class PaintItem {
        Paint mPaint;
        Path mPath;
    }

    List<PaintItem> drawStack = new ArrayList<PaintItem>();
    Paint mDrawPaint;
    PaintItem mCurrentItem;

    public PathPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDrawPaint = new Paint();
        mDrawPaint.setColor(Color.RED);
        mDrawPaint.setStrokeWidth(3);
        mDrawPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < drawStack.size(); i++) {
            PaintItem item = drawStack.get(i);
            canvas.drawPath(item.mPath, item.mPaint);
        }
    }

    public void undo() {
        if (drawStack.size() > 0) {
            drawStack.remove(drawStack.size() - 1);
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN :
                mCurrentItem = new PaintItem();
                mCurrentItem.mPaint = new Paint(mDrawPaint);
                mCurrentItem.mPath = new Path();
                mCurrentItem.mPath.moveTo(event.getX(), event.getY());
                drawStack.add(mCurrentItem);
                return true;
            case MotionEvent.ACTION_MOVE :
                mCurrentItem.mPath.lineTo(event.getX(),event.getY());
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }
}
