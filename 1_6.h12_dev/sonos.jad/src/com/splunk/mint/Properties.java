// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.File;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.splunk.mint:
//            ExcludedUrls, BreadcrumbsLimited, ExtraData, EnumStateStatus, 
//            TransactionsDatabase, EnumActionType, Utils, UidManager, 
//            MintUrls, RemoteSettings, RemoteSettingsData, Logger, 
//            Mint, MintLogLevel

public class Properties
{
    public static class RemoteSettingsProps
    {

        public static String toReadableFormat()
        {
            return (new StringBuilder()).append("loglevel: ").append(String.valueOf(logLevel)).append(" eventLevel: ").append(String.valueOf(eventLevel)).append(" netMonitoring: ").append(String.valueOf(netMonitoringEnabled)).append(" sessionTime: ").append(String.valueOf(sessionTime)).append(" devSettings: ").append(devSettings.toString()).append(" hashCode: ").append(hashCode).toString();
        }

        static JSONObject devSettings = new JSONObject();
        static Integer eventLevel;
        static String hashCode = "none";
        static Integer logLevel;
        static Boolean netMonitoringEnabled = Boolean.valueOf(true);
        static Integer sessionTime = Integer.valueOf(60);

        static 
        {
            logLevel = Integer.valueOf(Utils.convertLoggingLevelToInt(MintLogLevel.Verbose));
            eventLevel = Integer.valueOf(Utils.convertLoggingLevelToInt(MintLogLevel.Verbose));
        }

        public RemoteSettingsProps()
        {
        }
    }


    public Properties()
    {
    }

    protected static final String getSeparator(EnumActionType enumactiontype)
    {
        return (new StringBuilder()).append("{^1^").append(enumactiontype.toString()).append("^").append(Utils.getTime()).append("}").toString();
    }

    protected static void initialize(Context context, String s, String s1)
    {
        if(!initialized)
        {
            UID = UidManager.getUid(context);
            if(mintUrls == null && s1 != null)
                mintUrls = new MintUrls(s, s1);
            RemoteSettingsData remotesettingsdata;
            try
            {
                PackageInfo packageinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                APP_VERSIONNAME = packageinfo.versionName;
                APP_VERSIONCODE = String.valueOf(packageinfo.versionCode);
                APP_PACKAGE = packageinfo.packageName;
            }
            catch(Exception exception)
            {
                Logger.logError("Error collecting information about the package!");
                if(Mint.DEBUG)
                    exception.printStackTrace();
            }
            PHONE_MODEL = Build.MODEL;
            PHONE_BRAND = Build.MANUFACTURER;
            OS_VERSION = android.os.Build.VERSION.RELEASE;
            HAS_ROOT = Utils.checkForRoot();
            isKitKat = Utils.isKitKat();
            if(breadcrumbs == null)
                breadcrumbs = new BreadcrumbsLimited();
            if(extraData == null)
                extraData = new ExtraData();
            if(transactionsDatabase == null)
                transactionsDatabase = new TransactionsDatabase();
            try
            {
                FILES_PATH = context.getFilesDir().getAbsolutePath();
            }
            catch(Exception exception1)
            {
                if(Mint.DEBUG)
                    exception1.printStackTrace();
            }
            remotesettingsdata = RemoteSettings.loadRemoteSettings(context);
            if(remotesettingsdata != null)
            {
                RemoteSettingsProps.logLevel = remotesettingsdata.logLevel;
                RemoteSettingsProps.eventLevel = remotesettingsdata.eventLevel;
                RemoteSettingsProps.netMonitoringEnabled = remotesettingsdata.netMonitoring;
                RemoteSettingsProps.sessionTime = remotesettingsdata.sessionTime;
                RemoteSettingsProps.hashCode = remotesettingsdata.hashCode;
                HashMap hashmap;
                try
                {
                    RemoteSettingsProps.devSettings = new JSONObject(remotesettingsdata.devSettings);
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
            }
            initialized = true;
        }
        IS_GPS_ON = Utils.isGPSOn(context);
        LOCALE = Locale.getDefault().getCountry();
        if(LOCALE == null || LOCALE.length() == 0)
            LOCALE = "NA";
        CARRIER = Utils.getCarrier(context);
        SCREEN_ORIENTATION = Utils.getScreenOrientation(context);
        hashmap = Utils.getConnectionInfo(context);
        CONNECTION = (String)hashmap.get("connection");
        STATE = (String)hashmap.get("state");
    }

    public static boolean isPluginInitialized()
    {
        if(!initialized)
            Logger.logWarning("Mint SDK is not initialized!");
        return initialized;
    }

    static String API_KEY = "NA";
    protected static final String API_VERSION = "1";
    static String APP_PACKAGE = "NA";
    static String APP_VERSIONCODE = "NA";
    static String APP_VERSIONNAME = "NA";
    static String CARRIER = "NA";
    static String CONNECTION = "NA";
    static String FILES_PATH = null;
    static boolean HAS_ROOT = false;
    static EnumStateStatus IS_GPS_ON;
    static String LOCALE = "NA";
    static String LOG_FILTER = "";
    static int LOG_LINES = 0;
    static String OS_VERSION = "NA";
    static String PHONE_BRAND = null;
    static String PHONE_MODEL = "NA";
    static final String REMOTEIP_PLACEHOLDER = "{%#@@#%}";
    public static final String REST_VERSION = "1.0";
    static String SCREEN_ORIENTATION = "NA";
    protected static final String SDK_PLATFORM = "Android";
    protected static final String SDK_VERSION = "4.1";
    static boolean SEND_LOG = false;
    static String STATE = "NA";
    static final String TAG = "Mint";
    static long TIMESTAMP = 0L;
    static String UID = "";
    static BreadcrumbsLimited breadcrumbs = new BreadcrumbsLimited();
    private static final String defaultExcludedUrls[];
    public static final ExcludedUrls excludedUrls;
    static ExtraData extraData = new ExtraData();
    public static boolean flushOnlyOverWiFi = false;
    private static boolean initialized = false;
    public static boolean isKitKat = false;
    public static long lastPingTime = 0L;
    private static MintUrls mintUrls = null;
    static boolean proxyEnabled = false;
    static boolean sendOnlyWiFi = false;
    public static ArrayList transactions = new ArrayList(2);
    static volatile TransactionsDatabase transactionsDatabase = new TransactionsDatabase();
    static String userIdentifier = "NA";

    static 
    {
        String as[] = new String[1];
        as[0] = "splkmobile.com";
        defaultExcludedUrls = as;
        excludedUrls = new ExcludedUrls(defaultExcludedUrls);
        HAS_ROOT = false;
        SEND_LOG = false;
        LOG_LINES = 5000;
        IS_GPS_ON = EnumStateStatus.NA;
    }
}
