// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.util;

import java.lang.reflect.Method;

public class PropertyDescriptor
{

    public PropertyDescriptor(String s)
    {
        name = s;
    }

    public String getName()
    {
        return name;
    }

    public Class getPropertyType()
    {
        return type;
    }

    public Method getReadMethod()
    {
        return readMethod;
    }

    public Method getWriteMethod()
    {
        return writeMethod;
    }

    public void setPropertyType(Class class1)
    {
        type = class1;
    }

    public void setReadMethod(Method method)
    {
        readMethod = method;
    }

    public void setWriteMethod(Method method)
    {
        writeMethod = method;
    }

    private String name;
    private Method readMethod;
    private Class type;
    private Method writeMethod;
}
