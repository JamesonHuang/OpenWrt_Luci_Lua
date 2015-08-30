// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.hardware.display;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

// Referenced classes of package android.support.v4.hardware.display:
//            DisplayManagerJellybeanMr1

public abstract class DisplayManagerCompat
{
    private static class JellybeanMr1Impl extends DisplayManagerCompat
    {

        public Display getDisplay(int i)
        {
            return DisplayManagerJellybeanMr1.getDisplay(mDisplayManagerObj, i);
        }

        public Display[] getDisplays()
        {
            return DisplayManagerJellybeanMr1.getDisplays(mDisplayManagerObj);
        }

        public Display[] getDisplays(String s)
        {
            return DisplayManagerJellybeanMr1.getDisplays(mDisplayManagerObj, s);
        }

        private final Object mDisplayManagerObj;

        public JellybeanMr1Impl(Context context)
        {
            mDisplayManagerObj = DisplayManagerJellybeanMr1.getDisplayManager(context);
        }
    }

    private static class LegacyImpl extends DisplayManagerCompat
    {

        public Display getDisplay(int i)
        {
            Display display = mWindowManager.getDefaultDisplay();
            if(display.getDisplayId() != i)
                display = null;
            return display;
        }

        public Display[] getDisplays()
        {
            Display adisplay[] = new Display[1];
            adisplay[0] = mWindowManager.getDefaultDisplay();
            return adisplay;
        }

        public Display[] getDisplays(String s)
        {
            Display adisplay[];
            if(s == null)
                adisplay = getDisplays();
            else
                adisplay = new Display[0];
            return adisplay;
        }

        private final WindowManager mWindowManager;

        public LegacyImpl(Context context)
        {
            mWindowManager = (WindowManager)context.getSystemService("window");
        }
    }


    DisplayManagerCompat()
    {
    }

    public static DisplayManagerCompat getInstance(Context context)
    {
        WeakHashMap weakhashmap = sInstances;
        weakhashmap;
        JVM INSTR monitorenter ;
        Object obj = (DisplayManagerCompat)sInstances.get(context);
        if(obj == null)
        {
            if(android.os.Build.VERSION.SDK_INT >= 17)
                obj = new JellybeanMr1Impl(context);
            else
                obj = new LegacyImpl(context);
            sInstances.put(context, obj);
        }
        return ((DisplayManagerCompat) (obj));
    }

    public abstract Display getDisplay(int i);

    public abstract Display[] getDisplays();

    public abstract Display[] getDisplays(String s);

    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap sInstances = new WeakHashMap();

}
