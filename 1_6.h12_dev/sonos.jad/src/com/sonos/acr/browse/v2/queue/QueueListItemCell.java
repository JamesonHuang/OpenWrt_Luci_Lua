// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.queue;

import android.content.Context;
import com.sonos.acr.browse.v2.view.BrowseEditItemListViewCell;
import com.sonos.acr.nowplaying.controllers.PlayIndicatorController;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.PlayIndicatorView;
import com.sonos.sclib.*;

public class QueueListItemCell extends BrowseEditItemListViewCell
{

    public QueueListItemCell(Context context)
    {
        BrowseEditItemListViewCell(context);
        contentAlpha = 1.0F;
        playIndicatorView = (PlayIndicatorView)findViewById(0x7f0a0133);
        setBackgroundResource(0x7f0200ae);
        setClipToPadding(false);
        updateEditButtons(null);
    }

    private void updateContentFade()
    {
        if(albumArtImageView != null)
            ViewUtils.setAlpha(albumArtImageView, contentAlpha);
        if(topTitleText != null)
            ViewUtils.setAlpha(topTitleText, contentAlpha);
        if(bottomSubtitleText != null)
            ViewUtils.setAlpha(bottomSubtitleText, contentAlpha);
    }

    private void updateIndicatorImage(SCIBrowseItem scibrowseitem)
    {
        int i = 0;
        com.sonos.sclib.SCIPlayQueueItemState.State state = getPlayState(scibrowseitem);
        int j;
        if(!isInEditMode())
        {
            boolean flag;
            PlayIndicatorView playindicatorview;
            if(!isInEditMode() && state != com.sonos.sclib.SCIPlayQueueItemState.State.PQI_STATE_NONE)
                flag = true;
            else
                flag = false;
            playindicatorview = playIndicatorView;
            if(!flag)
                i = 4;
            playindicatorview.setVisibility(i);
        }
        if(state == com.sonos.sclib.SCIPlayQueueItemState.State.PQI_STATE_NONE)
            j = 0x7f0200ae;
        else
            j = 0x7f0200b0;
        setBackgroundResource(j);
    }

    protected boolean canDeleteItem(SCIBrowseItem scibrowseitem)
    {
        boolean flag;
        if(isInEditMode() && canDeleteItem(scibrowseitem))
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean canReorderItem(SCIBrowseItem scibrowseitem)
    {
        boolean flag;
        if(isInEditMode() && canReorderItem(scibrowseitem))
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected int getLayoutId()
    {
        return 0x7f03005d;
    }

    public com.sonos.sclib.SCIPlayQueueItemState.State getPlayState(SCIBrowseItem scibrowseitem)
    {
        if(scibrowseitem == null) goto _L2; else goto _L1
_L1:
        SCIPlayQueueItemState sciplayqueueitemstate = (SCIPlayQueueItemState)scibrowseitem.queryInterface(sclibConstants.SCIPLAYQUEUEITEMSTATE_INTERFACE);
        if(sciplayqueueitemstate == null) goto _L2; else goto _L3
_L3:
        com.sonos.sclib.SCIPlayQueueItemState.State state = sciplayqueueitemstate.getState();
_L5:
        return state;
_L2:
        state = com.sonos.sclib.SCIPlayQueueItemState.State.PQI_STATE_NONE;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void setContentAlpha(float f)
    {
        if(contentAlpha != f)
        {
            contentAlpha = f;
            updateContentFade();
        }
    }

    public void setPlayViewIndicatorController(PlayIndicatorController playindicatorcontroller)
    {
        playIndicatorView.setController(playindicatorcontroller);
    }

    public void stop()
    {
        playIndicatorView.setVisibility(4);
        stop();
    }

    protected void updateEditButtons(SCIBrowseItem scibrowseitem)
    {
        updateEditButtons(scibrowseitem);
        PlayIndicatorView playindicatorview = playIndicatorView;
        byte byte0;
        if(isInEditMode())
            byte0 = 4;
        else
            byte0 = 0;
        playindicatorview.setVisibility(byte0);
    }

    protected void updateViews(SCIBrowseItem scibrowseitem)
    {
        updateViews(scibrowseitem);
        updateIndicatorImage(scibrowseitem);
        updateContentFade();
    }

    public static final String LOG_TAG = com/sonos/acr/browse/v2/queue/QueueListItemCell.getSimpleName();
    protected float contentAlpha;
    protected PlayIndicatorView playIndicatorView;

}
