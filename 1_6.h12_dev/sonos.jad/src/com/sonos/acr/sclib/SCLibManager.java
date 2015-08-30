// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.*;
import android.content.res.*;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.*;
import android.util.DisplayMetrics;
import com.sonos.acr.Loc;
import com.sonos.acr.application.*;
import com.sonos.acr.location.SonosLocationServices;
import com.sonos.acr.network.*;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.services.ActionFactoryDelegate;
import com.sonos.acr.util.*;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import com.sonos.sclib.*;
import java.io.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.sclib:
//            AndroidDiagnosticCB, AndroidDiagnosticConsoleLogCB, AndroidLoggerCB, AndroidAssertionFailureCB, 
//            ITQHandler, PlatformDateTimeProvider

public class SCLibManager
    implements android.os.MessageQueue.IdleHandler, Runnable
{
    public static interface MessageListener
    {

        public abstract void onZoneGroupMessage(ZoneGroup zonegroup, String s, String s1, long l);
    }


    public SCLibManager(SonosApplication sonosapplication, Handler handler1)
    {
        lastNetworkSSID = "";
        deferredDeletionList = new ArrayList();
        firstRunAfterUpdate = false;
        handler = handler1;
        applicationContext = sonosapplication;
        System.loadLibrary("sonos-jni");
_L1:
        return;
        UnsatisfiedLinkError unsatisfiedlinkerror;
        unsatisfiedlinkerror;
        LibraryUtils.showLinkErrors();
        ApplicationStateManager.getInstance().closeAllActivities();
          goto _L1
    }

    static boolean appOnExternalStorage(Context context)
    {
        boolean flag = false;
        int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.flags;
        if((i & 0x40000) == 0x40000)
            flag = true;
_L2:
        return flag;
        Exception exception;
        exception;
        SLog.e(LOG_TAG, "Error attempting to determine if app is on external storage.  Assuming no.");
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void consolidateSonosFiles(File file, Context context)
        throws IOException
    {
        File afile[] = new File[4];
        afile[0] = FileUtils.getNewPhoneStorageDir(context);
        afile[1] = FileUtils.getNewExternalStorageDir(context);
        afile[2] = FileUtils.getOldExternalStorageDir(context);
        afile[3] = FileUtils.getOldPhoneStorageDir(context);
        boolean flag = dirContainsSonosFiles(file);
        int i = afile.length;
        for(int j = 0; j < i; j++)
        {
            File file1 = afile[j];
            if(file1 == null)
                continue;
            if(!flag && dirContainsSonosFiles(file1))
            {
                copySonosDirs(file1, file);
                flag = true;
            }
            if(!file1.equals(file) && flag)
            {
                FileUtils.deleteDir(new File(file1, "jffs"));
                FileUtils.deleteDir(new File(file1, "serverroot"));
            }
        }

    }

    private static void copySonosDirs(File file, File file1)
        throws IOException
    {
label0:
        {
            if(!file.equals(file1))
            {
                FileUtils.deleteDir(file1);
                if(!file1.mkdirs())
                    break label0;
                FileUtils.copyDir(new File(file, "jffs"), new File(file1, "jffs"));
            }
            return;
        }
        throw new RuntimeException("Unable to create sonos root");
    }

    private void createAnacapaConfIfNecessary(Context context, File file, File file1)
        throws IOException
    {
        if(!file.exists())
        {
            SLog.e("ACRDIRS", "ServerRoot path does not exist.");
            throw new IOException();
        }
        File file2 = new File(file, "conf");
        if(!file2.exists())
        {
            SLog.e("ACRDIRS", "Config dir does not exist.");
            throw new IOException();
        }
        String as[] = new String[2];
        as[0] = "acr.anacapa.conf";
        as[1] = "mime.types";
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s4 = as[j];
            File file6 = new File(file2, s4);
            if(file6.exists())
                continue;
            android.content.res.AssetManager.AssetInputStream assetinputstream1 = (android.content.res.AssetManager.AssetInputStream)context.getAssets().open((new StringBuilder()).append("anacapa/").append(s4).toString());
            FileOutputStream fileoutputstream1 = new FileOutputStream(file6);
            InputStreamReader inputstreamreader1 = new InputStreamReader(assetinputstream1);
            BufferedReader bufferedreader1 = new BufferedReader(inputstreamreader1);
            OutputStreamWriter outputstreamwriter1 = new OutputStreamWriter(fileoutputstream1);
            BufferedWriter bufferedwriter1 = new BufferedWriter(outputstreamwriter1);
            for(String s5 = bufferedreader1.readLine(); s5 != null; s5 = bufferedreader1.readLine())
            {
                if(s5.startsWith("ServerRoot"))
                    s5 = (new StringBuilder()).append("ServerRoot ").append(file.getAbsolutePath()).toString();
                bufferedwriter1.write(s5);
                bufferedwriter1.newLine();
            }

            bufferedreader1.close();
            bufferedwriter1.close();
            assetinputstream1.close();
            fileoutputstream1.close();
        }

        String as1[] = new String[2];
        as1[0] = "shared";
        as1[1] = "sclib";
        int k = as1.length;
        for(int l = 0; l < k; l++)
        {
            String s = as1[l];
            String s1 = (new StringBuilder()).append("anacapa/resources/").append(s).toString();
            File file3 = new File(file1, s);
            String as2[] = context.getAssets().list(s1);
            int i1 = as2.length;
            for(int j1 = 0; j1 < i1; j1++)
            {
                String s2 = as2[j1];
                File file4 = new File(s2);
                File file5 = new File(file3, file4.getName());
                android.content.res.AssetManager.AssetInputStream assetinputstream = (android.content.res.AssetManager.AssetInputStream)context.getAssets().open((new StringBuilder()).append(s1).append("/").append(s2).toString());
                FileOutputStream fileoutputstream = new FileOutputStream(file5);
                InputStreamReader inputstreamreader = new InputStreamReader(assetinputstream);
                BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
                OutputStreamWriter outputstreamwriter = new OutputStreamWriter(fileoutputstream);
                BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
                for(String s3 = bufferedreader.readLine(); s3 != null; s3 = bufferedreader.readLine())
                {
                    bufferedwriter.write(s3);
                    bufferedwriter.newLine();
                }

                bufferedreader.close();
                bufferedwriter.close();
                assetinputstream.close();
                fileoutputstream.close();
            }

        }

    }

    public static boolean dirContainsSonosFiles(File file)
    {
        boolean flag;
        if(file != null)
            flag = (new File(file, "jffs")).exists();
        else
            flag = false;
        return flag;
    }

    private String getDeveloperOptions()
    {
        String s = DbgProp.get("developerOptions", null);
        String s1 = ",MORE_MUSIC_WEB";
        if(android.os.Build.VERSION.SDK_INT == 7)
            s1 = "";
        StringBuilder stringbuilder = (new StringBuilder()).append("WOO_WOO,WOO_ALARM,CROSS_SERVICE_SEARCH,BROWSE_PICKER").append(s1);
        String s2;
        if(s != null)
            s2 = (new StringBuilder()).append(",").append(s).toString();
        else
            s2 = "";
        return stringbuilder.append(s2).toString();
    }

    private SCUserInterfaceParameters getDeviceUIParameters()
    {
        SCUserInterfaceParameters scuserinterfaceparameters;
        com.sonos.sclib.SCUserInterfaceParameters.BrowseSearchMode browsesearchmode;
        scuserinterfaceparameters = new SCUserInterfaceParameters();
        scuserinterfaceparameters.setM_formFactor(com.sonos.sclib.SCUserInterfaceParameters.FormFactorType.SCUI_FF_HANDHELD);
        scuserinterfaceparameters.setM_browseTextStyle(com.sonos.sclib.SCUserInterfaceParameters.BrowseTextStyle.SCUI_BTS_SHORT);
        DisplayMetrics displaymetrics = applicationContext.getResources().getDisplayMetrics();
        com.sonos.sclib.SCUserInterfaceParameters.ScreenDensityType screendensitytype = com.sonos.sclib.SCUserInterfaceParameters.ScreenDensityType.SCUI_DENSITY_LOW;
        Configuration configuration;
        if(displaymetrics.densityDpi == 240 || displaymetrics.densityDpi == 213)
            screendensitytype = com.sonos.sclib.SCUserInterfaceParameters.ScreenDensityType.SCUI_DENSITY_HIGH;
        else
        if(displaymetrics.densityDpi >= 160)
            screendensitytype = com.sonos.sclib.SCUserInterfaceParameters.ScreenDensityType.SCUI_DENSITY_MEDIUM;
        configuration = applicationContext.getResources().getConfiguration();
        switch(0xf & configuration.screenLayout)
        {
        default:
            scuserinterfaceparameters.setM_formFactor(com.sonos.sclib.SCUserInterfaceParameters.FormFactorType.SCUI_FF_HANDHELD);
            break;

        case 3: // '\003'
        case 4: // '\004'
            break MISSING_BLOCK_LABEL_294;
        }
_L1:
        reportDeviceConfiguration(configuration);
        SLog.d(LOG_TAG, (new StringBuilder()).append("Metrics:").append(displaymetrics).toString());
        scuserinterfaceparameters.addScreenResolution(Integer.toString(displaymetrics.widthPixels), Integer.toString(displaymetrics.heightPixels));
        scuserinterfaceparameters.setM_xDpi(displaymetrics.xdpi);
        scuserinterfaceparameters.setM_yDpi(displaymetrics.ydpi);
        scuserinterfaceparameters.setM_scaledScreenDensity(displaymetrics.scaledDensity);
        scuserinterfaceparameters.setM_densityDpi(displaymetrics.densityDpi);
        scuserinterfaceparameters.setM_pixelRatio(displaymetrics.density);
        scuserinterfaceparameters.setM_screenDensity(screendensitytype);
        if(applicationContext.getResources().getBoolean(0x7f070000))
            browsesearchmode = com.sonos.sclib.SCUserInterfaceParameters.BrowseSearchMode.SCUI_HAS_OWN_SEARCH_UI;
        else
            browsesearchmode = com.sonos.sclib.SCUserInterfaceParameters.BrowseSearchMode.SCUI_USES_BROWSE_FOR_SEARCH;
        scuserinterfaceparameters.setM_browseSearchMode(browsesearchmode);
        scuserinterfaceparameters.setM_localSharingSupport(com.sonos.sclib.SCUserInterfaceParameters.SupportsLocalSocialSharing.SCUI_SHARING_SUPPORT);
        if(scuserinterfaceparameters.getM_formFactor() == com.sonos.sclib.SCUserInterfaceParameters.FormFactorType.SCUI_FF_HANDHELD && (double)((float)displaymetrics.heightPixels / displaymetrics.scaledDensity) < 568D)
            scuserinterfaceparameters.setM_phoneType(com.sonos.sclib.SCUserInterfaceParameters.PhoneType.SCUI_PHONE_SHORT);
        return scuserinterfaceparameters;
        scuserinterfaceparameters.setM_formFactor(com.sonos.sclib.SCUserInterfaceParameters.FormFactorType.SCUI_FF_TABLET);
          goto _L1
    }

    private long getInstallTime(Context context)
    {
        long l1 = (new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir)).lastModified();
        long l = l1;
_L2:
        return l;
        Exception exception;
        exception;
        l = 0L;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String getLanguageID(String s)
    {
        String as[][];
        int i;
        int j;
        as = new String[13][];
        String as1[] = new String[2];
        as1[0] = "en";
        as1[1] = "en-US";
        as[0] = as1;
        String as2[] = new String[2];
        as2[0] = "de";
        as2[1] = "de-DE";
        as[1] = as2;
        String as3[] = new String[2];
        as3[0] = "da";
        as3[1] = "da-DK";
        as[2] = as3;
        String as4[] = new String[2];
        as4[0] = "es";
        as4[1] = "es-ES";
        as[3] = as4;
        String as5[] = new String[2];
        as5[0] = "fr";
        as5[1] = "fr-FR";
        as[4] = as5;
        String as6[] = new String[2];
        as6[0] = "it";
        as6[1] = "it-IT";
        as[5] = as6;
        String as7[] = new String[2];
        as7[0] = "ja";
        as7[1] = "ja-JP";
        as[6] = as7;
        String as8[] = new String[2];
        as8[0] = "no";
        as8[1] = "nb-NO";
        as[7] = as8;
        String as9[] = new String[2];
        as9[0] = "nb";
        as9[1] = "nb-NO";
        as[8] = as9;
        String as10[] = new String[2];
        as10[0] = "nl";
        as10[1] = "nl-NL";
        as[9] = as10;
        String as11[] = new String[2];
        as11[0] = "pt";
        as11[1] = "pt-BR";
        as[10] = as11;
        String as12[] = new String[2];
        as12[0] = "sv";
        as12[1] = "sv-SE";
        as[11] = as12;
        String as13[] = new String[2];
        as13[0] = "zh";
        as13[1] = "zh-CN";
        as[12] = as13;
        i = as.length;
        j = 0;
_L3:
        String as14[];
        if(j >= i)
            break MISSING_BLOCK_LABEL_376;
        as14 = as[j];
        if(!s.equals(as14[0])) goto _L2; else goto _L1
_L1:
        String s1 = as14[1];
_L4:
        return s1;
_L2:
        j++;
          goto _L3
        s1 = null;
          goto _L4
    }

    private String getMacAddress()
    {
        SharedPreferences sharedpreferences = LibraryUtils.getSharedPreferences();
        String s = sharedpreferences.getString("wifi_mac_address", null);
        if(s == null)
        {
            WifiManager wifimanager = (WifiManager)applicationContext.getSystemService("wifi");
            if(wifimanager != null)
            {
                boolean flag = false;
                if(!wifimanager.isWifiEnabled())
                {
                    WifiInfo wifiinfo;
                    String s1;
                    android.content.SharedPreferences.Editor editor;
                    try
                    {
                        wifimanager.setWifiEnabled(true);
                    }
                    catch(SecurityException securityexception1) { }
                    flag = true;
                }
                wifiinfo = wifimanager.getConnectionInfo();
                if(wifiinfo != null)
                    s1 = wifiinfo.getMacAddress();
                else
                    s1 = null;
                if(flag)
                    try
                    {
                        wifimanager.setWifiEnabled(false);
                    }
                    catch(SecurityException securityexception) { }
                if(s1 != null && s1.length() != 0)
                {
                    s = s1.replace(":", "");
                    editor = sharedpreferences.edit();
                    editor.putString("wifi_mac_address", s);
                    editor.commit();
                } else
                {
                    s = null;
                }
            }
        }
        return s;
    }

    public static File getStorageRoot(Context context)
    {
        File file;
        if(appOnExternalStorage(context))
            file = FileUtils.getNewExternalStorageDir(context);
        else
            file = FileUtils.getNewPhoneStorageDir(context);
        return file;
    }

    private void initializeCallbacks(SCLibParameters sclibparameters, Handler handler1)
    {
        androidDiagnosticsCb = new AndroidDiagnosticCB();
        deferredDeletionList.add(androidDiagnosticsCb.getWeakRefHelper());
        sclibparameters.setM_pDiagnosticCallback(androidDiagnosticsCb);
        androidDiagnosticConsoleLogCb = new AndroidDiagnosticConsoleLogCB();
        deferredDeletionList.add(androidDiagnosticConsoleLogCb.getWeakRefHelper());
        sclibparameters.setM_pDiagnosticConsoleLogCallback(androidDiagnosticConsoleLogCb);
        androidLogger = new AndroidLoggerCB();
        deferredDeletionList.add(androidLogger.getWeakRefHelper());
        sclibparameters.setM_pLoggerCB(androidLogger);
        assertionFailureCB = new AndroidAssertionFailureCB();
        deferredDeletionList.add(assertionFailureCB.getWeakRefHelper());
        sclibparameters.setM_pAssertionFailureCB(assertionFailureCB);
        itqHandler = new ITQHandler(handler1);
        deferredDeletionList.add(itqHandler.getWeakRefHelper());
        sclibparameters.setM_pCallUIThreadCB(itqHandler);
        setupWifiDelegate = new WifiSetupConnectionManager(applicationContext);
        deferredDeletionList.add(setupWifiDelegate.getWeakRefHelper());
        sclibparameters.setM_pWifiSetupDelegate(setupWifiDelegate);
        platformDateTimeProvider = new PlatformDateTimeProvider();
        deferredDeletionList.add(platformDateTimeProvider.getWeakRefHelper());
        sclibparameters.setM_pPlatformDateTimeProvider(platformDateTimeProvider);
    }

    private void initializeDeviceProperties(SCLibParameters sclibparameters)
    {
        sclibparameters.setM_sOSVersion(android.os.Build.VERSION.RELEASE);
        String s = getLanguageID(Locale.getDefault().getLanguage());
        if(s != null)
            sclibparameters.setM_sLanguageID(s);
        String s1 = Locale.getDefault().getCountry();
        if(s1 != null)
            sclibparameters.setM_sDefaultCountryCode(s1);
        String s2 = android.provider.Settings.Secure.getString(applicationContext.getContentResolver(), "android_id");
        String s3 = getMacAddress();
        String s4;
        String s5;
        if(s3 == null || !sclib.SCLibIsValidMACAddress(s3))
            s4 = "000000000000";
        else
            s4 = s3;
        SLog.i(LOG_TAG, (new StringBuilder()).append(Loc.NOLOC("MAC Address: ")).append(s4).toString());
        if(s3 != null)
            s2 = s3;
        sclibparameters.setM_sHostDeviceID(s2);
        sclibparameters.setM_sHostMACAddress(s4);
        sclibparameters.setM_UIParams(getDeviceUIParameters());
        sclibparameters.setM_bRequiresUnicastAlive(true);
        sclibparameters.setM_bEnableSearchManager(applicationContext.getResources().getBoolean(0x7f070000));
        sclibparameters.setM_pTruncatedStringsCallback(TextViewLogger.getInstance());
        sclibparameters.setM_sDeveloperOptions(getDeveloperOptions());
        sclibparameters.setM_nAnacapaPortSearchAttempts(10);
        sclibparameters.setM_nAnacapaMaxConnections(16);
        if(android.os.Build.VERSION.SDK_INT == 7)
            s5 = "ACR_2_1";
        else
            s5 = "ACR_";
        sclibparameters.setM_sHostModel((new StringBuilder()).append(s5).append(":").append(Build.MANUFACTURER).append(":").append(Build.PRODUCT).append(":").append(Build.MODEL).toString());
    }

    private static File initializeDir(File file, String s)
    {
        File file1 = new File(file, s);
        if(file1.exists() || file1.mkdirs()) goto _L2; else goto _L1
_L1:
        SLog.e(LOG_TAG, (new StringBuilder()).append("Failed to create directory: ").append(s).append(" with path: ").append(file).toString());
_L4:
        return file1;
_L2:
        File file2 = new File(file1, ".nomedia");
        try
        {
            if(!file2.exists() && !file2.createNewFile())
                SLog.e(LOG_TAG, "Failed to create nomedia file");
        }
        catch(IOException ioexception)
        {
            SLog.e(LOG_TAG, "Exception creating nomedia file! ", ioexception);
            ioexception.printStackTrace();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void onLowMemory()
    {
        NativeWeakReferenceHelper.cleanupObjects(0);
    }

    private void reportDeviceConfiguration(Configuration configuration)
    {
        SCIPropertyBag scipropertybag = sclib.createPropertyBag();
        scipropertybag.setIntProp("mask", configuration.screenLayout);
        scipropertybag.setStrProp("cfg", configuration.toString());
        sclib.getAppReportingInstance().reportEventWithProps("ACR", "ACRConfig", scipropertybag);
    }

    public void addMessageListener(MessageListener messagelistener)
    {
        actionFactoryDelegate.addMessageListener(messagelistener);
    }

    public SCIDealerMode getDealerMode()
    {
        return sciDealerMode;
    }

    public Household getHousehold()
    {
        return household;
    }

    public SCILibrary getLibrary()
    {
        return sciLibrary;
    }

    public SCILogoArtworkCache getLogoCache()
    {
        return logoCache;
    }

    public SCINetworkManagement getNetworkManagement()
    {
        return sciNetworkManagement;
    }

    public SCIOpFactory getOpFactory()
    {
        return sciOpFactory;
    }

    public SCISystem getSystem()
    {
        return sciSystem;
    }

    public WifiSetupConnectionManager getWifiSetupDelegate()
    {
        return setupWifiDelegate;
    }

    public SCILibrary initialize()
        throws Exception
    {
        SCILibrary scilibrary;
        try
        {
            if(sciLibrary == null)
            {
                sonosNetworkManager = applicationContext.getNetworkStatusMonitor();
                SCLibParameters sclibparameters = new SCLibParameters();
                deferredDeletionList.add(sclibparameters.getWeakRefHelper());
                initializeFileSystem(sclibparameters);
                initializeCallbacks(sclibparameters, handler);
                initializeDeviceProperties(sclibparameters);
                sciLibrary = sclib.SCLibInit(sclibparameters);
                if(sciLibrary == null)
                    SLog.e(LOG_TAG, "SCLibInit failed, library returned was null");
                NativeWeakReferenceHelper.forgetWeakReference(sciLibrary.getWeakRefHelper());
                itqHandler.setSCILibrary(sciLibrary);
                NetworkManagementDelegateImpl networkmanagementdelegateimpl = new NetworkManagementDelegateImpl(sonosNetworkManager);
                sciLibrary.setNetworkManagementDelegate(networkmanagementdelegateimpl);
                sciNetworkManagement = (SCINetworkManagement)LibraryUtils.cast(sciLibrary, com/sonos/sclib/SCINetworkManagement);
                sciSystem = (SCISystem)LibraryUtils.cast(sciLibrary, com/sonos/sclib/SCISystem);
                sciDealerMode = (SCIDealerMode)LibraryUtils.cast(sciLibrary, com/sonos/sclib/SCIDealerMode);
                household = new Household(sciLibrary.getHousehold());
                sciOpFactory = (SCIOpFactory)LibraryUtils.cast(sciLibrary, com/sonos/sclib/SCIOpFactory);
                logoCache = sciSystem.getArtworkCacheManager().getLogoArtworkCacheWithFormat(com.sonos.sclib.SCIArtworkCacheManager.LogoArtworkFormat.LOGO_FORMAT_SQUARE);
                SCIDebug scidebug = (SCIDebug)LibraryUtils.cast(sciLibrary, com/sonos/sclib/SCIDebug);
                if(scidebug != null)
                    scidebug.setMaxListenerCountThreshold(50);
                stopNetworking();
                Looper.myQueue().addIdleHandler(this);
            }
            actionFactoryDelegate = new ActionFactoryDelegate();
            sciLibrary.setActionFactory(actionFactoryDelegate);
            sonosLocationServices = new SonosLocationServices();
            sciLibrary.registerLocationServices(sonosLocationServices);
            scilibrary = sciLibrary;
        }
        catch(UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            LibraryUtils.showLinkErrors();
            ApplicationStateManager.getInstance().closeAllActivities();
            scilibrary = null;
        }
        return scilibrary;
    }

    protected void initializeFileSystem(SCLibParameters sclibparameters)
        throws IOException
    {
        File file = getStorageRoot(applicationContext);
        consolidateSonosFiles(file, applicationContext);
        jffsRoot = initializeDir(file, "jffs");
        serverRoot = initializeDir(file, "serverroot");
        File file1 = initializeDir(serverRoot, "resources");
        initializeDir(serverRoot, "htdocs");
        initializeDir(file1, "shared");
        initializeDir(file1, "sclib");
        sclibparameters.setM_sJFFSRoot(jffsRoot.getAbsolutePath());
        sclibparameters.setM_sResourcesPath(file1.getAbsolutePath());
        sclibparameters.setM_sDownloadedResourcesPath((new StringBuilder()).append(jffsRoot).append("/Sonos_logos").toString());
        sclibparameters.setM_sAnacapaConfFilePath((new StringBuilder()).append(initializeDir(serverRoot, "conf")).append("/acr.anacapa.conf").toString());
        sclibparameters.setM_bAnacapaUseConfFile(true);
        long l = getInstallTime(applicationContext);
        SharedPreferences sharedpreferences = LibraryUtils.getSharedPreferences();
        long l1 = sharedpreferences.getLong("AnanacapaResourceUpdateTime", 0L);
        String s = sharedpreferences.getString("AnanacapaResourcePath", null);
        String s1 = serverRoot.getAbsolutePath();
        if(l1 == 0L || l != l1 || !s1.equals(s))
        {
            createAnacapaConfIfNecessary(applicationContext, serverRoot, file1);
            sharedpreferences.edit().putLong("AnanacapaResourceUpdateTime", l).putString("AnanacapaResourcePath", s1).commit();
            firstRunAfterUpdate = true;
        }
        SCIStringArray scistringarray = sclibparameters.getDiagnosticFiles();
        scistringarray.append("/proc/net/arp");
        scistringarray.append("/proc/net/netstat");
        scistringarray.append("/proc/net/snmp");
        scistringarray.append("/proc/net/dev");
        scistringarray.append("/proc/net/wireless");
        scistringarray.append("/proc/uptime");
        scistringarray.append((new StringBuilder()).append(initializeDir(serverRoot, "log")).append("/acr.anacapa.trace").toString());
    }

    public boolean isAssociatedToHousehold()
    {
        boolean flag;
        if(household != null && household.getID().length() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isFirstRunAfterUpdate()
    {
        return firstRunAfterUpdate;
    }

    public boolean isInitialized()
    {
        boolean flag;
        if(sciLibrary != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void printStandardLocations()
    {
        SLog.d("FILES", Environment.getDataDirectory().getAbsolutePath());
        SLog.d("FILES", Environment.getDownloadCacheDirectory().getPath());
        SLog.d("FILES", Environment.getExternalStorageDirectory().getPath());
        SLog.d("FILES", Environment.getExternalStorageState());
        SLog.d("FILES", Environment.getRootDirectory().getPath());
        SLog.d("FILES", SonosApplication.getInstance().getPackageCodePath());
        SLog.d("FILES", SonosApplication.getInstance().getApplicationInfo().dataDir);
        SLog.d("FILES", SonosApplication.getInstance().getFilesDir().getPath());
    }

    public boolean queueIdle()
    {
        if(!NativeWeakReferenceHelper.cleanupObjects(10))
        {
            SLog.i(LOG_TAG, "Not all objects were freed, posting deferred cleanup");
            SonosApplication.getInstance().getHandler().post(this);
        }
        return true;
    }

    public void rebindNetworkSockets()
    {
        if(sciNetworkManagement != null)
        {
            SLog.i(LOG_TAG, "Rebind network sockets");
            long l = System.currentTimeMillis();
            sciNetworkManagement.rebindNetworkSockets();
            SLog.d(LOG_TAG, (new StringBuilder()).append("Rebind Network sockets took: ").append(System.currentTimeMillis() - l).append("ms").toString());
        }
    }

    public void refreshNetworking()
    {
        if(sciNetworkManagement != null && !SonosApplication.getInstance().getNetworkSearchStateMonitor().hasHouseholdSearchTimedOut())
        {
            SLog.i(LOG_TAG, "refresh network subscriptions");
            long l = System.currentTimeMillis();
            sciNetworkManagement.refreshNetworking();
            SLog.d(LOG_TAG, (new StringBuilder()).append("refresh network subscriptions took: ").append(System.currentTimeMillis() - l).append("ms").toString());
        }
    }

    public void removeActionFactory(SCIActionFactory sciactionfactory)
    {
        actionFactoryDelegate.removeCurrentActionFactory(sciactionfactory);
    }

    public void resetFirstRun()
    {
        firstRunAfterUpdate = false;
    }

    public void resumeDeviceExpiration()
    {
        if(sciNetworkManagement != null)
        {
            SLog.i(LOG_TAG, "resuming device expiration");
            sciNetworkManagement.resumeDeviceExpiration();
        }
    }

    public void resumeNetworking()
    {
        if(sonosNetworkManager != null && sonosNetworkManager.isWifiConnected())
            startNetworking();
    }

    public void run()
    {
    }

    public void setActionFactory(SCIActionFactory sciactionfactory)
    {
        actionFactoryDelegate.setCurrentActionFactory(sciactionfactory);
    }

    public void startNetworking()
    {
        if(sciNetworkManagement != null)
        {
            String s = SonosApplication.getInstance().getNetworkStatusMonitor().getNetworkSSID();
            if(SonosApplication.getInstance().getNetworkStatusMonitor().isWifiConnected() && !lastNetworkSSID.equals(s))
            {
                SLog.w(LOG_TAG, (new StringBuilder()).append("Network changed from ").append(lastNetworkSSID).append(" to ").append(s).toString());
                lastNetworkSSID = s;
                sciNetworkManagement.networkChanged();
            }
            SLog.i(LOG_TAG, "Resuming networking");
            long l = System.currentTimeMillis();
            SLog.d(LOG_TAG, (new StringBuilder()).append("Resuming Networking took: ").append(System.currentTimeMillis() - l).append("ms").toString());
            sciNetworkManagement.resumeNetworking();
            if(household != null && sciLibrary != null)
                household.setHousehold(sciLibrary.getHousehold());
        }
    }

    public void stopNetworking()
    {
        if(sciNetworkManagement != null)
        {
            SLog.i(LOG_TAG, "Suspending networking");
            long l = System.currentTimeMillis();
            sciNetworkManagement.suspendNetworking();
            SLog.d(LOG_TAG, (new StringBuilder()).append("Suspend Networking took: ").append(System.currentTimeMillis() - l).append("ms").toString());
        }
    }

    public void suspendDeviceExpiration()
    {
        if(sciNetworkManagement != null)
        {
            SLog.i(LOG_TAG, "suspending device expiration");
            sciNetworkManagement.suspendDeviceExpiration();
        }
    }

    public void suspendNetworking()
    {
        stopNetworking();
    }

    public void termSCLib()
    {
        if(sciLibrary != null)
        {
            Looper.myQueue().removeIdleHandler(this);
            androidDiagnosticsCb = null;
            androidDiagnosticConsoleLogCb = null;
            androidLogger = null;
            assertionFailureCB = null;
            sciNetworkManagement = null;
            sciSystem = null;
            sciDealerMode = null;
            household = null;
            sciOpFactory = null;
            logoCache = null;
            itqHandler = null;
            NativeWeakReferenceHelper.cleanupAllRemainingObjectsExceptFor(deferredDeletionList);
            sclib.SCLibTerm(sciLibrary);
            for(Iterator iterator = deferredDeletionList.iterator(); iterator.hasNext(); ((NativeWeakReferenceHelper)iterator.next()).dispose());
            deferredDeletionList.clear();
            sciLibrary = null;
        }
    }

    public static final String JFFS_DIR_NAME = "jffs";
    private static final String LOG_TAG = com/sonos/acr/sclib/SCLibManager.getSimpleName();
    private static final String RES_PATH_PREF = "AnanacapaResourcePath";
    private static final String RES_UPDATE_PREF = "AnanacapaResourceUpdateTime";
    public static final String SERVERROOT_DIR_NAME = "serverroot";
    private static final double SHORT_PHONE_SCREEN_HEIGHT = 568D;
    public static final String SONOS_JNI_LIB_NAME = "sonos-jni";
    public static final String WIFI_MAC_ADDRESS_PREF = "wifi_mac_address";
    private ActionFactoryDelegate actionFactoryDelegate;
    private AndroidDiagnosticConsoleLogCB androidDiagnosticConsoleLogCb;
    private AndroidDiagnosticCB androidDiagnosticsCb;
    private AndroidLoggerCB androidLogger;
    private SonosApplication applicationContext;
    private AndroidAssertionFailureCB assertionFailureCB;
    private ArrayList deferredDeletionList;
    private boolean firstRunAfterUpdate;
    private Handler handler;
    private Household household;
    private ITQHandler itqHandler;
    private File jffsRoot;
    private String lastNetworkSSID;
    private SCILogoArtworkCache logoCache;
    private PlatformDateTimeProvider platformDateTimeProvider;
    private SCIDealerMode sciDealerMode;
    private SCILibrary sciLibrary;
    private SCINetworkManagement sciNetworkManagement;
    private SCIOpFactory sciOpFactory;
    private SCISystem sciSystem;
    private File serverRoot;
    private WifiSetupConnectionManager setupWifiDelegate;
    private SonosLocationServices sonosLocationServices;
    private SonosNetworkManager sonosNetworkManager;

}
