// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.encoder;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;
import java.io.IOException;
import java.io.OutputStream;

public interface Encoder
    extends ContextAware, LifeCycle
{

    public abstract void close()
        throws IOException;

    public abstract void doEncode(Object obj)
        throws IOException;

    public abstract void init(OutputStream outputstream)
        throws IOException;
}
