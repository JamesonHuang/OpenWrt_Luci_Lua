// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCISystemTime, SCIOp, 
//            SCDateFormat, SCTimeFormat, SCITimeZone, SCITime, 
//            SCTimeStatus, SCIEnumerator, SCIEventSink

public class SCIDateTimeManager extends SCIObj
{

    protected SCIDateTimeManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDateTimeManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDateTimeManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDateTimeManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDateTimeManager scidatetimemanager)
    {
        long l;
        if(scidatetimemanager == null)
            l = 0L;
        else
            l = scidatetimemanager.swigCPtr;
        return l;
    }

    public SCIOp createSetCurrentLocalTimeOp(SCISystemTime scisystemtime)
    {
        long l = sclibJNI.SCIDateTimeManager_createSetCurrentLocalTimeOp(swigCPtr, this, SCISystemTime.getCPtr(scisystemtime), scisystemtime);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetDateFormatOp(SCDateFormat scdateformat)
    {
        long l = sclibJNI.SCIDateTimeManager_createSetDateFormatOp(swigCPtr, this, scdateformat.swigValue());
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetTimeFormatOp(SCTimeFormat sctimeformat)
    {
        long l = sclibJNI.SCIDateTimeManager_createSetTimeFormatOp(swigCPtr, this, sctimeformat.swigValue());
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetTimeZoneOp(SCITimeZone scitimezone)
    {
        long l = sclibJNI.SCIDateTimeManager_createSetTimeZoneOp(swigCPtr, this, SCITimeZone.getCPtr(scitimezone), scitimezone);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSwitchToInternetTimeOp()
    {
        long l = sclibJNI.SCIDateTimeManager_createSwitchToInternetTimeOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSwitchToManualTimeOp()
    {
        long l = sclibJNI.SCIDateTimeManager_createSwitchToManualTimeOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
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

    public SCISystemTime getCurrentLocalTime()
    {
        long l = sclibJNI.SCIDateTimeManager_getCurrentLocalTime(swigCPtr, this);
        SCISystemTime scisystemtime;
        if(l == 0L)
            scisystemtime = null;
        else
            scisystemtime = new SCISystemTime(l, true);
        return scisystemtime;
    }

    public SCISystemTime getCurrentTime()
    {
        long l = sclibJNI.SCIDateTimeManager_getCurrentTime(swigCPtr, this);
        SCISystemTime scisystemtime;
        if(l == 0L)
            scisystemtime = null;
        else
            scisystemtime = new SCISystemTime(l, true);
        return scisystemtime;
    }

    public SCDateFormat getDateFormat()
    {
        return SCDateFormat.swigToEnum(sclibJNI.SCIDateTimeManager_getDateFormat(swigCPtr, this));
    }

    public String getFormattedDate(SCISystemTime scisystemtime)
    {
        return sclibJNI.SCIDateTimeManager_getFormattedDate(swigCPtr, this, SCISystemTime.getCPtr(scisystemtime), scisystemtime);
    }

    public String getFormattedLocalDate()
    {
        return sclibJNI.SCIDateTimeManager_getFormattedLocalDate__SWIG_0(swigCPtr, this);
    }

    public String getFormattedLocalDate(boolean flag, boolean flag1)
    {
        return sclibJNI.SCIDateTimeManager_getFormattedLocalDate__SWIG_1(swigCPtr, this, flag, flag1);
    }

    public String getFormattedLocalTime()
    {
        return sclibJNI.SCIDateTimeManager_getFormattedLocalTime(swigCPtr, this);
    }

    public String getFormattedTime(SCITime scitime)
    {
        return sclibJNI.SCIDateTimeManager_getFormattedTime(swigCPtr, this, SCITime.getCPtr(scitime), scitime);
    }

    public SCISystemTime getTimeAsLocalTime(SCISystemTime scisystemtime)
    {
        long l = sclibJNI.SCIDateTimeManager_getTimeAsLocalTime(swigCPtr, this, SCISystemTime.getCPtr(scisystemtime), scisystemtime);
        SCISystemTime scisystemtime1;
        if(l == 0L)
            scisystemtime1 = null;
        else
            scisystemtime1 = new SCISystemTime(l, true);
        return scisystemtime1;
    }

    public SCTimeFormat getTimeFormat()
    {
        return SCTimeFormat.swigToEnum(sclibJNI.SCIDateTimeManager_getTimeFormat(swigCPtr, this));
    }

    public SCTimeStatus getTimeStatus()
    {
        return SCTimeStatus.swigToEnum(sclibJNI.SCIDateTimeManager_getTimeStatus(swigCPtr, this));
    }

    public SCITimeZone getTimeZone()
    {
        long l = sclibJNI.SCIDateTimeManager_getTimeZone(swigCPtr, this);
        SCITimeZone scitimezone;
        if(l == 0L)
            scitimezone = null;
        else
            scitimezone = new SCITimeZone(l, true);
        return scitimezone;
    }

    public int getTimeZoneIndex()
    {
        return sclibJNI.SCIDateTimeManager_getTimeZoneIndex(swigCPtr, this);
    }

    public SCIEnumerator getTimeZones()
    {
        long l = sclibJNI.SCIDateTimeManager_getTimeZones(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public boolean isTimeAvailable()
    {
        return sclibJNI.SCIDateTimeManager_isTimeAvailable(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIDateTimeManager_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIDateTimeManager_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDateTimeManager");
    private long swigCPtr;

}
