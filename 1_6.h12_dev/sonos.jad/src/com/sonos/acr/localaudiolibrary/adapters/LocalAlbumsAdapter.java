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

// Referenced classes of package com.sonos.acr.localaudiolibrary.adapters:
//            LocalMediaCursorAdapter, LocalMediaCursor

public class LocalAlbumsAdapter extends LocalMediaCursorAdapter
{
    public static class AlbumBrowseItem extends LocalMusicBrowseItem
    {

        public AlbumBrowseItem(String s, String s1, long l, String s2)
        {
            title = s;
            album = s;
            artist = s1;
            id = LocalMediaUtils.createItemId("album", Long.valueOf(l));
            resUri = id;
            container = true;
            aaUri = s2;
            if(StringUtils.isNotEmptyOrNull(aaUri))
            {
                aaUri = (new StringBuilder()).append("file://").append(aaUri).toString();
                aaMimeType = LocalMediaUtils.getAlbumArtMimeType(s2);
                aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL_URL;
            } else
            {
                aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL;
            }
            itemType = com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType.ALBUM;
        }
    }

    public static class AlbumCursor extends LocalMediaCursor
    {

        protected SCILocalMusicBrowseItemInfo createItemFromCurrent()
        {
            return setSubtitle(new AlbumBrowseItem(getString(nameColumn), getString(artistColumn), getLong(idColumn), getString(albumArtColumn)));
        }

        protected void loadColumnIndexes()
        {
            super.loadColumnIndexes();
            nameColumn = getColumnIndex("album");
            albumArtColumn = getColumnIndex("album_art");
            artistColumn = getColumnIndex("artist");
        }

        int albumArtColumn;
        int artistColumn;
        int nameColumn;

        public AlbumCursor(Cursor cursor, LocalMediaCursor.Subtitle subtitle)
        {
            super(cursor, subtitle);
        }
    }


    public LocalAlbumsAdapter(Context context)
    {
        super(context);
    }

    public LocalAlbumsAdapter(Context context, String s, String s1)
    {
        super(context, s, s1);
    }

    public LocalAlbumsAdapter(Context context, String s, String s1, String s2)
    {
        super(context, s, s1, s2);
    }

    protected AlbumCursor createMediaCursor(ContentResolver contentresolver)
    {
        AlbumCursor albumcursor = null;
        if(!StringUtils.isNotEmptyOrNull(searchTerm)) goto _L2; else goto _L1
_L1:
        android.net.Uri uri1 = android.provider.MediaStore.Audio.Albums.getContentUri("external");
        String as1[] = new String[1];
        as1[0] = (new StringBuilder()).append("%").append(searchTerm).append("%").toString();
        Cursor cursor3 = contentresolver.query(uri1, null, "album LIKE ?", as1, "album COLLATE NOCASE ASC");
        if(cursor3 != null)
            albumcursor = new AlbumCursor(cursor3, LocalMediaCursor.Subtitle.SUBTITLE_ARTIST);
_L4:
        return albumcursor;
_L2:
        if("artist_albums".equals(containerType))
        {
            if(StringUtils.isLong(containerId))
            {
                Cursor cursor2 = contentresolver.query(android.provider.MediaStore.Audio.Artists.Albums.getContentUri("external", Long.valueOf(containerId).longValue()), null, null, null, "album COLLATE NOCASE ASC");
                if(cursor2 != null)
                    albumcursor = new AlbumCursor(cursor2, LocalMediaCursor.Subtitle.SUBTITLE_NONE);
            }
        } else
        if("genre_albums".equals(containerType))
        {
            android.net.Uri uri = android.provider.MediaStore.Audio.Albums.getContentUri("external");
            String as[] = new String[1];
            as[0] = containerId;
            Cursor cursor1 = contentresolver.query(uri, null, "album_info._id IN (SELECT (audio_meta.album_id) album_id FROM audio_meta, audio_genres_map, audio_genres WHERE audio_genres_map.audio_id=audio_meta._id AND audio_genres_map.genre_id=audio_genres._id AND audio_genres.name=?)", as, "album COLLATE NOCASE ASC");
            if(cursor1 != null)
                albumcursor = new AlbumCursor(cursor1, LocalMediaCursor.Subtitle.SUBTITLE_ARTIST);
        } else
        {
            Cursor cursor = contentresolver.query(android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, "", null, "album COLLATE NOCASE ASC");
            if(cursor != null)
                albumcursor = new AlbumCursor(cursor, LocalMediaCursor.Subtitle.SUBTITLE_ARTIST);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected volatile LocalMediaCursor createMediaCursor(ContentResolver contentresolver)
    {
        return createMediaCursor(contentresolver);
    }

    public com.sonos.sclib.SCILocalMediaCollection.AllNodeType getAllNodeType()
    {
        com.sonos.sclib.SCILocalMediaCollection.AllNodeType allnodetype;
        if("artist_albums".equals(containerType) || "genre_albums".equals(containerType))
            allnodetype = com.sonos.sclib.SCILocalMediaCollection.AllNodeType.LMBI_ALL_NODE_PUSHABLE_ALL;
        else
            allnodetype = super.getAllNodeType();
        return allnodetype;
    }

    public com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType getPresentationType()
    {
        return com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_GRID;
    }
}
