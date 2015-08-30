// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.widgets;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;

// Referenced classes of package com.sonos.acr.services.widgets:
//            StackViewsFactory, HouseholdNowPlayingWidgetProvider

public class SonosRemoteViewsService extends RemoteViewsService
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{

    public SonosRemoteViewsService()
    {
    }

    private void handleIntent(Intent intent)
    {
        if("ACTION_UPDATE_HOUSEHOLD_WIDGET".equals(intent.getAction()))
            startHouseholdWidget(true);
    }

    private void startHouseholdWidget(boolean flag)
    {
        if(flag)
            stackedView = null;
        if(stackedView == null)
        {
            stackedView = new RemoteViews(getPackageName(), 0x7f0300b0);
            onHouseholdWidgetUpdate(new RemoteViews(getPackageName(), 0x7f0300b0));
        }
    }

    public void onCreate()
    {
        super.onCreate();
        appWidgetManager = AppWidgetManager.getInstance(this);
    }

    public void onDestroy()
    {
        super.onDestroy();
    }

    public android.widget.RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent)
    {
        SLog.e(LOG_TAG, "onGetViewFactory called!");
        if(stackViewsFactory == null)
            stackViewsFactory = new StackViewsFactory(this);
        return stackViewsFactory;
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged)
            startHouseholdWidget(true);
    }

    public void onHouseholdWidgetUpdate(RemoteViews remoteviews)
    {
        AppWidgetManager appwidgetmanager = AppWidgetManager.getInstance(this);
        int ai[] = appwidgetmanager.getAppWidgetIds(new ComponentName(this, com/sonos/acr/services/widgets/HouseholdNowPlayingWidgetProvider));
        if(ai != null && ai.length > 0)
        {
            int i = ai.length;
            for(int j = 0; j < i; j++)
            {
                int k = ai[j];
                SLog.e(LOG_TAG, (new StringBuilder()).append("updating big widget: ").append(k).toString());
                Intent intent = (new Intent(this, com/sonos/acr/services/widgets/SonosRemoteViewsService)).putExtra("appWidgetId", k);
                intent.setData(Uri.fromParts("content", String.valueOf(k), null));
                remoteviews.setRemoteAdapter(k, 0x7f0a01fc, intent);
                appwidgetmanager.updateAppWidget(k, remoteviews);
            }

        } else
        {
            SLog.w(LOG_TAG, "No HOUSEHOLD widgets to update");
        }
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        super.onStartCommand(intent, i, j);
        if(!LibraryUtils.getSCLibManager().isInitialized())
            SonosApplication.getInstance().startServices();
        if(intent != null)
            handleIntent(intent);
        return 1;
    }

    public void onWidgetUpdated()
    {
        AppWidgetManager appwidgetmanager = AppWidgetManager.getInstance(this);
        appwidgetmanager.notifyAppWidgetViewDataChanged(appwidgetmanager.getAppWidgetIds(new ComponentName(this, com/sonos/acr/services/widgets/HouseholdNowPlayingWidgetProvider)), 0x7f0a01fc);
    }

    public static final String ACTION_UPDATE_HOUSEHOLD_WIDGET = "ACTION_UPDATE_HOUSEHOLD_WIDGET";
    public static final String LOG_TAG = com/sonos/acr/services/widgets/SonosRemoteViewsService.getSimpleName();
    AppWidgetManager appWidgetManager;
    StackViewsFactory stackViewsFactory;
    private RemoteViews stackedView;

}
