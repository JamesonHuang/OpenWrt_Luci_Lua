// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.adapters;

import android.content.Context;
import android.widget.SectionIndexer;

// Referenced classes of package com.sonos.acr.browse.v2.adapters:
//            FixedSectionableListAdapter, IDataSourceAdapter

public class SectionableListAdapter extends FixedSectionableListAdapter
    implements SectionIndexer
{

    public SectionableListAdapter(Context context, IDataSourceAdapter idatasourceadapter)
    {
        FixedSectionableListAdapter(context, idatasourceadapter);
    }

    public volatile Object[] getSections()
    {
        return getSections();
    }
}
