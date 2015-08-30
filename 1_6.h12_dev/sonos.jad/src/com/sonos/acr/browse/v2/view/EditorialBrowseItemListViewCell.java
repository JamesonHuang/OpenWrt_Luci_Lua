// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.widget.TextView;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.SCIBrowseItem;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            BrowseItemListViewCell

public class EditorialBrowseItemListViewCell extends BrowseItemListViewCell
{

    public EditorialBrowseItemListViewCell(Context context)
    {
        super(context);
        summaryText = (TextView)findViewById(0x7f0a0041);
    }

    protected int getLayoutId()
    {
        return 0x7f030009;
    }

    protected void updateTitleViews(SCIBrowseItem scibrowseitem)
    {
        super.updateTitleViews(scibrowseitem);
        String s = scibrowseitem.getBrowseItemText(com.sonos.sclib.SCIBrowseItem.SCBrowseItemText.BIT_LINE_3);
        if(StringUtils.isNotEmptyOrNull(s))
        {
            summaryText.setText(s);
            summaryText.setVisibility(0);
        } else
        {
            summaryText.setVisibility(8);
        }
        topTitleText.setSingleLine(false);
        topTitleText.setMaxLines(2);
        bottomSubtitleText.setSingleLine(false);
        bottomSubtitleText.setMaxLines(2);
    }

    protected TextView summaryText;
}
