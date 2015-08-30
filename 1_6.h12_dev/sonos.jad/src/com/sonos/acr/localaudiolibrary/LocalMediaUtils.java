// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.localaudiolibrary.adapters.TrackCursor;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.SCILocalMusicBrowseItemInfo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class LocalMediaUtils
{

    public LocalMediaUtils()
    {
    }

    public static String createItemId(String s, Object obj)
    {
        return (new StringBuilder()).append(s).append(":").append(obj).toString();
    }

    private static LinkedHashMap createLruCache(int i)
    {
        return new LinkedHashMap(0.75F, true, i) {

            public boolean removeEldestEntry(java.util.Map.Entry entry)
            {
                boolean flag;
                if(size() > maxEntries)
                    flag = true;
                else
                    flag = false;
                return flag;
            }

            final int val$maxEntries;

            
            {
                maxEntries = j;
                super(final_i, f, flag);
            }
        }
;
    }

    public static Bitmap decodeBitmapUri(String s, android.graphics.BitmapFactory.Options options)
    {
        Bitmap bitmap;
        ParcelFileDescriptor parcelfiledescriptor;
        bitmap = null;
        parcelfiledescriptor = null;
        parcelfiledescriptor = contentResolver.openFileDescriptor(Uri.parse(s), "r");
        if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
        Bitmap bitmap1 = BitmapFactory.decodeFileDescriptor(parcelfiledescriptor.getFileDescriptor(), null, options);
        bitmap = bitmap1;
        if(parcelfiledescriptor != null)
            try
            {
                parcelfiledescriptor.close();
            }
            catch(IOException ioexception4)
            {
                SLog.e(LOG_TAG, "error closing file to decode bitmap", ioexception4);
            }
_L4:
        return bitmap;
_L2:
        if(parcelfiledescriptor != null)
            try
            {
                parcelfiledescriptor.close();
            }
            catch(IOException ioexception3)
            {
                SLog.e(LOG_TAG, "error closing file to decode bitmap", ioexception3);
            }
        continue; /* Loop/switch isn't completed */
        IllegalStateException illegalstateexception;
        illegalstateexception;
        if(parcelfiledescriptor != null)
            try
            {
                parcelfiledescriptor.close();
            }
            catch(IOException ioexception2)
            {
                SLog.e(LOG_TAG, "error closing file to decode bitmap", ioexception2);
            }
        continue; /* Loop/switch isn't completed */
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        if(parcelfiledescriptor != null)
            try
            {
                parcelfiledescriptor.close();
            }
            catch(IOException ioexception1)
            {
                SLog.e(LOG_TAG, "error closing file to decode bitmap", ioexception1);
            }
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        if(parcelfiledescriptor != null)
            try
            {
                parcelfiledescriptor.close();
            }
            catch(IOException ioexception)
            {
                SLog.e(LOG_TAG, "error closing file to decode bitmap", ioexception);
            }
        throw exception;
    }

    public static String getAlbumArtMimeType(String s)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        decodeBitmapUri(s, options);
        return options.outMimeType;
    }

    public static String getAlbumArtUri(long l, long l1)
    {
        String s = (String)albumArtLinkCache.get(Long.valueOf(l));
        if(s == null)
            s = (String)trackArtLinkCache.get(Long.valueOf(l1));
        if(s == null && !albumArtLinkCache.containsKey(Long.valueOf(l)) && !trackArtLinkCache.containsKey(Long.valueOf(l1)))
        {
            s = lookupArtworkUri(l, l1);
            if(l > -1L)
                albumArtLinkCache.put(Long.valueOf(l), s);
            if(l1 > -1L)
                trackArtLinkCache.put(Long.valueOf(l1), s);
        }
        return s;
    }

    public static SCILocalMusicBrowseItemInfo getAlbumItem(long l)
    {
        Cursor cursor = contentResolver.query(android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, (new StringBuilder()).append("_id = ").append(l).toString(), null, null);
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(cursor != null)
        {
            com.sonos.acr.localaudiolibrary.adapters.LocalAlbumsAdapter.AlbumCursor albumcursor = new com.sonos.acr.localaudiolibrary.adapters.LocalAlbumsAdapter.AlbumCursor(cursor, com.sonos.acr.localaudiolibrary.adapters.LocalMediaCursor.Subtitle.SUBTITLE_ARTIST);
            scilocalmusicbrowseiteminfo = albumcursor.getCurrentItem();
            albumcursor.close();
        } else
        {
            scilocalmusicbrowseiteminfo = null;
        }
        return scilocalmusicbrowseiteminfo;
    }

    public static SCILocalMusicBrowseItemInfo getArtistItem(String s, long l)
    {
        Cursor cursor = contentResolver.query(android.provider.MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, (new StringBuilder()).append("_id = ").append(l).toString(), null, null);
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(cursor != null)
        {
            com.sonos.acr.localaudiolibrary.adapters.LocalArtistsAdapter.ArtistsCursor artistscursor = new com.sonos.acr.localaudiolibrary.adapters.LocalArtistsAdapter.ArtistsCursor(cursor, s);
            scilocalmusicbrowseiteminfo = artistscursor.getCurrentItem();
            artistscursor.close();
        } else
        {
            scilocalmusicbrowseiteminfo = null;
        }
        return scilocalmusicbrowseiteminfo;
    }

    public static SCILocalMusicBrowseItemInfo getArtistItem(String s, String s1)
    {
        String s2 = android.provider.MediaStore.Audio.keyFor(s1);
        ContentResolver contentresolver = contentResolver;
        Uri uri = android.provider.MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        String as[] = new String[1];
        as[0] = s2;
        Cursor cursor = contentresolver.query(uri, null, "artist_key LIKE ?", as, null);
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(cursor != null)
        {
            com.sonos.acr.localaudiolibrary.adapters.LocalArtistsAdapter.ArtistsCursor artistscursor = new com.sonos.acr.localaudiolibrary.adapters.LocalArtistsAdapter.ArtistsCursor(cursor, s);
            scilocalmusicbrowseiteminfo = artistscursor.getCurrentItem();
            artistscursor.close();
        } else
        {
            scilocalmusicbrowseiteminfo = null;
        }
        return scilocalmusicbrowseiteminfo;
    }

    public static String getFilePathForTrack(String s)
    {
        String s1 = "";
        if(StringUtils.isLong(s))
        {
            Cursor cursor = contentResolver.query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, (new StringBuilder()).append("_id = ").append(s).toString(), null, null);
            if(cursor != null)
            {
                if(cursor.moveToFirst())
                    s1 = cursor.getString(cursor.getColumnIndex("_data"));
                else
                    SLog.e(LOG_TAG, "Failed to move cursor to first row (no query results).");
                cursor.close();
            } else
            {
                SLog.e(LOG_TAG, "Failed to retrieve track info, cursor is null");
            }
        } else
        {
            SLog.e(LOG_TAG, "TrackID provided is not a number");
        }
        return s1;
    }

    public static SCILocalMusicBrowseItemInfo getPlaylistItem(long l)
    {
        Cursor cursor = contentResolver.query(android.provider.MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, null, (new StringBuilder()).append("_id = ").append(l).toString(), null, null);
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(cursor != null)
        {
            com.sonos.acr.localaudiolibrary.adapters.LocalPlaylistsAdapter.PlaylistCursor playlistcursor = new com.sonos.acr.localaudiolibrary.adapters.LocalPlaylistsAdapter.PlaylistCursor(cursor);
            scilocalmusicbrowseiteminfo = playlistcursor.getCurrentItem();
            playlistcursor.close();
        } else
        {
            scilocalmusicbrowseiteminfo = null;
        }
        return scilocalmusicbrowseiteminfo;
    }

    public static String getPodcastAlbumArtwork(String s)
    {
        ContentResolver contentresolver = contentResolver;
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String as[] = new String[1];
        as[0] = "album_id";
        String as1[] = new String[1];
        as1[0] = s;
        Cursor cursor = contentresolver.query(uri, as, "album=? AND is_podcast != 0", as1, null);
        String s1 = null;
        if(cursor != null)
        {
            if(cursor.moveToFirst())
                s1 = getAlbumArtUri(cursor.getInt(0), -1L);
            cursor.close();
        }
        return s1;
    }

    public static SCILocalMusicBrowseItemInfo getPodcastAlbumItem(String s)
    {
        ContentResolver contentresolver = contentResolver;
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String as[] = new String[1];
        as[0] = s;
        Cursor cursor = contentresolver.query(uri, null, "is_podcast != 0 AND album LIKE ?", as, null);
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(cursor != null)
        {
            com.sonos.acr.localaudiolibrary.adapters.LocalPodcastAlbumsAdapter.PodcastAlbumCursor podcastalbumcursor = new com.sonos.acr.localaudiolibrary.adapters.LocalPodcastAlbumsAdapter.PodcastAlbumCursor(cursor);
            scilocalmusicbrowseiteminfo = podcastalbumcursor.getCurrentItem();
            podcastalbumcursor.close();
        } else
        {
            scilocalmusicbrowseiteminfo = null;
        }
        return scilocalmusicbrowseiteminfo;
    }

    public static SCILocalMusicBrowseItemInfo getTrackItem(long l)
    {
        Cursor cursor = contentResolver.query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, (new StringBuilder()).append("_id = ").append(l).toString(), null, null);
        if(cursor != null)
        {
            TrackCursor trackcursor = new TrackCursor(cursor, com.sonos.acr.localaudiolibrary.adapters.LocalMediaCursor.Subtitle.SUBTITLE_NONE, "_id");
            SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo = trackcursor.getCurrentItem();
            try
            {
                trackcursor.close();
            }
            catch(SecurityException securityexception) { }
        } else
        {
            scilocalmusicbrowseiteminfo = null;
        }
        return scilocalmusicbrowseiteminfo;
    }

    public static boolean hasAudioTracks(ContentResolver contentresolver)
    {
        boolean flag;
        try
        {
            Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String as[] = new String[1];
            as[0] = "_id";
            Cursor cursor = contentresolver.query(uri, as, "is_music != 0 OR is_podcast != 0", null, null);
            flag = false;
            if(cursor != null)
            {
                flag = cursor.moveToFirst();
                cursor.close();
            }
        }
        catch(SQLiteException sqliteexception)
        {
            flag = false;
        }
        return flag;
    }

    private static String lookupArtworkUri(long l, long l1)
    {
        String s = queryAlbumArt(l);
        if(s != null) goto _L2; else goto _L1
_L1:
        if(l <= -1L) goto _L4; else goto _L3
_L3:
        s = ContentUris.withAppendedId(ALBUMART_URI, l).toString();
_L2:
        return s;
_L4:
        if(l1 > -1L)
            s = (new StringBuilder()).append("content://media/external/audio/media/").append(l1).append("/albumart").toString();
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static String queryAlbumArt(long l)
    {
        String s = null;
        try
        {
            ContentResolver contentresolver = contentResolver;
            Uri uri = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
            String as[] = new String[2];
            as[0] = "_id";
            as[1] = "album_art";
            Cursor cursor = contentresolver.query(uri, as, (new StringBuilder()).append("_id=").append(l).toString(), null, null);
            if(cursor != null)
            {
                if(cursor.moveToFirst())
                {
                    s = cursor.getString(cursor.getColumnIndex("album_art"));
                    if(StringUtils.isNotEmptyOrNull(s))
                        s = (new StringBuilder()).append("file://").append(s).toString();
                }
                cursor.close();
            }
        }
        catch(SQLiteException sqliteexception)
        {
            SLog.e(LOG_TAG, "Failed to query Album Art");
        }
        return s;
    }

    private static final Uri ALBUMART_URI = Uri.parse("content://media/external/audio/albumart");
    public static final String ALBUMS_CONTAINER_ID = "albums";
    public static final String ALBUM_ID_PREFIX = "album";
    public static final String ARTISTS_CONTAINER_ID = "artists";
    public static final String ARTIST_ALBUMS_ID_PREFIX = "artist_albums";
    public static final String ARTIST_TRACKS_ID_PREFIX = "artist_tracks";
    public static final String COMPOSERS_CONTAINER_ID = "composers";
    public static final String DELIMITER = ":";
    public static final String GENRES_CONTAINER_ID = "genres";
    public static final String GENRE_ALBUMS_ID_PREFIX = "genre_albums";
    public static final String GENRE_TRACKS_ID_PREFIX = "genre_tracks";
    public static final int INVALID_ID = -1;
    public static final String IS_MUSIC_SELECTION = "is_music != 0";
    public static final String IS_PODCAST_SELECTION = "is_podcast != 0";
    public static final String LOG_TAG = com/sonos/acr/localaudiolibrary/LocalMediaUtils.getSimpleName();
    private static final int MAX_ENTRIES = 500;
    public static final String PLAYLISTS_CONTAINER_ID = "playlists";
    public static final String PLAYLIST_ID_PREFIX = "playlist";
    public static final String PODCASTS_CONTAINER_ID = "podcasts";
    public static final String PODCAST_ALBUM_ID_PREFIX = "podcast_album";
    public static final String ROOT_CONTAINER_ID = "root";
    public static final HashSet SUPPORTED_EXTENTIONS;
    public static final String TRACKS_CONTAINER_ID = "tracks";
    private static Map albumArtLinkCache = createLruCache(500);
    private static ContentResolver contentResolver = SonosApplication.getInstance().getContentResolver();
    private static Map trackArtLinkCache = createLruCache(500);

    static 
    {
        SUPPORTED_EXTENTIONS = new HashSet();
        SUPPORTED_EXTENTIONS.add("mp3");
        SUPPORTED_EXTENTIONS.add("m4a");
        SUPPORTED_EXTENTIONS.add("mp4");
        SUPPORTED_EXTENTIONS.add("ogg");
        SUPPORTED_EXTENTIONS.add("wav");
        SUPPORTED_EXTENTIONS.add("flac");
        SUPPORTED_EXTENTIONS.add("wma");
    }
}
