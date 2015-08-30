// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.*;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

public class RootLoggerAction extends Action
{

    public RootLoggerAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        inError = false;
        root = ((LoggerContext)context).getLogger("ROOT");
        String s1 = interpretationcontext.subst(attributes.getValue("level"));
        if(!OptionHelper.isEmpty(s1))
        {
            Level level = Level.toLevel(s1);
            addInfo((new StringBuilder()).append("Setting level of ROOT logger to ").append(level).toString());
            root.setLevel(level);
        }
        interpretationcontext.pushObject(root);
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
        if(!inError)
        {
            Object obj = interpretationcontext.peekObject();
            if(obj != root)
            {
                addWarn("The object on the top the of the stack is not the root logger");
                addWarn((new StringBuilder()).append("It is: ").append(obj).toString());
            } else
            {
                interpretationcontext.popObject();
            }
        }
    }

    public void finish(InterpretationContext interpretationcontext)
    {
    }

    boolean inError;
    Logger root;
}
