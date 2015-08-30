// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOpGetTrackPositionInfo, SCIOp, 
//            SCRet, SCNPPlaybackState, SCIPropertyBag

public class SCINowPlayingTransport extends SCIObj
{
    public static final class SCPlayPauseDisplayState extends Enum
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


        public static SCPlayPauseDisplayState swigToEnum(int i)
        {
            SCPlayPauseDisplayState ascplaypausedisplaystate[] = (SCPlayPauseDisplayState[])com/sonos/sclib/SCINowPlayingTransport$SCPlayPauseDisplayState.getEnumConstants();
            if(i >= ascplaypausedisplaystate.length || i < 0 || ascplaypausedisplaystate[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCPlayPauseDisplayState scplaypausedisplaystate1 = ascplaypausedisplaystate[i];
_L4:
            return scplaypausedisplaystate1;
_L2:
            int j = ascplaypausedisplaystate.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCPlayPauseDisplayState scplaypausedisplaystate = ascplaypausedisplaystate[k];
                if(scplaypausedisplaystate.swigValue == i)
                {
                    scplaypausedisplaystate1 = scplaypausedisplaystate;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCINowPlayingTransport$SCPlayPauseDisplayState).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCPlayPauseDisplayState valueOf(String s)
        {
            return (SCPlayPauseDisplayState)Enum.valueOf(com/sonos/sclib/SCINowPlayingTransport$SCPlayPauseDisplayState, s);
        }

        public static SCPlayPauseDisplayState[] values()
        {
            return (SCPlayPauseDisplayState[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCPlayPauseDisplayState $VALUES[];
        public static final SCPlayPauseDisplayState SC_PLAYPAUSE_DISPLAYSTATE_PAUSE;
        public static final SCPlayPauseDisplayState SC_PLAYPAUSE_DISPLAYSTATE_PLAY;
        public static final SCPlayPauseDisplayState SC_PLAYPAUSE_DISPLAYSTATE_STOP;
        private final int swigValue;

        static 
        {
            SC_PLAYPAUSE_DISPLAYSTATE_PLAY = new SCPlayPauseDisplayState("SC_PLAYPAUSE_DISPLAYSTATE_PLAY", 0);
            SC_PLAYPAUSE_DISPLAYSTATE_PAUSE = new SCPlayPauseDisplayState("SC_PLAYPAUSE_DISPLAYSTATE_PAUSE", 1);
            SC_PLAYPAUSE_DISPLAYSTATE_STOP = new SCPlayPauseDisplayState("SC_PLAYPAUSE_DISPLAYSTATE_STOP", 2);
            SCPlayPauseDisplayState ascplaypausedisplaystate[] = new SCPlayPauseDisplayState[3];
            ascplaypausedisplaystate[0] = SC_PLAYPAUSE_DISPLAYSTATE_PLAY;
            ascplaypausedisplaystate[1] = SC_PLAYPAUSE_DISPLAYSTATE_PAUSE;
            ascplaypausedisplaystate[2] = SC_PLAYPAUSE_DISPLAYSTATE_STOP;
            $VALUES = ascplaypausedisplaystate;
        }

        private SCPlayPauseDisplayState(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCPlayPauseDisplayState(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCPlayPauseDisplayState(String s, int i, SCPlayPauseDisplayState scplaypausedisplaystate)
        {
            Enum(s, i);
            swigValue = scplaypausedisplaystate.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCINowPlayingTransport(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCINowPlayingTransportUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINowPlayingTransport(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINowPlayingTransport(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINowPlayingTransport scinowplayingtransport)
    {
        long l;
        if(scinowplayingtransport == null)
            l = 0L;
        else
            l = scinowplayingtransport.swigCPtr;
        return l;
    }

    public SCIOpGetTrackPositionInfo createGetTrackPositionInfoOp()
    {
        long l = sclibJNI.SCINowPlayingTransport_createGetTrackPositionInfoOp(swigCPtr, this);
        SCIOpGetTrackPositionInfo sciopgettrackpositioninfo;
        if(l == 0L)
            sciopgettrackpositioninfo = null;
        else
            sciopgettrackpositioninfo = new SCIOpGetTrackPositionInfo(l, true);
        return sciopgettrackpositioninfo;
    }

    public SCIOp createNextTrackOnRateOp()
    {
        long l = sclibJNI.SCINowPlayingTransport_createNextTrackOnRateOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createNextTrackOp()
    {
        long l = sclibJNI.SCINowPlayingTransport_createNextTrackOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createPauseOp()
    {
        long l = sclibJNI.SCINowPlayingTransport_createPauseOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createPlayOp()
    {
        long l = sclibJNI.SCINowPlayingTransport_createPlayOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createPrevTrackOp()
    {
        long l = sclibJNI.SCINowPlayingTransport_createPrevTrackOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createRewindToStartOp()
    {
        long l = sclibJNI.SCINowPlayingTransport_createRewindToStartOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createRewindToStartOrPrevTrackOp(long l)
    {
        long l1 = sclibJNI.SCINowPlayingTransport_createRewindToStartOrPrevTrackOp(swigCPtr, this, l);
        SCIOp sciop;
        if(l1 == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l1, true);
        return sciop;
    }

    public SCIOp createSeekOp(String s, String s1)
    {
        long l = sclibJNI.SCINowPlayingTransport_createSeekOp(swigCPtr, this, s, s1);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSeekToTimeWithinTrackOp(long l)
    {
        long l1 = sclibJNI.SCINowPlayingTransport_createSeekToTimeWithinTrackOp(swigCPtr, this, l);
        SCIOp sciop;
        if(l1 == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l1, true);
        return sciop;
    }

    public SCIOp createSetCrossfadeModeOp(boolean flag)
    {
        long l = sclibJNI.SCINowPlayingTransport_createSetCrossfadeModeOp(swigCPtr, this, flag);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetRepeatModeOp(boolean flag)
    {
        long l = sclibJNI.SCINowPlayingTransport_createSetRepeatModeOp(swigCPtr, this, flag);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetShuffleModeOp(boolean flag)
    {
        long l = sclibJNI.SCINowPlayingTransport_createSetShuffleModeOp(swigCPtr, this, flag);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetTransportURIOp(String s, String s1)
    {
        long l = sclibJNI.SCINowPlayingTransport_createSetTransportURIOp(swigCPtr, this, s, s1);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSnoozeAlarmOp()
    {
        long l = sclibJNI.SCINowPlayingTransport_createSnoozeAlarmOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createTogglePlayPauseOp()
    {
        long l = sclibJNI.SCINowPlayingTransport_createTogglePlayPauseOp(swigCPtr, this);
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

    public String getAsynchronousErrorString()
    {
        return sclibJNI.SCINowPlayingTransport_getAsynchronousErrorString(swigCPtr, this);
    }

    public SCRet getAvtURI(Object aobj[])
    {
        return SCRet.swigToEnum(sclibJNI.SCINowPlayingTransport_getAvtURI(swigCPtr, this, aobj));
    }

    public boolean getCrossfadeMode()
    {
        return sclibJNI.SCINowPlayingTransport_getCrossfadeMode(swigCPtr, this);
    }

    public int getCurrentTrackDuration()
    {
        return sclibJNI.SCINowPlayingTransport_getCurrentTrackDuration(swigCPtr, this);
    }

    public void getErrorString(int i, Object aobj[])
    {
        sclibJNI.SCINowPlayingTransport_getErrorString(swigCPtr, this, i, aobj);
    }

    public void getErrorStringFromOpResultAndURI(int i, String s, Object aobj[])
    {
        sclibJNI.SCINowPlayingTransport_getErrorStringFromOpResultAndURI(swigCPtr, this, i, s, aobj);
    }

    public SCPlayPauseDisplayState getPlayPauseDisplayState()
    {
        return SCPlayPauseDisplayState.swigToEnum(sclibJNI.SCINowPlayingTransport_getPlayPauseDisplayState(swigCPtr, this));
    }

    public SCNPPlaybackState getPlaybackState()
    {
        return SCNPPlaybackState.swigToEnum(sclibJNI.SCINowPlayingTransport_getPlaybackState(swigCPtr, this));
    }

    public boolean getRepeatMode()
    {
        return sclibJNI.SCINowPlayingTransport_getRepeatMode(swigCPtr, this);
    }

    public boolean getShuffleMode()
    {
        return sclibJNI.SCINowPlayingTransport_getShuffleMode(swigCPtr, this);
    }

    public SCRet getTrackURI(Object aobj[])
    {
        return SCRet.swigToEnum(sclibJNI.SCINowPlayingTransport_getTrackURI(swigCPtr, this, aobj));
    }

    public int getTransportErrorHttpCode()
    {
        return sclibJNI.SCINowPlayingTransport_getTransportErrorHttpCode(swigCPtr, this);
    }

    public SCIPropertyBag getTransportErrorHttpHeaders()
    {
        long l = sclibJNI.SCINowPlayingTransport_getTransportErrorHttpHeaders(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public String getTransportErrorURI()
    {
        return sclibJNI.SCINowPlayingTransport_getTransportErrorURI(swigCPtr, this);
    }

    public boolean hasLocalMuseSession()
    {
        return sclibJNI.SCINowPlayingTransport_hasLocalMuseSession(swigCPtr, this);
    }

    public boolean hasMusic()
    {
        return sclibJNI.SCINowPlayingTransport_hasMusic(swigCPtr, this);
    }

    public boolean isCrossfadeEnabled()
    {
        return sclibJNI.SCINowPlayingTransport_isCrossfadeEnabled(swigCPtr, this);
    }

    public boolean isFastForwardEnabled(long l)
    {
        return sclibJNI.SCINowPlayingTransport_isFastForwardEnabled(swigCPtr, this, l);
    }

    public boolean isNextTrackEnabled()
    {
        return sclibJNI.SCINowPlayingTransport_isNextTrackEnabled(swigCPtr, this);
    }

    public boolean isPlayPauseEnabled()
    {
        return sclibJNI.SCINowPlayingTransport_isPlayPauseEnabled(swigCPtr, this);
    }

    public boolean isPreviousTrackEnabled()
    {
        return sclibJNI.SCINowPlayingTransport_isPreviousTrackEnabled(swigCPtr, this);
    }

    public boolean isRepeatEnabled()
    {
        return sclibJNI.SCINowPlayingTransport_isRepeatEnabled(swigCPtr, this);
    }

    public boolean isRewindEnabled(long l)
    {
        return sclibJNI.SCINowPlayingTransport_isRewindEnabled(swigCPtr, this, l);
    }

    public boolean isSeekEnabled()
    {
        return sclibJNI.SCINowPlayingTransport_isSeekEnabled(swigCPtr, this);
    }

    public boolean isShuffleEnabled()
    {
        return sclibJNI.SCINowPlayingTransport_isShuffleEnabled(swigCPtr, this);
    }

    public boolean isTrackPositionInfoAvailable()
    {
        return sclibJNI.SCINowPlayingTransport_isTrackPositionInfoAvailable(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINowPlayingTransport");
    private long swigCPtr;

}
