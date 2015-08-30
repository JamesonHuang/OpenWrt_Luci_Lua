// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.android.ASaxEventRecorder;
import ch.qos.logback.core.joran.action.IncludeAction;
import ch.qos.logback.core.joran.event.SaxEventRecorder;
import ch.qos.logback.core.joran.spi.*;
import java.io.InputStream;
import java.net.URL;
import org.xml.sax.Attributes;

public class FindIncludeAction extends IncludeAction
{

    public FindIncludeAction()
    {
        setEventOffset(1);
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
    }

    protected SaxEventRecorder createRecorder(InputStream inputstream, URL url)
    {
        Object obj;
        if(url.toString().endsWith("AndroidManifest.xml"))
        {
            obj = new ASaxEventRecorder();
            String as[] = new String[1];
            as[0] = "logback";
            ((ASaxEventRecorder) (obj)).setFilter(as);
        } else
        {
            obj = new SaxEventRecorder(getContext());
        }
        return ((SaxEventRecorder) (obj));
    }

    public void end(InterpretationContext interpretationcontext, String s)
        throws ActionException
    {
        URL url;
        if(interpretationcontext.isEmpty() || !(interpretationcontext.peekObject() instanceof ConditionalIncludeAction.State))
            break MISSING_BLOCK_LABEL_69;
        url = ((ConditionalIncludeAction.State)interpretationcontext.popObject()).getUrl();
        if(url == null)
            break MISSING_BLOCK_LABEL_108;
        addInfo((new StringBuilder()).append("Path found [").append(url.toString()).append("]").toString());
        processInclude(interpretationcontext, url);
_L1:
        return;
        JoranException joranexception;
        joranexception;
        addError((new StringBuilder()).append("Failed to process include [").append(url.toString()).append("]").toString(), joranexception);
          goto _L1
        addInfo("No paths found from includes");
          goto _L1
    }

    private static final int EVENT_OFFSET = 1;
}
