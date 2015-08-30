// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.wrappers;

import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.sclib.wrappers:
//            ZoneGroup

public class NowPlaying
{
    public static class NowPlayingMetaData
    {

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(this != obj) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            if(obj == null || getClass() != obj.getClass())
            {
                flag = false;
            } else
            {
                NowPlayingMetaData nowplayingmetadata = (NowPlayingMetaData)obj;
                if(labelId != nowplayingmetadata.labelId)
                    flag = false;
                else
                if(label == null ? nowplayingmetadata.label != null : !label.equals(nowplayingmetadata.label))
                    flag = false;
                else
                if(metadataType != nowplayingmetadata.metadataType)
                    flag = false;
                else
                if(text == null ? nowplayingmetadata.text != null : !text.equals(nowplayingmetadata.text))
                    flag = false;
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public String getLabel()
        {
            return label;
        }

        public int getLabelId()
        {
            return labelId;
        }

        public SCNPMetadataType getMetadataType()
        {
            return metadataType;
        }

        public String getText()
        {
            return text;
        }

        public int getTextAsInt()
        {
            int j = Integer.parseInt(text);
            int i = j;
_L2:
            return i;
            Exception exception;
            exception;
            i = 0;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public int hashCode()
        {
            int i = 0;
            int j = 31 * (31 * metadataType.hashCode() + labelId);
            int k;
            int l;
            if(label != null)
                k = label.hashCode();
            else
                k = 0;
            l = 31 * (j + k);
            if(text != null)
                i = text.hashCode();
            return l + i;
        }

        public String toString()
        {
            return (new StringBuilder()).append("MetaDataType[").append(metadataType).append("] Label[").append(label).append("] value[").append(text).append("]").toString();
        }

        final String label;
        final int labelId;
        final SCNPMetadataType metadataType;
        final String text;

        NowPlayingMetaData(SCNPMetadataType scnpmetadatatype, SCINowPlayingSource scinowplayingsource)
        {
            metadataType = scnpmetadatatype;
            label = scinowplayingsource.getPropertyLabel(scnpmetadatatype.swigValue());
            labelId = scinowplayingsource.getPropertyLabelID(scnpmetadatatype.swigValue());
            text = NowPlaying.getMetadataProperty(scnpmetadatatype, scinowplayingsource);
        }
    }


    public NowPlaying(SCINowPlaying scinowplaying)
    {
        sciNowPlaying = scinowplaying;
        sciNowPlayingSrc = (SCINowPlayingSource)LibraryUtils.cast(scinowplaying, com/sonos/sclib/SCINowPlayingSource);
        sciNowPlayingTransport = (SCINowPlayingTransport)LibraryUtils.cast(scinowplaying, com/sonos/sclib/SCINowPlayingTransport);
        sciNowPlayingSleepTimer = (SCINowPlayingSleepTimer)LibraryUtils.cast(scinowplaying, com/sonos/sclib/SCINowPlayingSleepTimer);
        if(sciNowPlayingSrc != null)
            sciNowPlayingRatings = (SCINowPlayingRatings)LibraryUtils.cast(sciNowPlayingSrc, com/sonos/sclib/SCINowPlayingRatings);
    }

    protected static String getMetadataProperty(SCNPMetadataType scnpmetadatatype, SCINowPlayingSource scinowplayingsource)
    {
        resetStringPointers();
        String s;
        if(scinowplayingsource.getProperty(scnpmetadatatype.swigValue(), stringPointer1) == SCRet.SC_RET_OK)
            s = stringPointer1[0];
        else
            s = null;
        return s;
    }

    private static void resetStringPointers()
    {
        stringPointer1[0] = "";
        stringPointer2[0] = "";
        stringPointer3[0] = "";
    }

    public boolean canShare()
    {
        return sciNowPlayingSrc.isShareable();
    }

    public void dumpAllMetaData()
    {
        for(Iterator iterator = getPositionalMetaData().values().iterator(); iterator.hasNext(); SLog.i("NowPlayingMD", ((NowPlayingMetaData)iterator.next()).toString()));
    }

    public String getAlbumArtURI(AlbumArtSize albumartsize)
    {
        return sciNowPlayingSrc.getAlbumArtURL(albumartsize.getPixelWidth());
    }

    public String getAsynchronousErrorString()
    {
        return getTransport().getAsynchronousErrorString();
    }

    public String getCurrentTrack()
    {
        return getMetadataProperty(SCNPMetadataType.SC_NP_META_CURRENT_TRACK, sciNowPlayingSrc);
    }

    public int getDefaultAlbumArtResourceId()
    {
        return getDefaultAlbumArtResourceId(0x7f060022);
    }

    public int getDefaultAlbumArtResourceId(int i)
    {
        SCNPAlbumArtIconID scnpalbumarticonid = sciNowPlayingSrc.getPlaceholderAlbumArtIconID();
        if(scnpalbumarticonid != null)
        {
            Integer integer = (Integer)ALBUMART_SWIG_MAP.get(Integer.valueOf(scnpalbumarticonid.swigValue()));
            if(integer != null)
                i = integer.intValue();
        }
        return i;
    }

    public int getDefaultSmallAlbumArtResourceId()
    {
        return getDefaultSmallAlbumArtResourceId(0x7f060022);
    }

    public int getDefaultSmallAlbumArtResourceId(int i)
    {
        SCNPAlbumArtIconID scnpalbumarticonid = sciNowPlayingSrc.getPlaceholderAlbumArtIconID();
        if(scnpalbumarticonid != null)
        {
            Integer integer = (Integer)ALBUMART_SMALL_SWIG_MAP.get(Integer.valueOf(scnpalbumarticonid.swigValue()));
            if(integer != null)
                i = integer.intValue();
        }
        return i;
    }

    public String[] getDoubleLineMetaData()
    {
        resetStringPointers();
        String as[];
        if(sciNowPlayingSrc.getTwoLineMetadata(stringPointer1, stringPointer2) == SCRet.SC_RET_OK)
        {
            as = new String[2];
            as[0] = stringPointer1[0];
            as[1] = stringPointer2[0];
        } else
        {
            as = null;
        }
        return as;
    }

    public String getErrorStringFromOpResultAndURI(String s, int i)
    {
        getTransport().getErrorStringFromOpResultAndURI(i, s, stringPointer1);
        return stringPointer1[0];
    }

    public NowPlayingMetaData getMetaData(String s)
    {
        return getPositionalMetadata(SCNPMetadataType.valueOf(s));
    }

    public String getNextTrackAlbumArtURI(AlbumArtSize albumartsize)
    {
        return sciNowPlayingSrc.getNextTrackAlbumArtURL(albumartsize.getPixelWidth());
    }

    protected String getNextTrackMetadataProperty(SCNPMetadataType scnpmetadatatype)
    {
        resetStringPointers();
        String s;
        if(sciNowPlayingSrc.getNextTrackProperty(scnpmetadatatype.swigValue(), stringPointer1) == SCRet.SC_RET_OK)
            s = stringPointer1[0];
        else
            s = null;
        return s;
    }

    public boolean getNightMode()
    {
        return sciNowPlayingSrc.getNightMode();
    }

    public SCINowPlayingSleepTimer getNowPlayingSleepTimer()
    {
        return sciNowPlayingSleepTimer;
    }

    public String getNumberOfTracks()
    {
        return getMetadataProperty(SCNPMetadataType.SC_NP_META_NUMBER_OF_TRACKS, sciNowPlayingSrc);
    }

    public int getPartnerLogoResourceId()
    {
        int i;
        if(sclibConstants.SCALBUMART_LOGO_TUNEIN.equals(getMetadataProperty(SCNPMetadataType.SC_NP_CUSTOM_PARTNER_LOGO, sciNowPlayingSrc)))
            i = 0x7f02008b;
        else
            i = 0;
        return i;
    }

    public com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState getPlayPauseDisplayState()
    {
        return getTransport().getPlayPauseDisplayState();
    }

    public SCIOp getPlayStateOp()
    {
        SCNPPlaybackState scnpplaybackstate = getTransport().getPlaybackState();
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCNPPlaybackState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState = new int[SCNPPlaybackState.values().length];
                NoSuchFieldError nosuchfielderror3;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PAUSED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_STOPPED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_UNKNOWN.ordinal()] = 4;
_L2:
                return;
                nosuchfielderror3;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCNPPlaybackState[scnpplaybackstate.ordinal()];
        JVM INSTR tableswitch 1 3: default 44
    //                   1 48
    //                   2 48
    //                   3 59;
           goto _L1 _L2 _L2 _L3
_L1:
        SCIOp sciop = null;
_L5:
        return sciop;
_L2:
        sciop = getTransport().createPlayOp();
        continue; /* Loop/switch isn't completed */
_L3:
        sciop = getTransport().createPauseOp();
        if(true) goto _L5; else goto _L4
_L4:
    }

