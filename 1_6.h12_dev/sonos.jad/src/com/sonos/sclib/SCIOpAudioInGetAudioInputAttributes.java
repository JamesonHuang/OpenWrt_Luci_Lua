// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpAudioInGetAudioInputAttributes extends SCIOp
{

    protected SCIOpAudioInGetAudioInputAttributes(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpAudioInGetAudioInputAttributesUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpAudioInGetAudioInputAttributes(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpAudioInGetAudioInputAttributes(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpAudioInGetAudioInputAttributes sciopaudioingetaudioinputattributes)
    {
        long l;
        if(sciopaudioingetaudioinputattributes == null)
            l = 0L;
        else
            l = sciopaudioingetaudioinputattributes.swigCPtr;
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

    public String getCurrentIcon()
    {
        return sclibJNI.SCIOpAudioInGetAudioInputAttributes_getCurrentIcon(swigCPtr, this);
    }

    public String getCurrentName()
    {
        return sclibJNI.SCIOpAudioInGetAudioInputAttributes_getCurrentName(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpAudioInGetAudioInputAttributes");
    private long swigCPtr;

}
