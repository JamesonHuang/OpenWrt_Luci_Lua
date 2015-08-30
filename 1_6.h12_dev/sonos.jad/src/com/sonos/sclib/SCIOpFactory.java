// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOpLoadAlbumArt, SCIOpLoadLogo, 
//            SCIOpRegEmailOptIn, SCIOpSubmitDiagnostics

public class SCIOpFactory extends SCIObj
{

    protected SCIOpFactory(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIOpFactoryUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpFactory(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpFactory(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpFactory sciopfactory)
    {
        long l;
        if(sciopfactory == null)
            l = 0L;
        else
            l = sciopfactory.swigCPtr;
        return l;
    }

    public SCIOpLoadAlbumArt createLoadAlbumArtOp(String s, boolean flag, int i, int j)
    {
        long l = sclibJNI.SCIOpFactory_createLoadAlbumArtOp(swigCPtr, this, s, flag, i, j);
        SCIOpLoadAlbumArt scioploadalbumart;
        if(l == 0L)
            scioploadalbumart = null;
        else
            scioploadalbumart = new SCIOpLoadAlbumArt(l, true);
        return scioploadalbumart;
    }

    public SCIOpLoadLogo createLoadLogoOp(String s, long l)
    {
        long l1 = sclibJNI.SCIOpFactory_createLoadLogoOp(swigCPtr, this, s, l);
        SCIOpLoadLogo scioploadlogo;
        if(l1 == 0L)
            scioploadlogo = null;
        else
            scioploadlogo = new SCIOpLoadLogo(l1, true);
        return scioploadlogo;
    }

    public SCIOpRegEmailOptIn createRegisterLeadOp(String s, String s1, String s2, int i)
    {
        long l = sclibJNI.SCIOpFactory_createRegisterLeadOp(swigCPtr, this, s, s1, s2, i);
        SCIOpRegEmailOptIn sciopregemailoptin;
        if(l == 0L)
            sciopregemailoptin = null;
        else
            sciopregemailoptin = new SCIOpRegEmailOptIn(l, true);
        return sciopregemailoptin;
    }

    public SCIOpSubmitDiagnostics createSubmitDiagnosticsOp()
    {
        long l = sclibJNI.SCIOpFactory_createSubmitDiagnosticsOp(swigCPtr, this);
        SCIOpSubmitDiagnostics sciopsubmitdiagnostics;
        if(l == 0L)
            sciopsubmitdiagnostics = null;
        else
            sciopsubmitdiagnostics = new SCIOpSubmitDiagnostics(l, true);
        return sciopsubmitdiagnostics;
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpFactory");
    private long swigCPtr;

}
