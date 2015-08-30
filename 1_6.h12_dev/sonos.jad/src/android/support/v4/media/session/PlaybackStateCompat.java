// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.os.*;
import android.text.TextUtils;

// Referenced classes of package android.support.v4.media.session:
//            PlaybackStateCompatApi21

public final class PlaybackStateCompat
    implements Parcelable
{
    public static final class Builder
    {

        public PlaybackStateCompat build()
        {
            return new PlaybackStateCompat(mState, mPosition, mBufferedPosition, mRate, mActions, mErrorMessage, mUpdateTime);
        }

        public Builder setActions(long l)
        {
            mActions = l;
            return this;
        }

        public Builder setBufferedPosition(long l)
        {
            mBufferedPosition = l;
            return this;
        }

        public Builder setErrorMessage(CharSequence charsequence)
        {
            mErrorMessage = charsequence;
            return this;
        }

        public Builder setState(int i, long l, float f)
        {
            return setState(i, l, f, SystemClock.elapsedRealtime());
        }

        public Builder setState(int i, long l, float f, long l1)
        {
            mState = i;
            mPosition = l;
            mUpdateTime = l1;
            mRate = f;
            return this;
        }

        private long mActions;
        private long mBufferedPosition;
        private CharSequence mErrorMessage;
        private long mPosition;
        private float mRate;
        private int mState;
        private long mUpdateTime;

        public Builder()
        {
        }

        public Builder(PlaybackStateCompat playbackstatecompat)
        {
            mState = playbackstatecompat.mState;
            mPosition = playbackstatecompat.mPosition;
            mRate = playbackstatecompat.mSpeed;
            mUpdateTime = playbackstatecompat.mUpdateTime;
            mBufferedPosition = playbackstatecompat.mBufferedPosition;
            mActions = playbackstatecompat.mActions;
            mErrorMessage = playbackstatecompat.mErrorMessage;
        }
    }

    public static final class CustomAction
        implements Parcelable
    {
        public static final class Builder
        {

            public CustomAction build()
            {
                return new CustomAction(mAction, mName, mIcon, mExtras);
            }

            public Builder setExtras(Bundle bundle)
            {
                mExtras = bundle;
                return this;
            }

            private final String mAction;
            private Bundle mExtras;
            private final int mIcon;
            private final CharSequence mName;

            public Builder(String s, CharSequence charsequence, int i)
            {
                if(TextUtils.isEmpty(s))
                    throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
                if(TextUtils.isEmpty(charsequence))
                    throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
                if(i == 0)
                {
                    throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
                } else
                {
                    mAction = s;
                    mName = charsequence;
                    mIcon = i;
                    return;
                }
            }
        }


        public int describeContents()
        {
            return 0;
        }

        public String getAction()
        {
            return mAction;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public int getIcon()
        {
            return mIcon;
        }

        public CharSequence getName()
        {
            return mName;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Action:mName='").append(mName).append(", mIcon=").append(mIcon).append(", mExtras=").append(mExtras).toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mAction);
            TextUtils.writeToParcel(mName, parcel, i);
            parcel.writeInt(mIcon);
            parcel.writeBundle(mExtras);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public CustomAction createFromParcel(Parcel parcel)
            {
                return new CustomAction(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public CustomAction[] newArray(int i)
            {
                return new CustomAction[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final String mAction;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;


        private CustomAction(Parcel parcel)
        {
            mAction = parcel.readString();
            mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            mIcon = parcel.readInt();
            mExtras = parcel.readBundle();
        }


        private CustomAction(String s, CharSequence charsequence, int i, Bundle bundle)
        {
            mAction = s;
            mName = charsequence;
            mIcon = i;
            mExtras = bundle;
        }

    }


    private PlaybackStateCompat(int i, long l, long l1, float f, long l2, CharSequence charsequence, long l3)
    {
        mState = i;
        mPosition = l;
        mBufferedPosition = l1;
        mSpeed = f;
        mActions = l2;
        mErrorMessage = charsequence;
        mUpdateTime = l3;
    }

    PlaybackStateCompat(int i, long l, long l1, float f, long l2, CharSequence charsequence, long l3, _cls1 _pcls1)
    {
        this(i, l, l1, f, l2, charsequence, l3);
    }

    private PlaybackStateCompat(Parcel parcel)
    {
        mState = parcel.readInt();
        mPosition = parcel.readLong();
        mSpeed = parcel.readFloat();
        mUpdateTime = parcel.readLong();
        mBufferedPosition = parcel.readLong();
        mActions = parcel.readLong();
        mErrorMessage = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }


    public static PlaybackStateCompat fromPlaybackState(Object obj)
    {
        PlaybackStateCompat playbackstatecompat;
        if(obj == null || android.os.Build.VERSION.SDK_INT < 21)
        {
            playbackstatecompat = null;
        } else
        {
            playbackstatecompat = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(obj), PlaybackStateCompatApi21.getPosition(obj), PlaybackStateCompatApi21.getBufferedPosition(obj), PlaybackStateCompatApi21.getPlaybackSpeed(obj), PlaybackStateCompatApi21.getActions(obj), PlaybackStateCompatApi21.getErrorMessage(obj), PlaybackStateCompatApi21.getLastPositionUpdateTime(obj));
            playbackstatecompat.mStateObj = obj;
        }
        return playbackstatecompat;
    }

    public int describeContents()
    {
        return 0;
    }

    public long getActions()
    {
        return mActions;
    }

    public long getBufferedPosition()
    {
        return mBufferedPosition;
    }

    public CharSequence getErrorMessage()
    {
        return mErrorMessage;
    }

    public long getLastPositionUpdateTime()
    {
        return mUpdateTime;
    }

    public float getPlaybackSpeed()
    {
        return mSpeed;
    }

    public Object getPlaybackState()
    {
        Object obj;
        if(mStateObj != null || android.os.Build.VERSION.SDK_INT < 21)
        {
            obj = mStateObj;
        } else
        {
            mStateObj = PlaybackStateCompatApi21.newInstance(mState, mPosition, mBufferedPosition, mSpeed, mActions, mErrorMessage, mUpdateTime);
            obj = mStateObj;
        }
        return obj;
    }

    public long getPosition()
    {
        return mPosition;
    }

    public int getState()
    {
        return mState;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("PlaybackState {");
        stringbuilder.append("state=").append(mState);
        stringbuilder.append(", position=").append(mPosition);
        stringbuilder.append(", buffered position=").append(mBufferedPosition);
        stringbuilder.append(", speed=").append(mSpeed);
        stringbuilder.append(", updated=").append(mUpdateTime);
        stringbuilder.append(", actions=").append(mActions);
        stringbuilder.append(", error=").append(mErrorMessage);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mState);
        parcel.writeLong(mPosition);
        parcel.writeFloat(mSpeed);
        parcel.writeLong(mUpdateTime);
        parcel.writeLong(mBufferedPosition);
        parcel.writeLong(mActions);
        TextUtils.writeToParcel(mErrorMessage, parcel, i);
    }

    public static final long ACTION_FAST_FORWARD = 64L;
    public static final long ACTION_PAUSE = 2L;
    public static final long ACTION_PLAY = 4L;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048L;
    public static final long ACTION_PLAY_PAUSE = 512L;
    public static final long ACTION_REWIND = 8L;
    public static final long ACTION_SEEK_TO = 256L;
    public static final long ACTION_SET_RATING = 128L;
    public static final long ACTION_SKIP_TO_NEXT = 32L;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16L;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;
    public static final long ACTION_STOP = 1L;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PlaybackStateCompat createFromParcel(Parcel parcel)
        {
            return new PlaybackStateCompat(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PlaybackStateCompat[] newArray(int i)
        {
            return new PlaybackStateCompat[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final long PLAYBACK_POSITION_UNKNOWN = -1L;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_STOPPED = 1;
    private final long mActions;
    private final long mBufferedPosition;
    private final CharSequence mErrorMessage;
    private final long mPosition;
    private final float mSpeed;
    private final int mState;
    private Object mStateObj;
    private final long mUpdateTime;








}
