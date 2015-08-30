// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

// Referenced classes of package android.support.v7.app:
//            MediaRouteControllerDialog

public class MediaRouteControllerDialogFragment extends DialogFragment
{

    public MediaRouteControllerDialogFragment()
    {
        setCancelable(true);
    }

    public MediaRouteControllerDialog onCreateControllerDialog(Context context, Bundle bundle)
    {
        return new MediaRouteControllerDialog(context);
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        return onCreateControllerDialog(getActivity(), bundle);
    }
}
