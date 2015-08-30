// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import com.sonos.acr.application.ApplicationStateManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;

// Referenced classes of package com.sonos.acr.services:
//            RemoteViewTransportController

public abstract class SonosService extends Service
    implements com.sonos.acr.sclib.SCLibManager.MessageListener
{

    public SonosService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        SonosApplication sonosapplication = SonosApplication.getInstance();
        if(!sonosapplication.getSCLibManager().isInitialized())
            sonosapplication.startServices();
        handler = sonosapplication.getHandler();
        transportController = new RemoteViewTransportController(this);
        LibraryUtils.getSCLibManager().addMessageListener(this);
_L1:
        return;
        UnsatisfiedLinkError unsatisfiedlinkerror;
        unsatisfiedlinkerror;
        LibraryUtils.showLinkErrors();
        ApplicationStateManager.getInstance().closeAllActivities();
          goto _L1
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
        LibraryUtils.showLinkErrors();
        ApplicationStateManager.getInstance().closeAllActivities();
          goto _L1
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        super.onStartCommand(intent, i, j);
        if(transportController != null)
            transportController.handleIntent(intent);
        return 0;
    }

    public void startActivity(Intent intent)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Starting new Activity: ").append(intent).toString());
        super.startActivity(intent);
    }

    public void startActivity(Intent intent, Bundle bundle)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Starting new Activity: ").append(intent).toString());
        super.startActivity(intent, bundle);
    }

    public final String LOG_TAG = getClass().getSimpleName();
    protected Handler handler;
    protected RemoteViewTransportController transportController;
}
