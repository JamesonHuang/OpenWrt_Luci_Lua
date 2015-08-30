// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOp, SCIStringArray, 
//            SCIEventSink

public class SCIDeviceMusicEqualization extends SCIObj
{

    protected SCIDeviceMusicEqualization(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDeviceMusicEqualizationUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDeviceMusicEqualization(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDeviceMusicEqualization(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDeviceMusicEqualization scidevicemusicequalization)
    {
        long l;
        if(scidevicemusicequalization == null)
            l = 0L;
        else
            l = scidevicemusicequalization.swigCPtr;
        return l;
    }

    public SCIOp createRefreshCacheOp()
    {
        long l = sclibJNI.SCIDeviceMusicEqualization_createRefreshCacheOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createResetAllOp()
    {
        long l = sclibJNI.SCIDeviceMusicEqualization_createResetAllOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetBassLevelOp(int i)
    {
        long l = sclibJNI.SCIDeviceMusicEqualization_createSetBassLevelOp(swigCPtr, this, i);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetLeftRightBalanceLevelOp(int i)
    {
        long l = sclibJNI.SCIDeviceMusicEqualization_createSetLeftRightBalanceLevelOp(swigCPtr, this, i);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetLoudnessBoostOp(boolean flag)
    {
        long l = sclibJNI.SCIDeviceMusicEqualization_createSetLoudnessBoostOp(swigCPtr, this, flag);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetTrebleLevelOp(int i)
    {
        long l = sclibJNI.SCIDeviceMusicEqualization_createSetTrebleLevelOp(swigCPtr, this, i);
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

    public int getBassLevel()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getBassLevel(swigCPtr, this);
    }

    public int getCrossover()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getCrossover(swigCPtr, this);
    }

    public int getCrossoverIndex()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getCrossoverIndex(swigCPtr, this);
    }

    public SCIStringArray getCrossoverIndexLabels()
    {
        long l = sclibJNI.SCIDeviceMusicEqualization_getCrossoverIndexLabels(swigCPtr, this);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public int getLeftRightBalanceLevel()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getLeftRightBalanceLevel(swigCPtr, this);
    }

    public boolean getLoudnessBoost()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getLoudnessBoost(swigCPtr, this);
    }

    public boolean getNightMode()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getNightMode(swigCPtr, this);
    }

    public String getRecommendedTitle()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getRecommendedTitle(swigCPtr, this);
    }

    public boolean getSubEnabled()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getSubEnabled(swigCPtr, this);
    }

    public int getSubGain()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getSubGain(swigCPtr, this);
    }

