// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCIWifiFrequency extends Enum
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


    private SCIWifiFrequency(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCIWifiFrequency(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCIWifiFrequency(String s, int i, SCIWifiFrequency sciwififrequency)
    {
        Enum(s, i);
        swigValue = sciwififrequency.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCIWifiFrequency swigToEnum(int i)
    {
        SCIWifiFrequency asciwififrequency[] = (SCIWifiFrequency[])com/sonos/sclib/SCIWifiFrequency.getEnumConstants();
        if(i >= asciwififrequency.length || i < 0 || asciwififrequency[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCIWifiFrequency sciwififrequency1 = asciwififrequency[i];
_L4:
        return sciwififrequency1;
_L2:
        int j = asciwififrequency.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCIWifiFrequency sciwififrequency = asciwififrequency[k];
            if(sciwififrequency.swigValue == i)
            {
                sciwififrequency1 = sciwififrequency;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIWifiFrequency).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCIWifiFrequency valueOf(String s)
    {
        return (SCIWifiFrequency)Enum.valueOf(com/sonos/sclib/SCIWifiFrequency, s);
    }

    public static SCIWifiFrequency[] values()
    {
        return (SCIWifiFrequency[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCIWifiFrequency $VALUES[];
    public static final SCIWifiFrequency WIFI_2_4;
    public static final SCIWifiFrequency WIFI_5;
    public static final SCIWifiFrequency WIFI_UnknownFreq;
    private final int swigValue;

    static 
    {
        WIFI_UnknownFreq = new SCIWifiFrequency("WIFI_UnknownFreq", 0, sclibJNI.WIFI_UnknownFreq_get());
        WIFI_2_4 = new SCIWifiFrequency("WIFI_2_4", 1);
        WIFI_5 = new SCIWifiFrequency("WIFI_5", 2);
        SCIWifiFrequency asciwififrequency[] = new SCIWifiFrequency[3];
        asciwififrequency[0] = WIFI_UnknownFreq;
        asciwififrequency[1] = WIFI_2_4;
        asciwififrequency[2] = WIFI_5;
        $VALUES = asciwififrequency;
    }
}
