// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.util.Log;

// Referenced classes of package com.splunk.mint:
//            Mint

public class Logger
{

    public Logger()
    {
    }

    public static void logError(String s)
    {
        if(s != null)
            Log.e("Mint", s);
    }

    public static void logInfo(String s)
    {
        if(Mint.DEBUG && s != null)
            Log.i("Mint", s);
    }

    public static void logWarning(String s)
    {
        if(s != null)
            Log.w("Mint", s);
    }
}
