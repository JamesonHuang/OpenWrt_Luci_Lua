// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIGetAboutSonosStringCB, sclibJNI

public class SCIGetAboutSonosStringCBSwigBase extends SCIGetAboutSonosStringCB
{

    public SCIGetAboutSonosStringCBSwigBase()
    {
        SCIGetAboutSonosStringCBSwigBase(sclibJNI.new_SCIGetAboutSonosStringCBSwigBase(), true);
        sclibJNI.SCIGetAboutSonosStringCBSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIGetAboutSonosStringCBSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIGetAboutSonosStringCB(sclibJNI.SWIGSCIGetAboutSonosStringCBSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIGetAboutSonosStringCBSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIGetAboutSonosStringCBSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIGetAboutSonosStringCBSwigBase scigetaboutsonosstringcbswigbase)
    {
        long l;
        if(scigetaboutsonosstringcbswigbase == null)
            l = 0L;
        else
            l = scigetaboutsonosstringcbswigbase.swigCPtr;
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

    public String dumpSCIObj()
    {
        String s;
        if(getClass() == com/sonos/sclib/SCIGetAboutSonosStringCBSwigBase)
            s = sclibJNI.SCIGetAboutSonosStringCBSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIGetAboutSonosStringCBSwigBase_dumpSCIObjSwigExplicitSCIGetAboutSonosStringCBSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIGetAboutSonosStringCBSwigBase");
    private long swigCPtr;

}
