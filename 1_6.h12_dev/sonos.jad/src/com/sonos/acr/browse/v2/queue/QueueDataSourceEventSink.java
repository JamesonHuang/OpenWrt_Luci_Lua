// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.queue;

import com.sonos.acr.browse.v2.BrowseDataSourceEventSink;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

public abstract class QueueDataSourceEventSink extends BrowseDataSourceEventSink
{

    public QueueDataSourceEventSink()
    {
    }

    public void dispatchEvent(SCIObj sciobj, String s)
    {
        super.dispatchEvent(sciobj, s);
        if((SCIBrowseDataSource)LibraryUtils.cast(sciobj, com/sonos/sclib/SCIBrowseDataSource) == null || !(sciobj instanceof SCIBrowseDataSource)) goto _L2; else goto _L1
_L1:
        SCIBrowseDataSource scibrowsedatasource = (SCIBrowseDataSource)sciobj;
        if(!s.equals(sclibConstants.SCIBROWSEDATASOURCE_ONQUEUECURRENTITEMCHANGED_EVENT)) goto _L4; else goto _L3
_L3:
        onQueueCurrentItemChanged(scibrowsedatasource);
_L2:
        return;
_L4:
        if(s.equals(sclibConstants.SCIBROWSEDATASOURCE_ONQUEUEINUSECHANGED_EVENT))
            onQueueInUseChanged(scibrowsedatasource);
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected abstract void onQueueCurrentItemChanged(SCIBrowseDataSource scibrowsedatasource);

    protected abstract void onQueueInUseChanged(SCIBrowseDataSource scibrowsedatasource);
}
