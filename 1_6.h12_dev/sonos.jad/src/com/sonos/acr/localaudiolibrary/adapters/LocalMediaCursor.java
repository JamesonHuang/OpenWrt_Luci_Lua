// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary.adapters;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.sonos.acr.localaudiolibrary.LocalMusicBrowseItem;
import com.sonos.sclib.SCILocalMusicBrowseItemInfo;

public abstract class LocalMediaCursor extends CursorWrapper
{
    public static final class Subtitle extends Enum
    {

        public static Subtitle valueOf(String s)
        {
            return (Subtitle)Enum.valueOf(com/sonos/acr/localaudiolibrary/adapters/LocalMediaCursor$Subtitle, s);
        }

        public static Subtitle[] values()
        {
            return (Subtitle[])$VALUES.clone();
        }

        private static final Subtitle $VALUES[];
        public static final Subtitle SUBTITLE_ALBUM;
        public static final Subtitle SUBTITLE_ARTIST;
        public static final Subtitle SUBTITLE_ARTIST_ALBUM;
        public static final Subtitle SUBTITLE_NONE;

        static 
        {
            SUBTITLE_ARTIST = new Subtitle("SUBTITLE_ARTIST", 0);
            SUBTITLE_ALBUM = new Subtitle("SUBTITLE_ALBUM", 1);
            SUBTITLE_ARTIST_ALBUM = new Subtitle("SUBTITLE_ARTIST_ALBUM", 2);
            SUBTITLE_NONE = new Subtitle("SUBTITLE_NONE", 3);
            Subtitle asubtitle[] = new Subtitle[4];
            asubtitle[0] = SUBTITLE_ARTIST;
            asubtitle[1] = SUBTITLE_ALBUM;
            asubtitle[2] = SUBTITLE_ARTIST_ALBUM;
            asubtitle[3] = SUBTITLE_NONE;
            $VALUES = asubtitle;
        }

        private Subtitle(String s, int i)
        {
            super(s, i);
        }
    }


    public LocalMediaCursor(Cursor cursor, Subtitle subtitle)
    {
        super(cursor);
        idColumn = -1;
        subTitleSelector = Subtitle.SUBTITLE_NONE;
        subTitleSelector = subtitle;
    }

    protected abstract SCILocalMusicBrowseItemInfo createItemFromCurrent();

    public SCILocalMusicBrowseItemInfo getCurrentItem()
    {
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(cursorValid)
        {
            if(idColumn < 0)
                loadColumnIndexes();
            scilocalmusicbrowseiteminfo = createItemFromCurrent();
        } else
        {
            scilocalmusicbrowseiteminfo = null;
        }
        return scilocalmusicbrowseiteminfo;
    }

    protected void loadColumnIndexes()
    {
        idColumn = getColumnIndexOrThrow("_id");
    }

    protected LocalMusicBrowseItem setSubtitle(LocalMusicBrowseItem localmusicbrowseitem)
    {
        class _cls1
        {

            static final int $SwitchMap$com$sonos$acr$localaudiolibrary$adapters$LocalMediaCursor$Subtitle[];

            static 
            {
                $SwitchMap$com$sonos$acr$localaudiolibrary$adapters$LocalMediaCursor$Subtitle = new int[Subtitle.values().length];
                NoSuchFieldError nosuchfielderror3;
                try
                {
                    $SwitchMap$com$sonos$acr$localaudiolibrary$adapters$LocalMediaCursor$Subtitle[Subtitle.SUBTITLE_ARTIST.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$acr$localaudiolibrary$adapters$LocalMediaCursor$Subtitle[Subtitle.SUBTITLE_ALBUM.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$acr$localaudiolibrary$adapters$LocalMediaCursor$Subtitle[Subtitle.SUBTITLE_ARTIST_ALBUM.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$com$sonos$acr$localaudiolibrary$adapters$LocalMediaCursor$Subtitle[Subtitle.SUBTITLE_NONE.ordinal()] = 4;
_L2:
                return;
                nosuchfielderror3;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.acr.localaudiolibrary.adapters.LocalMediaCursor.Subtitle[subTitleSelector.ordinal()];
        JVM INSTR tableswitch 1 4: default 40
    //                   1 42
    //                   2 53
    //                   3 64
    //                   4 96;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return localmusicbrowseitem;
_L2:
        localmusicbrowseitem.subTitle = localmusicbrowseitem.artist;
        continue; /* Loop/switch isn't completed */
_L3:
        localmusicbrowseitem.subTitle = localmusicbrowseitem.album;
        continue; /* Loop/switch isn't completed */
_L4:
        Object aobj[] = new Object[2];
        aobj[0] = localmusicbrowseitem.artist;
        aobj[1] = localmusicbrowseitem.album;
        localmusicbrowseitem.subTitle = String.format("%s - %s", aobj);
        continue; /* Loop/switch isn't completed */
_L5:
        localmusicbrowseitem.subTitle = null;
        if(true) goto _L1; else goto _L6
_L6:
    }

    public static final String LOG_TAG = com/sonos/acr/localaudiolibrary/adapters/LocalMediaCursor.getSimpleName();
    final boolean cursorValid = moveToFirst();
    protected int idColumn;
    protected Subtitle subTitleSelector;

}
