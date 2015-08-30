// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOpAttachPrivateQueue, SCIOpNewPrivateQueue, 
//            SCIDevice, SCIEnumerator

public class SCIZoneGroup extends SCIObj
{

    protected SCIZoneGroup(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIZoneGroupUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIZoneGroup(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIZoneGroup(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIZoneGroup scizonegroup)
    {
        long l;
        if(scizonegroup == null)
            l = 0L;
        else
            l = scizonegroup.swigCPtr;
        return l;
    }

    public SCIOpAttachPrivateQueue createAttachPrivateQueueOp(String s)
    {
        long l = sclibJNI.SCIZoneGroup_createAttachPrivateQueueOp(swigCPtr, this, s);
        SCIOpAttachPrivateQueue sciopattachprivatequeue;
        if(l == 0L)
            sciopattachprivatequeue = null;
        else
            sciopattachprivatequeue = new SCIOpAttachPrivateQueue(l, true);
        return sciopattachprivatequeue;
    }

    public SCIOpNewPrivateQueue createNewPrivateQueueOp(String s, String s1, String s2)
    {
        long l = sclibJNI.SCIZoneGroup_createNewPrivateQueueOp(swigCPtr, this, s, s1, s2);
        SCIOpNewPrivateQueue sciopnewprivatequeue;
        if(l == 0L)
            sciopnewprivatequeue = null;
        else
            sciopnewprivatequeue = new SCIOpNewPrivateQueue(l, true);
        return sciopnewprivatequeue;
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

    public SCIDevice getDevice(String s)
    {
        long l = sclibJNI.SCIZoneGroup_getDevice(swigCPtr, this, s);
        SCIDevice scidevice;
        if(l == 0L)
            scidevice = null;
        else
            scidevice = new SCIDevice(l, true);
        return scidevice;
    }

    public SCIEnumerator getDevices()
    {
        long l = sclibJNI.SCIZoneGroup_getDevices(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getID()
    {
        return sclibJNI.SCIZoneGroup_getID(swigCPtr, this);
    }

    public SCIDevice getMasterDevice()
    {
        long l = sclibJNI.SCIZoneGroup_getMasterDevice(swigCPtr, this);
        SCIDevice scidevice;
        if(l == 0L)
            scidevice = null;
        else
            scidevice = new SCIDevice(l, true);
        return scidevice;
    }

    public boolean isCompatible()
    {
        return sclibJNI.SCIZoneGroup_isCompatible(swigCPtr, this);
    }

    public boolean isCompatibleAndVisible()
    {
        return sclibJNI.SCIZoneGroup_isCompatibleAndVisible(swigCPtr, this);
    }

    public boolean isUnbondedSUB()
    {
        return sclibJNI.SCIZoneGroup_isUnbondedSUB(swigCPtr, this);
    }

    public boolean isUnconfigured()
    {
        return sclibJNI.SCIZoneGroup_isUnconfigured(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIZoneGroup");
    private long swigCPtr;

}
