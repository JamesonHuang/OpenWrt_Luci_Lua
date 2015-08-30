// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

// Referenced classes of package android.support.v4.app:
//            RemoteInputCompatApi20, RemoteInputCompatJellybean

public class RemoteInput extends RemoteInputCompatBase.RemoteInput
{
    static class ImplApi20
        implements Impl
    {

        public void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
        {
            RemoteInputCompatApi20.addResultsToIntent(aremoteinput, intent, bundle);
        }

        public Bundle getResultsFromIntent(Intent intent)
        {
            return RemoteInputCompatApi20.getResultsFromIntent(intent);
        }

        ImplApi20()
        {
        }
    }

    static class ImplJellybean
        implements Impl
    {

        public void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
        {
            RemoteInputCompatJellybean.addResultsToIntent(aremoteinput, intent, bundle);
        }

        public Bundle getResultsFromIntent(Intent intent)
        {
            return RemoteInputCompatJellybean.getResultsFromIntent(intent);
        }

        ImplJellybean()
        {
        }
    }

    static class ImplBase
        implements Impl
    {

        public void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
        {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        }

        public Bundle getResultsFromIntent(Intent intent)
        {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
            return null;
        }

        ImplBase()
        {
        }
    }

    static interface Impl
    {

        public abstract void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle);

        public abstract Bundle getResultsFromIntent(Intent intent);
    }

    public static final class Builder
    {

        public Builder addExtras(Bundle bundle)
        {
            if(bundle != null)
                mExtras.putAll(bundle);
            return this;
        }

        public RemoteInput build()
        {
            return new RemoteInput(mResultKey, mLabel, mChoices, mAllowFreeFormInput, mExtras);
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public Builder setAllowFreeFormInput(boolean flag)
        {
            mAllowFreeFormInput = flag;
            return this;
        }

        public Builder setChoices(CharSequence acharsequence[])
        {
            mChoices = acharsequence;
            return this;
        }

        public Builder setLabel(CharSequence charsequence)
        {
            mLabel = charsequence;
            return this;
        }

        private boolean mAllowFreeFormInput;
        private CharSequence mChoices[];
        private Bundle mExtras;
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder(String s)
        {
            mAllowFreeFormInput = true;
            mExtras = new Bundle();
            if(s == null)
            {
                throw new IllegalArgumentException("Result key can't be null");
            } else
            {
                mResultKey = s;
                return;
            }
        }
    }


    RemoteInput(String s, CharSequence charsequence, CharSequence acharsequence[], boolean flag, Bundle bundle)
    {
        mResultKey = s;
        mLabel = charsequence;
        mChoices = acharsequence;
        mAllowFreeFormInput = flag;
        mExtras = bundle;
    }

    public static void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
    {
        IMPL.addResultsToIntent(aremoteinput, intent, bundle);
    }

    public static Bundle getResultsFromIntent(Intent intent)
    {
        return IMPL.getResultsFromIntent(intent);
    }

    public boolean getAllowFreeFormInput()
    {
        return mAllowFreeFormInput;
    }

    public CharSequence[] getChoices()
    {
        return mChoices;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public String getResultKey()
    {
        return mResultKey;
    }

    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    public static final RemoteInputCompatBase.RemoteInput.Factory FACTORY = new RemoteInputCompatBase.RemoteInput.Factory() {

        public RemoteInput build(String s, CharSequence charsequence, CharSequence acharsequence[], boolean flag, Bundle bundle)
        {
            return new RemoteInput(s, charsequence, acharsequence, flag, bundle);
        }

        public volatile RemoteInputCompatBase.RemoteInput build(String s, CharSequence charsequence, CharSequence acharsequence[], boolean flag, Bundle bundle)
        {
            return build(s, charsequence, acharsequence, flag, bundle);
        }

        public RemoteInput[] newArray(int i)
        {
            return new RemoteInput[i];
        }

        public volatile RemoteInputCompatBase.RemoteInput[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final Impl IMPL;
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    private static final String TAG = "RemoteInput";
    private final boolean mAllowFreeFormInput;
    private final CharSequence mChoices[];
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 20)
            IMPL = new ImplApi20();
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new ImplJellybean();
        else
            IMPL = new ImplBase();
    }
}
