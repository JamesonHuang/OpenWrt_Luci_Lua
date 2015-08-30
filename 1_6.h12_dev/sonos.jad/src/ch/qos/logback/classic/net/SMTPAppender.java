// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.net;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.boolex.OnErrorEvaluator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.helpers.CyclicBuffer;
import ch.qos.logback.core.net.SMTPAppenderBase;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import org.slf4j.Marker;

public class SMTPAppender extends SMTPAppenderBase
{

    public SMTPAppender()
    {
        includeCallerData = false;
    }

    public SMTPAppender(EventEvaluator eventevaluator)
    {
        includeCallerData = false;
        eventEvaluator = eventevaluator;
    }

    protected boolean eventMarksEndOfLife(ILoggingEvent iloggingevent)
    {
        Marker marker = iloggingevent.getMarker();
        boolean flag;
        if(marker == null)
            flag = false;
        else
            flag = marker.contains(ClassicConstants.FINALIZE_SESSION_MARKER);
        return flag;
    }

    protected volatile boolean eventMarksEndOfLife(Object obj)
    {
        return eventMarksEndOfLife((ILoggingEvent)obj);
    }

    protected void fillBuffer(CyclicBuffer cyclicbuffer, StringBuffer stringbuffer)
    {
        int i = cyclicbuffer.length();
        for(int j = 0; j < i; j++)
        {
            ILoggingEvent iloggingevent = (ILoggingEvent)cyclicbuffer.get();
            stringbuffer.append(layout.doLayout(iloggingevent));
        }

    }

    public boolean isIncludeCallerData()
    {
        return includeCallerData;
    }

    protected PatternLayout makeNewToPatternLayout(String s)
    {
        PatternLayout patternlayout = new PatternLayout();
        patternlayout.setPattern((new StringBuilder()).append(s).append("%nopex").toString());
        return patternlayout;
    }

    protected volatile PatternLayoutBase makeNewToPatternLayout(String s)
    {
        return makeNewToPatternLayout(s);
    }

    protected Layout makeSubjectLayout(String s)
    {
        if(s == null)
            s = "%logger{20} - %m";
        PatternLayout patternlayout = new PatternLayout();
        patternlayout.setContext(getContext());
        patternlayout.setPattern(s);
        patternlayout.setPostCompileProcessor(null);
        patternlayout.start();
        return patternlayout;
    }

    public void setIncludeCallerData(boolean flag)
    {
        includeCallerData = flag;
    }

    public void start()
    {
        if(eventEvaluator == null)
        {
            OnErrorEvaluator onerrorevaluator = new OnErrorEvaluator();
            onerrorevaluator.setContext(getContext());
            onerrorevaluator.setName("onError");
            onerrorevaluator.start();
            eventEvaluator = onerrorevaluator;
        }
        super.start();
    }

    protected void subAppend(CyclicBuffer cyclicbuffer, ILoggingEvent iloggingevent)
    {
        if(includeCallerData)
            iloggingevent.getCallerData();
        iloggingevent.prepareForDeferredProcessing();
        cyclicbuffer.add(iloggingevent);
    }

    protected volatile void subAppend(CyclicBuffer cyclicbuffer, Object obj)
    {
        subAppend(cyclicbuffer, (ILoggingEvent)obj);
    }

    static final String DEFAULT_SUBJECT_PATTERN = "%logger{20} - %m";
    private boolean includeCallerData;
}
