// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.*;
import com.sonos.acr.browse.indexers.BrowseSection;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.util.ImageViewAlbumArtController;

public class BrowseSectionHeader extends FrameLayout
{

    public BrowseSectionHeader(Context context)
    {
        super(context);
        LayoutInflater.from(context).inflate(getLayoutId(), this, true);
        sectionTitle = (TextView)findViewById(0x7f0a00c9);
        albumArtImageView = (ImageView)findViewById(0x7f0a002c);
        if(albumArtImageView != null)
        {
            imageViewAlbumArtController = new ImageViewAlbumArtController(AlbumArtSize.SIZE_SEARCH, albumArtImageView);
            imageViewAlbumArtController.setDefaultResourceId(0x7f06001a);
        }
    }

    public BrowseSection getBrowseSection()
    {
        return sectionInfo;
    }

    protected int getLayoutId()
    {
        return 0x7f030057;
    }

    public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        super.setOnClickListener(onclicklistener);
    }

    public void updateSectionInfo(BrowseSection browsesection)
    {
        sectionInfo = browsesection;
        sectionTitle.setText(browsesection.getTitle());
        if(albumArtImageView != null)
            imageViewAlbumArtController.setImageURI(browsesection.getArtUrl(), browsesection.getArtType());
    }

    private static final String LOG_TAG = com/sonos/acr/browse/BrowseSectionHeader.getSimpleName();
    protected ImageView albumArtImageView;
    protected ImageViewAlbumArtController imageViewAlbumArtController;
    BrowseSection sectionInfo;
    protected TextView sectionTitle;

}
