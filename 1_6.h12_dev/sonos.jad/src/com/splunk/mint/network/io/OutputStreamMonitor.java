// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.io;

import com.splunk.mint.network.Counter;
import com.splunk.mint.network.MonitorRegistry;
import java.io.*;
import java.util.*;

public final class OutputStreamMonitor extends OutputStream
{

    public OutputStreamMonitor(String s, MonitorRegistry monitorregistry, OutputStream outputstream)
    {
        original = outputstream;
        counter = new Counter((new StringBuilder()).append(s).append("-bytes-out").toString());
        monitorregistry.add(counter);
        chars = new ArrayList();
        body = new StringBuffer();
    }

    private void updateBody()
    {
        byte abyte0[] = new byte[chars.size()];
        for(int i = 0; i < abyte0.length; i++)
            abyte0[i] = ((Byte)chars.get(i)).byteValue();

        chars.clear();
        body.append(new String(abyte0));
    }

    public void close()
        throws IOException
    {
        original.close();
    }

    public HashMap getHeaders()
    {
        HashMap hashmap = new HashMap(1);
        if(body == null || body.toString() == null || body.toString().length() <= 50) goto _L2; else goto _L1
_L1:
        BufferedReader bufferedreader;
        boolean flag;
        boolean flag1;
        bufferedreader = new BufferedReader(new StringReader(body.toString()));
        flag = false;
        flag1 = false;
_L3:
        String s = bufferedreader.readLine();
        if(s == null)
            break; /* Loop/switch isn't completed */
        if(!flag && s.contains(":") && s.length() < 50)
        {
            int i = s.indexOf(":");
            if(i > -1)
            {
                String s1 = s.substring(0, i).trim();
                if(s1.equals("Host"))
                {
                    String as2[] = new String[1];
                    as2[0] = s.substring(i + 1, s.length()).trim();
                    hashmap.put(s1, Arrays.asList(as2));
                    flag = true;
                }
            }
        }
        if(flag1 || !s.contains("POST") && !s.contains("GET"))
            continue; /* Loop/switch isn't completed */
        String as[] = s.split(" ");
        String as1[] = new String[1];
        as1[0] = as[1].trim();
        hashmap.put("splk-host2", Arrays.asList(as1));
        flag1 = true;
        if(!flag || !flag1) goto _L3; else goto _L2
_L2:
        return hashmap;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        if(true) goto _L2; else goto _L4
_L4:
    }

    public void write(int i)
        throws IOException
    {
        counter.inc();
        original.write(i);
        chars.add(Byte.valueOf((byte)i));
        updateBody();
    }

    public void write(byte abyte0[])
        throws IOException
    {
        counter.inc(abyte0.length);
        original.write(abyte0);
        for(int i = 0; i < abyte0.length; i++)
            chars.add(Byte.valueOf(abyte0[i]));

        updateBody();
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        counter.inc(j);
        original.write(abyte0, i, j);
        for(int k = i; k < j; k++)
            chars.add(Byte.valueOf(abyte0[k]));

        updateBody();
    }

    private static final int MAX_POSSIBLE_HEADER_LENGTH = 50;
    public static final String OUT_POSTFIX = "-bytes-out";
    private StringBuffer body;
    private List chars;
    private final Counter counter;
    private final OutputStream original;
}
