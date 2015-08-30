// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIDealerMode extends SCIObj
{

    protected SCIDealerMode(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDealerModeUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDealerMode(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDealerMode(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDealerMode scidealermode)
    {
        long l;
        if(scidealermode == null)
            l = 0L;
        else
            l = scidealermode.swigCPtr;
        return l;
    }

    public boolean checkStoresAlivePIN(String s)
    {
        return sclibJNI.SCIDealerMode_checkStoresAlivePIN(swigCPtr, this, s);
    }

    public void controllerWokeUp()
    {
        sclibJNI.SCIDealerMode_controllerWokeUp(swigCPtr, this);
    }

    public void deleteAllSonosPlaylists()
    {
        sclibJNI.SCIDealerMode_deleteAllSonosPlaylists(swigCPtr, this);
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

    public boolean isDealerLock()
    {
        return sclibJNI.SCIDealerMode_isDealerLock(swigCPtr, this);
    }

    public boolean isStoresAliveEnabled()
    {
        return sclibJNI.SCIDealerMode_isStoresAliveEnabled__SWIG_1(swigCPtr, this);
    }

    public boolean isStoresAliveEnabled(boolean flag)
    {
        return sclibJNI.SCIDealerMode_isStoresAliveEnabled__SWIG_0(swigCPtr, this, flag);
    }

    public boolean sendControllerAlive()
    {
        return sclibJNI.SCIDealerMode_sendControllerAlive(swigCPtr, this);
    }

    public void setDealerLock(boolean flag)
    {
        sclibJNI.SCIDealerMode_setDealerLock(swigCPtr, this, flag);
    }

    public void setStoresAlive(boolean flag)
    {
        sclibJNI.SCIDealerMode_setStoresAlive(swigCPtr, this, flag);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDealerMode");
    private long swigCPtr;

}
