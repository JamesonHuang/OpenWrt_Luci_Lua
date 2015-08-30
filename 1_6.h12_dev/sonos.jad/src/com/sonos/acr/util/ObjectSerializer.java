// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import java.io.*;

public class ObjectSerializer
{

    public ObjectSerializer()
    {
    }

    public static byte[] decodeBytes(String s)
    {
        byte abyte0[] = new byte[s.length() / 2];
        for(int i = 0; i < s.length(); i += 2)
        {
            char c = s.charAt(i);
            abyte0[i / 2] = (byte)(c + -97 << 4);
            char c1 = s.charAt(i + 1);
            int j = i / 2;
            abyte0[j] = (byte)(abyte0[j] + (c1 + -97));
        }

        return abyte0;
    }

    public static Object deserialize(String s)
    {
        Object obj;
        if(s == null || s.length() == 0)
        {
            obj = null;
        } else
        {
            Object obj1;
            try
            {
                obj1 = (new ObjectInputStream(new ByteArrayInputStream(decodeBytes(s)))).readObject();
            }
            catch(Exception exception)
            {
                throw new RuntimeException("Serialization Error", exception);
            }
            obj = obj1;
        }
        return obj;
    }

    public static String encodeBytes(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; i < abyte0.length; i++)
        {
            stringbuffer.append((char)(97 + (0xf & abyte0[i] >> 4)));
            stringbuffer.append((char)(97 + (0xf & abyte0[i])));
        }

        return stringbuffer.toString();
    }

    public static String serialize(Serializable serializable)
    {
        String s1;
        if(serializable == null)
        {
            s1 = "";
        } else
        {
            String s;
            try
            {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                ObjectOutputStream objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
                objectoutputstream.writeObject(serializable);
                objectoutputstream.close();
                s = encodeBytes(bytearrayoutputstream.toByteArray());
            }
            catch(Exception exception)
            {
                throw new RuntimeException("Serialization Error", exception);
            }
            s1 = s;
        }
        return s1;
    }
}
