// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.media.session.MediaSession;

class MediaSessionCompatApi22
{

    MediaSessionCompatApi22()
    {
    }

    public static void setRatingType(Object obj, int i)
    {
        ((MediaSession)obj).setRatingType(i);
    }
}
