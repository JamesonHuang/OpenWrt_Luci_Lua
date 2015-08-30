// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.net.ReceiverBase;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

public class ReceiverAction extends Action
{

    public ReceiverAction()
    {
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
        String s1 = attributes.getValue("class");
        if(!OptionHelper.isEmpty(s1)) goto _L2; else goto _L1
_L1:
        addError((new StringBuilder()).append("Missing class name for receiver. Near [").append(s).append("] line ").append(getLineNumber(interpretationcontext)).toString());
        inError = true;
_L4:
        return;
_L2:
        try
        {
            addInfo((new StringBuilder()).append("About to instantiate receiver of type [").append(s1).append("]").toString());
            receiver = (ReceiverBase)OptionHelper.instantiateByClassName(s1, ch/qos/logback/classic/net/ReceiverBase, context);
            receiver.setContext(context);
            interpretationcontext.pushObject(receiver);
        }
        catch(Exception exception)
        {
            inError = true;
            addError((new StringBuilder()).append("Could not create a receiver of type [").append(s1).append("].").toString(), exception);
            throw new ActionException(exception);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void end(InterpretationContext interpretationcontext, String s)
        throws ActionException
    {
        if(!inError)
        {
            interpretationcontext.getContext().register(receiver);
            receiver.start();
            if(interpretationcontext.peekObject() != receiver)
                addWarn("The object at the of the stack is not the remote pushed earlier.");
            else
                interpretationcontext.popObject();
        }
    }

    private boolean inError;
    private ReceiverBase receiver;
}
