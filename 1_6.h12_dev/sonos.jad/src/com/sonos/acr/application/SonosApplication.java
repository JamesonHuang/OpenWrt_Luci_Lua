// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import com.sonos.acr.localaudiolibrary.LocalMusicService;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.SonosListener;
import com.sonos.acr.services.notification.NotificationService;
import com.sonos.acr.util.*;
import com.sonos.sclib.SCIDealerMode;
import com.sonos.sclib.SCISystem;
import com.splunk.mint.Mint;
import java.io.*;
import java.util.HashMap;

// Referenced classes of package com.sonos.acr.application:
//            NetworkSearchStateManager, AppDataStore, ApplicationStateManager

public class SonosApplication extends Application
    implements com.sonos.acr.network.SonosNetworkManager.ConnectionListener
{
    public static final class ReleaseType extends Enum
    {

        public static ReleaseType valueOf(String s)
        {
            return (ReleaseType)Enum.valueOf(com/sonos/acr/application/SonosApplication$ReleaseType, s);
        }

        public static ReleaseType[] values()
        {
            return (ReleaseType[])$VALUES.clone();
        }

        private static final ReleaseType $VALUES[];
        public static final ReleaseType ALPHA;
        public static final ReleaseType BETA;
        public static final ReleaseType PROD;

        static 
        {
            ALPHA = new ReleaseType("ALPHA", 0);
            BETA = new ReleaseType("BETA", 1);
            PROD = new ReleaseType("PROD", 2);
            ReleaseType areleasetype[] = new ReleaseType[3];
            areleasetype[0] = ALPHA;
            areleasetype[1] = BETA;
            areleasetype[2] = PROD;
            $VALUES = areleasetype;
        }

        private ReleaseType(String s, int i)
        {
            super(s, i);
        }
    }


    public SonosApplication()
    {
        servicesStarted = false;
    }

    private void cleanStartupShutdown()
    {
        android.content.SharedPreferences.Editor editor = getSharedPreferences("Lifecycle", 0).edit();
        editor.putInt("Closed", 0);
        editor.putInt("Opened", 0);
        editor.commit();
    }

    private void clearNotifications()
    {
        startService((new Intent(this, com/sonos/acr/services/notification/NotificationService)).setAction("ACTION_END_NOTIFICATIONS"));
    }

    public static AppDataStore getAppDataStore()
    {
        return appDataStore;
    }

    public static SonosApplication getInstance()
    {
        return instance;
    }

    public static boolean isDebuggable()
    {
        boolean flag;
        if(instance != null && instance.getApplicationInfo() != null && (2 & instance.getApplicationInfo().flags) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isUserAMonkey()
    {
        boolean flag = false;
        if(DbgProp.getInstance().get("isMonkey", false) || ActivityManager.isUserAMonkey())
            flag = true;
        return flag;
    }

    private void readAndDumpStream(OutputStream outputstream, InputStream inputstream)
        throws IOException
    {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        do
        {
            String s = bufferedreader.readLine();
            if(s != null)
            {
                outputstream.write(s.getBytes());
                outputstream.write("<br/>&#x000D;&#x000A;".getBytes());
            } else
            {
                return;
            }
        } while(true);
    }

    private void writeShutdownPreference()
    {
        SharedPreferences sharedpreferences = getSharedPreferences("Lifecycle", 0);
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("Closed", 1 + sharedpreferences.getInt("Closed", 0));
        editor.commit();
    }

    private void writeStartupPreference()
    {
        SharedPreferences sharedpreferences = getSharedPreferences("Lifecycle", 0);
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("Opened", 1 + sharedpreferences.getInt("Opened", 0));
        editor.commit();
    }

    void addBasicBugsenseInfo()
    {
        Mint.clearExtraData();
        HashMap hashmap = new HashMap();
        hashmap.put("isUserAMonkey", String.valueOf(isUserAMonkey()));
        LibraryUtils.getHousehold();
        SonosApplication sonosapplication = getInstance();
        if(sonosapplication != null)
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
        Mint.addExtraDataMap(hashmap);
_L1:
        return;
        Exception exception;
        exception;
        SLog.e(LOG_TAG, "Error with bugsense: ", exception);
          goto _L1
    }

    public void clearApplicationData()
    {
        File file = new File(getCacheDir().getParent());
        if(file.exists())
        {
            String as[] = file.list();
            int i = as.length;
            for(int j = 0; j < i; j++)
            {
                String s = as[j];
                if(!s.equals("lib"))
                    deleteDir(new File(file, s));
            }

        }
    }

    public boolean deleteDir(File file)
    {
        boolean flag = false;
        if(file == null) goto _L2; else goto _L1
_L1:
        String as[];
        int i;
        if(!file.isDirectory())
            break MISSING_BLOCK_LABEL_55;
        as = file.list();
        i = 0;
_L4:
        if(i >= as.length)
            break MISSING_BLOCK_LABEL_55;
        if(deleteDir(new File(file, as[i]))) goto _L3; else goto _L2
_L2:
        return flag;
_L3:
        i++;
          goto _L4
        flag = file.delete();
          goto _L2
    }

    public Handler getHandler()
    {
        return mainThreadHandler;
    }

    public SonosListener getListener()
    {
        return sonosListener;
    }

    public NetworkSearchStateManager getNetworkSearchStateMonitor()
    {
        return networkSearchStateManager;
    }

    public SonosNetworkManager getNetworkStatusMonitor()
    {
        return networkManager;
    }

    public SCLibManager getSCLibManager()
    {
        return scLibManager;
    }

    public SharedPreferences getSharedPreferences()
    {
        return sharedPreferences;
    }

    public UserActivityMonitor getUserActivityMonitor()
    {
        return userActivityMonitor;
    }

    public boolean isReleaseBuild()
    {
        return getResources().getString(0x7f0c0036).equals("RELEASE");
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        DisplayController.resolvePreferredDensities(getResources());
        super.onConfigurationChanged(configuration);
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        if(getSCLibManager().getLibrary() != null)
            if(sonosnetworkmanager.isWifiConnected())
            {
                getSCLibManager().startNetworking();
                getInstance().getNetworkSearchStateMonitor().startConnectionSearchTimer();
                LocalMusicService.init(getInstance());
            } else
            {
                getInstance().getNetworkSearchStateMonitor().stopConnectionSearchTimer();
                getSCLibManager().stopNetworking();
                getInstance().stopService(new Intent(getInstance(), com/sonos/acr/localaudiolibrary/LocalMusicService));
            }
    }

    public void onCreate()
    {
        super.onCreate();
        instance = this;
        sharedPreferences = getSharedPreferences("SonosPreferences", 0);
        DisplayController.init(getResources(), sharedPreferences);
        if(isDebuggable())
            break MISSING_BLOCK_LABEL_252;
        String s = getResources().getString(0x7f0c0036);
        if(s.equals("ALPHA"))
        {
            SLog.setDefaultLogLevel(3);
            RELEASE_TYPE = ReleaseType.ALPHA;
        } else
        if(s.equals("BETA"))
        {
            SLog.setDefaultLogLevel(4);
            RELEASE_TYPE = ReleaseType.BETA;
        } else
        {
            SLog.setDefaultLogLevel(5);
            RELEASE_TYPE = ReleaseType.PROD;
        }
        Mint.setFlushOnlyOverWiFi(true);
        if(!s.equals("ALPHA") && !s.equals("BETA")) goto _L2; else goto _L1
_L1:
        Mint.initAndStartSession(this, "8d4f59ef");
_L3:
        Thread.setDefaultUncaughtExceptionHandler(new SonosDefaultExceptionHandler());
        scLibManager = new SCLibManager(this, mainThreadHandler);
        networkManager = new SonosNetworkManager(this);
        networkSearchStateManager = new NetworkSearchStateManager();
        sonosListener = new SonosListener();
        appDataStore = new AppDataStore();
        userActivityMonitor = new UserActivityMonitor(this);
        Exception exception;
        if(android.os.Build.VERSION.SDK_INT >= 9)
            try
            {
                NativeCrashHelper.initialize();
            }
            catch(Throwable throwable)
            {
                SLog.e(LOG_TAG, "Error initializing native crash reporter", throwable);
            }
        addBasicBugsenseInfo();
        cleanStartupShutdown();
        return;
_L2:
        try
        {
            Mint.initAndStartSession(this, "0c03081c");
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception) { }
          goto _L3
        SLog.setDefaultLogLevel(2);
          goto _L3
    }

    public void onLowMemory()
    {
        AlbumArtSize.onLowMemory();
        SCLibManager.onLowMemory();
        super.onLowMemory();
    }

    public void onShutdown()
    {
        writeShutdownPreference();
    }

    public void onStartup()
    {
        writeStartupPreference();
    }

    public void onTerminate()
    {
        stopServices();
        networkManager = null;
        sonosListener = null;
        super.onTerminate();
        SLog.e(LOG_TAG, "SonosApplication Terminated");
    }

    public void onTrimMemory(int i)
    {
        if(i == 10 || i == 15 || i == 5 || i == 60 || i == 80)
        {
            AlbumArtSize.onLowMemory();
            SCLibManager.onLowMemory();
            String s = LOG_TAG;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            SLog.w(s, String.format("onTrimMemory Called. Level = %d", aobj));
        }
        super.onTrimMemory(i);
    }

    public void reset()
    {
        LibraryUtils.getSharedPreferences().edit().clear().commit();
        clearNotifications();
        SCISystem scisystem = LibraryUtils.getSystem();
        if(scisystem != null)
        {
            SCIDealerMode scidealermode = scLibManager.getDealerMode();
            if(scidealermode != null)
            {
                if(scidealermode.isDealerLock())
                    scidealermode.setDealerLock(false);
                if(scidealermode.isStoresAliveEnabled())
                    scidealermode.setStoresAlive(false);
            }
            boolean flag = scisystem.factoryResetConfigFiles();
            String s;
            if(flag)
                s = getString(0x7f0c004a);
            else
                s = getString(0x7f0c0049);
            SonosToast.popup(s);
            if(flag)
            {
                clearApplicationData();
                networkManager.onFactoryReset();
                ApplicationStateManager.getInstance().killApp();
            }
        }
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

    public void startServices()
    {
        if(servicesStarted)
            break MISSING_BLOCK_LABEL_78;
        SLog.w(LOG_TAG, "Starting sonos services");
        scLibManager.initialize();
        networkManager.start();
        networkManager.subscribe(this);
        sonosListener.start();
        scLibManager.resumeNetworking();
        userActivityMonitor.start();
        ApplicationStateManager.getInstance().start();
        networkSearchStateManager.start();
        servicesStarted = true;
_L1:
        return;
        Exception exception;
        exception;
        SLog.e(LOG_TAG, "There was an error starting Sonos Services", exception);
          goto _L1
    }

    public void stopServices()
    {
        if(servicesStarted)
        {
            SLog.w(LOG_TAG, "Stopping sonos services");
            scLibManager.suspendNetworking();
            userActivityMonitor.stop();
            networkManager.unsubscribe(this);
            networkManager.stop();
            sonosListener.stop();
            ApplicationStateManager.getInstance().stop();
            networkSearchStateManager.stop();
            scLibManager.termSCLib();
            servicesStarted = false;
            SLog.w(LOG_TAG, "Stopping complete");
        }
    }

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String LOG_TAG = com/sonos/acr/application/SonosApplication.getSimpleName();
    public static ReleaseType RELEASE_TYPE;
    public static final String SONOS_PREFERENCES = "SonosPreferences";
    private static final String TAG = "SonosApplication";
    private static AppDataStore appDataStore;
    private static SonosApplication instance;
    private final Handler mainThreadHandler = new Handler();
    private SonosNetworkManager networkManager;
    private NetworkSearchStateManager networkSearchStateManager;
    private SCLibManager scLibManager;
    private boolean servicesStarted;
    private SharedPreferences sharedPreferences;
    private SonosListener sonosListener;
    private UserActivityMonitor userActivityMonitor;

}
