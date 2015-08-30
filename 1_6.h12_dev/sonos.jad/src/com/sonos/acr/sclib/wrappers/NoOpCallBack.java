// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.wrappers;

import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCIOpCBSwigBase;

public class NoOpCallBack extends SCIOpCBSwigBase
{

    public NoOpCallBack()
    {
    }

    public NoOpCallBack(String s, String s1, String s2)
    {
        logTag = s;
        succeSLogString = s1;
        errorLogString = s2;
    }

    public void _operationComplete(long l, int i)
    {
        if(logTag == null)
            logTag = com/sonos/acr/sclib/wrappers/NoOpCallBack.getSimpleName();
        if(i == 0)
        {
            String s2 = logTag;
            String s3;
            if(succeSLogString == null)
                s3 = "Operation Success";
            else
                s3 = succeSLogString;
            SLog.d(s2, s3);
        } else
        {
            String s = logTag;
            String s1;
            if(errorLogString == null)
                s1 = "Operation Failed";
            else
                s1 = errorLogString;
            SLog.e(s, s1);
        }
    }

    String errorLogString;
    String logTag;
    String succeSLogString;
}
