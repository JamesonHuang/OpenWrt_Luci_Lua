// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.hardware.SensorEvent;

public class SensorEventWrapper
{
    public static interface ISensorEventListener
    {

        public abstract void onInternalSensorChanged(SensorEventWrapper sensoreventwrapper);
    }


    public SensorEventWrapper(float f, float f1, float f2)
    {
        wrappedEvent = null;
        mockSensorValues = new float[3];
        mockSensorValues[0] = f;
        mockSensorValues[1] = f1;
        mockSensorValues[2] = f2;
    }

    public SensorEventWrapper(SensorEvent sensorevent)
    {
        wrappedEvent = sensorevent;
    }

    public float[] getValues()
    {
        float af[];
        if(wrappedEvent != null)
            af = wrappedEvent.values;
        else
            af = mockSensorValues;
        return af;
    }

    private float mockSensorValues[];
    private SensorEvent wrappedEvent;
}
