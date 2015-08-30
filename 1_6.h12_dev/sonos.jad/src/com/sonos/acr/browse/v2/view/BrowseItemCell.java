// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.sonos.acr.browse.v2.common.BrowseItemEventSink;
import com.sonos.acr.util.*;
import com.sonos.sclib.SCIBrowseItem;

public abstract class BrowseItemCell extends RelativeLayout
{

    public BrowseItemCell(Context context)
    {
        this(context, null);
    }

    public BrowseItemCell(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f010049);
    }

    public BrowseItemCell(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.BrowseItemCell, i, 0);
        int j = typedarray.getResourceId(1, 0);
        oddBackgroundDrawable = typedarray.getResourceId(0, 0);
        evenBackgroundDrawable = typedarray.getResourceId(2, 0);
        if(evenBackgroundDrawable == 0)
            evenBackgroundDrawable = typedarray.getResourceId(0, 0);
        typedarray.recycle();
        LayoutInflater.from(context).inflate(getLayoutId(), this, true);
        albumArtImageView = (ImageView)findViewById(0x7f0a003e);
        topTitleText = (TextView)findViewById(0x7f0a003f);
        bottomSubtitleText = (TextView)findViewById(0x7f0a0040);
        if(albumArtImageView != null)
        {
            imageViewAlbumArtController = new ImageViewAlbumArtController(getArtSize(), albumArtImageView);
            imageViewAlbumArtController.setDefaultResourceId(j);
            imageViewAlbumArtController.setAnimation(AnimationUtils.loadAnimation(context, 0x7f040001));
        }
    }

    private boolean canActOn(SCIBrowseItem scibrowseitem, boolean flag)
    {
        boolean flag1;
        if(ActionMenuUtils.canActOn(scibrowseitem, flag) || ActionMenuUtils.canPush(scibrowseitem, flag))
            flag1 = true;
        else
            flag1 = false;
        return flag1;
    }

    protected AlbumArtSize getArtSize()
    {
        return AlbumArtSize.SIZE_BROWSE;
    }

    public SCIBrowseItem getBrowseItem()
    {
        return browseItem;
    }

    public int getCellPosition()
    {
        return cellPosition;
    }

    protected abstract int getLayoutId();

    protected void onEditModeChanged()
    {
        if(browseItem != null && browseItem.isDataAvailable())
            updateViews(browseItem);
    }

    public void setHeaderPosition(int i)
    {
        headerPosition = i;
        updateViews(browseItem);
    }

    public void stop()
    {
        if(imageViewAlbumArtController != null)
            imageViewAlbumArtController.cancelDownload();
    }

    public void subscribe(SCIBrowseItem scibrowseitem, int i)
    {
        unsubscribe();
        cellPosition = i;
        browseItem = scibrowseitem;
        eventSink.subscribe(scibrowseitem);
        updateViews(scibrowseitem);
    }

    public void unsubscribe()
    {
        eventSink.unsubscribe();
        browseItem = null;
        cellPosition = -1;
        headerPosition = -1;
    }

    protected void updateAlbumArtImage(SCIBrowseItem scibrowseitem)
    {
        if(albumArtImageView != null)
            if(scibrowseitem.hasOrdinal())
            {
                albumArtImageView.setVisibility(8);
                imageViewAlbumArtController.reset();
            } else
            {
                albumArtImageView.setVisibility(0);
                if(scibrowseitem.getAlbumArtType() == com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE)
                    imageViewAlbumArtController.reset();
                else
                    imageViewAlbumArtController.setImageFromBrowseItem(scibrowseitem);
            }
    }

    protected void updateTitleViews(SCIBrowseItem scibrowseitem)
    {
        topTitleText.setText(scibrowseitem.getPrimaryAdornedTitle());
        String s = scibrowseitem.getSecondaryTitle();
        if(bottomSubtitleText != null)
            if(scibrowseitem.isSecondaryTitleValid() && s != null && s.length() > 0)
            {
                bottomSubtitleText.setVisibility(0);
                topTitleText.setMaxLines(1);
                topTitleText.setSingleLine(true);
                bottomSubtitleText.setText(scibrowseitem.getSecondaryTitle());
            } else
            {
                bottomSubtitleText.setVisibility(8);
                topTitleText.setSingleLine(false);
                topTitleText.setMaxLines(2);
            }
    }

    protected void updateViews(SCIBrowseItem scibrowseitem)
    {
        if(scibrowseitem != null)
        {
            boolean flag;
            if(canActOn(scibrowseitem, isInEditMode()) && scibrowseitem.getAlbumArtType() != com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_RESTRICTED)
                flag = true;
            else
                flag = false;
            setEnabled(flag);
            updateTitleViews(scibrowseitem);
            updateAlbumArtImage(scibrowseitem);
            if(oddBackgroundDrawable != 0)
            {
                int i;
                if(cellPosition % 2 == 0)
                    i = evenBackgroundDrawable;
                else
                    i = oddBackgroundDrawable;
                setBackgroundResource(i);
            }
        }
    }

    public static final String LOG_TAG = com/sonos/acr/browse/v2/view/BrowseItemCell.getSimpleName();
    protected ImageView albumArtImageView;
    protected TextView bottomSubtitleText;
    protected SCIBrowseItem browseItem;
    protected int cellPosition;
    protected int evenBackgroundDrawable;
    protected BrowseItemEventSink eventSink = new BrowseItemEventSink() {

        public void onItemChanged(SCIBrowseItem scibrowseitem)
        {
            SLog.d(BrowseItemCell.LOG_TAG, "On Item Changed Event! ");
            updateViews(scibrowseitem);
        }

        final BrowseItemCell this$0;

            
            {
                this$0 = BrowseItemCell.this;
                super();
            }
    }
;
    protected int headerPosition;
    protected ImageViewAlbumArtController imageViewAlbumArtController;
    protected int oddBackgroundDrawable;
    protected TextView topTitleText;

}
