// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.android;

import android.util.Log;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;

public class LogcatAppender extends AppenderBase
{

    public LogcatAppender()
    {
        encoder = null;
        tagEncoder = null;
        checkLoggable = false;
    }

    public void append(ILoggingEvent iloggingevent)
    {
        if(isStarted()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String s = getTag(iloggingevent);
        switch(iloggingevent.getLevel().levelInt)
        {
        case -2147483648: 
        case 5000: 
            if(!checkLoggable || Log.isLoggable(s, 2))
                Log.v(s, encoder.getLayout().doLayout(iloggingevent));
            break;

        case 10000: 
            if(!checkLoggable || Log.isLoggable(s, 3))
                Log.d(s, encoder.getLayout().doLayout(iloggingevent));
            break;

        case 20000: 
            if(!checkLoggable || Log.isLoggable(s, 4))
                Log.i(s, encoder.getLayout().doLayout(iloggingevent));
            break;

        case 30000: 
            if(!checkLoggable || Log.isLoggable(s, 5))
                Log.w(s, encoder.getLayout().doLayout(iloggingevent));
            break;

        case 40000: 
            if(!checkLoggable || Log.isLoggable(s, 6))
                Log.e(s, encoder.getLayout().doLayout(iloggingevent));
            break;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public volatile void append(Object obj)
    {
        append((ILoggingEvent)obj);
    }

    public boolean getCheckLoggable()
    {
        return checkLoggable;
    }

    public PatternLayoutEncoder getEncoder()
    {
        return encoder;
    }

    protected String getTag(ILoggingEvent iloggingevent)
    {
        String s;
        if(tagEncoder != null)
            s = tagEncoder.getLayout().doLayout(iloggingevent);
        else
            s = iloggingevent.getLoggerName();
        if(checkLoggable && s.length() > 23)
            s = (new StringBuilder()).append(s.substring(0, 22)).append("*").toString();
        return s;
    }

    public PatternLayoutEncoder getTagEncoder()
    {
        return tagEncoder;
    }

    public void setCheckLoggable(boolean flag)
    {
        checkLoggable = flag;
    }

    public void setEncoder(PatternLayoutEncoder patternlayoutencoder)
    {
        encoder = patternlayoutencoder;
    }

    public void setTagEncoder(PatternLayoutEncoder patternlayoutencoder)
    {
        tagEncoder = patternlayoutencoder;
    }

    public void start()
    {
        if(encoder != null && encoder.getLayout() != null) goto _L2; else goto _L1
_L1:
        addError((new StringBuilder()).append("No layout set for the appender named [").append(name).append("].").toString());
_L4:
        return;
_L2:
        if(tagEncoder != null)
        {
            Layout layout = tagEncoder.getLayout();
            if(layout == null)
            {
                addError((new StringBuilder()).append("No tag layout set for the appender named [").append(name).append("].").toString());
                continue; /* Loop/switch isn't completed */
            }
            if(layout instanceof PatternLayout)
            {
                String s = tagEncoder.getPattern();
                if(!s.contains("%nopex"))
                {
                    tagEncoder.stop();
                    tagEncoder.setPattern((new StringBuilder()).append(s).append("%nopex").toString());
                    tagEncoder.start();
                }
                ((PatternLayout)layout).setPostCompileProcessor(null);
            }
        }
        super.start();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int MAX_TAG_LENGTH = 23;
    private boolean checkLoggable;
    private PatternLayoutEncoder encoder;
    private PatternLayoutEncoder tagEncoder;
}
