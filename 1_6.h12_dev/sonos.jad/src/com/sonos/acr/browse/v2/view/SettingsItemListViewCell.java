// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.sonos.acr.browse.v2.settings.BooleanSharedPrefBrowseItem;
import com.sonos.acr.sclib.EnumeratorAdapter;
import com.sonos.acr.util.ImageViewAlbumArtController;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.browse.v2.view:
//            BrowseItemListViewCell

public class SettingsItemListViewCell extends BrowseItemListViewCell
{

    public SettingsItemListViewCell(Context context)
    {
        super(context);
        accessoryContainer = (ViewGroup)findViewById(0x7f0a0171);
    }

    private boolean getBooleanItemProperty(SCIBrowseItem scibrowseitem)
    {
        if(!(scibrowseitem instanceof BooleanSharedPrefBrowseItem)) goto _L2; else goto _L1
_L1:
        boolean flag;
        if(((BooleanSharedPrefBrowseItem)scibrowseitem).getPrefValue() && scibrowseitem.canActOn())
            flag = true;
        else
            flag = false;
_L4:
        return flag;
_L2:
        SCISettingsBrowseItem scisettingsbrowseitem = (SCISettingsBrowseItem)LibraryUtils.cast(scibrowseitem, com/sonos/sclib/SCISettingsBrowseItem);
        if(scisettingsbrowseitem != null)
        {
            com.sonos.sclib.SCISettingsProperty scisettingsproperty = scisettingsbrowseitem.getProperty(0L);
            if(scisettingsproperty != null)
            {
                SCIBooleanSettingsProperty scibooleansettingsproperty = (SCIBooleanSettingsProperty)LibraryUtils.cast(scisettingsproperty, com/sonos/sclib/SCIBooleanSettingsProperty);
                if(scibooleansettingsproperty != null)
                {
                    flag = scibooleansettingsproperty.getValue();
                    continue; /* Loop/switch isn't completed */
                }
            }
        }
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean itemHasCheckedProperty(SCIBrowseItem scibrowseitem)
    {
        return scibrowseitem.getAttributes().getBoolProp(sclib.SCBROWSEITEM_ATTR_BOOL_ISCHECKED);
    }

    private boolean itemHasSelectedProperty(SCIBrowseItem scibrowseitem)
    {
        return scibrowseitem.getAttributes().getBoolProp(sclib.SCBROWSEITEM_ATTR_BOOL_ISACTIVEITEM);
    }

    private void updateBooleanAccessory(SCIBrowseItem scibrowseitem)
    {
        if(!(accessoryView instanceof CheckBox))
            setAccessoryView(configureCompoundView(new CheckBox(getContext())));
        ((CompoundButton)accessoryView).setChecked(getBooleanItemProperty(scibrowseitem));
        if(!(scibrowseitem instanceof BooleanSharedPrefBrowseItem))
            bottomSubtitleText.setVisibility(8);
    }

    private void updateCheckedAccessory(SCIBrowseItem scibrowseitem)
    {
        if(!(accessoryView instanceof ImageView))
        {
            ImageView imageview = new ImageView(getContext());
            imageview.setImageResource(0x7f02003a);
            setAccessoryView(configureCompoundView(imageview));
        }
    }

    private void updateSelectedAccessory(SCIBrowseItem scibrowseitem)
    {
        if(!(accessoryView instanceof CheckBox))
            setAccessoryView(configureCompoundView(new CheckBox(getContext())));
        ((CompoundButton)accessoryView).setChecked(true);
        bottomSubtitleText.setVisibility(8);
    }

    protected View configureCompoundView(View view)
    {
        view.setFocusable(false);
        view.setFocusableInTouchMode(false);
        view.setClickable(false);
        android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-2, -2);
        layoutparams.gravity = 16;
        WindowManager windowmanager = (WindowManager)getContext().getSystemService("window");
        if(windowmanager != null)
        {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            windowmanager.getDefaultDisplay().getMetrics(displaymetrics);
            layoutparams.topMargin = (int)(2.0F * displaymetrics.scaledDensity);
        }
        view.setLayoutParams(layoutparams);
        return view;
    }

    protected View createDefaultAccessoryView()
    {
        return null;
    }

    protected int getLayoutId()
    {
        return 0x7f03006e;
    }

    protected boolean itemHasBooleanAction(SCIBrowseItem scibrowseitem)
    {
        boolean flag;
        if(scibrowseitem instanceof BooleanSharedPrefBrowseItem)
        {
            flag = true;
        } else
        {
            SCISettingsBrowseItem scisettingsbrowseitem = (SCISettingsBrowseItem)LibraryUtils.cast(scibrowseitem, com/sonos/sclib/SCISettingsBrowseItem);
            if(scisettingsbrowseitem != null)
                flag = (new EnumeratorAdapter(scisettingsbrowseitem.getProperties(), sclib.SCIBOOLEANSETTINGSPROPERTY_INTERFACE)).hasNext();
            else
                flag = false;
        }
        return flag;
    }

    public void setAccessoryView(View view)
    {
        if(accessoryView != view)
        {
            if(accessoryView != null)
                accessoryContainer.removeView(accessoryView);
            accessoryView = view;
            if(view != null)
            {
                accessoryContainer.addView(view);
                accessoryContainer.setVisibility(0);
            } else
            {
                accessoryContainer.setVisibility(8);
            }
        }
    }

    public void setEnabled(boolean flag)
    {
        super.setEnabled(flag);
        if(accessoryContainer != null)
        {
            ViewGroup viewgroup = accessoryContainer;
            int i;
            if(flag)
                i = 0;
            else
                i = 8;
            viewgroup.setVisibility(i);
        }
    }

    protected void updateAlbumArtImage(SCIBrowseItem scibrowseitem)
    {
        if(scibrowseitem.getAlbumArtType() == com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE && !scibrowseitem.hasOrdinal())
        {
            trackNumberText.setVisibility(8);
            albumArtImageView.setVisibility(8);
            imageViewAlbumArtController.reset();
        } else
        {
            super.updateAlbumArtImage(scibrowseitem);
        }
    }

    protected void updateViews(SCIBrowseItem scibrowseitem)
    {
        super.updateViews(scibrowseitem);
        if(scibrowseitem == null) goto _L2; else goto _L1
_L1:
        if(scibrowseitem.canPush()) goto _L4; else goto _L3
_L3:
        if(!itemHasBooleanAction(scibrowseitem)) goto _L6; else goto _L5
_L5:
        accessoryContainer.setVisibility(0);
        updateBooleanAccessory(scibrowseitem);
_L2:
        return;
_L6:
        if(itemHasSelectedProperty(scibrowseitem))
        {
            updateSelectedAccessory(scibrowseitem);
            continue; /* Loop/switch isn't completed */
        }
        if(itemHasCheckedProperty(scibrowseitem))
        {
            updateCheckedAccessory(scibrowseitem);
            continue; /* Loop/switch isn't completed */
        }
_L4:
        accessoryContainer.setVisibility(8);
        if(true) goto _L2; else goto _L7
_L7:
    }

    protected static final String LOG_TAG = com/sonos/acr/browse/v2/view/SettingsItemListViewCell.getSimpleName();
    protected ViewGroup accessoryContainer;
    protected View accessoryView;

}
