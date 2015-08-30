// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

// Referenced classes of package android.support.v4.os:
//            EnvironmentCompatKitKat

public class EnvironmentCompat
{

    public EnvironmentCompat()
    {
    }

    public static String getStorageState(File file)
    {
        if(android.os.Build.VERSION.SDK_INT < 19) goto _L2; else goto _L1
_L1:
        String s = EnvironmentCompatKitKat.getStorageState(file);
_L4:
        return s;
_L2:
        String s1;
        if(!file.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath()))
            break MISSING_BLOCK_LABEL_68;
        s1 = Environment.getExternalStorageState();
        s = s1;
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        Log.w("EnvironmentCompat", (new StringBuilder()).append("Failed to resolve canonical path: ").append(ioexception).toString());
        s = "unknown";
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";
}
