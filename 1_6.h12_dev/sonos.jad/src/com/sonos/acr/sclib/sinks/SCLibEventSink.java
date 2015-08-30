// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCIEventSinkSwigBase;
import com.sonos.sclib.SCIObj;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class SCLibEventSink extends SCIEventSinkSwigBase
{
    public static interface EventListener
    {
    }

    public static final class SCLibEvent extends Enum
    {

        public static SCLibEvent valueOf(String s)
        {
            return (SCLibEvent)Enum.valueOf(com/sonos/acr/sclib/sinks/SCLibEventSink$SCLibEvent, s);
        }

        public static SCLibEvent[] values()
        {
            return (SCLibEvent[])$VALUES.clone();
        }

        private static final SCLibEvent $VALUES[] = new SCLibEvent[0];


        private SCLibEvent(String s, int i)
        {
            super(s, i);
        }
    }


    public SCLibEventSink()
    {
    }

    private String getRemainingListeners()
    {
        StringBuilder stringbuilder = new StringBuilder();
        for(Iterator iterator = listeners.iterator(); iterator.hasNext(); stringbuilder.append(", "))
            stringbuilder.append(((EventListener)iterator.next()).getClass().getSimpleName());

        return stringbuilder.toString();
    }

    public boolean addListener(EventListener eventlistener)
    {
        boolean flag;
        if(!listeners.contains(eventlistener))
        {
            SLog.i(LOG_TAG, (new StringBuilder()).append("addListenerCalled: ").append(eventlistener.getClass().getSimpleName()).append(" Currrent Active Listener Count: ").append(listeners.size()).toString());
            listeners.add(eventlistener);
            if(listeners.size() > 0 && !hasSubscription())
            {
                SLog.d(LOG_TAG, "First Listener added, start Monitoring");
                startMonitoring();
            }
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public final void dispatchEvent(SCIObj sciobj, String s)
    {
        onDispatchEvent(sciobj, s);
    }

    protected abstract boolean hasSubscription();

    public abstract void onDispatchEvent(SCIObj sciobj, String s);

    public void removeListener(EventListener eventlistener)
    {
        if(listeners.remove(eventlistener) && listeners.size() == 0)
        {
            SLog.d(LOG_TAG, "No Listeners Left, stop Monitoring");
            stopMonitoring();
        }
        SLog.i(LOG_TAG, (new StringBuilder()).append("removeListener: ").append(eventlistener.getClass().getSimpleName()).append(" Listeners Remaining[").append(listeners.size()).append("]: ").append(getRemainingListeners()).toString());
    }

    protected abstract void startMonitoring();

    protected abstract void stopMonitoring();

    protected final String LOG_TAG = (new StringBuilder()).append(com/sonos/acr/sclib/sinks/SCLibEventSink.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
    protected final ArrayList listeners = new ArrayList();
}
