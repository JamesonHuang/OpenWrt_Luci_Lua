// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network;

import com.splunk.mint.*;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;

// Referenced classes of package com.splunk.mint.network:
//            MonitorRegistry, Metric, Counter

public class NetLogManager
{
    class StartConnectionInfo
    {

        public String protocol;
        public Long startTime;
        public int statusCode;
        final NetLogManager this$0;
        public String url;

        StartConnectionInfo()
        {
            this$0 = NetLogManager.this;
            super();
        }
    }


    public NetLogManager()
    {
    }

    private boolean checkIfURLisExcluded(String s)
    {
        boolean flag = true;
        if(s != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
label0:
        {
            if(Properties.excludedUrls == null)
                break label0;
            String s1 = cleanUrl(s);
            Iterator iterator = Properties.excludedUrls.iterator();
            do
                if(!iterator.hasNext())
                    break label0;
            while(!s1.contains((String)iterator.next()));
            continue; /* Loop/switch isn't completed */
        }
        flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private String cleanUrl(String s)
    {
        if(s != null)
            s = s.toLowerCase().replaceAll("https://", "").replaceAll("http://", "").replaceAll("www.", "");
        return s;
    }

    public static boolean deviceSupporsNetworkMonitoring()
    {
        boolean flag = false;
        Class class1 = Class.forName("java.net.PlainSocketImpl");
        if(class1 == null)
            break MISSING_BLOCK_LABEL_105;
        Constructor constructor = class1.getDeclaredConstructor(new Class[0]);
        if(constructor == null)
            break MISSING_BLOCK_LABEL_105;
        constructor.newInstance(new Object[0]);
        Logger.logInfo("Device supports Network Monitoring");
        flag = true;
        break MISSING_BLOCK_LABEL_105;
        RuntimeException runtimeexception;
        runtimeexception;
        Logger.logInfo((new StringBuilder()).append("deviceSupporsNetworkMonitoring: ").append(runtimeexception.getMessage()).toString());
        break MISSING_BLOCK_LABEL_105;
        Exception exception;
        exception;
        Logger.logInfo((new StringBuilder()).append("deviceSupporsNetworkMonitoring: ").append(exception.getMessage()).toString());
        return flag;
    }

    /**
     * @deprecated Method getInstance is deprecated
     */

    public static NetLogManager getInstance()
    {
        com/splunk/mint/network/NetLogManager;
        JVM INSTR monitorenter ;
        NetLogManager netlogmanager;
        if(mNetLogManager == null)
            mNetLogManager = new NetLogManager();
        netlogmanager = mNetLogManager;
        com/splunk/mint/network/NetLogManager;
        JVM INSTR monitorexit ;
        return netlogmanager;
        Exception exception;
        exception;
        throw exception;
    }

    public static final int getStatusCodeFromURLConnection(URLConnection urlconnection)
    {
        int i = 0;
        if(urlconnection == null) goto _L2; else goto _L1
_L1:
        if(!(urlconnection instanceof HttpURLConnection)) goto _L4; else goto _L3
_L3:
        int k = ((HttpURLConnection)urlconnection).getResponseCode();
        i = k;
_L2:
        return i;
_L4:
        if(!(urlconnection instanceof HttpsURLConnection))
            continue; /* Loop/switch isn't completed */
        int j;
        try
        {
            j = ((HttpsURLConnection)urlconnection).getResponseCode();
        }
        catch(Exception exception)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = j;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        if(true) goto _L2; else goto _L5
_L5:
    }

    /**
     * @deprecated Method removeOldEntries is deprecated
     */

    private void removeOldEntries()
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = connectionsMap.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            long l = ((StartConnectionInfo)((java.util.Map.Entry)iterator.next()).getValue()).startTime.longValue();
            if(System.currentTimeMillis() - l > 60000L)
                iterator.remove();
        } while(true);
        break MISSING_BLOCK_LABEL_73;
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method cancelNetworkCall is deprecated
     */

    public void cancelNetworkCall(MonitorRegistry monitorregistry, String s, long l, String s1, String s2)
    {
        this;
        JVM INSTR monitorenter ;
        if(s == null)
            break MISSING_BLOCK_LABEL_82;
        String s3 = cleanUrl(s);
        if(connectionsMap != null && connectionsMap.containsKey(s3))
        {
            StartConnectionInfo startconnectioninfo = (StartConnectionInfo)connectionsMap.get(s3);
            if(startconnectioninfo != null)
            {
                connectionsMap.remove(s3);
                ActionNetwork.logNetwork(startconnectioninfo.url, startconnectioninfo.startTime.longValue(), l, s1, 0, 0L, 0L, s2, null);
            }
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method endNetworkCall is deprecated
     */

    public void endNetworkCall(MonitorRegistry monitorregistry, String s, long l, int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(s == null)
            break MISSING_BLOCK_LABEL_201;
        String s1 = cleanUrl(s);
        if(connectionsMap != null && connectionsMap.containsKey(s1))
        {
            StartConnectionInfo startconnectioninfo = (StartConnectionInfo)connectionsMap.get(s1);
            if(startconnectioninfo != null)
            {
                connectionsMap.remove(s1);
                Long long1 = Long.valueOf(0L);
                long l1 = 0L;
                Iterator iterator = monitorregistry.getMetricsForName(s1).iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    Metric metric = (Metric)iterator.next();
                    if(metric instanceof Counter)
                        if(((Counter)metric).getName().endsWith("-bytes-out"))
                            long1 = (Long)metric.getValue();
                        else
                        if(((Counter)metric).getName().endsWith("-bytes-in"))
                            l1 = ((Long)metric.getValue()).longValue();
                } while(true);
                ActionNetwork.logNetwork(startconnectioninfo.url, startconnectioninfo.startTime.longValue(), l, startconnectioninfo.protocol, i, long1.longValue(), l1, null, null);
            }
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method logNetworkRequest is deprecated
     */

    public void logNetworkRequest(String s, String s1, long l, long l1, int i, 
            long l2, long l3, String s2, HashMap hashmap)
    {
        this;
        JVM INSTR monitorenter ;
        if(!checkIfURLisExcluded(s))
            ActionNetwork.logNetwork(s, l, l1, s1, i, l2, l3, s2, hashmap);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method startNetworkCall is deprecated
     */

    public void startNetworkCall(String s, String s1, long l, String s2)
    {
        this;
        JVM INSTR monitorenter ;
        if(s == null)
            break MISSING_BLOCK_LABEL_68;
        String s3 = cleanUrl(s);
        if(!checkIfURLisExcluded(s1))
        {
            StartConnectionInfo startconnectioninfo = new StartConnectionInfo();
            startconnectioninfo.startTime = Long.valueOf(l);
            startconnectioninfo.url = s1;
            startconnectioninfo.protocol = s2;
            connectionsMap.put(s3, startconnectioninfo);
            removeOldEntries();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final long CONNECTION_TIMEOUT = 60000L;
    private static volatile HashMap connectionsMap = new HashMap();
    private static NetLogManager mNetLogManager = null;

}
