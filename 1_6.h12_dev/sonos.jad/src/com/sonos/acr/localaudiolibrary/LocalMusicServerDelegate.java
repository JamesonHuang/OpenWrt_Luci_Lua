// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.localaudiolibrary.adapters.LocalAlbumsAdapter;
import com.sonos.acr.localaudiolibrary.adapters.LocalArtistsAdapter;
import com.sonos.acr.localaudiolibrary.adapters.LocalGenreAdapter;
import com.sonos.acr.localaudiolibrary.adapters.LocalMediaCursor;
import com.sonos.acr.localaudiolibrary.adapters.LocalMediaCursorAdapter;
import com.sonos.acr.localaudiolibrary.adapters.LocalMediaRootAdapter;
import com.sonos.acr.localaudiolibrary.adapters.LocalPlaylistsAdapter;
import com.sonos.acr.localaudiolibrary.adapters.LocalPodcastAlbumsAdapter;
import com.sonos.acr.localaudiolibrary.adapters.LocalTracksAdapter;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.io.*;
import java.net.URLDecoder;

// Referenced classes of package com.sonos.acr.localaudiolibrary:
//            LocalMediaUtils, LocalMusicService, LocalMusicSearchableDelegate, LocalTrackBrowseItem

public class LocalMusicServerDelegate extends SCIMusicServerDelegateSwigBase
{
    public static class EmptyCursorAdapter extends LocalMediaCursorAdapter
    {

        protected LocalMediaCursor createMediaCursor(ContentResolver contentresolver)
        {
            return new LocalMediaCursor(null, com.sonos.acr.localaudiolibrary.adapters.LocalMediaCursor.Subtitle.SUBTITLE_NONE) {

                protected SCILocalMusicBrowseItemInfo createItemFromCurrent()
                {
                    return null;
                }

                final EmptyCursorAdapter this$0;

                
                {
                    this$0 = EmptyCursorAdapter.this;
                    super(cursor, subtitle);
                }
            }
;
        }

        public EmptyCursorAdapter(Context context1)
        {
            super(context1);
        }
    }

    public static class LocalMusicServerBrowseDelegate extends SCIMusicServerBrowseDelegateSwigBase
    {

        private SCILocalMusicBrowseItemInfo createLocalMusicInfoForId(String s)
        {
            if(!StringUtils.isNotEmptyOrNull(s)) goto _L2; else goto _L1
_L1:
            if(!StringUtils.isLong(s)) goto _L4; else goto _L3
_L3:
            Object obj = LocalMediaUtils.getTrackItem(Long.parseLong(s));
_L6:
            return ((SCILocalMusicBrowseItemInfo) (obj));
_L4:
            if(s.contains(":"))
            {
                int i = s.indexOf(":");
                String s1 = s.substring(0, i);
                String s2 = s.substring(i + 1, s.length());
                if("genre_albums".equals(s1) || "genre_tracks".equals(s1))
                {
                    obj = new com.sonos.acr.localaudiolibrary.adapters.LocalGenreAdapter.GenreBrowseItem(s2, s1);
                    continue; /* Loop/switch isn't completed */
                }
                if("podcast_album".equals(s1))
                {
                    obj = new com.sonos.acr.localaudiolibrary.adapters.LocalPodcastAlbumsAdapter.PodcastAlbumBrowseItem(s2, "");
                    continue; /* Loop/switch isn't completed */
                }
                if(StringUtils.isLong(s2))
                {
                    if("album".equals(s1))
                        obj = LocalMediaUtils.getAlbumItem(Long.parseLong(s2));
                    else
                    if("playlist".equals(s1))
                        obj = LocalMediaUtils.getPlaylistItem(Long.parseLong(s2));
                    else
                    if("artist_albums".equals(s1) || "artist_tracks".equals(s1))
                        obj = LocalMediaUtils.getArtistItem(s1, Long.parseLong(s2));
                    else
                        obj = LocalMediaUtils.getTrackItem(Long.parseLong(s2));
                    continue; /* Loop/switch isn't completed */
                }
            } else
            {
                obj = LocalMediaRootAdapter.createRootItem(context, s);
                if(obj != null)
                    continue; /* Loop/switch isn't completed */
            }
_L2:
            obj = null;
            if(true) goto _L6; else goto _L5
_L5:
        }

