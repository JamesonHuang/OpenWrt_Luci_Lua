// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIGetSonosPlaylistsCB, sclibJNI

public class SCIGetSonosPlaylistsCBSwigBase extends SCIGetSonosPlaylistsCB
{

    public SCIGetSonosPlaylistsCBSwigBase()
    {
        SCIGetSonosPlaylistsCBSwigBase(sclibJNI.new_SCIGetSonosPlaylistsCBSwigBase(), true);
        sclibJNI.SCIGetSonosPlaylistsCBSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIGetSonosPlaylistsCBSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIGetSonosPlaylistsCB(sclibJNI.SWIGSCIGetSonosPlaylistsCBSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIGetSonosPlaylistsCBSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIGetSonosPlaylistsCBSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIGetSonosPlaylistsCBSwigBase scigetsonosplaylistscbswigbase)
    {
        long l;
        if(scigetsonosplaylistscbswigbase == null)
            l = 0L;
        else
            l = scigetsonosplaylistscbswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIGetSonosPlaylistsCBSwigBase)
            s = sclibJNI.SCIGetSonosPlaylistsCBSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIGetSonosPlaylistsCBSwigBase_dumpSCIObjSwigExplicitSCIGetSonosPlaylistsCBSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIGetSonosPlaylistsCBSwigBase");
    private long swigCPtr;

}
