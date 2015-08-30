// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import ch.qos.logback.core.spi.ContextAware;
import java.io.Serializable;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package ch.qos.logback.core.net.server:
//            Client

interface RemoteReceiverClient
    extends Client, ContextAware
{

    public abstract boolean offer(Serializable serializable);

    public abstract void setQueue(BlockingQueue blockingqueue);
}
