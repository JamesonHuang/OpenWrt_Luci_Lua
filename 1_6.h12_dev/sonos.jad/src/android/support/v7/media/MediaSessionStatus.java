// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.media;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.TimeUtils;

public final class MediaSessionStatus
{
    public static final class Builder
    {

        public MediaSessionStatus build()
        {
            return new MediaSessionStatus(mBundle);
        }

        public Builder setExtras(Bundle bundle)
        {
            mBundle.putBundle("extras", bundle);
            return this;
        }

        public Builder setQueuePaused(boolean flag)
        {
            mBundle.putBoolean("queuePaused", flag);
            return this;
        }

        public Builder setSessionState(int i)
        {
            mBundle.putInt("sessionState", i);
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
            setSessionState(i);
        }

        public Builder(MediaSessionStatus mediasessionstatus)
        {
            if(mediasessionstatus == null)
            {
                throw new IllegalArgumentException("status must not be null");
            } else
            {
                mBundle = new Bundle(mediasessionstatus.mBundle);
                return;
            }
        }
    }


    private MediaSessionStatus(Bundle bundle)
    {
        mBundle = bundle;
    }


    public static MediaSessionStatus fromBundle(Bundle bundle)
    {
        MediaSessionStatus mediasessionstatus;
        if(bundle != null)
            mediasessionstatus = new MediaSessionStatus(bundle);
        else
            mediasessionstatus = null;
        return mediasessionstatus;
    }

    private static String sessionStateToString(int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 35
    //                   1 41
    //                   2 47;
           goto _L1 _L2 _L3 _L4
_L1:
        String s = Integer.toString(i);
_L6:
        return s;
_L2:
        s = "active";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "ended";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "invalidated";
        if(true) goto _L6; else goto _L5
_L5:
    }

    public Bundle asBundle()
    {
        return mBundle;
    }

    public Bundle getExtras()
    {
        return mBundle.getBundle("extras");
    }

    public int getSessionState()
    {
        return mBundle.getInt("sessionState", 2);
    }

    public long getTimestamp()
    {
        return mBundle.getLong("timestamp");
    }

    public boolean isQueuePaused()
    {
        return mBundle.getBoolean("queuePaused");
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("MediaSessionStatus{ ");
        stringbuilder.append("timestamp=");
        TimeUtils.formatDuration(SystemClock.elapsedRealtime() - getTimestamp(), stringbuilder);
        stringbuilder.append(" ms ago");
        stringbuilder.append(", sessionState=").append(sessionStateToString(getSessionState()));
        stringbuilder.append(", queuePaused=").append(isQueuePaused());
        stringbuilder.append(", extras=").append(getExtras());
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_QUEUE_PAUSED = "queuePaused";
    private static final String KEY_SESSION_STATE = "sessionState";
    private static final String KEY_TIMESTAMP = "timestamp";
    public static final int SESSION_STATE_ACTIVE = 0;
    public static final int SESSION_STATE_ENDED = 1;
    public static final int SESSION_STATE_INVALIDATED = 2;
    private final Bundle mBundle;

}
