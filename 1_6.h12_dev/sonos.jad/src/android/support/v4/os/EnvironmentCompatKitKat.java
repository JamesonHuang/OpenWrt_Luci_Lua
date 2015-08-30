// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.Environment;
import java.io.File;

class EnvironmentCompatKitKat
{

    EnvironmentCompatKitKat()
    {
    }

    public static String getStorageState(File file)
    {
        return Environment.getStorageState(file);
    }
}
