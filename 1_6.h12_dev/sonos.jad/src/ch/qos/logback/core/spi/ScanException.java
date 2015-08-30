// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;


public class ScanException extends Exception
{

    public ScanException(String s)
    {
        super(s);
    }

    public ScanException(String s, Throwable throwable)
    {
        super(s);
        cause = throwable;
    }

    public Throwable getCause()
    {
        return cause;
    }

    private static final long serialVersionUID = 0xd488c1d661c8aff6L;
    Throwable cause;
}
