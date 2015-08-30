// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public class SCLibLogCallback
{

    public SCLibLogCallback()
    {
        SCLibLogCallback(sclibJNI.new_SCLibLogCallback(), true);
        sclibJNI.SCLibLogCallback_director_connect(this, swigCPtr, true, true);
    }

    protected SCLibLogCallback(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        swigCPtr = l;
        nativeRef = new NativeWeakReferenceHelper(this, nativecleanupinvocation);
    }

    protected SCLibLogCallback(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCLibLogCallback(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCLibLogCallback scliblogcallback)
    {
        long l;
        if(scliblogcallback == null)
            l = 0L;
        else
            l = scliblogcallback.swigCPtr;
        return l;
    }

    public void LogDebugMessage(String s, int i, String s1)
    {
        sclibJNI.SCLibLogCallback_LogDebugMessage(swigCPtr, this, s, i, s1);
    }

    public void dispose()
    {
        if(swigCPtr != 0L)
        {
            if(nativeRef != null)
            {
                NativeWeakReferenceHelper nativeweakreferencehelper = nativeRef;
                nativeRef = null;
                nativeweakreferencehelper.dispose();
            }
            swigCPtr = 0L;
        }
    }

    public NativeWeakReferenceHelper getWeakRefHelper()
    {
        return nativeRef;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCLibLogCallback");
    protected NativeWeakReferenceHelper nativeRef;
    private long swigCPtr;

}
