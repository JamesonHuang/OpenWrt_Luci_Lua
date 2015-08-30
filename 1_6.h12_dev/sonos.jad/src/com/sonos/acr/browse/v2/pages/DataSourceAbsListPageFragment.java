// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.view.*;
import android.widget.*;
import com.sonos.acr.browse.v2.adapters.BrowseDataSourceAdapter;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.view.ActionMenu;
import com.sonos.acr.util.*;
import com.sonos.sclib.SCIActionContext;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceEditPageFragment

public abstract class DataSourceAbsListPageFragment extends DataSourceEditPageFragment
{

    public DataSourceAbsListPageFragment()
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/browse/v2/pages/DataSourceAbsListPageFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
    }

    public DataSourceAbsListPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceEditPageFragment(scibrowsedatasource);
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/browse/v2/pages/DataSourceAbsListPageFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
    }

    private void updateBusyIndicator()
    {
        if(busyProgressIndicator != null)
        {
            ProgressBar progressbar = busyProgressIndicator;
            byte byte0;
            if(browseDataSource == null || browseDataSource.isValid())
                byte0 = 8;
            else
                byte0 = 0;
            progressbar.setVisibility(byte0);
        }
    }

    protected IDataSourceAdapter createDataSourceAdapter(SCIBrowseDataSource scibrowsedatasource, Context context)
    {
        return new BrowseDataSourceAdapter(context);
    }

    protected AlbumArtSize getArtSize()
    {
        return AlbumArtSize.SIZE_LARGE_BROWSE;
    }

    protected abstract int getLayoutId();

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view;
        View view1;
        view = layoutinflater.inflate(getLayoutId(), viewgroup, false);
        SLog.i(LOG_TAG, "onCreateThemeViewCalled");
        view1 = view.findViewById(0x7f0a0031);
        if(!(view1 instanceof LinearLayout)) goto _L2; else goto _L1
_L1:
        adapterLinearLayout = (LinearLayout)view1;
_L4:
        browseInfoText = (TextView)view.findViewById(0x7f0a0034);
        browseInfoIcon = (ImageView)view.findViewById(0x7f0a0043);
        if(browseInfoIcon != null)
            imageViewAlbumArtController = new ImageViewAlbumArtController(getArtSize(), browseInfoIcon);
        busyProgressIndicator = (ProgressBar)view.findViewById(0x7f0a0035);
        actionMenu = (ActionMenu)view.findViewById(0x7f0a0115);
        return view;
_L2:
        if(view1 instanceof AbsListView)
            adapterListView = (AbsListView)view1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onDataSourceUpdated()
    {
        onDataSourceUpdated();
        updateBrowseInfotext();
        updateBrowseInfoIcon();
        updateBusyIndicator();
        if(browseDataSource != null && browseDataSource.isValid())
            updateLinearLayout();
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        onViewCreated(view, bundle);
        if(adapterListView == null) goto _L2; else goto _L1
_L1:
        setAdapterOnList(adapterListView, dataSourceAdapter);
_L4:
        return;
_L2:
        if(adapterLinearLayout != null)
            updateLinearLayout();
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected abstract void setAdapterOnList(AbsListView abslistview, IDataSourceAdapter idatasourceadapter);

    protected void updateBrowseInfoIcon()
    {
        if(browseInfoIcon != null)
            if(browseDataSource != null && browseDataSource.isValid() && browseDataSource.getNumItems() == 0L)
            {
                com.sonos.sclib.SCIBrowseDataSource.PresentationArtworkType presentationartworktype = com.sonos.sclib.SCIBrowseDataSource.PresentationArtworkType.ARTWORK_ERROR;
                if(browseDataSource.getLastBrowseResult() == 0)
                    presentationartworktype = com.sonos.sclib.SCIBrowseDataSource.PresentationArtworkType.ARTWORK_EMPTY;
                String s = browseDataSource.getPresentationArtworkURL(presentationartworktype, getArtSize().getPixelWidth());
                com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype = browseDataSource.getPresentationArtworkArtType(presentationartworktype);
                imageViewAlbumArtController.setImageURI(s, scalbumarttype);
                browseInfoIcon.setVisibility(0);
            } else
            {
                browseInfoIcon.setVisibility(8);
            }
    }

    protected void updateBrowseInfotext()
    {
        if(browseInfoText != null)
            if(browseDataSource != null && browseDataSource.isValid() && browseDataSource.getNumItems() == 0L)
            {
                String s = null;
                if(browseDataSource.getLastBrowseResult() != 0)
                {
                    s = getDSPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.MESSAGE_LAST_ERROR);
                    browseInfoText.setText(s);
                    if(!hasPerformedErrorAction)
                    {
                        SCIActionContext sciactioncontext = browseDataSource.getLastErrorAction();
                        if(sciactioncontext != null)
                        {
                            sciactioncontext.perform();
                            hasPerformedErrorAction = true;
                        }
                    }
                }
                if(StringUtils.isEmptyOrNull(s))
                {
                    String s1 = getDSPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.MESSAGE_EMPTY);
                    SpannableString spannablestring = new SpannableString(s1);
                    SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
                    int i = s1.indexOf("\u22EF");
                    if(i != -1)
                        spannablestring.setSpan(new ImageSpan(getThemedContext(), 0x7f020056, 1), i, i + "\u22EF".length(), 33);
                    int j = s1.indexOf("*");
                    int k = s1.indexOf("*", j + 1);
                    if(j != -1 && k != -1)
                    {
                        spannablestring.setSpan(new StyleSpan(1), j + 1, k, 33);
                        spannablestringbuilder.append(spannablestring);
                        spannablestringbuilder.delete(k, k + 1);
                        spannablestringbuilder.delete(j, j + 1);
                    }
                    if(spannablestringbuilder.toString().length() != 0)
                        browseInfoText.setText(spannablestringbuilder, android.widget.TextView.BufferType.SPANNABLE);
                    else
                        browseInfoText.setText(spannablestring);
                }
                browseInfoText.setVisibility(0);
            } else
            {
                browseInfoText.setText("");
                browseInfoText.setVisibility(8);
            }
    }

    protected void updateLinearLayout()
    {
        if(adapterLinearLayout != null && dataSourceAdapter != null)
        {
            View aview[] = new View[adapterLinearLayout.getChildCount()];
            for(int i = 0; i < aview.length; i++)
                aview[i] = adapterLinearLayout.getChildAt(i);

            adapterLinearLayout.removeAllViews();
            int j = 0;
            while(j < dataSourceAdapter.getCount()) 
            {
                View view;
                if(j < aview.length)
                    view = aview[j];
                else
                    view = null;
                adapterLinearLayout.addView(dataSourceAdapter.getView(j, view, adapterLinearLayout));
                j++;
            }
        }
    }

    protected final String LOG_TAG;
    protected LinearLayout adapterLinearLayout;
    protected AbsListView adapterListView;
    protected ImageView browseInfoIcon;
    protected TextView browseInfoText;
    protected ProgressBar busyProgressIndicator;
    private boolean hasPerformedErrorAction;
    protected ImageViewAlbumArtController imageViewAlbumArtController;
}
