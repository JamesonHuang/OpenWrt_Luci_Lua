// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

class NotificationCompatBase
{
    public static abstract class UnreadConversation
    {
        public static interface Factory
        {

            public abstract UnreadConversation build(String as[], RemoteInputCompatBase.RemoteInput remoteinput, PendingIntent pendingintent, PendingIntent pendingintent1, String as1[], long l);
        }


        abstract long getLatestTimestamp();

        abstract String[] getMessages();

        abstract String getParticipant();

        abstract String[] getParticipants();

        abstract PendingIntent getReadPendingIntent();

        abstract RemoteInputCompatBase.RemoteInput getRemoteInput();

        abstract PendingIntent getReplyPendingIntent();

        public UnreadConversation()
        {
        }
    }

    public static abstract class Action
    {
        public static interface Factory
        {

            public abstract Action build(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInputCompatBase.RemoteInput aremoteinput[]);

            public abstract Action[] newArray(int i);
        }


        protected abstract PendingIntent getActionIntent();

        protected abstract Bundle getExtras();

        protected abstract int getIcon();

        protected abstract RemoteInputCompatBase.RemoteInput[] getRemoteInputs();

        protected abstract CharSequence getTitle();

        public Action()
        {
        }
    }


    NotificationCompatBase()
    {
    }
}
