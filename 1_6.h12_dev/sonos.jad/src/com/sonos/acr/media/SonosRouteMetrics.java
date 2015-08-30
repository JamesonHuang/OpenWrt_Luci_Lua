// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import com.sonos.sclib.*;
import java.util.Date;

public class SonosRouteMetrics
{

    public SonosRouteMetrics(String s)
    {
        routeSelectedTime = 0L;
        beginPlayTime = 0L;
        totalPlayTime = 0L;
        reporting = null;
        reporting = sclib.getAppReportingInstance();
        metricsCommonData = sclib.createPropertyBag();
        metricsCommonData.setStrProp("RouteId", s);
    }

    private long calculateElapsedTime(long l)
    {
        long l1 = (new Date()).getTime();
        long l2 = 0L;
        if(l != 0L && l1 > l)
            l2 = l1 - l;
        return l2;
    }

    private SCIPropertyBag createMetricsProperties()
    {
        SCIPropertyBag scipropertybag = sclib.createPropertyBag();
        metricsCommonData.copyAllValuesTo(scipropertybag);
        return scipropertybag;
    }

    private void resetMetrics()
    {
        routeSelectedTime = 0L;
        beginPlayTime = 0L;
        totalPlayTime = 0L;
    }

    private void resumePlayTimer()
    {
        totalPlayTime = calculateElapsedTime(beginPlayTime) + totalPlayTime;
        beginPlayTime = (new Date()).getTime();
    }

    private int stopPlayTimer()
    {
        totalPlayTime = calculateElapsedTime(beginPlayTime) + totalPlayTime;
        beginPlayTime = 0L;
        return (int)totalPlayTime / 1000;
    }

    public void reportNewSession(String s)
    {
        metricsCommonData.setStrProp("ClientApp", s);
        SCIPropertyBag scipropertybag = createMetricsProperties();
        scipropertybag.setStrProp("EventAction", "New Session");
        reporting.reportEventWithProps("MediaRouteProvider", "MediaRouteProvider", scipropertybag);
    }

    public void reportRouteDeselected()
    {
        int i = stopPlayTimer();
        int j = (int)calculateElapsedTime(routeSelectedTime) / 1000;
        SCIPropertyBag scipropertybag = createMetricsProperties();
        scipropertybag.setStrProp("EventAction", "Deselect Route");
        scipropertybag.setIntProp("PlayDuration", i);
        scipropertybag.setIntProp("RouteDuration", j);
        reporting.reportEventWithProps("MediaRouteProvider", "MediaRouteProvider", scipropertybag);
        resetMetrics();
    }

    public void reportRouteSelected()
    {
        routeSelectedTime = (new Date()).getTime();
        SCIPropertyBag scipropertybag = createMetricsProperties();
        scipropertybag.setStrProp("EventAction", "Select Route");
        reporting.reportEventWithProps("MediaRouteProvider", "MediaRouteProvider", scipropertybag);
    }

    public void updateRoutePlayState(int i)
    {
        if(i == 1)
            resumePlayTimer();
        else
            stopPlayTimer();
    }

    private static final String METRICS_CATEGORY = "MediaRouteProvider";
    private static final String METRICS_EVENT_ACTION_KEY = "EventAction";
    private static final String METRICS_EVENT_DESELECT_ROUTE = "Deselect Route";
    private static final String METRICS_EVENT_NAME = "MediaRouteProvider";
    private static final String METRICS_EVENT_NEWSESSION = "New Session";
    private static final String METRICS_EVENT_SELECT_ROUTE = "Select Route";
    private static final String METRICS_INFO_CLIENTAPP = "ClientApp";
    private static final String METRICS_INFO_PLAY_DURATION = "PlayDuration";
    private static final String METRICS_INFO_ROUTEID = "RouteId";
    private static final String METRICS_INFO_ROUTE_DURATION = "RouteDuration";
    private long beginPlayTime;
    private SCIPropertyBag metricsCommonData;
    private SCIAppReporting reporting;
    private long routeSelectedTime;
    private long totalPlayTime;
}
