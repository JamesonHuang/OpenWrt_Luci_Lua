// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.repackage.com.mindprod.ledatastream;

import java.io.*;

public final class LEDataInputStream
    implements DataInput
{

    public LEDataInputStream(InputStream inputstream)
    {
        is = inputstream;
        dis = new DataInputStream(inputstream);
    }

    public static String readUTF(DataInput datainput)
        throws IOException
    {
        return DataInputStream.readUTF(datainput);
    }

    public final void close()
        throws IOException
    {
        dis.close();
    }

    public final int read(byte abyte0[], int i, int j)
        throws IOException
    {
        return is.read(abyte0, i, j);
    }

    public final boolean readBoolean()
        throws IOException
    {
        return dis.readBoolean();
    }

    public final byte readByte()
        throws IOException
    {
        return dis.readByte();
    }

    public final char readChar()
        throws IOException
    {
        dis.readFully(work, 0, 2);
        return (char)((0xff & work[1]) << 8 | 0xff & work[0]);
    }

    public final double readDouble()
        throws IOException
    {
        return Double.longBitsToDouble(readLong());
    }

    public final float readFloat()
        throws IOException
    {
        return Float.intBitsToFloat(readInt());
    }

    public final void readFully(byte abyte0[])
        throws IOException
    {
        dis.readFully(abyte0, 0, abyte0.length);
    }

    public final void readFully(byte abyte0[], int i, int j)
        throws IOException
    {
        dis.readFully(abyte0, i, j);
    }

    public final int readInt()
        throws IOException
    {
        dis.readFully(work, 0, 4);
        return work[3] << 24 | (0xff & work[2]) << 16 | (0xff & work[1]) << 8 | 0xff & work[0];
    }

    public final String readLine()
        throws IOException
    {
        return dis.readLine();
    }

    public final long readLong()
        throws IOException
    {
        dis.readFully(work, 0, 8);
        return (long)work[7] << 56 | (long)(0xff & work[6]) << 48 | (long)(0xff & work[5]) << 40 | (long)(0xff & work[4]) << 32 | (long)(0xff & work[3]) << 24 | (long)(0xff & work[2]) << 16 | (long)(0xff & work[1]) << 8 | (long)(0xff & work[0]);
    }

    public final short readShort()
        throws IOException
    {
        dis.readFully(work, 0, 2);
        return (short)((0xff & work[1]) << 8 | 0xff & work[0]);
    }

    public final String readUTF()
        throws IOException
    {
        return dis.readUTF();
    }

    public final int readUnsignedByte()
        throws IOException
    {
        return dis.readUnsignedByte();
    }

    public final int readUnsignedShort()
        throws IOException
    {
        dis.readFully(work, 0, 2);
        return (0xff & work[1]) << 8 | 0xff & work[0];
    }

    public final int skipBytes(int i)
        throws IOException
    {
        return dis.skipBytes(i);
    }

    private static final String EMBEDDED_COPYRIGHT = "copyright (c) 1999-2010 Roedy Green, Canadian Mind Products, http://mindprod.com";
    protected final DataInputStream dis;
    protected final InputStream is;
    protected final byte work[] = new byte[8];
}
