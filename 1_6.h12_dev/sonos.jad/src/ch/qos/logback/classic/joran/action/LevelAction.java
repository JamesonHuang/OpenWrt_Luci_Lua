// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import org.xml.sax.Attributes;

public class LevelAction extends Action
{

    public LevelAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        Object obj = interpretationcontext.peekObject();
        if(!(obj instanceof Logger))
        {
            inError = true;
            addError("For element <level>, could not find a logger at the top of execution stack.");
        } else
        {
            Logger logger = (Logger)obj;
            String s1 = logger.getName();
            String s2 = interpretationcontext.subst(attributes.getValue("value"));
            if("INHERITED".equalsIgnoreCase(s2) || "NULL".equalsIgnoreCase(s2))
                logger.setLevel(null);
            else
                logger.setLevel(Level.toLevel(s2, Level.DEBUG));
            addInfo((new StringBuilder()).append(s1).append(" level set to ").append(logger.getLevel()).toString());
        }
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
    }

    public void finish(InterpretationContext interpretationcontext)
    {
    }

    boolean inError;
}
