// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public class SCLibTruncatedStringsCallback
{

    public SCLibTruncatedStringsCallback()
    {
        SCLibTruncatedStringsCallback(sclibJNI.new_SCLibTruncatedStringsCallback(), true);
        sclibJNI.SCLibTruncatedStringsCallback_director_connect(this, swigCPtr, true, true);
    }

    protected SCLibTruncatedStringsCallback(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        swigCPtr = l;
        nativeRef = new NativeWeakReferenceHelper(this, nativecleanupinvocation);
    }

    protected SCLibTruncatedStringsCallback(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCLibTruncatedStringsCallback(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCLibTruncatedStringsCallback sclibtruncatedstringscallback)
    {
        long l;
        if(sclibtruncatedstringscallback == null)
            l = 0L;
        else
            l = sclibtruncatedstringscallback.swigCPtr;
        return l;
    }

    public void clearTruncatedStrings()
    {
        sclibJNI.SCLibTruncatedStringsCallback_clearTruncatedStrings(swigCPtr, this);
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

    public String getTruncatedStrings()
    {
        return sclibJNI.SCLibTruncatedStringsCallback_getTruncatedStrings(swigCPtr, this);
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCLibTruncatedStringsCallback");
    protected NativeWeakReferenceHelper nativeRef;
    private long swigCPtr;

}
