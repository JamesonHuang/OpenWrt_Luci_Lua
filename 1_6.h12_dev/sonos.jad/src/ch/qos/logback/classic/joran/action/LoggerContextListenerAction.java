// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

public class LoggerContextListenerAction extends Action
{

    public LoggerContextListenerAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
        inError = false;
        String s1 = attributes.getValue("class");
        if(OptionHelper.isEmpty(s1))
        {
            addError("Mandatory \"class\" attribute not set for <loggerContextListener> element");
            inError = true;
        } else
        {
            try
            {
                lcl = (LoggerContextListener)OptionHelper.instantiateByClassName(s1, ch/qos/logback/classic/spi/LoggerContextListener, context);
                if(lcl instanceof ContextAware)
                    ((ContextAware)lcl).setContext(context);
                interpretationcontext.pushObject(lcl);
                addInfo((new StringBuilder()).append("Adding LoggerContextListener of type [").append(s1).append("] to the object stack").toString());
            }
            catch(Exception exception)
            {
                inError = true;
                addError((new StringBuilder()).append("Could not create LoggerContextListener of type ").append(s1).append("].").toString(), exception);
            }
        }
    }

    public void end(InterpretationContext interpretationcontext, String s)
        throws ActionException
    {
        if(!inError)
            if(interpretationcontext.peekObject() != lcl)
            {
                addWarn("The object on the top the of the stack is not the LoggerContextListener pushed earlier.");
            } else
            {
                if(lcl instanceof LifeCycle)
                {
                    ((LifeCycle)lcl).start();
                    addInfo("Starting LoggerContextListener");
                }
                ((LoggerContext)context).addListener(lcl);
                interpretationcontext.popObject();
            }
    }

    boolean inError;
    LoggerContextListener lcl;
}
