// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.recovery.ResilientFileOutputStream;
import ch.qos.logback.core.util.EnvUtil;
import ch.qos.logback.core.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

// Referenced classes of package ch.qos.logback.core:
//            OutputStreamAppender, Context, NOPOutputStream

public class FileAppender extends OutputStreamAppender
{

    public FileAppender()
    {
        append = true;
        fileName = null;
        prudent = false;
        initialized = false;
        lazyInit = false;
    }

    private String getAbsoluteFilePath(String s)
    {
        if(EnvUtil.isAndroidOS())
            s = FileUtil.prefixRelativePath(context.getProperty("DATA_DIR"), s);
        return s;
    }

    private void safeWrite(Object obj)
        throws IOException
    {
        FileChannel filechannel = ((ResilientFileOutputStream)getOutputStream()).getChannel();
        if(filechannel != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        FileLock filelock = null;
        filelock = filechannel.lock();
        long l = filechannel.position();
        long l1 = filechannel.size();
        if(l1 != l)
            filechannel.position(l1);
        super.writeOut(obj);
        if(filelock != null)
            filelock.release();
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        if(filelock != null)
            filelock.release();
        throw exception;
    }

    public String getFile()
    {
        return fileName;
    }

    public boolean getLazy()
    {
        return lazyInit;
    }

    public boolean isAppend()
    {
        return append;
    }

    public boolean isPrudent()
    {
        return prudent;
    }

    protected boolean openFile(String s)
        throws IOException
    {
        String s1 = getAbsoluteFilePath(s);
        ch.qos.logback.core.spi.LogbackLock logbacklock = lock;
        logbacklock;
        JVM INSTR monitorenter ;
        File file = new File(s1);
        if(FileUtil.isParentDirectoryCreationRequired(file) && !FileUtil.createMissingParentDirectories(file))
            addError((new StringBuilder()).append("Failed to create parent directories for [").append(file.getAbsolutePath()).append("]").toString());
        ResilientFileOutputStream resilientfileoutputstream = new ResilientFileOutputStream(file, append);
        resilientfileoutputstream.setContext(context);
        setOutputStream(resilientfileoutputstream);
        return true;
    }

    public final String rawFileProperty()
    {
        return fileName;
    }

    public void setAppend(boolean flag)
    {
        append = flag;
    }

    public void setFile(String s)
    {
        if(s == null)
            fileName = null;
        else
            fileName = s.trim();
    }

    public void setLazy(boolean flag)
    {
        lazyInit = flag;
    }

    public void setPrudent(boolean flag)
    {
        prudent = flag;
    }

    public void start()
    {
        boolean flag = false;
        String s = getFile();
        if(s != null)
        {
            String s1 = getAbsoluteFilePath(s);
            addInfo((new StringBuilder()).append("File property is set to [").append(s1).append("]").toString());
            if(prudent && !isAppend())
            {
                setAppend(true);
                addWarn("Setting \"Append\" property to true on account of \"Prudent\" mode");
            }
            if(!lazyInit)
                try
                {
                    openFile(s1);
                }
                catch(IOException ioexception)
                {
                    addError((new StringBuilder()).append("openFile(").append(s1).append(",").append(append).append(") failed").toString(), ioexception);
                    flag = true;
                }
            else
                setOutputStream(new NOPOutputStream());
        } else
        {
            addError((new StringBuilder()).append("\"File\" property not set for appender named [").append(name).append("]").toString());
            flag = true;
        }
        if(!flag)
            super.start();
    }

    protected void subAppend(Object obj)
    {
        if(!initialized && lazyInit)
        {
            initialized = true;
            try
            {
                openFile(getFile());
            }
            catch(IOException ioexception)
            {
                started = false;
                addError((new StringBuilder()).append("openFile(").append(fileName).append(",").append(append).append(") failed").toString(), ioexception);
            }
        }
        super.subAppend(obj);
    }

    protected void writeOut(Object obj)
        throws IOException
    {
        if(prudent)
            safeWrite(obj);
        else
            super.writeOut(obj);
    }

    protected boolean append;
    protected String fileName;
    private boolean initialized;
    private boolean lazyInit;
    private boolean prudent;
}
