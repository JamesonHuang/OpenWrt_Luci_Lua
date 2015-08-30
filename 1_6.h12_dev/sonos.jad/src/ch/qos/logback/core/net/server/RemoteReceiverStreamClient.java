// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.server;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.CloseUtil;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;

// Referenced classes of package ch.qos.logback.core.net.server:
//            RemoteReceiverClient

class RemoteReceiverStreamClient extends ContextAwareBase
    implements RemoteReceiverClient
{

    RemoteReceiverStreamClient(String s, OutputStream outputstream)
    {
        clientId = (new StringBuilder()).append("client ").append(s).append(": ").toString();
        socket = null;
        outputStream = outputstream;
    }

    public RemoteReceiverStreamClient(String s, Socket socket1)
    {
        clientId = (new StringBuilder()).append("client ").append(s).append(": ").toString();
        socket = socket1;
        outputStream = null;
    }

    private ObjectOutputStream createObjectOutputStream()
        throws IOException
    {
        ObjectOutputStream objectoutputstream;
        if(socket == null)
            objectoutputstream = new ObjectOutputStream(outputStream);
        else
            objectoutputstream = new ObjectOutputStream(socket.getOutputStream());
        return objectoutputstream;
    }

    public void close()
    {
        if(socket != null)
            CloseUtil.closeQuietly(socket);
    }

    public boolean offer(Serializable serializable)
    {
        if(queue == null)
            throw new IllegalStateException("client has no event queue");
        else
            return queue.offer(serializable);
    }

    public void run()
    {
        addInfo((new StringBuilder()).append(clientId).append("connected").toString());
        ObjectOutputStream objectoutputstream = createObjectOutputStream();
        Object obj;
        int i;
        obj = objectoutputstream;
        i = 0;
_L3:
        boolean flag = Thread.currentThread().isInterrupted();
        if(flag) goto _L2; else goto _L1
_L1:
        ((ObjectOutputStream) (obj)).writeObject((Serializable)queue.take());
        ((ObjectOutputStream) (obj)).flush();
        int k;
        k = i + 1;
        if(k < 70)
            break MISSING_BLOCK_LABEL_91;
        ((ObjectOutputStream) (obj)).reset();
        k = 0;
        i = k;
          goto _L3
        InterruptedException interruptedexception;
        interruptedexception;
        int j = i;
_L10:
        Thread.currentThread().interrupt();
        i = j;
          goto _L3
_L2:
        if(obj != null)
            CloseUtil.closeQuietly(((java.io.Closeable) (obj)));
        close();
        addInfo((new StringBuilder()).append(clientId).append("connection closed").toString());
_L4:
        return;
        SocketException socketexception;
        socketexception;
        Object obj1 = null;
_L9:
        addInfo((new StringBuilder()).append(clientId).append(socketexception).toString());
        if(obj1 != null)
            CloseUtil.closeQuietly(((java.io.Closeable) (obj1)));
        close();
        addInfo((new StringBuilder()).append(clientId).append("connection closed").toString());
          goto _L4
        IOException ioexception;
        ioexception;
        obj = null;
_L8:
        addError((new StringBuilder()).append(clientId).append(ioexception).toString());
        if(obj != null)
            CloseUtil.closeQuietly(((java.io.Closeable) (obj)));
        close();
        addInfo((new StringBuilder()).append(clientId).append("connection closed").toString());
          goto _L4
        RuntimeException runtimeexception;
        runtimeexception;
        obj = null;
_L7:
        addError((new StringBuilder()).append(clientId).append(runtimeexception).toString());
        if(obj != null)
            CloseUtil.closeQuietly(((java.io.Closeable) (obj)));
        close();
        addInfo((new StringBuilder()).append(clientId).append("connection closed").toString());
          goto _L4
        Exception exception;
        exception;
        obj = null;
_L6:
        if(obj != null)
            CloseUtil.closeQuietly(((java.io.Closeable) (obj)));
        close();
        addInfo((new StringBuilder()).append(clientId).append("connection closed").toString());
        throw exception;
        exception;
        continue; /* Loop/switch isn't completed */
        exception;
        obj = obj1;
        if(true) goto _L6; else goto _L5
_L5:
        runtimeexception;
          goto _L7
        ioexception;
          goto _L8
        socketexception;
        obj1 = obj;
          goto _L9
        InterruptedException interruptedexception1;
        interruptedexception1;
        j = 0;
          goto _L10
    }

    public void setQueue(BlockingQueue blockingqueue)
    {
        queue = blockingqueue;
    }

    private final String clientId;
    private final OutputStream outputStream;
    private BlockingQueue queue;
    private final Socket socket;
}
