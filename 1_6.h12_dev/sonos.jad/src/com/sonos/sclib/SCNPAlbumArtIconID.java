// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCNPAlbumArtIconID extends Enum
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


    private SCNPAlbumArtIconID(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCNPAlbumArtIconID(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCNPAlbumArtIconID(String s, int i, SCNPAlbumArtIconID scnpalbumarticonid)
    {
        Enum(s, i);
        swigValue = scnpalbumarticonid.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCNPAlbumArtIconID swigToEnum(int i)
    {
        SCNPAlbumArtIconID ascnpalbumarticonid[] = (SCNPAlbumArtIconID[])com/sonos/sclib/SCNPAlbumArtIconID.getEnumConstants();
        if(i >= ascnpalbumarticonid.length || i < 0 || ascnpalbumarticonid[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCNPAlbumArtIconID scnpalbumarticonid1 = ascnpalbumarticonid[i];
_L4:
        return scnpalbumarticonid1;
_L2:
        int j = ascnpalbumarticonid.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCNPAlbumArtIconID scnpalbumarticonid = ascnpalbumarticonid[k];
            if(scnpalbumarticonid.swigValue == i)
            {
                scnpalbumarticonid1 = scnpalbumarticonid;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCNPAlbumArtIconID).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCNPAlbumArtIconID valueOf(String s)
    {
        return (SCNPAlbumArtIconID)Enum.valueOf(com/sonos/sclib/SCNPAlbumArtIconID, s);
    }

    public static SCNPAlbumArtIconID[] values()
    {
        return (SCNPAlbumArtIconID[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCNPAlbumArtIconID $VALUES[];
    public static final SCNPAlbumArtIconID SC_NP_AAICON_GENERIC;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_LINEIN_AIRPLAY;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_LINEIN_AUDIO_COMPONENT;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_LINEIN_COMPUTER;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_LINEIN_HOME_THEATER;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_LINEIN_MP3_PLAYER;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_MAX;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_NO_SOURCE;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_RADIO;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_SONOS_ALARM;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_SONOS_DOCK;
    public static final SCNPAlbumArtIconID SC_NP_AAICON_SOUNDBAR_TV;
    private final int swigValue;

    static 
    {
        SC_NP_AAICON_GENERIC = new SCNPAlbumArtIconID("SC_NP_AAICON_GENERIC", 0, sclibJNI.SC_NP_AAICON_GENERIC_get());
        SC_NP_AAICON_NO_SOURCE = new SCNPAlbumArtIconID("SC_NP_AAICON_NO_SOURCE", 1);
        SC_NP_AAICON_RADIO = new SCNPAlbumArtIconID("SC_NP_AAICON_RADIO", 2);
        SC_NP_AAICON_SONOS_DOCK = new SCNPAlbumArtIconID("SC_NP_AAICON_SONOS_DOCK", 3);
        SC_NP_AAICON_LINEIN_AUDIO_COMPONENT = new SCNPAlbumArtIconID("SC_NP_AAICON_LINEIN_AUDIO_COMPONENT", 4);
        SC_NP_AAICON_LINEIN_COMPUTER = new SCNPAlbumArtIconID("SC_NP_AAICON_LINEIN_COMPUTER", 5);
        SC_NP_AAICON_LINEIN_HOME_THEATER = new SCNPAlbumArtIconID("SC_NP_AAICON_LINEIN_HOME_THEATER", 6);
        SC_NP_AAICON_LINEIN_MP3_PLAYER = new SCNPAlbumArtIconID("SC_NP_AAICON_LINEIN_MP3_PLAYER", 7);
        SC_NP_AAICON_LINEIN_AIRPLAY = new SCNPAlbumArtIconID("SC_NP_AAICON_LINEIN_AIRPLAY", 8);
        SC_NP_AAICON_SONOS_ALARM = new SCNPAlbumArtIconID("SC_NP_AAICON_SONOS_ALARM", 9);
        SC_NP_AAICON_SOUNDBAR_TV = new SCNPAlbumArtIconID("SC_NP_AAICON_SOUNDBAR_TV", 10);
        SC_NP_AAICON_MAX = new SCNPAlbumArtIconID("SC_NP_AAICON_MAX", 11);
        SCNPAlbumArtIconID ascnpalbumarticonid[] = new SCNPAlbumArtIconID[12];
        ascnpalbumarticonid[0] = SC_NP_AAICON_GENERIC;
        ascnpalbumarticonid[1] = SC_NP_AAICON_NO_SOURCE;
        ascnpalbumarticonid[2] = SC_NP_AAICON_RADIO;
        ascnpalbumarticonid[3] = SC_NP_AAICON_SONOS_DOCK;
        ascnpalbumarticonid[4] = SC_NP_AAICON_LINEIN_AUDIO_COMPONENT;
        ascnpalbumarticonid[5] = SC_NP_AAICON_LINEIN_COMPUTER;
        ascnpalbumarticonid[6] = SC_NP_AAICON_LINEIN_HOME_THEATER;
        ascnpalbumarticonid[7] = SC_NP_AAICON_LINEIN_MP3_PLAYER;
        ascnpalbumarticonid[8] = SC_NP_AAICON_LINEIN_AIRPLAY;
        ascnpalbumarticonid[9] = SC_NP_AAICON_SONOS_ALARM;
        ascnpalbumarticonid[10] = SC_NP_AAICON_SOUNDBAR_TV;
        ascnpalbumarticonid[11] = SC_NP_AAICON_MAX;
        $VALUES = ascnpalbumarticonid;
    }
}
