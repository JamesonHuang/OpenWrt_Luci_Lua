// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.recovery;

import java.io.*;
import java.nio.channels.FileChannel;

// Referenced classes of package ch.qos.logback.core.recovery:
//            ResilientOutputStreamBase

public class ResilientFileOutputStream extends ResilientOutputStreamBase
{

    public ResilientFileOutputStream(File file1, boolean flag)
        throws FileNotFoundException
    {
        file = file1;
        fos = new FileOutputStream(file1, flag);
        os = new BufferedOutputStream(fos);
        presumedClean = true;
    }

    public FileChannel getChannel()
    {
        FileChannel filechannel;
        if(os == null)
            filechannel = null;
        else
            filechannel = fos.getChannel();
        return filechannel;
    }

    String getDescription()
    {
        return (new StringBuilder()).append("file [").append(file).append("]").toString();
    }

    public File getFile()
    {
        return file;
    }

    OutputStream openNewOutputStream()
        throws IOException
    {
        fos = new FileOutputStream(file, true);
        return new BufferedOutputStream(fos);
    }

    public String toString()
    {
        return (new StringBuilder()).append("c.q.l.c.recovery.ResilientFileOutputStream@").append(System.identityHashCode(this)).toString();
    }

    private File file;
    private FileOutputStream fos;
}
