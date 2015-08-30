// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.services.builder.FullNowPlayingRemoteViewBuilder;
import com.sonos.acr.services.builder.WidgetNowPlayingViewBuilder;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;

public class RoomWidget extends ContextWrapper
    implements com.sonos.acr.services.builder.RemoteImageViewController.RemoteImageViewListener
{

    public RoomWidget(Context context, int i, String s)
    {
        super(context);
        dismissMessage = new Runnable() {

            public void run()
            {
                widgetBuilder.setInfoMessage(null);
                updateWidget();
            }

            final RoomWidget this$0;

            
            {
                this$0 = RoomWidget.this;
                super();
            }
        }
;
        connected = false;
        widgetId = i;
        deviceId = s;
        widgetBuilder = new WidgetNowPlayingViewBuilder(this, s);
        widgetBuilder.setEnabledAlpha(255).setDisabledAlpha(75).setImageViewListener(this);
        appWidgetManager = AppWidgetManager.getInstance(this);
        SLog.e(LOG_TAG, (new StringBuilder()).append("Creating Room widget with deviceId: ").append(s).toString());
        handler = SonosApplication.getInstance().getHandler();
        WidgetNowPlayingViewBuilder widgetnowplayingviewbuilder = widgetBuilder;
        String s1;
        if(connected)
            s1 = null;
        else
            s1 = getString(0x7f0c011a);
        widgetnowplayingviewbuilder.setSystemInfoMessage(s1, false);
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            Bundle bundle = appWidgetManager.getAppWidgetOptions(i);
            if(bundle != null)
            {
                int j = (int)TypedValue.applyDimension(1, bundle.getInt("appWidgetMaxHeight"), context.getResources().getDisplayMetrics());
                int k = (int)context.getResources().getDimension(0x7f090085);
                WidgetNowPlayingViewBuilder widgetnowplayingviewbuilder1 = widgetBuilder;
                boolean flag;
                if(j >= k)
                    flag = true;
                else
                    flag = false;
                widgetnowplayingviewbuilder1.setShowThirdLineMetadata(flag);
            }
        }
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void onImageUpdated()
    {
        updateWidget();
    }

    public void reset()
    {
        widgetBuilder.cancel();
        handler.removeCallbacks(dismissMessage);
    }

    public void setConnected(boolean flag)
    {
        if(flag != connected)
        {
            WidgetNowPlayingViewBuilder widgetnowplayingviewbuilder = widgetBuilder;
            String s;
            if(flag)
                s = null;
            else
                s = getString(0x7f0c011a);
            widgetnowplayingviewbuilder.setSystemInfoMessage(s, false);
        }
        connected = flag;
    }

    protected void showMessage(ZoneGroup zonegroup, String s, long l)
    {
        widgetBuilder.setInfoMessage(s);
        handler.removeCallbacks(dismissMessage);
        handler.postDelayed(dismissMessage, l);
        updateWidget(zonegroup);
    }

    protected void updateWidget()
    {
        updateWidget(LibraryUtils.getHousehold().lookupZoneGroupByDevice(deviceId));
    }

    protected void updateWidget(ZoneGroup zonegroup)
    {
        if(appWidgetManager != null)
        {
            AppWidgetManager appwidgetmanager = appWidgetManager;
            int i = widgetId;
            WidgetNowPlayingViewBuilder widgetnowplayingviewbuilder = widgetBuilder;
            if(!connected)
                zonegroup = null;
            appwidgetmanager.updateAppWidget(i, widgetnowplayingviewbuilder.createView(zonegroup, widgetId));
        }
    }

    public static final String LOG_TAG = com/sonos/acr/services/widgets/RoomWidget.getSimpleName();
    public static final String NOWPLAYING_WIDGET_DEVICE_ID = "NOWPLAYING_WIDGET_DEVICE_ID";
    AppWidgetManager appWidgetManager;
    private boolean connected;
    String deviceId;
    Runnable dismissMessage;
    Handler handler;
    private WidgetNowPlayingViewBuilder widgetBuilder;
    int widgetId;


}
