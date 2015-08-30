// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.os.Handler;

// Referenced classes of package com.sonos.acr.util:
//            SensorEventWrapper

public class MockSensorManager
{

    public MockSensorManager()
    {
    }

    public void doShake()
    {
        if(listener != null)
        {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                public void run()
                {
                    SensorEventWrapper sensoreventwrapper = new SensorEventWrapper(150F, 0.0F, 0.0F);
                    listener.onInternalSensorChanged(sensoreventwrapper);
                }

                final MockSensorManager this$0;

            
            {
                this$0 = MockSensorManager.this;
                super();
            }
            }
, 80L);
            handler.postDelayed(new Runnable() {

                public void run()
                {
                    SensorEventWrapper sensoreventwrapper = new SensorEventWrapper(-150F, 0.0F, 0.0F);
                    listener.onInternalSensorChanged(sensoreventwrapper);
                }

                final MockSensorManager this$0;

            
            {
                this$0 = MockSensorManager.this;
                super();
            }
            }
, 160L);
            handler.postDelayed(new Runnable() {

                public void run()
                {
                    SensorEventWrapper sensoreventwrapper = new SensorEventWrapper(150F, 0.0F, 0.0F);
                    listener.onInternalSensorChanged(sensoreventwrapper);
                }

                final MockSensorManager this$0;

            
            {
                this$0 = MockSensorManager.this;
                super();
            }
            }
, 240L);
            handler.postDelayed(new Runnable() {

                public void run()
                {
                    SensorEventWrapper sensoreventwrapper = new SensorEventWrapper(-150F, 0.0F, 0.0F);
                    listener.onInternalSensorChanged(sensoreventwrapper);
                }

                final MockSensorManager this$0;

            
            {
                this$0 = MockSensorManager.this;
                super();
            }
            }
, 320L);
            handler.postDelayed(new Runnable() {

                public void run()
                {
                    SensorEventWrapper sensoreventwrapper = new SensorEventWrapper(150F, 0.0F, 0.0F);
                    listener.onInternalSensorChanged(sensoreventwrapper);
                }

                final MockSensorManager this$0;

            
            {
                this$0 = MockSensorManager.this;
                super();
            }
            }
, 400L);
            handler.postDelayed(new Runnable() {

                public void run()
                {
                    SensorEventWrapper sensoreventwrapper = new SensorEventWrapper(-150F, 0.0F, 0.0F);
                    listener.onInternalSensorChanged(sensoreventwrapper);
                }

                final MockSensorManager this$0;

            
            {
                this$0 = MockSensorManager.this;
                super();
            }
            }
, 480L);
        }
    }

    public void registerListener(SensorEventWrapper.ISensorEventListener isensoreventlistener)
    {
        listener = isensoreventlistener;
    }

    SensorEventWrapper.ISensorEventListener listener;
}
