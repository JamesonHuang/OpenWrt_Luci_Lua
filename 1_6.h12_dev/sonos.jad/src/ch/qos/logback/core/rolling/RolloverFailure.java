// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.LogbackException;

public class RolloverFailure extends LogbackException
{

    public RolloverFailure(String s)
    {
        super(s);
    }

    public RolloverFailure(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    private static final long serialVersionUID = 0xc2d54b6c864076deL;
}
