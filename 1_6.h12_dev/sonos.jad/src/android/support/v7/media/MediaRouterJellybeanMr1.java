// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import java.lang.reflect.*;

final class MediaRouterJellybeanMr1
{
    static class CallbackProxy extends MediaRouterJellybean.CallbackProxy
    {

        public void onRoutePresentationDisplayChanged(MediaRouter mediarouter, android.media.MediaRouter.RouteInfo routeinfo)
        {
            ((Callback)mCallback).onRoutePresentationDisplayChanged(routeinfo);
        }

        public CallbackProxy(Callback callback)
        {
            super(callback);
        }
    }

    public static final class IsConnectingWorkaround
    {

        public boolean isConnecting(Object obj)
        {
            android.media.MediaRouter.RouteInfo routeinfo = (android.media.MediaRouter.RouteInfo)obj;
            if(mGetStatusCodeMethod == null) goto _L2; else goto _L1
_L1:
            int i;
            int j;
            i = ((Integer)mGetStatusCodeMethod.invoke(routeinfo, new Object[0])).intValue();
            j = mStatusConnecting;
            boolean flag;
            if(i == j)
                flag = true;
            else
                flag = false;
_L4:
            return flag;
            InvocationTargetException invocationtargetexception;
            invocationtargetexception;
_L2:
            flag = false;
            if(true) goto _L4; else goto _L3
_L3:
            IllegalAccessException illegalaccessexception;
            illegalaccessexception;
              goto _L2
        }

        private Method mGetStatusCodeMethod;
        private int mStatusConnecting;

        public IsConnectingWorkaround()
        {
            if(android.os.Build.VERSION.SDK_INT != 17)
                throw new UnsupportedOperationException();
            mStatusConnecting = android/media/MediaRouter$RouteInfo.getField("STATUS_CONNECTING").getInt(null);
            mGetStatusCodeMethod = android/media/MediaRouter$RouteInfo.getMethod("getStatusCode", new Class[0]);
_L2:
            return;
            IllegalAccessException illegalaccessexception;
            illegalaccessexception;
            continue; /* Loop/switch isn't completed */
            NoSuchMethodException nosuchmethodexception;
            nosuchmethodexception;
            continue; /* Loop/switch isn't completed */
            NoSuchFieldException nosuchfieldexception;
            nosuchfieldexception;
            if(true) goto _L2; else goto _L1
_L1:
        }
    }

    public static final class ActiveScanWorkaround
        implements Runnable
    {

        public void run()
        {
            if(mActivelyScanningWifiDisplays)
            {
                try
                {
                    mScanWifiDisplaysMethod.invoke(mDisplayManager, new Object[0]);
                }
                catch(IllegalAccessException illegalaccessexception)
                {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", illegalaccessexception);
                }
                catch(InvocationTargetException invocationtargetexception)
                {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", invocationtargetexception);
                }
                mHandler.postDelayed(this, 15000L);
            }
        }

        public void setActiveScanRouteTypes(int i)
        {
            if((i & 2) == 0) goto _L2; else goto _L1
_L1:
            if(!mActivelyScanningWifiDisplays)
                if(mScanWifiDisplaysMethod != null)
                {
                    mActivelyScanningWifiDisplays = true;
                    mHandler.post(this);
                } else
                {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays because the DisplayManager.scanWifiDisplays() method is not available on this device.");
                }
_L4:
            return;
_L2:
            if(mActivelyScanningWifiDisplays)
            {
                mActivelyScanningWifiDisplays = false;
                mHandler.removeCallbacks(this);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private static final int WIFI_DISPLAY_SCAN_INTERVAL = 15000;
        private boolean mActivelyScanningWifiDisplays;
        private final DisplayManager mDisplayManager;
        private final Handler mHandler;
        private Method mScanWifiDisplaysMethod;

        public ActiveScanWorkaround(Context context, Handler handler)
        {
            if(android.os.Build.VERSION.SDK_INT != 17)
                throw new UnsupportedOperationException();
            mDisplayManager = (DisplayManager)context.getSystemService("display");
            mHandler = handler;
            mScanWifiDisplaysMethod = android/hardware/display/DisplayManager.getMethod("scanWifiDisplays", new Class[0]);
_L2:
            return;
            NoSuchMethodException nosuchmethodexception;
            nosuchmethodexception;
            if(true) goto _L2; else goto _L1
_L1:
        }
    }

    public static interface Callback
        extends MediaRouterJellybean.Callback
    {

        public abstract void onRoutePresentationDisplayChanged(Object obj);
    }

    public static final class RouteInfo
    {

        public static Display getPresentationDisplay(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getPresentationDisplay();
        }

        public static boolean isEnabled(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).isEnabled();
        }

        public RouteInfo()
        {
        }
    }


    MediaRouterJellybeanMr1()
    {
    }

    public static Object createCallback(Callback callback)
    {
        return new CallbackProxy(callback);
    }

    private static final String TAG = "MediaRouterJellybeanMr1";
}
