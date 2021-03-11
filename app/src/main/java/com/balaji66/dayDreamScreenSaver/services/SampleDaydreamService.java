package com.balaji66.dayDreamScreenSaver.services;

import android.graphics.Point;
import android.service.dreams.DreamService;

import com.balaji66.dayDreamScreenSaver.DayDreamCustomView;

public class SampleDaydreamService extends DreamService {

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // Allow user touch
        setInteractive(true);
        // Hide system UI
        setFullscreen(true);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        int robotWidth = screenSize.x;
        int robotHeight = screenSize.y;
        DayDreamCustomView dayDreamCustomView = new DayDreamCustomView(this, robotWidth, robotHeight);

        dayDreamCustomView.setFocusable(true);
        dayDreamCustomView.setClickable(true);
        setContentView(dayDreamCustomView);
        dayDreamCustomView.setOnClickListener(v -> finish());

    }

    @Override
    public void onDreamingStarted() {
        //daydream started
        super.onDreamingStarted();
    }

    @Override
    public void onDreamingStopped() {
        //daydream stopped
        super.onDreamingStopped();
    }

    @Override
    public void onDetachedFromWindow() {
        //tidy up
        super.onDetachedFromWindow();
    }

}

