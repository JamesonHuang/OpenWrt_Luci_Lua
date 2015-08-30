// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.util.Log;

// Referenced classes of package com.splunk.mint:
//            MintLogLevel, ActionLog, DataSaver

public class MintLog
{

    public MintLog()
    {
    }

    public static void d(String s, String s1)
    {
        Log.d(s, s1);
        ActionLog.createLog((new StringBuilder()).append(s).append(": ").append(s1).toString(), MintLogLevel.Debug).save(new DataSaver());
    }

    public static void e(String s, String s1)
    {
        Log.e(s, s1);
        ActionLog.createLog((new StringBuilder()).append(s).append(": ").append(s1).toString(), MintLogLevel.Error).save(new DataSaver());
    }

    public static void i(String s, String s1)
    {
        Log.i(s, s1);
        ActionLog.createLog((new StringBuilder()).append(s).append(": ").append(s1).toString(), MintLogLevel.Info).save(new DataSaver());
    }

    public static void v(String s, String s1)
    {
        Log.v(s, s1);
        ActionLog.createLog((new StringBuilder()).append(s).append(": ").append(s1).toString(), MintLogLevel.Verbose).save(new DataSaver());
    }

    public static void w(String s, String s1)
    {
        Log.w(s, s1);
        ActionLog.createLog((new StringBuilder()).append(s).append(": ").append(s1).toString(), MintLogLevel.Warning).save(new DataSaver());
    }
}
