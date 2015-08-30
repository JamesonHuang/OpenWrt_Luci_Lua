// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIPlayQueueItemState extends SCIObj
{
    public static final class State extends Enum
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


        public static State swigToEnum(int i)
        {
            State astate[] = (State[])com/sonos/sclib/SCIPlayQueueItemState$State.getEnumConstants();
            if(i >= astate.length || i < 0 || astate[i].swigValue != i) goto _L2; else goto _L1
_L1:
            State state1 = astate[i];
_L4:
            return state1;
_L2:
            int j = astate.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                State state = astate[k];
                if(state.swigValue == i)
                {
                    state1 = state;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIPlayQueueItemState$State).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/sonos/sclib/SCIPlayQueueItemState$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final State $VALUES[];
        public static final State PQI_STATE_NONE;
        public static final State PQI_STATE_PAUSED;
        public static final State PQI_STATE_PLAYING;
        private final int swigValue;

        static 
        {
            PQI_STATE_NONE = new State("PQI_STATE_NONE", 0);
            PQI_STATE_PLAYING = new State("PQI_STATE_PLAYING", 1);
            PQI_STATE_PAUSED = new State("PQI_STATE_PAUSED", 2);
            State astate[] = new State[3];
            astate[0] = PQI_STATE_NONE;
            astate[1] = PQI_STATE_PLAYING;
            astate[2] = PQI_STATE_PAUSED;
            $VALUES = astate;
        }

        private State(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private State(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private State(String s, int i, State state)
        {
            Enum(s, i);
            swigValue = state.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIPlayQueueItemState(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIPlayQueueItemStateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIPlayQueueItemState(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIPlayQueueItemState(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIPlayQueueItemState sciplayqueueitemstate)
    {
        long l;
        if(sciplayqueueitemstate == null)
            l = 0L;
        else
            l = sciplayqueueitemstate.swigCPtr;
        return l;
    }

    /**
     * @deprecated Method dispose is deprecated
     */

    public void dispose()
    {
        this;
        JVM INSTR monitorenter ;
        swigCPtr = 0L;
        dispose();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public State getState()
    {
        return State.swigToEnum(sclibJNI.SCIPlayQueueItemState_getState(swigCPtr, this));
    }

    public boolean isCurrentItem()
    {
        return sclibJNI.SCIPlayQueueItemState_isCurrentItem(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIPlayQueueItemState");
    private long swigCPtr;

}
