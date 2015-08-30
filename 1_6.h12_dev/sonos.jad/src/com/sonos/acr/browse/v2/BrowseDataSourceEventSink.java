// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2;

import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

public abstract class BrowseDataSourceEventSink extends SCIEventSinkSwigBase
{

    protected BrowseDataSourceEventSink()
    {
        subscribedDataSource = null;
        SLog.i(LOG_TAG, "Created new event sink.");
    }

    public void dispatchEvent(SCIObj sciobj, String s)
    {
        SCIBrowseDataSource scibrowsedatasource = (SCIBrowseDataSource)LibraryUtils.cast(sciobj, com/sonos/sclib/SCIBrowseDataSource);
        if(scibrowsedatasource == null) goto _L2; else goto _L1
_L1:
        SLog.i(LOG_TAG, (new StringBuilder()).append("Revieved Event: ").append(s).append(" for sender: ").append(scibrowsedatasource.getSCUri()).toString());
        if(!s.equals(sclibConstants.SCIBROWSEDATASOURCE_ONBROWSECHANGED_EVENT)) goto _L4; else goto _L3
_L3:
        onBrowseChanged(scibrowsedatasource);
_L2:
        return;
_L4:
        if(s.equals(sclibConstants.SCIBROWSEDATASOURCE_ONINVALIDATION_EVENT))
            onBrowseInvalidation(scibrowsedatasource);
        else
        if(s.equals(sclibConstants.SCIBROWSEDATASOURCE_ONPOWERSCROLLINFO_EVENT))
            onPowerScrollInfo(scibrowsedatasource, (SCIPowerscrollDataSource)LibraryUtils.cast(scibrowsedatasource, com/sonos/sclib/SCIPowerscrollDataSource));
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean isSubscribed()
    {
        boolean flag;
        if(subscribedDataSource != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public abstract void onBrowseChanged(SCIBrowseDataSource scibrowsedatasource);

    public abstract void onBrowseInvalidation(SCIBrowseDataSource scibrowsedatasource);

    public abstract void onPowerScrollInfo(SCIBrowseDataSource scibrowsedatasource, SCIPowerscrollDataSource scipowerscrolldatasource);

    public void subscribe(SCIBrowseDataSource scibrowsedatasource)
    {
        if(scibrowsedatasource == subscribedDataSource && scibrowsedatasource != null)
            SLog.e(LOG_TAG, "Calling Subscribe twice on the same data source!!!!");
        unsubscribe();
        subscribedDataSource = scibrowsedatasource;
        if(subscribedDataSource != null)
        {
            if(!subscribedDataSource.isGone())
            {
                SLog.i(LOG_TAG, (new StringBuilder()).append("Subscribing EventSink: ").append(subscribedDataSource.getSCUri()).toString());
                subscribedDataSource.subscribe(this);
                activeSubscriptions = 1 + activeSubscriptions;
                if(activeSubscriptions > 10)
                    SLog.e(LOG_TAG, (new StringBuilder()).append("There are a LOT of event sinks currently Subscribed: ").append(activeSubscriptions).toString());
            } else
            {
                SLog.w(LOG_TAG, (new StringBuilder()).append("Failed to subscribe to datasource [").append(scibrowsedatasource.getSCUri()).append("]. DataSource is gone.").toString());
                onBrowseInvalidation(subscribedDataSource);
            }
        } else
        {
            SLog.w(LOG_TAG, "Failed to subscribe to datasource.  DataSource was null");
        }
    }

    public void unsubscribe()
    {
        if(subscribedDataSource != null)
        {
            SLog.i(LOG_TAG, "Unsubscribing EventSink: ");
            subscribedDataSource.unsubscribe(this);
            subscribedDataSource = null;
            activeSubscriptions = -1 + activeSubscriptions;
        }
    }

    private static int activeSubscriptions = 0;
    private final String LOG_TAG = (new StringBuilder()).append(com/sonos/acr/browse/v2/BrowseDataSourceEventSink.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
    protected SCIBrowseDataSource subscribedDataSource;

}
