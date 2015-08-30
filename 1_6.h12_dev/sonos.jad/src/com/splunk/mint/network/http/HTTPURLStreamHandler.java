// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.http;

import com.splunk.mint.network.MonitorRegistry;
import java.io.IOException;
import java.net.*;

// Referenced classes of package com.splunk.mint.network.http:
//            URLStreamHandlerBase, MonitorableHttpURLConnection

public final class HTTPURLStreamHandler extends URLStreamHandlerBase
{

    public HTTPURLStreamHandler(MonitorRegistry monitorregistry)
        throws ClassNotFoundException
    {
        super(SYSTEM_CLASSES);
        registry = monitorregistry;
    }

    public int getDefaultPort()
    {
        return 80;
    }

    public String getProtocol()
    {
        return "http";
    }

    protected URLConnection openConnection(URL url)
        throws IOException
    {
        URLConnection urlconnection = super.openConnection(url);
        return new MonitorableHttpURLConnection(registry, urlconnection);
    }

    protected URLConnection openConnection(URL url, Proxy proxy)
        throws IOException
    {
        URLConnection urlconnection = super.openConnection(url, proxy);
        return new MonitorableHttpURLConnection(registry, urlconnection);
    }

    private static final int PORT = 80;
    private static final String PROTOCOL = "http";
    private static final String SYSTEM_CLASSES[];
    private final MonitorRegistry registry;

    static 
    {
        String as[] = new String[3];
        as[0] = "libcore.net.http.HttpURLConnectionImpl";
        as[1] = "org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnectionImpl";
        as[2] = "org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnection";
        SYSTEM_CLASSES = as;
    }
}
