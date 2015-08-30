// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary.adapters;

import android.content.ContentResolver;
import android.content.Context;
import com.sonos.acr.util.StringUtils;

// Referenced classes of package com.sonos.acr.localaudiolibrary.adapters:
//            LocalMediaCursorAdapter, TrackCursor, LocalMediaCursor

public class LocalTracksAdapter extends LocalMediaCursorAdapter
{

    public LocalTracksAdapter(Context context, String s)
    {
        super(context, s, null);
        idColumn = "_id";
    }

    public LocalTracksAdapter(Context context, String s, String s1)
    {
        super(context, s, s1);
        idColumn = "_id";
    }

    public LocalTracksAdapter(Context context, String s, String s1, String s2)
    {
        super(context, s, s1, s2);
        idColumn = "_id";
    }

    protected volatile LocalMediaCursor createMediaCursor(ContentResolver contentresolver)
    {
        return createMediaCursor(contentresolver);
    }

    protected TrackCursor createMediaCursor(ContentResolver contentresolver)
    {
        TrackCursor trackcursor;
        android.net.Uri uri;
        String s;
        LocalMediaCursor.Subtitle subtitle;
        String s1;
        String as[];
        trackcursor = null;
        uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        s = "is_music != 0";
        subtitle = LocalMediaCursor.Subtitle.SUBTITLE_NONE;
        s1 = null;
        as = null;
        if(!StringUtils.isNotEmptyOrNull(searchTerm)) goto _L2; else goto _L1
_L1:
        subtitle = LocalMediaCursor.Subtitle.SUBTITLE_ARTIST_ALBUM;
        s = (new StringBuilder()).append(s).append(" AND title LIKE ?").toString();
        as = new String[1];
        as[0] = (new StringBuilder()).append("%").append(searchTerm).append("%").toString();
_L4:
        android.database.Cursor cursor = contentresolver.query(uri, null, s, as, s1);
        if(cursor != null)
            trackcursor = new TrackCursor(cursor, subtitle, idColumn);
        return trackcursor;
_L2:
        if("genre_tracks".equals(containerType))
        {
            subtitle = LocalMediaCursor.Subtitle.SUBTITLE_ARTIST_ALBUM;
            s1 = "title COLLATE NOCASE ASC";
            s = (new StringBuilder()).append("_id IN (SELECT audio_id FROM audio_genres_map, audio_genres WHERE audio_genres_map.genre_id=audio_genres._id AND audio_genres.name = ?) AND ").append(s).toString();
            as = new String[1];
            as[0] = containerId;
        } else
        if("podcast_album".equals(containerType))
        {
            s = "album = ? AND is_podcast != 0";
            as = new String[1];
            as[0] = containerId;
            subtitle = LocalMediaCursor.Subtitle.SUBTITLE_ARTIST_ALBUM;
        } else
        if(StringUtils.isLong(containerId))
        {
            if("album".equals(containerType))
            {
                s = (new StringBuilder()).append("album_id = ").append(containerId).append(" AND ").append(s).toString();
                showTrackNumber = true;
                s1 = "track ASC";
            } else
            if("artist_tracks".equals(containerType))
            {
                s = (new StringBuilder()).append("artist_id = ").append(containerId).append(" AND ").append(s).toString();
                subtitle = LocalMediaCursor.Subtitle.SUBTITLE_ALBUM;
                s1 = "title COLLATE NOCASE ASC";
            } else
            if("playlist".equals(containerType))
            {
                uri = android.provider.MediaStore.Audio.Playlists.Members.getContentUri("external", Long.parseLong(containerId));
                idColumn = "audio_id";
                subtitle = LocalMediaCursor.Subtitle.SUBTITLE_ARTIST_ALBUM;
                s1 = "play_order ASC";
            } else
            {
                s = (new StringBuilder()).append("_id = ").append(containerId).toString();
                subtitle = LocalMediaCursor.Subtitle.SUBTITLE_ARTIST_ALBUM;
                s1 = "title COLLATE NOCASE ASC";
            }
        } else
        if("tracks".equals(containerType))
        {
            subtitle = LocalMediaCursor.Subtitle.SUBTITLE_ARTIST_ALBUM;
            s1 = "title COLLATE NOCASE ASC";
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public com.sonos.sclib.SCILocalMediaCollection.AllNodeType getAllNodeType()
    {
        com.sonos.sclib.SCILocalMediaCollection.AllNodeType allnodetype;
        if("album".equals(containerType))
            allnodetype = com.sonos.sclib.SCILocalMediaCollection.AllNodeType.LMBI_ALL_NODE_COMPLETE_ALBUM;
        else
            allnodetype = com.sonos.sclib.SCILocalMediaCollection.AllNodeType.LMBI_ALL_NODE_ALL_TRACKS;
        return allnodetype;
    }

    public com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType getPresentationType()
    {
        com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType sclistpresentationtype;
        if("album".equals(containerType))
            sclistpresentationtype = com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_ALBUM;
        else
            sclistpresentationtype = com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_LIST;
        return sclistpresentationtype;
    }

    protected String idColumn;
}
