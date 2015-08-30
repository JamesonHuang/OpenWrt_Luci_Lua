// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.recovery;

import ch.qos.logback.core.net.SyslogOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;

// Referenced classes of package ch.qos.logback.core.recovery:
//            ResilientOutputStreamBase

public class ResilientSyslogOutputStream extends ResilientOutputStreamBase
{

    public ResilientSyslogOutputStream(String s, int i)
        throws UnknownHostException, SocketException
    {
        syslogHost = s;
        port = i;
        super.os = new SyslogOutputStream(s, i);
        presumedClean = true;
    }

    String getDescription()
    {
        return (new StringBuilder()).append("syslog [").append(syslogHost).append(":").append(port).append("]").toString();
    }

    OutputStream openNewOutputStream()
        throws IOException
    {
        return new SyslogOutputStream(syslogHost, port);
    }

    public String toString()
    {
        return (new StringBuilder()).append("c.q.l.c.recovery.ResilientSyslogOutputStream@").append(System.identityHashCode(this)).toString();
    }

    int port;
    String syslogHost;
}
