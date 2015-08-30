// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCServiceDescriptorFilterID extends Enum
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


    private SCServiceDescriptorFilterID(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCServiceDescriptorFilterID(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCServiceDescriptorFilterID(String s, int i, SCServiceDescriptorFilterID scservicedescriptorfilterid)
    {
        Enum(s, i);
        swigValue = scservicedescriptorfilterid.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCServiceDescriptorFilterID swigToEnum(int i)
    {
        SCServiceDescriptorFilterID ascservicedescriptorfilterid[] = (SCServiceDescriptorFilterID[])com/sonos/sclib/SCServiceDescriptorFilterID.getEnumConstants();
        if(i >= ascservicedescriptorfilterid.length || i < 0 || ascservicedescriptorfilterid[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCServiceDescriptorFilterID scservicedescriptorfilterid1 = ascservicedescriptorfilterid[i];
_L4:
        return scservicedescriptorfilterid1;
_L2:
        int j = ascservicedescriptorfilterid.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCServiceDescriptorFilterID scservicedescriptorfilterid = ascservicedescriptorfilterid[k];
            if(scservicedescriptorfilterid.swigValue == i)
            {
                scservicedescriptorfilterid1 = scservicedescriptorfilterid;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCServiceDescriptorFilterID).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCServiceDescriptorFilterID valueOf(String s)
    {
        return (SCServiceDescriptorFilterID)Enum.valueOf(com/sonos/sclib/SCServiceDescriptorFilterID, s);
    }

    public static SCServiceDescriptorFilterID[] values()
    {
        return (SCServiceDescriptorFilterID[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCServiceDescriptorFilterID $VALUES[];
    public static final SCServiceDescriptorFilterID SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLEMUSICSERVICES;
    public static final SCServiceDescriptorFilterID SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLENONSOCIALSERVICES;
    public static final SCServiceDescriptorFilterID SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESERVICES;
    public static final SCServiceDescriptorFilterID SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESOCIALSERVICES;
    public static final SCServiceDescriptorFilterID SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESOUNDLABSERVICES;
    public static final SCServiceDescriptorFilterID SC_SERVICEDESCRIPTORFILTER_ID_PRELOADSERVICES;
    private final int swigValue;

    static 
    {
        SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESERVICES = new SCServiceDescriptorFilterID("SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESERVICES", 0);
        SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLEMUSICSERVICES = new SCServiceDescriptorFilterID("SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLEMUSICSERVICES", 1);
        SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLENONSOCIALSERVICES = new SCServiceDescriptorFilterID("SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLENONSOCIALSERVICES", 2);
        SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESOUNDLABSERVICES = new SCServiceDescriptorFilterID("SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESOUNDLABSERVICES", 3);
        SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESOCIALSERVICES = new SCServiceDescriptorFilterID("SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESOCIALSERVICES", 4);
        SC_SERVICEDESCRIPTORFILTER_ID_PRELOADSERVICES = new SCServiceDescriptorFilterID("SC_SERVICEDESCRIPTORFILTER_ID_PRELOADSERVICES", 5);
        SCServiceDescriptorFilterID ascservicedescriptorfilterid[] = new SCServiceDescriptorFilterID[6];
        ascservicedescriptorfilterid[0] = SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESERVICES;
        ascservicedescriptorfilterid[1] = SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLEMUSICSERVICES;
        ascservicedescriptorfilterid[2] = SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLENONSOCIALSERVICES;
        ascservicedescriptorfilterid[3] = SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESOUNDLABSERVICES;
        ascservicedescriptorfilterid[4] = SC_SERVICEDESCRIPTORFILTER_ID_AVAILABLESOCIALSERVICES;
        ascservicedescriptorfilterid[5] = SC_SERVICEDESCRIPTORFILTER_ID_PRELOADSERVICES;
        $VALUES = ascservicedescriptorfilterid;
    }
}
