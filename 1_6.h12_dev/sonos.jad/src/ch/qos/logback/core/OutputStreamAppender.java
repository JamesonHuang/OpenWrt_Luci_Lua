// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.spi.DeferredProcessingAware;
import ch.qos.logback.core.spi.LogbackLock;
import ch.qos.logback.core.status.ErrorStatus;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package ch.qos.logback.core:
//            UnsynchronizedAppenderBase, Layout

public class OutputStreamAppender extends UnsynchronizedAppenderBase
{

    public OutputStreamAppender()
    {
        lock = new LogbackLock();
    }

    protected void append(Object obj)
    {
        if(isStarted())
            subAppend(obj);
    }

    protected void closeOutputStream()
    {
        if(outputStream == null)
            break MISSING_BLOCK_LABEL_23;
        encoderClose();
        outputStream.close();
        outputStream = null;
_L1:
        return;
        IOException ioexception;
        ioexception;
        addStatus(new ErrorStatus("Could not close output stream for OutputStreamAppender.", this, ioexception));
          goto _L1
    }

    void encoderClose()
    {
        if(encoder == null || outputStream == null)
            break MISSING_BLOCK_LABEL_23;
        encoder.close();
_L1:
        return;
        IOException ioexception;
        ioexception;
        started = false;
        addStatus(new ErrorStatus((new StringBuilder()).append("Failed to write footer for appender named [").append(name).append("].").toString(), this, ioexception));
          goto _L1
    }

    void encoderInit()
    {
        if(encoder == null || outputStream == null)
            break MISSING_BLOCK_LABEL_27;
        encoder.init(outputStream);
_L1:
        return;
        IOException ioexception;
        ioexception;
        started = false;
        addStatus(new ErrorStatus((new StringBuilder()).append("Failed to initialize encoder for appender named [").append(name).append("].").toString(), this, ioexception));
          goto _L1
    }

    public Encoder getEncoder()
    {
        return encoder;
    }

    public OutputStream getOutputStream()
    {
        return outputStream;
    }

    public void setEncoder(Encoder encoder1)
    {
        encoder = encoder1;
    }

    public void setLayout(Layout layout)
    {
        addWarn("This appender no longer admits a layout as a sub-component, set an encoder instead.");
        addWarn("To ensure compatibility, wrapping your layout in LayoutWrappingEncoder.");
        addWarn("See also http://logback.qos.ch/codes.html#layoutInsteadOfEncoder for details");
        LayoutWrappingEncoder layoutwrappingencoder = new LayoutWrappingEncoder();
        layoutwrappingencoder.setLayout(layout);
        layoutwrappingencoder.setContext(context);
        encoder = layoutwrappingencoder;
    }

    public void setOutputStream(OutputStream outputstream)
    {
        LogbackLock logbacklock = lock;
        logbacklock;
        JVM INSTR monitorenter ;
        closeOutputStream();
        outputStream = outputstream;
        if(encoder == null)
            addWarn("Encoder has not been set. Cannot invoke its init method.");
        else
            encoderInit();
        return;
    }

    public void start()
    {
        int i = 0;
        if(encoder == null)
        {
            addStatus(new ErrorStatus((new StringBuilder()).append("No encoder set for the appender named \"").append(name).append("\".").toString(), this));
            i = 1;
        }
        if(outputStream == null)
        {
            addStatus(new ErrorStatus((new StringBuilder()).append("No output stream set for the appender named \"").append(name).append("\".").toString(), this));
            i++;
        }
        if(i == 0)
            super.start();
    }

    public void stop()
    {
        LogbackLock logbacklock = lock;
        logbacklock;
        JVM INSTR monitorenter ;
        closeOutputStream();
        super.stop();
        return;
    }

    protected void subAppend(Object obj)
    {
        if(isStarted()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(obj instanceof DeferredProcessingAware)
            ((DeferredProcessingAware)obj).prepareForDeferredProcessing();
        LogbackLock logbacklock = lock;
        logbacklock;
        JVM INSTR monitorenter ;
        writeOut(obj);
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        started = false;
        addStatus(new ErrorStatus("IO failure in appender", this, ioexception));
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void writeOut(Object obj)
        throws IOException
    {
        encoder.doEncode(obj);
    }

    protected Encoder encoder;
    protected LogbackLock lock;
    private OutputStream outputStream;
}
