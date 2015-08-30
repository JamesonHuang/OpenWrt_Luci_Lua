// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.jniutil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NativeCleanupInvocation
    implements Runnable
{

    public NativeCleanupInvocation(Method method, long l)
    {
        m_swigCPtr = l;
        m_swigNativeMethod = method;
    }

    public static Method lookupMethod(Class class1, String s)
    {
        Method method1 = class1.getMethod(s, signature);
        Method method = method1;
_L2:
        return method;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        nosuchmethodexception.printStackTrace();
        method = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void run()
    {
        if(m_swigCPtr != 0L)
        {
            try
            {
                if(m_swigNativeMethod != null)
                {
                    Method method = m_swigNativeMethod;
                    Object aobj[] = new Object[1];
                    aobj[0] = Long.valueOf(m_swigCPtr);
                    method.invoke(null, aobj);
                }
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                illegalaccessexception.printStackTrace();
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                illegalargumentexception.printStackTrace();
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                invocationtargetexception.printStackTrace();
            }
            m_swigCPtr = 0L;
        }
    }

    static final Class signature[];
    private long m_swigCPtr;
    private Method m_swigNativeMethod;

    static 
    {
        Class aclass[] = new Class[1];
        aclass[0] = Long.TYPE;
        signature = aclass;
    }
}
