// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public class SCLibDiagnosticExtraInfoCallback
{

    public SCLibDiagnosticExtraInfoCallback()
    {
        SCLibDiagnosticExtraInfoCallback(sclibJNI.new_SCLibDiagnosticExtraInfoCallback(), true);
        sclibJNI.SCLibDiagnosticExtraInfoCallback_director_connect(this, swigCPtr, true, true);
    }

    protected SCLibDiagnosticExtraInfoCallback(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        swigCPtr = l;
        nativeRef = new NativeWeakReferenceHelper(this, nativecleanupinvocation);
    }

    protected SCLibDiagnosticExtraInfoCallback(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCLibDiagnosticExtraInfoCallback(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCLibDiagnosticExtraInfoCallback sclibdiagnosticextrainfocallback)
    {
        long l;
        if(sclibdiagnosticextrainfocallback == null)
            l = 0L;
        else
            l = sclibdiagnosticextrainfocallback.swigCPtr;
        return l;
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

    public String getDiagnosticExtraInfo()
    {
        return sclibJNI.SCLibDiagnosticExtraInfoCallback_getDiagnosticExtraInfo(swigCPtr, this);
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCLibDiagnosticExtraInfoCallback");
    protected NativeWeakReferenceHelper nativeRef;
    private long swigCPtr;

}
