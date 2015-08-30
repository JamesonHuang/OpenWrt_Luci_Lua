// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.encoder;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package ch.qos.logback.core.encoder:
//            EncoderBase

public class EchoEncoder extends EncoderBase
{

    public EchoEncoder()
    {
    }

    public void close()
        throws IOException
    {
    }

    public void doEncode(Object obj)
        throws IOException
    {
        String s = (new StringBuilder()).append(obj).append(CoreConstants.LINE_SEPARATOR).toString();
        outputStream.write(s.getBytes());
        outputStream.flush();
    }

    public void init(OutputStream outputstream)
        throws IOException
    {
        super.init(outputstream);
    }
}
