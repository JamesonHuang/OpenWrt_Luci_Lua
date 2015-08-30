// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCILocalMusicSearchableDelegate, sclibJNI

public class SCILocalMusicSearchableDelegateSwigBase extends SCILocalMusicSearchableDelegate
{

    public SCILocalMusicSearchableDelegateSwigBase()
    {
        SCILocalMusicSearchableDelegateSwigBase(sclibJNI.new_SCILocalMusicSearchableDelegateSwigBase(), true);
        sclibJNI.SCILocalMusicSearchableDelegateSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCILocalMusicSearchableDelegateSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCILocalMusicSearchableDelegate(sclibJNI.SWIGSCILocalMusicSearchableDelegateSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILocalMusicSearchableDelegateSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILocalMusicSearchableDelegateSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase)
    {
        long l;
        if(scilocalmusicsearchabledelegateswigbase == null)
            l = 0L;
        else
            l = scilocalmusicsearchabledelegateswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCILocalMusicSearchableDelegateSwigBase)
            s = sclibJNI.SCILocalMusicSearchableDelegateSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCILocalMusicSearchableDelegateSwigBase_dumpSCIObjSwigExplicitSCILocalMusicSearchableDelegateSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILocalMusicSearchableDelegateSwigBase");
    private long swigCPtr;

}
