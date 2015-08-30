// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCISonosPlaylist extends SCIObj
{

    protected SCISonosPlaylist(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISonosPlaylistUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISonosPlaylist(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISonosPlaylist(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISonosPlaylist scisonosplaylist)
    {
        long l;
        if(scisonosplaylist == null)
            l = 0L;
        else
            l = scisonosplaylist.swigCPtr;
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

    public String getID()
    {
        return sclibJNI.SCISonosPlaylist_getID(swigCPtr, this);
    }

    public String getTitle()
    {
        return sclibJNI.SCISonosPlaylist_getTitle(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISonosPlaylist");
    private long swigCPtr;

}
