// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIAlarmMusic extends SCIObj
{

    protected SCIAlarmMusic(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIAlarmMusicUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAlarmMusic(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAlarmMusic(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAlarmMusic scialarmmusic)
    {
        long l;
        if(scialarmmusic == null)
            l = 0L;
        else
            l = scialarmmusic.swigCPtr;
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

    public String getMusicMetadata()
    {
        return sclibJNI.SCIAlarmMusic_getMusicMetadata(swigCPtr, this);
    }

    public String getMusicURI()
    {
        return sclibJNI.SCIAlarmMusic_getMusicURI(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAlarmMusic");
    private long swigCPtr;

}
