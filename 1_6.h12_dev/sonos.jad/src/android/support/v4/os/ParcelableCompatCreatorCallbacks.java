// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.Parcel;

public interface ParcelableCompatCreatorCallbacks
{

    public abstract Object createFromParcel(Parcel parcel, ClassLoader classloader);

    public abstract Object[] newArray(int i);
}
