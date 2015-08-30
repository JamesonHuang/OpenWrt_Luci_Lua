// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.view.PageHeaderController;
import com.sonos.acr.util.*;
import com.sonos.acr.view.RemoteImageView;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.SCIInfoViewTextPaneMetadata;

public class TextPanePageFragment extends PageFragment
    implements android.view.ViewTreeObserver.OnGlobalLayoutListener
{

    public TextPanePageFragment(String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata)
    {
        setThemedAttributeId(0x7f010055);
        title = s;
        text = s1;
        metadata = sciinfoviewtextpanemetadata;
    }

    private void updateView()
    {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        albumArtMain.setDefaultResourceId(0x7f060022);
        if(albumArtNonArtist != null)
            albumArtNonArtist.setDefaultResourceId(0x7f060022);
        contentView.setText(text);
        int i;
        if(isPhoneLayout)
            i = AlbumArtSize.SIZE_NOW_PLAYING.getPixelWidth();
        else
            i = AlbumArtSize.SIZE_LARGE_BROWSE.getPixelWidth();
        if(metadata != null && !StringUtils.isEmptyOrNull(metadata.getAlbumArtURL(i)))
        {
            String s = metadata.getAlbumArtURL(i);
            if(s != null && s.length() > 0)
            {
                albumArtMain.setImageURI(s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_VIRTUAL_URL);
                if(albumArtNonArtist != null)
                    albumArtNonArtist.setImageURI(s, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_VIRTUAL_URL);
            }
            String s1 = metadata.getTitle();
            String s2 = metadata.getArtist();
            if(s2 != null && s2.length() > 0)
                metadataText2.setText(s2);
            else
                metadataText2.setVisibility(8);
            if(s1 != null && s1.length() > 0)
                metadataText1.setText(s1);
            else
                metadataText1.setVisibility(8);
            if(metadata.isArtist())
            {
                metadataText2.setVisibility(8);
                if(!isPhoneLayout)
                {
                    for(int j = 0; j < albumArtMain.getChildCount(); j++)
                    {
                        View view = albumArtMain.getChildAt(j);
                        if(view instanceof SonosImageView)
                        {
                            ((SonosImageView)view).setFixedDimention(0);
                            ((SonosImageView)view).setScaleType(android.widget.ImageView.ScaleType.CENTER_CROP);
                        }
                    }

                }
            } else
            if(isPhoneLayout)
            {
                if(albumArtNonArtist != null)
                    albumArtNonArtist.setVisibility(0);
                albumArtMain.setVisibility(8);
            }
        } else
        {
            if(artContainer != null)
                artContainer.setVisibility(8);
            albumArtMain.setVisibility(8);
            if(albumArtNonArtist != null)
                albumArtNonArtist.setVisibility(8);
            metadataText1.setVisibility(8);
            metadataText2.setVisibility(8);
        }
        if(metadataOverlay != null && metadataText1.getVisibility() == 8 && metadataText2.getVisibility() == 8)
            metadataOverlay.setVisibility(8);
    }

    public volatile CharSequence getTitle()
    {
        return getTitle();
    }

    public String getTitle()
    {
        return title;
    }

    public boolean isGone()
    {
        return false;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        onConfigurationChanged(configuration);
        View view = onCreateView((LayoutInflater)getActivity().getSystemService("layout_inflater"), null, null);
        updateView();
        pageHeader = view.findViewById(0x7f0a0047);
        pageHeaderController.watchView(pageHeader);
        updateHeaderBar();
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = layoutinflater.inflate(0x7f030053, null);
        scrollView = (ScrollView)view.findViewById(0x7f0a0122);
        albumArtMain = (RemoteImageView)view.findViewById(0x7f0a002c);
        albumArtNonArtist = (RemoteImageView)view.findViewById(0x7f0a0123);
        metadataText1 = (TextView)view.findViewById(0x7f0a00ce);
        metadataText2 = (TextView)view.findViewById(0x7f0a00cf);
        metadataOverlay = view.findViewById(0x7f0a0124);
        contentView = (TextView)view.findViewById(0x7f0a0125);
        artContainer = view.findViewById(0x7f0a0126);
        boolean flag;
        if(DisplayController.getScreenType() == 0)
            flag = true;
        else
            flag = false;
        isPhoneLayout = flag;
        artVisibleHeight = (int)TypedValue.applyDimension(1, 168F, getResources().getDisplayMetrics());
        artistExtraHeight = (int)TypedValue.applyDimension(1, 110F, getResources().getDisplayMetrics());
        if(rootView == null)
            rootView = new FrameLayout(getActivity());
        else
            rootView.removeAllViews();
        rootView.addView(view);
        return rootView;
    }

    public void onGlobalLayout()
    {
        if(!isPhoneLayout) goto _L2; else goto _L1
_L1:
        if(metadata != null && albumArtMain.getVisibility() != 8 && albumArtMain.getHeight() > 0)
        {
            int l = artVisibleHeight;
            if(metadata.isArtist())
                l += artistExtraHeight;
            rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            contentView.setMinimumHeight(getView().getHeight());
            final int scrollOffset = albumArtMain.getHeight() - l;
            scrollView.post(new Runnable() {

                public void run()
                {
                    scrollView.scrollTo(0, scrollOffset);
                }

                final TextPanePageFragment this$0;
                final int val$scrollOffset;

            
            {
                this$0 = TextPanePageFragment.this;
                scrollOffset = i;
                Object();
            }
            }
);
        }
        if(metadata != null && albumArtNonArtist.getVisibility() != 8 && albumArtNonArtist.getHeight() > 0)
        {
            int k = artVisibleHeight;
            if(metadata.isArtist())
                k += artistExtraHeight;
            rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            contentView.setMinimumHeight(getView().getHeight() - k);
            final int scrollOffset = albumArtNonArtist.getHeight() - k;
            scrollView.post(new Runnable() {

                public void run()
                {
                    scrollView.scrollTo(0, scrollOffset);
                }

                final TextPanePageFragment this$0;
                final int val$scrollOffset;

            
            {
                this$0 = TextPanePageFragment.this;
                scrollOffset = i;
                Object();
            }
            }
);
        }
_L4:
        return;
_L2:
        View view = albumArtMain.getCurrentView();
        if(view instanceof SonosImageView)
        {
            Drawable drawable = ((SonosImageView)view).getDrawable();
            if(drawable != null && drawable.getIntrinsicWidth() != drawable.getIntrinsicHeight() && drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0)
            {
                rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                float f = (float)drawable.getIntrinsicWidth() / (float)drawable.getIntrinsicHeight();
                int i = albumArtMain.getLayoutParams().width;
                int j = (int)((float)i / f);
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000));
                android.view.ViewGroup.LayoutParams layoutparams = albumArtMain.getLayoutParams();
                layoutparams.height = view.getMeasuredHeight();
                albumArtMain.setLayoutParams(layoutparams);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onStop()
    {
        onStop();
        rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        onViewCreated(view, bundle);
        updateView();
    }

    private static final int ARTIST_EXTRA_HEIGHT = 110;
    private static final int ART_VISIBLE_HEIGHT = 168;
    private RemoteImageView albumArtMain;
    private RemoteImageView albumArtNonArtist;
    private View artContainer;
    private int artVisibleHeight;
    private int artistExtraHeight;
    private TextView contentView;
    private boolean isPhoneLayout;
    private SCIInfoViewTextPaneMetadata metadata;
    private View metadataOverlay;
    private TextView metadataText1;
    private TextView metadataText2;
    private ViewGroup rootView;
    private ScrollView scrollView;
    private String text;
    private String title;

}
