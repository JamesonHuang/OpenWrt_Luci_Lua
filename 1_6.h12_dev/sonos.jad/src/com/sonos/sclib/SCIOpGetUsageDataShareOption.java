// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpGetUsageDataShareOption extends SCIOp
{

    protected SCIOpGetUsageDataShareOption(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpGetUsageDataShareOptionUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpGetUsageDataShareOption(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpGetUsageDataShareOption(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpGetUsageDataShareOption sciopgetusagedatashareoption)
    {
        long l;
        if(sciopgetusagedatashareoption == null)
            l = 0L;
        else
            l = sciopgetusagedatashareoption.swigCPtr;
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

    public boolean isUsageDataShareEnabled()
    {
        return sclibJNI.SCIOpGetUsageDataShareOption_isUsageDataShareEnabled(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpGetUsageDataShareOption");
    private long swigCPtr;

}
