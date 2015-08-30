// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.MDC;
import org.slf4j.Marker;

// Referenced classes of package ch.qos.logback.classic.turbo:
//            MatchingFilter

public class MDCFilter extends MatchingFilter
{

    public MDCFilter()
    {
    }

    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object aobj[], Throwable throwable)
    {
        FilterReply filterreply;
        if(MDCKey == null)
        {
            filterreply = FilterReply.NEUTRAL;
        } else
        {
            String s1 = MDC.get(MDCKey);
            if(value.equals(s1))
                filterreply = onMatch;
            else
                filterreply = onMismatch;
        }
        return filterreply;
    }

    public void setMDCKey(String s)
    {
        MDCKey = s;
    }

    public void setValue(String s)
    {
        value = s;
    }

    String MDCKey;
    String value;
}
