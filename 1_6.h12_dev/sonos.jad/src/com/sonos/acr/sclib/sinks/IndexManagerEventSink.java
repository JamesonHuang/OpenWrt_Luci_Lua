// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            SCLibEventSink

public class IndexManagerEventSink extends SCLibEventSink
{
    public static interface IndexManagerListener
        extends SCLibEventSink.EventListener
    {

        public abstract void onIndexingEvent(SCIIndexManager sciindexmanager, IndexManagerEvent indexmanagerevent);
    }

    public static final class IndexManagerEvent extends Enum
    {

        public static IndexManagerEvent valueOf(String s)
        {
            return (IndexManagerEvent)Enum.valueOf(com/sonos/acr/sclib/sinks/IndexManagerEventSink$IndexManagerEvent, s);
        }

        public static IndexManagerEvent[] values()
        {
            return (IndexManagerEvent[])$VALUES.clone();
        }

        private static final IndexManagerEvent $VALUES[];
        public static final IndexManagerEvent OnIndexing;
        public static final IndexManagerEvent OnIndexingError;

        static 
        {
            OnIndexing = new IndexManagerEvent("OnIndexing", 0);
            OnIndexingError = new IndexManagerEvent("OnIndexingError", 1);
            IndexManagerEvent aindexmanagerevent[] = new IndexManagerEvent[2];
            aindexmanagerevent[0] = OnIndexing;
            aindexmanagerevent[1] = OnIndexingError;
            $VALUES = aindexmanagerevent;
        }

        private IndexManagerEvent(String s, int i)
        {
            super(s, i);
        }
    }


    public IndexManagerEventSink()
    {
    }

    public static IndexManagerEventSink getInstance()
    {
        if(instance == null)
            instance = new IndexManagerEventSink();
        return instance;
    }

    private void subscribe()
    {
        if(LibraryUtils.getHousehold() != null)
        {
            manager = LibraryUtils.getHousehold().getIndexManager();
            manager.subscribe(this);
        }
    }

    private void unsubscribe()
    {
        if(manager != null)
        {
            manager.unsubscribe(this);
            manager = null;
        }
    }

    protected boolean hasSubscription()
    {
        boolean flag;
        if(manager != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onDispatchEvent(SCIObj sciobj, String s)
    {
        if(sciobj instanceof SCIIndexManager)
        {
            ArrayList arraylist = new ArrayList(listeners);
            if(s.equals(sclibConstants.SCIINDEXMANAGER_ONINDEX_EVENT))
            {
                for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); ((IndexManagerListener)iterator1.next()).onIndexingEvent(manager, IndexManagerEvent.OnIndexing));
            } else
            if(s.equals(sclibConstants.SCIINDEXMANAGER_ONINDEXERROR_EVENT))
            {
                for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); ((IndexManagerListener)iterator.next()).onIndexingEvent(manager, IndexManagerEvent.OnIndexingError));
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

    private static IndexManagerEventSink instance;
    SCIIndexManager manager;
}
