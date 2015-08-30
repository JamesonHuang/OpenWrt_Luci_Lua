// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.http;

import com.splunk.mint.Properties;
import com.splunk.mint.network.*;
import com.splunk.mint.network.io.*;
import java.io.*;
import java.net.*;
import java.security.Permission;
import java.security.cert.Certificate;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class MonitorableHttpsURLConnection extends HttpsURLConnection
{

    public MonitorableHttpsURLConnection(MonitorRegistry monitorregistry, URLConnection urlconnection)
    {
        super(urlconnection.getURL());
        mInputStreamMonitor = null;
        mOutputStreamMonitor = null;
        mInputStreamMonitorKitKat = null;
        registry = monitorregistry;
        original = urlconnection;
        connectionName = url.toString();
        timer = new Timer(connectionName);
        NetLogManager.getInstance().startNetworkCall(urlconnection.getURL().toExternalForm(), urlconnection.getURL().toExternalForm(), System.currentTimeMillis(), "HTTPS");
    }

    public void addRequestProperty(String s, String s1)
    {
        original.addRequestProperty(s, s1);
    }

    public void connect()
        throws IOException
    {
        try
        {
            timer.start();
            original.connect();
            NetLogManager.getInstance().startNetworkCall(original.getURL().toExternalForm(), original.getURL().toExternalForm(), System.currentTimeMillis(), "HTTPS");
            return;
        }
        catch(IOException ioexception)
        {
            NetLogManager.getInstance().cancelNetworkCall(registry, url.toExternalForm(), System.currentTimeMillis(), "HTTPS", ioexception.getMessage());
            throw ioexception;
        }
    }

    public void disconnect()
    {
        int i = NetLogManager.getStatusCodeFromURLConnection(original);
        NetLogManager.getInstance().endNetworkCall(registry, url.toExternalForm(), System.currentTimeMillis(), i);
        if(original instanceof HttpURLConnection)
            ((HttpURLConnection)original).disconnect();
    }

    public boolean getAllowUserInteraction()
    {
        return original.getAllowUserInteraction();
    }

    public String getCipherSuite()
    {
        String s;
        if(original instanceof HttpsURLConnection)
            s = ((HttpsURLConnection)original).getCipherSuite();
        else
            s = "";
        return s;
    }

    public int getConnectTimeout()
    {
        return original.getConnectTimeout();
    }

    public Object getContent()
        throws IOException
    {
        Object obj;
        try
        {
            obj = original.getContent();
        }
        catch(IOException ioexception)
        {
            NetLogManager.getInstance().cancelNetworkCall(registry, url.toExternalForm(), System.currentTimeMillis(), "HTTPS", ioexception.getMessage());
            throw ioexception;
        }
        return obj;
    }

    public Object getContent(Class aclass[])
        throws IOException
    {
        Object obj;
        try
        {
            obj = original.getContent(aclass);
        }
        catch(IOException ioexception)
        {
            NetLogManager.getInstance().cancelNetworkCall(registry, url.toExternalForm(), System.currentTimeMillis(), "HTTPS", ioexception.getMessage());
            throw ioexception;
        }
        return obj;
    }

    public String getContentEncoding()
    {
        return original.getContentEncoding();
    }

    public int getContentLength()
    {
        return original.getContentLength();
    }

    public String getContentType()
    {
        return original.getContentType();
    }

    public long getDate()
    {
        return original.getDate();
    }

    public boolean getDefaultUseCaches()
    {
        return original.getDefaultUseCaches();
    }

    public boolean getDoInput()
    {
        return original.getDoInput();
    }

    public boolean getDoOutput()
    {
        return original.getDoOutput();
    }

    public InputStream getErrorStream()
    {
        InputStream inputstream;
        if(original instanceof HttpsURLConnection)
            inputstream = ((HttpsURLConnection)original).getErrorStream();
        else
            inputstream = null;
        return inputstream;
    }

    public long getExpiration()
    {
        return original.getExpiration();
    }

    public String getHeaderField(int i)
    {
        return original.getHeaderField(i);
    }

    public String getHeaderField(String s)
    {
        return original.getHeaderField(s);
    }

    public long getHeaderFieldDate(String s, long l)
    {
        return original.getHeaderFieldDate(s, l);
    }

    public int getHeaderFieldInt(String s, int i)
    {
        return original.getHeaderFieldInt(s, i);
    }

    public String getHeaderFieldKey(int i)
    {
        return original.getHeaderFieldKey(i);
    }

    public Map getHeaderFields()
    {
        return original.getHeaderFields();
    }

    public long getIfModifiedSince()
    {
        return original.getIfModifiedSince();
    }

    public InputStream getInputStream()
        throws IOException
    {
        Object obj;
        try
        {
            if(Properties.isKitKat)
            {
                if(mInputStreamMonitorKitKat == null)
                    mInputStreamMonitorKitKat = new InputStreamMonitorKitKat((new StringBuilder()).append(original.getURL().toString()).append("-in").toString(), registry, original.getInputStream(), null);
                obj = mInputStreamMonitorKitKat;
            } else
            {
                if(mInputStreamMonitor == null)
                    mInputStreamMonitor = new InputStreamMonitor((new StringBuilder()).append(original.getURL().toString()).append("-in").toString(), registry, original.getInputStream(), null);
                obj = mInputStreamMonitor;
            }
        }
        catch(IOException ioexception)
        {
            NetLogManager.getInstance().cancelNetworkCall(registry, url.toExternalForm(), System.currentTimeMillis(), "HTTPS", ioexception.getMessage());
            throw ioexception;
        }
        return ((InputStream) (obj));
    }

    public boolean getInstanceFollowRedirects()
    {
        boolean flag;
        if(original instanceof HttpsURLConnection)
            flag = ((HttpsURLConnection)original).getInstanceFollowRedirects();
        else
            flag = true;
        return flag;
    }

    public long getLastModified()
    {
        return original.getLastModified();
    }

    public Certificate[] getLocalCertificates()
    {
        Certificate acertificate[];
        if(original instanceof HttpsURLConnection)
            acertificate = ((HttpsURLConnection)original).getLocalCertificates();
        else
            acertificate = null;
        return acertificate;
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        OutputStreamMonitor outputstreammonitor;
        try
        {
            if(mOutputStreamMonitor == null)
                mOutputStreamMonitor = new OutputStreamMonitor((new StringBuilder()).append(original.getURL().toString()).append("-out").toString(), registry, original.getOutputStream());
            outputstreammonitor = mOutputStreamMonitor;
        }
        catch(IOException ioexception)
        {
            NetLogManager.getInstance().cancelNetworkCall(registry, url.toExternalForm(), System.currentTimeMillis(), "HTTPS", ioexception.getMessage());
            throw ioexception;
        }
        return outputstreammonitor;
    }

    public Permission getPermission()
        throws IOException
    {
        Permission permission;
        try
        {
            permission = original.getPermission();
        }
        catch(IOException ioexception)
        {
            NetLogManager.getInstance().cancelNetworkCall(registry, url.toExternalForm(), System.currentTimeMillis(), "HTTPS", ioexception.getMessage());
            throw ioexception;
        }
        return permission;
    }

    public int getReadTimeout()
    {
        return original.getReadTimeout();
    }

    public String getRequestMethod()
    {
        String s;
        if(original instanceof HttpsURLConnection)
            s = ((HttpsURLConnection)original).getRequestMethod();
        else
            s = "GET";
        return s;
    }

    public Map getRequestProperties()
    {
        return original.getRequestProperties();
    }

    public String getRequestProperty(String s)
    {
        return original.getRequestProperty(s);
    }

    public int getResponseCode()
        throws IOException
    {
        int i;
        if(original instanceof HttpsURLConnection)
            i = ((HttpsURLConnection)original).getResponseCode();
        else
            i = -1;
        return i;
    }

    public String getResponseMessage()
        throws IOException
    {
        String s;
        if(original instanceof HttpsURLConnection)
            s = ((HttpsURLConnection)original).getResponseMessage();
        else
            s = "";
        return s;
    }

    public Certificate[] getServerCertificates()
        throws SSLPeerUnverifiedException
    {
        Certificate acertificate[];
        if(original instanceof HttpsURLConnection)
            acertificate = ((HttpsURLConnection)original).getServerCertificates();
        else
            acertificate = null;
        return acertificate;
    }

    public URL getURL()
    {
        return original.getURL();
    }

    public boolean getUseCaches()
    {
        return original.getUseCaches();
    }

    public void setAllowUserInteraction(boolean flag)
    {
        original.setAllowUserInteraction(flag);
    }

    public void setChunkedStreamingMode(int i)
    {
        if(original instanceof HttpsURLConnection)
            ((HttpsURLConnection)original).setChunkedStreamingMode(i);
    }

    public void setConnectTimeout(int i)
    {
        original.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean flag)
    {
        original.setDefaultUseCaches(flag);
    }

    public void setDoInput(boolean flag)
    {
        original.setDoInput(flag);
    }

    public void setDoOutput(boolean flag)
    {
        original.setDoOutput(flag);
    }

    public void setFixedLengthStreamingMode(int i)
    {
        if(original instanceof HttpsURLConnection)
            ((HttpsURLConnection)original).setFixedLengthStreamingMode(i);
    }

    public void setIfModifiedSince(long l)
    {
        original.setIfModifiedSince(l);
    }

    public void setInstanceFollowRedirects(boolean flag)
    {
        if(original instanceof HttpsURLConnection)
            ((HttpsURLConnection)original).setInstanceFollowRedirects(flag);
    }

    public void setReadTimeout(int i)
    {
        original.setReadTimeout(i);
    }

    public void setRequestMethod(String s)
        throws ProtocolException
    {
        if(original instanceof HttpsURLConnection)
            ((HttpsURLConnection)original).setRequestMethod(s);
    }

    public void setRequestProperty(String s, String s1)
    {
        original.setRequestProperty(s, s1);
    }

    public void setUseCaches(boolean flag)
    {
        original.setUseCaches(flag);
    }

    public boolean usingProxy()
    {
        boolean flag;
        if(original instanceof HttpsURLConnection)
            flag = ((HttpsURLConnection)original).usingProxy();
        else
            flag = false;
        return flag;
    }

    private final String connectionName;
    private InputStreamMonitor mInputStreamMonitor;
    private InputStreamMonitorKitKat mInputStreamMonitorKitKat;
    private OutputStreamMonitor mOutputStreamMonitor;
    private URLConnection original;
    private final MonitorRegistry registry;
    private final Timer timer;
}
