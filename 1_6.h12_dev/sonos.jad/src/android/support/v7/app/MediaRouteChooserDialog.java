// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import java.util.*;

// Referenced classes of package android.support.v7.app:
//            MediaRouterThemeHelper

public class MediaRouteChooserDialog extends Dialog
{
    private static final class RouteComparator
        implements Comparator
    {

        public int compare(android.support.v7.media.MediaRouter.RouteInfo routeinfo, android.support.v7.media.MediaRouter.RouteInfo routeinfo1)
        {
            return routeinfo.getName().compareTo(routeinfo1.getName());
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((android.support.v7.media.MediaRouter.RouteInfo)obj, (android.support.v7.media.MediaRouter.RouteInfo)obj1);
        }

        public static final RouteComparator sInstance = new RouteComparator();


        private RouteComparator()
        {
        }
    }

    private final class MediaRouterCallback extends android.support.v7.media.MediaRouter.Callback
    {

        public void onRouteAdded(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoutes();
        }

        public void onRouteChanged(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoutes();
        }

        public void onRouteRemoved(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            refreshRoutes();
        }

        public void onRouteSelected(MediaRouter mediarouter, android.support.v7.media.MediaRouter.RouteInfo routeinfo)
        {
            dismiss();
        }

        final MediaRouteChooserDialog this$0;

        private MediaRouterCallback()
        {
            this$0 = MediaRouteChooserDialog.this;
            super();
        }

    }

    private final class RouteAdapter extends ArrayAdapter
        implements android.widget.AdapterView.OnItemClickListener
    {

        public boolean areAllItemsEnabled()
        {
            return false;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = view;
            if(view1 == null)
                view1 = mInflater.inflate(android.support.v7.mediarouter.R.layout.mr_media_route_list_item, viewgroup, false);
            android.support.v7.media.MediaRouter.RouteInfo routeinfo = (android.support.v7.media.MediaRouter.RouteInfo)getItem(i);
            TextView textview = (TextView)view1.findViewById(0x1020014);
            TextView textview1 = (TextView)view1.findViewById(0x1020015);
            textview.setText(routeinfo.getName());
            String s = routeinfo.getDescription();
            if(TextUtils.isEmpty(s))
            {
                textview1.setVisibility(8);
                textview1.setText("");
            } else
            {
                textview1.setVisibility(0);
                textview1.setText(s);
            }
            view1.setEnabled(routeinfo.isEnabled());
            return view1;
        }

        public boolean isEnabled(int i)
        {
            return ((android.support.v7.media.MediaRouter.RouteInfo)getItem(i)).isEnabled();
        }

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            android.support.v7.media.MediaRouter.RouteInfo routeinfo = (android.support.v7.media.MediaRouter.RouteInfo)getItem(i);
            if(routeinfo.isEnabled())
            {
                routeinfo.select();
                dismiss();
            }
        }

        private final LayoutInflater mInflater;
        final MediaRouteChooserDialog this$0;

        public RouteAdapter(Context context, List list)
        {
            this$0 = MediaRouteChooserDialog.this;
            super(context, 0, list);
            mInflater = LayoutInflater.from(context);
        }
    }


    public MediaRouteChooserDialog(Context context)
    {
        this(context, 0);
    }

    public MediaRouteChooserDialog(Context context, int i)
    {
        super(MediaRouterThemeHelper.createThemedContext(context, true), i);
        mSelector = MediaRouteSelector.EMPTY;
        mRouter = MediaRouter.getInstance(getContext());
        mCallback = new MediaRouterCallback();
    }

    public MediaRouteSelector getRouteSelector()
    {
        return mSelector;
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mAttachedToWindow = true;
        mRouter.addCallback(mSelector, mCallback, 1);
        refreshRoutes();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().requestFeature(3);
        setContentView(android.support.v7.mediarouter.R.layout.mr_media_route_chooser_dialog);
        setTitle(android.support.v7.mediarouter.R.string.mr_media_route_chooser_title);
        getWindow().setFeatureDrawableResource(3, MediaRouterThemeHelper.getThemeResource(getContext(), android.support.v7.mediarouter.R.attr.mediaRouteOffDrawable));
        mRoutes = new ArrayList();
        mAdapter = new RouteAdapter(getContext(), mRoutes);
        mListView = (ListView)findViewById(android.support.v7.mediarouter.R.id.media_route_list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mAdapter);
        mListView.setEmptyView(findViewById(0x1020004));
    }

    public void onDetachedFromWindow()
    {
        mAttachedToWindow = false;
        mRouter.removeCallback(mCallback);
        super.onDetachedFromWindow();
    }

    public boolean onFilterRoute(android.support.v7.media.MediaRouter.RouteInfo routeinfo)
    {
        boolean flag;
        if(!routeinfo.isDefault() && routeinfo.isEnabled() && routeinfo.matchesSelector(mSelector))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onFilterRoutes(List list)
    {
        int i = list.size();
        do
        {
            int j = i - 1;
            if(i > 0)
            {
                if(!onFilterRoute((android.support.v7.media.MediaRouter.RouteInfo)list.get(j)))
                {
                    list.remove(j);
                    i = j;
                } else
                {
                    i = j;
                }
            } else
            {
                return;
            }
        } while(true);
    }

    public void refreshRoutes()
    {
        if(mAttachedToWindow)
        {
            mRoutes.clear();
            mRoutes.addAll(mRouter.getRoutes());
            onFilterRoutes(mRoutes);
            Collections.sort(mRoutes, RouteComparator.sInstance);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setRouteSelector(MediaRouteSelector mediarouteselector)
    {
        if(mediarouteselector == null)
            throw new IllegalArgumentException("selector must not be null");
        if(!mSelector.equals(mediarouteselector))
        {
            mSelector = mediarouteselector;
            if(mAttachedToWindow)
            {
                mRouter.removeCallback(mCallback);
                mRouter.addCallback(mediarouteselector, mCallback, 1);
            }
            refreshRoutes();
        }
    }

    private RouteAdapter mAdapter;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ListView mListView;
    private final MediaRouter mRouter;
    private ArrayList mRoutes;
    private MediaRouteSelector mSelector;
}
