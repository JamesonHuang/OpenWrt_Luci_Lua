// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.routing;

import android.media.MediaRouter;

// Referenced classes of package android.support.v4.media.routing:
//            MediaRouterJellybeanMr1

class MediaRouterJellybeanMr2 extends MediaRouterJellybeanMr1
{
    public static final class UserRouteInfo
    {

        public static void setDescription(Object obj, CharSequence charsequence)
        {
            ((android.media.MediaRouter.UserRouteInfo)obj).setDescription(charsequence);
        }

        public UserRouteInfo()
        {
        }
    }

    public static final class RouteInfo
    {

        public static CharSequence getDescription(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).getDescription();
        }

        public static boolean isConnecting(Object obj)
        {
            return ((android.media.MediaRouter.RouteInfo)obj).isConnecting();
        }

        public RouteInfo()
        {
        }
    }


    MediaRouterJellybeanMr2()
    {
    }

    public static void addCallback(Object obj, int i, Object obj1, int j)
    {
        ((MediaRouter)obj).addCallback(i, (android.media.MediaRouter.Callback)obj1, j);
    }

    public static Object getDefaultRoute(Object obj)
    {
        return ((MediaRouter)obj).getDefaultRoute();
    }
}
