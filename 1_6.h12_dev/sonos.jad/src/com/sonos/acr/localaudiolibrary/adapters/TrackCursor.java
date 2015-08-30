// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary.adapters;

import android.database.Cursor;
import com.sonos.acr.localaudiolibrary.LocalMediaUtils;
import com.sonos.acr.localaudiolibrary.LocalTrackBrowseItem;
import com.sonos.sclib.*;
import java.util.HashSet;

// Referenced classes of package com.sonos.acr.localaudiolibrary.adapters:
//            LocalMediaCursor

public class TrackCursor extends LocalMediaCursor
{

    public TrackCursor(Cursor cursor, LocalMediaCursor.Subtitle subtitle, String s)
    {
        super(cursor, subtitle);
        artistColumn = -1;
        titleColumn = -1;
        albumColumn = -1;
        durationColumn = -1;
        mimeTypeColumn = -1;
        albumIdColumn = -1;
        dataColumn = -1;
        trackNumColumn = -1;
        isPodcastColumn = -1;
        idColumnName = s;
    }

    protected SCILocalMusicBrowseItemInfo createItemFromCurrent()
    {
        LocalTrackBrowseItem localtrackbrowseitem = new LocalTrackBrowseItem();
        localtrackbrowseitem.id = getString(idColumn);
        localtrackbrowseitem.trackId = getLong(idColumn);
        localtrackbrowseitem.playableId = getString(dataColumn);
        int i = localtrackbrowseitem.playableId.lastIndexOf('.');
        if(i > 0)
        {
            String s = localtrackbrowseitem.playableId.substring(i + 1).toLowerCase();
            if(LocalMediaUtils.SUPPORTED_EXTENTIONS.contains(s))
                localtrackbrowseitem.resUri = sclib.getVirtualResUriForResourceId(ResURIDataType.TYPE_TRACK, localtrackbrowseitem.id, s, true);
        }
        localtrackbrowseitem.album = getString(albumColumn);
        localtrackbrowseitem.artist = getString(artistColumn);
        localtrackbrowseitem.duration = Math.round((double)getLong(durationColumn) / 1000D);
        localtrackbrowseitem.title = getString(titleColumn);
        localtrackbrowseitem.resMimeType = getString(mimeTypeColumn);
        localtrackbrowseitem.albumId = getLong(albumIdColumn);
        localtrackbrowseitem.trackNumber = getInt(trackNumColumn);
        com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType itemtype;
        if(getInt(isPodcastColumn) != 0)
            itemtype = com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType.PODCAST;
        else
            itemtype = com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType.TRACK;
        localtrackbrowseitem.itemType = itemtype;
        return setSubtitle(localtrackbrowseitem);
    }

    protected void loadColumnIndexes()
    {
        idColumn = getColumnIndexOrThrow(idColumnName);
        artistColumn = getColumnIndexOrThrow("artist");
        titleColumn = getColumnIndexOrThrow("title");
        albumColumn = getColumnIndexOrThrow("album");
        durationColumn = getColumnIndexOrThrow("duration");
        mimeTypeColumn = getColumnIndexOrThrow("mime_type");
        albumIdColumn = getColumnIndexOrThrow("album_id");
        dataColumn = getColumnIndexOrThrow("_data");
        trackNumColumn = getColumnIndexOrThrow("track");
        isPodcastColumn = getColumnIndexOrThrow("is_podcast");
    }

    protected int albumColumn;
    protected int albumIdColumn;
    protected int artistColumn;
    protected int dataColumn;
    protected int durationColumn;
    protected String idColumnName;
    protected int isPodcastColumn;
    protected int mimeTypeColumn;
    protected int titleColumn;
    protected int trackNumColumn;
}
