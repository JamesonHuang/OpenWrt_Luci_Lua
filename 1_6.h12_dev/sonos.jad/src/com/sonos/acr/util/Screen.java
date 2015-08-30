// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;

public class Screen
{
    private class SummaryTextBuilder
    {

        public SummaryTextBuilder addLine(String s)
        {
            sb.append(s).append("\n");
            return this;
        }

        public SummaryTextBuilder addLine(String s, double d)
        {
            sb.append(s).append(" ").append(d).append("\n");
            return this;
        }

        public SummaryTextBuilder addLine(String s, float f)
        {
            sb.append(s).append(" ").append(f).append("\n");
            return this;
        }

        public SummaryTextBuilder addLine(String s, int i)
        {
            sb.append(s).append(" ").append(i).append("\n");
            return this;
        }

        public SummaryTextBuilder addLine(String s, String s1)
        {
            sb.append(s).append(" ").append(s1).append("\n");
            return this;
        }

        public SummaryTextBuilder addNewLine()
        {
            sb.append("\n");
            return this;
        }

        public String toString()
        {
            return sb.toString();
        }

        private Context ctx;
        private StringBuilder sb;
        final Screen this$0;

        public SummaryTextBuilder(Context context)
        {
            this$0 = Screen.this;
            super();
            sb = new StringBuilder();
            ctx = context;
        }
    }


    public Screen(Context context)
    {
        WindowManager windowmanager = (WindowManager)context.getSystemService("window");
        mDisplay = windowmanager.getDefaultDisplay();
        mConfig = context.getResources().getConfiguration();
        mSizeClass = 0xf & mConfig.screenLayout;
        DisplayMetrics displaymetrics1;
        int i;
        double d;
        double d1;
        double d2;
        try
        {
            Class class1 = mDisplay.getClass();
            Class aclass[] = new Class[1];
            aclass[0] = android/graphics/Point;
            Method method = class1.getMethod("getSize", aclass);
            Point point = new Point();
            Display display = mDisplay;
            Object aobj[] = new Object[1];
            aobj[0] = point;
            method.invoke(display, aobj);
            widthPx = point.x;
            heightPx = point.y;
        }
        catch(Exception exception)
        {
            widthPx = mDisplay.getWidth();
            heightPx = mDisplay.getHeight();
        }
        realWidthPx = -255;
        realHeightPx = -255;
        if(android.os.Build.VERSION.SDK_INT >= 17)
        {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            windowmanager.getDefaultDisplay().getRealMetrics(displaymetrics);
            realWidthPx = displaymetrics.widthPixels;
            realHeightPx = displaymetrics.heightPixels;
        }
        displaymetrics1 = new DisplayMetrics();
        mDisplay.getMetrics(displaymetrics1);
        if(realHeightPx == -255)
            i = heightPx;
        else
            i = realHeightPx;
        heightDp = (int)(0.5D + (double)i / (double)displaymetrics1.density);
        widthDp = -255;
        smallestDp = -255;
        if(android.os.Build.VERSION.SDK_INT >= 13)
        {
            widthDp = mConfig.screenWidthDp;
            smallestDp = mConfig.smallestScreenWidthDp;
        } else
        {
            int j;
            int k;
            if(realWidthPx == -255)
                j = widthPx;
            else
                j = realWidthPx;
            widthDp = (int)(0.5D + (double)j / (double)displaymetrics1.density);
            if(widthDp <= heightDp)
                k = widthDp;
            else
                k = heightDp;
            smallestDp = k;
        }
        densityDpi = displaymetrics1.densityDpi;
        xdpi = displaymetrics1.xdpi;
        ydpi = displaymetrics1.ydpi;
        density = displaymetrics1.density;
        scaledDensity = displaymetrics1.scaledDensity;
        d = displaymetrics1.xdpi;
        if(d < 1.0D)
            d = displaymetrics1.densityDpi;
        d1 = displaymetrics1.ydpi;
        if(d1 < 1.0D)
            d1 = displaymetrics1.densityDpi;
        physicalWidth = (double)displaymetrics1.widthPixels / d;
        physicalHeight = (double)displaymetrics1.heightPixels / d1;
        d2 = Math.sqrt(Math.pow(physicalWidth, 2D) + Math.pow(physicalHeight, 2D));
        diagonalSizeInches = Math.floor(0.5D + 10D * d2) / 10D;
        diagonalSizeMillimeters = Math.floor(0.5D + 25.399999999999999D * d2);
        screenLayout = 0x30 & mConfig.screenLayout;
        defaultOrientation = mConfig.orientation;
        touchScreen = mConfig.touchscreen;
        determineCurrentRotation(context);
        pixelFormat = -255;
        if(android.os.Build.VERSION.SDK_INT < 17)
            pixelFormat = mDisplay.getPixelFormat();
        refreshRate = mDisplay.getRefreshRate();
    }

