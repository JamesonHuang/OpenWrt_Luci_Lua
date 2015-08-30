// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpAudioInGetLineInLevel extends SCIOp
{

    protected SCIOpAudioInGetLineInLevel(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpAudioInGetLineInLevelUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpAudioInGetLineInLevel(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpAudioInGetLineInLevel(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpAudioInGetLineInLevel sciopaudioingetlineinlevel)
    {
        long l;
        if(sciopaudioingetlineinlevel == null)
            l = 0L;
        else
            l = sciopaudioingetlineinlevel.swigCPtr;
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

    public int getCurrentLeftLineInLevel()
    {
        return sclibJNI.SCIOpAudioInGetLineInLevel_getCurrentLeftLineInLevel(swigCPtr, this);
    }

    public int getCurrentRightLineInLevel()
    {
        return sclibJNI.SCIOpAudioInGetLineInLevel_getCurrentRightLineInLevel(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpAudioInGetLineInLevel");
    private long swigCPtr;

}