    public boolean getSubPolarity()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getSubPolarity(swigCPtr, this);
    }

    public boolean getSurroundEnabled()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getSurroundEnabled(swigCPtr, this);
    }

    public int getSurroundLevel()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getSurroundLevel(swigCPtr, this);
    }

    public int getSurroundMode()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getSurroundMode(swigCPtr, this);
    }

    public int getTVAudioDelay()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getTVAudioDelay(swigCPtr, this);
    }

    public boolean getTVDialogEnhancement()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getTVDialogEnhancement(swigCPtr, this);
    }

    public int getTrebleLevel()
    {
        return sclibJNI.SCIDeviceMusicEqualization_getTrebleLevel(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCIDeviceMusicEqualization_isValid(swigCPtr, this);
    }

    public void resetBasicEq()
    {
        sclibJNI.SCIDeviceMusicEqualization_resetBasicEq(swigCPtr, this);
    }

    public void resetSubEq()
    {
        sclibJNI.SCIDeviceMusicEqualization_resetSubEq(swigCPtr, this);
    }

    public void resetSurround()
    {
        sclibJNI.SCIDeviceMusicEqualization_resetSurround(swigCPtr, this);
    }

    public void resetTVDialog()
    {
        sclibJNI.SCIDeviceMusicEqualization_resetTVDialog(swigCPtr, this);
    }

    public void setBassLevel(int i)
    {
        sclibJNI.SCIDeviceMusicEqualization_setBassLevel(swigCPtr, this, i);
    }

    public void setCrossover(int i)
    {
        sclibJNI.SCIDeviceMusicEqualization_setCrossover(swigCPtr, this, i);
    }

    public void setCrossoverIndex(int i)
    {
        sclibJNI.SCIDeviceMusicEqualization_setCrossoverIndex(swigCPtr, this, i);
    }

    public void setLeftRightBalanceLevel(int i)
    {
        sclibJNI.SCIDeviceMusicEqualization_setLeftRightBalanceLevel(swigCPtr, this, i);
    }

    public void setLoudnessBoost(boolean flag)
    {
        sclibJNI.SCIDeviceMusicEqualization_setLoudnessBoost(swigCPtr, this, flag);
    }

    public void setNightMode(boolean flag)
    {
        sclibJNI.SCIDeviceMusicEqualization_setNightMode(swigCPtr, this, flag);
    }

    public void setSubEnabled(boolean flag)
    {
        sclibJNI.SCIDeviceMusicEqualization_setSubEnabled(swigCPtr, this, flag);
    }

    public void setSubGain(int i)
    {
        sclibJNI.SCIDeviceMusicEqualization_setSubGain(swigCPtr, this, i);
    }

    public void setSubPolarity(boolean flag)
    {
        sclibJNI.SCIDeviceMusicEqualization_setSubPolarity(swigCPtr, this, flag);
    }

    public void setSurroundEnabled(boolean flag)
    {
        sclibJNI.SCIDeviceMusicEqualization_setSurroundEnabled(swigCPtr, this, flag);
    }

    public void setSurroundLevel(int i)
    {
        sclibJNI.SCIDeviceMusicEqualization_setSurroundLevel(swigCPtr, this, i);
    }

    public void setSurroundMode(int i)
    {
        sclibJNI.SCIDeviceMusicEqualization_setSurroundMode(swigCPtr, this, i);
    }

    public void setTVAudioDelay(int i)
    {
        sclibJNI.SCIDeviceMusicEqualization_setTVAudioDelay(swigCPtr, this, i);
    }

    public void setTVDialogEnhancement(boolean flag)
    {
        sclibJNI.SCIDeviceMusicEqualization_setTVDialogEnhancement(swigCPtr, this, flag);
    }

    public void setTrebleLevel(int i)
    {
        sclibJNI.SCIDeviceMusicEqualization_setTrebleLevel(swigCPtr, this, i);
    }

    public boolean shouldShowBalance()
    {
        return sclibJNI.SCIDeviceMusicEqualization_shouldShowBalance(swigCPtr, this);
    }

    public boolean shouldShowCrossoverAdjust()
    {
        return sclibJNI.SCIDeviceMusicEqualization_shouldShowCrossoverAdjust(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIDeviceMusicEqualization_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIDeviceMusicEqualization_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public static final int MAX_BALANCE_LEVEL = sclibJNI.SCIDeviceMusicEqualization_MAX_BALANCE_LEVEL_get();
    public static final int MAX_BASS_LEVEL = sclibJNI.SCIDeviceMusicEqualization_MAX_BASS_LEVEL_get();
    public static final int MAX_SUB_GAIN = sclibJNI.SCIDeviceMusicEqualization_MAX_SUB_GAIN_get();
    public static final int MAX_SURROUND_LEVEL = sclibJNI.SCIDeviceMusicEqualization_MAX_SURROUND_LEVEL_get();
    public static final int MAX_TREBLE_LEVEL = sclibJNI.SCIDeviceMusicEqualization_MAX_TREBLE_LEVEL_get();
    public static final int MAX_TV_AUDIO_DELAY = sclibJNI.SCIDeviceMusicEqualization_MAX_TV_AUDIO_DELAY_get();
    public static final int MIN_BALANCE_LEVEL = sclibJNI.SCIDeviceMusicEqualization_MIN_BALANCE_LEVEL_get();
    public static final int MIN_BASS_LEVEL = sclibJNI.SCIDeviceMusicEqualization_MIN_BASS_LEVEL_get();
    public static final int MIN_SUB_GAIN = sclibJNI.SCIDeviceMusicEqualization_MIN_SUB_GAIN_get();
    public static final int MIN_SURROUND_LEVEL = sclibJNI.SCIDeviceMusicEqualization_MIN_SURROUND_LEVEL_get();
    public static final int MIN_TREBLE_LEVEL = sclibJNI.SCIDeviceMusicEqualization_MIN_TREBLE_LEVEL_get();
    public static final int MIN_TV_AUDIO_DELAY = sclibJNI.SCIDeviceMusicEqualization_MIN_TV_AUDIO_DELAY_get();
    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDeviceMusicEqualization");
    private long swigCPtr;

}
