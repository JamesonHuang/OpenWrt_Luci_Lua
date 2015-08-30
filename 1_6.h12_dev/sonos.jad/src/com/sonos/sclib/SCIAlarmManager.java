// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIAlarm, SCIActionContext, 
//            SCIOp, SCIEnumerator, SCIBrowseDataSource, SCIEventSink

public class SCIAlarmManager extends SCIObj
{

    protected SCIAlarmManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIAlarmManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAlarmManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAlarmManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAlarmManager scialarmmanager)
    {
        long l;
        if(scialarmmanager == null)
            l = 0L;
        else
            l = scialarmmanager.swigCPtr;
        return l;
    }

    public void clearEditableAlarm()
    {
        sclibJNI.SCIAlarmManager_clearEditableAlarm(swigCPtr, this);
    }

    public SCIActionContext createDeleteAlarmAction(SCIAlarm scialarm)
    {
        long l = sclibJNI.SCIAlarmManager_createDeleteAlarmAction(swigCPtr, this, SCIAlarm.getCPtr(scialarm), scialarm);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public SCIOp createDeleteAlarmOp(SCIAlarm scialarm)
    {
        long l = sclibJNI.SCIAlarmManager_createDeleteAlarmOp(swigCPtr, this, SCIAlarm.getCPtr(scialarm), scialarm);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIAlarm createNewAlarm()
    {
        long l = sclibJNI.SCIAlarmManager_createNewAlarm(swigCPtr, this);
        SCIAlarm scialarm;
        if(l == 0L)
            scialarm = null;
        else
            scialarm = new SCIAlarm(l, true);
        return scialarm;
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

    public SCIEnumerator getAlarms()
    {
        long l = sclibJNI.SCIAlarmManager_getAlarms(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIAlarm getEditableAlarm(int i)
    {
        long l = sclibJNI.SCIAlarmManager_getEditableAlarm(swigCPtr, this, i);
        SCIAlarm scialarm;
        if(l == 0L)
            scialarm = null;
        else
            scialarm = new SCIAlarm(l, true);
        return scialarm;
    }

    public SCIBrowseDataSource getNewAlarmDataSource()
    {
        long l = sclibJNI.SCIAlarmManager_getNewAlarmDataSource(swigCPtr, this);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
    }

    public SCIAlarm lookupAlarm(int i)
    {
        long l = sclibJNI.SCIAlarmManager_lookupAlarm(swigCPtr, this, i);
        SCIAlarm scialarm;
        if(l == 0L)
            scialarm = null;
        else
            scialarm = new SCIAlarm(l, true);
        return scialarm;
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIAlarmManager_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIAlarmManager_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAlarmManager");
    private long swigCPtr;

}
