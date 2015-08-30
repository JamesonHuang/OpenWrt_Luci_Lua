// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.android.CommonPathUtil;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;
import java.io.File;
import java.io.InputStream;
import java.net.*;

// Referenced classes of package ch.qos.logback.classic.util:
//            StatusListenerConfigHelper

public class ContextInitializer
{

    public ContextInitializer(LoggerContext loggercontext)
    {
        loggerContext = loggercontext;
    }

    private URL findConfigFileFromSystemProperties(boolean flag)
    {
        String s;
        String s1;
        s = null;
        s1 = OptionHelper.getSystemProperty("logback.configurationFile");
        if(s1 == null) goto _L2; else goto _L1
_L1:
        File file = new File(s1);
        if(!file.exists() || !file.isFile()) goto _L4; else goto _L3
_L3:
        URL url2;
        if(flag)
            statusOnResourceSearch(s1, classLoader, s1);
        url2 = file.toURI().toURL();
        URL url = url2;
_L8:
        if(flag)
        {
            ClassLoader classloader3 = classLoader;
            if(url != null)
                s = url.toString();
            statusOnResourceSearch(s1, classloader3, s);
        }
_L6:
        return url;
_L4:
        url = new URL(s1);
        continue; /* Loop/switch isn't completed */
        MalformedURLException malformedurlexception;
        malformedurlexception;
        URL url1 = Loader.getResource(s1, classLoader);
        url = url1;
        if(url != null)
        {
            if(flag)
            {
                ClassLoader classloader2 = classLoader;
                if(url != null)
                    s = url.toString();
                statusOnResourceSearch(s1, classloader2, s);
            }
            continue; /* Loop/switch isn't completed */
        }
        if(flag)
        {
            ClassLoader classloader1 = classLoader;
            String s2;
            if(url != null)
                s2 = url.toString();
            else
                s2 = null;
            statusOnResourceSearch(s1, classloader1, s2);
        }
_L2:
        url = null;
        if(true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        if(flag)
        {
            ClassLoader classloader = classLoader;
            if(false)
                s = null.toString();
            statusOnResourceSearch(s1, classloader, s);
        }
        throw exception;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private InputStream findConfigFileURLFromAssets(boolean flag)
    {
        return getResource((new StringBuilder()).append(ASSETS_DIR).append("/").append("logback.xml").toString(), classLoader, flag);
    }

    private InputStream getResource(String s, ClassLoader classloader, boolean flag)
    {
        InputStream inputstream = classloader.getResourceAsStream(s);
        if(flag)
        {
            String s1 = null;
            if(inputstream != null)
                s1 = s;
            statusOnResourceSearch(s, classloader, s1);
        }
        return inputstream;
    }

    private void statusOnResourceSearch(String s, ClassLoader classloader, String s1)
    {
        StatusManager statusmanager = loggerContext.getStatusManager();
        if(s1 == null)
            statusmanager.add(new InfoStatus((new StringBuilder()).append("Could NOT find resource [").append(s).append("]").toString(), loggerContext));
        else
            statusmanager.add(new InfoStatus((new StringBuilder()).append("Found resource [").append(s).append("] at [").append(s1).append("]").toString(), loggerContext));
    }

    public void autoConfig()
        throws JoranException
    {
        StatusListenerConfigHelper.installIfAsked(loggerContext);
        boolean flag = false;
        JoranConfigurator joranconfigurator = new JoranConfigurator();
        joranconfigurator.setContext(loggerContext);
        URL url = findConfigFileFromSystemProperties(true);
        if(url != null)
        {
            joranconfigurator.doConfigure(url);
            flag = true;
        }
        if(!flag)
        {
            InputStream inputstream = findConfigFileURLFromAssets(true);
            if(inputstream != null)
                joranconfigurator.doConfigure(inputstream);
        }
    }

    private static final String ASSETS_DIR = CommonPathUtil.getAssetsDirectoryPath();
    public static final String AUTOCONFIG_FILE = "logback.xml";
    public static final String CONFIG_FILE_PROPERTY = "logback.configurationFile";
    public static final String STATUS_LISTENER_CLASS = "logback.statusListenerClass";
    final ClassLoader classLoader = Loader.getClassLoaderOfObject(this);
    final LoggerContext loggerContext;

}
