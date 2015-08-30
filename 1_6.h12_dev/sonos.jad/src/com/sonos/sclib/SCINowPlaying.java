// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIZoneGroup, SCIEventSink

public class SCINowPlaying extends SCIObj
{

    protected SCINowPlaying(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCINowPlayingUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINowPlaying(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINowPlaying(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINowPlaying scinowplaying)
    {
        long l;
        if(scinowplaying == null)
            l = 0L;
        else
            l = scinowplaying.swigCPtr;
        return l;
    }

    public static SCINowPlaying getInterface(SCIZoneGroup scizonegroup)
    {
        long l = sclibJNI.SCINowPlaying_getInterface(SCIZoneGroup.getCPtr(scizonegroup), scizonegroup);
        SCINowPlaying scinowplaying;
        if(l == 0L)
            scinowplaying = null;
        else
            scinowplaying = new SCINowPlaying(l, true);
        return scinowplaying;
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

    public String getGroupID()
    {
        return sclibJNI.SCINowPlaying_getGroupID(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCINowPlaying_isValid(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCINowPlaying_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCINowPlaying_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINowPlaying");
    private long swigCPtr;

}
