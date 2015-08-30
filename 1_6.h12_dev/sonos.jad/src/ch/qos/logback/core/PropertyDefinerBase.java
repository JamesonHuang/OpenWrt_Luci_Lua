// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.PropertyDefiner;

public abstract class PropertyDefinerBase extends ContextAwareBase
    implements PropertyDefiner
{

    public PropertyDefinerBase()
    {
    }

    protected static String booleanAsStr(boolean flag)
    {
        String s;
        if(flag)
            s = Boolean.TRUE.toString();
        else
            s = Boolean.FALSE.toString();
        return s;
    }
}
