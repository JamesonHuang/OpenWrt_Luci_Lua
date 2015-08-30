// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.spi.ContextAwareBase;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            PeriodicityType

public class RollingCalendar extends GregorianCalendar
{

    public RollingCalendar()
    {
        periodicityType = PeriodicityType.ERRONEOUS;
    }

    public RollingCalendar(TimeZone timezone, Locale locale)
    {
        super(timezone, locale);
        periodicityType = PeriodicityType.ERRONEOUS;
    }

    public static int diffInMonths(long l, long l1)
    {
        if(l > l1)
        {
            throw new IllegalArgumentException("startTime cannot be larger than endTime");
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(l);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTimeInMillis(l1);
            int i = calendar1.get(1) - calendar.get(1);
            return (calendar1.get(2) - calendar.get(2)) + i * 12;
        }
    }

    private void setPeriodicityType(PeriodicityType periodicitytype)
    {
        periodicityType = periodicitytype;
    }

    public PeriodicityType computePeriodicityType(String s)
    {
        RollingCalendar rollingcalendar;
        Date date;
        PeriodicityType aperiodicitytype[];
        int i;
        int j;
        rollingcalendar = new RollingCalendar(GMT_TIMEZONE, Locale.getDefault());
        date = new Date(0L);
        if(s == null)
            break MISSING_BLOCK_LABEL_134;
        aperiodicitytype = PeriodicityType.VALID_ORDERED_LIST;
        i = aperiodicitytype.length;
        j = 0;
_L3:
        PeriodicityType periodicitytype;
        String s1;
        String s2;
        if(j >= i)
            break MISSING_BLOCK_LABEL_134;
        periodicitytype = aperiodicitytype[j];
        SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
        simpledateformat.setTimeZone(GMT_TIMEZONE);
        s1 = simpledateformat.format(date);
        rollingcalendar.setPeriodicityType(periodicitytype);
        s2 = simpledateformat.format(new Date(rollingcalendar.getNextTriggeringMillis(date)));
        if(s1 == null || s2 == null || s1.equals(s2)) goto _L2; else goto _L1
_L1:
        return periodicitytype;
_L2:
        j++;
          goto _L3
        periodicitytype = PeriodicityType.ERRONEOUS;
          goto _L1
    }

    public Date getNextTriggeringDate(Date date)
    {
        return getRelativeDate(date, 1);
    }

    public long getNextTriggeringMillis(Date date)
    {
        return getNextTriggeringDate(date).getTime();
    }

    public PeriodicityType getPeriodicityType()
    {
        return periodicityType;
    }

