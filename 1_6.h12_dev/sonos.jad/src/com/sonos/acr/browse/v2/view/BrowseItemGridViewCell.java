// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.util.AttributeSet;
import com.sonos.acr.util.AlbumArtSize;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            BrowseEditItemListViewCell

public class BrowseItemGridViewCell extends BrowseEditItemListViewCell
{

    public BrowseItemGridViewCell(Context context)
    {
        this(context, null);
    }

    public BrowseItemGridViewCell(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0x7f01004a);
    }

    protected AlbumArtSize getArtSize()
    {
        return AlbumArtSize.SIZE_LARGE_BROWSE;
    }

    protected int getLayoutId()
    {
        return 0x7f03000b;
    }
}
