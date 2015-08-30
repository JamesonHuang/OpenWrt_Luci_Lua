// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.sift;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.event.InPlayListener;
import ch.qos.logback.core.joran.event.SaxEvent;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.classic.sift:
//            SiftingAppender, AppenderFactoryUsingJoran

public class SiftAction extends Action
    implements InPlayListener
{

    public SiftAction()
    {
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
        seList = new ArrayList();
        interpretationcontext.addInPlayListener(this);
    }

    public void end(InterpretationContext interpretationcontext, String s)
        throws ActionException
    {
        interpretationcontext.removeInPlayListener(this);
        Object obj = interpretationcontext.peekObject();
        if(obj instanceof SiftingAppender)
        {
            SiftingAppender siftingappender = (SiftingAppender)obj;
            java.util.Map map = interpretationcontext.getCopyOfPropertyMap();
            siftingappender.setAppenderFactory(new AppenderFactoryUsingJoran(seList, siftingappender.getDiscriminatorKey(), map));
        }
    }

    public List getSeList()
    {
        return seList;
    }

    public void inPlay(SaxEvent saxevent)
    {
        seList.add(saxevent);
    }

    List seList;
}
