// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.widget.TextView;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            BrowseItemCell

public class ActionItemListViewCell extends BrowseItemCell
{

    public ActionItemListViewCell(Context context)
    {
        super(context, null, 0);
        topTitleText = (TextView)findViewById(0x7f0a0032);
    }

    protected int getLayoutId()
    {
        return 0x7f030003;
    }

    public static final String LOG_TAG = com/sonos/acr/browse/v2/view/ActionItemListViewCell.getSimpleName();

}
