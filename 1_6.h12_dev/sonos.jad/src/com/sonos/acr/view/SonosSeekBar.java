// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class SonosSeekBar extends SeekBar
{
    public static interface SonosSeekBarListener
        extends android.widget.SeekBar.OnSeekBarChangeListener
    {

        public abstract int onSonosSeekBarTappedToDecrease(SonosSeekBar sonosseekbar);

        public abstract int onSonosSeekBarTappedToIncrease(SonosSeekBar sonosseekbar);
    }

    private static final class SonosSeekBarState extends Enum
    {

        public static SonosSeekBarState valueOf(String s)
        {
            return (SonosSeekBarState)Enum.valueOf(com/sonos/acr/view/SonosSeekBar$SonosSeekBarState, s);
        }

        public static SonosSeekBarState[] values()
        {
            return (SonosSeekBarState[])$VALUES.clone();
        }

        private static final SonosSeekBarState $VALUES[];
        public static final SonosSeekBarState DELAYED_DECISION_STATE;
        public static final SonosSeekBarState GRAB_STATE;
        public static final SonosSeekBarState IDLE_STATE;
        public static final SonosSeekBarState TAP_STATE;

        static 
        {
            IDLE_STATE = new SonosSeekBarState("IDLE_STATE", 0);
            TAP_STATE = new SonosSeekBarState("TAP_STATE", 1);
            DELAYED_DECISION_STATE = new SonosSeekBarState("DELAYED_DECISION_STATE", 2);
            GRAB_STATE = new SonosSeekBarState("GRAB_STATE", 3);
            SonosSeekBarState asonosseekbarstate[] = new SonosSeekBarState[4];
            asonosseekbarstate[0] = IDLE_STATE;
            asonosseekbarstate[1] = TAP_STATE;
            asonosseekbarstate[2] = DELAYED_DECISION_STATE;
            asonosseekbarstate[3] = GRAB_STATE;
            $VALUES = asonosseekbarstate;
        }

        private SonosSeekBarState(String s, int i)
        {
            super(s, i);
        }
    }


    public SonosSeekBar(Context context)
    {
        this(context, null);
    }

    public SonosSeekBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SonosSeekBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        int ai[] = new int[1];
        ai[0] = 0;
        activeStates = ai;
        additionalStates = NO_STATE;
        mSonosSeekBarUser = null;
        mSonosSeekBarState = SonosSeekBarState.IDLE_STATE;
        mTouchDeltaX = 0.0F;
        mDelayedDownEvent = null;
        mGrabMinXThres = 0.0F;
        mDelayedTapMinXThres = 0.0F;
        isUserInteractable = true;
        setFocusableInTouchMode(false);
    }

    private void doTapDecrease()
    {
        if(mSonosSeekBarUser != null)
            mSonosSeekBarUser.onSonosSeekBarTappedToDecrease(this);
        else
            incrementProgressBy(-1);
    }

    private void doTapIncrease()
    {
        if(mSonosSeekBarUser != null)
            mSonosSeekBarUser.onSonosSeekBarTappedToIncrease(this);
        else
            incrementProgressBy(1);
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[];
        if(activeStates != null)
        {
            ai = super.onCreateDrawableState(i + activeStates.length + additionalStates.length);
            mergeDrawableStates(ai, activeStates);
            mergeDrawableStates(ai, additionalStates);
        } else
        {
            ai = super.onCreateDrawableState(i);
        }
        return ai;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        int i;
        flag = true;
        i = 0;
        if(isUserInteractable) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        boolean flag1 = false;
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 52
    //                   0 105
    //                   1 259
    //                   2 359
    //                   3 259;
           goto _L3 _L4 _L5 _L6 _L5
_L3:
        int j;
        int k;
        boolean flag2;
        float f;
        int l;
        float f1;
        if(mSonosSeekBarState == SonosSeekBarState.TAP_STATE || flag1)
            flag2 = flag;
        else
            flag2 = false;
        if(!flag2)
            motionevent.offsetLocation(-mTouchDeltaX, 0.0F);
        if(flag2 || super.onTouchEvent(motionevent))
            i = ((flag) ? 1 : 0);
        flag = i;
        if(true) goto _L1; else goto _L4
_L4:
        activeStates[i] = 0x10100a7;
        invalidate();
        refreshDrawableState();
        l = getMeasuredWidth();
        f1 = (100F * motionevent.getX()) / (float)l - (float)getProgress();
        if(f1 > 15F)
        {
            mTouchDeltaX = 0.0F;
            mSonosSeekBarState = SonosSeekBarState.TAP_STATE;
            doTapIncrease();
        } else
        if(f1 < -15F)
        {
            mTouchDeltaX = 0.0F;
            mSonosSeekBarState = SonosSeekBarState.TAP_STATE;
            doTapDecrease();
        } else
        {
            mSonosSeekBarState = SonosSeekBarState.DELAYED_DECISION_STATE;
            flag1 = true;
            mTouchDeltaX = (f1 * (float)l) / 100F;
            mDelayedDownEvent = MotionEvent.obtain(motionevent);
            mGrabMinXThres = (l * 5) / 100;
            mDelayedTapMinXThres = (0.3F * (float)l) / 100F;
        }
          goto _L3
_L5:
        activeStates[i] = 0;
        refreshDrawableState();
        invalidate();
        if(mSonosSeekBarState == SonosSeekBarState.DELAYED_DECISION_STATE)
        {
            f = motionevent.getX() - mDelayedDownEvent.getX();
            if(f > mDelayedTapMinXThres)
                doTapIncrease();
            else
            if(f < -mDelayedTapMinXThres)
                doTapDecrease();
            flag1 = true;
        } else
        if(mSonosSeekBarState == SonosSeekBarState.TAP_STATE)
            flag1 = true;
        mSonosSeekBarState = SonosSeekBarState.IDLE_STATE;
          goto _L3
_L6:
        if(mSonosSeekBarState == SonosSeekBarState.DELAYED_DECISION_STATE)
        {
            if(Math.abs(motionevent.getX() - mDelayedDownEvent.getX()) > mGrabMinXThres)
                j = ((flag) ? 1 : 0);
            else
                j = 0;
            if(motionevent.getEventTime() - motionevent.getDownTime() > 300L)
                k = ((flag) ? 1 : 0);
            else
                k = 0;
            if(j != 0 || k != 0)
            {
                mSonosSeekBarState = SonosSeekBarState.GRAB_STATE;
                mDelayedDownEvent.setLocation(motionevent.getX(), motionevent.getY());
                super.onTouchEvent(mDelayedDownEvent);
            } else
            {
                flag1 = true;
            }
        }
          goto _L3
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        boolean flag;
        if(!isUserInteractable)
            flag = true;
        else
            flag = super.onTrackballEvent(motionevent);
        return flag;
    }

    public void setAdditionalStates(int ai[])
    {
        if(ai == null)
            ai = NO_STATE;
        additionalStates = ai;
        refreshDrawableState();
    }

    public void setIsUserInteractable(boolean flag)
    {
        isUserInteractable = flag;
    }

    public void setOnSonosSeekBarChangeListener(SonosSeekBarListener sonosseekbarlistener)
    {
        mSonosSeekBarUser = sonosseekbarlistener;
        super.setOnSeekBarChangeListener(sonosseekbarlistener);
    }

    private static final float DELAYED_TAP_DELTAX_THRESHOLD = 0.3F;
    private static final int GRAB_DELTAX_THRESHOLD = 15;
    private static final int GRAB_MINX_THRESHOLD = 5;
    private static final int GRAB_MIN_DELAY = 300;
    public static final int NO_STATE[] = new int[0];
    private static final int TAP_DELTAX_DEFAULT = 1;
    protected final int activeStates[];
    private int additionalStates[];
    private boolean isUserInteractable;
    private MotionEvent mDelayedDownEvent;
    private float mDelayedTapMinXThres;
    private float mGrabMinXThres;
    private SonosSeekBarState mSonosSeekBarState;
    private SonosSeekBarListener mSonosSeekBarUser;
    private float mTouchDeltaX;

}
