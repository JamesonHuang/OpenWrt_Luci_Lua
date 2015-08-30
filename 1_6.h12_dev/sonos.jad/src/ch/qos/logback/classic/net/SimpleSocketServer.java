// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import javax.net.ServerSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Referenced classes of package ch.qos.logback.classic.net:
//            SocketNode

public class SimpleSocketServer extends Thread
{

    public SimpleSocketServer(LoggerContext loggercontext, int i)
    {
        logger = LoggerFactory.getLogger(ch/qos/logback/classic/net/SimpleSocketServer);
        closed = false;
        socketNodeList = new ArrayList();
        lc = loggercontext;
        port = i;
    }

    public static void configureLC(LoggerContext loggercontext, String s)
        throws JoranException
    {
        JoranConfigurator joranconfigurator = new JoranConfigurator();
        loggercontext.reset();
        joranconfigurator.setContext(loggercontext);
        joranconfigurator.doConfigure(s);
    }

    protected static void doMain(Class class1, String as[])
        throws Exception
    {
        int i;
        String s;
        LoggerContext loggercontext;
        if(as.length == 2)
        {
            i = parsePortNumber(as[0]);
        } else
        {
            usage("Wrong number of arguments.");
            i = -1;
        }
        s = as[1];
        loggercontext = (LoggerContext)LoggerFactory.getILoggerFactory();
        configureLC(loggercontext, s);
        (new SimpleSocketServer(loggercontext, i)).start();
    }

    public static void main(String args[])
        throws Exception
    {
        doMain(ch/qos/logback/classic/net/SimpleSocketServer, args);
    }

    static int parsePortNumber(String s)
    {
        int j = Integer.parseInt(s);
        int i = j;
_L2:
        return i;
        NumberFormatException numberformatexception;
        numberformatexception;
        numberformatexception.printStackTrace();
        usage((new StringBuilder()).append("Could not interpret port number [").append(s).append("].").toString());
        i = -1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static void usage(String s)
    {
        System.err.println(s);
        System.err.println((new StringBuilder()).append("Usage: java ").append(ch/qos/logback/classic/net/SimpleSocketServer.getName()).append(" port configFile").toString());
        System.exit(1);
    }

    public void close()
    {
        closed = true;
        if(serverSocket == null)
            break MISSING_BLOCK_LABEL_24;
        serverSocket.close();
        serverSocket = null;
_L2:
        logger.info("closing this server");
        List list = socketNodeList;
        list;
        JVM INSTR monitorenter ;
        for(Iterator iterator = socketNodeList.iterator(); iterator.hasNext(); ((SocketNode)iterator.next()).close());
        break MISSING_BLOCK_LABEL_114;
        Exception exception;
        exception;
        throw exception;
        IOException ioexception;
        ioexception;
        logger.error("Failed to close serverSocket", ioexception);
        serverSocket = null;
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception1;
        exception1;
        serverSocket = null;
        throw exception1;
        list;
        JVM INSTR monitorexit ;
        if(socketNodeList.size() != 0)
            logger.warn("Was expecting a 0-sized socketNodeList after server shutdown");
        return;
    }

    protected String getClientThreadName(Socket socket)
    {
        Object aobj[] = new Object[1];
        aobj[0] = socket.getRemoteSocketAddress();
        return String.format("Logback SocketNode (client: %s)", aobj);
    }

    public CountDownLatch getLatch()
    {
        return latch;
    }

    protected ServerSocketFactory getServerSocketFactory()
    {
        return ServerSocketFactory.getDefault();
    }

    protected String getServerThreadName()
    {
        Object aobj[] = new Object[2];
        aobj[0] = getClass().getSimpleName();
        aobj[1] = Integer.valueOf(port);
        return String.format("Logback %s (port %d)", aobj);
    }

    public boolean isClosed()
    {
        return closed;
    }

    public void run()
    {
        String s = Thread.currentThread().getName();
        String s1 = getServerThreadName();
        Thread.currentThread().setName(s1);
        logger.info((new StringBuilder()).append("Listening on port ").append(port).toString());
        serverSocket = getServerSocketFactory().createServerSocket(port);
_L3:
        if(closed) goto _L2; else goto _L1
_L1:
        Socket socket;
        SocketNode socketnode;
        logger.info("Waiting to accept a new client.");
        signalAlmostReadiness();
        socket = serverSocket.accept();
        logger.info((new StringBuilder()).append("Connected to client at ").append(socket.getInetAddress()).toString());
        logger.info("Starting new socket node.");
        socketnode = new SocketNode(this, socket, lc);
        synchronized(socketNodeList)
        {
            socketNodeList.add(socketnode);
        }
        (new Thread(socketnode, getClientThreadName(socket))).start();
          goto _L3
        Exception exception1;
        exception1;
        if(!closed) goto _L5; else goto _L4
_L4:
        logger.info("Exception in run method for a closed server. This is normal.");
_L7:
        Thread.currentThread().setName(s);
_L6:
        return;
        exception2;
        list;
        JVM INSTR monitorexit ;
        throw exception2;
        Exception exception;
        exception;
        Thread.currentThread().setName(s);
        throw exception;
_L2:
        Thread.currentThread().setName(s);
        if(true) goto _L6; else goto _L5
_L5:
        logger.error("Unexpected failure in run method", exception1);
          goto _L7
    }

    void setLatch(CountDownLatch countdownlatch)
    {
        latch = countdownlatch;
    }

    void signalAlmostReadiness()
    {
        if(latch != null && latch.getCount() != 0L)
            latch.countDown();
    }

    public void socketNodeClosing(SocketNode socketnode)
    {
        logger.debug("Removing {}", socketnode);
        List list = socketNodeList;
        list;
        JVM INSTR monitorenter ;
        socketNodeList.remove(socketnode);
        return;
    }

    private boolean closed;
    private CountDownLatch latch;
    private final LoggerContext lc;
    Logger logger;
    private final int port;
    private ServerSocket serverSocket;
    private List socketNodeList;
}