        private String getSearchTerm(String s)
        {
            String s1;
            s1 = null;
            if(s == null || s.charAt(0) != '"' || s.charAt(-1 + s.length()) != '"')
                break MISSING_BLOCK_LABEL_73;
            String s2 = URLDecoder.decode(s.substring(1, -1 + s.length()), "UTF-8");
            s1 = s2;
_L1:
            s1 = s1.replaceAll(" ", "%");
_L2:
            return s1;
            UnsupportedEncodingException unsupportedencodingexception;
            unsupportedencodingexception;
            unsupportedencodingexception.printStackTrace();
              goto _L1
            SLog.w(LOG_TAG, "Invalid Search Term!");
              goto _L2
        }

        public SCILocalMediaCollection getLocalMediaCollectionForId(String s)
        {
            if(!StringUtils.isNotEmptyOrNull(s)) goto _L2; else goto _L1
_L1:
            if(!"root".equals(s)) goto _L4; else goto _L3
_L3:
            Object obj = new LocalMediaRootAdapter(context);
_L6:
            return ((SCILocalMediaCollection) (obj));
_L4:
            if("playlists".equals(s))
            {
                obj = new LocalPlaylistsAdapter(context);
                continue; /* Loop/switch isn't completed */
            }
            if("tracks".equals(s))
            {
                obj = new LocalTracksAdapter(context, s);
                continue; /* Loop/switch isn't completed */
            }
            if("artists".equals(s))
            {
                obj = new LocalArtistsAdapter(context);
                continue; /* Loop/switch isn't completed */
            }
            if("albums".equals(s))
            {
                obj = new LocalAlbumsAdapter(context);
                continue; /* Loop/switch isn't completed */
            }
            if("composers".equals(s))
            {
                obj = new LocalTracksAdapter(context, s);
                continue; /* Loop/switch isn't completed */
            }
            if("genres".equals(s))
            {
                obj = new LocalGenreAdapter(context);
                continue; /* Loop/switch isn't completed */
            }
            if("podcasts".equals(s))
            {
                obj = new LocalPodcastAlbumsAdapter(context, s, null);
                continue; /* Loop/switch isn't completed */
            }
            if(StringUtils.isLong(s))
            {
                obj = new LocalTracksAdapter(context, "", s);
                continue; /* Loop/switch isn't completed */
            }
            if(s.contains(":"))
            {
                int i = s.indexOf(":");
                String s1 = s.substring(0, i);
                String s2 = s.substring(i + 1, s.length());
                String s3 = getSearchTerm(s2);
                if(s3 == null)
                {
                    if("artist_albums".equals(s1) || "genre_albums".equals(s1))
                        obj = new LocalAlbumsAdapter(context, s1, s2);
                    else
                        obj = new LocalTracksAdapter(context, s1, s2);
                    continue; /* Loop/switch isn't completed */
                }
                if(!"".equals(s3))
                {
                    if("tracks".equals(s1))
                    {
                        obj = new LocalTracksAdapter(context, s1, s2, s3);
                        continue; /* Loop/switch isn't completed */
                    }
                    if("artists".equals(s1))
                    {
                        obj = new LocalArtistsAdapter(context, s1, s2, s3);
                        continue; /* Loop/switch isn't completed */
                    }
                    if("albums".equals(s1))
                    {
                        obj = new LocalAlbumsAdapter(context, s1, s2, s3);
                        continue; /* Loop/switch isn't completed */
                    }
                    if("genres".equals(s1))
                    {
                        obj = new LocalGenreAdapter(context, s1, s2, s3);
                        continue; /* Loop/switch isn't completed */
                    }
                    if("podcasts".equals(s1))
                    {
                        obj = new LocalPodcastAlbumsAdapter(context, s1, s2, s3);
                        continue; /* Loop/switch isn't completed */
                    }
                }
                obj = new EmptyCursorAdapter(context);
                continue; /* Loop/switch isn't completed */
            }
_L2:
            obj = new LocalMediaRootAdapter(context);
            if(true) goto _L6; else goto _L5
_L5:
        }

