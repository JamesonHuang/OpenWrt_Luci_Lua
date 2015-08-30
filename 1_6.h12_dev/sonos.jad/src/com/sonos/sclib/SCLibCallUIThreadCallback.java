// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public class SCLibCallUIThreadCallback
{

    public SCLibCallUIThreadCallback()
    {
        SCLibCallUIThreadCallback(sclibJNI.new_SCLibCallUIThreadCallback(), true);
        sclibJNI.SCLibCallUIThreadCallback_director_connect(this, swigCPtr, true, true);
    }

    protected SCLibCallUIThreadCallback(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        swigCPtr = l;
        nativeRef = new NativeWeakReferenceHelper(this, nativecleanupinvocation);
    }

    protected SCLibCallUIThreadCallback(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCLibCallUIThreadCallback(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCLibCallUIThreadCallback sclibcalluithreadcallback)
    {
        long l;
        if(sclibcalluithreadcallback == null)
            l = 0L;
        else
            l = sclibcalluithreadcallback.swigCPtr;
        return l;
    }

    public void callSCLibOnUIThread()
    {
        sclibJNI.SCLibCallUIThreadCallback_callSCLibOnUIThread(swigCPtr, this);
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCLibCallUIThreadCallback");
    protected NativeWeakReferenceHelper nativeRef;
    private long swigCPtr;

}
