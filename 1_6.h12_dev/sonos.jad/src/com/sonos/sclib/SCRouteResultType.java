// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCRouteResultType extends Enum
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


    private SCRouteResultType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCRouteResultType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCRouteResultType(String s, int i, SCRouteResultType scrouteresulttype)
    {
        Enum(s, i);
        swigValue = scrouteresulttype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCRouteResultType swigToEnum(int i)
    {
        SCRouteResultType ascrouteresulttype[] = (SCRouteResultType[])com/sonos/sclib/SCRouteResultType.getEnumConstants();
        if(i >= ascrouteresulttype.length || i < 0 || ascrouteresulttype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCRouteResultType scrouteresulttype1 = ascrouteresulttype[i];
_L4:
        return scrouteresulttype1;
_L2:
        int j = ascrouteresulttype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCRouteResultType scrouteresulttype = ascrouteresulttype[k];
            if(scrouteresulttype.swigValue == i)
            {
                scrouteresulttype1 = scrouteresulttype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCRouteResultType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCRouteResultType valueOf(String s)
    {
        return (SCRouteResultType)Enum.valueOf(com/sonos/sclib/SCRouteResultType, s);
    }

    public static SCRouteResultType[] values()
    {
        return (SCRouteResultType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCRouteResultType $VALUES[];
    public static final SCRouteResultType FAILED;
    public static final SCRouteResultType NOTPROCESSED;
    public static final SCRouteResultType PROCESSED;
    private final int swigValue;

    static 
    {
        PROCESSED = new SCRouteResultType("PROCESSED", 0);
        NOTPROCESSED = new SCRouteResultType("NOTPROCESSED", 1);
        FAILED = new SCRouteResultType("FAILED", 2);
        SCRouteResultType ascrouteresulttype[] = new SCRouteResultType[3];
        ascrouteresulttype[0] = PROCESSED;
        ascrouteresulttype[1] = NOTPROCESSED;
        ascrouteresulttype[2] = FAILED;
        $VALUES = ascrouteresulttype;
    }
}
