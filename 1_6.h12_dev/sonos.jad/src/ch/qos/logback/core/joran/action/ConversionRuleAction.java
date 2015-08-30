// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action

public class ConversionRuleAction extends Action
{

    public ConversionRuleAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        inError = false;
        String s1 = attributes.getValue("conversionWord");
        String s2 = attributes.getValue("converterClass");
        if(OptionHelper.isEmpty(s1))
        {
            inError = true;
            addError("No 'conversionWord' attribute in <conversionRule>");
        } else
        if(OptionHelper.isEmpty(s2))
        {
            inError = true;
            interpretationcontext.addError("No 'converterClass' attribute in <conversionRule>");
        } else
        {
            try
            {
                Object obj = (Map)context.getObject("PATTERN_RULE_REGISTRY");
                if(obj == null)
                {
                    obj = new HashMap();
                    context.putObject("PATTERN_RULE_REGISTRY", obj);
                }
                addInfo((new StringBuilder()).append("registering conversion word ").append(s1).append(" with class [").append(s2).append("]").toString());
                ((Map) (obj)).put(s1, s2);
            }
            catch(Exception exception)
            {
                inError = true;
                addError("Could not add conversion rule to PatternLayout.");
            }
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
