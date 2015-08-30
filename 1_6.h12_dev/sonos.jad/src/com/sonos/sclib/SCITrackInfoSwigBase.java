// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCITrackInfo, sclibJNI

public class SCITrackInfoSwigBase extends SCITrackInfo
{

    public SCITrackInfoSwigBase()
    {
        SCITrackInfoSwigBase(sclibJNI.new_SCITrackInfoSwigBase(), true);
        sclibJNI.SCITrackInfoSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCITrackInfoSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCITrackInfo(sclibJNI.SWIGSCITrackInfoSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCITrackInfoSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCITrackInfoSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCITrackInfoSwigBase scitrackinfoswigbase)
    {
        long l;
        if(scitrackinfoswigbase == null)
            l = 0L;
        else
            l = scitrackinfoswigbase.swigCPtr;
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

    public String dumpSCIObj()
    {
        String s;
        if(getClass() == com/sonos/sclib/SCITrackInfoSwigBase)
            s = sclibJNI.SCITrackInfoSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCITrackInfoSwigBase_dumpSCIObjSwigExplicitSCITrackInfoSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCITrackInfoSwigBase");
    private long swigCPtr;

}
