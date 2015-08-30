// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions;

import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.sclib.SCINowPlayingSource;

// Referenced classes of package com.sonos.acr.browse.v2.actions:
//            DataSourceActionMenuData

public class NowPlayingActionData extends DataSourceActionMenuData
{

    public NowPlayingActionData(NowPlaying nowplaying)
    {
        dataSource = nowplaying.sciNowPlayingSrc.getMoreMenuDataSource();
        primaryText = nowplaying.getDoubleLineMetaData()[0];
        secondaryText = nowplaying.getDoubleLineMetaData()[1];
        aaUri = nowplaying.getAlbumArtURI(AlbumArtSize.SIZE_BROWSE);
    }
}
