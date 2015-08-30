// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net.server;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.util.CloseUtil;
import java.io.*;
import java.net.Socket;

// Referenced classes of package ch.qos.logback.classic.net.server:
//            RemoteAppenderClient

class RemoteAppenderStreamClient
    implements RemoteAppenderClient
{

    public RemoteAppenderStreamClient(String s, InputStream inputstream)
    {
        id = s;
        socket = null;
        inputStream = inputstream;
    }

    public RemoteAppenderStreamClient(String s, Socket socket1)
    {
        id = s;
        socket = socket1;
        inputStream = null;
    }

    private ObjectInputStream createObjectInputStream()
        throws IOException
    {
        ObjectInputStream objectinputstream;
        if(inputStream != null)
            objectinputstream = new ObjectInputStream(inputStream);
        else
            objectinputstream = new ObjectInputStream(socket.getInputStream());
        return objectinputstream;
    }

    public void close()
    {
        if(socket != null)
            CloseUtil.closeQuietly(socket);
    }

    public void run()
    {
        Object obj;
        logger.info((new StringBuilder()).append(this).append(": connected").toString());
        obj = null;
        ObjectInputStream objectinputstream1 = createObjectInputStream();
        ObjectInputStream objectinputstream = objectinputstream1;
        do
        {
            ILoggingEvent iloggingevent;
            Logger logger1;
            do
            {
                iloggingevent = (ILoggingEvent)objectinputstream.readObject();
                logger1 = lc.getLogger(iloggingevent.getLoggerName());
            } while(!logger1.isEnabledFor(iloggingevent.getLevel()));
            logger1.callAppenders(iloggingevent);
        } while(true);
        EOFException eofexception1;
        eofexception1;
        obj = objectinputstream;
_L8:
        if(obj != null)
            CloseUtil.closeQuietly(((java.io.Closeable) (obj)));
        close();
        logger.info((new StringBuilder()).append(this).append(": connection closed").toString());
_L1:
        return;
        IOException ioexception;
        ioexception;
        IOException ioexception1;
        objectinputstream = null;
        ioexception1 = ioexception;
_L6:
        logger.info((new StringBuilder()).append(this).append(": ").append(ioexception1).toString());
        if(objectinputstream != null)
            CloseUtil.closeQuietly(objectinputstream);
        close();
        logger.info((new StringBuilder()).append(this).append(": connection closed").toString());
          goto _L1
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        objectinputstream = null;
_L5:
        logger.error((new StringBuilder()).append(this).append(": unknown event class").toString());
        if(objectinputstream != null)
            CloseUtil.closeQuietly(objectinputstream);
        close();
        logger.info((new StringBuilder()).append(this).append(": connection closed").toString());
          goto _L1
        RuntimeException runtimeexception;
        runtimeexception;
        RuntimeException runtimeexception1;
        objectinputstream = null;
        runtimeexception1 = runtimeexception;
_L4:
        logger.error((new StringBuilder()).append(this).append(": ").append(runtimeexception1).toString());
        if(objectinputstream != null)
            CloseUtil.closeQuietly(objectinputstream);
        close();
        logger.info((new StringBuilder()).append(this).append(": connection closed").toString());
          goto _L1
        Exception exception;
        exception;
        Exception exception1;
        objectinputstream = null;
        exception1 = exception;
_L3:
        if(objectinputstream != null)
            CloseUtil.closeQuietly(objectinputstream);
        close();
        logger.info((new StringBuilder()).append(this).append(": connection closed").toString());
        throw exception1;
        exception1;
        if(true) goto _L3; else goto _L2
_L2:
        runtimeexception1;
          goto _L4
        ClassNotFoundException classnotfoundexception1;
        classnotfoundexception1;
          goto _L5
        ioexception1;
          goto _L6
        EOFException eofexception;
        eofexception;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void setLoggerContext(LoggerContext loggercontext)
    {
        lc = loggercontext;
        logger = loggercontext.getLogger(getClass().getPackage().getName());
    }

    public String toString()
    {
        return (new StringBuilder()).append("client ").append(id).toString();
    }

    private final String id;
    private final InputStream inputStream;
    private LoggerContext lc;
    private Logger logger;
    private final Socket socket;
}
