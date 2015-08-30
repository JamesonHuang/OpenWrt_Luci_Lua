// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.os.Environment;
import com.sonos.acr.application.SonosApplication;
import java.io.*;
import java.util.Properties;

// Referenced classes of package com.sonos.acr.util:
//            SLog

public class DbgProp
{

    private DbgProp()
    {
        properties = new Properties();
        File file = new File(getSonosDebugDir(), "debug.properties");
        File file1 = new File(getSonosDataDir(), "debug.properties");
        if(file.exists())
        {
            SLog.i("DebugProperties", (new StringBuilder()).append("Attempting to read debug file at: ").append(file.getAbsolutePath()).toString());
            loadProperties(file);
            dumpProperties(file1);
        } else
        if(file1.exists())
        {
            SLog.i("DebugProperties", (new StringBuilder()).append("Properties file unavailable!  Trying local storage: ").append(file1.getAbsolutePath()).toString());
            loadProperties(file1);
        } else
        {
            SLog.i("DebugProperties", "No debug properties stored anywhere!");
        }
    }

    private void dumpProperties(File file)
    {
        FileOutputStream fileoutputstream = new FileOutputStream(file, false);
        properties.store(fileoutputstream, "");
        SLog.d("DebugProperties", (new StringBuilder()).append("Successfully dumped properties to: ").append(file.getAbsolutePath()).toString());
_L1:
        return;
        Exception exception;
        exception;
        SLog.e("DebugProperties", "Error dumping debug properties", exception);
          goto _L1
    }

    public static int get(String s, int i)
    {
        return Integer.valueOf(getInstance().properties.getProperty(s, Integer.toString(i))).intValue();
    }

    public static String get(String s, String s1)
    {
        return getInstance().properties.getProperty(s, s1);
    }

    public static boolean get(String s)
    {
        return getInstance().get(s, false);
    }

    public static DbgProp getInstance()
    {
        if(instance == null)
            instance = new DbgProp();
        return instance;
    }

    private File getSonosDataDir()
    {
        return new File(Environment.getDataDirectory(), (new StringBuilder()).append("data/").append(SonosApplication.getInstance().getPackageName()).toString());
    }

    public static File getSonosDebugDir()
    {
        return new File(Environment.getExternalStorageDirectory(), "sonos");
    }

    private void loadProperties(File file)
    {
        FileInputStream fileinputstream = new FileInputStream(file);
        properties.load(fileinputstream);
        properties.list(System.out);
_L1:
        return;
        Exception exception;
        exception;
        SLog.e("DebugProperties", "Error Loading debug properties", exception);
          goto _L1
    }

    public boolean get(String s, boolean flag)
    {
        return Boolean.valueOf(properties.getProperty(s, Boolean.toString(flag))).booleanValue();
    }

    public static final String CALL_PAUSE_TYPE = "callPauseType";
    public static final String CRASH_NATIVE_ON_SETTINGS = "crashNativeOnSettings";
    public static final String CRASH_ON_SETTINGS = "crashOnSettings";
    public static final String DEBUG_PROPERTIES_FILE_NAME = "debug.properties";
    public static final String DEBUG_SVG = "debugSVG";
    public static final String DEVELOPER_OPTIONS = "developerOptions";
    public static final String DUMP_TEXTVIEW_PNG = "dumpTextView";
    public static final String EXCEPTION_DUMP = "exceptionDump";
    public static final String GROUP_VOLUME_TIMEOUT = "groupVolumeTimeout";
    public static final String IS_MONKEY = "isMonkey";
    public static final String SPLASH_TIMEOUT = "splashTimeout";
    protected static final String TAG = "DebugProperties";
    public static final String TEXT_OVERFLOW_HIGHLIGHTING = "textOverflowHighlighting";
    public static final String TEXT_OVERFLOW_LOGGING = "textOverflowLogging";
    private static DbgProp instance;
    private Properties properties;
}
