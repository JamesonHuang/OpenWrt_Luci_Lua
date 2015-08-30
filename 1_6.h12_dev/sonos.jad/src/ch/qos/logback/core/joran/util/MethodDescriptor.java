// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.util;

import java.lang.reflect.Method;

public class MethodDescriptor
{

    public MethodDescriptor(String s, Method method1)
    {
        name = s;
        method = method1;
    }

    public Method getMethod()
    {
        return method;
    }

    public String getName()
    {
        return name;
    }

    private Method method;
    private String name;
}
