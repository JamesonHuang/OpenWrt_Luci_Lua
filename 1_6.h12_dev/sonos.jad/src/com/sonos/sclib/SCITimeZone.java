// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCISystemTime

public class SCITimeZone extends SCIObj
{

    protected SCITimeZone(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCITimeZoneUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCITimeZone(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCITimeZone(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCITimeZone scitimezone)
    {
        long l;
        if(scitimezone == null)
            l = 0L;
        else
            l = scitimezone.swigCPtr;
        return l;
    }

    public SCITimeZone clone()
    {
        long l = sclibJNI.SCITimeZone_clone(swigCPtr, this);
        SCITimeZone scitimezone;
        if(l == 0L)
            scitimezone = null;
        else
            scitimezone = new SCITimeZone(l, true);
        return scitimezone;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
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

    public boolean getAutoAdjustForDST()
    {
        return sclibJNI.SCITimeZone_getAutoAdjustForDST(swigCPtr, this);
    }

    public SCISystemTime getDSTEndTime()
    {
        long l = sclibJNI.SCITimeZone_getDSTEndTime(swigCPtr, this);
        SCISystemTime scisystemtime;
        if(l == 0L)
            scisystemtime = null;
        else
            scisystemtime = new SCISystemTime(l, true);
        return scisystemtime;
    }

    public int getDSTOffset()
    {
        return sclibJNI.SCITimeZone_getDSTOffset(swigCPtr, this);
    }

    public SCISystemTime getDSTStartTime()
    {
        long l = sclibJNI.SCITimeZone_getDSTStartTime(swigCPtr, this);
        SCISystemTime scisystemtime;
        if(l == 0L)
            scisystemtime = null;
        else
            scisystemtime = new SCISystemTime(l, true);
        return scisystemtime;
    }

    public String getDisplayName()
    {
        return sclibJNI.SCITimeZone_getDisplayName(swigCPtr, this);
    }

    public int getIndex()
    {
        return sclibJNI.SCITimeZone_getIndex(swigCPtr, this);
    }

    public String getShortName()
    {
        return sclibJNI.SCITimeZone_getShortName(swigCPtr, this);
    }

    public boolean getSupportsDST()
    {
        return sclibJNI.SCITimeZone_getSupportsDST(swigCPtr, this);
    }

    public int getUTCOffset()
    {
        return sclibJNI.SCITimeZone_getUTCOffset(swigCPtr, this);
    }

    public void setAutoAdjustForDST(boolean flag)
    {
        sclibJNI.SCITimeZone_setAutoAdjustForDST(swigCPtr, this, flag);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCITimeZone");
    private long swigCPtr;

}
