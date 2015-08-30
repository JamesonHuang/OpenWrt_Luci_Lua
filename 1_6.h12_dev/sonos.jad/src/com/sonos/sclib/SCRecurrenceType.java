// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCRecurrenceType extends Enum
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


    private SCRecurrenceType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCRecurrenceType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCRecurrenceType(String s, int i, SCRecurrenceType screcurrencetype)
    {
        Enum(s, i);
        swigValue = screcurrencetype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCRecurrenceType swigToEnum(int i)
    {
        SCRecurrenceType ascrecurrencetype[] = (SCRecurrenceType[])com/sonos/sclib/SCRecurrenceType.getEnumConstants();
        if(i >= ascrecurrencetype.length || i < 0 || ascrecurrencetype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCRecurrenceType screcurrencetype1 = ascrecurrencetype[i];
_L4:
        return screcurrencetype1;
_L2:
        int j = ascrecurrencetype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCRecurrenceType screcurrencetype = ascrecurrencetype[k];
            if(screcurrencetype.swigValue == i)
            {
                screcurrencetype1 = screcurrencetype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCRecurrenceType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCRecurrenceType valueOf(String s)
    {
        return (SCRecurrenceType)Enum.valueOf(com/sonos/sclib/SCRecurrenceType, s);
    }

    public static SCRecurrenceType[] values()
    {
        return (SCRecurrenceType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCRecurrenceType $VALUES[];
    public static final SCRecurrenceType SCRECURRENCE_DAYS_OF_WEEK;
    public static final SCRecurrenceType SCRECURRENCE_ONCE;
    private final int swigValue;

    static 
    {
        SCRECURRENCE_ONCE = new SCRecurrenceType("SCRECURRENCE_ONCE", 0);
        SCRECURRENCE_DAYS_OF_WEEK = new SCRecurrenceType("SCRECURRENCE_DAYS_OF_WEEK", 1);
        SCRecurrenceType ascrecurrencetype[] = new SCRecurrenceType[2];
        ascrecurrencetype[0] = SCRECURRENCE_ONCE;
        ascrecurrencetype[1] = SCRECURRENCE_DAYS_OF_WEEK;
        $VALUES = ascrecurrencetype;
    }
}
