// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.status.*;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action

public class StatusListenerAction extends Action
{

    public StatusListenerAction()
    {
        inError = false;
        statusListener = null;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
        String s1;
        inError = false;
        s1 = attributes.getValue("class");
        if(!OptionHelper.isEmpty(s1)) goto _L2; else goto _L1
_L1:
        addError((new StringBuilder()).append("Missing class name for statusListener. Near [").append(s).append("] line ").append(getLineNumber(interpretationcontext)).toString());
        inError = true;
_L4:
        return;
_L2:
        if(!ch/qos/logback/core/status/OnConsoleStatusListener.getName().equals(s1))
            break; /* Loop/switch isn't completed */
        OnConsoleStatusListener.addNewInstanceToContext(context);
_L5:
        addInfo((new StringBuilder()).append("Added status listener of type [").append(s1).append("]").toString());
        interpretationcontext.pushObject(statusListener);
        Exception exception;
        if(true) goto _L4; else goto _L3
_L3:
        try
        {
            statusListener = (StatusListener)OptionHelper.instantiateByClassName(s1, ch/qos/logback/core/status/StatusListener, context);
            interpretationcontext.getContext().getStatusManager().add(statusListener);
            if(statusListener instanceof ContextAware)
                ((ContextAware)statusListener).setContext(context);
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            inError = true;
            addError((new StringBuilder()).append("Could not create an StatusListener of type [").append(s1).append("].").toString(), exception);
            throw new ActionException(exception);
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
        if(!inError)
        {
            if(statusListener instanceof LifeCycle)
                ((LifeCycle)statusListener).start();
            if(interpretationcontext.peekObject() != statusListener)
                addWarn("The object at the of the stack is not the statusListener pushed earlier.");
            else
                interpretationcontext.popObject();
        }
    }

    public void finish(InterpretationContext interpretationcontext)
    {
    }

    boolean inError;
    StatusListener statusListener;
}
