// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;


// Referenced classes of package android.support.v7.app:
//            MediaRouteChooserDialogFragment, MediaRouteControllerDialogFragment

public class MediaRouteDialogFactory
{

    public MediaRouteDialogFactory()
    {
    }

    public static MediaRouteDialogFactory getDefault()
    {
        return sDefault;
    }

    public MediaRouteChooserDialogFragment onCreateChooserDialogFragment()
    {
        return new MediaRouteChooserDialogFragment();
    }

    public MediaRouteControllerDialogFragment onCreateControllerDialogFragment()
    {
        return new MediaRouteControllerDialogFragment();
    }

    private static final MediaRouteDialogFactory sDefault = new MediaRouteDialogFactory();

}
