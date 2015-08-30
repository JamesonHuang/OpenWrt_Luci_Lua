// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core;

import ch.qos.logback.core.spi.ContextAwareBase;

// Referenced classes of package ch.qos.logback.core:
//            Layout, Context

public abstract class LayoutBase extends ContextAwareBase
    implements Layout
{

    public LayoutBase()
    {
    }

    public String getContentType()
    {
        return "text/plain";
    }

    public Context getContext()
    {
        return context;
    }

    public String getFileFooter()
    {
        return fileFooter;
    }

    public String getFileHeader()
    {
        return fileHeader;
    }

    public String getPresentationFooter()
    {
        return presentationFooter;
    }

    public String getPresentationHeader()
    {
        return presentationHeader;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public void setFileFooter(String s)
    {
        fileFooter = s;
    }

    public void setFileHeader(String s)
    {
        fileHeader = s;
    }

    public void setPresentationFooter(String s)
    {
        presentationFooter = s;
    }

    public void setPresentationHeader(String s)
    {
        presentationHeader = s;
    }

    public void start()
    {
        started = true;
    }

    public void stop()
    {
        started = false;
    }

    String fileFooter;
    String fileHeader;
    String presentationFooter;
    String presentationHeader;
    protected boolean started;
}
