// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIData, sclibJNI

public class SCIAudioData extends SCIData
{

    protected SCIAudioData(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIData(sclibJNI.SWIGSCIAudioDataUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAudioData(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAudioData(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAudioData sciaudiodata)
    {
        long l;
        if(sciaudiodata == null)
            l = 0L;
        else
            l = sciaudiodata.swigCPtr;
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

    public int getTimeSeekOffset(long l)
    {
        return sclibJNI.SCIAudioData_getTimeSeekOffset(swigCPtr, this, l);
    }

    public long getTotalTime()
    {
        return sclibJNI.SCIAudioData_getTotalTime(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAudioData");
    private long swigCPtr;

}
