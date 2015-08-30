// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCILocalMusicBrowseItemInfo, sclibJNI

public class SCILocalMusicBrowseItemInfoSwigBase extends SCILocalMusicBrowseItemInfo
{

    public SCILocalMusicBrowseItemInfoSwigBase()
    {
        SCILocalMusicBrowseItemInfoSwigBase(sclibJNI.new_SCILocalMusicBrowseItemInfoSwigBase(), true);
        sclibJNI.SCILocalMusicBrowseItemInfoSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCILocalMusicBrowseItemInfoSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCILocalMusicBrowseItemInfo(sclibJNI.SWIGSCILocalMusicBrowseItemInfoSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILocalMusicBrowseItemInfoSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILocalMusicBrowseItemInfoSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        long l;
        if(scilocalmusicbrowseiteminfoswigbase == null)
            l = 0L;
        else
            l = scilocalmusicbrowseiteminfoswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCILocalMusicBrowseItemInfoSwigBase)
            s = sclibJNI.SCILocalMusicBrowseItemInfoSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCILocalMusicBrowseItemInfoSwigBase_dumpSCIObjSwigExplicitSCILocalMusicBrowseItemInfoSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILocalMusicBrowseItemInfoSwigBase");
    private long swigCPtr;

}
