// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCIWifiConnectionResult extends Enum
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


    private SCIWifiConnectionResult(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCIWifiConnectionResult(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCIWifiConnectionResult(String s, int i, SCIWifiConnectionResult sciwificonnectionresult)
    {
        Enum(s, i);
        swigValue = sciwificonnectionresult.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCIWifiConnectionResult swigToEnum(int i)
    {
        SCIWifiConnectionResult asciwificonnectionresult[] = (SCIWifiConnectionResult[])com/sonos/sclib/SCIWifiConnectionResult.getEnumConstants();
        if(i >= asciwificonnectionresult.length || i < 0 || asciwificonnectionresult[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCIWifiConnectionResult sciwificonnectionresult1 = asciwificonnectionresult[i];
_L4:
        return sciwificonnectionresult1;
_L2:
        int j = asciwificonnectionresult.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCIWifiConnectionResult sciwificonnectionresult = asciwificonnectionresult[k];
            if(sciwificonnectionresult.swigValue == i)
            {
                sciwificonnectionresult1 = sciwificonnectionresult;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIWifiConnectionResult).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCIWifiConnectionResult valueOf(String s)
    {
        return (SCIWifiConnectionResult)Enum.valueOf(com/sonos/sclib/SCIWifiConnectionResult, s);
    }

    public static SCIWifiConnectionResult[] values()
    {
        return (SCIWifiConnectionResult[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCIWifiConnectionResult $VALUES[];
    public static final SCIWifiConnectionResult WIFI_RESULT_FAILURE;
    public static final SCIWifiConnectionResult WIFI_RESULT_SUCCESS;
    public static final SCIWifiConnectionResult WIFI_RESULT_WATCHDOG;
    private final int swigValue;

    static 
    {
        WIFI_RESULT_SUCCESS = new SCIWifiConnectionResult("WIFI_RESULT_SUCCESS", 0, sclibJNI.WIFI_RESULT_SUCCESS_get());
        WIFI_RESULT_FAILURE = new SCIWifiConnectionResult("WIFI_RESULT_FAILURE", 1, sclibJNI.WIFI_RESULT_FAILURE_get());
        WIFI_RESULT_WATCHDOG = new SCIWifiConnectionResult("WIFI_RESULT_WATCHDOG", 2, sclibJNI.WIFI_RESULT_WATCHDOG_get());
        SCIWifiConnectionResult asciwificonnectionresult[] = new SCIWifiConnectionResult[3];
        asciwificonnectionresult[0] = WIFI_RESULT_SUCCESS;
        asciwificonnectionresult[1] = WIFI_RESULT_FAILURE;
        asciwificonnectionresult[2] = WIFI_RESULT_WATCHDOG;
        $VALUES = asciwificonnectionresult;
    }
}
