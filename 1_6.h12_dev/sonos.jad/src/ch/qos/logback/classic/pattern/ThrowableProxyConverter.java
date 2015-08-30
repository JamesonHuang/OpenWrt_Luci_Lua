// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.*;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.status.ErrorStatus;
import java.util.*;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            ThrowableHandlingConverter

public class ThrowableProxyConverter extends ThrowableHandlingConverter
{

    public ThrowableProxyConverter()
    {
        evaluatorList = null;
        errorCount = 0;
    }

    private void addEvaluator(EventEvaluator eventevaluator)
    {
        if(evaluatorList == null)
            evaluatorList = new ArrayList();
        evaluatorList.add(eventevaluator);
    }

    private void recursiveAppend(StringBuilder stringbuilder, String s, int i, IThrowableProxy ithrowableproxy)
    {
        if(ithrowableproxy != null)
        {
            subjoinFirstLine(stringbuilder, s, i, ithrowableproxy);
            stringbuilder.append(CoreConstants.LINE_SEPARATOR);
            subjoinSTEPArray(stringbuilder, i, ithrowableproxy);
            IThrowableProxy aithrowableproxy[] = ithrowableproxy.getSuppressed();
            if(aithrowableproxy != null)
            {
                int j = aithrowableproxy.length;
                for(int k = 0; k < j; k++)
                {
                    IThrowableProxy ithrowableproxy1 = aithrowableproxy[k];
                    recursiveAppend(stringbuilder, "Suppressed: ", i + 1, ithrowableproxy1);
                }

            }
            recursiveAppend(stringbuilder, "Caused by: ", i, ithrowableproxy.getCause());
        }
    }

    private void subjoinExceptionMessage(StringBuilder stringbuilder, IThrowableProxy ithrowableproxy)
    {
        stringbuilder.append(ithrowableproxy.getClassName()).append(": ").append(ithrowableproxy.getMessage());
    }

    private void subjoinFirstLine(StringBuilder stringbuilder, String s, int i, IThrowableProxy ithrowableproxy)
    {
        ThrowableProxyUtil.indent(stringbuilder, i - 1);
        if(s != null)
            stringbuilder.append(s);
        subjoinExceptionMessage(stringbuilder, ithrowableproxy);
    }

    public String convert(ILoggingEvent iloggingevent)
    {
        boolean flag;
        IThrowableProxy ithrowableproxy;
        flag = false;
        ithrowableproxy = iloggingevent.getThrowableProxy();
        if(ithrowableproxy != null) goto _L2; else goto _L1
_L1:
        String s = "";
_L8:
        return s;
_L2:
        if(evaluatorList == null) goto _L4; else goto _L3
_L3:
        int i = 0;
_L9:
        EventEvaluator eventevaluator;
        if(i >= evaluatorList.size())
            break MISSING_BLOCK_LABEL_232;
        eventevaluator = (EventEvaluator)evaluatorList.get(i);
        boolean flag1 = eventevaluator.evaluate(iloggingevent);
        if(!flag1) goto _L6; else goto _L5
_L5:
        if(flag) goto _L4; else goto _L7
_L7:
        s = "";
          goto _L8
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
_L6:
        i++;
          goto _L9
_L4:
        s = throwableProxyToString(ithrowableproxy);
          goto _L8
        flag = true;
          goto _L5
    }

    public volatile String convert(Object obj)
    {
        return convert((ILoggingEvent)obj);
    }

    protected void extraData(StringBuilder stringbuilder, StackTraceElementProxy stacktraceelementproxy)
    {
    }

    public void start()
    {
        String s = getFirstOption();
        List list;
        if(s == null)
        {
            lengthOption = 0x7fffffff;
        } else
        {
            String s1 = s.toLowerCase();
            if("full".equals(s1))
                lengthOption = 0x7fffffff;
            else
            if("short".equals(s1))
                lengthOption = 1;
            else
                try
                {
                    lengthOption = Integer.parseInt(s1);
                }
                catch(NumberFormatException numberformatexception)
                {
                    addError((new StringBuilder()).append("Could not parse [").append(s1).append("] as an integer").toString());
                    lengthOption = 0x7fffffff;
                }
        }
        list = getOptionList();
        if(list != null && list.size() > 1)
        {
            int i = list.size();
            for(int j = 1; j < i; j++)
            {
                String s2 = (String)list.get(j);
                addEvaluator((EventEvaluator)((Map)getContext().getObject("EVALUATOR_MAP")).get(s2));
            }

        }
        super.start();
    }

    public void stop()
    {
        evaluatorList = null;
        super.stop();
    }

    protected void subjoinSTEPArray(StringBuilder stringbuilder, int i, IThrowableProxy ithrowableproxy)
    {
        int j = 0;
        StackTraceElementProxy astacktraceelementproxy[] = ithrowableproxy.getStackTraceElementProxyArray();
        int k = ithrowableproxy.getCommonFrames();
        boolean flag;
        int l;
        if(lengthOption > astacktraceelementproxy.length)
            flag = true;
        else
            flag = false;
        if(flag)
            l = astacktraceelementproxy.length;
        else
            l = lengthOption;
        if(k > 0 && flag)
            l -= k;
        for(; j < l; j++)
        {
            ThrowableProxyUtil.indent(stringbuilder, i);
            stringbuilder.append(astacktraceelementproxy[j]);
            extraData(stringbuilder, astacktraceelementproxy[j]);
            stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        }

        if(k > 0 && flag)
        {
            ThrowableProxyUtil.indent(stringbuilder, i);
            stringbuilder.append("... ").append(ithrowableproxy.getCommonFrames()).append(" common frames omitted").append(CoreConstants.LINE_SEPARATOR);
        }
    }

    protected String throwableProxyToString(IThrowableProxy ithrowableproxy)
    {
        StringBuilder stringbuilder = new StringBuilder(2048);
        recursiveAppend(stringbuilder, null, 1, ithrowableproxy);
        return stringbuilder.toString();
    }

    protected static final int BUILDER_CAPACITY = 2048;
    int errorCount;
    List evaluatorList;
    int lengthOption;
}
