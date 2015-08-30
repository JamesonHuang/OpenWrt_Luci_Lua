// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.sonos.acr.browse.v2.pages.DataSourceListPageFragment;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.util.ImageViewAlbumArtController;
import com.sonos.sclib.SCIBrowseDataSource;

public class HeroHeaderController extends DataSourceListPageFragment
{

    public HeroHeaderController()
    {
    }

    protected AlbumArtSize getArtSize()
    {
        return AlbumArtSize.SIZE_LARGE_BROWSE;
    }

    protected int getLayoutId()
    {
        return 0x7f03000c;
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = super.onCreateThemedView(layoutinflater, viewgroup, bundle);
        albumArt = (ImageView)view.findViewById(0x7f0a003e);
        albumArtController = new ImageViewAlbumArtController(getArtSize(), albumArt);
        albumArtController.setDefaultResourceId(0x7f06001a);
        titleText = (TextView)view.findViewById(0x7f0a003f);
        titleText.setVisibility(8);
        subtitleText = (TextView)view.findViewById(0x7f0a0040);
        return view;
    }

    public void update()
    {
        com.sonos.sclib.SCIBrowseDataSource.PresentationArtworkType presentationartworktype = com.sonos.sclib.SCIBrowseDataSource.PresentationArtworkType.ARTWORK_DEFAULT;
        String s = browseDataSource.getPresentationArtworkURL(presentationartworktype, getArtSize().getPixelWidth());
        com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype = browseDataSource.getPresentationArtworkArtType(presentationartworktype);
        albumArtController.setImageURI(s, scalbumarttype);
        subtitleText.setText(browseDataSource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.TEXT_TYPE_SUMMARY));
    }

    protected ImageView albumArt;
    protected ImageViewAlbumArtController albumArtController;
    protected TextView subtitleText;
    protected TextView titleText;
}
