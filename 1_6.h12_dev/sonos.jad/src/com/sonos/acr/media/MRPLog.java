// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import com.sonos.acr.util.SLog;

public class MRPLog
{

    public MRPLog()
    {
    }

    public static void e(String s, String s1)
    {
        SLog.e(s, s1);
    }

    public static void i(String s, String s1)
    {
        SLog.w(s, s1);
    }

    public static void w(String s, String s1)
    {
        SLog.w(s, s1);
    }
}
