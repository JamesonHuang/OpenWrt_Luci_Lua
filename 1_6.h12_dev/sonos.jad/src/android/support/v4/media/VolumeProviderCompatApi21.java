// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.media.VolumeProvider;

class VolumeProviderCompatApi21
{
    public static interface Delegate
    {

        public abstract void onAdjustVolume(int i);

        public abstract void onSetVolumeTo(int i);
    }


    VolumeProviderCompatApi21()
    {
    }

    public static Object createVolumeProvider(final int final_i, int i, int j, Delegate delegate1)
    {
        return new VolumeProvider(i, j, delegate1) {

            public void onAdjustVolume(int k)
            {
                delegate.onAdjustVolume(k);
            }

            public void onSetVolumeTo(int k)
            {
                delegate.onSetVolumeTo(k);
            }

            final Delegate val$delegate;

            
            {
                delegate = delegate1;
                super(final_i, j, k);
            }
        }
;
    }

    public static void setCurrentVolume(Object obj, int i)
    {
        ((VolumeProvider)obj).setCurrentVolume(i);
    }
}
