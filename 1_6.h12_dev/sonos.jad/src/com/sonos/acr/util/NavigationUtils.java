// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;


public class NavigationUtils
{
    public static final class BackNavigationRouting extends Enum
    {

        public static BackNavigationRouting valueOf(String s)
        {
            return (BackNavigationRouting)Enum.valueOf(com/sonos/acr/util/NavigationUtils$BackNavigationRouting, s);
        }

        public static BackNavigationRouting[] values()
        {
            return (BackNavigationRouting[])$VALUES.clone();
        }

        private static final BackNavigationRouting $VALUES[];
        public static final BackNavigationRouting BACKS_TO_NOWPLAYING;
        public static final BackNavigationRouting BACKS_TO_QUEUE;
        public static final BackNavigationRouting NONE;

        static 
        {
            NONE = new BackNavigationRouting("NONE", 0);
            BACKS_TO_NOWPLAYING = new BackNavigationRouting("BACKS_TO_NOWPLAYING", 1);
            BACKS_TO_QUEUE = new BackNavigationRouting("BACKS_TO_QUEUE", 2);
            BackNavigationRouting abacknavigationrouting[] = new BackNavigationRouting[3];
            abacknavigationrouting[0] = NONE;
            abacknavigationrouting[1] = BACKS_TO_NOWPLAYING;
            abacknavigationrouting[2] = BACKS_TO_QUEUE;
            $VALUES = abacknavigationrouting;
        }

        private BackNavigationRouting(String s, int i)
        {
            super(s, i);
        }
    }


    public NavigationUtils()
    {
    }

    public static final String BackNavigationRoutingKey = "com.sonos.acr.util.backNavigationRoutingKey";
}
