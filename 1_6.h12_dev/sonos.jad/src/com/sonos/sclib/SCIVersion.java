// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIVersion extends SCIObj
{

    protected SCIVersion(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIVersionUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIVersion(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIVersion(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIVersion sciversion)
    {
        long l;
        if(sciversion == null)
            l = 0L;
        else
            l = sciversion.swigCPtr;
        return l;
    }

    public int compare(SCIVersion sciversion)
    {
        return sclibJNI.SCIVersion_compare(swigCPtr, this, getCPtr(sciversion), sciversion);
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

    public int getBuild()
    {
        return sclibJNI.SCIVersion_getBuild(swigCPtr, this);
    }

    public int getMajor()
    {
        return sclibJNI.SCIVersion_getMajor(swigCPtr, this);
    }

    public int getMinor()
    {
        return sclibJNI.SCIVersion_getMinor(swigCPtr, this);
    }

    public boolean isBeta()
    {
        return sclibJNI.SCIVersion_isBeta(swigCPtr, this);
    }

    public boolean isDiag()
    {
        return sclibJNI.SCIVersion_isDiag(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIVersion");
    private long swigCPtr;

}
