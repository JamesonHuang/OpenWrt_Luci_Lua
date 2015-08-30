// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIconID, SCRoomID

public class SCIRoomResource extends SCIObj
{

    protected SCIRoomResource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIRoomResourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIRoomResource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIRoomResource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIRoomResource sciroomresource)
    {
        long l;
        if(sciroomresource == null)
            l = 0L;
        else
            l = sciroomresource.swigCPtr;
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

    public SCIconID getIconID()
    {
        return SCIconID.swigToEnum(sclibJNI.SCIRoomResource_getIconID(swigCPtr, this));
    }

    public String getIconURI()
    {
        return sclibJNI.SCIRoomResource_getIconURI(swigCPtr, this);
    }

    public String getName()
    {
        return sclibJNI.SCIRoomResource_getName(swigCPtr, this);
    }

    public SCRoomID getRoomID()
    {
        return SCRoomID.swigToEnum(sclibJNI.SCIRoomResource_getRoomID(swigCPtr, this));
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIRoomResource");
    private long swigCPtr;

}