        public SCILocalMusicBrowseItemInfo getLocalMusicItemInfoForId(String s)
        {
            Object obj = createLocalMusicInfoForId(s);
            if(obj == null)
                obj = new LocalTrackBrowseItem();
            return ((SCILocalMusicBrowseItemInfo) (obj));
        }

        public SCILocalMusicSearchableDelegate getLocalMusicSearchableDelegate()
        {
            return searchableDelegate;
        }

        public String getObjectIdForAssociationType(String s, com.sonos.sclib.SCIMusicServerBrowseDelegate.AssociationType associationtype)
        {
            String s1;
            String s2;
            String s3;
            String s4;
            s1 = null;
            s2 = null;
            SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo1;
            if(StringUtils.isLong(s))
                s2 = s;
            else
            if(s.contains(":"))
            {
                int i = s.indexOf(":");
                s1 = s.substring(0, i);
                s2 = s.substring(i + 1, s.length());
            }
            if(s2 == null) goto _L2; else goto _L1
_L1:
            if(associationtype != com.sonos.sclib.SCIMusicServerBrowseDelegate.AssociationType.ARTIST_ALBUMS && associationtype != com.sonos.sclib.SCIMusicServerBrowseDelegate.AssociationType.ARTIST_TRACKS) goto _L4; else goto _L3
_L3:
            if(associationtype == com.sonos.sclib.SCIMusicServerBrowseDelegate.AssociationType.ARTIST_ALBUMS)
                s4 = "artist_albums";
            else
                s4 = "artist_tracks";
            if(s1 != null) goto _L6; else goto _L5
_L5:
            scilocalmusicbrowseiteminfo1 = LocalMediaUtils.getArtistItem(s4, LocalMediaUtils.getTrackItem(Long.parseLong(s2)).getArtist());
            if(scilocalmusicbrowseiteminfo1 == null) goto _L2; else goto _L7
_L7:
            s3 = scilocalmusicbrowseiteminfo1.getId();
_L9:
            return s3;
_L6:
            if("album".equals(s1))
            {
                SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo = LocalMediaUtils.getArtistItem(s4, LocalMediaUtils.getAlbumItem(Long.parseLong(s2)).getArtist());
                if(scilocalmusicbrowseiteminfo != null)
                {
                    s3 = scilocalmusicbrowseiteminfo.getId();
                    continue; /* Loop/switch isn't completed */
                }
            } else
            {
                s3 = LocalMediaUtils.createItemId(s4, s2);
                continue; /* Loop/switch isn't completed */
            }
              goto _L2
_L4:
            if(associationtype == com.sonos.sclib.SCIMusicServerBrowseDelegate.AssociationType.ALBUM_TRACKS)
                if(s1 == null)
                {
                    SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo2 = LocalMediaUtils.getTrackItem(Long.parseLong(s2));
                    if(scilocalmusicbrowseiteminfo2 instanceof LocalTrackBrowseItem)
                        if(scilocalmusicbrowseiteminfo2.getItemType() == com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType.TRACK)
                        {
                            SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo4 = LocalMediaUtils.getAlbumItem(((LocalTrackBrowseItem)scilocalmusicbrowseiteminfo2).albumId);
                            if(scilocalmusicbrowseiteminfo4 != null)
                            {
                                s3 = scilocalmusicbrowseiteminfo4.getId();
                                continue; /* Loop/switch isn't completed */
                            }
                        } else
                        if(scilocalmusicbrowseiteminfo2.getItemType() == com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType.PODCAST)
                        {
                            SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo3 = LocalMediaUtils.getPodcastAlbumItem(scilocalmusicbrowseiteminfo2.getAlbum());
                            if(scilocalmusicbrowseiteminfo3 != null)
                            {
                                s3 = scilocalmusicbrowseiteminfo3.getId();
                                continue; /* Loop/switch isn't completed */
                            }
                        }
                } else
                if("album".equals(s1))
                {
                    s3 = LocalMediaUtils.createItemId("album", s2);
                    continue; /* Loop/switch isn't completed */
                }
_L2:
            s3 = "";
            if(true) goto _L9; else goto _L8
_L8:
        }