    public HashMap getPositionalMetaData()
    {
        SCNPMetadataType ascnpmetadatatype[] = SCNPMetadataType.values();
        HashMap hashmap = new HashMap(ascnpmetadatatype.length);
        int i = ascnpmetadatatype.length;
        for(int j = 0; j < i; j++)
        {
            SCNPMetadataType scnpmetadatatype = ascnpmetadatatype[j];
            hashmap.put(scnpmetadatatype, new NowPlayingMetaData(scnpmetadatatype, sciNowPlayingSrc));
        }

        return hashmap;
    }

    public NowPlayingMetaData getPositionalMetadata(SCNPMetadataType scnpmetadatatype)
    {
        NowPlayingMetaData nowplayingmetadata;
        if(scnpmetadatatype != null)
            nowplayingmetadata = new NowPlayingMetaData(scnpmetadatatype, sciNowPlayingSrc);
        else
            nowplayingmetadata = null;
        return nowplayingmetadata;
    }

    public SCINowPlayingRatings getRatings()
    {
        return sciNowPlayingRatings;
    }

    public String getSingleLineMetaData()
    {
        resetStringPointers();
        String s;
        if(sciNowPlayingSrc.getOneLineMetadata(stringPointer1) == SCRet.SC_RET_OK)
            s = stringPointer1[0];
        else
            s = null;
        return s;
    }

