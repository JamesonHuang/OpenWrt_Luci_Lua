// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;


// Referenced classes of package ch.qos.logback.core.spi:
//            ContextAware

public interface PropertyDefiner
    extends ContextAware
{

    public abstract String getPropertyValue();
}
