// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import java.util.*;

public class NotificationCenter
{
    public static interface NotificationListener
    {

        public abstract void onNotification(NotificationCenter notificationcenter, String s, Object obj);
    }


    private NotificationCenter()
    {
        mObservers = new HashMap();
    }

    public static NotificationCenter defaultCenter()
    {
        return sDefaultCenter;
    }

    public void addObserver(String s, NotificationListener notificationlistener)
    {
        getListeners(s).add(notificationlistener);
    }

    protected ArrayList getListeners(String s)
    {
        ArrayList arraylist = (ArrayList)mObservers.get(s);
        if(arraylist == null)
        {
            arraylist = new ArrayList(1);
            mObservers.put(s, arraylist);
        }
        return arraylist;
    }

    public void postNotification(String s, Object obj)
    {
        ArrayList arraylist = getListeners(s);
        if(arraylist.size() > 0)
        {
            for(Iterator iterator = ((ArrayList)arraylist.clone()).iterator(); iterator.hasNext(); ((NotificationListener)iterator.next()).onNotification(this, s, obj));
        }
    }

    public void removeObserver(NotificationListener notificationlistener)
    {
        for(Iterator iterator = mObservers.keySet().iterator(); iterator.hasNext(); removeObserver((String)iterator.next(), notificationlistener));
    }

    public void removeObserver(String s, NotificationListener notificationlistener)
    {
        ArrayList arraylist = (ArrayList)mObservers.get(s);
        if(arraylist != null)
            arraylist.remove(notificationlistener);
    }

    private static final String LOG_TAG = com/sonos/acr/util/NotificationCenter.getSimpleName();
    private static final NotificationCenter sDefaultCenter = new NotificationCenter();
    protected HashMap mObservers;

}
