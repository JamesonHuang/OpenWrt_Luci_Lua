// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.os.Bundle;
import android.view.View;
import com.sonos.acr.browse.v2.PickerFragment;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            PageFactory

public class BrowseStackPickerFragment extends PickerFragment
{
    public class BrowseStackManagerEventSink extends SCIEventSinkSwigBase
    {

        public void dispatchEvent(SCIObj sciobj, String s)
        {
            SCIBrowseStackManager scibrowsestackmanager = (SCIBrowseStackManager)LibraryUtils.cast(sciobj, com/sonos/sclib/SCIBrowseStackManager);
            if(scibrowsestackmanager == null) goto _L2; else goto _L1
_L1:
            if(!s.equals(sclibConstants.SCIBROWSESTACKMANAGER_ONBROWSEINVALIDATION_EVENT)) goto _L4; else goto _L3
_L3:
            onBrowseInvalidation(scibrowsestackmanager.getNumberOfStackLevelsInvalidated());
_L2:
            return;
_L4:
            if(s.equals(sclibConstants.SCIBROWSESTACKMANAGER_ONSTACKINVALIDATION_EVENT))
                onStackInvalidation();
            if(true) goto _L2; else goto _L5
_L5:
        }

        final BrowseStackPickerFragment this$0;

        public BrowseStackManagerEventSink()
        {
            this$0 = BrowseStackPickerFragment.this;
            SCIEventSinkSwigBase();
        }
    }


    public BrowseStackPickerFragment(SCIBrowseStackManager scibrowsestackmanager)
    {
        eventSink = new BrowseStackManagerEventSink();
        gone = false;
        browseStackManager = scibrowsestackmanager;
    }

    public boolean isGone()
    {
        return gone;
    }

    public void onBrowseInvalidation(int i)
    {
        popPages(i);
    }

    public void onDestroyView()
    {
        onDestroyView();
        browseStackManager.unsubscribe(eventSink);
    }

    public void onStackInvalidation()
    {
        gone = true;
        invalidatePage();
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        onViewCreated(view, bundle);
        browseStackManager.subscribe(eventSink);
        pushPage(pageFactory.createBrowsePage(browseStackManager.getTopDataSource()), true);
    }

    protected void popPage(boolean flag)
    {
        popPage(flag);
        browseStackManager.pop();
    }

    public void pushItem(SCIBrowseItem scibrowseitem)
    {
        pushURI(scibrowseitem.getSCUri(), scibrowseitem.getPrimaryTitle(), false, com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE);
    }

    public boolean pushURI(String s, String s1, boolean flag, com.sonos.acr.util.NavigationUtils.BackNavigationRouting backnavigationrouting)
    {
        browseStackManager.push(s, s1);
        pushPage(pageFactory.createBrowsePage(browseStackManager.getTopDataSource()), flag);
        return true;
    }

    SCIBrowseStackManager browseStackManager;
    BrowseStackManagerEventSink eventSink;
    private boolean gone;
}
