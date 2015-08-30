// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net;

import java.io.*;
import java.net.*;

public class SyslogOutputStream extends OutputStream
{

    public SyslogOutputStream(String s, int i)
        throws UnknownHostException, SocketException
    {
        baos = new ByteArrayOutputStream();
        address = InetAddress.getByName(s);
        port = i;
        ds = new DatagramSocket();
    }

    public void close()
    {
        address = null;
        ds = null;
    }

    public void flush()
        throws IOException
    {
        DatagramPacket datagrampacket;
        byte abyte0[] = baos.toByteArray();
        datagrampacket = new DatagramPacket(abyte0, abyte0.length, address, port);
        if(baos.size() > 1024)
            baos = new ByteArrayOutputStream();
        else
            baos.reset();
        break MISSING_BLOCK_LABEL_51;
        if(abyte0.length != 0 && ds != null)
            ds.send(datagrampacket);
        return;
    }

    public int getPort()
    {
        return port;
    }

    int getSendBufferSize()
        throws SocketException
    {
        return ds.getSendBufferSize();
    }

    public void write(int i)
        throws IOException
    {
        baos.write(i);
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        baos.write(abyte0, i, j);
    }

    private static final int MAX_LEN = 1024;
    private InetAddress address;
    private ByteArrayOutputStream baos;
    private DatagramSocket ds;
    private final int port;
}
