// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpAVTransportGetRemainingSleepTimerDuration extends SCIOp
{

    protected SCIOpAVTransportGetRemainingSleepTimerDuration(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpAVTransportGetRemainingSleepTimerDurationUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpAVTransportGetRemainingSleepTimerDuration(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpAVTransportGetRemainingSleepTimerDuration(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpAVTransportGetRemainingSleepTimerDuration sciopavtransportgetremainingsleeptimerduration)
    {
        long l;
        if(sciopavtransportgetremainingsleeptimerduration == null)
            l = 0L;
        else
            l = sciopavtransportgetremainingsleeptimerduration.swigCPtr;
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

    public long getCurrentSleepTimerGeneration()
    {
        return sclibJNI.SCIOpAVTransportGetRemainingSleepTimerDuration_getCurrentSleepTimerGeneration(swigCPtr, this);
    }

    public String getRemainingSleepTimerDuration()
    {
        return sclibJNI.SCIOpAVTransportGetRemainingSleepTimerDuration_getRemainingSleepTimerDuration(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpAVTransportGetRemainingSleepTimerDuration");
    private long swigCPtr;

}
