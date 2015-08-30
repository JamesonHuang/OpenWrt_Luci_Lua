// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.event.SaxEvent;
import ch.qos.logback.core.joran.event.SaxEventRecorder;
import ch.qos.logback.core.joran.spi.*;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            AbstractIncludeAction

public class IncludeAction extends AbstractIncludeAction
{

    public IncludeAction()
    {
        eventOffset = 2;
    }

    private String getEventName(SaxEvent saxevent)
    {
        String s;
        if(saxevent.qName.length() > 0)
            s = saxevent.qName;
        else
            s = saxevent.localName;
        return s;
    }

    private InputStream openURL(URL url)
    {
        InputStream inputstream1 = url.openStream();
        InputStream inputstream = inputstream1;
_L2:
        return inputstream;
        IOException ioexception;
        ioexception;
        if(!isOptional())
            addError((new StringBuilder()).append("Failed to open [").append(url.toString()).append("]").toString(), ioexception);
        inputstream = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void trimHeadAndTail(SaxEventRecorder saxeventrecorder)
    {
        List list = saxeventrecorder.getSaxEventList();
        if(list.size() != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        SaxEvent saxevent = (SaxEvent)list.get(0);
        boolean flag;
        boolean flag1;
        if(saxevent != null)
        {
            String s1 = getEventName(saxevent);
            boolean flag2 = "included".equalsIgnoreCase(s1);
            boolean flag3 = "configuration".equalsIgnoreCase(s1);
            flag1 = flag2;
            flag = flag3;
        } else
        {
            flag = false;
            flag1 = false;
        }
        if(flag1 || flag)
        {
            list.remove(0);
            int i = list.size();
            if(i != 0)
            {
                int j = i - 1;
                SaxEvent saxevent1 = (SaxEvent)list.get(j);
                if(saxevent1 != null)
                {
                    String s = getEventName(saxevent1);
                    if(flag1 && "included".equalsIgnoreCase(s) || flag && "configuration".equalsIgnoreCase(s))
                        list.remove(j);
                }
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected SaxEventRecorder createRecorder(InputStream inputstream, URL url)
    {
        return new SaxEventRecorder(getContext());
    }

    protected void processInclude(InterpretationContext interpretationcontext, URL url)
        throws JoranException
    {
        InputStream inputstream;
        inputstream = openURL(url);
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_66;
        ConfigurationWatchListUtil.addToWatchList(getContext(), url);
        SaxEventRecorder saxeventrecorder = createRecorder(inputstream, url);
        saxeventrecorder.setContext(getContext());
        saxeventrecorder.recordEvents(inputstream);
        trimHeadAndTail(saxeventrecorder);
        interpretationcontext.getJoranInterpreter().getEventPlayer().addEventsDynamically(saxeventrecorder.getSaxEventList(), eventOffset);
        close(inputstream);
_L2:
        return;
        JoranException joranexception;
        joranexception;
        addError((new StringBuilder()).append("Failed processing [").append(url.toString()).append("]").toString(), joranexception);
        close(inputstream);
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        close(inputstream);
        throw exception;
    }

    protected void setEventOffset(int i)
    {
        eventOffset = i;
    }

    private static final String CONFIG_TAG = "configuration";
    private static final String INCLUDED_TAG = "included";
    private int eventOffset;
}
