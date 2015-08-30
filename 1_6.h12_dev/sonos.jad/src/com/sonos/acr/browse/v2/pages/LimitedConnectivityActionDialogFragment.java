// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.actions.ActionData;
import com.sonos.acr.browse.v2.actions.ActionSet;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.sclib.SCIActionFilter;
import com.sonos.sclib.SCIBrowseItem;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            ActionDialogFragment, LimitedConnectivityActionListPageFragment

public class LimitedConnectivityActionDialogFragment extends ActionDialogFragment
{

    public LimitedConnectivityActionDialogFragment(ActionData actiondata)
    {
        ActionDialogFragment(actiondata);
    }

    public LimitedConnectivityActionDialogFragment(ActionData actiondata, boolean flag)
    {
        ActionDialogFragment(actiondata, flag);
    }

    public LimitedConnectivityActionDialogFragment(NowPlaying nowplaying)
    {
        ActionDialogFragment(nowplaying);
    }

    public LimitedConnectivityActionDialogFragment(SCIBrowseItem scibrowseitem, SCIActionFilter sciactionfilter)
    {
        ActionDialogFragment(scibrowseitem, sciactionfilter);
    }

    protected PageFragment createActionFragment(ActionData actiondata)
    {
        Object obj;
        if(actiondata instanceof ActionSet)
        {
            obj = new LimitedConnectivityActionListPageFragment((ActionSet)actiondata);
            ((LimitedConnectivityActionListPageFragment) (obj)).setClickListener(this);
        } else
        {
            obj = createActionFragment(actiondata);
        }
        return ((PageFragment) (obj));
    }
}
