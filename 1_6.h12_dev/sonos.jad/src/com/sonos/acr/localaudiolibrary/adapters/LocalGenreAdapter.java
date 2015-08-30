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

public class LocalGenreAdapter extends LocalMediaCursorAdapter
{
    public static class GenreBrowseItem extends LocalMusicBrowseItem
    {

        public GenreBrowseItem(String s, String s1)
        {
            title = s;
            id = LocalMediaUtils.createItemId(s1, s);
            resUri = LocalMediaUtils.createItemId("genre_tracks", s);
            container = true;
            aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            aaUri = sclib.SCALBUMART_MULTITRACK;
        }
    }

    public static class GenreCursor extends LocalMediaCursor
    {

        public SCILocalMusicBrowseItemInfo createItemFromCurrent()
        {
            return setSubtitle(new GenreBrowseItem(getString(nameColumn), typePrefix));
        }

        protected void loadColumnIndexes()
        {
            nameColumn = getColumnIndex("name");
        }

        int nameColumn;
        String typePrefix;

        public GenreCursor(Cursor cursor, String s)
        {
            super(cursor, LocalMediaCursor.Subtitle.SUBTITLE_NONE);
            typePrefix = s;
        }
    }


    public LocalGenreAdapter(Context context)
    {
        super(context);
    }

    public LocalGenreAdapter(Context context, String s, String s1, String s2)
    {
        super(context, s, s1, s2);
    }

    protected GenreCursor createMediaCursor(ContentResolver contentresolver)
    {
        String as[] = new String[1];
        as[0] = "DISTINCT name";
        String s;
        String as1[];
        Cursor cursor;
        GenreCursor genrecursor;
        if(StringUtils.isNotEmptyOrNull(searchTerm))
        {
            s = (new StringBuilder()).append("_id IN (SELECT genre_id FROM audio_genres_map, audio_meta, audio_genres WHERE audio_meta._id=audio_genres_map.audio_id AND audio_meta.is_music=1 AND audio_genres_map.genre_id=audio_genres._id ").append("AND audio_genres.name LIKE ?)").toString();
            as1 = new String[1];
            as1[0] = (new StringBuilder()).append("%").append(searchTerm).append("%").toString();
        } else
        {
            s = (new StringBuilder()).append("_id IN (SELECT genre_id FROM audio_genres_map, audio_meta, audio_genres WHERE audio_meta._id=audio_genres_map.audio_id AND audio_meta.is_music=1 AND audio_genres_map.genre_id=audio_genres._id ").append("AND TRIM(audio_genres.name)!=?)").toString();
            as1 = new String[1];
            as1[0] = "";
        }
        cursor = contentresolver.query(android.provider.MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI, as, s, as1, "name COLLATE NOCASE ASC");
        if(cursor != null)
            genrecursor = new GenreCursor(cursor, "genre_albums");
        else
            genrecursor = null;
        return genrecursor;
    }

    protected volatile LocalMediaCursor createMediaCursor(ContentResolver contentresolver)
    {
        return createMediaCursor(contentresolver);
    }
}
