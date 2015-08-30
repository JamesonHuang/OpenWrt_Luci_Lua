// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            SCLibEventSink

public class PrivateQueueEventSink extends SCLibEventSink
{
    public static interface QueueListener
        extends SCLibEventSink.EventListener
    {

        public abstract void onPrivateQueueChanged(SCIPlayQueue sciplayqueue);

        public abstract void onQueueInvalidationEvent(SCIPlayQueue sciplayqueue);
    }


    public PrivateQueueEventSink(SCIPlayQueue sciplayqueue)
    {
        hasSubscription = false;
        playQueue = sciplayqueue;
    }

    protected boolean hasSubscription()
    {
        return hasSubscription;
    }

    public void onDispatchEvent(SCIObj sciobj, String s)
    {
        if(sciobj instanceof SCIPlayQueue)
        {
            SCIPlayQueue sciplayqueue = (SCIPlayQueue)sciobj;
            if(s.equals(sclibConstants.SCIPLAYQUEUE_ONQUEUEINVALIDATION_EVENT))
            {
                for(Iterator iterator1 = listeners.iterator(); iterator1.hasNext(); ((QueueListener)iterator1.next()).onQueueInvalidationEvent(sciplayqueue));
            } else
            if(s.equals(sclibConstants.SCIPLAYQUEUE_ONPRIVATEQUEUECHANGED_EVENT))
            {
                for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((QueueListener)iterator.next()).onPrivateQueueChanged(sciplayqueue));
            }
        }
    }

    protected void startMonitoring()
    {
        if(playQueue != null)
        {
            playQueue.subscribe(this);
            hasSubscription = true;
        }
    }

    protected void stopMonitoring()
    {
        SLog.d("PrivateQueueEventSink", "stopMonitoring");
        if(playQueue != null)
        {
            SLog.d("PrivateQueueEventSink", "unsubscribing from playQueue");
            hasSubscription = false;
            playQueue.unsubscribe(this);
        } else
        {
            SLog.d("PrivateQueueEventSink", "playQueue is already null");
        }
        playQueue = null;
    }

    private static final String LOG_TAG = "PrivateQueueEventSink";
    private boolean hasSubscription;
    protected SCIPlayQueue playQueue;
}
