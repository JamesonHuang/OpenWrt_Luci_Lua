// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary;

import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.sclibConstants;

// Referenced classes of package com.sonos.acr.localaudiolibrary:
//            LocalMusicBrowseItem, LocalMediaUtils

public class LocalTrackBrowseItem extends LocalMusicBrowseItem
{

    public LocalTrackBrowseItem()
    {
        albumId = -1L;
        trackId = -1L;
    }

    public String getArtMimeType()
    {
        if(aaMimeType == null)
            aaMimeType = LocalMediaUtils.getAlbumArtMimeType(getArtUri());
        return super.getArtMimeType();
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getArtType()
    {
        if(aaType == com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE)
            if(!isPlayable())
                aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_RESTRICTED;
            else
            if(StringUtils.isNotEmptyOrNull(getArtUri()))
                aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_LOCAL_URL;
            else
                aaType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_URL;
        return super.getArtType();
    }

    public String getArtUri()
    {
        if(aaUri == null)
            if(!isPlayable())
                aaUri = sclibConstants.SCALBUMART_RESTRICTED;
            else
                aaUri = LocalMediaUtils.getAlbumArtUri(albumId, trackId);
        return super.getArtUri();
    }

    public long albumId;
    public long trackId;
}
