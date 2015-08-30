// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary;

import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.SCILocalMusicBrowseItemInfoSwigBase;

public class LocalMusicBrowseItem extends SCILocalMusicBrowseItemInfoSwigBase
{

    public LocalMusicBrowseItem()
    {
        aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE;
        duration = -1L;
        trackNumber = 0;
        container = false;
        itemType = com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType.TRACK;
    }

    public String getAlbum()
    {
        return StringUtils.ensureNotNull(album);
    }

    public String getArtMimeType()
    {
        return StringUtils.ensureNotNull(aaMimeType);
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getArtType()
    {
        return aaType;
    }

    public String getArtUri()
    {
        return StringUtils.ensureNotNull(aaUri);
    }

    public String getArtist()
    {
        return StringUtils.ensureNotNull(artist);
    }

    public int getByteOffsetForTime(long l)
    {
        return -1;
    }

    public int getDiscNumber()
    {
        return trackNumber / 1000;
    }

    public String getDisplayString(com.sonos.sclib.SCILocalMusicBrowseItemInfo.DisplayStringId displaystringid)
    {
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCILocalMusicBrowseItemInfo$DisplayStringId[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCILocalMusicBrowseItemInfo$DisplayStringId = new int[com.sonos.sclib.SCILocalMusicBrowseItemInfo.DisplayStringId.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCILocalMusicBrowseItemInfo$DisplayStringId[com.sonos.sclib.SCILocalMusicBrowseItemInfo.DisplayStringId.LMBI_DISPLAY_STRING_MAIN.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$com$sonos$sclib$SCILocalMusicBrowseItemInfo$DisplayStringId[com.sonos.sclib.SCILocalMusicBrowseItemInfo.DisplayStringId.LMBI_DISPLAY_STRING_LINE_2.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCILocalMusicBrowseItemInfo.DisplayStringId[displaystringid.ordinal()];
        JVM INSTR tableswitch 1 2: default 32
    //                   1 37
    //                   2 48;
           goto _L1 _L2 _L3
_L1:
        String s = "";
_L5:
        return s;
_L2:
        s = StringUtils.ensureNotNull(title);
        continue; /* Loop/switch isn't completed */
_L3:
        s = StringUtils.ensureNotNull(subTitle);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public long getDuration()
    {
        return duration;
    }

    public String getId()
    {
        return StringUtils.ensureNotNull(id);
    }

    public com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType getItemType()
    {
        return itemType;
    }

    public String getMimeType()
    {
        return StringUtils.ensureNotNull(resMimeType);
    }

    public String getResUri()
    {
        return StringUtils.ensureNotNull(resUri);
    }

    public String getTitle()
    {
        return StringUtils.ensureNotNull(title);
    }

    public int getTrackNumber()
    {
        return trackNumber % 1000;
    }

    public boolean isContainer()
    {
        return container;
    }

    public boolean isPlayable()
    {
        return StringUtils.isNotEmptyOrNull(resUri);
    }

    public String toString()
    {
        return (new StringBuilder()).append("LocalMusicBrowseItem{id='").append(id).append('\'').append(", album='").append(album).append('\'').append(", artist='").append(artist).append('\'').append(", resUri='").append(resUri).append('\'').append(", resMimeType='").append(resMimeType).append('\'').append(", aaUri='").append(aaUri).append('\'').append(", aaType='").append(aaType).append('\'').append(", aaMimeType='").append(aaMimeType).append('\'').append(", title='").append(title).append('\'').append(", duration=").append(duration).append(", subTitle='").append(subTitle).append('\'').append(", container=").append(container).append('}').toString();
    }

    public String aaMimeType;
    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType aaType;
    public String aaUri;
    public String album;
    public String artist;
    public boolean container;
    public long duration;
    public String id;
    public com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType itemType;
    public String playableId;
    public String resMimeType;
    public String resUri;
    public String subTitle;
    public String title;
    public int trackNumber;
}
