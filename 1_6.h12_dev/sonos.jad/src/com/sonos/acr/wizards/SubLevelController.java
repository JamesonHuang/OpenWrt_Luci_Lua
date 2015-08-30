// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards;

import android.view.View;
import android.widget.RadioGroup;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.SCISubCalibrator;

public class SubLevelController
    implements android.view.View.OnClickListener, android.widget.RadioGroup.OnCheckedChangeListener
{

    public SubLevelController(View view, SCISubCalibrator scisubcalibrator)
    {
        rootView = view;
        calibrator = scisubcalibrator;
        playPauseButton = (SonosImageView)view.findViewById(0x7f0a00d5);
        playPauseButton.setOnClickListener(this);
        levelGroup = (RadioGroup)view.findViewById(0x7f0a0195);
        levelGroup.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(RadioGroup radiogroup, int i)
    {
        View view = rootView.findViewById(i);
        if(calibrator != null)
            calibrator.setSubLevelIndex(Integer.valueOf(view.getTag().toString()).intValue());
    }

    public void onClick(View view)
    {
        if(view.getId() == 0x7f0a00d5)
            if(paused)
            {
                calibrator.play();
                paused = false;
                playPauseButton.setImageResource(0x7f060039);
            } else
            {
                calibrator.stop();
                paused = true;
                playPauseButton.setImageResource(0x7f06003a);
            }
    }

    public void start()
    {
        paused = true;
        levelGroup.check(0x7f0a0196);
    }

    public void stop()
    {
        if(calibrator != null)
            calibrator.stop();
    }

    private SCISubCalibrator calibrator;
    private RadioGroup levelGroup;
    private boolean paused;
    private SonosImageView playPauseButton;
    private View rootView;
}
