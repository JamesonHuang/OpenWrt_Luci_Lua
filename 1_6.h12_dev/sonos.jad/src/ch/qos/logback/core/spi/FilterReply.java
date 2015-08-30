// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;


public final class FilterReply extends Enum
{

    private FilterReply(String s, int i)
    {
        super(s, i);
    }

    public static FilterReply valueOf(String s)
    {
        return (FilterReply)Enum.valueOf(ch/qos/logback/core/spi/FilterReply, s);
    }

    public static FilterReply[] values()
    {
        return (FilterReply[])$VALUES.clone();
    }

    private static final FilterReply $VALUES[];
    public static final FilterReply ACCEPT;
    public static final FilterReply DENY;
    public static final FilterReply NEUTRAL;

    static 
    {
        DENY = new FilterReply("DENY", 0);
        NEUTRAL = new FilterReply("NEUTRAL", 1);
        ACCEPT = new FilterReply("ACCEPT", 2);
        FilterReply afilterreply[] = new FilterReply[3];
        afilterreply[0] = DENY;
        afilterreply[1] = NEUTRAL;
        afilterreply[2] = ACCEPT;
        $VALUES = afilterreply;
    }
}