        public SCILocalMusicBrowseItemInfo getRootItem()
        {
            return LocalMediaRootAdapter.createRootItem(context, "root");
        }

        public static final String LOG_TAG = com/sonos/acr/localaudiolibrary/LocalMusicServerDelegate$LocalMusicServerBrowseDelegate.getSimpleName();
        public ContentResolver contentResolver;
        Context context;
        LocalMusicSearchableDelegate searchableDelegate;


        public LocalMusicServerBrowseDelegate(Context context1)
        {
            context = context1;
            contentResolver = context1.getContentResolver();
            searchableDelegate = new LocalMusicSearchableDelegate(context1);
        }
    }


    public LocalMusicServerDelegate(Context context1)
    {
        context = context1;
        contentResolver = context1.getContentResolver();
        browseDelegate = new LocalMusicServerBrowseDelegate(context1);
    }

    public void fillImageBytes(SCIData scidata, String s)
    {
        if(!StringUtils.isLong(s)) goto _L2; else goto _L1
_L1:
        String s1;
        byte abyte0[];
        ParcelFileDescriptor parcelfiledescriptor;
        s1 = LocalMediaUtils.getAlbumArtUri(-1L, Long.parseLong(s));
        abyte0 = null;
        if(!StringUtils.isNotEmptyOrNull(s1))
            break MISSING_BLOCK_LABEL_84;
        parcelfiledescriptor = null;
        byte abyte1[];
        parcelfiledescriptor = SonosApplication.getInstance().getContentResolver().openFileDescriptor(Uri.parse(s1), "r");
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_74;
        abyte1 = FileUtils.readBytes(new FileInputStream(parcelfiledescriptor.getFileDescriptor()));
        abyte0 = abyte1;
        if(parcelfiledescriptor != null)
            try
            {
                parcelfiledescriptor.close();
            }
            catch(IOException ioexception3)
            {
                ioexception3.printStackTrace();
            }
_L4:
        if(abyte0 != null)
            scidata.setImageBytes(abyte0, abyte0.length);
_L2:
        return;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        if(parcelfiledescriptor != null)
            try
            {
                parcelfiledescriptor.close();
            }
            catch(IOException ioexception2)
            {
                ioexception2.printStackTrace();
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
                ioexception1.printStackTrace();
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
                ioexception.printStackTrace();
            }
        throw exception;
    }

    public SCIMusicServerBrowseDelegate getMusicServerBrowseDelegate()
    {
        return browseDelegate;
    }

    public String getPlayableTrackId(String s)
    {
        String s1 = LocalMediaUtils.getFilePathForTrack(s);
        if(StringUtils.isNotEmptyOrNull(s1))
            LocalMusicService.onLocalSongPlayRequest(context);
        return s1;
    }

    public void onBeginStreaming()
    {
        LocalMusicService.onBeginStreaming(context);
    }

    public void onEndStreaming()
    {
        LocalMusicService.onEndStreaming(context);
    }

    public static final String LOG_TAG = com/sonos/acr/localaudiolibrary/LocalMusicServerDelegate.getSimpleName();
    LocalMusicServerBrowseDelegate browseDelegate;
    public ContentResolver contentResolver;
    Context context;

}
