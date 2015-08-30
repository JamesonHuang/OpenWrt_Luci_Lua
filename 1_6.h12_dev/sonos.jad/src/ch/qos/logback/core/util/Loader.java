// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import ch.qos.logback.core.Context;
import java.io.IOException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.util:
//            OptionHelper

public class Loader
{

    public Loader()
    {
    }

    public static ClassLoader getClassLoaderAsPrivileged(final Class clazz)
    {
        ClassLoader classloader;
        if(!HAS_GET_CLASS_LOADER_PERMISSION)
            classloader = null;
        else
            classloader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {

                public ClassLoader run()
                {
                    return clazz.getClassLoader();
                }

                public volatile Object run()
                {
                    return run();
                }

                final Class val$clazz;

            
            {
                clazz = class1;
                super();
            }
            }
);
        return classloader;
    }

    public static ClassLoader getClassLoaderOfClass(Class class1)
    {
        ClassLoader classloader = class1.getClassLoader();
        if(classloader == null)
            classloader = ClassLoader.getSystemClassLoader();
        return classloader;
    }

    public static ClassLoader getClassLoaderOfObject(Object obj)
    {
        if(obj == null)
            throw new NullPointerException("Argument cannot be null");
        else
            return getClassLoaderOfClass(obj.getClass());
    }

    public static URL getResource(String s, ClassLoader classloader)
    {
        URL url1 = classloader.getResource(s);
        URL url = url1;
_L2:
        return url;
        Throwable throwable;
        throwable;
        url = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static URL getResourceBySelfClassLoader(String s)
    {
        return getResource(s, getClassLoaderOfClass(ch/qos/logback/core/util/Loader));
    }

    public static Set getResourceOccurrenceCount(String s, ClassLoader classloader)
        throws IOException
    {
        HashSet hashset = new HashSet();
        for(Enumeration enumeration = classloader.getResources(s); enumeration.hasMoreElements(); hashset.add((URL)enumeration.nextElement()));
        return hashset;
    }

    public static ClassLoader getTCL()
    {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class loadClass(String s)
        throws ClassNotFoundException
    {
        if(!ignoreTCL) goto _L2; else goto _L1
_L1:
        Class class1 = Class.forName(s);
_L4:
        return class1;
_L2:
        Class class2 = getTCL().loadClass(s);
        class1 = class2;
        continue; /* Loop/switch isn't completed */
        Throwable throwable;
        throwable;
        class1 = Class.forName(s);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Class loadClass(String s, Context context)
        throws ClassNotFoundException
    {
        return getClassLoaderOfObject(context).loadClass(s);
    }

    private static boolean HAS_GET_CLASS_LOADER_PERMISSION = false;
    public static final String IGNORE_TCL_PROPERTY_NAME = "logback.ignoreTCL";
    static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";
    private static boolean ignoreTCL = false;

    static 
    {
        HAS_GET_CLASS_LOADER_PERMISSION = false;
        String s = OptionHelper.getSystemProperty("logback.ignoreTCL", null);
        if(s != null)
            ignoreTCL = Boolean.valueOf(s).booleanValue();
        HAS_GET_CLASS_LOADER_PERMISSION = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

            public Boolean run()
            {
                Boolean boolean2;
                AccessController.checkPermission(new RuntimePermission("getClassLoader"));
                boolean2 = Boolean.valueOf(true);
                Boolean boolean1 = boolean2;
_L2:
                return boolean1;
                SecurityException securityexception;
                securityexception;
                boolean1 = Boolean.valueOf(false);
                if(true) goto _L2; else goto _L1
_L1:
            }

            public volatile Object run()
            {
                return run();
            }

        }
)).booleanValue();
    }
}
