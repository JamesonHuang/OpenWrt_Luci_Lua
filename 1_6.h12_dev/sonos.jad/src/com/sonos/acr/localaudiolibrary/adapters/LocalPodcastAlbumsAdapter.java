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

public class LocalPodcastAlbumsAdapter extends LocalMediaCursorAdapter
{
    protected static abstract class PodcastCursor extends LocalMediaCursor
    {

        protected PodcastCursor(Cursor cursor, LocalMediaCursor.Subtitle subtitle)
        {
            super(cursor, subtitle);
        }
    }

    public static class PodcastAlbumBrowseItem extends LocalMusicBrowseItem
    {

        public String getArtMimeType()
        {
            if(aaMimeType == null)
                aaMimeType = LocalMediaUtils.getAlbumArtMimeType(getArtUri());
            return super.getArtMimeType();
        }

        public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getArtType()
        {
            if(aaType == com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE)
                if(StringUtils.isNotEmptyOrNull(getArtUri()))
                    aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL_URL;
                else
                    aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL;
            return super.getArtType();
        }

        public String getArtUri()
        {
            if(aaUri == null)
                aaUri = LocalMediaUtils.getPodcastAlbumArtwork(album);
            return super.getArtUri();
        }

        public PodcastAlbumBrowseItem(String s, String s1)
        {
            title = s;
            album = s;
            artist = s1;
            container = true;
            id = LocalMediaUtils.createItemId("podcast_album", s);
            resUri = id;
            itemType = com.sonos.sclib.SCILocalMusicBrowseItemInfo.ItemType.PODCAST;
        }
    }

    public static class PodcastAlbumCursor extends PodcastCursor
    {

        public SCILocalMusicBrowseItemInfo createItemFromCurrent()
        {
            return setSubtitle(new PodcastAlbumBrowseItem(getString(nameColumn), getString(artistColumn)));
        }

        protected void loadColumnIndexes()
        {
            nameColumn = getColumnIndex("album");
            artistColumn = getColumnIndex("artist");
        }

        int artistColumn;
        int nameColumn;

        public PodcastAlbumCursor(Cursor cursor)
        {
            super(cursor, LocalMediaCursor.Subtitle.SUBTITLE_ARTIST);
        }
    }


    public LocalPodcastAlbumsAdapter(Context context, String s, String s1)
    {
        super(context, s, s1);
    }

    public LocalPodcastAlbumsAdapter(Context context, String s, String s1, String s2)
    {
        super(context, s, s1, s2);
    }

    protected volatile LocalMediaCursor createMediaCursor(ContentResolver contentresolver)
    {
        return createMediaCursor(contentresolver);
    }

    protected PodcastCursor createMediaCursor(ContentResolver contentresolver)
    {
        PodcastAlbumCursor podcastalbumcursor = null;
        if("podcasts".equals(containerType))
        {
            String as[] = new String[2];
            as[0] = " DISTINCT album";
            as[1] = "artist";
            String s = "is_podcast != 0";
            String as1[] = null;
            if(StringUtils.isNotEmptyOrNull(searchTerm))
            {
                s = (new StringBuilder()).append(s).append(" AND (artist LIKE ?  OR album LIKE ?)").toString();
                as1 = new String[1];
                as1[0] = (new StringBuilder()).append("%").append(searchTerm).append("%").toString();
            }
            Cursor cursor = contentresolver.query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, as, s, as1, null);
            if(cursor != null)
                podcastalbumcursor = new PodcastAlbumCursor(cursor);
        }
        return podcastalbumcursor;
    }
}
