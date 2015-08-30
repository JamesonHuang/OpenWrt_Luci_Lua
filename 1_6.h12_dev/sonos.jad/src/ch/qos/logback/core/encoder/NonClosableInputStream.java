// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.encoder;

import java.io.*;

public class NonClosableInputStream extends FilterInputStream
{

    NonClosableInputStream(InputStream inputstream)
    {
        super(inputstream);
    }

    public void close()
    {
    }

    public void realClose()
        throws IOException
    {
        super.close();
    }
}
