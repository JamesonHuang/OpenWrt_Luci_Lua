// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.sonos.acr.browse.v2.actions.*;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.view.ActionMenu;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.SCIBrowseDataSource;
import com.sonos.sclib.sclib;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourcePageFragment, EditableBrowseDataSourceAdapter

public abstract class DataSourceEditPageFragment extends DataSourcePageFragment
{

    public DataSourceEditPageFragment()
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/browse/v2/pages/DataSourceEditPageFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
    }

    public DataSourceEditPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourcePageFragment(scibrowsedatasource);
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/browse/v2/pages/DataSourceEditPageFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
    }

    protected boolean canEdit()
    {
        return false;
    }

    protected IDataSourceAdapter createDataSourceAdapter(SCIBrowseDataSource scibrowsedatasource, Context context)
    {
        return new EditableBrowseDataSourceAdapter(context);
    }

    public String getActionFilter(boolean flag)
    {
        String s;
        if(inEditMode)
            s = sclib.SC_ACTION_FILTERNAME_EDIT;
        else
            s = getActionFilter(flag);
        return s;
    }

    public ActionSet getDataSourceActions()
    {
        ActionSet actionset = getDataSourceActions();
        if(canEdit())
            if(inEditMode)
                actionset = new ActionSet(new SimpleActionItem(getString(0x7f0c0045)) {

                    public void perform()
                    {
                        setInEditMode(false);
                    }

                    final DataSourceEditPageFragment this$0;

            
            {
                this$0 = DataSourceEditPageFragment.this;
                SimpleActionItem(s);
            }
                }
);
            else
                actionset.addActionItem(new SimpleActionItem(browseDataSource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.MENU_EDIT)) {

                    public void perform()
                    {
                        setInEditMode(true);
                    }

                    final DataSourceEditPageFragment this$0;

            
            {
                this$0 = DataSourceEditPageFragment.this;
                SimpleActionItem(s);
            }
                }
);
        return actionset;
    }

    public ActionData getPageActions()
    {
        ActionData actiondata;
        if(isInEditMode())
            actiondata = null;
        else
            actiondata = getPageActions();
        return actiondata;
    }

    public volatile CharSequence getTitle()
    {
        return getTitle();
    }

    public String getTitle()
    {
        String s = getTitle();
        if(StringUtils.isNotEmptyOrNull(s) && isInEditMode())
        {
            Resources resources = getResources();
            Object aobj[] = new Object[1];
            aobj[0] = s;
            s = String.format(resources.getString(0x7f0c0048, aobj), new Object[0]);
        }
        return s;
    }

    public boolean hasFooterMenu()
    {
        boolean flag;
        if(actionMenu != null && ((View)actionMenu).isShown())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isInEditMode()
    {
        return inEditMode;
    }

    public boolean onBackPressed()
    {
        boolean flag;
        if(inEditMode)
        {
            setInEditMode(false);
            flag = true;
        } else
        {
            flag = onBackPressed();
        }
        return flag;
    }

    public void onDataSourceUpdated()
    {
        onDataSourceUpdated();
        updateActionMenu();
        if(isInEditMode() && dataSourceAdapter.getCount() == 0)
            setInEditMode(false);
    }

    protected void onEditModeChange(boolean flag)
    {
        updateActionMenu();
        notifyPageUpdated();
    }

    public void onUnsubscribeEventSinks()
    {
        onUnsubscribeEventSinks();
        setInEditMode(false);
    }

    public final void setInEditMode(boolean flag)
    {
        if(inEditMode != flag)
        {
            inEditMode = flag;
            onEditModeChange(flag);
        }
    }

    public void updateActionMenu()
    {
        if(actionMenu == null) goto _L2; else goto _L1
_L1:
        ActionSet actionset = getDataSourceActions();
        if(actionset.isEmpty() || !isInEditMode()) goto _L4; else goto _L3
_L3:
        if(((View)actionMenu).getVisibility() != 0)
        {
            actionMenu.setActions(actionset);
            ((View)actionMenu).startAnimation(AnimationUtils.loadAnimation(getThemedContext(), 0x7f040001));
            ((View)actionMenu).setVisibility(0);
        }
_L2:
        return;
_L4:
        if(((View)actionMenu).getVisibility() == 0)
        {
            ((View)actionMenu).startAnimation(AnimationUtils.loadAnimation(getThemedContext(), 0x7f040002));
            ((View)actionMenu).setVisibility(8);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected final String LOG_TAG;
    protected ActionMenu actionMenu;
    protected boolean inEditMode;
}
