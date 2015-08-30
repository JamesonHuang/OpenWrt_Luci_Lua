// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.downloadmanager;

import android.content.Context;
import android.content.res.Resources;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.sclib.sclibConstants;
import java.util.HashMap;

// Referenced classes of package com.sonos.acr.downloadmanager:
//            BitmapDownloadOperation

public class BitmapLocalDownloadOperation extends BitmapDownloadOperation
{

    public BitmapLocalDownloadOperation(Context context, String s, AlbumArtSize albumartsize)
    {
        super(context, s, albumartsize);
    }

    private int resourceIDForUri(String s, AlbumArtSize albumartsize)
    {
        int i;
        if(smallIconMap.containsKey(s))
            i = ((Integer)smallIconMap.get(s)).intValue();
        else
        if(s.startsWith(sclibConstants.SC_DEVICE_ICONURI_PREFIX))
        {
            i = -1;
        } else
        {
            i = getContext().getResources().getIdentifier(s, "raw", getContext().getPackageName());
            if(i == 0)
                i = 0x7f06001a;
            else
                smallIconMap.put(s, Integer.valueOf(i));
        }
        return i;
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getDownloadType()
    {
        return com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
    }

    public void performDownload()
    {
        AlbumArtSize albumartsize = getAlbumArtSize();
        int i = resourceIDForUri(getInternalUri(), albumartsize);
        if(i > 0)
            onBitmapDownloadSucceeded(null, i);
        else
            onBitmapDownloadFailed();
    }

    public boolean wasCacheHit()
    {
        return true;
    }

    public static HashMap smallIconMap;

    static 
    {
        smallIconMap = new HashMap();
        smallIconMap.put(sclibConstants.SCALBUMART_ALARMS, Integer.valueOf(0x7f06000c));
        smallIconMap.put(sclibConstants.SCALBUMART_ALL_NODE, Integer.valueOf(0x7f060000));
        smallIconMap.put(sclibConstants.SCALBUMART_BROADCAST, Integer.valueOf(0x7f060029));
        smallIconMap.put(sclibConstants.SCALBUMART_TRACK, Integer.valueOf(0x7f06002e));
        smallIconMap.put(sclibConstants.SCALBUMART_FOLDER, Integer.valueOf(0x7f060014));
        smallIconMap.put(sclibConstants.SCALBUMART_MULTITRACK, Integer.valueOf(0x7f06001d));
        smallIconMap.put(sclibConstants.SCALBUMART_DOCK, Integer.valueOf(0x7f060012));
        smallIconMap.put(sclibConstants.SCALBUMART_LINEIN, Integer.valueOf(0x7f060017));
        smallIconMap.put(sclibConstants.SCALBUMART_PLAYLIST_ITEM, Integer.valueOf(0x7f060026));
        smallIconMap.put(sclibConstants.SCALBUMART_PLAYLIST_ITEM_DISABLED, Integer.valueOf(0x7f060025));
        smallIconMap.put(sclibConstants.SCALBUMART_PLUS, Integer.valueOf(0x7f06000b));
        smallIconMap.put(sclibConstants.SCALBUMART_FAVORITES, Integer.valueOf(0x7f060013));
        smallIconMap.put(sclibConstants.SCALBUMART_LIBRARY, Integer.valueOf(0x7f060016));
        smallIconMap.put(sclibConstants.SCALBUMART_DEFAULT, Integer.valueOf(0x7f06001b));
        smallIconMap.put(sclibConstants.SCALBUMART_PLAYLIST, Integer.valueOf(0x7f06002d));
        smallIconMap.put(sclibConstants.SCALBUMART_RADIO, Integer.valueOf(0x7f060028));
        smallIconMap.put(sclibConstants.SCALBUMART_SETTINGS, Integer.valueOf(0x7f06002c));
        smallIconMap.put(sclibConstants.SCALBUMART_SOUNDLAB, Integer.valueOf(0x7f06002b));
        smallIconMap.put(sclibConstants.SCALBUMART_UPDATE, Integer.valueOf(0x7f060031));
        smallIconMap.put(sclibConstants.SCALBUMART_TVAUDIO, Integer.valueOf(0x7f06002f));
        smallIconMap.put(sclibConstants.SCALBUMART_WMSERVER, Integer.valueOf(0x7f060032));
        smallIconMap.put(sclibConstants.SCALBUMART_LIBRARY_ALBUMS, Integer.valueOf(0x7f06000d));
        smallIconMap.put(sclibConstants.SCALBUMART_LIBRARY_ARTISTS, Integer.valueOf(0x7f06000f));
        smallIconMap.put(sclibConstants.SCALBUMART_LIBRARY_COMPILATIONS, Integer.valueOf(0x7f060010));
        smallIconMap.put(sclibConstants.SCALBUMART_LIBRARY_COMPOSERS, Integer.valueOf(0x7f060011));
        smallIconMap.put(sclibConstants.SCALBUMART_LIBRARY_GENRES, Integer.valueOf(0x7f060015));
        smallIconMap.put(sclibConstants.SCALBUMART_LIBRARY_PODCASTS, Integer.valueOf(0x7f060027));
        smallIconMap.put(sclibConstants.SCALBUMART_LIBRARY_PLAYLISTS, Integer.valueOf(0x7f060026));
        smallIconMap.put(sclibConstants.SCALBUMART_LIBRARY_TRACKS, Integer.valueOf(0x7f06002e));
        smallIconMap.put(sclibConstants.SCALBUMART_EMPTY_FAVORITES, Integer.valueOf(0x7f060002));
        smallIconMap.put(sclibConstants.SCALBUMART_EMPTY_PLAYLISTS, Integer.valueOf(0x7f060004));
        smallIconMap.put(sclibConstants.SCALBUMART_EMPTY_LINEIN, Integer.valueOf(0x7f060003));
        smallIconMap.put(sclibConstants.SCALBUMART_RESTRICTED, Integer.valueOf(0x7f06002a));
        smallIconMap.put(sclibConstants.SCALBUMART_ALLMUSIC, Integer.valueOf(0x7f06001d));
        smallIconMap.put(sclibConstants.SCALBUMART_NONE, Integer.valueOf(0x7f060018));
        smallIconMap.put(sclibConstants.SCALBUMART_PANDORATHUMBSUP, Integer.valueOf(0x7f060009));
        smallIconMap.put(sclibConstants.SCALBUMART_PANDORATHUMBSDOWN, Integer.valueOf(0x7f060007));
        smallIconMap.put(sclibConstants.SCALBUMART_PANDORATHUMBSUP_EMPTY, Integer.valueOf(0x7f06000a));
        smallIconMap.put(sclibConstants.SCALBUMART_PANDORATHUMBSDOWN_EMPTY, Integer.valueOf(0x7f060008));
        smallIconMap.put(sclibConstants.SCALBUMART_LASTFMLOVE, Integer.valueOf(0x7f060005));
        smallIconMap.put(sclibConstants.SCALBUMART_LASTFMBAN, Integer.valueOf(0x7f060001));
        smallIconMap.put("ACR_LOCAL_LIBRARY", Integer.valueOf(0x7f06000e));
    }
}
