// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import com.splunk.mint.network.MonitorRegistry;
import com.splunk.mint.network.NetLogManager;
import com.splunk.mint.network.http.MonitorableURLStreamHandlerFactory;
import com.splunk.mint.network.socket.MonitoringSocketFactory;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import javax.net.ssl.SSLSocket;
import org.json.JSONObject;

// Referenced classes of package com.splunk.mint:
//            Properties, ExtraData, ExcludedUrls, CrashInfo, 
//            ActionEvent, DataSaver, Utils, DataFlusher, 
//            Logger, LowPriorityThreadFactory, ExceptionHandler, BreadcrumbsLimited, 
//            ActionError, EnumActionType, EnumExceptionType, ActionView, 
//            ActionTransactionStop, ActionTransactionStart, MintCallback, MintLogLevel, 
//            NetSender

public final class Mint
{

    public Mint()
    {
    }

    public static void addExtraData(String s, String s1)
    {
        if(Properties.extraData == null)
            Properties.extraData = new ExtraData();
        if(s != null)
        {
            if(s1 == null)
                s1 = "null";
            Properties.extraData.addExtraData(s, s1);
        }
    }

    public static void addExtraDataMap(HashMap hashmap)
    {
        if(Properties.extraData == null)
            Properties.extraData = new ExtraData();
        if(hashmap != null)
            Properties.extraData.addExtraDataMap(hashmap);
    }

    public static void addURLToBlackList(String s)
    {
        if(s != null)
            Properties.excludedUrls.addValue(s);
    }

    public static void clearExtraData()
    {
        if(Properties.extraData == null)
            Properties.extraData = new ExtraData();
        Properties.extraData.clearData();
    }

    public static void clearTotalCrashesNum()
    {
        if(Properties.isPluginInitialized())
            (new CrashInfo()).clearCrashCounter();
    }

    public static void closeSession(Context context)
    {
        if(Properties.isPluginInitialized() && isSessionActive)
        {
            isSessionActive = false;
            ActionEvent.createGnip().save(new DataSaver());
            Utils.clearLastPingSentTime(context);
        }
    }

    public static void disableNetworkMonitoring()
    {
        networkMonitoringEnabled = false;
    }

    public static void enableDebug()
    {
        DEBUG = true;
    }

    public static void enableLogging(boolean flag)
    {
        Properties.SEND_LOG = flag;
    }

    public static void flush()
    {
        if(Properties.isPluginInitialized())
            (new DataFlusher()).send();
    }

    public static JSONObject getDevSettings()
    {
        return Properties.RemoteSettingsProps.devSettings;
    }

    public static HashMap getExtraData()
    {
        if(Properties.extraData == null)
            Properties.extraData = new ExtraData();
        return Properties.extraData.getExtraData();
    }

    public static String getLastCrashID()
    {
        return CrashInfo.getLastCrashID();
    }

    public static final String getMintUUID()
    {
        String s;
        if(Properties.UID != null || Properties.UID.length() > 0)
            s = Properties.UID;
        else
            s = "NA";
        return s;
    }

    public static final String getSessionId()
    {
        String s;
        if(ActionEvent.savedSessionID != null || ActionEvent.savedSessionID.length() > 0)
            s = ActionEvent.savedSessionID;
        else
            s = "NA";
        return s;
    }

    public static int getTotalCrashesNum()
    {
        return CrashInfo.getTotalCrashesNum();
    }

    public static void initAndStartSession(Context context, String s)
    {
        initAndStartSession(context, s, null);
    }

    private static void initAndStartSession(final Context context, String s, String s1)
    {
        if(context == null)
        {
            Logger.logWarning("Context is null!");
        } else
        {
            if(s == null || s.length() < 8 || s.length() > 14)
                throw new IllegalArgumentException("Your Mint API Key is invalid!");
            Properties.API_KEY = s;
            Properties.TIMESTAMP = System.currentTimeMillis();
            Properties.initialize(context, s1, s);
            (new LowPriorityThreadFactory()).newThread(new Runnable() {

                public void run()
                {
                    Mint.installExceptionHandler();
                    if(!Mint.netInitializedSuccessfully)
                        Mint.initializeNetworkMonitoring();
                    Mint.startSession(context);
                    Mint.flush();
                }

                final Context val$context;

            
            {
                context = context1;
                super();
            }
            }
).start();
        }
    }

