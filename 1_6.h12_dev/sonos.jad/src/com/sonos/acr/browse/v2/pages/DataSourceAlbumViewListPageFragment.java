// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.util.AlbumArtSize;
import com.sonos.acr.util.ImageViewAlbumArtController;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceListPageFragment

public class DataSourceAlbumViewListPageFragment extends DataSourceListPageFragment
{

    public DataSourceAlbumViewListPageFragment()
    {
    }

    public DataSourceAlbumViewListPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceListPageFragment(scibrowsedatasource);
    }

    public String getAlbumViewSubTitle()
    {
        return browseDataSource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.SUBTITLE_DEFAULT);
    }

    public String getAlbumViewTitle()
    {
        return browseDataSource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.TITLE_DEFAULT);
    }

    protected int getLayoutId()
    {
        int i;
        if((0xf & SonosApplication.getInstance().getResources().getConfiguration().screenLayout) >= 3)
        {
            isTablet = true;
            i = 0x7f03004c;
        } else
        {
            i = getLayoutId();
        }
        return i;
    }

    public volatile CharSequence getTitle()
    {
        return getTitle();
    }

    public String getTitle()
    {
        String s;
        if(isTablet)
            s = null;
        else
            s = getTitle();
        return s;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        onConfigurationChanged(configuration);
        updateLayoutForConfiguration(configuration);
        updateHeaderBar();
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = onCreateThemedView(layoutinflater, viewgroup, bundle);
        browseList = (ListView)view.findViewById(0x7f0a0031);
        if(isTablet)
        {
            landscapeMetadataGroup = view.findViewById(0x7f0a011d);
            portraitAlbumHeader = view.findViewById(0x7f0a0117);
            albumViewTitlePortrait = (TextView)view.findViewById(0x7f0a011a);
            albumViewSubtitlePortrait = (TextView)view.findViewById(0x7f0a011b);
            albumViewTitleLandscape = (TextView)view.findViewById(0x7f0a011e);
            albumViewSubtitleLandscape = (TextView)view.findViewById(0x7f0a011f);
            albumArtLandscape = (ImageView)view.findViewById(0x7f0a011c);
            albumArtControllerLandscape = new ImageViewAlbumArtController(getArtSize(), albumArtLandscape);
            albumArtControllerLandscape.setDefaultResourceId(0x7f06001a);
            albumArtPortrait = (ImageView)view.findViewById(0x7f0a0118);
            albumArtControllerPortrait = new ImageViewAlbumArtController(getArtSize(), albumArtPortrait);
            albumArtControllerPortrait.setDefaultResourceId(0x7f06001a);
        }
        return view;
    }

    public void onDataSourceUpdated()
    {
        onDataSourceUpdated();
        updateAlbumViewHeader();
    }

    public void onStart()
    {
        if(isTablet)
            updateAlbumViewLayoutForConfiguration(themedContext.getResources().getConfiguration());
        onStart();
    }

    protected void updateAlbumViewHeader()
    {
        if(isTablet)
            if(browseDataSource == null || !browseDataSource.isValid())
            {
                landscapeMetadataGroup.setVisibility(8);
                portraitAlbumHeader.setVisibility(8);
                albumArtLandscape.setVisibility(8);
                albumArtPortrait.setVisibility(8);
            } else
            {
                albumViewTitleLandscape.setText(getAlbumViewTitle());
                albumViewSubtitleLandscape.setText(getAlbumViewSubTitle());
                albumViewTitlePortrait.setText(getAlbumViewTitle());
                albumViewSubtitlePortrait.setText(getAlbumViewSubTitle());
                com.sonos.sclib.SCIBrowseDataSource.PresentationArtworkType presentationartworktype = com.sonos.sclib.SCIBrowseDataSource.PresentationArtworkType.ARTWORK_DEFAULT;
                String s = browseDataSource.getPresentationArtworkURL(presentationartworktype, getArtSize().getPixelWidth());
                com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype = browseDataSource.getPresentationArtworkArtType(presentationartworktype);
                albumArtControllerLandscape.setImageURI(s, scalbumarttype);
                albumArtControllerPortrait.setImageURI(s, scalbumarttype);
                updateLayoutForConfiguration(themedContext.getResources().getConfiguration());
            }
    }

    protected void updateAlbumViewLayoutForConfiguration(Configuration configuration)
    {
        if(configuration.orientation == 2)
        {
            landscapeMetadataGroup.setVisibility(0);
            portraitAlbumHeader.setVisibility(8);
            albumArtLandscape.setVisibility(0);
            albumArtPortrait.setVisibility(8);
        } else
        {
            landscapeMetadataGroup.setVisibility(8);
            portraitAlbumHeader.setVisibility(0);
            albumArtLandscape.setVisibility(8);
            albumArtPortrait.setVisibility(0);
        }
    }

    protected void updateLayoutForConfiguration(Configuration configuration)
    {
        if(configuration.orientation == 2)
        {
            int l = browseList.getPaddingBottom();
            int i1 = browseList.getPaddingTop();
            int j1 = browseList.getPaddingRight();
            int k1 = (int)(getResources().getDimension(0x7f090066) + getResources().getDimension(0x7f090063));
            browseList.setPadding(k1, i1, j1, l);
        } else
        {
            int i = browseList.getPaddingBottom();
            int j = browseList.getPaddingTop();
            int k = browseList.getPaddingRight();
            browseList.setPadding(0, j, k, i);
        }
        updateAlbumViewLayoutForConfiguration(configuration);
    }

    protected ImageViewAlbumArtController albumArtControllerLandscape;
    protected ImageViewAlbumArtController albumArtControllerPortrait;
    protected ImageView albumArtLandscape;
    protected ImageView albumArtPortrait;
    protected TextView albumViewSubtitleLandscape;
    protected TextView albumViewSubtitlePortrait;
    protected TextView albumViewTitleLandscape;
    protected TextView albumViewTitlePortrait;
    protected ListView browseList;
    protected boolean isTablet;
    protected View landscapeMetadataGroup;
    protected View portraitAlbumHeader;
}
