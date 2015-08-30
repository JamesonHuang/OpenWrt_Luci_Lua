// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import com.sonos.acr.util.SLog;

// Referenced classes of package com.sonos.acr.services.widgets:
//            SonosRemoteViewsService

public class HouseholdNowPlayingWidgetProvider extends AppWidgetProvider
{

    public HouseholdNowPlayingWidgetProvider()
    {
    }

    public void onUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
        SLog.e(com/sonos/acr/services/widgets/HouseholdNowPlayingWidgetProvider.getSimpleName(), (new StringBuilder()).append("Update called on Widgets: ").append(ai.length).toString());
        Intent intent = new Intent(context, com/sonos/acr/services/widgets/SonosRemoteViewsService);
        intent.setAction("ACTION_UPDATE_HOUSEHOLD_WIDGET");
        context.startService(intent);
        super.onUpdate(context, appwidgetmanager, ai);
    }
}
