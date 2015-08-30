// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.android;

import ch.qos.logback.core.joran.event.SaxEventRecorder;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.repackage.brut.androlib.res.decoder.AXmlResourceParser;
import java.util.*;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.LocatorImpl;
import org.xmlpull.v1.XmlPullParser;

public class ASaxEventRecorder extends SaxEventRecorder
{
    static class StatePassFilter
    {

        public boolean checkEnd(String s)
        {
            boolean flag = false;
            if(_depth <= 0 || !s.equals(_states[-1 + _depth])) goto _L2; else goto _L1
_L1:
            _depth = -1 + _depth;
_L4:
            return flag;
_L2:
            if(_depth == _states.length)
                flag = true;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public boolean checkStart(String s)
        {
            boolean flag;
            if(_depth == _states.length)
            {
                flag = true;
            } else
            {
                if(s.equals(_states[_depth]))
                    _depth = 1 + _depth;
                flag = false;
            }
            return flag;
        }

        public int depth()
        {
            return _depth;
        }

        public boolean passed()
        {
            boolean flag;
            if(_depth == _states.length)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void reset()
        {
            _depth = 0;
        }

        public int size()
        {
            return _states.length;
        }

        private int _depth;
        private final String _states[];

        public transient StatePassFilter(String as[])
        {
            _depth = 0;
            if(as == null)
                as = new String[0];
            _states = as;
        }
    }


    public ASaxEventRecorder()
    {
        holderForStartAndLength = new int[2];
        filter = new StatePassFilter(new String[0]);
        elemNameToWatch = null;
        elemAttrs = null;
    }

    private void characters(XmlPullParser xmlpullparser)
    {
        if(filter.passed())
            super.characters(xmlpullparser.getTextCharacters(holderForStartAndLength), holderForStartAndLength[0], holderForStartAndLength[1]);
    }

    private void checkForWatchedAttributes(XmlPullParser xmlpullparser)
    {
        if(elemNameToWatch != null && elemAttrs == null && xmlpullparser.getName().equals(elemNameToWatch))
        {
            HashMap hashmap = new HashMap();
            int i = 0;
            while(i < xmlpullparser.getAttributeCount()) 
            {
                String s = xmlpullparser.getAttributeNamespace(i);
                String s1;
                if(s.length() > 0)
                {
                    int j = s.lastIndexOf("/");
                    if(j > -1 && j + 1 < s.length())
                        s = s.substring(j + 1);
                    s1 = (new StringBuilder()).append(s).append(":").toString();
                } else
                {
                    s1 = "";
                }
                hashmap.put((new StringBuilder()).append(s1).append(xmlpullparser.getAttributeName(i)).toString(), xmlpullparser.getAttributeValue(i));
                i++;
            }
            elemAttrs = hashmap;
        }
    }

    private void endElement(XmlPullParser xmlpullparser)
    {
        String s = xmlpullparser.getName();
        if(filter.checkEnd(s))
            endElement(xmlpullparser.getNamespace(), s, s);
    }

    private void startDocument(XmlPullParser xmlpullparser)
    {
        super.startDocument();
        super.setDocumentLocator(new LocatorImpl());
    }

    private void startElement(XmlPullParser xmlpullparser)
    {
        String s = xmlpullparser.getName();
        if(filter.checkStart(s))
        {
            AttributesImpl attributesimpl = new AttributesImpl();
            for(int i = 0; i < xmlpullparser.getAttributeCount(); i++)
                attributesimpl.addAttribute(xmlpullparser.getAttributeNamespace(i), xmlpullparser.getAttributeName(i), xmlpullparser.getAttributeName(i), xmlpullparser.getAttributeType(i), xmlpullparser.getAttributeValue(i));

            startElement(xmlpullparser.getNamespace(), s, s, ((org.xml.sax.Attributes) (attributesimpl)));
        }
        checkForWatchedAttributes(xmlpullparser);
    }

    public Map getAttributeWatchValues()
    {
        return elemAttrs;
    }

    public List recordEvents(InputSource inputsource)
        throws JoranException
    {
        java.io.InputStream inputstream;
        inputstream = inputsource.getByteStream();
        if(inputstream == null)
            throw new IllegalArgumentException("Input source must specify an input stream");
        AXmlResourceParser axmlresourceparser;
        axmlresourceparser = new AXmlResourceParser(inputstream);
        elemAttrs = null;
_L5:
        int i = axmlresourceparser.next();
        if(i <= -1) goto _L2; else goto _L1
_L1:
        if(i != 0) goto _L4; else goto _L3
_L3:
        filter.reset();
        startDocument(axmlresourceparser);
          goto _L5
        Exception exception;
        exception;
        addError(exception.getMessage(), exception);
        throw new JoranException("Can't parse Android XML resource", exception);
_L4:
        if(1 != i)
            break MISSING_BLOCK_LABEL_113;
        filter.reset();
        endDocument();
_L2:
        return getSaxEventList();
        if(2 == i)
            startElement(axmlresourceparser);
        else
        if(3 == i)
            endElement(axmlresourceparser);
        else
        if(4 == i)
            characters(axmlresourceparser);
          goto _L5
    }

    public void setAttributeWatch(String s)
    {
        elemNameToWatch = s;
    }

    public transient void setFilter(String as[])
    {
        filter = new StatePassFilter(as);
    }

    private Map elemAttrs;
    private String elemNameToWatch;
    private StatePassFilter filter;
    private int holderForStartAndLength[];
}
