// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;

// Referenced classes of package ch.qos.logback.core.net.server:
//            ConcurrentServerRunner, RemoteReceiverClient, ServerListener, Client

class RemoteReceiverServerRunner extends ConcurrentServerRunner
{

    public RemoteReceiverServerRunner(ServerListener serverlistener, Executor executor, int i)
    {
        super(serverlistener, executor);
        clientQueueSize = i;
    }

    protected volatile boolean configureClient(Client client)
    {
        return configureClient((RemoteReceiverClient)client);
    }

    protected boolean configureClient(RemoteReceiverClient remotereceiverclient)
    {
        remotereceiverclient.setContext(getContext());
        remotereceiverclient.setQueue(new ArrayBlockingQueue(clientQueueSize));
        return true;
    }

    private final int clientQueueSize;
}
