// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.indexers;

import com.sonos.acr.util.StringUtils;

public class BrowseSection
{

    public BrowseSection(int i, String s)
    {
        this(i, s, null, null, null, null);
    }

    public BrowseSection(int i, String s, String s1, String s2, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype, String s3)
    {
        startPosition = i;
        title = s;
        description = s1;
        artUrl = s2;
        artType = scalbumarttype;
        browseSCUri = s3;
    }

    public boolean canPush()
    {
        return StringUtils.isNotEmptyOrNull(getBrowseSCUri());
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getArtType()
    {
        return artType;
    }

    public String getArtUrl()
    {
        return artUrl;
    }

    public String getBrowseSCUri()
    {
        return browseSCUri;
    }

    public String getDescription()
    {
        return description;
    }

    public int getStartPosition()
    {
        return startPosition;
    }

    public String getTitle()
    {
        return title;
    }

    public String toString()
    {
        return title;
    }

    final com.sonos.sclib.SCIBrowseItem.SCAlbumArtType artType;
    final String artUrl;
    final String browseSCUri;
    final String description;
    final int startPosition;
    final String title;
}
