// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.status.ErrorStatus;
import java.util.*;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ClassicConverter

public class CallerDataConverter extends ClassicConverter
{

    public CallerDataConverter()
    {
        depth = 5;
        evaluatorList = null;
        errorCount = 0;
    }

    private void addEvaluator(EventEvaluator eventevaluator)
    {
        if(evaluatorList == null)
            evaluatorList = new ArrayList();
        evaluatorList.add(eventevaluator);
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        int i;
        StringBuilder stringbuilder;
        i = 0;
        stringbuilder = new StringBuilder();
        if(evaluatorList == null) goto _L2; else goto _L1
_L1:
        int k = 0;
_L6:
        EventEvaluator eventevaluator;
        if(k >= evaluatorList.size())
            break MISSING_BLOCK_LABEL_327;
        eventevaluator = (EventEvaluator)evaluatorList.get(k);
        boolean flag1 = eventevaluator.evaluate(iloggingevent);
        if(!flag1) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L8:
        if(flag) goto _L2; else goto _L5
_L5:
        String s = "";
_L7:
        return s;
        EvaluationException evaluationexception;
        evaluationexception;
        errorCount = 1 + errorCount;
        if(errorCount < 4)
            addError((new StringBuilder()).append("Exception thrown for evaluator named [").append(eventevaluator.getName()).append("]").toString(), evaluationexception);
        else
        if(errorCount == 4)
        {
            ErrorStatus errorstatus = new ErrorStatus((new StringBuilder()).append("Exception thrown for evaluator named [").append(eventevaluator.getName()).append("].").toString(), this, evaluationexception);
            errorstatus.add(new ErrorStatus("This was the last warning about this evaluator's errors.We don't want the StatusManager to get flooded.", this));
            addStatus(errorstatus);
        }
_L4:
        k++;
          goto _L6
_L2:
        StackTraceElement astacktraceelement[] = iloggingevent.getCallerData();
        if(astacktraceelement != null && astacktraceelement.length > 0)
        {
            int j;
            if(depth < astacktraceelement.length)
                j = depth;
            else
                j = astacktraceelement.length;
            for(; i < j; i++)
            {
                stringbuilder.append(getCallerLinePrefix());
                stringbuilder.append(i);
                stringbuilder.append("\t at ");
                stringbuilder.append(astacktraceelement[i]);
                stringbuilder.append(CoreConstants.LINE_SEPARATOR);
            }

            s = stringbuilder.toString();
        } else
        {
            s = CallerData.CALLER_DATA_NA;
        }
          goto _L7
        flag = false;
          goto _L8
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }

    protected String getCallerLinePrefix()
    {
        return "Caller+";
    }

    public void start()
    {
        String s = getFirstOption();
        if(s != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        List list;
        try
        {
            depth = Integer.parseInt(s);
        }
        catch(NumberFormatException numberformatexception)
        {
            addError((new StringBuilder()).append("Failed to parse depth option [").append(s).append("]").toString(), numberformatexception);
        }
        list = getOptionList();
        if(list != null && list.size() > 1)
        {
            int i = list.size();
            int j = 1;
            while(j < i) 
            {
                String s1 = (String)list.get(j);
                Context context = getContext();
                if(context != null)
                {
                    EventEvaluator eventevaluator = (EventEvaluator)((Map)context.getObject("EVALUATOR_MAP")).get(s1);
                    if(eventevaluator != null)
                        addEvaluator(eventevaluator);
                }
                j++;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static final String DEFAULT_CALLER_LINE_PREFIX = "Caller+";
    final int MAX_ERROR_COUNT = 4;
    int depth;
    int errorCount;
    List evaluatorList;
}
