// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.turbo;

import ch.qos.logback.core.spi.FilterReply;

// Referenced classes of package ch.qos.logback.classic.turbo:
//            TurboFilter

public abstract class MatchingFilter extends TurboFilter
{

    public MatchingFilter()
    {
        onMatch = FilterReply.NEUTRAL;
        onMismatch = FilterReply.NEUTRAL;
    }

    public final void setOnMatch(String s)
    {
        if(!"NEUTRAL".equals(s)) goto _L2; else goto _L1
_L1:
        onMatch = FilterReply.NEUTRAL;
_L4:
        return;
_L2:
        if("ACCEPT".equals(s))
            onMatch = FilterReply.ACCEPT;
        else
        if("DENY".equals(s))
            onMatch = FilterReply.DENY;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public final void setOnMismatch(String s)
    {
        if(!"NEUTRAL".equals(s)) goto _L2; else goto _L1
_L1:
        onMismatch = FilterReply.NEUTRAL;
_L4:
        return;
_L2:
        if("ACCEPT".equals(s))
            onMismatch = FilterReply.ACCEPT;
        else
        if("DENY".equals(s))
            onMismatch = FilterReply.DENY;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected FilterReply onMatch;
    protected FilterReply onMismatch;
}