    private void determineCurrentRotation(Context context)
    {
        ((Integer)mDisplay.getClass().getMethod("getRotation", new Class[0]).invoke(mDisplay, new Object[0])).intValue();
        JVM INSTR tableswitch 0 3: default 117
    //                   0 64
    //                   1 93
    //                   2 102
    //                   3 111;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        currentOrientation = "0";
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        currentOrientation = orientationText(context, mDisplay.getOrientation());
        break; /* Loop/switch isn't completed */
_L3:
        currentOrientation = "90";
        break; /* Loop/switch isn't completed */
_L4:
        currentOrientation = "180";
        break; /* Loop/switch isn't completed */
_L5:
        currentOrientation = "270";
    }

    private String orientationText(Context context, int i)
    {
        i;
        JVM INSTR tableswitch 0 3: default 32
    //                   0 59
    //                   1 45
    //                   2 38
    //                   3 52;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        String s = "ORIENTATION_UNDEFINED";
_L7:
        return s;
_L4:
        s = "ORIENTATION_LANDSCAPE";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "ORIENTATION_PORTRAIT";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "ORIENTATION_SQUARE";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "ORIENTATION_UNDEFINED";
        if(true) goto _L7; else goto _L6
_L6:
    }

    public String androidVersion()
    {
        return android.os.Build.VERSION.RELEASE;
    }

    public String currentOrientationText()
    {
        return currentOrientation;
    }

    public int defaultOrientation()
    {
        return defaultOrientation;
    }

    public String defaultOrientationText(Context context)
    {
        return orientationText(context, defaultOrientation);
    }

    public double density()
    {
        return density;
    }

    public int densityDpi()
    {
        return densityDpi;
    }

    public String densityDpiText(Context context)
    {
        densityDpi;
        JVM INSTR lookupswitch 6: default 64
    //                   120: 97
    //                   160: 104
    //                   213: 90
    //                   240: 111
    //                   320: 118
    //                   480: 125;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        String s = (new StringBuilder()).append("density_").append(densityDpi).toString();
_L9:
        return s;
_L4:
        s = "tvdpi";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "ldpi";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "mdpi";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "hdpi";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "xhdpi";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "xxhdpi";
        if(true) goto _L9; else goto _L8
_L8:
    }

    public String deviceModel()
    {
        return Build.MODEL;
    }

    public double diagonalSizeInches()
    {
        return diagonalSizeInches;
    }

    public double diagonalSizeMillimeters()
    {
        return diagonalSizeMillimeters;
    }

    public int heightDp()
    {
        return heightDp;
    }

    public int heightPx()
    {
        return heightPx;
    }

    public double physicalHeight()
    {
        return physicalHeight;
    }

    public double physicalWidth()
    {
        return physicalWidth;
    }

    public int pixelFormat()
    {
        return pixelFormat;
    }

    public String pixelFormatText(Context context)
    {
        pixelFormat;
        JVM INSTR lookupswitch 20: default 176
    //                   -255: 315
    //                   -3: 266
    //                   -2: 273
    //                   -1: 210
    //                   0: 280
    //                   1: 252
    //                   2: 259
    //                   3: 231
    //                   4: 224
    //                   5: 308
    //                   6: 245
    //                   7: 238
    //                   8: 182
    //                   9: 196
    //                   10: 203
    //                   11: 217
    //                   16: 301
    //                   17: 287
    //                   20: 294
    //                   256: 189;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21
_L1:
        String s = "UNKNOWN";
_L23:
        return s;
_L14:
        s = "A_8";
        continue; /* Loop/switch isn't completed */
_L21:
        s = "JPEG";
        continue; /* Loop/switch isn't completed */
_L15:
        s = "L_8";
        continue; /* Loop/switch isn't completed */
_L16:
        s = "LA_88";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "OPAQUE";
        continue; /* Loop/switch isn't completed */
_L17:
        s = "RGB_332";
        continue; /* Loop/switch isn't completed */
_L10:
        s = "RGB_565";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "RGB_888";
        continue; /* Loop/switch isn't completed */
_L13:
        s = "RGBA_4444";
        continue; /* Loop/switch isn't completed */
_L12:
        s = "RGBA_5551";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "RGBA_8888";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "RGBX_8888";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "TRANSLUCENT";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "TRANSPARENT";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "UNKNOWN";
        continue; /* Loop/switch isn't completed */
_L19:
        s = "NV21";
        continue; /* Loop/switch isn't completed */
_L20:
        s = "YUY2";
        continue; /* Loop/switch isn't completed */
_L18:
        s = "NV16";
        continue; /* Loop/switch isn't completed */
_L11:
        s = "BGRA_8888";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "UNSUPPORTED";
        if(true) goto _L23; else goto _L22
_L22:
    }

    public int realHeightPx()
    {
        return realHeightPx;
    }

    public String realHeightPxText(Context context)
    {
        String s;
        if(realHeightPx == -255)
            s = "UNSUPPORTED";
        else
            s = Integer.toString(realHeightPx);
        return s;
    }

