// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.content.res.Resources;
import com.sonos.sclib.SCIBrowseItem;
import java.util.regex.Pattern;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            BrowseItemListViewCell

public class SearchBrowseListItemCell extends BrowseItemListViewCell
{

    public SearchBrowseListItemCell(Context context)
    {
        super(context);
        aggregateIndent = (int)getResources().getDimension(0x7f09005f);
        aggregateSidePadding = (int)getResources().getDimension(0x7f090060);
        standardSidePadding = (int)getResources().getDimension(0x7f090065);
    }

    protected int getLayoutId()
    {
        return 0x7f030068;
    }

    public void setSectioned(boolean flag)
    {
        if(flag)
            setPadding(aggregateSidePadding + aggregateIndent, 0, aggregateSidePadding, 0);
        else
            setPadding(standardSidePadding, 0, standardSidePadding, 0);
    }

    protected void updateTitleViews(SCIBrowseItem scibrowseitem)
    {
        super.updateTitleViews(scibrowseitem);
    }

    protected void updateViews(SCIBrowseItem scibrowseitem)
    {
        super.updateViews(scibrowseitem);
        if(scibrowseitem != null && oddBackgroundDrawable != 0)
        {
            int i;
            if((cellPosition - headerPosition) % 2 == 0)
                i = evenBackgroundDrawable;
            else
                i = oddBackgroundDrawable;
            setBackgroundResource(i);
        }
    }

    public static final String LOG_TAG = com/sonos/acr/browse/v2/view/SearchBrowseListItemCell.getSimpleName();
    private int aggregateIndent;
    private int aggregateSidePadding;
    protected Pattern searchRegEx;
    private int standardSidePadding;

}
