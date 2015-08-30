// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.spi;

import org.slf4j.ILoggerFactory;

public interface LoggerFactoryBinder
{

    public abstract ILoggerFactory getLoggerFactory();

    public abstract String getLoggerFactoryClassStr();
}