    public int realWidthPx()
    {
        return realWidthPx;
    }

    public String realWidthPxText(Context context)
    {
        String s;
        if(realWidthPx == -255)
            s = "UNSUPPORTED";
        else
            s = Integer.toString(realWidthPx);
        return s;
    }

    public float refreshRate()
    {
        return refreshRate;
    }

    public float scaledDensity()
    {
        return scaledDensity;
    }

    public int screenLayout()
    {
        return screenLayout;
    }

    public String screenLayoutText(Context context)
    {
        screenLayout;
        JVM INSTR lookupswitch 3: default 40
    //                   0: 64
    //                   16: 57
    //                   32: 50;
           goto _L1 _L2 _L3 _L4
_L1:
        String s = context.getString(0x7f0c00cf);
_L6:
        return s;
_L4:
        s = "SCREENLAYOUT_LONG_YES";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "SCREENLAYOUT_LONG_NO";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "SCREENLAYOUT_LONG_UNDEFINED";
        if(true) goto _L6; else goto _L5
_L5:
    }

    public int sizeClassification()
    {
        return mSizeClass;
    }

    public String sizeClassificationText(Context context)
    {
        mSizeClass;
        JVM INSTR tableswitch 0 4: default 40
    //                   0 74
    //                   1 46
    //                   2 53
    //                   3 60
    //                   4 67;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        String s = "SCREENLAYOUT_SIZE_UNKNOWN";
_L8:
        return s;
_L3:
        s = "SCREENLAYOUT_SIZE_SMALL";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "SCREENLAYOUT_SIZE_NORMAL";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "SCREENLAYOUT_SIZE_LARGE";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "SCREENLAYOUT_SIZE_XLARGE";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "SCREENLAYOUT_SIZE_UNDEFINED";
        if(true) goto _L8; else goto _L7
_L7:
    }

    public int smallestDp()
    {
        return smallestDp;
    }

    public String summaryText(Context context)
    {
        SummaryTextBuilder summarytextbuilder = new SummaryTextBuilder(context);
        summarytextbuilder.addLine("Device Model", deviceModel()).addLine("os_version_label", androidVersion()).addLine("screen_class_label", sizeClassificationText(context)).addLine("density_class_label", densityDpiText(context)).addLine("total_width_pixels_label", realWidthPxText(context)).addLine("total_height_pixels_label", realHeightPxText(context)).addLine("width_pixels_label", widthPx()).addLine("height_pixels_label", heightPx()).addLine("width_dp_label", widthDp()).addLine("height_dp_label", heightDp()).addLine("smallest_dp_label", smallestDp()).addLine("long_wide_label", screenLayoutText(context)).addLine("natural_orientation_label", defaultOrientationText(context)).addLine("current_orientation_label", currentOrientationText()).addLine("touchscreen_label", touchScreenText(context)).addLine("screen_dpi_label", densityDpi()).addLine("actual_xdpi_label", xdpi()).addLine("actual_ydpi_label", ydpi()).addLine("logical_density_label", density()).addLine("font_scale_density_label", scaledDensity()).addLine("computed_diagonal_size_inches_label", diagonalSizeInches()).addLine("computed_diagonal_size_mm_label", diagonalSizeMillimeters()).addLine("pixel_format_label", pixelFormatText(context)).addLine("refresh_rate_label", refreshRate()).addNewLine().addLine("play_store_link");
        return summarytextbuilder.toString();
    }

    public int touchScreen()
    {
        return touchScreen;
    }

    public String touchScreenText(Context context)
    {
        touchScreen;
        JVM INSTR tableswitch 0 3: default 36
    //                   0 63
    //                   1 56
    //                   2 49
    //                   3 42;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        String s = "unknown";
_L7:
        return s;
_L5:
        s = "TOUCHSCREEN_FINGER";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "TOUCHSCREEN_STYLUS";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "TOUCHSCREEN_NOTOUCH";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "TOUCHSCREEN_UNDEFINED";
        if(true) goto _L7; else goto _L6
_L6:
    }

    public int widthDp()
    {
        return widthDp;
    }

    public int widthPx()
    {
        return widthPx;
    }

    public float xdpi()
    {
        return xdpi;
    }

    public float ydpi()
    {
        return ydpi;
    }

    public static final int UNSUPPORTED = -255;
    private String currentOrientation;
    private int defaultOrientation;
    private double density;
    private int densityDpi;
    private double diagonalSizeInches;
    private double diagonalSizeMillimeters;
    private int heightDp;
    private int heightPx;
    private Configuration mConfig;
    private Display mDisplay;
    private int mSizeClass;
    private double physicalHeight;
    private double physicalWidth;
    private int pixelFormat;
    private int realHeightPx;
    private int realWidthPx;
    private float refreshRate;
    private float scaledDensity;
    private int screenLayout;
    private int smallestDp;
    private int touchScreen;
    private int widthDp;
    private int widthPx;
    private float xdpi;
    private float ydpi;
}
