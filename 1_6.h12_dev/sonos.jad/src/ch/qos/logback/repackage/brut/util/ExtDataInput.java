// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.repackage.brut.util;

import java.io.*;

// Referenced classes of package ch.qos.logback.repackage.brut.util:
//            DataInputDelegate

public class ExtDataInput extends DataInputDelegate
{

    public ExtDataInput(DataInput datainput)
    {
        super(datainput);
    }

    public ExtDataInput(InputStream inputstream)
    {
        this(((DataInput) (new DataInputStream(inputstream))));
    }

    public int[] readIntArray(int i)
        throws IOException
    {
        int ai[] = new int[i];
        for(int j = 0; j < i; j++)
            ai[j] = readInt();

        return ai;
    }

    public String readNulEndedString(int i, boolean flag)
        throws IOException
    {
        StringBuilder stringbuilder = new StringBuilder(16);
        do
        {
            int j;
            short word0;
label0:
            {
                j = i - 1;
                if(i != 0)
                {
                    word0 = readShort();
                    if(word0 != 0)
                        break label0;
                }
                if(flag)
                    skipBytes(j * 2);
                return stringbuilder.toString();
            }
            stringbuilder.append((char)word0);
            i = j;
        } while(true);
    }

    public void skipCheckByte(byte byte0)
        throws IOException
    {
        byte byte1 = readByte();
        if(byte1 != byte0)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Byte.valueOf(byte0);
            aobj[1] = Byte.valueOf(byte1);
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", aobj));
        } else
        {
            return;
        }
    }

    public void skipCheckInt(int i)
        throws IOException
    {
        int j = readInt();
        if(j != i)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(i);
            aobj[1] = Integer.valueOf(j);
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", aobj));
        } else
        {
            return;
        }
    }

    public void skipCheckShort(short word0)
        throws IOException
    {
        short word1 = readShort();
        if(word1 != word0)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Short.valueOf(word0);
            aobj[1] = Short.valueOf(word1);
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", aobj));
        } else
        {
            return;
        }
    }

    public void skipInt()
        throws IOException
    {
        skipBytes(4);
    }
}
