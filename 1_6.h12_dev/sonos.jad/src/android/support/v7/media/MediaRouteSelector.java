// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.content.IntentFilter;
import android.os.Bundle;
import java.util.*;

public final class MediaRouteSelector
{
    public static final class Builder
    {

        public Builder addControlCategories(Collection collection)
        {
            if(collection == null)
                throw new IllegalArgumentException("categories must not be null");
            if(!collection.isEmpty())
            {
                for(Iterator iterator = collection.iterator(); iterator.hasNext(); addControlCategory((String)iterator.next()));
            }
            return this;
        }

        public Builder addControlCategory(String s)
        {
            if(s == null)
                throw new IllegalArgumentException("category must not be null");
            if(mControlCategories == null)
                mControlCategories = new ArrayList();
            if(!mControlCategories.contains(s))
                mControlCategories.add(s);
            return this;
        }

        public Builder addSelector(MediaRouteSelector mediarouteselector)
        {
            if(mediarouteselector == null)
            {
                throw new IllegalArgumentException("selector must not be null");
            } else
            {
                addControlCategories(mediarouteselector.getControlCategories());
                return this;
            }
        }

        public MediaRouteSelector build()
        {
            MediaRouteSelector mediarouteselector;
            if(mControlCategories == null)
            {
                mediarouteselector = MediaRouteSelector.EMPTY;
            } else
            {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("controlCategories", mControlCategories);
                mediarouteselector = new MediaRouteSelector(bundle, mControlCategories);
            }
            return mediarouteselector;
        }

        private ArrayList mControlCategories;

        public Builder()
        {
        }

        public Builder(MediaRouteSelector mediarouteselector)
        {
            if(mediarouteselector == null)
                throw new IllegalArgumentException("selector must not be null");
            mediarouteselector.ensureControlCategories();
            if(!mediarouteselector.mControlCategories.isEmpty())
                mControlCategories = new ArrayList(mediarouteselector.mControlCategories);
        }
    }


    private MediaRouteSelector(Bundle bundle, List list)
    {
        mBundle = bundle;
        mControlCategories = list;
    }


    private void ensureControlCategories()
    {
        if(mControlCategories == null)
        {
            mControlCategories = mBundle.getStringArrayList("controlCategories");
            if(mControlCategories == null || mControlCategories.isEmpty())
                mControlCategories = Collections.emptyList();
        }
    }

    public static MediaRouteSelector fromBundle(Bundle bundle)
    {
        MediaRouteSelector mediarouteselector;
        if(bundle != null)
            mediarouteselector = new MediaRouteSelector(bundle, null);
        else
            mediarouteselector = null;
        return mediarouteselector;
    }

    public Bundle asBundle()
    {
        return mBundle;
    }

    public boolean contains(MediaRouteSelector mediarouteselector)
    {
        boolean flag;
        if(mediarouteselector != null)
        {
            ensureControlCategories();
            mediarouteselector.ensureControlCategories();
            flag = mControlCategories.containsAll(mediarouteselector.mControlCategories);
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof MediaRouteSelector)
        {
            MediaRouteSelector mediarouteselector = (MediaRouteSelector)obj;
            ensureControlCategories();
            mediarouteselector.ensureControlCategories();
            flag = mControlCategories.equals(mediarouteselector.mControlCategories);
        } else
        {
            flag = false;
        }
        return flag;
    }

    public List getControlCategories()
    {
        ensureControlCategories();
        return mControlCategories;
    }

    public boolean hasControlCategory(String s)
    {
        int i;
        int j;
        if(s == null)
            break MISSING_BLOCK_LABEL_58;
        ensureControlCategories();
        i = mControlCategories.size();
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_58;
        if(!((String)mControlCategories.get(j)).equals(s)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        j++;
          goto _L3
        flag = false;
          goto _L4
    }

    public int hashCode()
    {
        ensureControlCategories();
        return mControlCategories.hashCode();
    }

    public boolean isEmpty()
    {
        ensureControlCategories();
        return mControlCategories.isEmpty();
    }

    public boolean isValid()
    {
        ensureControlCategories();
        boolean flag;
        if(mControlCategories.contains(null))
            flag = false;
        else
            flag = true;
        return flag;
    }

    public boolean matchesControlFilters(List list)
    {
        int i;
        int j;
        int k;
        if(list == null)
            break MISSING_BLOCK_LABEL_105;
        ensureControlCategories();
        i = mControlCategories.size();
        if(i == 0)
            break MISSING_BLOCK_LABEL_105;
        j = list.size();
        k = 0;
_L6:
        if(k >= j) goto _L2; else goto _L1
_L1:
        IntentFilter intentfilter;
        int l;
        intentfilter = (IntentFilter)list.get(k);
        if(intentfilter == null)
            continue; /* Loop/switch isn't completed */
        l = 0;
_L5:
        if(l >= i)
            continue; /* Loop/switch isn't completed */
        if(!intentfilter.hasCategory((String)mControlCategories.get(l))) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L7:
        return flag;
_L4:
        l++;
          goto _L5
        k++;
          goto _L6
_L2:
        flag = false;
          goto _L7
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("MediaRouteSelector{ ");
        stringbuilder.append("controlCategories=").append(Arrays.toString(getControlCategories().toArray()));
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public static final MediaRouteSelector EMPTY = new MediaRouteSelector(new Bundle(), null);
    private static final String KEY_CONTROL_CATEGORIES = "controlCategories";
    private final Bundle mBundle;
    private List mControlCategories;



}
