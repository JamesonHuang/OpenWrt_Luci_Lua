// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.Parcel;

// Referenced classes of package android.support.v4.os:
//            ParcelableCompatCreatorHoneycombMR2Stub, ParcelableCompatCreatorCallbacks

public class ParcelableCompat
{
    static class CompatCreator
        implements android.os.Parcelable.Creator
    {

        public Object createFromParcel(Parcel parcel)
        {
            return mCallbacks.createFromParcel(parcel, null);
        }

        public Object[] newArray(int i)
        {
            return mCallbacks.newArray(i);
        }

        final ParcelableCompatCreatorCallbacks mCallbacks;

        public CompatCreator(ParcelableCompatCreatorCallbacks parcelablecompatcreatorcallbacks)
        {
            mCallbacks = parcelablecompatcreatorcallbacks;
        }
    }


    public ParcelableCompat()
    {
    }

    public static android.os.Parcelable.Creator newCreator(ParcelableCompatCreatorCallbacks parcelablecompatcreatorcallbacks)
    {
        if(android.os.Build.VERSION.SDK_INT >= 13)
            ParcelableCompatCreatorHoneycombMR2Stub.instantiate(parcelablecompatcreatorcallbacks);
        return new CompatCreator(parcelablecompatcreatorcallbacks);
    }
}
