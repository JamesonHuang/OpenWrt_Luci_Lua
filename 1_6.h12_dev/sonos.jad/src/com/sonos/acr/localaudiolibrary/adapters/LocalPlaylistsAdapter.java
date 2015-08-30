// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import com.sonos.acr.localaudiolibrary.LocalMediaUtils;
import com.sonos.acr.localaudiolibrary.LocalMusicBrowseItem;
import com.sonos.sclib.SCILocalMusicBrowseItemInfo;
import com.sonos.sclib.sclib;

// Referenced classes of package com.sonos.acr.localaudiolibrary.adapters:
//            LocalMediaCursorAdapter, LocalMediaCursor

public class LocalPlaylistsAdapter extends LocalMediaCursorAdapter
{
    public static class PlaylistBrowseItem extends LocalMusicBrowseItem
    {

        public PlaylistBrowseItem(String s, long l)
        {
            title = s;
            id = LocalMediaUtils.createItemId("playlist", Long.valueOf(l));
            resUri = id;
            container = true;
            aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            aaUri = sclib.SCALBUMART_MULTITRACK;
        }
    }

    public static class PlaylistCursor extends LocalMediaCursor
    {

        protected SCILocalMusicBrowseItemInfo createItemFromCurrent()
        {
            return setSubtitle(new PlaylistBrowseItem(getString(nameColumn), getLong(idColumn)));
        }

        protected void loadColumnIndexes()
        {
            super.loadColumnIndexes();
            nameColumn = getColumnIndex("name");
        }

        int nameColumn;

        public PlaylistCursor(Cursor cursor)
        {
            super(cursor, LocalMediaCursor.Subtitle.SUBTITLE_NONE);
        }
    }


    public LocalPlaylistsAdapter(Context context)
    {
        super(context);
    }

    protected volatile LocalMediaCursor createMediaCursor(ContentResolver contentresolver)
    {
        return createMediaCursor(contentresolver);
    }

    protected PlaylistCursor createMediaCursor(ContentResolver contentresolver)
    {
        PlaylistCursor playlistcursor = null;
        Cursor cursor = contentresolver.query(android.provider.MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, null, "", null, "name COLLATE NOCASE ASC");
        if(cursor != null)
            playlistcursor = new PlaylistCursor(cursor);
        return playlistcursor;
    }
}
