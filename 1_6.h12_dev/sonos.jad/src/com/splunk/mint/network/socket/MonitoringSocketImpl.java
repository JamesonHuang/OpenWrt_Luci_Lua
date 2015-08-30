// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.socket;

import com.splunk.mint.Logger;
import com.splunk.mint.Properties;
import com.splunk.mint.network.*;
import com.splunk.mint.network.io.*;
import com.splunk.mint.network.util.Delegator;
import com.splunk.mint.network.util.ReflectionUtil;
import java.io.*;
import java.net.*;
import java.util.*;

public class MonitoringSocketImpl extends SocketImpl
{

    public MonitoringSocketImpl(MonitorRegistry monitorregistry)
    {
        mInputStreamMonitor = null;
        mOutputStreamMonitor = null;
        mInputStreamMonitorKitKat = null;
        protocol = "NA";
        readingDone = false;
        method = ReflectionUtil.extractCallingMethod(SYSTEM_PACKAGES);
        http = ReflectionUtil.callingClassAnyOf(HTTP_CLASSES);
        registry = monitorregistry;
    }

    private void createActionEventFromCollectedStats(ArrayList arraylist, HashMap hashmap, HashMap hashmap1, long l, String s, String s1)
    {
        String s2 = "";
        if(hashmap == null)
            break MISSING_BLOCK_LABEL_70;
        int i;
        Long long1;
        long l1;
        Iterator iterator;
        Exception exception;
        long l2;
        Metric metric;
        Exception exception1;
        int j;
        Exception exception3;
        String s3;
        try
        {
            s2 = (String)((List)hashmap.get("Host")).get(0);
        }
        catch(Exception exception2) { }
        s3 = (new StringBuilder()).append(s2).append((String)((List)hashmap.get("splk-host2")).get(0)).toString();
        s2 = s3;
_L6:
        if(s != null)
            s2 = s1;
        i = 0;
        if(hashmap1 == null)
            break MISSING_BLOCK_LABEL_116;
        j = Integer.valueOf((String)((List)hashmap1.get("splk-statuscode")).get(0)).intValue();
        i = j;
_L4:
        long1 = Long.valueOf(0L);
        l1 = 0L;
        iterator = arraylist.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            metric = (Metric)iterator.next();
            if(metric instanceof Counter)
                if(((Counter)metric).getName().endsWith("-bytes-out"))
                    long1 = (Long)metric.getValue();
                else
                if(((Counter)metric).getName().endsWith("-bytes-in"))
                    l1 = ((Long)metric.getValue()).longValue();
        } while(true);
        l2 = Long.valueOf((String)((List)hashmap1.get("Content-Length")).get(0)).longValue();
        l1 = l2;
_L2:
        NetLogManager.getInstance().logNetworkRequest(s2, protocol, startTime, l, i, long1.longValue(), l1, s, null);
        return;
        exception;
        Logger.logInfo("Could not read the Content-Length HTTP header value");
        if(true) goto _L2; else goto _L1
_L1:
        exception1;
        if(true) goto _L4; else goto _L3
_L3:
        exception3;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void setProtocolFromPort(int i)
    {
        if(i != 80) goto _L2; else goto _L1
_L1:
        protocol = "HTTP";
_L4:
        return;
_L2:
        if(i == 443)
            protocol = "HTTPS";
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void accept(SocketImpl socketimpl)
        throws IOException
    {
        Delegator delegator1 = delegator;
        Object aobj[] = new Object[1];
        aobj[0] = socketimpl;
        delegator1.invoke(aobj);
_L1:
        return;
        Exception exception;
        exception;
        createActionEventFromCollectedStats(registry.getMetricsForName(name), null, null, System.currentTimeMillis(), exception.getMessage(), name);
          goto _L1
    }

    protected int available()
        throws IOException
    {
        int i = 0;
        int j = ((Integer)delegator.invoke(new Object[0])).intValue();
        i = j;
_L2:
        return i;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void bind(InetAddress inetaddress, int i)
        throws IOException
    {
        name = inetaddress.getHostName();
        try
        {
            Delegator delegator1 = delegator;
            Object aobj[] = new Object[2];
            aobj[0] = inetaddress;
            aobj[1] = Integer.valueOf(i);
            delegator1.invoke(aobj);
        }
        catch(Exception exception)
        {
            createActionEventFromCollectedStats(registry.getMetricsForName(name), null, null, System.currentTimeMillis(), exception.getMessage(), name);
        }
        startTime = System.currentTimeMillis();
    }

    protected void close()
        throws IOException
    {
        delegator.invoke(new Object[0]);
_L1:
        return;
        Exception exception;
        exception;
        Logger.logError((new StringBuilder()).append("Error closing socket impl: ").append(exception.getMessage()).toString());
          goto _L1
    }

    protected void connect(String s, int i)
        throws IOException
    {
        name = s;
        try
        {
            Delegator delegator1 = delegator;
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(i);
            delegator1.invoke(aobj);
        }
        catch(Exception exception)
        {
            createActionEventFromCollectedStats(registry.getMetricsForName(name), null, null, System.currentTimeMillis(), exception.getMessage(), s);
        }
        startTime = System.currentTimeMillis();
        setProtocolFromPort(i);
    }

    protected void connect(InetAddress inetaddress, int i)
        throws IOException
    {
        name = inetaddress.getHostName();
        try
        {
            Delegator delegator1 = delegator;
            Class aclass[] = new Class[2];
            aclass[0] = java/net/InetAddress;
            aclass[1] = Integer.TYPE;
            com.splunk.mint.network.util.Delegator.DelegatorMethodFinder delegatormethodfinder = delegator1.delegateTo("connect", aclass);
            Object aobj[] = new Object[2];
            aobj[0] = inetaddress;
            aobj[1] = Integer.valueOf(i);
            delegatormethodfinder.invoke(aobj);
        }
        catch(Exception exception)
        {
            createActionEventFromCollectedStats(registry.getMetricsForName(name), null, null, System.currentTimeMillis(), exception.getMessage(), name);
        }
        startTime = System.currentTimeMillis();
        setProtocolFromPort(i);
    }

    protected void connect(SocketAddress socketaddress, int i)
        throws IOException
    {
        if(socketaddress instanceof InetSocketAddress)
        {
            InetSocketAddress inetsocketaddress = (InetSocketAddress)socketaddress;
            name = inetsocketaddress.getHostName();
            setProtocolFromPort(inetsocketaddress.getPort());
        } else
        {
            name = socketaddress.toString();
        }
        try
        {
            Delegator delegator1 = delegator;
            Object aobj[] = new Object[2];
            aobj[0] = socketaddress;
            aobj[1] = Integer.valueOf(i);
            delegator1.invoke(aobj);
        }
        catch(Exception exception)
        {
            createActionEventFromCollectedStats(registry.getMetricsForName(name), null, null, System.currentTimeMillis(), exception.getMessage(), name);
        }
        startTime = System.currentTimeMillis();
    }

    protected void create(boolean flag)
        throws IOException
    {
        Delegator delegator1 = delegator;
        Object aobj[] = new Object[1];
        aobj[0] = Boolean.valueOf(flag);
        delegator1.invoke(aobj);
_L1:
        return;
        Exception exception;
        exception;
        createActionEventFromCollectedStats(registry.getMetricsForName(name), null, null, System.currentTimeMillis(), exception.getMessage(), name);
          goto _L1
    }

    protected FileDescriptor getFileDescriptor()
    {
        FileDescriptor filedescriptor;
        try
        {
            filedescriptor = (FileDescriptor)delegator.invoke(new Object[0]);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            filedescriptor = null;
        }
        return filedescriptor;
    }

    protected InetAddress getInetAddress()
    {
        InetAddress inetaddress;
        try
        {
            inetaddress = (InetAddress)delegator.invoke(new Object[0]);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            inetaddress = null;
        }
        return inetaddress;
    }

    protected InputStream getInputStream()
        throws IOException
    {
        InputStream inputstream = null;
        Object obj;
        try
        {
            inputstream = (InputStream)delegator.invoke(new Object[0]);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(inputstream != null)
        {
            if(Properties.isKitKat)
            {
                if(mInputStreamMonitorKitKat == null)
                    mInputStreamMonitorKitKat = new InputStreamMonitorKitKat(name, registry, inputstream, this);
                obj = mInputStreamMonitorKitKat;
            } else
            {
                if(mInputStreamMonitor == null)
                    mInputStreamMonitor = new InputStreamMonitor(name, registry, inputstream, this);
                obj = mInputStreamMonitor;
            }
        } else
        {
            obj = null;
        }
        return ((InputStream) (obj));
    }

    protected int getLocalPort()
    {
        int j = ((Integer)delegator.invoke(new Object[0])).intValue();
        int i = j;
_L2:
        return i;
        Exception exception;
        exception;
        exception.printStackTrace();
        i = -1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getMethod()
    {
        return method;
    }

    public Object getOption(int i)
        throws SocketException
    {
        Object obj1;
        Delegator delegator1 = delegator;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        obj1 = delegator1.invoke(aobj);
        Object obj = obj1;
_L2:
        return obj;
        Exception exception;
        exception;
        exception.printStackTrace();
        obj = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected OutputStream getOutputStream()
        throws IOException
    {
        OutputStream outputstream = null;
        OutputStreamMonitor outputstreammonitor;
        try
        {
            outputstream = (OutputStream)delegator.invoke(new Object[0]);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(outputstream != null)
        {
            if(mOutputStreamMonitor == null)
                mOutputStreamMonitor = new OutputStreamMonitor(name, registry, outputstream);
            outputstreammonitor = mOutputStreamMonitor;
        } else
        {
            outputstreammonitor = null;
        }
        return outputstreammonitor;
    }

    protected int getPort()
    {
        int j = ((Integer)delegator.invoke(new Object[0])).intValue();
        int i = j;
_L2:
        return i;
        Exception exception;
        exception;
        exception.printStackTrace();
        i = -1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isHttp()
    {
        return http;
    }

    protected void listen(int i)
        throws IOException
    {
        Delegator delegator1 = delegator;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        delegator1.invoke(aobj);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    public void readingDone()
    {
        if(readingDone) goto _L2; else goto _L1
_L1:
        readingDone = true;
        if(!Properties.isKitKat) goto _L4; else goto _L3
_L3:
        if(mOutputStreamMonitor != null && mInputStreamMonitorKitKat != null)
            createActionEventFromCollectedStats(registry.getMetricsForName(name), mOutputStreamMonitor.getHeaders(), mInputStreamMonitorKitKat.getHeaders(), System.currentTimeMillis(), null, null);
_L2:
        return;
_L4:
        if(mOutputStreamMonitor != null && mInputStreamMonitor != null)
            createActionEventFromCollectedStats(registry.getMetricsForName(name), mOutputStreamMonitor.getHeaders(), mInputStreamMonitor.getHeaders(), System.currentTimeMillis(), null, null);
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void sendUrgentData(int i)
        throws IOException
    {
        Delegator delegator1 = delegator;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        delegator1.invoke(aobj);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    public void setOption(int i, Object obj)
        throws SocketException
    {
        Delegator delegator1 = delegator;
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = obj;
        delegator1.invoke(aobj);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    protected void setPerformancePreferences(int i, int j, int k)
    {
        Delegator delegator1 = delegator;
        Object aobj[] = new Object[3];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        aobj[2] = Integer.valueOf(k);
        delegator1.invoke(aobj);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    protected void shutdownInput()
        throws IOException
    {
        if(Properties.isKitKat)
        {
            if(mInputStreamMonitorKitKat != null)
                mInputStreamMonitorKitKat.close();
        } else
        if(mInputStreamMonitor != null)
            mInputStreamMonitor.close();
        delegator.invoke(new Object[0]);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    protected void shutdownOutput()
        throws IOException
    {
        if(mOutputStreamMonitor != null)
            mOutputStreamMonitor.close();
        delegator.invoke(new Object[0]);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    protected boolean supportsUrgentData()
    {
        boolean flag = false;
        boolean flag1 = ((Boolean)delegator.invoke(new Object[0])).booleanValue();
        flag = flag1;
_L2:
        return flag;
        Exception exception;
        exception;
        exception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final String ENCAPSULATED_SOCKET_IMPL = "java.net.PlainSocketImpl";
    private static final String HTTP_CLASSES[];
    private static final String SYSTEM_PACKAGES[];
    private final Delegator delegator = new Delegator(this, java/net/SocketImpl, "java.net.PlainSocketImpl");
    private final boolean http;
    private InputStreamMonitor mInputStreamMonitor;
    private InputStreamMonitorKitKat mInputStreamMonitorKitKat;
    private OutputStreamMonitor mOutputStreamMonitor;
    private final String method;
    private String name;
    private String protocol;
    private boolean readingDone;
    private final MonitorRegistry registry;
    private long startTime;

    static 
    {
        String as[] = new String[5];
        as[0] = "android";
        as[1] = "java";
        as[2] = "org.apache";
        as[3] = "splunk";
        as[4] = "libcore";
        SYSTEM_PACKAGES = as;
        String as1[] = new String[4];
        as1[0] = "HttpClient";
        as1[1] = "URLConnection";
        as1[2] = "HttpsURLConnectionImpl";
        as1[3] = "HttpURLConnectionImpl";
        HTTP_CLASSES = as1;
    }
}
