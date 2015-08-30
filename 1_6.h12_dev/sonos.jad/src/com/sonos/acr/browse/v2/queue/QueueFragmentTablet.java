// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.queue;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.AbsListView;
import android.widget.TextView;
import com.sonos.acr.SonosFragment;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.util.FragmentHolderDialog;

// Referenced classes of package com.sonos.acr.browse.v2.queue:
//            QueueFragment, SaveQueueFragment

public class QueueFragmentTablet extends QueueFragment
    implements com.sonos.acr.browse.v2.PageFragment.PageFragmentListener
{

    public QueueFragmentTablet()
    {
    }

    protected void dismissSaveQueue()
    {
        if(saveQueueFragment != null)
        {
            holderDialog.dismiss();
            saveQueueFragment = null;
        }
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        onConfigurationChanged(configuration);
        TextView textview = browseInfoText;
        int i = browseInfoText.getPaddingLeft();
        int j;
        if(configuration.orientation == 2)
            j = getResources().getDimensionPixelOffset(0x7f090058);
        else
            j = getResources().getDimensionPixelOffset(0x7f090059);
        textview.setPadding(i, j, browseInfoText.getPaddingRight(), browseInfoText.getPaddingBottom());
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = onCreateThemedView(layoutinflater, viewgroup, bundle);
        TextView textview = browseInfoText;
        int i = browseInfoText.getPaddingLeft();
        int j;
        if(getResources().getConfiguration().orientation == 2)
            j = getResources().getDimensionPixelOffset(0x7f090058);
        else
            j = getResources().getDimensionPixelOffset(0x7f090059);
        textview.setPadding(i, j, browseInfoText.getPaddingRight(), browseInfoText.getPaddingBottom());
        adapterListView.setVerticalFadingEdgeEnabled(true);
        adapterListView.setFadingEdgeLength(getResources().getDimensionPixelSize(0x7f090055));
        return view;
    }

    public void onPageInvalidated(PageFragment pagefragment)
    {
        dismissSaveQueue();
    }

    public void onPageUpdated(PageFragment pagefragment)
    {
    }

    protected void showSaveQueue()
    {
        if(saveQueueFragment == null)
        {
            saveQueueFragment = new SaveQueueFragment(playQueueMgr);
            saveQueueFragment.addPageFragmentListener(this);
            holderDialog = new FragmentHolderDialog(saveQueueFragment) {

                public void onDismiss(DialogInterface dialoginterface)
                {
                    onDismiss(dialoginterface);
                    holderDialog = null;
                    saveQueueFragment = null;
                }

                final QueueFragmentTablet this$0;

            
            {
                this$0 = QueueFragmentTablet.this;
                FragmentHolderDialog(sonosfragment);
            }
            }
;
            holderDialog.show(getActivity().getSupportFragmentManager(), "");
        }
    }

    private FragmentHolderDialog holderDialog;


/*
    static FragmentHolderDialog access$002(QueueFragmentTablet queuefragmenttablet, FragmentHolderDialog fragmentholderdialog)
    {
        queuefragmenttablet.holderDialog = fragmentholderdialog;
        return fragmentholderdialog;
    }

*/
}