    public SCNPSourceType getSourceType()
    {
        return SCNPSourceType.swigToEnum(sciNowPlayingSrc.getType());
    }

    public String getStreamInfo()
    {
        return getMetadataProperty(SCNPMetadataType.SC_NP_META_STREAMINFO, sciNowPlayingSrc);
    }

    public boolean getTVDialogEnhancement()
    {
        return sciNowPlayingSrc.getTVDialogEnhancement();
    }

    public String getTrackURI()
    {
        String s;
        if(getTransport().getTrackURI(stringPointer1) == SCRet.SC_RET_OK)
            s = stringPointer1[0];
        else
            s = null;
        return s;
    }

    public SCINowPlayingTransport getTransport()
    {
        return sciNowPlayingTransport;
    }

    public String[] getTripleLineMetaData()
    {
        resetStringPointers();
        String as[];
        if(sciNowPlayingSrc.getThreeLineMetadata(stringPointer1, stringPointer2, stringPointer3) == SCRet.SC_RET_OK)
        {
            as = new String[3];
            as[0] = stringPointer1[0];
            as[1] = stringPointer2[0];
            as[2] = stringPointer3[0];
        } else
        {
            as = null;
        }
        return as;
    }

    public ZoneGroup getZoneGroup()
    {
        SCIZoneGroup scizonegroup = (SCIZoneGroup)LibraryUtils.cast(sciNowPlayingSrc, com/sonos/sclib/SCIZoneGroup);
        ZoneGroup zonegroup;
        if(scizonegroup != null)
            zonegroup = new ZoneGroup(scizonegroup);
        else
            zonegroup = null;
        return zonegroup;
    }

