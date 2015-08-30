// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.settings;

import com.sonos.sclib.*;
import java.util.ArrayList;

public class BaseBrowseItem extends SCIBrowseItemSwigBase
{

    public BaseBrowseItem(String s, String s1)
    {
        eventSinks = new ArrayList();
        primaryText = s;
        scuri = s1;
    }

    public boolean canActOn()
    {
        return true;
    }

    public boolean canPush()
    {
        return false;
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getAlbumArtType()
    {
        return com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE;
    }

    public String getAlbumArtURL()
    {
        return "";
    }

    public String getAlbumArtURL(long l)
    {
        return getAlbumArtURL();
    }

    public SCIPropertyBag getAttributes()
    {
        return sclib.createPropertyBag();
    }

    public SCIEnumerator getFilteredActions(SCIActionFilter sciactionfilter)
    {
        return getActions();
    }

    public SCIBrowseDataSource getMoreMenuDataSource()
    {
        return null;
    }

    public String getOrdinal()
    {
        return "";
    }

    public String getPrimaryAdornedTitle()
    {
        return primaryText;
    }

    public String getPrimaryTitle()
    {
        return primaryText;
    }

    public String getSCUri()
    {
        return scuri;
    }

    public String getSecondaryTitle()
    {
        return "";
    }

    public boolean hasOrdinal()
    {
        return false;
    }

    public boolean isDataAvailable()
    {
        return true;
    }

    public boolean isParentOfSearch()
    {
        return false;
    }

    public boolean isSecondaryTitleValid()
    {
        return true;
    }

    public void subscribe(SCIEventSink scieventsink, boolean flag)
    {
        if(eventSinks != null && !eventSinks.contains(scieventsink))
            eventSinks.add(scieventsink);
        scieventsink.dispatchEvent(this, sclib.SCIBROWSEITEM_ONITEMCHANGED_EVENT);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        if(eventSinks != null && eventSinks.contains(scieventsink))
            eventSinks.remove(scieventsink);
    }

    protected ArrayList eventSinks;
    protected String primaryText;
    protected String scuri;
}
