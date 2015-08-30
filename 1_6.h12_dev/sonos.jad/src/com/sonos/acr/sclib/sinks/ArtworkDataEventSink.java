// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            SCLibEventSink

public class ArtworkDataEventSink extends SCLibEventSink
{
    public static interface ArtworkDataListener
        extends SCLibEventSink.EventListener
    {

        public abstract void onArtworkLoadedEvent(SCIArtworkData sciartworkdata, boolean flag);
    }


    public ArtworkDataEventSink(SCIArtworkData sciartworkdata)
    {
        isSubscribed = false;
        data = sciartworkdata;
    }

    private void subscribe()
    {
        if(!data.isReady())
        {
            data.subscribe(this);
            isSubscribed = true;
        }
    }

    private void unsubscribe()
    {
        data.unsubscribe(this);
        isSubscribed = false;
    }

    public boolean addListener(ArtworkDataListener artworkdatalistener)
    {
        boolean flag = super.addListener(artworkdatalistener);
        if(flag && artworkdatalistener != null && data.isReady())
        {
            SCIArtworkData sciartworkdata = data;
            boolean flag1;
            if(!isSubscribed)
                flag1 = true;
            else
                flag1 = false;
            artworkdatalistener.onArtworkLoadedEvent(sciartworkdata, flag1);
        }
        return flag;
    }

    public volatile boolean addListener(SCLibEventSink.EventListener eventlistener)
    {
        return addListener((ArtworkDataListener)eventlistener);
    }

    protected boolean hasSubscription()
    {
        return isSubscribed;
    }

    public void onDispatchEvent(SCIObj sciobj, String s)
    {
        if(sciobj instanceof SCIArtworkData)
        {
            ArrayList arraylist = new ArrayList(listeners);
            if(s.equals(sclibConstants.SCIARTWORKDATA_ONARTWORKDATACHANGED_EVENT))
            {
                for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); ((ArtworkDataListener)iterator.next()).onArtworkLoadedEvent((SCIArtworkData)sciobj, false));
            }
        }
    }

    protected void startMonitoring()
    {
        subscribe();
    }

    protected void stopMonitoring()
    {
        unsubscribe();
    }

    SCIArtworkData data;
    boolean isSubscribed;
}
