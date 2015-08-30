// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.filter;

import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.spi.FilterReply;

// Referenced classes of package ch.qos.logback.core.filter:
//            AbstractMatcherFilter

public class EvaluatorFilter extends AbstractMatcherFilter
{

    public EvaluatorFilter()
    {
    }

    public FilterReply decide(Object obj)
    {
        FilterReply filterreply;
        if(!isStarted() || !evaluator.isStarted())
            filterreply = FilterReply.NEUTRAL;
        else
            try
            {
                if(evaluator.evaluate(obj))
                    filterreply = onMatch;
                else
                    filterreply = onMismatch;
            }
            catch(EvaluationException evaluationexception)
            {
                addError((new StringBuilder()).append("Evaluator ").append(evaluator.getName()).append(" threw an exception").toString(), evaluationexception);
                filterreply = FilterReply.NEUTRAL;
            }
        return filterreply;
    }

    public EventEvaluator getEvaluator()
    {
        return evaluator;
    }

    public void setEvaluator(EventEvaluator eventevaluator)
    {
        evaluator = eventevaluator;
    }

    public void start()
    {
        if(evaluator != null)
            super.start();
        else
            addError((new StringBuilder()).append("No evaluator set for filter ").append(getName()).toString());
    }

    EventEvaluator evaluator;
}
