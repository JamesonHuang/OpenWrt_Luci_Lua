// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.*;
import com.sonos.acr.SonosFragment;

public class FragmentHolderDialog extends DialogFragment
{

    public FragmentHolderDialog(SonosFragment sonosfragment)
    {
        this(sonosfragment, true);
    }

    public FragmentHolderDialog(SonosFragment sonosfragment, boolean flag)
    {
        childFragment = sonosfragment;
        cancelable = flag;
        super.setStyle(2, 0x1030010);
    }

    public SonosFragment getChildFragment()
    {
        return childFragment;
    }

    protected int getLayoutId()
    {
        return 0x7f030018;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        Dialog dialog = new Dialog(getActivity()) {

            public void onBackPressed()
            {
                if(childFragment != null && !childFragment.onBackPressed())
                    super.onBackPressed();
            }

            public boolean onKeyDown(int i, KeyEvent keyevent)
            {
                boolean flag = true;
                if(i != 24 && i != 25) goto _L2; else goto _L1
_L1:
                getActivity().onKeyDown(i, keyevent);
                getActivity().onKeyUp(i, keyevent);
_L4:
                return flag;
_L2:
                if(!cancelable && i == 4)
                {
                    if(childFragment.onBackPressed())
                        continue; /* Loop/switch isn't completed */
                    dismiss();
                }
                flag = super.onKeyDown(i, keyevent);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final FragmentHolderDialog this$0;

            
            {
                this$0 = FragmentHolderDialog.this;
                super(context);
            }
        }
;
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().requestFeature(1);
        return dialog;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        mainView = layoutinflater.inflate(getLayoutId(), viewgroup, false);
        setShowsDialog(true);
        return mainView;
    }

    public void onStart()
    {
        super.onStart();
        if(!childFragment.isAdded())
        {
            FragmentTransaction fragmenttransaction = getChildFragmentManager().beginTransaction();
            fragmenttransaction.add(0x7f0a0066, childFragment);
            fragmenttransaction.commit();
        }
    }

    private static final String LOG_TAG = "FragmentHolderDialog";
    protected boolean cancelable;
    protected SonosFragment childFragment;
    protected View mainView;
}
