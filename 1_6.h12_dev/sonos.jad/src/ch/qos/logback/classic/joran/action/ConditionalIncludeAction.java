// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran.action;

import ch.qos.logback.core.joran.action.AbstractIncludeAction;
import ch.qos.logback.core.joran.spi.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.UnknownHostException;
import org.xml.sax.Attributes;

public class ConditionalIncludeAction extends AbstractIncludeAction
{
    class State
    {

        URL getUrl()
        {
            return url;
        }

        void setUrl(URL url1)
        {
            url = url1;
        }

        final ConditionalIncludeAction this$0;
        private URL url;

        State()
        {
            this$0 = ConditionalIncludeAction.this;
            super();
        }
    }


    public ConditionalIncludeAction()
    {
    }

    private URL peekPath(InterpretationContext interpretationcontext)
    {
        if(interpretationcontext.isEmpty()) goto _L2; else goto _L1
_L1:
        Object obj = interpretationcontext.peekObject();
        if(!(obj instanceof State)) goto _L2; else goto _L3
_L3:
        URL url = ((State)obj).getUrl();
        if(url == null) goto _L2; else goto _L4
_L4:
        return url;
_L2:
        url = null;
        if(true) goto _L4; else goto _L5
_L5:
    }

    private URL pushPath(InterpretationContext interpretationcontext, URL url)
    {
        State state = new State();
        state.setUrl(url);
        interpretationcontext.pushObject(state);
        return url;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
        if(peekPath(interpretationcontext) == null)
            super.begin(interpretationcontext, s, attributes);
    }

    protected void handleError(String s, Exception exception)
    {
        if(exception != null && !(exception instanceof FileNotFoundException) && !(exception instanceof UnknownHostException))
            addWarn(s, exception);
        else
            addInfo(s);
    }

    protected void processInclude(InterpretationContext interpretationcontext, URL url)
        throws JoranException
    {
        pushPath(interpretationcontext, url);
    }
}
