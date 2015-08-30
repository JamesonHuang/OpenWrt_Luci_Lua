// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIconID

public class SCIAudioInputResource extends SCIObj
{

    protected SCIAudioInputResource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIAudioInputResourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAudioInputResource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAudioInputResource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAudioInputResource sciaudioinputresource)
    {
        long l;
        if(sciaudioinputresource == null)
            l = 0L;
        else
            l = sciaudioinputresource.swigCPtr;
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

    public boolean doesSetAutoPlayGroup()
    {
        return sclibJNI.SCIAudioInputResource_doesSetAutoPlayGroup(swigCPtr, this);
    }

    public SCIconID getIconID()
    {
        return SCIconID.swigToEnum(sclibJNI.SCIAudioInputResource_getIconID(swigCPtr, this));
    }

    public String getLineID()
    {
        return sclibJNI.SCIAudioInputResource_getLineID(swigCPtr, this);
    }

    public String getName()
    {
        return sclibJNI.SCIAudioInputResource_getName(swigCPtr, this);
    }

    public int getVolume()
    {
        return sclibJNI.SCIAudioInputResource_getVolume(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAudioInputResource");
    private long swigCPtr;

}
