// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.spi.ContextAware;
import java.util.Date;

public interface ArchiveRemover
    extends ContextAware
{

    public abstract void clean(Date date);

    public abstract void setMaxHistory(int i);
}
