// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.joran.action;

import ch.qos.logback.classic.*;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

public class LoggerAction extends Action
{

    public LoggerAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        inError = false;
        logger = null;
        LoggerContext loggercontext = (LoggerContext)context;
        String s1 = interpretationcontext.subst(attributes.getValue("name"));
        if(OptionHelper.isEmpty(s1))
        {
            inError = true;
            String s4 = getLineColStr(interpretationcontext);
            addError((new StringBuilder()).append("No 'name' attribute in element ").append(s).append(", around ").append(s4).toString());
        } else
        {
            logger = loggercontext.getLogger(s1);
            String s2 = interpretationcontext.subst(attributes.getValue("level"));
            String s3;
            if(!OptionHelper.isEmpty(s2))
                if("INHERITED".equalsIgnoreCase(s2) || "NULL".equalsIgnoreCase(s2))
                {
                    addInfo((new StringBuilder()).append("Setting level of logger [").append(s1).append("] to null, i.e. INHERITED").toString());
                    logger.setLevel(null);
                } else
                {
                    Level level = Level.toLevel(s2);
                    addInfo((new StringBuilder()).append("Setting level of logger [").append(s1).append("] to ").append(level).toString());
                    logger.setLevel(level);
                }
            s3 = interpretationcontext.subst(attributes.getValue("additivity"));
            if(!OptionHelper.isEmpty(s3))
            {
                boolean flag = Boolean.valueOf(s3).booleanValue();
                addInfo((new StringBuilder()).append("Setting additivity of logger [").append(s1).append("] to ").append(flag).toString());
                logger.setAdditive(flag);
            }
            interpretationcontext.pushObject(logger);
        }
    }

    public void end(InterpretationContext interpretationcontext, String s)
    {
        if(!inError)
        {
            Object obj = interpretationcontext.peekObject();
            if(obj != logger)
            {
                addWarn((new StringBuilder()).append("The object on the top the of the stack is not ").append(logger).append(" pushed earlier").toString());
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

    public static final String LEVEL_ATTRIBUTE = "level";
    boolean inError;
    Logger logger;
}
