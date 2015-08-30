// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.os.Bundle;
import android.support.v4.app.*;
import android.view.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.Browseable;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.PickerFragment;
import com.sonos.acr.browse.v2.actions.*;
import com.sonos.acr.browse.v2.view.*;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Stack;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            PageFactory, ActionDialogFragment, BrowseStackPickerFragment, DataSourcePageFragment, 
//            TextPanePageFragment

public class BrowseFlipperPageFragment extends PageFragment
    implements com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener, com.sonos.acr.browse.v2.PageFragment.PageFragmentListener, Browseable, PageFactory
{

    public BrowseFlipperPageFragment()
    {
        BrowseFlipperPageFragment(0);
    }

    public BrowseFlipperPageFragment(int i)
    {
        BrowseFlipperPageFragment(i, 0x7f030011);
    }

    public BrowseFlipperPageFragment(int i, int j)
    {
        PageFragment(i);
        pages = new Stack();
        forceAnimation = false;
        layoutRes = j;
    }

    private void popNPInfoviewPages()
    {
        int i = 0;
        for(int j = 0; j < pages.size(); j++)
            if(((PageFragment)pages.get(pages.size() - (j + 1))).getBackNagivation() != com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE)
                i = j + 1;

        if(i > 0)
            popPages(i);
    }

    private void showActionList(BrowseItemCell browseitemcell, BrowseItemActionData browseitemactiondata, boolean flag)
    {
        ActionDialogFragment actiondialogfragment = new ActionDialogFragment(browseitemactiondata, flag);
        Object obj = null;
        BrowseItemCell browseitemcell1 = null;
        int i = 0;
        boolean flag1 = false;
        if(DisplayController.getScreenType() != 0)
            if(browseitemcell instanceof BrowseItemGridViewCell)
            {
                flag1 = true;
                obj = browseitemcell.findViewById(0x7f0a003e);
                browseitemcell1 = browseitemcell;
            } else
            {
                i = 17;
                obj = browseitemcell;
                browseitemcell1 = browseitemcell;
            }
        actiondialogfragment.show(getChildFragmentManager(), "", ((View) (obj)), i, flag1, browseitemcell1);
    }

    protected void checkStack()
    {
        int i = popInvalidatedPages();
        if(!pages.empty()) goto _L2; else goto _L1
_L1:
        invalidatePage();
_L4:
        return;
_L2:
        if(i > 0)
            notifyPageUpdated();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void clearStack()
    {
        for(; !pages.isEmpty(); popPage(false));
    }

    public DataSourcePageFragment createBrowsePage(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourcePageFragment datasourcepagefragment;
        if(pageFactory != null)
            datasourcepagefragment = pageFactory.createBrowsePage(scibrowsedatasource);
        else
            datasourcepagefragment = null;
        return datasourcepagefragment;
    }

    public void displayBrowseStack(SCIBrowseStackManager scibrowsestackmanager)
    {
        if(!pages.empty() && (pages.peek() instanceof Browseable))
        {
            ((Browseable)pages.peek()).displayBrowseStack(scibrowsestackmanager);
        } else
        {
            BrowseStackPickerFragment browsestackpickerfragment = new BrowseStackPickerFragment(scibrowsestackmanager);
            browsestackpickerfragment.setPageFactory(this);
            pushPage(browsestackpickerfragment, false);
        }
    }

    public ActionData getPageActions()
    {
        ActionData actiondata;
        if(!pages.empty())
            actiondata = ((PageFragment)pages.peek()).getPageActions();
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
        String s;
        if(!pages.empty())
            s = ((PageFragment)pages.peek()).getTitle();
        else
            s = getTitle();
        return s;
    }

    public PageFragment getTopPage()
    {
        PageFragment pagefragment;
        if(pages.size() > 0)
            pagefragment = (PageFragment)pages.peek();
        else
            pagefragment = null;
        return pagefragment;
    }

    public boolean isAtBrowseRoot()
    {
        boolean flag = true;
        if(pages.size() != flag)
            flag = false;
        return flag;
    }

    public boolean isBrowseEmpty()
    {
        boolean flag;
        if(pages.size() == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isGone()
    {
        return pages.empty();
    }

    public boolean onBackPressed()
    {
        if(pages.empty()) goto _L2; else goto _L1
_L1:
        if(!((PageFragment)pages.peek()).onBackPressed()) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L4:
        if(((PageFragment)pages.peek()).getBackNagivation() == com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_NOWPLAYING || ((PageFragment)pages.peek()).getBackNagivation() == com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_QUEUE)
        {
            getSonosActivity().showNowPlaying();
            if(((PageFragment)pages.peek()).getBackNagivation() == com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_QUEUE)
                getSonosActivity().showQueue();
            if(pages.size() > 1)
            {
                popPage(true);
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
        }
        if(pages.size() > 1)
        {
            popPage(true);
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        rootView = (ViewGroup)layoutinflater.inflate(layoutRes, null);
        return rootView;
    }

    public void onDestroyView()
    {
        onDestroyView();
        rootView = null;
    }

    public void onItemClick(BrowseItemCell browseitemcell)
    {
        SCIBrowseItem scibrowseitem = browseitemcell.getBrowseItem();
        if(scibrowseitem == null) goto _L2; else goto _L1
_L1:
        BrowseItemActionData browseitemactiondata;
        boolean flag;
        browseitemactiondata = new BrowseItemActionData(scibrowseitem, sclib.createDefaultSCIActionFilter(getActionFilter(false)));
        flag = browseitemactiondata.containsCategory(sclibConstants.SC_ACTION_CATEGORY_EDIT);
        if(!scibrowseitem.canPush() || flag) goto _L4; else goto _L3
_L3:
        pushItem(scibrowseitem);
_L2:
        return;
_L4:
        boolean flag1 = false;
        if((getTopPage() instanceof DataSourcePageFragment) && ((DataSourcePageFragment)getTopPage()).getSCUri().equals(sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Favorites)))
            flag1 = true;
        if(browseitemactiondata.size() == 1 && !flag && (flag1 || scibrowseitem.getMoreMenuDataSource() == null && !BrowseUtils.isPlayAction((ActionItem)browseitemactiondata.getActions().get(0))))
        {
            ActionItem actionitem = (ActionItem)browseitemactiondata.getActions().get(0);
            SLog.e(LOG_TAG, (new StringBuilder()).append("Executing Item: ").append(actionitem).toString());
            actionitem.perform();
        } else
        if(browseitemactiondata.size() > 0 || browseitemactiondata.getExtendedActionData() != null)
            showActionList(browseitemcell, browseitemactiondata, flag);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean onItemLongClick(BrowseItemCell browseitemcell)
    {
        boolean flag;
        SCIBrowseItem scibrowseitem;
        flag = true;
        scibrowseitem = browseitemcell.getBrowseItem();
        if(scibrowseitem == null) goto _L2; else goto _L1
_L1:
        String s = getActionFilter(flag);
        boolean flag1;
        BrowseItemActionData browseitemactiondata;
        if(s == sclib.SC_ACTION_FILTERNAME_EDIT)
            flag1 = flag;
        else
            flag1 = false;
        browseitemactiondata = new BrowseItemActionData(scibrowseitem, sclib.createDefaultSCIActionFilter(s));
        if(browseitemactiondata.size() <= 0) goto _L2; else goto _L3
_L3:
        showActionList(browseitemcell, browseitemactiondata, flag1);
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void onPageInvalidated(PageFragment pagefragment)
    {
        checkStack();
    }

    public void onPageUpdated(PageFragment pagefragment)
    {
        updateHeaderBar();
        notifyPageUpdated();
    }

    public void onStackUpdated()
    {
        updateHeaderBar();
    }

    public void onSubscribeEventSinks()
    {
        onSubscribeEventSinks();
        checkStack();
    }

    public void popBrowseToRoot()
    {
        for(; pages.size() > 1; popPage(true));
    }

    protected int popInvalidatedPages()
    {
        int i;
        for(i = 0; !pages.empty() && ((PageFragment)pages.peek()).isGone(); i++)
            popPage(true);

        return i;
    }

    protected void popPage(boolean flag)
    {
        PageFragment pagefragment = (PageFragment)pages.pop();
        FragmentTransaction fragmenttransaction = getChildFragmentManager().beginTransaction();
        if(flag && pagefragment.getView() != null && pagefragment.getView().isShown())
            fragmenttransaction.setCustomAnimations(0x7f040000, 0x7f040017);
        fragmenttransaction.remove(pagefragment);
        if(!pages.isEmpty())
            fragmenttransaction.show((Fragment)pages.peek());
        fragmenttransaction.commitAllowingStateLoss();
        pagefragment.removePageFragmentListener(this);
        onStackUpdated();
    }

    public void popPages(int i)
    {
        if(!pages.empty() && (pages.peek() instanceof Browseable))
        {
            ((Browseable)pages.peek()).popPages(i);
        } else
        {
            int j = 0;
            while(j < i) 
            {
                popPage(true);
                j++;
            }
        }
    }

    public void pushItem(SCIBrowseItem scibrowseitem)
    {
        pushPage(pageFactory.createBrowsePage(LibraryUtils.createBrowseDataSource(scibrowseitem)), false, false);
    }

    public final void pushPage(PageFragment pagefragment, boolean flag)
    {
        if(!(pagefragment instanceof DataSourcePageFragment) || pages.size() <= 0 || !(pages.peek() instanceof DataSourcePageFragment) || !((DataSourcePageFragment)pagefragment).getSCUri().equals(((DataSourcePageFragment)pages.peek()).getSCUri()))
        {
            pagefragment.addPageFragmentListener(this);
            FragmentTransaction fragmenttransaction = getChildFragmentManager().beginTransaction();
            if(!flag && (pages.size() > 0 || forceAnimation))
                fragmenttransaction.setCustomAnimations(0x7f040016, 0x7f040000);
            fragmenttransaction.add(0x7f0a002f, pagefragment);
            if(pages.size() > 0)
            {
                fragmenttransaction.hide((Fragment)pages.peek());
                pagefragment.pageHeaderController.setBackOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        onBackPressed();
                    }

                    final BrowseFlipperPageFragment this$0;

            
            {
                this$0 = BrowseFlipperPageFragment.this;
                Object();
            }
                }
);
            }
            fragmenttransaction.commitAllowingStateLoss();
            pages.push(pagefragment);
            updateHeaderBar();
            onStackUpdated();
        }
    }

    public void pushPage(DataSourcePageFragment datasourcepagefragment, boolean flag, boolean flag1)
    {
        if(flag)
            clearStack();
        datasourcepagefragment.setOnItemClickListener(this);
        pushPage(((PageFragment) (datasourcepagefragment)), flag1);
        disableAnimations = false;
    }

    public void pushTextPane(String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata, com.sonos.acr.util.NavigationUtils.BackNavigationRouting backnavigationrouting)
    {
        boolean flag = true;
        if(!pages.empty() && (pages.peek() instanceof TextPanePageFragment))
            popPage(flag);
        if(backnavigationrouting != com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE)
            popNPInfoviewPages();
        TextPanePageFragment textpanepagefragment = new TextPanePageFragment(s, s1, sciinfoviewtextpanemetadata);
        textpanepagefragment.setBackNavigation(backnavigationrouting);
        if(backnavigationrouting == com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE)
            flag = false;
        pushPage(textpanepagefragment, flag);
    }

    public void pushURI(String s, String s1, boolean flag)
    {
        pushURI(s, s1, flag, com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE);
    }

    public boolean pushURI(String s, String s1, boolean flag, com.sonos.acr.util.NavigationUtils.BackNavigationRouting backnavigationrouting)
    {
        boolean flag1 = true;
        if(backnavigationrouting != com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE)
            popNPInfoviewPages();
        if(!pages.empty() && (pages.peek() instanceof Browseable))
        {
            ((Browseable)pages.peek()).pushURI(s, s1, flag);
            ((PageFragment)pages.peek()).setBackNavigation(backnavigationrouting);
        } else
        {
            if(flag)
                clearStack();
            SCIBrowseDataSource scibrowsedatasource = LibraryUtils.createBrowseDataSource(s, s1);
            if(scibrowsedatasource != null && !scibrowsedatasource.isGone())
            {
                DataSourcePageFragment datasourcepagefragment = pageFactory.createBrowsePage(scibrowsedatasource);
                datasourcepagefragment.setBackNavigation(backnavigationrouting);
                boolean flag2;
                if(backnavigationrouting != com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE)
                    flag2 = flag1;
                else
                    flag2 = false;
                pushPage(datasourcepagefragment, false, flag2);
            } else
            {
                flag1 = false;
            }
        }
        return flag1;
    }

    public boolean setBrowseRoot(String s, String s1)
    {
        return pushURI(s, s1, true, com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE);
    }

    public void setForceAnimation(boolean flag)
    {
        forceAnimation = flag;
    }

    public void setPageFactory(PageFactory pagefactory)
    {
        pageFactory = pagefactory;
    }

    public void showPicker(final SCIBrowseDataSource root, String s)
    {
        PickerFragment pickerfragment = new PickerFragment() {

            public void onViewCreated(View view, Bundle bundle)
            {
                onViewCreated(view, bundle);
                pushPage(pageFactory.createBrowsePage(root), false, false);
            }

            final BrowseFlipperPageFragment this$0;
            final SCIBrowseDataSource val$root;

            
            {
                this$0 = BrowseFlipperPageFragment.this;
                root = scibrowsedatasource;
                PickerFragment();
            }
        }
;
        pickerfragment.setPageFactory(pageFactory);
        pushPage(pickerfragment, false);
    }

    public boolean topPageBacksToNowPlaying()
    {
        boolean flag;
        if(!pages.empty() && ((PageFragment)pages.peek()).getBackNagivation() != com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean forceAnimation;
    protected int layoutRes;
    protected PageFactory pageFactory;
    protected final Stack pages;
    protected ViewGroup rootView;
}
