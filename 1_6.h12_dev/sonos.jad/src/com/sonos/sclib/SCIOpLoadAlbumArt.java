// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpLoadAlbumArt extends SCIOp
{

    protected SCIOpLoadAlbumArt(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpLoadAlbumArtUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpLoadAlbumArt(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpLoadAlbumArt(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpLoadAlbumArt scioploadalbumart)
    {
        long l;
        if(scioploadalbumart == null)
            l = 0L;
        else
            l = scioploadalbumart.swigCPtr;
        return l;
    }

    public boolean dataLoaded()
    {
        return sclibJNI.SCIOpLoadAlbumArt_dataLoaded(swigCPtr, this);
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

    public long getData(byte abyte0[])
    {
        return sclibJNI.SCIOpLoadAlbumArt_getData(swigCPtr, this, abyte0);
    }

    public long getDataSize()
    {
        return sclibJNI.SCIOpLoadAlbumArt_getDataSize(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpLoadAlbumArt");
    private long swigCPtr;

}
