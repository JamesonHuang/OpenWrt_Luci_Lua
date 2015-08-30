// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIArtworkData

public class SCIArtworkCache extends SCIObj
{

    protected SCIArtworkCache(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIArtworkCacheUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIArtworkCache(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIArtworkCache(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIArtworkCache sciartworkcache)
    {
        long l;
        if(sciartworkcache == null)
            l = 0L;
        else
            l = sciartworkcache.swigCPtr;
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

    public void emptyCache()
    {
        sclibJNI.SCIArtworkCache_emptyCache(swigCPtr, this);
    }

    public long getTotalSizeInBytes()
    {
        return sclibJNI.SCIArtworkCache_getTotalSizeInBytes(swigCPtr, this);
    }

    public SCIArtworkData requestArtwork(String s, SCIBrowseItem.SCAlbumArtType scalbumarttype)
    {
        long l = sclibJNI.SCIArtworkCache_requestArtwork(swigCPtr, this, s, scalbumarttype.swigValue());
        SCIArtworkData sciartworkdata;
        if(l == 0L)
            sciartworkdata = null;
        else
            sciartworkdata = new SCIArtworkData(l, true);
        return sciartworkdata;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIArtworkCache");
    private long swigCPtr;

}
