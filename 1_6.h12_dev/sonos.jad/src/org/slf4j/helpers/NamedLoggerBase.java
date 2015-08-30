// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import java.io.ObjectStreamException;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class NamedLoggerBase
    implements Logger, Serializable
{

    NamedLoggerBase()
    {
    }

    public String getName()
    {
        return name;
    }

    protected Object readResolve()
        throws ObjectStreamException
    {
        return LoggerFactory.getLogger(getName());
    }

    private static final long serialVersionUID = 0x68929dc81c4e557dL;
    protected String name;
}
