package com.balaji66.dayDreamScreenSaver;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.view.View;

public class DayDreamCustomView extends View {
    private final Path path1 = new Path();
    private final Path path2 = new Path();
    private final Path path3 = new Path();
    private final Path path4 = new Path();
    private final Paint paint1 = new Paint();
    private final Paint paint2 = new Paint();
    private float length;
    private int screenWidth, screenHeight;

    public DayDreamCustomView(Context context, int screenWidth, int screenHeight) {
        super(context);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        init();
    }


    private static PathEffect createPathEffect(float pathLength, float phase) {
        return new DashPathEffect(new float[]{pathLength, pathLength},
                Math.max(phase * pathLength, (float) 0.0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        canvas.drawPath(path1, paint1);
        canvas.drawPath(path2, paint2);
        canvas.drawPath(path3, paint1);
        canvas.drawPath(path4, paint2);
    }

    private void init() {
        paint1.setColor(Color.BLUE);
        paint1.setStrokeWidth(10);
        paint1.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.WHITE);
        paint2.setStrokeWidth(10);
        paint2.setStyle(Paint.Style.STROKE);
        drawPaint(path1, (screenWidth) / 5, 1, screenWidth / 5, screenHeight);
        drawPaint(path2, (screenWidth * 2) / 5, screenHeight, (screenWidth * 2) / 5, 1);
        drawPaint(path3, (screenWidth * 3) / 5, 1, (screenWidth * 3) / 5, screenHeight);
        drawPaint(path4, (screenWidth * 4) / 5, screenHeight, (screenWidth * 4) / 5, 1);
    }

    public void drawPaint(Path path, int startPoint_of_X, int startPoint_of_Y, int fixed_X, int fixed_Y) {
        path.moveTo(startPoint_of_X, startPoint_of_Y);
        for (int i = 0; i < 500; i++) {
            path.lineTo(fixed_X, fixed_Y);
        }
        // Measure the path
        PathMeasure measure = new PathMeasure(path, false);
        length = measure.getLength();
        float[] intervals = new float[]{length, length};
        createObjectAnimator();
    }

    public void createObjectAnimator() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(DayDreamCustomView.this, "phase", 1.0f, 0.0f);
        anim.setDuration(5000);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.start();
    }

    //is called by animator object
    public void setPhase(float phase) {
        paint1.setPathEffect(createPathEffect(length, phase));
        paint2.setPathEffect(createPathEffect(length, phase));
        invalidate();//will call onDraw
    }

}
