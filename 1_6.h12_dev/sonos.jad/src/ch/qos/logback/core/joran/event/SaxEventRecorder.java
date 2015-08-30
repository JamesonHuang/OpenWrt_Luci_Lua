// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.event;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.ElementPath;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.ContextAwareImpl;
import ch.qos.logback.core.status.Status;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.sax2.Driver;

// Referenced classes of package ch.qos.logback.core.joran.event:
//            BodyEvent, EndEvent, SaxEvent, StartEvent

public class SaxEventRecorder extends DefaultHandler
    implements ContextAware
{

    public SaxEventRecorder()
    {
        saxEventList = new ArrayList();
        globalElementPath = new ElementPath();
        cai = new ContextAwareImpl(null, this);
    }

    public SaxEventRecorder(Context context)
    {
        saxEventList = new ArrayList();
        globalElementPath = new ElementPath();
        cai = new ContextAwareImpl(context, this);
    }

    private Driver buildPullParser()
        throws JoranException
    {
        Driver driver = new Driver();
        Exception exception;
        try
        {
            driver.setFeature("http://xml.org/sax/features/validation", false);
        }
        catch(SAXNotSupportedException saxnotsupportedexception) { }
        driver.setFeature("http://xml.org/sax/features/namespaces", true);
        return driver;
        exception;
        addError("Parser configuration error occurred", exception);
        throw new JoranException("Parser configuration error occurred", exception);
    }

    private void handleError(String s, Throwable throwable)
        throws JoranException
    {
        addError(s, throwable);
        throw new JoranException(s, throwable);
    }

    public void addError(String s)
    {
        cai.addError(s);
    }

    public void addError(String s, Throwable throwable)
    {
        cai.addError(s, throwable);
    }

    public void addInfo(String s)
    {
        cai.addInfo(s);
    }

    public void addInfo(String s, Throwable throwable)
    {
        cai.addInfo(s, throwable);
    }

    public void addStatus(Status status)
    {
        cai.addStatus(status);
    }

    public void addWarn(String s)
    {
        cai.addWarn(s);
    }

    public void addWarn(String s, Throwable throwable)
    {
        cai.addWarn(s, throwable);
    }

    public void characters(char ac[], int i, int j)
    {
        String s;
        SaxEvent saxevent;
        s = new String(ac, i, j);
        saxevent = getLastEvent();
        if(!(saxevent instanceof BodyEvent)) goto _L2; else goto _L1
_L1:
        ((BodyEvent)saxevent).append(s);
_L4:
        return;
_L2:
        if(!isSpaceOnly(s))
            saxEventList.add(new BodyEvent(s, getLocator()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void endElement(String s, String s1, String s2)
    {
        if(s2 != null)
            if(s2.length() != 0);
        saxEventList.add(new EndEvent(s, s1, s2, getLocator()));
        globalElementPath.pop();
    }

    public void error(SAXParseException saxparseexception)
        throws SAXException
    {
        addError((new StringBuilder()).append("XML_PARSING - Parsing error on line ").append(saxparseexception.getLineNumber()).append(" and column ").append(saxparseexception.getColumnNumber()).toString(), saxparseexception);
    }

    public void fatalError(SAXParseException saxparseexception)
        throws SAXException
    {
        addError((new StringBuilder()).append("XML_PARSING - Parsing fatal error on line ").append(saxparseexception.getLineNumber()).append(" and column ").append(saxparseexception.getColumnNumber()).toString(), saxparseexception);
    }

    public Context getContext()
    {
        return cai.getContext();
    }

    SaxEvent getLastEvent()
    {
        SaxEvent saxevent;
        if(saxEventList.isEmpty())
        {
            saxevent = null;
        } else
        {
            int i = saxEventList.size();
            saxevent = (SaxEvent)saxEventList.get(i - 1);
        }
        return saxevent;
    }

    public Locator getLocator()
    {
        return locator;
    }

    public List getSaxEventList()
    {
        return saxEventList;
    }

    String getTagName(String s, String s1)
    {
        if(s == null || s.length() < 1)
            s = s1;
        return s;
    }

    boolean isSpaceOnly(String s)
    {
        boolean flag;
        if(s.trim().length() == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public List recordEvents(InputSource inputsource)
        throws JoranException
    {
        Driver driver = buildPullParser();
        List list;
        driver.setContentHandler(this);
        driver.setErrorHandler(this);
        driver.parse(inputsource);
        list = saxEventList;
        return list;
        EOFException eofexception;
        eofexception;
        handleError(eofexception.getLocalizedMessage(), new SAXParseException(eofexception.getLocalizedMessage(), locator, eofexception));
_L2:
        throw new IllegalStateException("This point can never be reached");
        IOException ioexception;
        ioexception;
        handleError("I/O error occurred while parsing xml file", ioexception);
        continue; /* Loop/switch isn't completed */
        SAXException saxexception;
        saxexception;
        throw new JoranException("Problem parsing XML document. See previously reported errors.", saxexception);
        Exception exception;
        exception;
        handleError("Unexpected exception while parsing XML document.", exception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final void recordEvents(InputStream inputstream)
        throws JoranException
    {
        recordEvents(new InputSource(inputstream));
    }

    public void setContext(Context context)
    {
        cai.setContext(context);
    }

    public void setDocumentLocator(Locator locator1)
    {
        locator = locator1;
    }

    public void startDocument()
    {
    }

    public void startElement(String s, String s1, String s2, Attributes attributes)
    {
        if(s2 != null)
            if(s2.length() != 0);
        String s3 = getTagName(s1, s2);
        globalElementPath.push(s3);
        ElementPath elementpath = globalElementPath.duplicate();
        saxEventList.add(new StartEvent(elementpath, s, s1, s2, attributes, getLocator()));
    }

    public void warning(SAXParseException saxparseexception)
        throws SAXException
    {
        addWarn((new StringBuilder()).append("XML_PARSING - Parsing warning on line ").append(saxparseexception.getLineNumber()).append(" and column ").append(saxparseexception.getColumnNumber()).toString(), saxparseexception);
    }

    private final ContextAwareImpl cai;
    ElementPath globalElementPath;
    private Locator locator;
    private List saxEventList;
}
