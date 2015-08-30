// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCTimeStatus extends Enum
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


    private SCTimeStatus(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCTimeStatus(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCTimeStatus(String s, int i, SCTimeStatus sctimestatus)
    {
        Enum(s, i);
        swigValue = sctimestatus.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCTimeStatus swigToEnum(int i)
    {
        SCTimeStatus asctimestatus[] = (SCTimeStatus[])com/sonos/sclib/SCTimeStatus.getEnumConstants();
        if(i >= asctimestatus.length || i < 0 || asctimestatus[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCTimeStatus sctimestatus1 = asctimestatus[i];
_L4:
        return sctimestatus1;
_L2:
        int j = asctimestatus.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCTimeStatus sctimestatus = asctimestatus[k];
            if(sctimestatus.swigValue == i)
            {
                sctimestatus1 = sctimestatus;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCTimeStatus).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCTimeStatus valueOf(String s)
    {
        return (SCTimeStatus)Enum.valueOf(com/sonos/sclib/SCTimeStatus, s);
    }

    public static SCTimeStatus[] values()
    {
        return (SCTimeStatus[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCTimeStatus $VALUES[];
    public static final SCTimeStatus SCTIMESTATUS_INTERNET;
    public static final SCTimeStatus SCTIMESTATUS_MANUAL;
    public static final SCTimeStatus SCTIMESTATUS_NOTSET;
    public static final SCTimeStatus SCTIMESTATUS_TIMESERVERUNAVAILABLE;
    public static final SCTimeStatus SCTIMESTATUS_UNAVAILABLE;
    public static final SCTimeStatus SCTIMESTATUS_UNKNOWN;
    private final int swigValue;

    static 
    {
        SCTIMESTATUS_UNKNOWN = new SCTimeStatus("SCTIMESTATUS_UNKNOWN", 0);
        SCTIMESTATUS_UNAVAILABLE = new SCTimeStatus("SCTIMESTATUS_UNAVAILABLE", 1);
        SCTIMESTATUS_INTERNET = new SCTimeStatus("SCTIMESTATUS_INTERNET", 2);
        SCTIMESTATUS_TIMESERVERUNAVAILABLE = new SCTimeStatus("SCTIMESTATUS_TIMESERVERUNAVAILABLE", 3);
        SCTIMESTATUS_MANUAL = new SCTimeStatus("SCTIMESTATUS_MANUAL", 4);
        SCTIMESTATUS_NOTSET = new SCTimeStatus("SCTIMESTATUS_NOTSET", 5);
        SCTimeStatus asctimestatus[] = new SCTimeStatus[6];
        asctimestatus[0] = SCTIMESTATUS_UNKNOWN;
        asctimestatus[1] = SCTIMESTATUS_UNAVAILABLE;
        asctimestatus[2] = SCTIMESTATUS_INTERNET;
        asctimestatus[3] = SCTIMESTATUS_TIMESERVERUNAVAILABLE;
        asctimestatus[4] = SCTIMESTATUS_MANUAL;
        asctimestatus[5] = SCTIMESTATUS_NOTSET;
        $VALUES = asctimestatus;
    }
}
