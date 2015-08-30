// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.common;

import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

public abstract class BrowseItemEventSink extends SCIEventSinkSwigBase
{

    public BrowseItemEventSink()
    {
    }

    public void dispatchEvent(SCIObj sciobj, String s)
    {
        if((SCIBrowseItem)LibraryUtils.cast(sciobj, com/sonos/sclib/SCIBrowseItem) != null && s.equals(sclibConstants.SCIBROWSEITEM_ONITEMCHANGED_EVENT))
            onItemChanged((SCIBrowseItem)sciobj);
    }

    public void onItemChanged(SCIBrowseItem scibrowseitem)
    {
    }

    public void subscribe()
    {
        if(subscribedBrowseItem != null)
            subscribe(subscribedBrowseItem);
    }

    public void subscribe(SCIBrowseItem scibrowseitem)
    {
        unsubscribe();
        if(scibrowseitem != null)
        {
            subscribedBrowseItem = scibrowseitem;
            subscribedBrowseItem.subscribe(this, false);
        }
    }

    public void unsubscribe()
    {
        if(subscribedBrowseItem != null)
        {
            subscribedBrowseItem.unsubscribe(this);
            subscribedBrowseItem = null;
        }
    }

    public SCIBrowseItem subscribedBrowseItem;
}
