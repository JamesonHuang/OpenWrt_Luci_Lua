// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.selector.ContextSelector;
import ch.qos.logback.classic.selector.DefaultContextSelector;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ContextSelectorStaticBinder
{

    public ContextSelectorStaticBinder()
    {
    }

    static ContextSelector dynamicalContextSelector(LoggerContext loggercontext, String s)
        throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Class class1 = Loader.loadClass(s);
        Class aclass[] = new Class[1];
        aclass[0] = ch/qos/logback/classic/LoggerContext;
        Constructor constructor = class1.getConstructor(aclass);
        Object aobj[] = new Object[1];
        aobj[0] = loggercontext;
        return (ContextSelector)constructor.newInstance(aobj);
    }

    public static ContextSelectorStaticBinder getSingleton()
    {
        return singleton;
    }

    public ContextSelector getContextSelector()
    {
        return contextSelector;
    }

    public void init(LoggerContext loggercontext, Object obj)
        throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        String s;
        if(key == null)
            key = obj;
        else
        if(key != obj)
            throw new IllegalAccessException("Only certain classes can access this method.");
        s = OptionHelper.getSystemProperty("logback.ContextSelector");
        if(s == null)
        {
            contextSelector = new DefaultContextSelector(loggercontext);
        } else
        {
            if(s.equals("JNDI"))
                throw new RuntimeException("JNDI not supported");
            contextSelector = dynamicalContextSelector(loggercontext, s);
        }
    }

    static ContextSelectorStaticBinder singleton = new ContextSelectorStaticBinder();
    ContextSelector contextSelector;
    Object key;

}