    /**
     * @deprecated Method initializeNetworkMonitoring is deprecated
     */

    private static void initializeNetworkMonitoring()
    {
        com/splunk/mint/Mint;
        JVM INSTR monitorenter ;
        if(!networkMonitoringEnabled || triedToInitNet || !NetLogManager.deviceSupporsNetworkMonitoring() || !Properties.RemoteSettingsProps.netMonitoringEnabled.booleanValue()) goto _L2; else goto _L1
_L1:
        Logger.logInfo("Initializing Network Monitoring");
        triedToInitNet = true;
        URL.setURLStreamHandlerFactory(new MonitorableURLStreamHandlerFactory(registry));
_L3:
        MonitoringSocketFactory monitoringsocketfactory = new MonitoringSocketFactory(registry);
        Socket.setSocketImplFactory(monitoringsocketfactory);
        SSLSocket.setSocketImplFactory(monitoringsocketfactory);
        netInitializedSuccessfully = true;
_L4:
        if(netInitializedSuccessfully)
            Logger.logInfo("Network monitoring was initialized successfully!");
_L2:
        com/splunk/mint/Mint;
        JVM INSTR monitorexit ;
        return;
        Throwable throwable;
        throwable;
        netInitializedSuccessfully = false;
          goto _L3
        Exception exception;
        exception;
        throw exception;
        Throwable throwable1;
        throwable1;
        netInitializedSuccessfully = false;
          goto _L4
    }

