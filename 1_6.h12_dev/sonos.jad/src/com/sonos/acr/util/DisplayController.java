// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.LongSparseArray;
import com.sonos.acr.application.SonosApplication;
import java.lang.reflect.Field;

// Referenced classes of package com.sonos.acr.util:
//            SLog

public class DisplayController
{

    public DisplayController()
    {
    }

    private static boolean clearDrawableCache(Resources resources)
    {
        boolean flag;
        flag = true;
        System.gc();
        Field afield[];
        int i;
        int j;
        afield = resources.getClass().getDeclaredFields();
        i = afield.length;
        j = 0;
_L2:
        LongSparseArray longsparsearray;
        int k;
        int l;
        if(j >= i)
            break MISSING_BLOCK_LABEL_114;
        Field field = afield[j];
        if(!field.getType().equals(android/util/LongSparseArray))
            break MISSING_BLOCK_LABEL_94;
        field.setAccessible(true);
        longsparsearray = (LongSparseArray)field.get(resources);
        k = longsparsearray.size();
        l = 0;
_L1:
        if(l >= k)
            break MISSING_BLOCK_LABEL_94;
        longsparsearray.setValueAt(l, null);
        l++;
          goto _L1
        j++;
          goto _L2
        Exception exception;
        exception;
        SLog.e(com/sonos/acr/util/DisplayController.getSimpleName(), "Error trying to clear the drawable cache", exception);
        flag = false;
        return flag;
    }

    public static void determinePreferredScreenDensities(SharedPreferences sharedpreferences)
    {
        if(isLyingCraplet(defaultLayout, defaultDisplayMetrics))
        {
            preferredDensityDpi = 160;
            preferredDensity = 1.0F;
            preferredLayout = defaultLayout;
        } else
        {
            preferredDensityDpi = defaultDisplayMetrics.densityDpi;
            preferredDensity = defaultDisplayMetrics.density;
            preferredLayout = defaultLayout;
        }
    }

    public static int getScreenType()
    {
        0xf & SonosApplication.getInstance().getResources().getConfiguration().screenLayout;
        JVM INSTR tableswitch 1 3: default 40
    //                   1 49
    //                   2 49
    //                   3 44;
           goto _L1 _L2 _L2 _L3
_L1:
        byte byte0 = 2;
_L5:
        return byte0;
_L3:
        byte0 = 1;
        continue; /* Loop/switch isn't completed */
_L2:
        byte0 = 0;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static void init(Resources resources, SharedPreferences sharedpreferences)
    {
        defaultDisplayMetrics.setTo(resources.getDisplayMetrics());
        defaultLayout = resources.getConfiguration().screenLayout;
        determinePreferredScreenDensities(sharedpreferences);
        resolvePreferredDensities(resources);
    }

    public static boolean isLandscapableScreen()
    {
        double d = (float)defaultDisplayMetrics.widthPixels / defaultDisplayMetrics.xdpi;
        double d1 = (float)defaultDisplayMetrics.heightPixels / defaultDisplayMetrics.ydpi;
        double d2 = Math.sqrt(d * d + d1 * d1);
        boolean flag;
        if(getScreenType() == 0 && defaultDisplayMetrics.densityDpi == 320 && d2 > 4D)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isLyingCraplet(int i, DisplayMetrics displaymetrics)
    {
        boolean flag;
        if((i & 0xf) == 3 && displaymetrics.densityDpi == 240 && displaymetrics.xdpi < 200F && displaymetrics.ydpi < 200F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void resolvePreferredDensities(Resources resources)
    {
        DisplayMetrics displaymetrics = resources.getDisplayMetrics();
        if(preferredDensityDpi != displaymetrics.densityDpi)
        {
            clearDrawableCache(resources);
            displaymetrics.densityDpi = preferredDensityDpi;
            displaymetrics.density = preferredDensity;
            displaymetrics.scaledDensity = preferredDensity;
            Configuration configuration = resources.getConfiguration();
            if(android.os.Build.VERSION.SDK_INT >= 13)
            {
                configuration.screenLayout = 0xfffffff0 & configuration.screenLayout | preferredLayout;
                configuration.screenHeightDp = (int)((float)displaymetrics.heightPixels / displaymetrics.density);
                configuration.screenWidthDp = (int)((float)displaymetrics.widthPixels / displaymetrics.density);
                configuration.smallestScreenWidthDp = Math.min(configuration.screenHeightDp, configuration.screenWidthDp);
            }
            resources.updateConfiguration(configuration, displaymetrics);
        }
    }

    public static final String LOG_TAG = com/sonos/acr/util/DisplayController.getSimpleName();
    public static final int SCREEN_LARGE = 1;
    public static final int SCREEN_NORMAL = 0;
    public static final int SCREEN_XLARGE = 2;
    static final DisplayMetrics defaultDisplayMetrics = new DisplayMetrics();
    static int defaultLayout;
    private static android.content.SharedPreferences.OnSharedPreferenceChangeListener listener;
    private static float preferredDensity;
    private static int preferredDensityDpi;
    private static int preferredLayout;

}
