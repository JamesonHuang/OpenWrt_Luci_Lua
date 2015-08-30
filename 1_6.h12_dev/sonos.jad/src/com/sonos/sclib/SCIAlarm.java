// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIActionContext, SCIOpAlarmSave, 
//            SCIAlarmMusic, SCITime, SCIRecurrence, SCIEventSink

public class SCIAlarm extends SCIObj
{
    public static final class RecurrenceForm extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$002(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$008()
            {
                int i = next;
                next = i + 1;
                return i;
            }

*/

            private SwigNext()
            {
            }
        }


        public static RecurrenceForm swigToEnum(int i)
        {
            RecurrenceForm arecurrenceform[] = (RecurrenceForm[])com/sonos/sclib/SCIAlarm$RecurrenceForm.getEnumConstants();
            if(i >= arecurrenceform.length || i < 0 || arecurrenceform[i].swigValue != i) goto _L2; else goto _L1
_L1:
            RecurrenceForm recurrenceform1 = arecurrenceform[i];
_L4:
            return recurrenceform1;
_L2:
            int j = arecurrenceform.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                RecurrenceForm recurrenceform = arecurrenceform[k];
                if(recurrenceform.swigValue == i)
                {
                    recurrenceform1 = recurrenceform;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIAlarm$RecurrenceForm).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static RecurrenceForm valueOf(String s)
        {
            return (RecurrenceForm)Enum.valueOf(com/sonos/sclib/SCIAlarm$RecurrenceForm, s);
        }

        public static RecurrenceForm[] values()
        {
            return (RecurrenceForm[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final RecurrenceForm $VALUES[];
        public static final RecurrenceForm RecurrenceLong;
        public static final RecurrenceForm RecurrenceNormal;
        public static final RecurrenceForm RecurrenceShort;
        private final int swigValue;

        static 
        {
            RecurrenceNormal = new RecurrenceForm("RecurrenceNormal", 0, sclibJNI.SCIAlarm_RecurrenceNormal_get());
            RecurrenceShort = new RecurrenceForm("RecurrenceShort", 1, sclibJNI.SCIAlarm_RecurrenceShort_get());
            RecurrenceLong = new RecurrenceForm("RecurrenceLong", 2, sclibJNI.SCIAlarm_RecurrenceLong_get());
            RecurrenceForm arecurrenceform[] = new RecurrenceForm[3];
            arecurrenceform[0] = RecurrenceNormal;
            arecurrenceform[1] = RecurrenceShort;
            arecurrenceform[2] = RecurrenceLong;
            $VALUES = arecurrenceform;
        }

        private RecurrenceForm(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private RecurrenceForm(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private RecurrenceForm(String s, int i, RecurrenceForm recurrenceform)
        {
            Enum(s, i);
            swigValue = recurrenceform.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIAlarm(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIAlarmUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAlarm(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAlarm(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAlarm scialarm)
    {
        long l;
        if(scialarm == null)
            l = 0L;
        else
            l = scialarm.swigCPtr;
        return l;
    }

    public SCIAlarm clone()
    {
        long l = sclibJNI.SCIAlarm_clone(swigCPtr, this);
        SCIAlarm scialarm;
        if(l == 0L)
            scialarm = null;
        else
            scialarm = new SCIAlarm(l, true);
        return scialarm;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public SCIActionContext createSaveAlarmAction()
    {
        long l = sclibJNI.SCIAlarm_createSaveAlarmAction(swigCPtr, this);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public SCIOpAlarmSave createSaveAlarmOp()
    {
        long l = sclibJNI.SCIAlarm_createSaveAlarmOp(swigCPtr, this);
        SCIOpAlarmSave sciopalarmsave;
        if(l == 0L)
            sciopalarmsave = null;
        else
            sciopalarmsave = new SCIOpAlarmSave(l, true);
        return sciopalarmsave;
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

    public SCIAlarmMusic getAlarmMusic()
    {
        long l = sclibJNI.SCIAlarm_getAlarmMusic(swigCPtr, this);
        SCIAlarmMusic scialarmmusic;
        if(l == 0L)
            scialarmmusic = null;
        else
            scialarmmusic = new SCIAlarmMusic(l, true);
        return scialarmmusic;
    }

    public String getDeviceID()
    {
        return sclibJNI.SCIAlarm_getDeviceID(swigCPtr, this);
    }

    public SCITime getDuration()
    {
        long l = sclibJNI.SCIAlarm_getDuration(swigCPtr, this);
        SCITime scitime;
        if(l == 0L)
            scitime = null;
        else
            scitime = new SCITime(l, true);
        return scitime;
    }

    public boolean getEnabled()
    {
        return sclibJNI.SCIAlarm_getEnabled(swigCPtr, this);
    }

    public String getFormattedTime()
    {
        return sclibJNI.SCIAlarm_getFormattedTime(swigCPtr, this);
    }

    public int getID()
    {
        return sclibJNI.SCIAlarm_getID(swigCPtr, this);
    }

    public boolean getIncludeLinkedZones()
    {
        return sclibJNI.SCIAlarm_getIncludeLinkedZones(swigCPtr, this);
    }

    public String getMusicTitle()
    {
        return sclibJNI.SCIAlarm_getMusicTitle(swigCPtr, this);
    }

    public SCIRecurrence getRecurrence()
    {
        long l = sclibJNI.SCIAlarm_getRecurrence(swigCPtr, this);
        SCIRecurrence scirecurrence;
        if(l == 0L)
            scirecurrence = null;
        else
            scirecurrence = new SCIRecurrence(l, true);
        return scirecurrence;
    }

    public String getRecurrenceText()
    {
        return sclibJNI.SCIAlarm_getRecurrenceText__SWIG_0(swigCPtr, this);
    }

    public String getRecurrenceText(RecurrenceForm recurrenceform)
    {
        return sclibJNI.SCIAlarm_getRecurrenceText__SWIG_1(swigCPtr, this, recurrenceform.swigValue());
    }

    public String getSCUriForAlarmMusicBrowse()
    {
        return sclibJNI.SCIAlarm_getSCUriForAlarmMusicBrowse(swigCPtr, this);
    }

    public boolean getShuffleEnabled()
    {
        return sclibJNI.SCIAlarm_getShuffleEnabled(swigCPtr, this);
    }

    public boolean getShuffleMode()
    {
        return sclibJNI.SCIAlarm_getShuffleMode(swigCPtr, this);
    }

    public SCITime getStartTime()
    {
        long l = sclibJNI.SCIAlarm_getStartTime(swigCPtr, this);
        SCITime scitime;
        if(l == 0L)
            scitime = null;
        else
            scitime = new SCITime(l, true);
        return scitime;
    }

    public int getVolume()
    {
        return sclibJNI.SCIAlarm_getVolume(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCIAlarm_isValid(swigCPtr, this);
    }

    public void setAlarmMusic(SCIAlarmMusic scialarmmusic)
    {
        sclibJNI.SCIAlarm_setAlarmMusic(swigCPtr, this, SCIAlarmMusic.getCPtr(scialarmmusic), scialarmmusic);
    }

    public void setDeviceID(String s)
    {
        sclibJNI.SCIAlarm_setDeviceID(swigCPtr, this, s);
    }

    public void setDuration(SCITime scitime)
    {
        sclibJNI.SCIAlarm_setDuration(swigCPtr, this, SCITime.getCPtr(scitime), scitime);
    }

    public void setEnabled(boolean flag)
    {
        sclibJNI.SCIAlarm_setEnabled(swigCPtr, this, flag);
    }

    public void setIncludeLinkedZones(boolean flag)
    {
        sclibJNI.SCIAlarm_setIncludeLinkedZones(swigCPtr, this, flag);
    }

    public void setRecurrence(SCIRecurrence scirecurrence)
    {
        sclibJNI.SCIAlarm_setRecurrence(swigCPtr, this, SCIRecurrence.getCPtr(scirecurrence), scirecurrence);
    }

    public void setShuffleMode(boolean flag)
    {
        sclibJNI.SCIAlarm_setShuffleMode(swigCPtr, this, flag);
    }

    public void setStartTime(SCITime scitime)
    {
        sclibJNI.SCIAlarm_setStartTime(swigCPtr, this, SCITime.getCPtr(scitime), scitime);
    }

    public void setVolume(int i)
    {
        sclibJNI.SCIAlarm_setVolume(swigCPtr, this, i);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIAlarm_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIAlarm_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAlarm");
    private long swigCPtr;

}
