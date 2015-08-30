// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCServiceAccountFilterID extends Enum
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


    private SCServiceAccountFilterID(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCServiceAccountFilterID(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCServiceAccountFilterID(String s, int i, SCServiceAccountFilterID scserviceaccountfilterid)
    {
        Enum(s, i);
        swigValue = scserviceaccountfilterid.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCServiceAccountFilterID swigToEnum(int i)
    {
        SCServiceAccountFilterID ascserviceaccountfilterid[] = (SCServiceAccountFilterID[])com/sonos/sclib/SCServiceAccountFilterID.getEnumConstants();
        if(i >= ascserviceaccountfilterid.length || i < 0 || ascserviceaccountfilterid[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCServiceAccountFilterID scserviceaccountfilterid1 = ascserviceaccountfilterid[i];
_L4:
        return scserviceaccountfilterid1;
_L2:
        int j = ascserviceaccountfilterid.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCServiceAccountFilterID scserviceaccountfilterid = ascserviceaccountfilterid[k];
            if(scserviceaccountfilterid.swigValue == i)
            {
                scserviceaccountfilterid1 = scserviceaccountfilterid;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCServiceAccountFilterID).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCServiceAccountFilterID valueOf(String s)
    {
        return (SCServiceAccountFilterID)Enum.valueOf(com/sonos/sclib/SCServiceAccountFilterID, s);
    }

    public static SCServiceAccountFilterID[] values()
    {
        return (SCServiceAccountFilterID[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCServiceAccountFilterID $VALUES[];
    public static final SCServiceAccountFilterID SC_SERVICEACCOUNTFILTER_ID_BY_SERVICE;
    public static final SCServiceAccountFilterID SC_SERVICEACCOUNTFILTER_ID_MUSICACCOUNTS;
    public static final SCServiceAccountFilterID SC_SERVICEACCOUNTFILTER_ID_NONSOCIALACCOUNTS;
    public static final SCServiceAccountFilterID SC_SERVICEACCOUNTFILTER_ID_SOCIALACCOUNTS;
    private final int swigValue;

    static 
    {
        SC_SERVICEACCOUNTFILTER_ID_MUSICACCOUNTS = new SCServiceAccountFilterID("SC_SERVICEACCOUNTFILTER_ID_MUSICACCOUNTS", 0);
        SC_SERVICEACCOUNTFILTER_ID_NONSOCIALACCOUNTS = new SCServiceAccountFilterID("SC_SERVICEACCOUNTFILTER_ID_NONSOCIALACCOUNTS", 1);
        SC_SERVICEACCOUNTFILTER_ID_SOCIALACCOUNTS = new SCServiceAccountFilterID("SC_SERVICEACCOUNTFILTER_ID_SOCIALACCOUNTS", 2);
        SC_SERVICEACCOUNTFILTER_ID_BY_SERVICE = new SCServiceAccountFilterID("SC_SERVICEACCOUNTFILTER_ID_BY_SERVICE", 3);
        SCServiceAccountFilterID ascserviceaccountfilterid[] = new SCServiceAccountFilterID[4];
        ascserviceaccountfilterid[0] = SC_SERVICEACCOUNTFILTER_ID_MUSICACCOUNTS;
        ascserviceaccountfilterid[1] = SC_SERVICEACCOUNTFILTER_ID_NONSOCIALACCOUNTS;
        ascserviceaccountfilterid[2] = SC_SERVICEACCOUNTFILTER_ID_SOCIALACCOUNTS;
        ascserviceaccountfilterid[3] = SC_SERVICEACCOUNTFILTER_ID_BY_SERVICE;
        $VALUES = ascserviceaccountfilterid;
    }
}
