// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.*;

public final class MediaRouteDescriptor
{
    public static final class Builder
    {

        public Builder addControlFilter(IntentFilter intentfilter)
        {
            if(intentfilter == null)
                throw new IllegalArgumentException("filter must not be null");
            if(mControlFilters == null)
                mControlFilters = new ArrayList();
            if(!mControlFilters.contains(intentfilter))
                mControlFilters.add(intentfilter);
            return this;
        }

        public Builder addControlFilters(Collection collection)
        {
            if(collection == null)
                throw new IllegalArgumentException("filters must not be null");
            if(!collection.isEmpty())
            {
                for(Iterator iterator = collection.iterator(); iterator.hasNext(); addControlFilter((IntentFilter)iterator.next()));
            }
            return this;
        }

        public MediaRouteDescriptor build()
        {
            if(mControlFilters != null)
                mBundle.putParcelableArrayList("controlFilters", mControlFilters);
            return new MediaRouteDescriptor(mBundle, mControlFilters);
        }

        public Builder setConnecting(boolean flag)
        {
            mBundle.putBoolean("connecting", flag);
            return this;
        }

        public Builder setDescription(String s)
        {
            mBundle.putString("status", s);
            return this;
        }

        public Builder setEnabled(boolean flag)
        {
            mBundle.putBoolean("enabled", flag);
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mBundle.putBundle("extras", bundle);
            return this;
        }

        public Builder setId(String s)
        {
            mBundle.putString("id", s);
            return this;
        }

        public Builder setName(String s)
        {
            mBundle.putString("name", s);
            return this;
        }

        public Builder setPlaybackStream(int i)
        {
            mBundle.putInt("playbackStream", i);
            return this;
        }

        public Builder setPlaybackType(int i)
        {
            mBundle.putInt("playbackType", i);
            return this;
        }

        public Builder setPresentationDisplayId(int i)
        {
            mBundle.putInt("presentationDisplayId", i);
            return this;
        }

        public Builder setVolume(int i)
        {
            mBundle.putInt("volume", i);
            return this;
        }

        public Builder setVolumeHandling(int i)
        {
            mBundle.putInt("volumeHandling", i);
            return this;
        }

        public Builder setVolumeMax(int i)
        {
            mBundle.putInt("volumeMax", i);
            return this;
        }

        private final Bundle mBundle;
        private ArrayList mControlFilters;

        public Builder(MediaRouteDescriptor mediaroutedescriptor)
        {
            if(mediaroutedescriptor == null)
                throw new IllegalArgumentException("descriptor must not be null");
            mBundle = new Bundle(mediaroutedescriptor.mBundle);
            mediaroutedescriptor.ensureControlFilters();
            if(!mediaroutedescriptor.mControlFilters.isEmpty())
                mControlFilters = new ArrayList(mediaroutedescriptor.mControlFilters);
        }

        public Builder(String s, String s1)
        {
            mBundle = new Bundle();
            setId(s);
            setName(s1);
        }
    }


    private MediaRouteDescriptor(Bundle bundle, List list)
    {
        mBundle = bundle;
        mControlFilters = list;
    }


    private void ensureControlFilters()
    {
        if(mControlFilters == null)
        {
            mControlFilters = mBundle.getParcelableArrayList("controlFilters");
            if(mControlFilters == null)
                mControlFilters = Collections.emptyList();
        }
    }

    public static MediaRouteDescriptor fromBundle(Bundle bundle)
    {
        MediaRouteDescriptor mediaroutedescriptor;
        if(bundle != null)
            mediaroutedescriptor = new MediaRouteDescriptor(bundle, null);
        else
            mediaroutedescriptor = null;
        return mediaroutedescriptor;
    }

    public Bundle asBundle()
    {
        return mBundle;
    }

    public List getControlFilters()
    {
        ensureControlFilters();
        return mControlFilters;
    }

    public String getDescription()
    {
        return mBundle.getString("status");
    }

    public Bundle getExtras()
    {
        return mBundle.getBundle("extras");
    }

    public String getId()
    {
        return mBundle.getString("id");
    }

    public String getName()
    {
        return mBundle.getString("name");
    }

    public int getPlaybackStream()
    {
        return mBundle.getInt("playbackStream", -1);
    }

    public int getPlaybackType()
    {
        return mBundle.getInt("playbackType", 1);
    }

    public int getPresentationDisplayId()
    {
        return mBundle.getInt("presentationDisplayId", -1);
    }

    public int getVolume()
    {
        return mBundle.getInt("volume");
    }

    public int getVolumeHandling()
    {
        return mBundle.getInt("volumeHandling", 0);
    }

    public int getVolumeMax()
    {
        return mBundle.getInt("volumeMax");
    }

    public boolean isConnecting()
    {
        return mBundle.getBoolean("connecting", false);
    }

    public boolean isEnabled()
    {
        return mBundle.getBoolean("enabled", true);
    }

    public boolean isValid()
    {
        ensureControlFilters();
        boolean flag;
        if(TextUtils.isEmpty(getId()) || TextUtils.isEmpty(getName()) || mControlFilters.contains(null))
            flag = false;
        else
            flag = true;
        return flag;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("MediaRouteDescriptor{ ");
        stringbuilder.append("id=").append(getId());
        stringbuilder.append(", name=").append(getName());
        stringbuilder.append(", description=").append(getDescription());
        stringbuilder.append(", isEnabled=").append(isEnabled());
        stringbuilder.append(", isConnecting=").append(isConnecting());
        stringbuilder.append(", controlFilters=").append(Arrays.toString(getControlFilters().toArray()));
        stringbuilder.append(", playbackType=").append(getPlaybackType());
        stringbuilder.append(", playbackStream=").append(getPlaybackStream());
        stringbuilder.append(", volume=").append(getVolume());
        stringbuilder.append(", volumeMax=").append(getVolumeMax());
        stringbuilder.append(", volumeHandling=").append(getVolumeHandling());
        stringbuilder.append(", presentationDisplayId=").append(getPresentationDisplayId());
        stringbuilder.append(", extras=").append(getExtras());
        stringbuilder.append(", isValid=").append(isValid());
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    private static final String KEY_CONNECTING = "connecting";
    private static final String KEY_CONTROL_FILTERS = "controlFilters";
    private static final String KEY_DESCRIPTION = "status";
    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PLAYBACK_STREAM = "playbackStream";
    private static final String KEY_PLAYBACK_TYPE = "playbackType";
    private static final String KEY_PRESENTATION_DISPLAY_ID = "presentationDisplayId";
    private static final String KEY_VOLUME = "volume";
    private static final String KEY_VOLUME_HANDLING = "volumeHandling";
    private static final String KEY_VOLUME_MAX = "volumeMax";
    private final Bundle mBundle;
    private List mControlFilters;



}
