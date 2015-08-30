// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling;

import ch.qos.logback.core.spi.LifeCycle;
import java.io.File;

public interface TriggeringPolicy
    extends LifeCycle
{

    public abstract boolean isTriggeringEvent(File file, Object obj);
}
