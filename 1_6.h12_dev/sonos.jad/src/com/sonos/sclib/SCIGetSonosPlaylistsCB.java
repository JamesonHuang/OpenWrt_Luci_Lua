// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIGetSonosPlaylistsCB extends SCIObj
{

    protected SCIGetSonosPlaylistsCB(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIGetSonosPlaylistsCBUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIGetSonosPlaylistsCB(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIGetSonosPlaylistsCB(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIGetSonosPlaylistsCB scigetsonosplaylistscb)
    {
        long l;
        if(scigetsonosplaylistscb == null)
            l = 0L;
        else
            l = scigetsonosplaylistscb.swigCPtr;
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

    public void getSonosPlaylistsFailed()
    {
        sclibJNI.SCIGetSonosPlaylistsCB_getSonosPlaylistsFailed(swigCPtr, this);
    }

    public void getSonosPlaylistsSucceeded(String s)
    {
        sclibJNI.SCIGetSonosPlaylistsCB_getSonosPlaylistsSucceeded(swigCPtr, this, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIGetSonosPlaylistsCB");
    private long swigCPtr;

}
