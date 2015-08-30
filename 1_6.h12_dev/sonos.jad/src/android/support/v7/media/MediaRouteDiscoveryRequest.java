// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.os.Bundle;

// Referenced classes of package android.support.v7.media:
//            MediaRouteSelector

public final class MediaRouteDiscoveryRequest
{

    private MediaRouteDiscoveryRequest(Bundle bundle)
    {
        mBundle = bundle;
    }

    public MediaRouteDiscoveryRequest(MediaRouteSelector mediarouteselector, boolean flag)
    {
        if(mediarouteselector == null)
        {
            throw new IllegalArgumentException("selector must not be null");
        } else
        {
            mBundle = new Bundle();
            mSelector = mediarouteselector;
            mBundle.putBundle("selector", mediarouteselector.asBundle());
            mBundle.putBoolean("activeScan", flag);
            return;
        }
    }

    private void ensureSelector()
    {
        if(mSelector == null)
        {
            mSelector = MediaRouteSelector.fromBundle(mBundle.getBundle("selector"));
            if(mSelector == null)
                mSelector = MediaRouteSelector.EMPTY;
        }
    }

    public static MediaRouteDiscoveryRequest fromBundle(Bundle bundle)
    {
        MediaRouteDiscoveryRequest mediaroutediscoveryrequest;
        if(bundle != null)
            mediaroutediscoveryrequest = new MediaRouteDiscoveryRequest(bundle);
        else
            mediaroutediscoveryrequest = null;
        return mediaroutediscoveryrequest;
    }

    public Bundle asBundle()
    {
        return mBundle;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MediaRouteDiscoveryRequest)
        {
            MediaRouteDiscoveryRequest mediaroutediscoveryrequest = (MediaRouteDiscoveryRequest)obj;
            if(getSelector().equals(mediaroutediscoveryrequest.getSelector()) && isActiveScan() == mediaroutediscoveryrequest.isActiveScan())
                flag = true;
        }
        return flag;
    }

    public MediaRouteSelector getSelector()
    {
        ensureSelector();
        return mSelector;
    }

    public int hashCode()
    {
        int i = getSelector().hashCode();
        boolean flag;
        if(isActiveScan())
            flag = true;
        else
            flag = false;
        return flag ^ i;
    }

    public boolean isActiveScan()
    {
        return mBundle.getBoolean("activeScan");
    }

    public boolean isValid()
    {
        ensureSelector();
        return mSelector.isValid();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("DiscoveryRequest{ selector=").append(getSelector());
        stringbuilder.append(", activeScan=").append(isActiveScan());
        stringbuilder.append(", isValid=").append(isValid());
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    private static final String KEY_ACTIVE_SCAN = "activeScan";
    private static final String KEY_SELECTOR = "selector";
    private final Bundle mBundle;
    private MediaRouteSelector mSelector;
}
