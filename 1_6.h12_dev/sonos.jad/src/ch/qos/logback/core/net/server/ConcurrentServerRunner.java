// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package ch.qos.logback.core.net.server:
//            ServerRunner, Client, ClientVisitor, ServerListener

public abstract class ConcurrentServerRunner extends ContextAwareBase
    implements Runnable, ServerRunner
{
    private class ClientWrapper
        implements Client
    {

        public void close()
        {
            _flddelegate.close();
        }

        public void run()
        {
            addClient(_flddelegate);
            _flddelegate.run();
            removeClient(_flddelegate);
            return;
            Exception exception;
            exception;
            removeClient(_flddelegate);
            throw exception;
        }

        private final Client _flddelegate;
        final ConcurrentServerRunner this$0;

        public ClientWrapper(Client client)
        {
            this$0 = ConcurrentServerRunner.this;
            super();
            _flddelegate = client;
        }
    }


    public ConcurrentServerRunner(ServerListener serverlistener, Executor executor1)
    {
        listener = serverlistener;
        executor = executor1;
    }

    private void addClient(Client client)
    {
        clientsLock.lock();
        clients.add(client);
        clientsLock.unlock();
        return;
        Exception exception;
        exception;
        clientsLock.unlock();
        throw exception;
    }

    private Collection copyClients()
    {
        clientsLock.lock();
        ArrayList arraylist = new ArrayList(clients);
        clientsLock.unlock();
        return arraylist;
        Exception exception;
        exception;
        clientsLock.unlock();
        throw exception;
    }

    private void removeClient(Client client)
    {
        clientsLock.lock();
        clients.remove(client);
        clientsLock.unlock();
        return;
        Exception exception;
        exception;
        clientsLock.unlock();
        throw exception;
    }

    public void accept(ClientVisitor clientvisitor)
    {
        for(Iterator iterator = copyClients().iterator(); iterator.hasNext();)
        {
            Client client = (Client)iterator.next();
            try
            {
                clientvisitor.visit(client);
            }
            catch(RuntimeException runtimeexception)
            {
                addError((new StringBuilder()).append(client).append(": ").append(runtimeexception).toString());
            }
        }

    }

    protected abstract boolean configureClient(Client client);

    public boolean isRunning()
    {
        return running;
    }

    public void run()
    {
        setRunning(true);
        addInfo((new StringBuilder()).append("listening on ").append(listener).toString());
_L5:
        if(Thread.currentThread().isInterrupted()) goto _L2; else goto _L1
_L1:
        Client client = listener.acceptClient();
        if(configureClient(client)) goto _L4; else goto _L3
_L3:
        addError((new StringBuilder()).append(client).append(": connection dropped").toString());
        client.close();
          goto _L5
        InterruptedException interruptedexception;
        interruptedexception;
_L2:
        setRunning(false);
        addInfo("shutting down");
        listener.close();
        return;
_L4:
        executor.execute(new ClientWrapper(client));
          goto _L5
        RejectedExecutionException rejectedexecutionexception;
        rejectedexecutionexception;
        addError((new StringBuilder()).append(client).append(": connection dropped").toString());
        client.close();
          goto _L5
        Exception exception;
        exception;
        addError((new StringBuilder()).append("listener: ").append(exception).toString());
          goto _L2
    }

    protected void setRunning(boolean flag)
    {
        running = flag;
    }

    public void stop()
        throws IOException
    {
        listener.close();
        accept(new ClientVisitor() {

            public void visit(Client client)
            {
                client.close();
            }

            final ConcurrentServerRunner this$0;

            
            {
                this$0 = ConcurrentServerRunner.this;
                super();
            }
        }
);
    }

    private final Collection clients = new ArrayList();
    private final Lock clientsLock = new ReentrantLock();
    private final Executor executor;
    private final ServerListener listener;
    private boolean running;


}
