// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.event;

import org.xml.sax.Locator;

// Referenced classes of package ch.qos.logback.core.joran.event:
//            SaxEvent

public class BodyEvent extends SaxEvent
{

    BodyEvent(String s, Locator locator)
    {
        super(null, null, null, locator);
        text = s;
    }

    public void append(String s)
    {
        text = (new StringBuilder()).append(text).append(s).toString();
    }

    public String getText()
    {
        String s;
        if(text != null)
            s = text.trim();
        else
            s = text;
        return s;
    }

    public String toString()
    {
        return (new StringBuilder()).append("BodyEvent(").append(getText()).append(")").append(locator.getLineNumber()).append(",").append(locator.getColumnNumber()).toString();
    }

    private String text;
}
