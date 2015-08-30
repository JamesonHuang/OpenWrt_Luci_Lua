// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.android;

import android.os.Environment;
import ch.qos.logback.core.util.EnvUtil;
import ch.qos.logback.core.util.OptionHelper;
import java.io.File;

public abstract class CommonPathUtil
{

    public CommonPathUtil()
    {
    }

    public static String getAssetsDirectoryPath()
    {
        return "assets";
    }

    public static String getDatabaseDirectoryPath(String s)
    {
        String s1;
        if(EnvUtil.isAndroidOS())
            s1 = Environment.getDataDirectory().getAbsolutePath();
        else
            s1 = "/data";
        return (new StringBuilder()).append(s1).append("/data/").append(s).append("/databases").toString();
    }

    public static String getExternalStorageDirectoryPath()
    {
        if(!EnvUtil.isAndroidOS()) goto _L2; else goto _L1
_L1:
        String s = Environment.getExternalStorageDirectory().getAbsolutePath();
_L4:
        return s;
_L2:
        s = OptionHelper.getEnv("EXTERNAL_STORAGE");
        if(s == null)
            s = "/sdcard";
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String getFilesDirectoryPath(String s)
    {
        String s1;
        if(EnvUtil.isAndroidOS())
            s1 = Environment.getDataDirectory().getAbsolutePath();
        else
            s1 = "/data";
        return (new StringBuilder()).append(s1).append("/data/").append(s).append("/files").toString();
    }

    public static String getMountedExternalStorageDirectoryPath()
    {
        String s;
        if(EnvUtil.isAndroidOS())
        {
            s = null;
            String s1 = Environment.getExternalStorageState();
            if(s1.equals("mounted") || s1.equals("mounted_ro"))
                s = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else
        {
            s = "/mnt/sdcard";
        }
        return s;
    }

    private static final String ASSETS_DIRECTORY = "assets";
}
