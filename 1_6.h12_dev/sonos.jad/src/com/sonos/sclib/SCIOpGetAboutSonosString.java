// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI, SCIGetAboutSonosStringCB

public class SCIOpGetAboutSonosString extends SCIOp
{

    protected SCIOpGetAboutSonosString(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpGetAboutSonosStringUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpGetAboutSonosString(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpGetAboutSonosString(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpGetAboutSonosString sciopgetaboutsonosstring)
    {
        long l;
        if(sciopgetaboutsonosstring == null)
            l = 0L;
        else
            l = sciopgetaboutsonosstring.swigCPtr;
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

    public String getAssociatedDeviceIP(boolean flag)
    {
        return sclibJNI.SCIOpGetAboutSonosString_getAssociatedDeviceIP(swigCPtr, this, flag);
    }

    public String getCopyright()
    {
        return sclibJNI.SCIOpGetAboutSonosString_getCopyright(swigCPtr, this);
    }

    public String getCopyrightLegalLink()
    {
        return sclibJNI.SCIOpGetAboutSonosString_getCopyrightLegalLink(swigCPtr, this);
    }

    public String getDevicesString()
    {
        return sclibJNI.SCIOpGetAboutSonosString_getDevicesString(swigCPtr, this);
    }

    public String getInternalVersion(boolean flag)
    {
        return sclibJNI.SCIOpGetAboutSonosString_getInternalVersion(swigCPtr, this, flag);
    }

    public String getMarketingVersion(boolean flag)
    {
        return sclibJNI.SCIOpGetAboutSonosString_getMarketingVersion(swigCPtr, this, flag);
    }

    public String getPartnerCopyrightString()
    {
        return sclibJNI.SCIOpGetAboutSonosString_getPartnerCopyrightString(swigCPtr, this);
    }

    public String getSonosID(boolean flag)
    {
        return sclibJNI.SCIOpGetAboutSonosString_getSonosID(swigCPtr, this, flag);
    }

    public String getString()
    {
        return sclibJNI.SCIOpGetAboutSonosString_getString(swigCPtr, this);
    }

    public void setCallback(SCIGetAboutSonosStringCB scigetaboutsonosstringcb)
    {
        sclibJNI.SCIOpGetAboutSonosString_setCallback(swigCPtr, this, SCIGetAboutSonosStringCB.getCPtr(scigetaboutsonosstringcb), scigetaboutsonosstringcb);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpGetAboutSonosString");
    private long swigCPtr;

}
