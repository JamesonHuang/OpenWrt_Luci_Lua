// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpAVTransportGetPositionInfo extends SCIOp
{

    protected SCIOpAVTransportGetPositionInfo(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpAVTransportGetPositionInfoUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpAVTransportGetPositionInfo(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpAVTransportGetPositionInfo(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpAVTransportGetPositionInfo sciopavtransportgetpositioninfo)
    {
        long l;
        if(sciopavtransportgetpositioninfo == null)
            l = 0L;
        else
            l = sciopavtransportgetpositioninfo.swigCPtr;
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

    public int getAbsCount()
    {
        return sclibJNI.SCIOpAVTransportGetPositionInfo_getAbsCount(swigCPtr, this);
    }

    public String getAbsTime()
    {
        return sclibJNI.SCIOpAVTransportGetPositionInfo_getAbsTime(swigCPtr, this);
    }

    public int getRelCount()
    {
        return sclibJNI.SCIOpAVTransportGetPositionInfo_getRelCount(swigCPtr, this);
    }

    public String getRelTime()
    {
        return sclibJNI.SCIOpAVTransportGetPositionInfo_getRelTime(swigCPtr, this);
    }

    public long getTrack()
    {
        return sclibJNI.SCIOpAVTransportGetPositionInfo_getTrack(swigCPtr, this);
    }

    public String getTrackDuration()
    {
        return sclibJNI.SCIOpAVTransportGetPositionInfo_getTrackDuration(swigCPtr, this);
    }

    public String getTrackMetaData()
    {
        return sclibJNI.SCIOpAVTransportGetPositionInfo_getTrackMetaData(swigCPtr, this);
    }

    public String getTrackURI()
    {
        return sclibJNI.SCIOpAVTransportGetPositionInfo_getTrackURI(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpAVTransportGetPositionInfo");
    private long swigCPtr;

}
