// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.os.Bundle;

class RemoteInputCompatBase
{
    public static abstract class RemoteInput
    {
        public static interface Factory
        {

            public abstract RemoteInput build(String s, CharSequence charsequence, CharSequence acharsequence[], boolean flag, Bundle bundle);

            public abstract RemoteInput[] newArray(int i);
        }


        protected abstract boolean getAllowFreeFormInput();

        protected abstract CharSequence[] getChoices();

        protected abstract Bundle getExtras();

        protected abstract CharSequence getLabel();

        protected abstract String getResultKey();

        public RemoteInput()
        {
        }
    }


    RemoteInputCompatBase()
    {
    }
}
