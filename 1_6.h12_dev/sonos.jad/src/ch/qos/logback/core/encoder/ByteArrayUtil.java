// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.encoder;

import java.io.ByteArrayOutputStream;

public class ByteArrayUtil
{

    public ByteArrayUtil()
    {
    }

    public static byte[] hexStringToByteArray(String s)
    {
        byte abyte0[] = new byte[s.length() / 2];
        for(int i = 0; i < abyte0.length; i++)
        {
            int j = i * 2;
            abyte0[i] = (byte)(0xff & Integer.parseInt(s.substring(j, j + 2), 16));
        }

        return abyte0;
    }

    static int readInt(byte abyte0[], int i)
    {
        int j = 0;
        int k = 0;
        for(; j < 4; j++)
        {
            int l = 24 - j * 8;
            k += (0xff & abyte0[i + j]) << l;
        }

        return k;
    }

    public static String toHexString(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = abyte0.length;
        for(int j = 0; j < i; j++)
        {
            String s = Integer.toHexString(0xff & abyte0[j]);
            if(s.length() == 1)
                stringbuffer.append('0');
            stringbuffer.append(s);
        }

        return stringbuffer.toString();
    }

    static void writeInt(ByteArrayOutputStream bytearrayoutputstream, int i)
    {
        for(int j = 0; j < 4; j++)
            bytearrayoutputstream.write((byte)(i >>> 24 - j * 8));

    }

    static void writeInt(byte abyte0[], int i, int j)
    {
        for(int k = 0; k < 4; k++)
        {
            int l = 24 - k * 8;
            abyte0[i + k] = (byte)(j >>> l);
        }

    }
}
