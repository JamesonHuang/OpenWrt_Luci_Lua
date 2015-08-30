// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIDeviceOutputMode, SCRet

public class SCIDeviceVolume extends SCIObj
{
    public static final class PresentationTextType extends Enum
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


        public static PresentationTextType swigToEnum(int i)
        {
            PresentationTextType apresentationtexttype[] = (PresentationTextType[])com/sonos/sclib/SCIDeviceVolume$PresentationTextType.getEnumConstants();
            if(i >= apresentationtexttype.length || i < 0 || apresentationtexttype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            PresentationTextType presentationtexttype1 = apresentationtexttype[i];
_L4:
            return presentationtexttype1;
_L2:
            int j = apresentationtexttype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                PresentationTextType presentationtexttype = apresentationtexttype[k];
                if(presentationtexttype.swigValue == i)
                {
                    presentationtexttype1 = presentationtexttype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIDeviceVolume$PresentationTextType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static PresentationTextType valueOf(String s)
        {
            return (PresentationTextType)Enum.valueOf(com/sonos/sclib/SCIDeviceVolume$PresentationTextType, s);
        }

        public static PresentationTextType[] values()
        {
            return (PresentationTextType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final PresentationTextType $VALUES[];
        public static final PresentationTextType DEVICE_TITLE;
        public static final PresentationTextType UNADJUSTABLE_TEXT;
        private final int swigValue;

        static 
        {
            DEVICE_TITLE = new PresentationTextType("DEVICE_TITLE", 0);
            UNADJUSTABLE_TEXT = new PresentationTextType("UNADJUSTABLE_TEXT", 1);
            PresentationTextType apresentationtexttype[] = new PresentationTextType[2];
            apresentationtexttype[0] = DEVICE_TITLE;
            apresentationtexttype[1] = UNADJUSTABLE_TEXT;
            $VALUES = apresentationtexttype;
        }

        private PresentationTextType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private PresentationTextType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private PresentationTextType(String s, int i, PresentationTextType presentationtexttype)
        {
            Enum(s, i);
            swigValue = presentationtexttype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIDeviceVolume(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDeviceVolumeUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDeviceVolume(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDeviceVolume(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDeviceVolume scidevicevolume)
    {
        long l;
        if(scidevicevolume == null)
            l = 0L;
        else
            l = scidevicevolume.swigCPtr;
        return l;
    }

    public void beginContinuousVolumeAdjustments()
    {
        sclibJNI.SCIDeviceVolume_beginContinuousVolumeAdjustments(swigCPtr, this);
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

    public void endContinuousVolumeAdjustments()
    {
        sclibJNI.SCIDeviceVolume_endContinuousVolumeAdjustments(swigCPtr, this);
    }

    public String getDeviceID()
    {
        return sclibJNI.SCIDeviceVolume_getDeviceID(swigCPtr, this);
    }

    public String getPresentationText(PresentationTextType presentationtexttype)
    {
        return sclibJNI.SCIDeviceVolume_getPresentationText(swigCPtr, this, presentationtexttype.swigValue());
    }

    public int getVolume()
    {
        return sclibJNI.SCIDeviceVolume_getVolume(swigCPtr, this);
    }

    public boolean isMuted()
    {
        return sclibJNI.SCIDeviceVolume_isMuted(swigCPtr, this);
    }

    public boolean isVolumeAdjustable()
    {
        return sclibJNI.SCIDeviceVolume_isVolumeAdjustable(swigCPtr, this);
    }

    public SCIDeviceOutputMode outputMode()
    {
        return SCIDeviceOutputMode.swigToEnum(sclibJNI.SCIDeviceVolume_outputMode(swigCPtr, this));
    }

    public SCRet setAbsoluteVolume(int i)
    {
        return SCRet.swigToEnum(sclibJNI.SCIDeviceVolume_setAbsoluteVolume(swigCPtr, this, i));
    }

    public SCRet setMute(boolean flag)
    {
        return SCRet.swigToEnum(sclibJNI.SCIDeviceVolume_setMute(swigCPtr, this, flag));
    }

    public SCRet setRelativeVolume(int i)
    {
        return SCRet.swigToEnum(sclibJNI.SCIDeviceVolume_setRelativeVolume(swigCPtr, this, i));
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDeviceVolume");
    private long swigCPtr;

}
