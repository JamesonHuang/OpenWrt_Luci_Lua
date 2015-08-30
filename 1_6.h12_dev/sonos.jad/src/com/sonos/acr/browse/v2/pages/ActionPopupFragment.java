// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.SonosFragment;
import com.sonos.acr.util.SonosPopupFragment;
import com.sonos.acr.util.ViewUtils;
import com.sonos.acr.view.SonosLinearLayout;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            ActionDialogFragment

public class ActionPopupFragment extends SonosPopupFragment
    implements ActionDialogFragment.ISonosDialogDismissListener, com.sonos.acr.SonosActivity.IOrientationChangedListener
{

    public ActionPopupFragment(Fragment fragment1)
    {
        fragment = fragment1;
    }

    public ActionPopupFragment(ActionDialogFragment actiondialogfragment)
    {
        actiondialogfragment.setOnDismissListener(this);
        actiondialogfragment.setShowsDialog(false);
        fragment = actiondialogfragment;
    }

    public boolean onBackPressed()
    {
        boolean flag;
        if(fragment != null && (fragment instanceof SonosFragment))
            flag = ((SonosFragment)fragment).onBackPressed();
        else
            flag = false;
        return flag;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        if(mainView == null)
        {
            mainView = (SonosLinearLayout)layoutinflater.inflate(0x7f030005, viewgroup, false);
            android.support.v4.app.FragmentActivity fragmentactivity = getActivity();
            if(fragmentactivity != null && (fragmentactivity instanceof SonosActivity))
                ((SonosActivity)fragmentactivity).addOrientationListener(this);
            Point point = ViewUtils.getDisplaySize(getActivity());
            int i = getResources().getDimensionPixelSize(0x7f09001e);
            mainView.setMaxHeight(point.y - i * 2);
        }
        return mainView;
    }

    public void onOrientationChanged(int i)
    {
        unsubscribeFromOrientation();
        dismiss();
    }

    public void onPopupDismissed()
    {
        onPopupDismissed();
        if(selectedView != null)
            selectedView.setSelected(false);
        unsubscribeFromOrientation();
        getChildFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
    }

    public void onSonosDialogDismissed()
    {
        unsubscribeFromOrientation();
        dismiss();
        getChildFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
    }

    public void onStart()
    {
        onStart();
        if(isVisible)
            getChildFragmentManager().beginTransaction().add(0x7f0a0036, fragment).commit();
        if(selectedView != null)
            selectedView.setSelected(true);
    }

    public void onStop()
    {
        onStop();
        dismissAllowingStateLoss();
    }

    public void setSelectedView(View view)
    {
        selectedView = view;
    }

    protected void unsubscribeFromOrientation()
    {
        android.support.v4.app.FragmentActivity fragmentactivity = getActivity();
        if(fragmentactivity != null && (fragmentactivity instanceof SonosActivity))
            ((SonosActivity)fragmentactivity).removeOrientationListener(this);
    }

    private static final String LOG_TAG = com/sonos/acr/browse/v2/pages/ActionPopupFragment.getSimpleName();
    protected Fragment fragment;
    protected SonosLinearLayout mainView;
    private View selectedView;

}
