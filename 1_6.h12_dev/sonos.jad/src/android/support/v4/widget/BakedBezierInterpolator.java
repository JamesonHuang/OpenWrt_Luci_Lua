// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.view.animation.Interpolator;

final class BakedBezierInterpolator
    implements Interpolator
{

    private BakedBezierInterpolator()
    {
    }

    public static final BakedBezierInterpolator getInstance()
    {
        return INSTANCE;
    }

    public float getInterpolation(float f)
    {
        float f1 = 1.0F;
        if(f < f1)
            if(f <= 0.0F)
            {
                f1 = 0.0F;
            } else
            {
                int i = Math.min((int)(f * (float)(-1 + VALUES.length)), -2 + VALUES.length);
                float f2 = (f - (float)i * STEP_SIZE) / STEP_SIZE;
                f1 = VALUES[i] + f2 * (VALUES[i + 1] - VALUES[i]);
            }
        return f1;
    }

    private static final BakedBezierInterpolator INSTANCE = new BakedBezierInterpolator();
    private static final float STEP_SIZE;
    private static final float VALUES[];

    static 
    {
        float af[] = new float[101];
        af[0] = 0.0F;
        af[1] = 0.0002F;
        af[2] = 0.0009F;
        af[3] = 0.0019F;
        af[4] = 0.0036F;
        af[5] = 0.0059F;
        af[6] = 0.0086F;
        af[7] = 0.0119F;
        af[8] = 0.0157F;
        af[9] = 0.0209F;
        af[10] = 0.0257F;
        af[11] = 0.0321F;
        af[12] = 0.0392F;
        af[13] = 0.0469F;
        af[14] = 0.0566F;
        af[15] = 0.0656F;
        af[16] = 0.0768F;
        af[17] = 0.0887F;
        af[18] = 0.1033F;
        af[19] = 0.1186F;
        af[20] = 0.1349F;
        af[21] = 0.1519F;
        af[22] = 0.1696F;
        af[23] = 0.1928F;
        af[24] = 0.2121F;
        af[25] = 0.237F;
        af[26] = 0.2627F;
        af[27] = 0.2892F;
        af[28] = 0.3109F;
        af[29] = 0.3386F;
        af[30] = 0.3667F;
        af[31] = 0.3952F;
        af[32] = 0.4241F;
        af[33] = 0.4474F;
        af[34] = 0.4766F;
        af[35] = 0.5F;
        af[36] = 0.5234F;
        af[37] = 0.5468F;
        af[38] = 0.5701F;
        af[39] = 0.5933F;
        af[40] = 0.6134F;
        af[41] = 0.6333F;
        af[42] = 0.6531F;
        af[43] = 0.6698F;
        af[44] = 0.6891F;
        af[45] = 0.7054F;
        af[46] = 0.7214F;
        af[47] = 0.7346F;
        af[48] = 0.7502F;
        af[49] = 0.763F;
        af[50] = 0.7756F;
        af[51] = 0.7879F;
        af[52] = 0.8F;
        af[53] = 0.8107F;
        af[54] = 0.8212F;
        af[55] = 0.8326F;
        af[56] = 0.8415F;
        af[57] = 0.8503F;
        af[58] = 0.8588F;
        af[59] = 0.8672F;
        af[60] = 0.8754F;
        af[61] = 0.8833F;
        af[62] = 0.8911F;
        af[63] = 0.8977F;
        af[64] = 0.9041F;
        af[65] = 0.9113F;
        af[66] = 0.9165F;
        af[67] = 0.9232F;
        af[68] = 0.9281F;
        af[69] = 0.9328F;
        af[70] = 0.9382F;
        af[71] = 0.9434F;
        af[72] = 0.9476F;
        af[73] = 0.9518F;
        af[74] = 0.9557F;
        af[75] = 0.9596F;
        af[76] = 0.9632F;
        af[77] = 0.9662F;
        af[78] = 0.9695F;
        af[79] = 0.9722F;
        af[80] = 0.9753F;
        af[81] = 0.9777F;
        af[82] = 0.9805F;
        af[83] = 0.9826F;
        af[84] = 0.9847F;
        af[85] = 0.9866F;
        af[86] = 0.9884F;
        af[87] = 0.9901F;
        af[88] = 0.9917F;
        af[89] = 0.9931F;
        af[90] = 0.9944F;
        af[91] = 0.9955F;
        af[92] = 0.9964F;
        af[93] = 0.9973F;
        af[94] = 0.9981F;
        af[95] = 0.9986F;
        af[96] = 0.9992F;
        af[97] = 0.9995F;
        af[98] = 0.9998F;
        af[99] = 1.0F;
        af[100] = 1.0F;
        VALUES = af;
        STEP_SIZE = 1.0F / (float)(-1 + VALUES.length);
    }
}
