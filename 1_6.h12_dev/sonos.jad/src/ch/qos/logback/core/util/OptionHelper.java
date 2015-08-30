// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.android.SystemPropertiesProxy;
import ch.qos.logback.core.spi.*;
import ch.qos.logback.core.subst.NodeToStringTransformer;
import java.lang.reflect.Constructor;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.util:
//            IncompatibleClassException, DynamicClassLoadingException, Loader

public class OptionHelper
{

    public OptionHelper()
    {
    }

    public static String[] extractDefaultReplacement(String s)
    {
        String as[] = new String[2];
        if(s != null) goto _L2; else goto _L1
_L1:
        return as;
_L2:
        as[0] = s;
        int i = s.indexOf(":-");
        if(i != -1)
        {
            as[0] = s.substring(0, i);
            as[1] = s.substring(i + 2);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static String getAndroidSystemProperty(String s)
    {
        String s1 = null;
        String s2 = SystemPropertiesProxy.getInstance().get(s, null);
        s1 = s2;
_L2:
        return s1;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String getEnv(String s)
    {
        String s2 = System.getenv(s);
        String s1 = s2;
_L2:
        return s1;
        SecurityException securityexception;
        securityexception;
        s1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Properties getSystemProperties()
    {
        Properties properties1 = System.getProperties();
        Properties properties = properties1;
_L2:
        return properties;
        SecurityException securityexception;
        securityexception;
        properties = new Properties();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String getSystemProperty(String s)
    {
        String s1;
        String s2;
        s1 = System.getProperty(s);
        if(s1 != null)
            break MISSING_BLOCK_LABEL_16;
        s2 = getAndroidSystemProperty(s);
        s1 = s2;
_L2:
        return s1;
        SecurityException securityexception;
        securityexception;
        s1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String getSystemProperty(String s, String s1)
    {
        String s2 = System.getProperty(s, s1);
        s1 = s2;
_L2:
        return s1;
        SecurityException securityexception;
        securityexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Object instantiateByClassName(String s, Class class1, Context context)
        throws IncompatibleClassException, DynamicClassLoadingException
    {
        return instantiateByClassName(s, class1, Loader.getClassLoaderOfObject(context));
    }

    public static Object instantiateByClassName(String s, Class class1, ClassLoader classloader)
        throws IncompatibleClassException, DynamicClassLoadingException
    {
        return instantiateByClassNameAndParameter(s, class1, classloader, null, null);
    }

    public static Object instantiateByClassNameAndParameter(String s, Class class1, Context context, Class class2, Object obj)
        throws IncompatibleClassException, DynamicClassLoadingException
    {
        return instantiateByClassNameAndParameter(s, class1, Loader.getClassLoaderOfObject(context), class2, obj);
    }

    public static Object instantiateByClassNameAndParameter(String s, Class class1, ClassLoader classloader, Class class2, Object obj)
        throws IncompatibleClassException, DynamicClassLoadingException
    {
        Object obj2;
        if(s == null)
            throw new NullPointerException();
        Class class3;
        Class aclass[];
        Constructor constructor;
        Object aobj[];
        Object obj1;
        try
        {
            class3 = classloader.loadClass(s);
            if(!class1.isAssignableFrom(class3))
                throw new IncompatibleClassException(class1, class3);
        }
        catch(IncompatibleClassException incompatibleclassexception)
        {
            throw incompatibleclassexception;
        }
        catch(Throwable throwable)
        {
            throw new DynamicClassLoadingException((new StringBuilder()).append("Failed to instantiate type ").append(s).toString(), throwable);
        }
        if(class2 != null)
            break MISSING_BLOCK_LABEL_58;
        obj2 = class3.newInstance();
        break MISSING_BLOCK_LABEL_137;
        aclass = new Class[1];
        aclass[0] = class2;
        constructor = class3.getConstructor(aclass);
        aobj = new Object[1];
        aobj[0] = obj;
        obj1 = constructor.newInstance(aobj);
        obj2 = obj1;
        return obj2;
    }

    public static boolean isEmpty(String s)
    {
        boolean flag;
        if(s == null || "".equals(s))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static String propertyLookup(String s, PropertyContainer propertycontainer, PropertyContainer propertycontainer1)
    {
        String s1 = propertycontainer.getProperty(s);
        if(s1 == null && propertycontainer1 != null)
            s1 = propertycontainer1.getProperty(s);
        if(s1 == null)
            s1 = getSystemProperty(s, null);
        if(s1 == null)
            s1 = getEnv(s);
        return s1;
    }

    public static void setSystemProperties(ContextAware contextaware, Properties properties)
    {
        String s;
        for(Iterator iterator = properties.keySet().iterator(); iterator.hasNext(); setSystemProperty(contextaware, s, properties.getProperty(s)))
            s = (String)iterator.next();

    }

    public static void setSystemProperty(ContextAware contextaware, String s, String s1)
    {
        System.setProperty(s, s1);
_L1:
        return;
        SecurityException securityexception;
        securityexception;
        contextaware.addError((new StringBuilder()).append("Failed to set system property [").append(s).append("]").toString(), securityexception);
          goto _L1
    }

    public static String substVars(String s, PropertyContainer propertycontainer)
    {
        return substVars(s, propertycontainer, null);
    }

    public static String substVars(String s, PropertyContainer propertycontainer, PropertyContainer propertycontainer1)
    {
        String s1;
        try
        {
            s1 = NodeToStringTransformer.substituteVariable(s, propertycontainer, propertycontainer1);
        }
        catch(ScanException scanexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to parse input [").append(s).append("]").toString(), scanexception);
        }
        return s1;
    }

    public static boolean toBoolean(String s, boolean flag)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        String s1 = s.trim();
        if("true".equalsIgnoreCase(s1))
            flag = true;
        else
        if("false".equalsIgnoreCase(s1))
            flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    static final String DELIM_DEFAULT = ":-";
    static final int DELIM_DEFAULT_LEN = 2;
    static final String DELIM_START = "${";
    static final int DELIM_START_LEN = 2;
    static final char DELIM_STOP = 125;
    static final int DELIM_STOP_LEN = 1;
    static final String _IS_UNDEFINED = "_IS_UNDEFINED";
}
