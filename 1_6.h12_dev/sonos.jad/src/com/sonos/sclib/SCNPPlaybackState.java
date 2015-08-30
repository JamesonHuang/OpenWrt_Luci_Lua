// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCNPPlaybackState extends Enum
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


    private SCNPPlaybackState(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCNPPlaybackState(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCNPPlaybackState(String s, int i, SCNPPlaybackState scnpplaybackstate)
    {
        Enum(s, i);
        swigValue = scnpplaybackstate.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCNPPlaybackState swigToEnum(int i)
    {
        SCNPPlaybackState ascnpplaybackstate[] = (SCNPPlaybackState[])com/sonos/sclib/SCNPPlaybackState.getEnumConstants();
        if(i >= ascnpplaybackstate.length || i < 0 || ascnpplaybackstate[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCNPPlaybackState scnpplaybackstate1 = ascnpplaybackstate[i];
_L4:
        return scnpplaybackstate1;
_L2:
        int j = ascnpplaybackstate.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCNPPlaybackState scnpplaybackstate = ascnpplaybackstate[k];
            if(scnpplaybackstate.swigValue == i)
            {
                scnpplaybackstate1 = scnpplaybackstate;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCNPPlaybackState).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCNPPlaybackState valueOf(String s)
    {
        return (SCNPPlaybackState)Enum.valueOf(com/sonos/sclib/SCNPPlaybackState, s);
    }

    public static SCNPPlaybackState[] values()
    {
        return (SCNPPlaybackState[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCNPPlaybackState $VALUES[];
    public static final SCNPPlaybackState SC_NP_PLAYBACK_PAUSED;
    public static final SCNPPlaybackState SC_NP_PLAYBACK_PLAYING;
    public static final SCNPPlaybackState SC_NP_PLAYBACK_STOPPED;
    public static final SCNPPlaybackState SC_NP_PLAYBACK_UNKNOWN;
    private final int swigValue;

    static 
    {
        SC_NP_PLAYBACK_UNKNOWN = new SCNPPlaybackState("SC_NP_PLAYBACK_UNKNOWN", 0, sclibJNI.SC_NP_PLAYBACK_UNKNOWN_get());
        SC_NP_PLAYBACK_PLAYING = new SCNPPlaybackState("SC_NP_PLAYBACK_PLAYING", 1, sclibJNI.SC_NP_PLAYBACK_PLAYING_get());
        SC_NP_PLAYBACK_PAUSED = new SCNPPlaybackState("SC_NP_PLAYBACK_PAUSED", 2);
        SC_NP_PLAYBACK_STOPPED = new SCNPPlaybackState("SC_NP_PLAYBACK_STOPPED", 3);
        SCNPPlaybackState ascnpplaybackstate[] = new SCNPPlaybackState[4];
        ascnpplaybackstate[0] = SC_NP_PLAYBACK_UNKNOWN;
        ascnpplaybackstate[1] = SC_NP_PLAYBACK_PLAYING;
        ascnpplaybackstate[2] = SC_NP_PLAYBACK_PAUSED;
        ascnpplaybackstate[3] = SC_NP_PLAYBACK_STOPPED;
        $VALUES = ascnpplaybackstate;
    }
}