    private static void installExceptionHandler()
    {
        Logger.logInfo("Registering the exception handler");
        Thread.UncaughtExceptionHandler uncaughtexceptionhandler = Thread.getDefaultUncaughtExceptionHandler();
        if(!(uncaughtexceptionhandler instanceof ExceptionHandler))
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(uncaughtexceptionhandler));
    }

    public static void leaveBreadcrumb(String s)
    {
        if(Properties.isPluginInitialized() && s != null)
            Properties.breadcrumbs.addToList(s);
    }

    public static void logEvent(String s)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionEvent.createEvent(s).save(new DataSaver());
    }

    public static void logEvent(String s, MintLogLevel mintloglevel)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionEvent.createEvent(s, mintloglevel, null).save(new DataSaver());
    }

    public static void logEvent(String s, MintLogLevel mintloglevel, String s1, String s2)
    {
        if(Properties.isPluginInitialized())
        {
            HashMap hashmap = new HashMap(1);
            hashmap.put(s1, s2);
            logEvent(s, mintloglevel, hashmap);
        }
    }

    public static void logEvent(String s, MintLogLevel mintloglevel, HashMap hashmap)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionEvent.createEvent(s, mintloglevel, hashmap).save(new DataSaver());
    }

    public static void logException(Exception exception)
    {
        logExceptionMap(new HashMap(0), exception);
    }

    public static void logExceptionMap(HashMap hashmap, Exception exception)
    {
        if(Properties.isPluginInitialized())
        {
            StringWriter stringwriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringwriter));
            (new ActionError(EnumActionType.error, stringwriter.toString(), EnumExceptionType.HANDLED, hashmap)).save(new DataSaver());
        }
    }

    public static void logExceptionMessage(String s, String s1, Exception exception)
    {
        HashMap hashmap = new HashMap(1);
        if(s != null && s1 != null)
            hashmap.put(s, s1);
        logExceptionMap(hashmap, exception);
    }

    public static void logView(String s)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionView.logView(s).save(new DataSaver());
    }

    public static void removeExtraData(String s)
    {
        if(Properties.extraData == null)
            Properties.extraData = new ExtraData();
        if(s != null)
            Properties.extraData.removeKey(s);
    }

    public static void setFlushOnlyOverWiFi(boolean flag)
    {
        Properties.flushOnlyOverWiFi = flag;
    }

    public static void setLogging(int i)
    {
        if(i > 0)
        {
            Properties.SEND_LOG = true;
            Properties.LOG_LINES = i;
        }
    }

    public static void setLogging(int i, String s)
    {
        if(s != null && i > 0)
        {
            Properties.SEND_LOG = true;
            Properties.LOG_LINES = i;
            Properties.LOG_FILTER = s;
        }
    }

    public static void setLogging(String s)
    {
        if(s != null)
        {
            Properties.SEND_LOG = true;
            Properties.LOG_FILTER = s;
        }
    }

    public static final void setMintCallback(MintCallback mintcallback)
    {
        mintCallback = mintcallback;
    }

    public static void setUserIdentifier(String s)
    {
        Properties.userIdentifier = s;
    }

    public static void startSession(final Context context)
    {
        if(context == null)
        {
            Logger.logWarning("Context is null!");
        } else
        {
            if(!isSessionActive)
            {
                isSessionActive = true;
                Properties.initialize(context, null, null);
            }
            (new LowPriorityThreadFactory()).newThread(new Runnable() {

                public void run()
                {
                    if(Utils.shouldSendPing(context))
                        ActionEvent.createPing().send(context, new NetSender(), true);
                }

                final Context val$context;

            
            {
                context = context1;
                super();
            }
            }
).start();
        }
    }

    public static void transactionCancel(String s, String s1)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionTransactionStop.createTransactionCancel(s, s1, null).save(new DataSaver());
    }

    public static void transactionCancel(String s, String s1, String s2, String s3)
    {
        if(Properties.isPluginInitialized() && s != null)
        {
            HashMap hashmap = new HashMap(1);
            hashmap.put(s2, s3);
            transactionCancel(s2, s1, hashmap);
        }
    }

    public static void transactionCancel(String s, String s1, HashMap hashmap)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionTransactionStop.createTransactionCancel(s, s1, hashmap).save(new DataSaver());
    }

    public static void transactionStart(String s)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionTransactionStart.createTransactionStart(s, null).save(new DataSaver());
    }

    public static void transactionStart(String s, String s1, String s2)
    {
        if(Properties.isPluginInitialized())
        {
            HashMap hashmap = new HashMap(1);
            hashmap.put(s1, s2);
            transactionStart(s1, hashmap);
        }
    }

    public static void transactionStart(String s, HashMap hashmap)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionTransactionStart.createTransactionStart(s, hashmap).save(new DataSaver());
    }

    public static void transactionStop(String s)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionTransactionStop.createTransactionStop(s, null).save(new DataSaver());
    }

    public static void transactionStop(String s, String s1, String s2)
    {
        if(Properties.isPluginInitialized() && s != null)
        {
            HashMap hashmap = new HashMap(1);
            hashmap.put(s1, s2);
            transactionStop(s1, hashmap);
        }
    }

    public static void transactionStop(String s, HashMap hashmap)
    {
        if(Properties.isPluginInitialized() && s != null)
            ActionTransactionStop.createTransactionStop(s, hashmap).save(new DataSaver());
    }

    public static void xamarinException(Exception exception, boolean flag, HashMap hashmap)
    {
        StringWriter stringwriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringwriter));
        EnumExceptionType enumexceptiontype = EnumExceptionType.UNHANDLED;
        if(flag)
            enumexceptiontype = EnumExceptionType.HANDLED;
        (new ActionError(EnumActionType.error, stringwriter.toString().replaceFirst("\n", "\n\t"), enumexceptiontype, hashmap)).save(new DataSaver());
    }

    public MonitorRegistry getRegistry()
    {
        if(registry == null)
            registry = new MonitorRegistry();
        return registry;
    }

    static boolean DEBUG = false;
    public static final String XSplunkMintSessionIdHeader = "X-Splunk-Mint-Session-id";
    public static final String XSplunkMintUuidHeader = "X-Splunk-Mint-uuid";
    private static boolean isSessionActive = false;
    static MintCallback mintCallback = null;
    static boolean netInitializedSuccessfully = false;
    private static boolean networkMonitoringEnabled = true;
    private static MonitorRegistry registry = new MonitorRegistry();
    static boolean triedToInitNet = false;

    static 
    {
        DEBUG = false;
    }


}
