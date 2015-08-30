// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.encoder;

import java.io.*;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.encoder:
//            EncoderBase, ByteArrayUtil

public class ObjectStreamEncoder extends EncoderBase
{

    public ObjectStreamEncoder()
    {
        MAX_BUFFER_SIZE = 100;
        bufferList = new ArrayList(MAX_BUFFER_SIZE);
    }

    public void close()
        throws IOException
    {
        writeBuffer();
    }

    public void doEncode(Object obj)
        throws IOException
    {
        bufferList.add(obj);
        if(bufferList.size() == MAX_BUFFER_SIZE)
            writeBuffer();
    }

    public void init(OutputStream outputstream)
        throws IOException
    {
        super.init(outputstream);
        bufferList.clear();
    }

    void writeBuffer()
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(10000);
        int i = bufferList.size();
        writeHeader(bytearrayoutputstream, i);
        ObjectOutputStream objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
        for(Iterator iterator = bufferList.iterator(); iterator.hasNext(); objectoutputstream.writeObject(iterator.next()));
        bufferList.clear();
        objectoutputstream.flush();
        writeFooter(bytearrayoutputstream, i);
        byte abyte0[] = bytearrayoutputstream.toByteArray();
        objectoutputstream.close();
        writeEndPosition(abyte0);
        outputStream.write(abyte0);
    }

    void writeEndPosition(byte abyte0[])
    {
        ByteArrayUtil.writeInt(abyte0, 8, abyte0.length - 8);
    }

    void writeFooter(ByteArrayOutputStream bytearrayoutputstream, int i)
    {
        ByteArrayUtil.writeInt(bytearrayoutputstream, 0x262b5373);
        ByteArrayUtil.writeInt(bytearrayoutputstream, 0x262b5373 ^ i);
    }

    void writeHeader(ByteArrayOutputStream bytearrayoutputstream, int i)
    {
        ByteArrayUtil.writeInt(bytearrayoutputstream, 0x6e78f671);
        ByteArrayUtil.writeInt(bytearrayoutputstream, i);
        ByteArrayUtil.writeInt(bytearrayoutputstream, 0);
        ByteArrayUtil.writeInt(bytearrayoutputstream, 0x6e78f671 ^ i);
    }

    public static final int START_PEBBLE = 0x6e78f671;
    public static final int STOP_PEBBLE = 0x262b5373;
    private int MAX_BUFFER_SIZE;
    List bufferList;
}
