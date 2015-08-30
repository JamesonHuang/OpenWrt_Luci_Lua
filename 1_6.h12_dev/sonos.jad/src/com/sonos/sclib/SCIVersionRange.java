// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIVersion

public class SCIVersionRange extends SCIObj
{

    protected SCIVersionRange(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIVersionRangeUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIVersionRange(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIVersionRange(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIVersionRange sciversionrange)
    {
        long l;
        if(sciversionrange == null)
            l = 0L;
        else
            l = sciversionrange.swigCPtr;
        return l;
    }

    public boolean containsVersion(SCIVersion sciversion)
    {
        return sclibJNI.SCIVersionRange_containsVersion(swigCPtr, this, SCIVersion.getCPtr(sciversion), sciversion);
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

    public SCIVersion maxVersion()
    {
        long l = sclibJNI.SCIVersionRange_maxVersion(swigCPtr, this);
        SCIVersion sciversion;
        if(l == 0L)
            sciversion = null;
        else
            sciversion = new SCIVersion(l, true);
        return sciversion;
    }

    public SCIVersion minVersion()
    {
        long l = sclibJNI.SCIVersionRange_minVersion(swigCPtr, this);
        SCIVersion sciversion;
        if(l == 0L)
            sciversion = null;
        else
            sciversion = new SCIVersion(l, true);
        return sciversion;
    }

    public boolean overlapsWith(SCIVersionRange sciversionrange)
    {
        return sclibJNI.SCIVersionRange_overlapsWith(swigCPtr, this, getCPtr(sciversionrange), sciversionrange);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIVersionRange");
    private long swigCPtr;

}
