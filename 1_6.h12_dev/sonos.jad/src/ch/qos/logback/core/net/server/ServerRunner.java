// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import ch.qos.logback.core.spi.ContextAware;
import java.io.IOException;

// Referenced classes of package ch.qos.logback.core.net.server:
//            ClientVisitor

public interface ServerRunner
    extends ContextAware, Runnable
{

    public abstract void accept(ClientVisitor clientvisitor);

    public abstract boolean isRunning();

    public abstract void stop()
        throws IOException;
}
