// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.encoder:
//            NonClosableInputStream, ByteArrayUtil

public class EventObjectInputStream extends InputStream
{

    EventObjectInputStream(InputStream inputstream)
        throws IOException
    {
        buffer = new ArrayList();
        index = 0;
        ncis = new NonClosableInputStream(inputstream);
    }

    private void internalReset()
    {
        index = 0;
        buffer.clear();
    }

    public int available()
        throws IOException
    {
        return ncis.available();
    }

    public void close()
        throws IOException
    {
        ncis.realClose();
    }

    Object getFromBuffer()
    {
        Object obj;
        if(index >= buffer.size())
        {
            obj = null;
        } else
        {
            List list = buffer;
            int i = index;
            index = i + 1;
            obj = list.get(i);
        }
        return obj;
    }

    public int read()
        throws IOException
    {
        throw new UnsupportedOperationException("Only the readEvent method is supported.");
    }

    public Object readEvent()
        throws IOException
    {
        Object obj = getFromBuffer();
        if(obj == null)
        {
            internalReset();
            int i = readHeader();
            if(i == -1)
            {
                obj = null;
            } else
            {
                readPayload(i);
                readFooter(i);
                obj = getFromBuffer();
            }
        }
        return obj;
    }

    Object readEvents(ObjectInputStream objectinputstream)
        throws IOException
    {
        Object obj1 = objectinputstream.readObject();
        Object obj = obj1;
        buffer.add(obj);
_L2:
        return obj;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        ClassNotFoundException classnotfoundexception1;
        obj = null;
        classnotfoundexception1 = classnotfoundexception;
_L3:
        classnotfoundexception1.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
        classnotfoundexception1;
          goto _L3
    }

    void readFooter(int i)
        throws IOException
    {
        byte abyte0[] = new byte[8];
        if(ncis.read(abyte0) == -1)
            throw new IllegalStateException("Looks like a corrupt stream");
        if(ByteArrayUtil.readInt(abyte0, 0) != 0x262b5373)
            throw new IllegalStateException("Looks like a corrupt stream");
        if(ByteArrayUtil.readInt(abyte0, 4) != (0x262b5373 ^ i))
            throw new IllegalStateException("Invalid checksum");
        else
            return;
    }

    int readHeader()
        throws IOException
    {
        int i = -1;
        byte abyte0[] = new byte[16];
        if(ncis.read(abyte0) != i)
        {
            if(ByteArrayUtil.readInt(abyte0, 0) != 0x6e78f671)
                throw new IllegalStateException("Does not look like data created by ObjectStreamEncoder");
            i = ByteArrayUtil.readInt(abyte0, 4);
            if(ByteArrayUtil.readInt(abyte0, 12) != (0x6e78f671 ^ i))
                throw new IllegalStateException("Invalid checksum");
        }
        return i;
    }

    void readPayload(int i)
        throws IOException
    {
        ArrayList arraylist = new ArrayList(i);
        ObjectInputStream objectinputstream = new ObjectInputStream(ncis);
        for(int j = 0; j < i; j++)
            arraylist.add(readEvents(objectinputstream));

        objectinputstream.close();
    }

    List buffer;
    int index;
    NonClosableInputStream ncis;
}
