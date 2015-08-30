// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.os.Bundle;
import java.util.Arrays;

class BundleUtil
{

    BundleUtil()
    {
    }

    public static Bundle[] getBundleArrayFromBundle(Bundle bundle, String s)
    {
        android.os.Parcelable aparcelable[] = bundle.getParcelableArray(s);
        Bundle abundle[];
        if((aparcelable instanceof Bundle[]) || aparcelable == null)
        {
            abundle = (Bundle[])(Bundle[])aparcelable;
        } else
        {
            abundle = (Bundle[])Arrays.copyOf(aparcelable, aparcelable.length, [Landroid/os/Bundle;);
            bundle.putParcelableArray(s, abundle);
        }
        return abundle;
    }
}
