// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.*;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action

public class NewRuleAction extends Action
{

    public NewRuleAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        inError = false;
        String s1 = attributes.getValue("pattern");
        String s2 = attributes.getValue("actionClass");
        if(OptionHelper.isEmpty(s1))
        {
            inError = true;
            addError("No 'pattern' attribute in <newRule>");
        } else
        if(OptionHelper.isEmpty(s2))
        {
            inError = true;
            addError("No 'actionClass' attribute in <newRule>");
        } else
        {
            try
            {
                addInfo((new StringBuilder()).append("About to add new Joran parsing rule [").append(s1).append(",").append(s2).append("].").toString());
                interpretationcontext.getJoranInterpreter().getRuleStore().addRule(new ElementSelector(s1), s2);
            }
            catch(Exception exception)
            {
                inError = true;
                addError((new StringBuilder()).append("Could not add new Joran parsing rule [").append(s1).append(",").append(s2).append("]").toString());
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
