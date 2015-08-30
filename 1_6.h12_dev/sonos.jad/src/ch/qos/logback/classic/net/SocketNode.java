// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.io.*;
import java.net.*;

// Referenced classes of package ch.qos.logback.classic.net:
//            SimpleSocketServer

public class SocketNode
    implements Runnable
{

    public SocketNode(SimpleSocketServer simplesocketserver, Socket socket1, LoggerContext loggercontext)
    {
        closed = false;
        socketServer = simplesocketserver;
        socket = socket1;
        remoteSocketAddress = socket1.getRemoteSocketAddress();
        context = loggercontext;
        logger = loggercontext.getLogger(ch/qos/logback/classic/net/SocketNode);
    }

    void close()
    {
        if(!closed) goto _L2; else goto _L1
_L1:
        return;
_L2:
        closed = true;
        if(ois == null) goto _L1; else goto _L3
_L3:
        ois.close();
        ois = null;
          goto _L1
        IOException ioexception;
        ioexception;
        logger.warn("Could not close connection.", ioexception);
        ois = null;
          goto _L1
        Exception exception;
        exception;
        ois = null;
        throw exception;
    }

    public void run()
    {
        try
        {
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        }
        catch(Exception exception)
        {
            logger.error((new StringBuilder()).append("Could not open ObjectInputStream to ").append(socket).toString(), exception);
            closed = true;
        }
        try
        {
            do
            {
                if(closed)
                    break;
                ILoggingEvent iloggingevent = (ILoggingEvent)ois.readObject();
                Logger logger1 = context.getLogger(iloggingevent.getLoggerName());
                if(logger1.isEnabledFor(iloggingevent.getLevel()))
                    logger1.callAppenders(iloggingevent);
            } while(true);
        }
        catch(EOFException eofexception)
        {
            logger.info("Caught java.io.EOFException closing connection.");
        }
        catch(SocketException socketexception)
        {
            logger.info("Caught java.net.SocketException closing connection.");
        }
        catch(IOException ioexception)
        {
            logger.info((new StringBuilder()).append("Caught java.io.IOException: ").append(ioexception).toString());
            logger.info("Closing connection.");
        }
        catch(Exception exception1)
        {
            logger.error("Unexpected exception. Closing connection.", exception1);
        }
        socketServer.socketNodeClosing(this);
        close();
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append(remoteSocketAddress.toString()).toString();
    }

    boolean closed;
    LoggerContext context;
    Logger logger;
    ObjectInputStream ois;
    SocketAddress remoteSocketAddress;
    Socket socket;
    SimpleSocketServer socketServer;
}
