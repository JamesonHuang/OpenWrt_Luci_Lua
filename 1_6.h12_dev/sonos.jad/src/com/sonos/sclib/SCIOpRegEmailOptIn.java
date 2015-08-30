// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpRegEmailOptIn extends SCIOp
{
    public static final class RegEmailOptInResult extends Enum
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


        public static RegEmailOptInResult swigToEnum(int i)
        {
            RegEmailOptInResult aregemailoptinresult[] = (RegEmailOptInResult[])com/sonos/sclib/SCIOpRegEmailOptIn$RegEmailOptInResult.getEnumConstants();
            if(i >= aregemailoptinresult.length || i < 0 || aregemailoptinresult[i].swigValue != i) goto _L2; else goto _L1
_L1:
            RegEmailOptInResult regemailoptinresult1 = aregemailoptinresult[i];
_L4:
            return regemailoptinresult1;
_L2:
            int j = aregemailoptinresult.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                RegEmailOptInResult regemailoptinresult = aregemailoptinresult[k];
                if(regemailoptinresult.swigValue == i)
                {
                    regemailoptinresult1 = regemailoptinresult;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIOpRegEmailOptIn$RegEmailOptInResult).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static RegEmailOptInResult valueOf(String s)
        {
            return (RegEmailOptInResult)Enum.valueOf(com/sonos/sclib/SCIOpRegEmailOptIn$RegEmailOptInResult, s);
        }

        public static RegEmailOptInResult[] values()
        {
            return (RegEmailOptInResult[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final RegEmailOptInResult $VALUES[];
        public static final RegEmailOptInResult REOIOP_RESULT_BAD_EMAIL;
        public static final RegEmailOptInResult REOIOP_RESULT_ERROR;
        public static final RegEmailOptInResult REOIOP_RESULT_FAILURE;
        public static final RegEmailOptInResult REOIOP_RESULT_IN_PROGRESS;
        public static final RegEmailOptInResult REOIOP_RESULT_NOTSTARTED;
        public static final RegEmailOptInResult REOIOP_RESULT_SUCCESS;
        private final int swigValue;

        static 
        {
            REOIOP_RESULT_SUCCESS = new RegEmailOptInResult("REOIOP_RESULT_SUCCESS", 0, sclibJNI.SCIOpRegEmailOptIn_REOIOP_RESULT_SUCCESS_get());
            REOIOP_RESULT_NOTSTARTED = new RegEmailOptInResult("REOIOP_RESULT_NOTSTARTED", 1);
            REOIOP_RESULT_IN_PROGRESS = new RegEmailOptInResult("REOIOP_RESULT_IN_PROGRESS", 2);
            REOIOP_RESULT_FAILURE = new RegEmailOptInResult("REOIOP_RESULT_FAILURE", 3);
            REOIOP_RESULT_BAD_EMAIL = new RegEmailOptInResult("REOIOP_RESULT_BAD_EMAIL", 4);
            REOIOP_RESULT_ERROR = new RegEmailOptInResult("REOIOP_RESULT_ERROR", 5);
            RegEmailOptInResult aregemailoptinresult[] = new RegEmailOptInResult[6];
            aregemailoptinresult[0] = REOIOP_RESULT_SUCCESS;
            aregemailoptinresult[1] = REOIOP_RESULT_NOTSTARTED;
            aregemailoptinresult[2] = REOIOP_RESULT_IN_PROGRESS;
            aregemailoptinresult[3] = REOIOP_RESULT_FAILURE;
            aregemailoptinresult[4] = REOIOP_RESULT_BAD_EMAIL;
            aregemailoptinresult[5] = REOIOP_RESULT_ERROR;
            $VALUES = aregemailoptinresult;
        }

        private RegEmailOptInResult(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private RegEmailOptInResult(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private RegEmailOptInResult(String s, int i, RegEmailOptInResult regemailoptinresult)
        {
            Enum(s, i);
            swigValue = regemailoptinresult.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIOpRegEmailOptIn(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpRegEmailOptInUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpRegEmailOptIn(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpRegEmailOptIn(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpRegEmailOptIn sciopregemailoptin)
    {
        long l;
        if(sciopregemailoptin == null)
            l = 0L;
        else
            l = sciopregemailoptin.swigCPtr;
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

    public int getOpResult()
    {
        return sclibJNI.SCIOpRegEmailOptIn_getOpResult(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpRegEmailOptIn");
    private long swigCPtr;

}
