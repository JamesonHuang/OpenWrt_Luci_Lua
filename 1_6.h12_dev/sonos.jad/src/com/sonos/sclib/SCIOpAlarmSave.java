// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpAlarmSave extends SCIOp
{

    protected SCIOpAlarmSave(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpAlarmSaveUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpAlarmSave(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpAlarmSave(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpAlarmSave sciopalarmsave)
    {
        long l;
        if(sciopalarmsave == null)
            l = 0L;
        else
            l = sciopalarmsave.swigCPtr;
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

    public int getAssignedID()
    {
        return sclibJNI.SCIOpAlarmSave_getAssignedID(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpAlarmSave");
    private long swigCPtr;

}
