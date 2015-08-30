// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.http;

import com.splunk.mint.Logger;
import com.splunk.mint.network.util.ReflectionUtil;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.*;

public abstract class URLStreamHandlerBase extends URLStreamHandler
{

    public URLStreamHandlerBase(String as[])
        throws ClassNotFoundException
    {
        initConstructors(as);
        if(proxyConstructor == null || simpleConstructor == null)
            throw new ClassNotFoundException("No implementation detected");
        else
            return;
    }

    private void initConstructors(String as[])
    {
        int i;
        int j;
        i = as.length;
        j = 0;
_L2:
        String s;
        if(j >= i)
            break MISSING_BLOCK_LABEL_69;
        s = as[j];
        proxyConstructor = ReflectionUtil.findConstructor(s, PROXY_FIELD_TYPES);
        if(proxyConstructor == null);
        simpleConstructor = ReflectionUtil.findConstructor(s, SIMPLE_FIELD_TYPES);
        if(simpleConstructor == null);
        proxyConstructor.setAccessible(true);
        simpleConstructor.setAccessible(true);
        return;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        proxyConstructor = null;
        simpleConstructor = null;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public abstract int getDefaultPort();

    public abstract String getProtocol();

    protected URLConnection openConnection(URL url)
        throws IOException
    {
        URLConnection urlconnection;
        try
        {
            Constructor constructor = simpleConstructor;
            Object aobj[] = new Object[2];
            aobj[0] = url;
            aobj[1] = Integer.valueOf(getDefaultPort());
            urlconnection = (URLConnection)constructor.newInstance(aobj);
        }
        catch(InstantiationException instantiationexception)
        {
            Logger.logError((new StringBuilder()).append("Error initializing connection - can't instantiate object: ").append(instantiationexception.getMessage()).toString());
            throw new IOException();
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            Logger.logError((new StringBuilder()).append("Error initializing connection - can't access constructor: ").append(illegalaccessexception.getMessage()).toString());
            throw new IOException();
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Logger.logError((new StringBuilder()).append("Error initializing connection - invalid argument: ").append(illegalargumentexception.getMessage()).toString());
            throw new IOException();
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            Logger.logError((new StringBuilder()).append("Error initializing connection - can't invoke target: ").append(invocationtargetexception.getMessage()).toString());
            throw new IOException();
        }
        return urlconnection;
    }

    protected URLConnection openConnection(URL url, Proxy proxy)
        throws IOException
    {
        if(proxy != null) goto _L2; else goto _L1
_L1:
        URLConnection urlconnection = openConnection(url);
_L4:
        return urlconnection;
_L2:
        try
        {
            Constructor constructor = proxyConstructor;
            Object aobj[] = new Object[3];
            aobj[0] = url;
            aobj[1] = Integer.valueOf(getDefaultPort());
            aobj[2] = proxy;
            urlconnection = (URLConnection)constructor.newInstance(aobj);
        }
        catch(InstantiationException instantiationexception)
        {
            Logger.logError((new StringBuilder()).append("Error initializing connection - can't instantiate object: ").append(instantiationexception.getMessage()).toString());
            throw new IOException();
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            Logger.logError((new StringBuilder()).append("Error initializing connection - can't access constructor: ").append(illegalaccessexception.getMessage()).toString());
            throw new IOException();
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Logger.logError((new StringBuilder()).append("Error initializing connection - invalid argument: ").append(illegalargumentexception.getMessage()).toString());
            throw new IOException();
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            Logger.logError((new StringBuilder()).append("Error initializing connection - can't invoke target: ").append(invocationtargetexception.getMessage()).toString());
            throw new IOException();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    static final Class PROXY_FIELD_TYPES[];
    static final Class SIMPLE_FIELD_TYPES[];
    private Constructor proxyConstructor;
    private Constructor simpleConstructor;

    static 
    {
        Class aclass[] = new Class[2];
        aclass[0] = java/net/URL;
        aclass[1] = Integer.TYPE;
        SIMPLE_FIELD_TYPES = aclass;
        Class aclass1[] = new Class[3];
        aclass1[0] = java/net/URL;
        aclass1[1] = Integer.TYPE;
        aclass1[2] = java/net/Proxy;
        PROXY_FIELD_TYPES = aclass1;
    }
}
