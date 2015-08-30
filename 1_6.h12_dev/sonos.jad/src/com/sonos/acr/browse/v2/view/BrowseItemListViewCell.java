// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.sonos.acr.util.ActionMenuUtils;
import com.sonos.sclib.SCIBrowseItem;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            BrowseItemCell

public class BrowseItemListViewCell extends BrowseItemCell
{

    public BrowseItemListViewCell(Context context)
    {
        super(context);
        init();
    }

    public BrowseItemListViewCell(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init();
    }

    public BrowseItemListViewCell(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        init();
    }

    protected int getLayoutId()
    {
        return 0x7f03000c;
    }

    protected void init()
    {
        trackNumberText = (TextView)findViewById(0x7f0a0046);
    }

    protected void updateAlbumArtImage(SCIBrowseItem scibrowseitem)
    {
        if(!scibrowseitem.hasOrdinal()) goto _L2; else goto _L1
_L1:
        String s;
        s = scibrowseitem.getOrdinal();
        if(s.length() <= 0)
            break MISSING_BLOCK_LABEL_31;
        String s1 = Integer.valueOf(s).toString();
        s = s1;
_L6:
        if(trackNumberText != null)
        {
            trackNumberText.setVisibility(0);
            trackNumberText.setText(s);
        }
_L4:
        super.updateAlbumArtImage(scibrowseitem);
        return;
_L2:
        if(trackNumberText != null)
            trackNumberText.setVisibility(8);
        if(true) goto _L4; else goto _L3
_L3:
        NumberFormatException numberformatexception;
        numberformatexception;
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void updateViews(SCIBrowseItem scibrowseitem)
    {
        super.updateViews(scibrowseitem);
        if(scibrowseitem != null)
        {
            boolean flag = isInEditMode();
            boolean flag1;
            if((ActionMenuUtils.canActOn(scibrowseitem, flag) || ActionMenuUtils.canPush(scibrowseitem, flag)) && (scibrowseitem.getAlbumArtType() != com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_RESTRICTED || flag))
                flag1 = true;
            else
                flag1 = false;
            setEnabled(flag1);
        }
    }

    public static final String LOG_TAG = com/sonos/acr/browse/v2/view/BrowseItemListViewCell.getSimpleName();
    protected TextView trackNumberText;

}
