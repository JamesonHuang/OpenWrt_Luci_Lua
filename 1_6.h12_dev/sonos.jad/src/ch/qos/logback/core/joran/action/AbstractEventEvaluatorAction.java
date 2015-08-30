// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Map;
import org.xml.sax.Attributes;

// Referenced classes of package ch.qos.logback.core.joran.action:
//            Action

public abstract class AbstractEventEvaluatorAction extends Action
{

    public AbstractEventEvaluatorAction()
    {
        inError = false;
    }

    public void begin(InterpretationContext interpretationcontext, String s, Attributes attributes)
    {
        inError = false;
        evaluator = null;
        String s1 = attributes.getValue("class");
        if(OptionHelper.isEmpty(s1))
        {
            s1 = defaultClassName();
            addInfo((new StringBuilder()).append("Assuming default evaluator class [").append(s1).append("]").toString());
        }
        String s2 = s1;
        if(OptionHelper.isEmpty(s2))
        {
            defaultClassName();
            inError = true;
            addError("Mandatory \"class\" attribute not set for <evaluator>");
        } else
        {
            String s3 = attributes.getValue("name");
            if(OptionHelper.isEmpty(s3))
            {
                inError = true;
                addError("Mandatory \"name\" attribute not set for <evaluator>");
            } else
            {
                try
                {
                    evaluator = (EventEvaluator)OptionHelper.instantiateByClassName(s2, ch/qos/logback/core/boolex/EventEvaluator, context);
                    evaluator.setContext(context);
                    evaluator.setName(s3);
                    interpretationcontext.pushObject(evaluator);
                    addInfo((new StringBuilder()).append("Adding evaluator named [").append(s3).append("] to the object stack").toString());
                }
                catch(Exception exception)
                {
                    inError = true;
                    addError((new StringBuilder()).append("Could not create evaluator of type ").append(s2).append("].").toString(), exception);
                }
            }
        }
    }

    protected abstract String defaultClassName();

    public void end(InterpretationContext interpretationcontext, String s)
    {
        if(!inError) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(evaluator instanceof LifeCycle)
        {
            evaluator.start();
            addInfo((new StringBuilder()).append("Starting evaluator named [").append(evaluator.getName()).append("]").toString());
        }
        if(interpretationcontext.peekObject() != evaluator)
        {
            addWarn("The object on the top the of the stack is not the evaluator pushed earlier.");
            continue; /* Loop/switch isn't completed */
        }
        interpretationcontext.popObject();
        Map map = (Map)context.getObject("EVALUATOR_MAP");
        Exception exception;
        if(map == null)
        {
            addError("Could not find EvaluatorMap");
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            map.put(evaluator.getName(), evaluator);
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            addError((new StringBuilder()).append("Could not set evaluator named [").append(evaluator).append("].").toString(), exception);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void finish(InterpretationContext interpretationcontext)
    {
    }

    EventEvaluator evaluator;
    boolean inError;
}
