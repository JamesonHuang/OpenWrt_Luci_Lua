// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCITrackInfo extends SCIObj
{

    protected SCITrackInfo(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCITrackInfoUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCITrackInfo(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCITrackInfo(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCITrackInfo scitrackinfo)
    {
        long l;
        if(scitrackinfo == null)
            l = 0L;
        else
            l = scitrackinfo.swigCPtr;
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

    public String getAlbum()
    {
        return sclibJNI.SCITrackInfo_getAlbum(swigCPtr, this);
    }

    public String getArtist()
    {
        return sclibJNI.SCITrackInfo_getArtist(swigCPtr, this);
    }

    public long getDuration()
    {
        return sclibJNI.SCITrackInfo_getDuration(swigCPtr, this);
    }

    public String getId()
    {
        return sclibJNI.SCITrackInfo_getId(swigCPtr, this);
    }

    public String getResUri()
    {
        return sclibJNI.SCITrackInfo_getResUri(swigCPtr, this);
    }

    public String getTitle()
    {
        return sclibJNI.SCITrackInfo_getTitle(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCITrackInfo");
    private long swigCPtr;

}
