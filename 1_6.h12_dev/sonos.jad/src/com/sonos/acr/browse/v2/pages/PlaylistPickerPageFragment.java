// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import com.sonos.acr.browse.v2.PickerFragment;
import com.sonos.acr.browse.v2.actions.ActionItem;
import com.sonos.acr.browse.v2.actions.ActionSet;
import com.sonos.acr.browse.v2.adapters.*;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.browse.v2.view.BrowseItemListViewCell;
import com.sonos.sclib.SCIBrowseDataSource;
import com.sonos.sclib.sclibConstants;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceSectionedListPageFragment

public class PlaylistPickerPageFragment extends DataSourceSectionedListPageFragment
{

    public PlaylistPickerPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceSectionedListPageFragment(scibrowsedatasource);
    }

    private void dismiss()
    {
        ((PickerFragment)getParentFragment()).dismissPicker();
    }

    public BrowseItemCell createCellView(int i)
    {
        return new BrowseItemListViewCell(themedContext) {

            protected int getLayoutId()
            {
                return 0x7f030010;
            }

            final PlaylistPickerPageFragment this$0;

            
            {
                this$0 = PlaylistPickerPageFragment.this;
                BrowseItemListViewCell(context);
            }
        }
;
    }

    protected IDataSourceAdapter createDataSourceAdapter(SCIBrowseDataSource scibrowsedatasource, Context context)
    {
        return new SectionableListAdapter(context, new BrowseDataSourceAdapter(context) {

            public boolean onLongClick(View view)
            {
                return true;
            }

            final PlaylistPickerPageFragment this$0;

            
            {
                this$0 = PlaylistPickerPageFragment.this;
                BrowseDataSourceAdapter(context);
            }
        }
);
    }

    protected String getDSPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType presentationtexttype)
    {
        String s;
        if(presentationtexttype == com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.MESSAGE_EMPTY)
            s = getResources().getString(0x7f0c002d);
        else
            s = getDSPresentationText(presentationtexttype);
        return s;
    }

    protected int getLayoutId()
    {
        return 0x7f030054;
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = onCreateThemedView(layoutinflater, viewgroup, bundle);
        titleText = (TextView)view.findViewById(0x7f0a0128);
        ActionSet actionset = getDataSourceActions();
        if(actionset != null)
        {
            final ActionItem newPlaylistAction = actionset.findActionById(sclibConstants.SC_ACTIONID_NEW_PLAYLIST);
            if(newPlaylistAction != null)
            {
                View view1 = view.findViewById(0x7f0a0129);
                view1.setVisibility(0);
                view1.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view2)
                    {
                        dismiss();
                        newPlaylistAction.perform();
                    }

                    final PlaylistPickerPageFragment this$0;
                    final ActionItem val$newPlaylistAction;

            
            {
                this$0 = PlaylistPickerPageFragment.this;
                newPlaylistAction = actionitem;
                Object();
            }
                }
);
                ((TextView)view.findViewById(0x7f0a012a)).setText(newPlaylistAction.getLabel());
            }
        }
        return view;
    }

    protected void updateHeaderBar()
    {
        titleText.setText(getTitle());
    }

    TextView titleText;

}
