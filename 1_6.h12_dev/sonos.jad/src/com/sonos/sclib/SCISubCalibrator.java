// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIEventSink

public class SCISubCalibrator extends SCIObj
{
    public static final class Phase extends Enum
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


        public static Phase swigToEnum(int i)
        {
            Phase aphase[] = (Phase[])com/sonos/sclib/SCISubCalibrator$Phase.getEnumConstants();
            if(i >= aphase.length || i < 0 || aphase[i].swigValue != i) goto _L2; else goto _L1
_L1:
            Phase phase1 = aphase[i];
_L4:
            return phase1;
_L2:
            int j = aphase.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                Phase phase = aphase[k];
                if(phase.swigValue == i)
                {
                    phase1 = phase;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISubCalibrator$Phase).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static Phase valueOf(String s)
        {
            return (Phase)Enum.valueOf(com/sonos/sclib/SCISubCalibrator$Phase, s);
        }

        public static Phase[] values()
        {
            return (Phase[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final Phase $VALUES[];
        public static final Phase PHASE_A;
        public static final Phase PHASE_B;
        public static final Phase PHASE_NA;
        private final int swigValue;

        static 
        {
            PHASE_NA = new Phase("PHASE_NA", 0);
            PHASE_A = new Phase("PHASE_A", 1);
            PHASE_B = new Phase("PHASE_B", 2);
            Phase aphase[] = new Phase[3];
            aphase[0] = PHASE_NA;
            aphase[1] = PHASE_A;
            aphase[2] = PHASE_B;
            $VALUES = aphase;
        }

        private Phase(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private Phase(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private Phase(String s, int i, Phase phase)
        {
            Enum(s, i);
            swigValue = phase.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCISubCalibrator(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISubCalibratorUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISubCalibrator(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISubCalibrator(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISubCalibrator scisubcalibrator)
    {
        long l;
        if(scisubcalibrator == null)
            l = 0L;
        else
            l = scisubcalibrator.swigCPtr;
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

    public Phase getPhase()
    {
        return Phase.swigToEnum(sclibJNI.SCISubCalibrator_getPhase(swigCPtr, this));
    }

    public void play()
    {
        sclibJNI.SCISubCalibrator_play(swigCPtr, this);
    }

    public void setSubLevelIndex(int i)
    {
        sclibJNI.SCISubCalibrator_setSubLevelIndex(swigCPtr, this, i);
    }

    public void stop()
    {
        sclibJNI.SCISubCalibrator_stop(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCISubCalibrator_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCISubCalibrator_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISubCalibrator");
    private long swigCPtr;

}
