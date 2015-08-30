// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards;

import android.view.View;
import android.widget.ImageView;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.*;

public class SubPhaseController extends SCIEventSinkSwigBase
    implements android.view.View.OnClickListener
{
    public static interface SubControllerImpl
    {

        public abstract void setSubPhaseBIsLouder(boolean flag);
    }


    public SubPhaseController(View view, SCISubCalibrator scisubcalibrator, SubControllerImpl subcontrollerimpl)
    {
        calibrator = scisubcalibrator;
        playPauseButton = (SonosImageView)view.findViewById(0x7f0a00d5);
        playPauseButton.setOnClickListener(this);
        aButton = (ImageView)view.findViewById(0x7f0a0197);
        bButton = (ImageView)view.findViewById(0x7f0a0198);
        listener = subcontrollerimpl;
    }

    public void dispatchEvent(SCIObj sciobj, String s)
    {
        if(calibrator != null)
        {
            com.sonos.sclib.SCISubCalibrator.Phase phase = calibrator.getPhase();
            if(phase == com.sonos.sclib.SCISubCalibrator.Phase.PHASE_A)
            {
                aButton.setImageState(STATE_PLAY_SET, true);
                bButton.setImageState(STATE_PAUSE_SET, true);
            } else
            if(phase == com.sonos.sclib.SCISubCalibrator.Phase.PHASE_B)
            {
                aButton.setImageState(STATE_PAUSE_SET, true);
                bButton.setImageState(STATE_PLAY_SET, true);
            } else
            {
                aButton.setImageState(STATE_PAUSE_SET, true);
                bButton.setImageState(STATE_PAUSE_SET, true);
            }
        }
    }

    public void onClick(View view)
    {
        if(view.getId() == 0x7f0a00d5)
            if(bPaused)
            {
                if(calibrator != null)
                    calibrator.play();
                bPaused = false;
                playPauseButton.setImageResource(0x7f060039);
            } else
            {
                if(calibrator != null)
                    calibrator.stop();
                bPaused = true;
                playPauseButton.setImageResource(0x7f06003a);
            }
        if(view.getId() != 0x7f0a0056) goto _L2; else goto _L1
_L1:
        listener.setSubPhaseBIsLouder(false);
_L4:
        return;
_L2:
        if(view.getId() == 0x7f0a0057)
            listener.setSubPhaseBIsLouder(false);
        else
        if(view.getId() == 0x7f0a0058)
            listener.setSubPhaseBIsLouder(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void start()
    {
        bPaused = true;
        if(calibrator != null)
            calibrator.subscribe(this);
    }

    public void stop()
    {
        if(calibrator != null)
        {
            calibrator.unsubscribe(this);
            calibrator.stop();
            calibrator = null;
        }
    }

    protected static int STATE_PAUSE_SET[];
    protected static int STATE_PLAY_SET[];
    ImageView aButton;
    ImageView bButton;
    boolean bPaused;
    SCISubCalibrator calibrator;
    SubControllerImpl listener;
    SonosImageView playPauseButton;

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x7f01002d;
        STATE_PLAY_SET = ai;
        int ai1[] = new int[1];
        ai1[0] = 0x7f01002f;
        STATE_PAUSE_SET = ai1;
    }
}
