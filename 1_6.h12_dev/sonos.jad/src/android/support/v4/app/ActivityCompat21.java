// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

class ActivityCompat21
{
    private static class SharedElementCallbackImpl extends SharedElementCallback
    {

        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectf)
        {
            return mCallback.onCaptureSharedElementSnapshot(view, matrix, rectf);
        }

        public View onCreateSnapshotView(Context context, Parcelable parcelable)
        {
            return mCallback.onCreateSnapshotView(context, parcelable);
        }

        public void onMapSharedElements(List list, Map map)
        {
            mCallback.onMapSharedElements(list, map);
        }

        public void onRejectSharedElements(List list)
        {
            mCallback.onRejectSharedElements(list);
        }

        public void onSharedElementEnd(List list, List list1, List list2)
        {
            mCallback.onSharedElementEnd(list, list1, list2);
        }

        public void onSharedElementStart(List list, List list1, List list2)
        {
            mCallback.onSharedElementStart(list, list1, list2);
        }

        private SharedElementCallback21 mCallback;

        public SharedElementCallbackImpl(SharedElementCallback21 sharedelementcallback21)
        {
            mCallback = sharedelementcallback21;
        }
    }

    public static abstract class SharedElementCallback21
    {

        public abstract Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectf);

        public abstract View onCreateSnapshotView(Context context, Parcelable parcelable);

        public abstract void onMapSharedElements(List list, Map map);

        public abstract void onRejectSharedElements(List list);

        public abstract void onSharedElementEnd(List list, List list1, List list2);

        public abstract void onSharedElementStart(List list, List list1, List list2);

        public SharedElementCallback21()
        {
        }
    }


    ActivityCompat21()
    {
    }

    private static SharedElementCallback createCallback(SharedElementCallback21 sharedelementcallback21)
    {
        SharedElementCallbackImpl sharedelementcallbackimpl = null;
        if(sharedelementcallback21 != null)
            sharedelementcallbackimpl = new SharedElementCallbackImpl(sharedelementcallback21);
        return sharedelementcallbackimpl;
    }

    public static void finishAfterTransition(Activity activity)
    {
        activity.finishAfterTransition();
    }

    public static void postponeEnterTransition(Activity activity)
    {
        activity.postponeEnterTransition();
    }

    public static void setEnterSharedElementCallback(Activity activity, SharedElementCallback21 sharedelementcallback21)
    {
        activity.setEnterSharedElementCallback(createCallback(sharedelementcallback21));
    }

    public static void setExitSharedElementCallback(Activity activity, SharedElementCallback21 sharedelementcallback21)
    {
        activity.setExitSharedElementCallback(createCallback(sharedelementcallback21));
    }

    public static void startPostponedEnterTransition(Activity activity)
    {
        activity.startPostponedEnterTransition();
    }
}
