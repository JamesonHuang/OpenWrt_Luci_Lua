// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j.helpers;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

// Referenced classes of package org.slf4j.helpers:
//            NOPLogger

public class NOPLoggerFactory
    implements ILoggerFactory
{

    public NOPLoggerFactory()
    {
    }

    public Logger getLogger(String s)
    {
        return NOPLogger.NOP_LOGGER;
    }
}
