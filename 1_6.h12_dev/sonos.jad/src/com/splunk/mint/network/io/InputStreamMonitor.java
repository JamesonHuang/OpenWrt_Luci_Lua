// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.io;

import com.splunk.mint.network.Counter;
import com.splunk.mint.network.MonitorRegistry;
import com.splunk.mint.network.socket.MonitoringSocketImpl;
import java.io.*;
import java.util.*;

public final class InputStreamMonitor extends InputStream
{

    public InputStreamMonitor(String s, MonitorRegistry monitorregistry, InputStream inputstream, MonitoringSocketImpl monitoringsocketimpl)
    {
        finishedReadingHeaders = false;
        statusCodeFound = false;
        contentLengthFound = false;
        headers = new HashMap(2);
        original = inputstream;
        counter = new Counter((new StringBuilder()).append(s).append("-bytes-in").toString());
        chars = new ArrayList();
        body = new StringBuffer();
        monSocIm = monitoringsocketimpl;
        finishedReadingHeaders = false;
        monitorregistry.add(counter);
    }

    private void updateBody()
    {
        byte abyte0[] = new byte[chars.size()];
        for(int i = 0; i < abyte0.length; i++)
            abyte0[i] = ((Byte)chars.get(i)).byteValue();

        chars.clear();
        body.append(new String(abyte0));
        if(body.toString().contains("\r\n\r\n"))
        {
            finishedReadingHeaders = true;
            tryToReadHeaders();
        }
    }

    public void close()
        throws IOException
    {
        original.close();
    }

    public HashMap getHeaders()
    {
        return headers;
    }

    public int read()
        throws IOException
    {
        int i = original.read();
        if(i > -1)
            counter.inc();
        if(!finishedReadingHeaders)
        {
            chars.add(Byte.valueOf((byte)i));
            updateBody();
        }
        return i;
    }

    public int read(byte abyte0[])
        throws IOException
    {
        int i = original.read(abyte0);
        if(i > -1)
            counter.inc(i);
        if(!finishedReadingHeaders)
        {
            for(int j = 0; j < abyte0.length; j++)
                chars.add(Byte.valueOf(abyte0[j]));

            updateBody();
        }
        return i;
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        int k = original.read(abyte0, i, j);
        if(k > -1)
            counter.inc(k);
        if(!finishedReadingHeaders)
        {
            for(int l = i; l < j; l++)
                chars.add(Byte.valueOf(abyte0[l]));

            updateBody();
        }
        return k;
    }

    public void tryToReadHeaders()
    {
        BufferedReader bufferedreader = new BufferedReader(new StringReader(body.toString()));
_L2:
        String s;
        int j;
        s = bufferedreader.readLine();
        if(s == null)
            break; /* Loop/switch isn't completed */
        if(statusCodeFound || !s.contains("HTTP/"))
            break MISSING_BLOCK_LABEL_105;
        j = s.length();
        if(j >= 90)
            break MISSING_BLOCK_LABEL_105;
        IOException ioexception;
        boolean flag;
        int i;
        String s1;
        boolean flag1;
        try
        {
            String as1[] = s.split(" ");
            String as2[] = new String[1];
            as2[0] = as1[1].trim();
            headers.put("splk-statuscode", Arrays.asList(as2));
            statusCodeFound = true;
        }
        catch(ArrayIndexOutOfBoundsException arrayindexoutofboundsexception1) { }
        if(contentLengthFound || !s.contains(":") || s.length() >= 90)
            continue; /* Loop/switch isn't completed */
        i = s.indexOf(":");
        if(i <= -1)
            continue; /* Loop/switch isn't completed */
        s1 = s.substring(0, i).trim();
        flag1 = s1.equals("Content-Length");
        if(!flag1)
            continue; /* Loop/switch isn't completed */
        try
        {
            String as[] = new String[1];
            as[0] = s.substring(i + 1, s.length()).trim();
            headers.put(s1, Arrays.asList(as));
            contentLengthFound = true;
        }
        catch(ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) { }
        if(!statusCodeFound) goto _L2; else goto _L1
_L1:
        flag = contentLengthFound;
        if(!flag) goto _L2; else goto _L3
_L3:
        if(monSocIm != null)
            monSocIm.readingDone();
        return;
        ioexception;
        ioexception.printStackTrace();
        if(true) goto _L3; else goto _L4
_L4:
    }

    private static final String IN_POSTFIX = "-bytes-in";
    private static final int MAX_POSSIBLE_HEADER_LENGTH = 90;
    private StringBuffer body;
    private List chars;
    boolean contentLengthFound;
    private final Counter counter;
    private boolean finishedReadingHeaders;
    HashMap headers;
    MonitoringSocketImpl monSocIm;
    private final InputStream original;
    boolean statusCodeFound;
}
