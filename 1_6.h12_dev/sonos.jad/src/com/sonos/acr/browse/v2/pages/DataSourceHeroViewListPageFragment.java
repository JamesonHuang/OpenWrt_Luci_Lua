// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.util.ImageViewAlbumArtController;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceAlbumViewListPageFragment

public class DataSourceHeroViewListPageFragment extends DataSourceAlbumViewListPageFragment
{

    public DataSourceHeroViewListPageFragment()
    {
    }

    public DataSourceHeroViewListPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceAlbumViewListPageFragment(scibrowsedatasource);
    }

    public String getAlbumViewSubTitle()
    {
        return browseDataSource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.TEXT_TYPE_SUMMARY);
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = onCreateThemedView(layoutinflater, viewgroup, bundle);
        if(isTablet)
        {
            Resources resources = getResources();
            if(resources != null)
            {
                albumViewTitlePortrait.setMaxLines(-2 + resources.getInteger(0x7f0b0006));
                albumViewSubtitlePortrait.setMaxLines(2 + resources.getInteger(0x7f0b0004));
                albumViewTitleLandscape.setMaxLines(-2 + resources.getInteger(0x7f0b0005));
                albumViewSubtitleLandscape.setMaxLines(2 + resources.getInteger(0x7f0b0003));
            }
        } else
        {
            ListView listview = (ListView)view.findViewById(0x7f0a0031);
            phoneHeader = getLayoutInflater(bundle).inflate(0x7f030024, null);
            listview.addHeaderView(phoneHeader);
            albumArt = (ImageView)view.findViewById(0x7f0a003e);
            albumArtController = new ImageViewAlbumArtController(getArtSize(), albumArt);
            albumArtController.setDefaultResourceId(0x7f06001b);
            summaryText = (TextView)view.findViewById(0x7f0a0041);
        }
        return view;
    }

    protected void updateAlbumViewHeader()
    {
        if(!isTablet)
            if(browseDataSource == null || !browseDataSource.isValid())
            {
                summaryText.setVisibility(8);
            } else
            {
                summaryText.setVisibility(0);
                summaryText.setText(getAlbumViewSubTitle());
                com.sonos.sclib.SCIBrowseDataSource.PresentationArtworkType presentationartworktype = com.sonos.sclib.SCIBrowseDataSource.PresentationArtworkType.ARTWORK_DEFAULT;
                String s = browseDataSource.getPresentationArtworkURL(presentationartworktype, getArtSize().getPixelWidth());
                com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype = browseDataSource.getPresentationArtworkArtType(presentationartworktype);
                albumArtController.setImageURI(s, scalbumarttype);
            }
        updateAlbumViewHeader();
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
        if(isTablet)
            browseitemcell.findViewById(0x7f0a0045).setVisibility(8);
        else
            browseitemcell.findViewById(0x7f0a0045).setVisibility(4);
    }

    protected ImageView albumArt;
    protected ImageViewAlbumArtController albumArtController;
    protected View phoneHeader;
    protected TextView summaryText;
}
