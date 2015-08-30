// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOp, SCIOpAVTransportGetRemainingSleepTimerDuration

public class SCINowPlayingSleepTimer extends SCIObj
{

    protected SCINowPlayingSleepTimer(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCINowPlayingSleepTimerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINowPlayingSleepTimer(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINowPlayingSleepTimer(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINowPlayingSleepTimer scinowplayingsleeptimer)
    {
        long l;
        if(scinowplayingsleeptimer == null)
            l = 0L;
        else
            l = scinowplayingsleeptimer.swigCPtr;
        return l;
    }

    public SCIOp createConfigureSleepTimerOp(String s)
    {
        long l = sclibJNI.SCINowPlayingSleepTimer_createConfigureSleepTimerOp(swigCPtr, this, s);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOpAVTransportGetRemainingSleepTimerDuration createGetRemainingSleepTimerDurationOp()
    {
        long l = sclibJNI.SCINowPlayingSleepTimer_createGetRemainingSleepTimerDurationOp(swigCPtr, this);
        SCIOpAVTransportGetRemainingSleepTimerDuration sciopavtransportgetremainingsleeptimerduration;
        if(l == 0L)
            sciopavtransportgetremainingsleeptimerduration = null;
        else
            sciopavtransportgetremainingsleeptimerduration = new SCIOpAVTransportGetRemainingSleepTimerDuration(l, true);
        return sciopavtransportgetremainingsleeptimerduration;
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINowPlayingSleepTimer");
    private long swigCPtr;

}
