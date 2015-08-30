// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.imaging;

import android.graphics.Bitmap;
import android.graphics.Color;

// Referenced classes of package com.sonos.acr.imaging:
//            BlurProcessor

public class JavaBlur
    implements BlurProcessor
{

    public JavaBlur()
    {
    }

    public static int[] getColorComponents(int i)
    {
        colors[0] = (0xff0000 & i) >> 16;
        colors[1] = (0xff00 & i) >> 8;
        colors[2] = i & 0xff;
        return colors;
    }

    public void performBlur(Bitmap bitmap, Bitmap bitmap1, float f)
    {
        int i = (int)f;
        if(i >= 1)
        {
            int j = 1 + (i + i);
            int k = bitmap.getWidth();
            int l = bitmap.getHeight();
            int ai[] = new int[k * l];
            bitmap.getPixels(ai, 0, k, 0, 0, k, l);
            int ai1[] = new int[k * l];
            if(k > 1)
            {
                for(int k4 = 0; k4 < l; k4++)
                {
                    int l4 = 0;
                    int i5 = 0;
                    int j5 = 0;
                    int k5 = k4 * k;
                    for(int l5 = 0; l5 < j; l5++)
                    {
                        int i8 = l5 - i;
                        if(i8 < 0)
                            i8 = 0;
                        int j8 = k - 1;
                        if(i8 > j8)
                            i8 = k - 1;
                        int ai7[] = getColorComponents(ai[k5 + i8]);
                        l4 += ai7[0];
                        i5 += ai7[1];
                        j5 += ai7[2];
                    }

                    for(int i6 = 0; i6 < k; i6++)
                    {
                        int j6 = Color.argb(255, l4 / j, i5 / j, j5 / j);
                        ai1[k5 + i6] = j6;
                        int k6 = i6 - i;
                        if(k6 < 0)
                            k6 = 0;
                        int l6 = 1 + (i6 + i);
                        int i7 = k - 1;
                        if(l6 > i7)
                            l6 = k - 1;
                        int ai5[] = getColorComponents(ai[k5 + k6]);
                        int j7 = l4 - ai5[0];
                        int k7 = i5 - ai5[1];
                        int l7 = j5 - ai5[2];
                        int ai6[] = getColorComponents(ai[k5 + l6]);
                        l4 = j7 + ai6[0];
                        i5 = k7 + ai6[1];
                        j5 = l7 + ai6[2];
                    }

                }

            } else
            {
                ai1 = ai;
            }
            if(l > 1)
            {
                for(int i1 = 0; i1 < k; i1++)
                {
                    int j1 = 0;
                    int k1 = 0;
                    int l1 = 0;
                    for(int i2 = 0; i2 < j; i2++)
                    {
                        int i4 = i2 - i;
                        if(i4 < 0)
                            i4 = 0;
                        int j4 = l - 1;
                        if(i4 > j4)
                            i4 = l - 1;
                        int ai4[] = getColorComponents(ai1[i1 + i4 * k]);
                        j1 += ai4[0];
                        k1 += ai4[1];
                        l1 += ai4[2];
                    }

                    for(int j2 = 0; j2 < l; j2++)
                    {
                        int k2 = Color.argb(255, j1 / j, k1 / j, l1 / j);
                        ai[i1 + j2 * k] = k2;
                        int l2 = j2 - i;
                        if(l2 < 0)
                            l2 = 0;
                        int i3 = 1 + (j2 + i);
                        if(i3 > l - 1)
                            i3 = l - 1;
                        int ai2[] = getColorComponents(ai1[i1 + l2 * k]);
                        int j3 = j1 - ai2[0];
                        int k3 = k1 - ai2[1];
                        int l3 = l1 - ai2[2];
                        int ai3[] = getColorComponents(ai1[i1 + i3 * k]);
                        j1 = j3 + ai3[0];
                        k1 = k3 + ai3[1];
                        l1 = l3 + ai3[2];
                    }

                }

            } else
            {
                ai = ai1;
            }
            bitmap1.setPixels(ai, 0, k, 0, 0, k, l);
        }
    }

    public static final String LOG_TAG = com/sonos/acr/imaging/JavaBlur.getSimpleName();
    static int colors[] = new int[3];

}
