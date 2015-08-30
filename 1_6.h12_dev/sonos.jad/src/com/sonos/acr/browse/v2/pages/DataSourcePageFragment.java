// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.actions.*;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.adapters.IDataSourceHandler;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

public abstract class DataSourcePageFragment extends PageFragment
    implements com.sonos.acr.browse.v2.adapters.IDataSourceAdapter.AdapterListener, com.sonos.acr.browse.v2.adapters.IDataSourceAdapter.CellFactory, IDataSourceHandler
{

    public DataSourcePageFragment()
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/browse/v2/pages/DataSourcePageFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
    }

    public DataSourcePageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/browse/v2/pages/DataSourcePageFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
        setBrowseDataSource(scibrowsedatasource);
    }

    private void resolveBrowseAdapter()
    {
        if(themedContext != null)
        {
            if(dataSourceAdapter == null)
            {
                dataSourceAdapter = createDataSourceAdapter(browseDataSource, themedContext);
                dataSourceAdapter.setCellFactory(this);
                dataSourceAdapter.setOnItemClickListener(itemClickListener);
                dataSourceAdapter.setAdapterListener(this);
            }
            dataSourceAdapter.setBrowseDataSource(browseDataSource);
        }
    }

    protected abstract IDataSourceAdapter createDataSourceAdapter(SCIBrowseDataSource scibrowsedatasource, Context context);

    protected String getDSPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType presentationtexttype)
    {
        String s = "";
        if(browseDataSource != null)
            s = browseDataSource.getPresentationText(presentationtexttype);
        return s;
    }

    public ActionSet getDataSourceActions()
    {
        return getDataSourceActions(sclib.createDefaultSCIActionFilter(getActionFilter(true)));
    }

    public ActionSet getDataSourceActions(SCIActionFilter sciactionfilter)
    {
        com.sonos.sclib.SCIEnumerator scienumerator;
        if(browseDataSource != null)
            scienumerator = browseDataSource.getFilteredActions(sciactionfilter);
        else
            scienumerator = null;
        return new ActionSet(scienumerator);
    }

    public ActionData getPageActions()
    {
        ActionSet actionset = getDataSourceActions();
        Object obj;
        if(browseDataSource != null && actionset.size() > 0)
            obj = new DataSourceActionsActionMenuData(browseDataSource, actionset);
        else
        if(browseDataSource != null && browseDataSource.getMoreMenuDataSource() != null)
            obj = new DataSourceActionMenuData(browseDataSource.getMoreMenuDataSource(), getTitle());
        else
            obj = null;
        return ((ActionData) (obj));
    }

    public String getSCUri()
    {
        String s;
        if(browseDataSource == null)
            s = "";
        else
            s = browseDataSource.getSCUri();
        return s;
    }

    public String getSubTitle()
    {
        String s;
        if(!isGone() && browseDataSource != null)
            s = browseDataSource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.SUBTITLE_DEFAULT);
        else
            s = null;
        return s;
    }

    public volatile CharSequence getTitle()
    {
        return getTitle();
    }

    public String getTitle()
    {
        String s;
        if(!isGone() && browseDataSource != null)
            s = browseDataSource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.TITLE_DEFAULT);
        else
            s = null;
        return s;
    }

    public ActionData getTitleActions()
    {
        ActionSet actionset = getDataSourceActions(sclib.createDefaultSCIActionFilter(sclib.SC_ACTION_FILTERNAME_ACCOUNT));
        DataSourceActionsActionMenuData datasourceactionsactionmenudata;
        if(browseDataSource != null && actionset.size() > 0)
            datasourceactionsactionmenudata = new DataSourceActionsActionMenuData(browseDataSource, actionset) {

                public String getPrimaryText()
                {
                    String s = browseDataSource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.TITLE_DEFAULT);
                    String s1 = getResources().getString(0x7f0c001d);
                    Object aobj[] = new Object[1];
                    aobj[0] = s;
                    return String.format(s1, aobj);
                }

                final DataSourcePageFragment this$0;

            
            {
                this$0 = DataSourcePageFragment.this;
                DataSourceActionsActionMenuData(scibrowsedatasource, actionset);
            }
            }
;
        else
            datasourceactionsactionmenudata = null;
        return datasourceactionsactionmenudata;
    }

    public boolean isGone()
    {
        boolean flag;
        if(dataSourceAdapter == null || dataSourceAdapter.isGone())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onCreate(Bundle bundle)
    {
        onCreate(bundle);
        if(bundle != null)
            setBrowseDataSource(LibraryUtils.createBrowseDataSource(bundle.getString("DATA_SOURCE_URI"), bundle.getString("DATA_SOURCE_TITLE")));
    }

    public void onDataSourceGone()
    {
        invalidatePage();
    }

    public void onDataSourceInvalidated()
    {
        notifyPageUpdated();
    }

    public void onDataSourceUpdated()
    {
        notifyPageUpdated();
    }

    public void onDestroy()
    {
        onDestroy();
        if(dataSourceAdapter != null)
        {
            dataSourceAdapter.setAdapterListener(null);
            dataSourceAdapter = null;
        }
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        if(!isGone())
        {
            if(bundle == null)
                bundle = new Bundle();
            onSaveInstanceState(bundle);
            bundle.putCharSequence("DATA_SOURCE_URI", browseDataSource.getSCUri());
            bundle.putCharSequence("DATA_SOURCE_TITLE", browseDataSource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.TITLE_DEFAULT));
        }
    }

    public void onStart()
    {
        onStart();
        resolveBrowseAdapter();
    }

    public void onSubscribeEventSinks()
    {
        onSubscribeEventSinks();
        if(dataSourceAdapter != null)
            dataSourceAdapter.subscribe();
    }

    public void onUnsubscribeEventSinks()
    {
        onUnsubscribeEventSinks();
        if(dataSourceAdapter != null)
        {
            dataSourceAdapter.unsubscribe();
            dataSourceAdapter.releaseResources();
        }
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        onViewCreated(view, bundle);
        resolveBrowseAdapter();
    }

    public void setBrowseDataSource(SCIBrowseDataSource scibrowsedatasource)
    {
        browseDataSource = scibrowsedatasource;
        resolveBrowseAdapter();
    }

    public void setOnItemClickListener(com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener onitemclicklistener)
    {
        itemClickListener = onitemclicklistener;
        if(dataSourceAdapter != null)
            dataSourceAdapter.setOnItemClickListener(onitemclicklistener);
    }

    protected static final String DATA_SOURCE_TITLE = "DATA_SOURCE_TITLE";
    protected static final String DATA_SOURCE_URI = "DATA_SOURCE_URI";
    public final String LOG_TAG;
    protected SCIBrowseDataSource browseDataSource;
    protected IDataSourceAdapter dataSourceAdapter;
    protected com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener itemClickListener;
}