    public boolean hasMoreMenu()
    {
        boolean flag;
        if(sciNowPlayingSrc.getMoreMenuDataSource() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasMusic()
    {
        SCINowPlayingTransport scinowplayingtransport = getTransport();
        boolean flag;
        if(scinowplayingtransport != null && scinowplayingtransport.hasMusic())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isAlarmRunning()
    {
        return "True".equals(getMetadataProperty(SCNPMetadataType.SC_NP_META_ALARM_RUNNING, sciNowPlayingSrc));
    }

    public boolean isNightModeEnabled()
    {
        boolean flag;
        if(getSourceType() == SCNPSourceType.SC_NP_TYPE_HT_AUDIO_SOURCE)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isPlayPauseEnabled()
    {
        return getTransport().isPlayPauseEnabled();
    }

    public boolean isPlaying()
    {
        boolean flag;
        if(getTransport().getPlaybackState() == SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isProgressBarEnabled()
    {
        SCINowPlayingTransport scinowplayingtransport = getTransport();
        boolean flag;
        if(scinowplayingtransport != null && (scinowplayingtransport.isRepeatEnabled() || scinowplayingtransport.isCrossfadeEnabled() || scinowplayingtransport.isShuffleEnabled() || scinowplayingtransport.isTrackPositionInfoAvailable()))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSleepSet()
    {
        boolean flag;
        if(!"0".equals(getMetadataProperty(SCNPMetadataType.SC_NP_META_SLEEP_TIMER_GENERATION, sciNowPlayingSrc)))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSnoozeRunning()
    {
        return "True".equals(getMetadataProperty(SCNPMetadataType.SC_NP_META_SNOOZE_RUNNING, sciNowPlayingSrc));
    }

    public boolean isSpeechEnhEnabled()
    {
        boolean flag;
        if(getSourceType() == SCNPSourceType.SC_NP_TYPE_HT_AUDIO_SOURCE)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setNightMode(boolean flag)
    {
        sciNowPlayingSrc.setNightMode(flag);
    }

    public void setTVDialogEnhancement(boolean flag)
    {
        sciNowPlayingSrc.setTVDialogEnhancement(flag);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sciNowPlaying.subscribe(scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sciNowPlaying.unsubscribe(scieventsink);
    }

    private static final HashMap ALBUMART_SMALL_SWIG_MAP;
    private static final HashMap ALBUMART_SWIG_MAP;
    private static final String stringPointer1[];
    private static final String stringPointer2[];
    private static final String stringPointer3[];
    public final SCINowPlaying sciNowPlaying;
    private SCINowPlayingRatings sciNowPlayingRatings;
    public final SCINowPlayingSleepTimer sciNowPlayingSleepTimer;
    public final SCINowPlayingSource sciNowPlayingSrc;
    public final SCINowPlayingTransport sciNowPlayingTransport;

    static 
    {
        String as[] = new String[1];
        as[0] = "";
        stringPointer1 = as;
        String as1[] = new String[1];
        as1[0] = "";
        stringPointer2 = as1;
        String as2[] = new String[1];
        as2[0] = "";
        stringPointer3 = as2;
        ALBUMART_SWIG_MAP = new HashMap(20);
        ALBUMART_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_AUDIO_COMPONENT.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_COMPUTER.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_HOME_THEATER.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_MP3_PLAYER.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_SONOS_ALARM.swigValue()), Integer.valueOf(0x7f06001e));
        ALBUMART_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_SONOS_DOCK.swigValue()), Integer.valueOf(0x7f06001f));
        ALBUMART_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_AIRPLAY.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_SOUNDBAR_TV.swigValue()), Integer.valueOf(0x7f060023));
        ALBUMART_SMALL_SWIG_MAP = new HashMap(20);
        ALBUMART_SMALL_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_AUDIO_COMPONENT.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SMALL_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_COMPUTER.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SMALL_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_HOME_THEATER.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SMALL_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_MP3_PLAYER.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SMALL_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_SONOS_ALARM.swigValue()), Integer.valueOf(0x7f06001e));
        ALBUMART_SMALL_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_SONOS_DOCK.swigValue()), Integer.valueOf(0x7f060020));
        ALBUMART_SMALL_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_LINEIN_AIRPLAY.swigValue()), Integer.valueOf(0x7f060021));
        ALBUMART_SMALL_SWIG_MAP.put(Integer.valueOf(SCNPAlbumArtIconID.SC_NP_AAICON_SOUNDBAR_TV.swigValue()), Integer.valueOf(0x7f060024));
    }
}
