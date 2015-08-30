// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import java.io.*;

public final class ConsoleTarget extends Enum
{

    private ConsoleTarget(String s, int i, String s1, OutputStream outputstream)
    {
        super(s, i);
        name = s1;
        stream = outputstream;
    }

    public static ConsoleTarget findByName(String s)
    {
        ConsoleTarget aconsoletarget[];
        int i;
        int j;
        aconsoletarget = values();
        i = aconsoletarget.length;
        j = 0;
_L3:
        ConsoleTarget consoletarget;
        if(j >= i)
            break MISSING_BLOCK_LABEL_40;
        consoletarget = aconsoletarget[j];
        if(!consoletarget.name.equalsIgnoreCase(s)) goto _L2; else goto _L1
_L1:
        return consoletarget;
_L2:
        j++;
          goto _L3
        consoletarget = null;
          goto _L1
    }

    public static ConsoleTarget valueOf(String s)
    {
        return (ConsoleTarget)Enum.valueOf(ch/qos/logback/core/joran/spi/ConsoleTarget, s);
    }

    public static ConsoleTarget[] values()
    {
        return (ConsoleTarget[])$VALUES.clone();
    }

    public String getName()
    {
        return name;
    }

    public OutputStream getStream()
    {
        return stream;
    }

    private static final ConsoleTarget $VALUES[];
    public static final ConsoleTarget SystemErr;
    public static final ConsoleTarget SystemOut;
    private final String name;
    private final OutputStream stream;

    static 
    {
        SystemOut = new ConsoleTarget("SystemOut", 0, "System.out", new OutputStream() {

            public void flush()
                throws IOException
            {
                System.out.flush();
            }

            public void write(int i)
                throws IOException
            {
                System.out.write(i);
            }

            public void write(byte abyte0[])
                throws IOException
            {
                System.out.write(abyte0);
            }

            public void write(byte abyte0[], int i, int j)
                throws IOException
            {
                System.out.write(abyte0, i, j);
            }

        }
);
        SystemErr = new ConsoleTarget("SystemErr", 1, "System.err", new OutputStream() {

            public void flush()
                throws IOException
            {
                System.err.flush();
            }

            public void write(int i)
                throws IOException
            {
                System.err.write(i);
            }

            public void write(byte abyte0[])
                throws IOException
            {
                System.err.write(abyte0);
            }

            public void write(byte abyte0[], int i, int j)
                throws IOException
            {
                System.err.write(abyte0, i, j);
            }

        }
);
        ConsoleTarget aconsoletarget[] = new ConsoleTarget[2];
        aconsoletarget[0] = SystemOut;
        aconsoletarget[1] = SystemErr;
        $VALUES = aconsoletarget;
    }
}
