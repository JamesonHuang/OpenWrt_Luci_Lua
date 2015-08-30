// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.encoder;

import ch.qos.logback.core.spi.ContextAwareBase;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package ch.qos.logback.core.encoder:
//            Encoder

public abstract class EncoderBase extends ContextAwareBase
    implements Encoder
{

    public EncoderBase()
    {
    }

    public void init(OutputStream outputstream)
        throws IOException
    {
        outputStream = outputstream;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void start()
    {
        started = true;
    }

    public void stop()
    {
        started = false;
    }

    protected OutputStream outputStream;
    protected boolean started;
}
