// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpConnectionManagerGetProtocolInfo extends SCIOp
{

    protected SCIOpConnectionManagerGetProtocolInfo(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpConnectionManagerGetProtocolInfoUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpConnectionManagerGetProtocolInfo(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpConnectionManagerGetProtocolInfo(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpConnectionManagerGetProtocolInfo sciopconnectionmanagergetprotocolinfo)
    {
        long l;
        if(sciopconnectionmanagergetprotocolinfo == null)
            l = 0L;
        else
            l = sciopconnectionmanagergetprotocolinfo.swigCPtr;
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

    public String getSink()
    {
        return sclibJNI.SCIOpConnectionManagerGetProtocolInfo_getSink(swigCPtr, this);
    }

    public String getSource()
    {
        return sclibJNI.SCIOpConnectionManagerGetProtocolInfo_getSource(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpConnectionManagerGetProtocolInfo");
    private long swigCPtr;

}
