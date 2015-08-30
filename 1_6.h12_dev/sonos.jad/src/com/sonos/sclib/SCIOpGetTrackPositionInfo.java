// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpGetTrackPositionInfo extends SCIOp
{

    protected SCIOpGetTrackPositionInfo(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpGetTrackPositionInfoUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpGetTrackPositionInfo(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpGetTrackPositionInfo(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpGetTrackPositionInfo sciopgettrackpositioninfo)
    {
        long l;
        if(sciopgettrackpositioninfo == null)
            l = 0L;
        else
            l = sciopgettrackpositioninfo.swigCPtr;
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

    public long getTrackDurationInSecs()
    {
        return sclibJNI.SCIOpGetTrackPositionInfo_getTrackDurationInSecs(swigCPtr, this);
    }

    public long getTrackIndex()
    {
        return sclibJNI.SCIOpGetTrackPositionInfo_getTrackIndex(swigCPtr, this);
    }

    public long getTrackPositionInSecs()
    {
        return sclibJNI.SCIOpGetTrackPositionInfo_getTrackPositionInSecs(swigCPtr, this);
    }

    public String getTrackURI()
    {
        return sclibJNI.SCIOpGetTrackPositionInfo_getTrackURI(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpGetTrackPositionInfo");
    private long swigCPtr;

}
