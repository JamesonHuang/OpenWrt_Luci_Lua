// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.*;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.util.TimeUtils;
import com.sonos.acr.view.SonosProgressBar;
import com.sonos.sclib.SCINowPlayingTransport;

// Referenced classes of package com.sonos.acr.nowplaying.views:
//            TransportView

public class ProgressTransportView extends FrameLayout
    implements TransportView, android.widget.SeekBar.OnSeekBarChangeListener
{

    public ProgressTransportView(Context context)
    {
        this(context, null);
    }

    public ProgressTransportView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ProgressTransportView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.ProgressTransportView);
        int j = typedarray.getResourceId(0, 0x7f03005b);
        typedarray.recycle();
        LayoutInflater.from(context).inflate(j, this);
        progressWithThumb = (SonosProgressBar)findViewById(0x7f0a012e);
        remainingTime = (TextView)findViewById(0x7f0a0130);
        elapsedTime = (TextView)findViewById(0x7f0a012f);
        trackUnfilledBack = (FrameLayout)findViewById(0x7f0a012c);
        progressWithThumb.setOnSeekBarChangeListener(this);
        progressWithThumbTrack = (SonosProgressBar)findViewById(0x7f0a012d);
        if(progressWithThumbTrack != null)
        {
            progressWithThumbTrack.setIsUserInteractable(false);
            progressWithThumbTrack.setEnabled(false);
            progressWithThumbTrack.setClickable(false);
            progressWithThumbTrack.setProgressable(false);
        }
    }

    private void updateProgessTrack()
    {
        if(progressWithThumbTrack != null && progressWithThumb != null)
        {
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)progressWithThumbTrack.getLayoutParams();
            layoutparams.rightMargin = Math.max(0, (int)(((double)progressWithThumb.getProgress() / (double)progressWithThumb.getMax()) * (double)getResources().getDimensionPixelSize(0x7f09005e)));
            progressWithThumbTrack.setLayoutParams(layoutparams);
        }
    }

    private void updateTimes(long l)
    {
        if(elapsedTime != null)
            elapsedTime.setText(TimeUtils.toShortTime(l));
        long l1 = (long)currentTrackDuration - l;
        if(remainingTime != null)
            remainingTime.setText(TimeUtils.toShortTime(l1, true));
    }

    public long getProgress()
    {
        long l;
        if(progressWithThumb != null)
            l = progressWithThumb.getProgress();
        else
            l = 0L;
        return l;
    }

    public void onProgressChange(SCINowPlayingTransport scinowplayingtransport, long l, boolean flag)
    {
        currentTrackDuration = scinowplayingtransport.getCurrentTrackDuration();
        updateTimes(l);
        progressWithThumb.setMax(currentTrackDuration);
        progressWithThumb.setProgress((int)l);
    }

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
        if(flag)
            updateTimes(i);
        if(progressWithThumbTrack != null)
        {
            progressWithThumbTrack.setMax(progressWithThumb.getMax());
            progressWithThumbTrack.setProgress(progressWithThumb.getProgress());
        }
        updateProgessTrack();
    }

    public void onStartTrackingTouch(SeekBar seekbar)
    {
        if(listener != null)
            listener.onStartTrackingTouch(this);
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
        if(listener != null)
            listener.onStopTrackingTouch(this, seekbar.getProgress());
    }

    public void setTransportViewController(TransportView.TransportEventListener transporteventlistener)
    {
        listener = transporteventlistener;
    }

    public void updateView(NowPlaying nowplaying)
    {
        boolean flag = true;
        SCINowPlayingTransport scinowplayingtransport = nowplaying.getTransport();
        boolean flag1 = scinowplayingtransport.isTrackPositionInfoAvailable();
        boolean flag2 = scinowplayingtransport.isSeekEnabled();
        SonosProgressBar sonosprogressbar = progressWithThumb;
        boolean flag3;
        SonosProgressBar sonosprogressbar1;
        if(flag1 && flag2)
            flag3 = flag;
        else
            flag3 = false;
        sonosprogressbar.setEnabled(flag3);
        progressWithThumb.setProgressable(flag1);
        sonosprogressbar1 = progressWithThumb;
        if(!flag1 || !flag2)
            flag = false;
        sonosprogressbar1.setIsUserInteractable(flag);
        if(trackUnfilledBack != null)
        {
            FrameLayout framelayout = trackUnfilledBack;
            SonosProgressBar sonosprogressbar2;
            int j;
            if(flag1)
                j = 0;
            else
                j = 8;
            framelayout.setVisibility(j);
        }
        if(progressWithThumbTrack != null)
        {
            sonosprogressbar2 = progressWithThumbTrack;
            int i;
            if(flag1)
                i = 0;
            else
                i = 8;
            sonosprogressbar2.setVisibility(i);
        }
        if(!flag1) goto _L2; else goto _L1
_L1:
        progressWithThumb.setVisibility(0);
        if(elapsedTime != null)
            elapsedTime.setVisibility(0);
        if(remainingTime != null)
            remainingTime.setVisibility(0);
_L4:
        return;
_L2:
        progressWithThumb.setVisibility(8);
        if(elapsedTime != null)
            elapsedTime.setVisibility(8);
        if(remainingTime != null)
            remainingTime.setVisibility(8);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int currentTrackDuration;
    TextView elapsedTime;
    TransportView.TransportEventListener listener;
    SonosProgressBar progressWithThumb;
    SonosProgressBar progressWithThumbTrack;
    TextView remainingTime;
    FrameLayout trackUnfilledBack;
}
