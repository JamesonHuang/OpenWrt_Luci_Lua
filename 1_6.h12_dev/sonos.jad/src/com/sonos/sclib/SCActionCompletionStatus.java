// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCActionCompletionStatus extends Enum
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


    private SCActionCompletionStatus(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCActionCompletionStatus(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCActionCompletionStatus(String s, int i, SCActionCompletionStatus scactioncompletionstatus)
    {
        Enum(s, i);
        swigValue = scactioncompletionstatus.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCActionCompletionStatus swigToEnum(int i)
    {
        SCActionCompletionStatus ascactioncompletionstatus[] = (SCActionCompletionStatus[])com/sonos/sclib/SCActionCompletionStatus.getEnumConstants();
        if(i >= ascactioncompletionstatus.length || i < 0 || ascactioncompletionstatus[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCActionCompletionStatus scactioncompletionstatus1 = ascactioncompletionstatus[i];
_L4:
        return scactioncompletionstatus1;
_L2:
        int j = ascactioncompletionstatus.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCActionCompletionStatus scactioncompletionstatus = ascactioncompletionstatus[k];
            if(scactioncompletionstatus.swigValue == i)
            {
                scactioncompletionstatus1 = scactioncompletionstatus;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCActionCompletionStatus).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCActionCompletionStatus valueOf(String s)
    {
        return (SCActionCompletionStatus)Enum.valueOf(com/sonos/sclib/SCActionCompletionStatus, s);
    }

    public static SCActionCompletionStatus[] values()
    {
        return (SCActionCompletionStatus[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCActionCompletionStatus $VALUES[];
    public static final SCActionCompletionStatus DONE_WITH_ACTION;
    public static final SCActionCompletionStatus WAIT_FOR_CALLBACK;
    private final int swigValue;

    static 
    {
        DONE_WITH_ACTION = new SCActionCompletionStatus("DONE_WITH_ACTION", 0);
        WAIT_FOR_CALLBACK = new SCActionCompletionStatus("WAIT_FOR_CALLBACK", 1);
        SCActionCompletionStatus ascactioncompletionstatus[] = new SCActionCompletionStatus[2];
        ascactioncompletionstatus[0] = DONE_WITH_ACTION;
        ascactioncompletionstatus[1] = WAIT_FOR_CALLBACK;
        $VALUES = ascactioncompletionstatus;
    }
}
