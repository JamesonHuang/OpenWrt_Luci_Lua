// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package android.support.v4.media:
//            MediaMetadataCompatApi21, RatingCompat, MediaDescriptionCompat

public final class MediaMetadataCompat
    implements Parcelable
{
    public static final class Builder
    {

        public MediaMetadataCompat build()
        {
            return new MediaMetadataCompat(mBundle);
        }

        public Builder putBitmap(String s, Bitmap bitmap)
        {
            if(MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 2)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a Bitmap").toString());
            } else
            {
                mBundle.putParcelable(s, bitmap);
                return this;
            }
        }

        public Builder putLong(String s, long l)
        {
            if(MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a long").toString());
            } else
            {
                mBundle.putLong(s, l);
                return this;
            }
        }

        public Builder putRating(String s, RatingCompat ratingcompat)
        {
            if(MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 3)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a Rating").toString());
            } else
            {
                mBundle.putParcelable(s, ratingcompat);
                return this;
            }
        }

        public Builder putString(String s, String s1)
        {
            if(MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a String").toString());
            } else
            {
                mBundle.putCharSequence(s, s1);
                return this;
            }
        }

        public Builder putText(String s, CharSequence charsequence)
        {
            if(MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 1)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a CharSequence").toString());
            } else
            {
                mBundle.putCharSequence(s, charsequence);
                return this;
            }
        }

        private final Bundle mBundle;

        public Builder()
        {
            mBundle = new Bundle();
        }

        public Builder(MediaMetadataCompat mediametadatacompat)
        {
            mBundle = new Bundle(mediametadatacompat.mBundle);
        }
    }


    private MediaMetadataCompat(Bundle bundle)
    {
        mBundle = new Bundle(bundle);
    }


    private MediaMetadataCompat(Parcel parcel)
    {
        mBundle = parcel.readBundle();
    }


    public static MediaMetadataCompat fromMediaMetadata(Object obj)
    {
        if(obj != null && android.os.Build.VERSION.SDK_INT >= 21) goto _L2; else goto _L1
_L1:
        MediaMetadataCompat mediametadatacompat = null;
_L4:
        return mediametadatacompat;
_L2:
        Builder builder;
        builder = new Builder();
        Iterator iterator = MediaMetadataCompatApi21.keySet(obj).iterator();
        do
        {
label0:
            {
                String s;
                Integer integer;
                do
                {
                    if(!iterator.hasNext())
                        break label0;
                    s = (String)iterator.next();
                    integer = (Integer)METADATA_KEYS_TYPE.get(s);
                } while(integer == null);
                switch(integer.intValue())
                {
                case 0: // '\0'
                    builder.putLong(s, MediaMetadataCompatApi21.getLong(obj, s));
                    break;

                case 2: // '\002'
                    builder.putBitmap(s, MediaMetadataCompatApi21.getBitmap(obj, s));
                    break;

                case 3: // '\003'
                    builder.putRating(s, RatingCompat.fromRating(MediaMetadataCompatApi21.getRating(obj, s)));
                    break;

                case 1: // '\001'
                    builder.putText(s, MediaMetadataCompatApi21.getText(obj, s));
                    break;
                }
            }
        } while(true);
        mediametadatacompat = builder.build();
        mediametadatacompat.mMetadataObj = obj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean containsKey(String s)
    {
        return mBundle.containsKey(s);
    }

    public int describeContents()
    {
        return 0;
    }

    public Bitmap getBitmap(String s)
    {
        Bitmap bitmap = null;
        try
        {
            bitmap = (Bitmap)mBundle.getParcelable(s);
        }
        catch(Exception exception)
        {
            Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", exception);
        }
        return bitmap;
    }

    public Bundle getBundle()
    {
        return mBundle;
    }

    public MediaDescriptionCompat getDescription()
    {
        if(mDescription == null) goto _L2; else goto _L1
_L1:
        MediaDescriptionCompat mediadescriptioncompat = mDescription;
_L7:
        return mediadescriptioncompat;
_L2:
        int i1;
        int j1;
        String s = getString("android.media.metadata.MEDIA_ID");
        CharSequence acharsequence[] = new CharSequence[3];
        Bitmap bitmap = null;
        Uri uri = null;
        CharSequence charsequence = getText("android.media.metadata.DISPLAY_TITLE");
        MediaDescriptionCompat.Builder builder;
        String s1;
        Bitmap bitmap1;
        if(!TextUtils.isEmpty(charsequence))
        {
            acharsequence[0] = charsequence;
            acharsequence[1] = getText("android.media.metadata.DISPLAY_SUBTITLE");
            acharsequence[2] = getText("android.media.metadata.DISPLAY_DESCRIPTION");
        } else
        {
            int i = 0;
            int j = 0;
            while(i < acharsequence.length && j < PREFERRED_DESCRIPTION_ORDER.length) 
            {
                String as[] = PREFERRED_DESCRIPTION_ORDER;
                int k = j + 1;
                CharSequence charsequence1 = getText(as[j]);
                if(!TextUtils.isEmpty(charsequence1))
                {
                    int l = i + 1;
                    acharsequence[i] = charsequence1;
                    i = l;
                }
                j = k;
            }
        }
        i1 = 0;
_L8:
        if(i1 >= PREFERRED_BITMAP_ORDER.length) goto _L4; else goto _L3
_L3:
        bitmap1 = getBitmap(PREFERRED_BITMAP_ORDER[i1]);
        if(bitmap1 == null) goto _L6; else goto _L5
_L5:
        bitmap = bitmap1;
_L4:
        j1 = 0;
_L9:
        if(j1 < PREFERRED_URI_ORDER.length)
        {
            s1 = getString(PREFERRED_URI_ORDER[j1]);
            if(TextUtils.isEmpty(s1))
                break MISSING_BLOCK_LABEL_300;
            uri = Uri.parse(s1);
        }
        builder = new MediaDescriptionCompat.Builder();
        builder.setMediaId(s);
        builder.setTitle(acharsequence[0]);
        builder.setSubtitle(acharsequence[1]);
        builder.setDescription(acharsequence[2]);
        builder.setIconBitmap(bitmap);
        builder.setIconUri(uri);
        mDescription = builder.build();
        mediadescriptioncompat = mDescription;
          goto _L7
_L6:
        i1++;
          goto _L8
        j1++;
          goto _L9
    }

    public long getLong(String s)
    {
        return mBundle.getLong(s, 0L);
    }

    public Object getMediaMetadata()
    {
        if(mMetadataObj == null && android.os.Build.VERSION.SDK_INT >= 21) goto _L2; else goto _L1
_L1:
        Object obj = mMetadataObj;
_L4:
        return obj;
_L2:
        Object obj1;
        obj1 = MediaMetadataCompatApi21.Builder.newInstance();
        Iterator iterator = keySet().iterator();
        do
        {
label0:
            {
                String s;
                Integer integer;
                do
                {
                    if(!iterator.hasNext())
                        break label0;
                    s = (String)iterator.next();
                    integer = (Integer)METADATA_KEYS_TYPE.get(s);
                } while(integer == null);
                switch(integer.intValue())
                {
                case 0: // '\0'
                    MediaMetadataCompatApi21.Builder.putLong(obj1, s, getLong(s));
                    break;

                case 2: // '\002'
                    MediaMetadataCompatApi21.Builder.putBitmap(obj1, s, getBitmap(s));
                    break;

                case 3: // '\003'
                    MediaMetadataCompatApi21.Builder.putRating(obj1, s, getRating(s).getRating());
                    break;

                case 1: // '\001'
                    MediaMetadataCompatApi21.Builder.putText(obj1, s, getText(s));
                    break;
                }
            }
        } while(true);
        mMetadataObj = MediaMetadataCompatApi21.Builder.build(obj1);
        obj = mMetadataObj;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public RatingCompat getRating(String s)
    {
        RatingCompat ratingcompat = null;
        try
        {
            ratingcompat = (RatingCompat)mBundle.getParcelable(s);
        }
        catch(Exception exception)
        {
            Log.w("MediaMetadata", "Failed to retrieve a key as Rating.", exception);
        }
        return ratingcompat;
    }

    public String getString(String s)
    {
        CharSequence charsequence = mBundle.getCharSequence(s);
        String s1;
        if(charsequence != null)
            s1 = charsequence.toString();
        else
            s1 = null;
        return s1;
    }

    public CharSequence getText(String s)
    {
        return mBundle.getCharSequence(s);
    }

    public Set keySet()
    {
        return mBundle.keySet();
    }

    public int size()
    {
        return mBundle.size();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeBundle(mBundle);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MediaMetadataCompat createFromParcel(Parcel parcel)
        {
            return new MediaMetadataCompat(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MediaMetadataCompat[] newArray(int i)
        {
            return new MediaMetadataCompat[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final ArrayMap METADATA_KEYS_TYPE;
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    private static final int METADATA_TYPE_BITMAP = 2;
    private static final int METADATA_TYPE_LONG = 0;
    private static final int METADATA_TYPE_RATING = 3;
    private static final int METADATA_TYPE_TEXT = 1;
    private static final String PREFERRED_BITMAP_ORDER[];
    private static final String PREFERRED_DESCRIPTION_ORDER[];
    private static final String PREFERRED_URI_ORDER[];
    private static final String TAG = "MediaMetadata";
    private final Bundle mBundle;
    private MediaDescriptionCompat mDescription;
    private Object mMetadataObj;

    static 
    {
        METADATA_KEYS_TYPE = new ArrayMap();
        METADATA_KEYS_TYPE.put("android.media.metadata.TITLE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.ARTIST", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DURATION", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.AUTHOR", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.WRITER", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.COMPOSER", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.COMPILATION", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DATE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.YEAR", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.GENRE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.TRACK_NUMBER", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.NUM_TRACKS", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISC_NUMBER", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ARTIST", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.ART", Integer.valueOf(2));
        METADATA_KEYS_TYPE.put("android.media.metadata.ART_URI", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART", Integer.valueOf(2));
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART_URI", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.USER_RATING", Integer.valueOf(3));
        METADATA_KEYS_TYPE.put("android.media.metadata.RATING", Integer.valueOf(3));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_TITLE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_SUBTITLE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_DESCRIPTION", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON", Integer.valueOf(2));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON_URI", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_ID", Integer.valueOf(1));
        String as[] = new String[7];
        as[0] = "android.media.metadata.TITLE";
        as[1] = "android.media.metadata.ARTIST";
        as[2] = "android.media.metadata.ALBUM";
        as[3] = "android.media.metadata.ALBUM_ARTIST";
        as[4] = "android.media.metadata.WRITER";
        as[5] = "android.media.metadata.AUTHOR";
        as[6] = "android.media.metadata.COMPOSER";
        PREFERRED_DESCRIPTION_ORDER = as;
        String as1[] = new String[3];
        as1[0] = "android.media.metadata.DISPLAY_ICON";
        as1[1] = "android.media.metadata.ART";
        as1[2] = "android.media.metadata.ALBUM_ART";
        PREFERRED_BITMAP_ORDER = as1;
        String as2[] = new String[3];
        as2[0] = "android.media.metadata.DISPLAY_ICON_URI";
        as2[1] = "android.media.metadata.ART_URI";
        as2[2] = "android.media.metadata.ALBUM_ART_URI";
        PREFERRED_URI_ORDER = as2;
    }


}
