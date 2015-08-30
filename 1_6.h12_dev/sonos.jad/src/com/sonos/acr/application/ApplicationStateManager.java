// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Process;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.SonosListener;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.*;
import com.splunk.mint.Mint;
import java.util.*;

// Referenced classes of package com.sonos.acr.application:
//            SonosApplication

public class ApplicationStateManager
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{

    public ApplicationStateManager()
    {
        sonosActivities = new ArrayList();
        pausedActivities = new ArrayList();
    }

    private static void finishHim()
    {
        SonosApplication.getInstance().getHandler().postDelayed(new Runnable() {

            public void run()
            {
                Process.killProcess(Process.myPid());
            }

        }
, 500L);
    }

    public static ApplicationStateManager getInstance()
    {
        if(instance == null)
            instance = new ApplicationStateManager();
        return instance;
    }

    public void activityCreated(SonosActivity sonosactivity)
    {
        sonosActivities.add(sonosactivity);
        pausedActivities.add(sonosactivity);
    }

    public void activityDestroyed(SonosActivity sonosactivity)
    {
        sonosActivities.remove(sonosactivity);
        pausedActivities.remove(sonosactivity);
    }

    public void activityPaused(SonosActivity sonosactivity)
    {
        pausedActivities.add(sonosactivity);
    }

    public void activityResumed(SonosActivity sonosactivity)
    {
        if(pausedActivities.size() == sonosActivities.size())
            SLog.i(LOG_TAG, "Activity Resumed!");
        pausedActivities.remove(sonosactivity);
    }

    void addExtraBugsenseInfo()
    {
        Mint.clearExtraData();
        HashMap hashmap = new HashMap();
        hashmap.put("isUserAMonkey", String.valueOf(SonosApplication.isUserAMonkey()));
        LibraryUtils.getHousehold();
        SonosApplication sonosapplication = SonosApplication.getInstance();
        if(sonosapplication != null)
        {
            if(sonosapplication.getListener() != null)
            {
                String s = sonosapplication.getListener().getHouseholdID();
                if(StringUtils.isNotEmptyOrNull(s))
                {
                    hashmap.put("hhid", s);
                    Mint.setUserIdentifier(s);
                }
            }
            try
            {
                PackageInfo packageinfo = sonosapplication.getPackageManager().getPackageInfo(sonosapplication.getPackageName(), 0);
                if(packageinfo != null)
                    hashmap.put("VersionCode", Integer.toString(packageinfo.versionCode));
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                SLog.e(LOG_TAG, "Error with bugsense: ", namenotfoundexception);
            }
        }
        Mint.addExtraDataMap(hashmap);
_L1:
        return;
        Exception exception;
        exception;
        SLog.e(LOG_TAG, "Error with bugsense: ", exception);
          goto _L1
    }

    public void closeAllActivities()
    {
        Iterator iterator = sonosActivities.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Context context = (Context)iterator.next();
            if(context instanceof SonosActivity)
                ((SonosActivity)context).finish();
        } while(true);
    }

    public void closeAllActivitiesExcept(SonosActivity sonosactivity)
    {
        Iterator iterator = sonosActivities.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Context context = (Context)iterator.next();
            if((context instanceof SonosActivity) && context != sonosactivity && !((SonosActivity)context).isFinishing())
                ((SonosActivity)context).finish();
        } while(true);
    }

    public void killApp()
    {
        SonosApplication.getInstance().getHandler().post(new Runnable() {

            public void run()
            {
                closeAllActivities();
                ApplicationStateManager.finishHim();
            }

            final ApplicationStateManager this$0;

            
            {
                this$0 = ApplicationStateManager.this;
                super();
            }
        }
);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent != com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnCurrentZoneGroupChanged);
        if(household != null)
            addExtraBugsenseInfo();
    }

    public void start()
    {
        HouseholdEventSink.getInstance().addListener(this);
    }

    public void stop()
    {
        HouseholdEventSink.getInstance().removeListener(this);
    }

    public static final String LOG_TAG = com/sonos/acr/application/ApplicationStateManager.getSimpleName();
    private static ApplicationStateManager instance;
    public ArrayList pausedActivities;
    public ArrayList sonosActivities;


}
