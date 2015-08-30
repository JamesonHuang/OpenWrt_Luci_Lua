// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCDayOfWeek extends Enum
{
    private static class SwigNext
    {

        private static int next = 0;



/*
        static int access$002(int i)
        {
            next = i;
            return i;
        }

*/


/*
        static int access$008()
        {
            int i = next;
            next = i + 1;
            return i;
        }

*/

        private SwigNext()
        {
        }
    }


    private SCDayOfWeek(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCDayOfWeek(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCDayOfWeek(String s, int i, SCDayOfWeek scdayofweek)
    {
        Enum(s, i);
        swigValue = scdayofweek.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCDayOfWeek swigToEnum(int i)
    {
        SCDayOfWeek ascdayofweek[] = (SCDayOfWeek[])com/sonos/sclib/SCDayOfWeek.getEnumConstants();
        if(i >= ascdayofweek.length || i < 0 || ascdayofweek[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCDayOfWeek scdayofweek1 = ascdayofweek[i];
_L4:
        return scdayofweek1;
_L2:
        int j = ascdayofweek.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCDayOfWeek scdayofweek = ascdayofweek[k];
            if(scdayofweek.swigValue == i)
            {
                scdayofweek1 = scdayofweek;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCDayOfWeek).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCDayOfWeek valueOf(String s)
    {
        return (SCDayOfWeek)Enum.valueOf(com/sonos/sclib/SCDayOfWeek, s);
    }

    public static SCDayOfWeek[] values()
    {
        return (SCDayOfWeek[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCDayOfWeek $VALUES[];
    public static final SCDayOfWeek SCDAY_FRIDAY;
    public static final SCDayOfWeek SCDAY_MONDAY;
    public static final SCDayOfWeek SCDAY_SATURDAY;
    public static final SCDayOfWeek SCDAY_SUNDAY;
    public static final SCDayOfWeek SCDAY_THURSDAY;
    public static final SCDayOfWeek SCDAY_TUESDAY;
    public static final SCDayOfWeek SCDAY_WEDNESDAY;
    private final int swigValue;

    static 
    {
        SCDAY_SUNDAY = new SCDayOfWeek("SCDAY_SUNDAY", 0, sclibJNI.SCDAY_SUNDAY_get());
        SCDAY_MONDAY = new SCDayOfWeek("SCDAY_MONDAY", 1);
        SCDAY_TUESDAY = new SCDayOfWeek("SCDAY_TUESDAY", 2);
        SCDAY_WEDNESDAY = new SCDayOfWeek("SCDAY_WEDNESDAY", 3);
        SCDAY_THURSDAY = new SCDayOfWeek("SCDAY_THURSDAY", 4);
        SCDAY_FRIDAY = new SCDayOfWeek("SCDAY_FRIDAY", 5);
        SCDAY_SATURDAY = new SCDayOfWeek("SCDAY_SATURDAY", 6);
        SCDayOfWeek ascdayofweek[] = new SCDayOfWeek[7];
        ascdayofweek[0] = SCDAY_SUNDAY;
        ascdayofweek[1] = SCDAY_MONDAY;
        ascdayofweek[2] = SCDAY_TUESDAY;
        ascdayofweek[3] = SCDAY_WEDNESDAY;
        ascdayofweek[4] = SCDAY_THURSDAY;
        ascdayofweek[5] = SCDAY_FRIDAY;
        ascdayofweek[6] = SCDAY_SATURDAY;
        $VALUES = ascdayofweek;
    }
}
