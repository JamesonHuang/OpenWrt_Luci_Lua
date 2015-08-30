// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.*;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCILibrary;

// Referenced classes of package com.sonos.acr:
//            SonosActivity

public class SonosDialogFragment extends DialogFragment
{

    public SonosDialogFragment()
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/SonosDialogFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
        themedAttributeId = 0;
    }

    public SonosDialogFragment(int i)
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/SonosDialogFragment.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
        themedAttributeId = i;
    }

    public Household getHousehold()
    {
        return LibraryUtils.getHousehold();
    }

    public SCILibrary getLibrary()
    {
        return getSonosActivity().getLibrary();
    }

    public SonosActivity getSonosActivity()
    {
        return (SonosActivity)getActivity();
    }

    public boolean onBackPressed()
    {
        return false;
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return null;
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        if(themedAttributeId != 0)
            layoutinflater = LayoutInflater.from(new ContextThemeWrapper(getActivity(), resolveThemeStyle(themedAttributeId)));
        return onCreateThemedView(layoutinflater, viewgroup, bundle);
    }

    public void onHiddenChanged(boolean flag)
    {
        super.onHiddenChanged(flag);
        SLog.i(LOG_TAG, "onHiddenChanged called");
        if(isResumed())
            if(flag)
                onUnsubscribeEventSinks();
            else
                onSubscribeEventSinks();
    }

    public void onPause()
    {
        super.onPause();
        onUnsubscribeEventSinks();
        SLog.i(LOG_TAG, "onPause called");
    }

    public void onResume()
    {
        super.onResume();
        SLog.i(LOG_TAG, "onResume called");
        if(!isHidden())
            onSubscribeEventSinks();
    }

    public void onStart()
    {
        super.onStart();
        SLog.i(LOG_TAG, "onStart called");
    }

    public void onStop()
    {
        super.onStop();
        SLog.i(LOG_TAG, "onStop called");
    }

    public void onSubscribeEventSinks()
    {
        SLog.i(LOG_TAG, "onSubscribeEventSinks called");
    }

    public void onUnsubscribeEventSinks()
    {
        SLog.i(LOG_TAG, "onUnsubscribeEventSinks called");
    }

    public int resolveThemeStyle(int i)
    {
        TypedValue typedvalue = new TypedValue();
        getActivity().getTheme().resolveAttribute(i, typedvalue, true);
        return typedvalue.resourceId;
    }

    private final String LOG_TAG;
    final int themedAttributeId;
}
