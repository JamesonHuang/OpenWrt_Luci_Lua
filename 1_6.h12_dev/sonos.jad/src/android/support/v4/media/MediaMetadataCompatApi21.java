// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.media.Rating;
import java.util.Set;

class MediaMetadataCompatApi21
{
    public static class Builder
    {

        public static Object build(Object obj)
        {
            return ((android.media.MediaMetadata.Builder)obj).build();
        }

        public static Object newInstance()
        {
            return new android.media.MediaMetadata.Builder();
        }

        public static void putBitmap(Object obj, String s, Bitmap bitmap)
        {
            ((android.media.MediaMetadata.Builder)obj).putBitmap(s, bitmap);
        }

        public static void putLong(Object obj, String s, long l)
        {
            ((android.media.MediaMetadata.Builder)obj).putLong(s, l);
        }

        public static void putRating(Object obj, String s, Object obj1)
        {
            ((android.media.MediaMetadata.Builder)obj).putRating(s, (Rating)obj1);
        }

        public static void putString(Object obj, String s, String s1)
        {
            ((android.media.MediaMetadata.Builder)obj).putString(s, s1);
        }

        public static void putText(Object obj, String s, CharSequence charsequence)
        {
            ((android.media.MediaMetadata.Builder)obj).putText(s, charsequence);
        }

        public Builder()
        {
        }
    }


    MediaMetadataCompatApi21()
    {
    }

    public static Bitmap getBitmap(Object obj, String s)
    {
        return ((MediaMetadata)obj).getBitmap(s);
    }

    public static long getLong(Object obj, String s)
    {
        return ((MediaMetadata)obj).getLong(s);
    }

    public static Object getRating(Object obj, String s)
    {
        return ((MediaMetadata)obj).getRating(s);
    }

    public static CharSequence getText(Object obj, String s)
    {
        return ((MediaMetadata)obj).getText(s);
    }

    public static Set keySet(Object obj)
    {
        return ((MediaMetadata)obj).keySet();
    }
}
