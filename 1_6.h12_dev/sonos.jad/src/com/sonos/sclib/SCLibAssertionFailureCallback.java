// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public class SCLibAssertionFailureCallback
{

    public SCLibAssertionFailureCallback()
    {
        SCLibAssertionFailureCallback(sclibJNI.new_SCLibAssertionFailureCallback(), true);
        sclibJNI.SCLibAssertionFailureCallback_director_connect(this, swigCPtr, true, true);
    }

    protected SCLibAssertionFailureCallback(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        swigCPtr = l;
        nativeRef = new NativeWeakReferenceHelper(this, nativecleanupinvocation);
    }

    protected SCLibAssertionFailureCallback(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCLibAssertionFailureCallback(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCLibAssertionFailureCallback sclibassertionfailurecallback)
    {
        long l;
        if(sclibassertionfailurecallback == null)
            l = 0L;
        else
            l = sclibassertionfailurecallback.swigCPtr;
        return l;
    }

    public void assertionFailed(String s, int i, String s1)
    {
        sclibJNI.SCLibAssertionFailureCallback_assertionFailed(swigCPtr, this, s, i, s1);
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCLibAssertionFailureCallback");
    protected NativeWeakReferenceHelper nativeRef;
    private long swigCPtr;

}
