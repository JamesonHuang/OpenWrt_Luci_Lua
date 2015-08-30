// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.event;

import ch.qos.logback.core.joran.spi.ElementPath;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.helpers.AttributesImpl;

// Referenced classes of package ch.qos.logback.core.joran.event:
//            SaxEvent

public class StartEvent extends SaxEvent
{

    StartEvent(ElementPath elementpath, String s, String s1, String s2, Attributes attributes1, Locator locator)
    {
        super(s, s1, s2, locator);
        attributes = new AttributesImpl(attributes1);
        elementPath = elementpath;
    }

    public Attributes getAttributes()
    {
        return attributes;
    }

    public String toString()
    {
        return (new StringBuilder()).append("StartEvent(").append(getQName()).append(")  [").append(locator.getLineNumber()).append(",").append(locator.getColumnNumber()).append("]").toString();
    }

    public final Attributes attributes;
    public final ElementPath elementPath;
}
