// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCLibLogCallback;

class AndroidLoggerCB extends SCLibLogCallback
{

    AndroidLoggerCB()
    {
    }

    public void LogDebugMessage(String s, int i, String s1)
    {
        int j = 7 - i;
        if(j < 2)
            j = 2;
        SLog.log((new StringBuilder()).append("Sonos: ").append(s).toString(), j, s1);
    }
}
