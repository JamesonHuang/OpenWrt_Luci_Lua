// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;

public interface Layout
    extends ContextAware, LifeCycle
{

    public abstract String doLayout(Object obj);

    public abstract String getContentType();

    public abstract String getFileFooter();

    public abstract String getFileHeader();

    public abstract String getPresentationFooter();

    public abstract String getPresentationHeader();
}
