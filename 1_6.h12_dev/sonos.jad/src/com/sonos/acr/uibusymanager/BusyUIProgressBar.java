// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uibusymanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.sonos.acr.SystemEventSink;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.SCISystem;

public class BusyUIProgressBar extends ProgressBar
{

    public BusyUIProgressBar(Context context)
    {
        this(context, null);
    }

    public BusyUIProgressBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mHandler = new Handler();
        mBusyIndicatorEnabled = false;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.BusyUIProgressBar);
        runningOpIndicatorDelay = typedarray.getInt(0, 150);
        typedarray.recycle();
    }

    public boolean isBusyIndicatorEnabled()
    {
        return mBusyIndicatorEnabled;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mSystem = LibraryUtils.getSystem();
        if(isBusyIndicatorEnabled())
            subscribeToRunningOpChanges();
        else
            setVisibility(8);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        unsubscribeFromRunningOpChanges();
    }

    protected void onOpRunningCountChanged(SCISystem scisystem)
    {
        if(mRunningOpRunnable != null)
        {
            mHandler.removeCallbacks(mRunningOpRunnable);
            mRunningOpRunnable = null;
        }
        if(mBusyIndicatorEnabled)
        {
            if(scisystem.isRunningBackgroundOperations())
            {
                mRunningOpRunnable = new Runnable() {

                    public void run()
                    {
                        setVisibility(0);
                    }

                    final BusyUIProgressBar this$0;

            
            {
                this$0 = BusyUIProgressBar.this;
                super();
            }
                }
;
                mHandler.postDelayed(mRunningOpRunnable, runningOpIndicatorDelay);
            } else
            {
                setVisibility(8);
            }
        } else
        {
            setVisibility(8);
        }
    }

    public void setBusyIndicatorEnabled(boolean flag)
    {
        if(mBusyIndicatorEnabled != flag)
        {
            mBusyIndicatorEnabled = flag;
            if(flag)
                subscribeToRunningOpChanges();
            else
                unsubscribeFromRunningOpChanges();
        }
    }

    protected void subscribeToRunningOpChanges()
    {
        if(mSystemEventSink == null && mSystem != null)
        {
            if(mSystem.isRunningBackgroundOperations())
                setVisibility(0);
            if(mSystemEventSink == null)
                mSystemEventSink = new SystemEventSink() {

                    public void onOpRunningCountChanged(SCISystem scisystem)
                    {
                        if(mBusyIndicatorEnabled)
                            BusyUIProgressBar.this.onOpRunningCountChanged(scisystem);
                    }

                    final BusyUIProgressBar this$0;

            
            {
                this$0 = BusyUIProgressBar.this;
                super();
            }
                }
;
            mSystem.subscribe(mSystemEventSink);
        }
    }

    protected void unsubscribeFromRunningOpChanges()
    {
        if(mSystem != null && mSystemEventSink != null)
            mSystem.unsubscribe(mSystemEventSink);
    }

    private static final String LOG_TAG = com/sonos/acr/uibusymanager/BusyUIProgressBar.getName();
    private boolean mBusyIndicatorEnabled;
    private final Handler mHandler;
    private Runnable mRunningOpRunnable;
    private SCISystem mSystem;
    private SystemEventSink mSystemEventSink;
    private int runningOpIndicatorDelay;


}
