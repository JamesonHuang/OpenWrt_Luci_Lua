// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.android;

import java.lang.reflect.Method;

public class SystemPropertiesProxy
{

    private SystemPropertiesProxy(ClassLoader classloader)
    {
        setClassLoader(classloader);
_L2:
        return;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static SystemPropertiesProxy getInstance()
    {
        return SINGLETON;
    }

    public String get(String s, String s1)
        throws IllegalArgumentException
    {
        if(SystemProperties != null && getString != null) goto _L2; else goto _L1
_L1:
        s1 = null;
_L4:
        return s1;
_L2:
        String s2;
        try
        {
            Method method = getString;
            Class class1 = SystemProperties;
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = s1;
            s2 = (String)method.invoke(class1, aobj);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw illegalargumentexception;
        }
        catch(Exception exception)
        {
            s2 = null;
        }
        if(s2 != null && s2.length() != 0)
            s1 = s2;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Boolean getBoolean(String s, boolean flag)
        throws IllegalArgumentException
    {
        Boolean boolean1;
        if(SystemProperties == null || getBoolean == null)
        {
            boolean1 = Boolean.valueOf(flag);
        } else
        {
            Boolean boolean2 = Boolean.valueOf(flag);
            try
            {
                Method method = getBoolean;
                Class class1 = SystemProperties;
                Object aobj[] = new Object[2];
                aobj[0] = s;
                aobj[1] = Boolean.valueOf(flag);
                boolean1 = (Boolean)method.invoke(class1, aobj);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw illegalargumentexception;
            }
            catch(Exception exception)
            {
                boolean1 = boolean2;
            }
        }
        return boolean1;
    }

    public void setClassLoader(ClassLoader classloader)
        throws ClassNotFoundException, SecurityException, NoSuchMethodException
    {
        if(classloader == null)
            classloader = getClass().getClassLoader();
        SystemProperties = classloader.loadClass("android.os.SystemProperties");
        Class class1 = SystemProperties;
        Class aclass[] = new Class[2];
        aclass[0] = java/lang/String;
        aclass[1] = java/lang/String;
        getString = class1.getMethod("get", aclass);
        Class class2 = SystemProperties;
        Class aclass1[] = new Class[2];
        aclass1[0] = java/lang/String;
        aclass1[1] = Boolean.TYPE;
        getBoolean = class2.getMethod("getBoolean", aclass1);
    }

    private static final SystemPropertiesProxy SINGLETON = new SystemPropertiesProxy(null);
    private Class SystemProperties;
    private Method getBoolean;
    private Method getString;

}
