// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.http;

import com.splunk.mint.network.MonitorRegistry;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.splunk.mint.network.http:
//            HTTPURLStreamHandler, URLStreamHandlerBase, HTTPSURLStreamHandler

public class MonitorableURLStreamHandlerFactory
    implements URLStreamHandlerFactory
{

    public MonitorableURLStreamHandlerFactory(MonitorRegistry monitorregistry)
        throws ClassNotFoundException
    {
        handlers = new HashMap();
        HTTPURLStreamHandler httpurlstreamhandler = new HTTPURLStreamHandler(monitorregistry);
        handlers.put(httpurlstreamhandler.getProtocol(), httpurlstreamhandler);
        HTTPSURLStreamHandler httpsurlstreamhandler = new HTTPSURLStreamHandler(monitorregistry);
        handlers.put(httpsurlstreamhandler.getProtocol(), httpsurlstreamhandler);
    }

    public URLStreamHandler createURLStreamHandler(String s)
    {
        return (URLStreamHandler)handlers.get(s);
    }

    private Map handlers;
}
