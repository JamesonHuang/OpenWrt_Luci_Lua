// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.encoder;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.Layout;
import java.io.*;
import java.nio.charset.Charset;

// Referenced classes of package ch.qos.logback.core.encoder:
//            EncoderBase

public class LayoutWrappingEncoder extends EncoderBase
{

    public LayoutWrappingEncoder()
    {
        immediateFlush = true;
    }

    private void appendIfNotNull(StringBuilder stringbuilder, String s)
    {
        if(s != null)
            stringbuilder.append(s);
    }

    private byte[] convertToBytes(String s)
    {
        byte abyte1[];
        if(charset == null)
        {
            abyte1 = s.getBytes();
        } else
        {
            byte abyte0[];
            try
            {
                abyte0 = s.getBytes(charset.name());
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                throw new IllegalStateException("An existing charset cannot possibly be unsupported.");
            }
            abyte1 = abyte0;
        }
        return abyte1;
    }

    public void close()
        throws IOException
    {
        writeFooter();
    }

    public void doEncode(Object obj)
        throws IOException
    {
        String s = layout.doLayout(obj);
        outputStream.write(convertToBytes(s));
        if(immediateFlush)
            outputStream.flush();
    }

    public Charset getCharset()
    {
        return charset;
    }

    public Layout getLayout()
    {
        return layout;
    }

    public void init(OutputStream outputstream)
        throws IOException
    {
        super.init(outputstream);
        writeHeader();
    }

    public boolean isImmediateFlush()
    {
        return immediateFlush;
    }

    public boolean isStarted()
    {
        return false;
    }

    public void setCharset(Charset charset1)
    {
        charset = charset1;
    }

    public void setImmediateFlush(boolean flag)
    {
        immediateFlush = flag;
    }

    public void setLayout(Layout layout1)
    {
        layout = layout1;
    }

    public void start()
    {
        started = true;
    }

    public void stop()
    {
        started = false;
        if(outputStream == null)
            break MISSING_BLOCK_LABEL_19;
        outputStream.flush();
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void writeFooter()
        throws IOException
    {
        if(layout != null && outputStream != null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            appendIfNotNull(stringbuilder, layout.getPresentationFooter());
            appendIfNotNull(stringbuilder, layout.getFileFooter());
            if(stringbuilder.length() > 0)
            {
                outputStream.write(convertToBytes(stringbuilder.toString()));
                outputStream.flush();
            }
        }
    }

    void writeHeader()
        throws IOException
    {
        if(layout != null && outputStream != null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            appendIfNotNull(stringbuilder, layout.getFileHeader());
            appendIfNotNull(stringbuilder, layout.getPresentationHeader());
            if(stringbuilder.length() > 0)
            {
                stringbuilder.append(CoreConstants.LINE_SEPARATOR);
                outputStream.write(convertToBytes(stringbuilder.toString()));
                outputStream.flush();
            }
        }
    }

    private Charset charset;
    private boolean immediateFlush;
    protected Layout layout;
}
