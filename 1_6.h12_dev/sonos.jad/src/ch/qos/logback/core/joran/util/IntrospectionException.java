// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.util;


public class IntrospectionException extends RuntimeException
{

    public IntrospectionException(Exception exception)
    {
        super(exception);
    }

    public IntrospectionException(String s)
    {
        super(s);
    }

    private static final long serialVersionUID = 0xa22f02d18ad8d402L;
}
