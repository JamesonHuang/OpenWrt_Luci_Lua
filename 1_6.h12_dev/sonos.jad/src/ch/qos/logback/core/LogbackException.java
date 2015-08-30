// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;


public class LogbackException extends RuntimeException
{

    public LogbackException(String s)
    {
        super(s);
    }

    public LogbackException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    private static final long serialVersionUID = 0xf4e5fbf10299c40eL;
}
