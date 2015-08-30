// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIWizard, sclibJNI

public class SCISonosNetSetupWizard extends SCIWizard
{
    public static final class SonosNetSetupWizardState extends Enum
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


        public static SonosNetSetupWizardState swigToEnum(int i)
        {
            SonosNetSetupWizardState asonosnetsetupwizardstate[] = (SonosNetSetupWizardState[])com/sonos/sclib/SCISonosNetSetupWizard$SonosNetSetupWizardState.getEnumConstants();
            if(i >= asonosnetsetupwizardstate.length || i < 0 || asonosnetsetupwizardstate[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SonosNetSetupWizardState sonosnetsetupwizardstate1 = asonosnetsetupwizardstate[i];
_L4:
            return sonosnetsetupwizardstate1;
_L2:
            int j = asonosnetsetupwizardstate.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SonosNetSetupWizardState sonosnetsetupwizardstate = asonosnetsetupwizardstate[k];
                if(sonosnetsetupwizardstate.swigValue == i)
                {
                    sonosnetsetupwizardstate1 = sonosnetsetupwizardstate;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCISonosNetSetupWizard$SonosNetSetupWizardState).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SonosNetSetupWizardState valueOf(String s)
        {
            return (SonosNetSetupWizardState)Enum.valueOf(com/sonos/sclib/SCISonosNetSetupWizard$SonosNetSetupWizardState, s);
        }

        public static SonosNetSetupWizardState[] values()
        {
            return (SonosNetSetupWizardState[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SonosNetSetupWizardState $VALUES[];
        public static final SonosNetSetupWizardState STATE_SONOSNETSETUP_CHOOSEOPTION;
        public static final SonosNetSetupWizardState STATE_SONOSNETSETUP_COMPLETE;
        public static final SonosNetSetupWizardState STATE_SONOSNETSETUP_DETAILS;
        public static final SonosNetSetupWizardState STATE_SONOSNETSETUP_INIT;
        public static final SonosNetSetupWizardState STATE_SONOSNETSETUP_MAX;
        public static final SonosNetSetupWizardState STATE_SONOSNETSETUP_UNKNOWN;
        private final int swigValue;

        static 
        {
            STATE_SONOSNETSETUP_UNKNOWN = new SonosNetSetupWizardState("STATE_SONOSNETSETUP_UNKNOWN", 0, sclibJNI.SCISonosNetSetupWizard_STATE_SONOSNETSETUP_UNKNOWN_get());
            STATE_SONOSNETSETUP_INIT = new SonosNetSetupWizardState("STATE_SONOSNETSETUP_INIT", 1, sclibJNI.SCISonosNetSetupWizard_STATE_SONOSNETSETUP_INIT_get());
            STATE_SONOSNETSETUP_COMPLETE = new SonosNetSetupWizardState("STATE_SONOSNETSETUP_COMPLETE", 2, sclibJNI.SCISonosNetSetupWizard_STATE_SONOSNETSETUP_COMPLETE_get());
            STATE_SONOSNETSETUP_DETAILS = new SonosNetSetupWizardState("STATE_SONOSNETSETUP_DETAILS", 3);
            STATE_SONOSNETSETUP_CHOOSEOPTION = new SonosNetSetupWizardState("STATE_SONOSNETSETUP_CHOOSEOPTION", 4);
            STATE_SONOSNETSETUP_MAX = new SonosNetSetupWizardState("STATE_SONOSNETSETUP_MAX", 5);
            SonosNetSetupWizardState asonosnetsetupwizardstate[] = new SonosNetSetupWizardState[6];
            asonosnetsetupwizardstate[0] = STATE_SONOSNETSETUP_UNKNOWN;
            asonosnetsetupwizardstate[1] = STATE_SONOSNETSETUP_INIT;
            asonosnetsetupwizardstate[2] = STATE_SONOSNETSETUP_COMPLETE;
            asonosnetsetupwizardstate[3] = STATE_SONOSNETSETUP_DETAILS;
            asonosnetsetupwizardstate[4] = STATE_SONOSNETSETUP_CHOOSEOPTION;
            asonosnetsetupwizardstate[5] = STATE_SONOSNETSETUP_MAX;
            $VALUES = asonosnetsetupwizardstate;
        }

        private SonosNetSetupWizardState(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SonosNetSetupWizardState(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SonosNetSetupWizardState(String s, int i, SonosNetSetupWizardState sonosnetsetupwizardstate)
        {
            Enum(s, i);
            swigValue = sonosnetsetupwizardstate.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCISonosNetSetupWizard(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIWizard(sclibJNI.SWIGSCISonosNetSetupWizardUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISonosNetSetupWizard(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISonosNetSetupWizard(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISonosNetSetupWizard scisonosnetsetupwizard)
    {
        long l;
        if(scisonosnetsetupwizard == null)
            l = 0L;
        else
            l = scisonosnetsetupwizard.swigCPtr;
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISonosNetSetupWizard");
    private long swigCPtr;

}
