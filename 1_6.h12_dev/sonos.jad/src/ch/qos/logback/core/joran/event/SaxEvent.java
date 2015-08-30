// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.event;

import org.xml.sax.Locator;
import org.xml.sax.helpers.LocatorImpl;

public class SaxEvent
{

    SaxEvent(String s, String s1, String s2, Locator locator1)
    {
        namespaceURI = s;
        localName = s1;
        qName = s2;
        locator = new LocatorImpl(locator1);
    }

    public String getLocalName()
    {
        return localName;
    }

    public Locator getLocator()
    {
        return locator;
    }

    public String getNamespaceURI()
    {
        return namespaceURI;
    }

    public String getQName()
    {
        return qName;
    }

    public final String localName;
    public final Locator locator;
    public final String namespaceURI;
    public final String qName;
}
