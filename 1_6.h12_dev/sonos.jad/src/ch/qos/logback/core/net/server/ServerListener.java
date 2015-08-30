// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import java.io.Closeable;
import java.io.IOException;

// Referenced classes of package ch.qos.logback.core.net.server:
//            Client

public interface ServerListener
    extends Closeable
{

    public abstract Client acceptClient()
        throws IOException, InterruptedException;

    public abstract void close();
}
