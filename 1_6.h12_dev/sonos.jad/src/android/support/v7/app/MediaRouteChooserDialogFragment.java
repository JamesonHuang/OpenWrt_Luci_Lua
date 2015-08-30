// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.media.MediaRouteSelector;

// Referenced classes of package android.support.v7.app:
//            MediaRouteChooserDialog

public class MediaRouteChooserDialogFragment extends DialogFragment
{

    public MediaRouteChooserDialogFragment()
    {
        setCancelable(true);
    }

    private void ensureRouteSelector()
    {
        if(mSelector == null)
        {
            Bundle bundle = getArguments();
            if(bundle != null)
                mSelector = MediaRouteSelector.fromBundle(bundle.getBundle("selector"));
            if(mSelector == null)
                mSelector = MediaRouteSelector.EMPTY;
        }
    }

    public MediaRouteSelector getRouteSelector()
    {
        ensureRouteSelector();
        return mSelector;
    }

    public MediaRouteChooserDialog onCreateChooserDialog(Context context, Bundle bundle)
    {
        return new MediaRouteChooserDialog(context);
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        MediaRouteChooserDialog mediaroutechooserdialog = onCreateChooserDialog(getActivity(), bundle);
        mediaroutechooserdialog.setRouteSelector(getRouteSelector());
        return mediaroutechooserdialog;
    }

    public void setRouteSelector(MediaRouteSelector mediarouteselector)
    {
        if(mediarouteselector == null)
            throw new IllegalArgumentException("selector must not be null");
        ensureRouteSelector();
        if(!mSelector.equals(mediarouteselector))
        {
            mSelector = mediarouteselector;
            Bundle bundle = getArguments();
            if(bundle == null)
                bundle = new Bundle();
            bundle.putBundle("selector", mediarouteselector.asBundle());
            setArguments(bundle);
            MediaRouteChooserDialog mediaroutechooserdialog = (MediaRouteChooserDialog)getDialog();
            if(mediaroutechooserdialog != null)
                mediaroutechooserdialog.setRouteSelector(mediarouteselector);
        }
    }

    private final String ARGUMENT_SELECTOR = "selector";
    private MediaRouteSelector mSelector;
}
