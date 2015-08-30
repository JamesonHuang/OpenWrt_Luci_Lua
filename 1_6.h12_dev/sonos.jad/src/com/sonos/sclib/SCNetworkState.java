// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCNetworkState extends Enum
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


    private SCNetworkState(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCNetworkState(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCNetworkState(String s, int i, SCNetworkState scnetworkstate)
    {
        Enum(s, i);
        swigValue = scnetworkstate.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCNetworkState swigToEnum(int i)
    {
        SCNetworkState ascnetworkstate[] = (SCNetworkState[])com/sonos/sclib/SCNetworkState.getEnumConstants();
        if(i >= ascnetworkstate.length || i < 0 || ascnetworkstate[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCNetworkState scnetworkstate1 = ascnetworkstate[i];
_L4:
        return scnetworkstate1;
_L2:
        int j = ascnetworkstate.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCNetworkState scnetworkstate = ascnetworkstate[k];
            if(scnetworkstate.swigValue == i)
            {
                scnetworkstate1 = scnetworkstate;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCNetworkState).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCNetworkState valueOf(String s)
    {
        return (SCNetworkState)Enum.valueOf(com/sonos/sclib/SCNetworkState, s);
    }

    public static SCNetworkState[] values()
    {
        return (SCNetworkState[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCNetworkState $VALUES[];
    public static final SCNetworkState SCNetworkState_ERROR;
    public static final SCNetworkState SCNetworkState_INIT;
    public static final SCNetworkState SCNetworkState_REBINDING_RUNNING;
    public static final SCNetworkState SCNetworkState_REBINDING_SUSPENDED;
    public static final SCNetworkState SCNetworkState_RUNNING;
    public static final SCNetworkState SCNetworkState_SUSPENDED;
    private final int swigValue;

    static 
    {
        SCNetworkState_INIT = new SCNetworkState("SCNetworkState_INIT", 0);
        SCNetworkState_ERROR = new SCNetworkState("SCNetworkState_ERROR", 1);
        SCNetworkState_RUNNING = new SCNetworkState("SCNetworkState_RUNNING", 2);
        SCNetworkState_SUSPENDED = new SCNetworkState("SCNetworkState_SUSPENDED", 3);
        SCNetworkState_REBINDING_SUSPENDED = new SCNetworkState("SCNetworkState_REBINDING_SUSPENDED", 4);
        SCNetworkState_REBINDING_RUNNING = new SCNetworkState("SCNetworkState_REBINDING_RUNNING", 5);
        SCNetworkState ascnetworkstate[] = new SCNetworkState[6];
        ascnetworkstate[0] = SCNetworkState_INIT;
        ascnetworkstate[1] = SCNetworkState_ERROR;
        ascnetworkstate[2] = SCNetworkState_RUNNING;
        ascnetworkstate[3] = SCNetworkState_SUSPENDED;
        ascnetworkstate[4] = SCNetworkState_REBINDING_SUSPENDED;
        ascnetworkstate[5] = SCNetworkState_REBINDING_RUNNING;
        $VALUES = ascnetworkstate;
    }
}
