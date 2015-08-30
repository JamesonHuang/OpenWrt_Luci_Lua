// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.*;
import android.os.Bundle;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCIAppReporting;
import com.sonos.sclib.sclib;

// Referenced classes of package com.sonos.acr.services.widgets:
//            RoomWidgetService

public class RoomWidgetProvider extends AppWidgetProvider
{

    public RoomWidgetProvider()
    {
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appwidgetmanager, int i, Bundle bundle)
    {
        SLog.e(LOG_TAG, "onAppWidgetOptionsChanged()");
        RoomWidgetService.init(context);
        super.onAppWidgetOptionsChanged(context, appwidgetmanager, i, bundle);
    }

    public void onDeleted(Context context, int ai[])
    {
        sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.DEACTIVATED, com.sonos.sclib.SCIAppReporting.SCViewID.WIDGET, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_NONE);
        if(AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetIds(new ComponentName(context.getApplicationContext(), com/sonos/acr/services/widgets/RoomWidgetProvider)).length == 0)
            context.getApplicationContext().startService((new Intent(context.getApplicationContext(), com/sonos/acr/services/widgets/RoomWidgetService)).setAction("STOP_SERVICE"));
    }

    public void onUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
        SLog.e(LOG_TAG, "onUpdate()");
        RoomWidgetService.init(context);
        sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.ACTIVATED, com.sonos.sclib.SCIAppReporting.SCViewID.WIDGET, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_NONE);
        super.onUpdate(context, appwidgetmanager, ai);
    }

    private static final String LOG_TAG = com/sonos/acr/services/widgets/RoomWidgetProvider.getSimpleName();

}
