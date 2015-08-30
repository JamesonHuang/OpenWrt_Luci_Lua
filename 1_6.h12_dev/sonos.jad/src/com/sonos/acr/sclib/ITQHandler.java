// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import android.media.ToneGenerator;
import android.os.Handler;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCILibrary;
import com.sonos.sclib.SCLibCallUIThreadCallback;
import java.util.Date;

public class ITQHandler extends SCLibCallUIThreadCallback
    implements Runnable
{

    public ITQHandler(Handler handler)
    {
        sciLibrary = null;
        mainThreadHandler = handler;
        boolean flag;
        if(SonosApplication.RELEASE_TYPE == com.sonos.acr.application.SonosApplication.ReleaseType.PROD)
            flag = true;
        else
            flag = false;
        prodRelease = flag;
        tg = new ToneGenerator(5, 100);
    }

    private static void finishProfiling(long l)
    {
        lastCallTime = getMicrosecondTime();
        if(lastCallTime - l > 10000L)
            SLog.i("PROFILE", (new StringBuilder()).append("UI callback took ").append(lastCallTime - l).append(" Usecs").toString());
    }

    private static long getMicrosecondTime()
    {
        return System.nanoTime() / 1000L;
    }

    private static long startProfiling()
    {
        long l = getMicrosecondTime();
        if(lastCallTime != 0L)
            if(l - lastCallTime < 10000L)
            {
                SLog.i("PROFILE", (new StringBuilder()).append("re-calling UI callback after ").append(l - lastCallTime).append(" Usecs").toString());
                if(baseTime == 0L)
                    baseTime = l;
            } else
            {
                long l1 = l - baseTime;
                baseTime = 0L;
                SLog.i("PROFILE", (new StringBuilder()).append("Accumulated time in SCLIB callbacks on UI thread is ").append(l1).append(" Usecs").toString());
            }
        return l;
    }

    public void callSCLibOnUIThread()
    {
        mainThreadHandler.post(this);
    }

    public void run()
    {
        long l1;
        long l = System.currentTimeMillis();
        if(sciLibrary != null)
            sciLibrary.SCLibUIThreadCallback();
        else
            SLog.e("ITQHandler", "sclib not initialized yet");
        l1 = System.currentTimeMillis() - l;
        if(!debuggable) goto _L2; else goto _L1
_L1:
        if(l1 > 1000L)
        {
            SLog.e("ITQHandler", (new StringBuilder()).append("Callback took ").append(l1).append("ms").toString());
            tg.startTone(94);
        }
_L4:
        return;
_L2:
        if(!prodRelease && l1 > 1000L)
            SLog.e("ITQHandler", (new StringBuilder()).append("Callback took ").append(l1).append("ms").toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setSCILibrary(SCILibrary scilibrary)
    {
        sciLibrary = scilibrary;
    }

    private static long baseTime = 0L;
    private static long lastCallTime = 0L;
    private static Date timer = new Date();
    private final boolean debuggable = SonosApplication.isDebuggable();
    private final Handler mainThreadHandler;
    private final boolean prodRelease;
    private SCILibrary sciLibrary;
    private ToneGenerator tg;

}
