// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import com.sonos.acr.localaudiolibrary.LocalMediaUtils;
import com.sonos.acr.localaudiolibrary.LocalMusicBrowseItem;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.SCILocalMusicBrowseItemInfo;
import com.sonos.sclib.sclib;

// Referenced classes of package com.sonos.acr.localaudiolibrary.adapters:
//            LocalMediaCursorAdapter, LocalMediaCursor

public class LocalArtistsAdapter extends LocalMediaCursorAdapter
{
    public static class ArtistBrowseItem extends LocalMusicBrowseItem
    {

        public ArtistBrowseItem(String s, long l, String s1)
        {
            title = s;
            artist = s;
            id = LocalMediaUtils.createItemId(s1, Long.valueOf(l));
            resUri = LocalMediaUtils.createItemId("artist_tracks", Long.valueOf(l));
            container = true;
            aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            aaUri = sclib.SCALBUMART_MULTITRACK;
            itemType = com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType.ARTIST;
        }
    }

    public static class ArtistsCursor extends LocalMediaCursor
    {

        public SCILocalMusicBrowseItemInfo createItemFromCurrent()
        {
            return setSubtitle(new ArtistBrowseItem(getString(nameColumn), getLong(idColumn), typePrefix));
        }

        protected void loadColumnIndexes()
        {
            super.loadColumnIndexes();
            nameColumn = getColumnIndex("artist");
        }

        int nameColumn;
        String typePrefix;

        public ArtistsCursor(Cursor cursor, String s)
        {
            super(cursor, LocalMediaCursor.Subtitle.SUBTITLE_NONE);
            typePrefix = s;
        }
    }


    public LocalArtistsAdapter(Context context)
    {
        super(context);
    }

    public LocalArtistsAdapter(Context context, String s, String s1, String s2)
    {
        super(context, s, s1, s2);
    }

    protected ArtistsCursor createMediaCursor(ContentResolver contentresolver)
    {
        ArtistsCursor artistscursor = null;
        if(!StringUtils.isNotEmptyOrNull(searchTerm)) goto _L2; else goto _L1
_L1:
        android.net.Uri uri = android.provider.MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append("%").append(searchTerm).append("%").toString();
        Cursor cursor1 = contentresolver.query(uri, null, "artist LIKE ?", as, null);
        if(cursor1 != null)
            artistscursor = new ArtistsCursor(cursor1, "artist_albums");
_L4:
        return artistscursor;
_L2:
        Cursor cursor = contentresolver.query(android.provider.MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, "", null, null);
        if(cursor != null)
            artistscursor = new ArtistsCursor(cursor, "artist_albums");
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected volatile LocalMediaCursor createMediaCursor(ContentResolver contentresolver)
    {
        return createMediaCursor(contentresolver);
    }
}
