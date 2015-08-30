// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary.adapters;

import android.content.Context;
import com.sonos.acr.localaudiolibrary.LocalMusicBrowseItem;
import com.sonos.sclib.*;
import java.util.ArrayList;

public class LocalMediaRootAdapter extends SCILocalMediaCollectionSwigBase
{
    private static class RootCollectionItem extends LocalMusicBrowseItem
    {

        private RootCollectionItem(String s, String s1, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype, String s2, boolean flag)
        {
            id = s;
            title = s1;
            container = true;
            aaType = scalbumarttype;
            aaUri = s2;
            if(flag)
                resUri = s;
        }

    }


    public LocalMediaRootAdapter(Context context1)
    {
        context = context1;
        localMusicBrowseItems.add(createRootItem(context1, "artists"));
        localMusicBrowseItems.add(createRootItem(context1, "albums"));
        localMusicBrowseItems.add(createRootItem(context1, "genres"));
        localMusicBrowseItems.add(createRootItem(context1, "tracks"));
        localMusicBrowseItems.add(createRootItem(context1, "playlists"));
        localMusicBrowseItems.add(createRootItem(context1, "podcasts"));
    }

    public static LocalMusicBrowseItem createRootItem(Context context1, String s)
    {
        int i = -1;
        com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE;
        String s1 = "";
        boolean flag = false;
        RootCollectionItem rootcollectionitem;
        if("root".equals(s))
        {
            i = 0x7f0c002f;
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            s1 = "ACR_LOCAL_LIBRARY";
        } else
        if("artists".equals(s))
        {
            i = 0x7f0c002a;
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            s1 = sclibConstants.SCALBUMART_LIBRARY_ARTISTS;
        } else
        if("albums".equals(s))
        {
            i = 0x7f0c0029;
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            s1 = sclibConstants.SCALBUMART_LIBRARY_ALBUMS;
        } else
        if("composers".equals(s))
        {
            i = 0x7f0c002b;
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            s1 = sclibConstants.SCALBUMART_LIBRARY_COMPOSERS;
        } else
        if("genres".equals(s))
        {
            i = 0x7f0c002e;
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            s1 = sclibConstants.SCALBUMART_LIBRARY_GENRES;
        } else
        if("tracks".equals(s))
        {
            i = 0x7f0c0033;
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            s1 = sclibConstants.SCALBUMART_LIBRARY_TRACKS;
            flag = true;
        } else
        if("playlists".equals(s))
        {
            i = 0x7f0c0031;
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            s1 = sclibConstants.SCALBUMART_LIBRARY_PLAYLISTS;
        } else
        if("podcasts".equals(s))
        {
            i = 0x7f0c0032;
            scalbumarttype = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL;
            s1 = sclibConstants.SCALBUMART_LIBRARY_PODCASTS;
        }
        if(i > 0)
            rootcollectionitem = new RootCollectionItem(s, context1.getString(i), scalbumarttype, s1, flag);
        else
            rootcollectionitem = null;
        return rootcollectionitem;
    }

    public com.sonos.sclib.SCILocalMediaCollection.AllNodeType getAllNodeType()
    {
        return com.sonos.sclib.SCILocalMediaCollection.AllNodeType.LMBI_ALL_NODE_NONE;
    }

    public long getCount()
    {
        return (long)localMusicBrowseItems.size();
    }

    public SCILocalMusicBrowseItemInfo getItemAt(long l)
    {
        return (SCILocalMusicBrowseItemInfo)localMusicBrowseItems.get((int)l);
    }

    public String getPowerscrollCSV()
    {
        return "";
    }

    public com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType getPresentationType()
    {
        return com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_LIST;
    }

    public void registerMediaCollectionListener(SCILocalMediaCollectionListener scilocalmediacollectionlistener)
    {
    }

    public boolean showTrackNumber()
    {
        return false;
    }

    public static final String AA_URL_ROOT_LOCAL_LIBRARY = "ACR_LOCAL_LIBRARY";
    Context context;
    final ArrayList localMusicBrowseItems = new ArrayList();
}