    public Date getRelativeDate(Date date, int i)
    {
        setTime(date);
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType = new int[PeriodicityType.values().length];
                NoSuchFieldError nosuchfielderror7;
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType[PeriodicityType.TOP_OF_MILLISECOND.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType[PeriodicityType.TOP_OF_SECOND.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType[PeriodicityType.TOP_OF_MINUTE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType[PeriodicityType.TOP_OF_HOUR.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType[PeriodicityType.HALF_DAY.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType[PeriodicityType.TOP_OF_DAY.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType[PeriodicityType.TOP_OF_WEEK.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                $SwitchMap$ch$qos$logback$core$rolling$helper$PeriodicityType[PeriodicityType.TOP_OF_MONTH.ordinal()] = 8;
_L2:
                return;
                nosuchfielderror7;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.ch.qos.logback.core.rolling.helper.PeriodicityType[periodicityType.ordinal()];
        JVM INSTR tableswitch 1 8: default 64
    //                   1 74
    //                   2 86
    //                   3 103
    //                   4 127
    //                   5 64
    //                   6 158
    //                   7 195
    //                   8 242;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7 _L8
_L1:
        throw new IllegalStateException("Unknown periodicity type.");
_L2:
        add(14, i);
_L10:
        return getTime();
_L3:
        set(14, 0);
        add(13, i);
        continue; /* Loop/switch isn't completed */
_L4:
        set(13, 0);
        set(14, 0);
        add(12, i);
        continue; /* Loop/switch isn't completed */
_L5:
        set(12, 0);
        set(13, 0);
        set(14, 0);
        add(11, i);
        continue; /* Loop/switch isn't completed */
_L6:
        set(11, 0);
        set(12, 0);
        set(13, 0);
        set(14, 0);
        add(5, i);
        continue; /* Loop/switch isn't completed */
_L7:
        set(7, getFirstDayOfWeek());
        set(11, 0);
        set(12, 0);
        set(13, 0);
        set(14, 0);
        add(3, i);
        continue; /* Loop/switch isn't completed */
_L8:
        set(5, 1);
        set(11, 0);
        set(12, 0);
        set(13, 0);
        set(14, 0);
        add(2, i);
        if(true) goto _L10; else goto _L9
_L9:
    }

    public void init(String s)
    {
        periodicityType = computePeriodicityType(s);
    }

    public long periodsElapsed(long l, long l1)
    {
        long l2;
        if(l > l1)
            throw new IllegalArgumentException("Start cannot come before end");
        l2 = l1 - l;
        _cls1..SwitchMap.ch.qos.logback.core.rolling.helper.PeriodicityType[periodicityType.ordinal()];
        JVM INSTR tableswitch 1 8: default 80
    //                   1 98
    //                   2 90
    //                   3 101
    //                   4 112
    //                   5 80
    //                   6 124
    //                   7 135
    //                   8 146;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7 _L8
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalStateException("Unknown periodicity type.");
_L3:
        l2 /= 1000L;
_L10:
        return l2;
_L4:
        l2 /= 60000L;
        continue; /* Loop/switch isn't completed */
_L5:
        l2 = (int)l2 / 0x36ee80;
        continue; /* Loop/switch isn't completed */
_L6:
        l2 /= 0x5265c00L;
        continue; /* Loop/switch isn't completed */
_L7:
        l2 /= 0x240c8400L;
        continue; /* Loop/switch isn't completed */
_L8:
        l2 = diffInMonths(l, l1);
        if(true) goto _L10; else goto _L9
_L9:
    }

    public void printPeriodicity(ContextAwareBase contextawarebase)
    {
        _cls1..SwitchMap.ch.qos.logback.core.rolling.helper.PeriodicityType[periodicityType.ordinal()];
        JVM INSTR tableswitch 1 8: default 56
    //                   1 63
    //                   2 72
    //                   3 81
    //                   4 90
    //                   5 99
    //                   6 108
    //                   7 117
    //                   8 126;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        contextawarebase.addInfo("Unknown periodicity.");
_L11:
        return;
_L2:
        contextawarebase.addInfo("Roll-over every millisecond.");
        continue; /* Loop/switch isn't completed */
_L3:
        contextawarebase.addInfo("Roll-over every second.");
        continue; /* Loop/switch isn't completed */
_L4:
        contextawarebase.addInfo("Roll-over every minute.");
        continue; /* Loop/switch isn't completed */
_L5:
        contextawarebase.addInfo("Roll-over at the top of every hour.");
        continue; /* Loop/switch isn't completed */
_L6:
        contextawarebase.addInfo("Roll-over at midday and midnight.");
        continue; /* Loop/switch isn't completed */
_L7:
        contextawarebase.addInfo("Roll-over at midnight.");
        continue; /* Loop/switch isn't completed */
_L8:
        contextawarebase.addInfo("Rollover at the start of week.");
        continue; /* Loop/switch isn't completed */
_L9:
        contextawarebase.addInfo("Rollover at start of every month.");
        if(true) goto _L11; else goto _L10
_L10:
    }

    static final TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT");
    private static final long serialVersionUID = 0xad99a0e20fafa44fL;
    PeriodicityType periodicityType;

}
