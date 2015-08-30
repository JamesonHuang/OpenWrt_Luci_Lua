// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.TimeUtils;

public final class MediaItemStatus
{
    public static final class Builder
    {

        public MediaItemStatus build()
        {
            return new MediaItemStatus(mBundle);
        }

        public Builder setContentDuration(long l)
        {
            mBundle.putLong("contentDuration", l);
            return this;
        }

        public Builder setContentPosition(long l)
        {
            mBundle.putLong("contentPosition", l);
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mBundle.putBundle("extras", bundle);
            return this;
        }

        public Builder setPlaybackState(int i)
        {
            mBundle.putInt("playbackState", i);
            return this;
        }

        public Builder setTimestamp(long l)
        {
            mBundle.putLong("timestamp", l);
            return this;
        }

        private final Bundle mBundle;

        public Builder(int i)
        {
            mBundle = new Bundle();
            setTimestamp(SystemClock.elapsedRealtime());
            setPlaybackState(i);
        }

        public Builder(MediaItemStatus mediaitemstatus)
        {
            if(mediaitemstatus == null)
            {
                throw new IllegalArgumentException("status must not be null");
            } else
            {
                mBundle = new Bundle(mediaitemstatus.mBundle);
                return;
            }
        }
    }


    private MediaItemStatus(Bundle bundle)
    {
        mBundle = bundle;
    }


    public static MediaItemStatus fromBundle(Bundle bundle)
    {
        MediaItemStatus mediaitemstatus;
        if(bundle != null)
            mediaitemstatus = new MediaItemStatus(bundle);
        else
            mediaitemstatus = null;
        return mediaitemstatus;
    }

    private static String playbackStateToString(int i)
    {
        i;
        JVM INSTR tableswitch 0 7: default 48
    //                   0 55
    //                   1 67
    //                   2 73
    //                   3 61
    //                   4 79
    //                   5 85
    //                   6 91
    //                   7 97;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        String s = Integer.toString(i);
_L11:
        return s;
_L2:
        s = "pending";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "buffering";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "playing";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "paused";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "finished";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "canceled";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "invalidated";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "error";
        if(true) goto _L11; else goto _L10
_L10:
    }

    public Bundle asBundle()
    {
        return mBundle;
    }

    public long getContentDuration()
    {
        return mBundle.getLong("contentDuration", -1L);
    }

    public long getContentPosition()
    {
        return mBundle.getLong("contentPosition", -1L);
    }

    public Bundle getExtras()
    {
        return mBundle.getBundle("extras");
    }

    public int getPlaybackState()
    {
        return mBundle.getInt("playbackState", 7);
    }

    public long getTimestamp()
    {
        return mBundle.getLong("timestamp");
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("MediaItemStatus{ ");
        stringbuilder.append("timestamp=");
        TimeUtils.formatDuration(SystemClock.elapsedRealtime() - getTimestamp(), stringbuilder);
        stringbuilder.append(" ms ago");
        stringbuilder.append(", playbackState=").append(playbackStateToString(getPlaybackState()));
        stringbuilder.append(", contentPosition=").append(getContentPosition());
        stringbuilder.append(", contentDuration=").append(getContentDuration());
        stringbuilder.append(", extras=").append(getExtras());
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public static final String EXTRA_HTTP_RESPONSE_HEADERS = "android.media.status.extra.HTTP_RESPONSE_HEADERS";
    public static final String EXTRA_HTTP_STATUS_CODE = "android.media.status.extra.HTTP_STATUS_CODE";
    private static final String KEY_CONTENT_DURATION = "contentDuration";
    private static final String KEY_CONTENT_POSITION = "contentPosition";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_PLAYBACK_STATE = "playbackState";
    private static final String KEY_TIMESTAMP = "timestamp";
    public static final int PLAYBACK_STATE_BUFFERING = 3;
    public static final int PLAYBACK_STATE_CANCELED = 5;
    public static final int PLAYBACK_STATE_ERROR = 7;
    public static final int PLAYBACK_STATE_FINISHED = 4;
    public static final int PLAYBACK_STATE_INVALIDATED = 6;
    public static final int PLAYBACK_STATE_PAUSED = 2;
    public static final int PLAYBACK_STATE_PENDING = 0;
    public static final int PLAYBACK_STATE_PLAYING = 1;
    private final Bundle mBundle;

}
