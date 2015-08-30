// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.util.OptionHelper;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action

public class AppenderAction extends Action
{

    public AppenderAction()
    {
        inError = false;
    }

    private void warnDeprecated(String s)
    {
        if(s.equals("ch.qos.logback.core.ConsoleAppender"))
            addWarn("ConsoleAppender is deprecated for LogcatAppender");
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
        throws ActionException
    {
        String s1;
        appender = null;
        inError = false;
        s1 = attributes.getValue("class");
        if(!OptionHelper.isEmpty(s1)) goto _L2; else goto _L1
_L1:
        addError((new StringBuilder()).append("Missing class name for appender. Near [").append(s).append("] line ").append(getLineNumber(interpretationcontext)).toString());
        inError = true;
_L4:
        return;
_L2:
        String s2;
        addInfo((new StringBuilder()).append("About to instantiate appender of type [").append(s1).append("]").toString());
        warnDeprecated(s1);
        appender = (Appender)OptionHelper.instantiateByClassName(s1, ch/qos/logback/core/Appender, context);
        appender.setContext(context);
        s2 = interpretationcontext.subst(attributes.getValue("name"));
        if(!OptionHelper.isEmpty(s2))
            break; /* Loop/switch isn't completed */
        addWarn((new StringBuilder()).append("No appender name given for appender of type ").append(s1).append("].").toString());
_L5:
        ((HashMap)interpretationcontext.getObjectMap().get("APPENDER_BAG")).put(s2, appender);
        interpretationcontext.pushObject(appender);
        Exception exception;
        if(true) goto _L4; else goto _L3
_L3:
        try
        {
            appender.setName(s2);
            addInfo((new StringBuilder()).append("Naming appender as [").append(s2).append("]").toString());
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            inError = true;
            addError((new StringBuilder()).append("Could not create an Appender of type [").append(s1).append("].").toString(), exception);
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
            if(appender instanceof LifeCycle)
                appender.start();
            if(interpretationcontext.peekObject() != appender)
                addWarn((new StringBuilder()).append("The object at the of the stack is not the appender named [").append(appender.getName()).append("] pushed earlier.").toString());
            else
                interpretationcontext.popObject();
        }
    }

    Appender appender;
    private boolean inError;
}
