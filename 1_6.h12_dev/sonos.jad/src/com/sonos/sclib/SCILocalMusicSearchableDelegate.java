// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIStringArray

public class SCILocalMusicSearchableDelegate extends SCIObj
{

    protected SCILocalMusicSearchableDelegate(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCILocalMusicSearchableDelegateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILocalMusicSearchableDelegate(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILocalMusicSearchableDelegate(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILocalMusicSearchableDelegate scilocalmusicsearchabledelegate)
    {
        long l;
        if(scilocalmusicsearchabledelegate == null)
            l = 0L;
        else
            l = scilocalmusicsearchabledelegate.swigCPtr;
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

    public SCIStringArray getCategoryIDs()
    {
        long l = sclibJNI.SCILocalMusicSearchableDelegate_getCategoryIDs(swigCPtr, this);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public String getLogoURI()
    {
        return sclibJNI.SCILocalMusicSearchableDelegate_getLogoURI(swigCPtr, this);
    }

    public String getTitle()
    {
        return sclibJNI.SCILocalMusicSearchableDelegate_getTitle(swigCPtr, this);
    }

    public String objectIdForSearch(String s, String s1)
    {
        return sclibJNI.SCILocalMusicSearchableDelegate_objectIdForSearch(swigCPtr, this, s, s1);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILocalMusicSearchableDelegate");
    private long swigCPtr;

}
