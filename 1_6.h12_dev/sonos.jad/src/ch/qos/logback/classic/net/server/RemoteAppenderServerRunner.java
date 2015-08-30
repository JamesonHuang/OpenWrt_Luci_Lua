// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net.server;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.net.server.*;
import java.util.concurrent.Executor;

// Referenced classes of package ch.qos.logback.classic.net.server:
//            RemoteAppenderClient

class RemoteAppenderServerRunner extends ConcurrentServerRunner
{

    public RemoteAppenderServerRunner(ServerListener serverlistener, Executor executor)
    {
        super(serverlistener, executor);
    }

    protected boolean configureClient(RemoteAppenderClient remoteappenderclient)
    {
        remoteappenderclient.setLoggerContext((LoggerContext)getContext());
        return true;
    }

    protected volatile boolean configureClient(Client client)
    {
        return configureClient((RemoteAppenderClient)client);
    }
}
