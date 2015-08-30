// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.os.Bundle;
import java.util.*;

// Referenced classes of package android.support.v7.media:
//            MediaRouteDescriptor

public final class MediaRouteProviderDescriptor
{
    public static final class Builder
    {

        public Builder addRoute(MediaRouteDescriptor mediaroutedescriptor)
        {
            if(mediaroutedescriptor == null)
                throw new IllegalArgumentException("route must not be null");
            if(mRoutes == null)
                mRoutes = new ArrayList();
            else
            if(mRoutes.contains(mediaroutedescriptor))
                throw new IllegalArgumentException("route descriptor already added");
            mRoutes.add(mediaroutedescriptor);
            return this;
        }

        public Builder addRoutes(Collection collection)
        {
            if(collection == null)
                throw new IllegalArgumentException("routes must not be null");
            if(!collection.isEmpty())
            {
                for(Iterator iterator = collection.iterator(); iterator.hasNext(); addRoute((MediaRouteDescriptor)iterator.next()));
            }
            return this;
        }

        public MediaRouteProviderDescriptor build()
        {
            if(mRoutes != null)
            {
                int i = mRoutes.size();
                ArrayList arraylist = new ArrayList(i);
                for(int j = 0; j < i; j++)
                    arraylist.add(((MediaRouteDescriptor)mRoutes.get(j)).asBundle());

                mBundle.putParcelableArrayList("routes", arraylist);
            }
            return new MediaRouteProviderDescriptor(mBundle, mRoutes);
        }

        private final Bundle mBundle;
        private ArrayList mRoutes;

        public Builder()
        {
            mBundle = new Bundle();
        }

        public Builder(MediaRouteProviderDescriptor mediarouteproviderdescriptor)
        {
            if(mediarouteproviderdescriptor == null)
                throw new IllegalArgumentException("descriptor must not be null");
            mBundle = new Bundle(mediarouteproviderdescriptor.mBundle);
            mediarouteproviderdescriptor.ensureRoutes();
            if(!mediarouteproviderdescriptor.mRoutes.isEmpty())
                mRoutes = new ArrayList(mediarouteproviderdescriptor.mRoutes);
        }
    }


    private MediaRouteProviderDescriptor(Bundle bundle, List list)
    {
        mBundle = bundle;
        mRoutes = list;
    }


    private void ensureRoutes()
    {
        if(mRoutes == null)
        {
            ArrayList arraylist = mBundle.getParcelableArrayList("routes");
            if(arraylist == null || arraylist.isEmpty())
            {
                mRoutes = Collections.emptyList();
            } else
            {
                int i = arraylist.size();
                mRoutes = new ArrayList(i);
                int j = 0;
                while(j < i) 
                {
                    mRoutes.add(MediaRouteDescriptor.fromBundle((Bundle)arraylist.get(j)));
                    j++;
                }
            }
        }
    }

    public static MediaRouteProviderDescriptor fromBundle(Bundle bundle)
    {
        MediaRouteProviderDescriptor mediarouteproviderdescriptor;
        if(bundle != null)
            mediarouteproviderdescriptor = new MediaRouteProviderDescriptor(bundle, null);
        else
            mediarouteproviderdescriptor = null;
        return mediarouteproviderdescriptor;
    }

    public Bundle asBundle()
    {
        return mBundle;
    }

    public List getRoutes()
    {
        ensureRoutes();
        return mRoutes;
    }

    public boolean isValid()
    {
        int i;
        int j;
        ensureRoutes();
        i = mRoutes.size();
        j = 0;
_L3:
        MediaRouteDescriptor mediaroutedescriptor;
        if(j >= i)
            break MISSING_BLOCK_LABEL_59;
        mediaroutedescriptor = (MediaRouteDescriptor)mRoutes.get(j);
        if(mediaroutedescriptor != null && mediaroutedescriptor.isValid()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        j++;
          goto _L3
        flag = true;
          goto _L4
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("MediaRouteProviderDescriptor{ ");
        stringbuilder.append("routes=").append(Arrays.toString(getRoutes().toArray()));
        stringbuilder.append(", isValid=").append(isValid());
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    private static final String KEY_ROUTES = "routes";
    private final Bundle mBundle;
    private List mRoutes;



}
