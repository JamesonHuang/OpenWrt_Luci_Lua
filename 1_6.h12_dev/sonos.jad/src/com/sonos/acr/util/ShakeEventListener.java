// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.hardware.*;

// Referenced classes of package com.sonos.acr.util:
//            SensorEventWrapper, FeedbackHandler

public class ShakeEventListener
    implements SensorEventWrapper.ISensorEventListener, SensorEventListener
{
    public static interface OnShakeListener
    {

        public abstract void onShake();
    }


    public ShakeEventListener(OnShakeListener onshakelistener)
    {
        float af[] = new float[3];
        af[0] = 0.0F;
        af[1] = 0.0F;
        af[2] = 0.0F;
        mGravity = af;
        float af1[] = new float[3];
        af1[0] = 0.0F;
        af1[1] = 0.0F;
        af1[2] = 0.0F;
        mLinearAcceleration = af1;
        startTime = 0L;
        moveCount = 0;
        feedbackHandler = null;
        mShakeListener = onshakelistener;
    }

    private float getMaxCurrentLinearAcceleration()
    {
        float f = mLinearAcceleration[0];
        if(mLinearAcceleration[1] > f)
            f = mLinearAcceleration[1];
        if(mLinearAcceleration[2] > f)
            f = mLinearAcceleration[2];
        return f;
    }

    private void resetShakeDetection()
    {
        startTime = 0L;
        moveCount = 0;
    }

    private void setCurrentAcceleration(SensorEventWrapper sensoreventwrapper)
    {
        mGravity[0] = 0.8F * mGravity[0] + 0.2F * sensoreventwrapper.getValues()[0];
        mGravity[1] = 0.8F * mGravity[1] + 0.2F * sensoreventwrapper.getValues()[1];
        mGravity[2] = 0.8F * mGravity[2] + 0.2F * sensoreventwrapper.getValues()[2];
        mLinearAcceleration[0] = sensoreventwrapper.getValues()[0] - mGravity[0];
        mLinearAcceleration[1] = sensoreventwrapper.getValues()[1] - mGravity[1];
        mLinearAcceleration[2] = sensoreventwrapper.getValues()[2] - mGravity[2];
    }

    public void onAccuracyChanged(Sensor sensor, int i)
    {
    }

    public void onInternalSensorChanged(SensorEventWrapper sensoreventwrapper)
    {
        setCurrentAcceleration(sensoreventwrapper);
        if(getMaxCurrentLinearAcceleration() <= 5F) goto _L2; else goto _L1
_L1:
        long l;
        l = System.currentTimeMillis();
        if(startTime == 0L)
            startTime = l;
        if(l - startTime <= 500L) goto _L4; else goto _L3
_L3:
        resetShakeDetection();
_L2:
        return;
_L4:
        moveCount = 1 + moveCount;
        if(moveCount > 3)
        {
            mShakeListener.onShake();
            resetShakeDetection();
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void onSensorChanged(SensorEvent sensorevent)
    {
        onInternalSensorChanged(new SensorEventWrapper(sensorevent));
    }

    private static final int MAX_SHAKE_DURATION = 500;
    private static final int MIN_MOVEMENTS = 3;
    private static final int MIN_SHAKE_ACCELERATION = 5;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    public FeedbackHandler feedbackHandler;
    private float mGravity[];
    private float mLinearAcceleration[];
    private OnShakeListener mShakeListener;
    int moveCount;
    long startTime;
}
