// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.SonosListener;
import com.splunk.mint.Mint;
import java.util.HashMap;

// Referenced classes of package com.sonos.acr.util:
//            NativeException, StringUtils, SLog

public class NativeCrashHelper
{

    public NativeCrashHelper()
    {
    }

    public static native void doNativeCrash();

    public static native boolean initialize();

    public static void reportNativeCrash(int i, int j, String s, StackTraceElement astacktraceelement[], int k)
    {
        String s1 = (new StringBuilder()).append(s).append(" [signal ").append(i).append(", code ").append(j).append("]").toString();
        if(astacktraceelement != null)
        {
            NativeException.nativeStack = astacktraceelement;
            if(astacktraceelement.length > 0 && astacktraceelement[0] != null)
                s1 = (new StringBuilder()).append(s1).append(" (").append(astacktraceelement[0].getClassName()).append(")").toString();
        }
        NativeException nativeexception = new NativeException(s1);
        if(!SonosApplication.isDebuggable())
        {
            HashMap hashmap = new HashMap();
            SonosApplication sonosapplication = SonosApplication.getInstance();
            if(sonosapplication != null)
            {
                String s2 = sonosapplication.getListener().getHouseholdID();
                if(StringUtils.isNotEmptyOrNull(s2))
                {
                    hashmap.put("hhid", s2);
                    Mint.setUserIdentifier(s2);
                }
                try
                {
                    PackageInfo packageinfo = sonosapplication.getPackageManager().getPackageInfo(sonosapplication.getPackageName(), 0);
                    if(packageinfo != null)
                        hashmap.put("VersionCode", Integer.toString(packageinfo.versionCode));
                }
                catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
                try
                {
                    SLog.i(LOG_TAG, "Native crash occurred");
                    Mint.logExceptionMap(hashmap, nativeexception);
                    SLog.e(LOG_TAG, "Reported native crash", nativeexception);
                }
                catch(Exception exception) { }
            }
        }
        SLog.e(LOG_TAG, "Native crash occurred", nativeexception);
    }

    private static final String LOG_TAG = com/sonos/acr/util/NativeCrashHelper.getSimpleName();

}
