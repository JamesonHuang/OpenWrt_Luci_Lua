// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            Sampler, RenderScript, RenderScriptThunker, ExceptionThunker

class SamplerThunker extends android.support.v8.renderscript.Sampler
{
    public static class Builder
    {

        public android.support.v8.renderscript.Sampler create()
        {
            mRS.validate();
            SamplerThunker samplerthunker;
            try
            {
                android.renderscript.Sampler.Builder builder = new android.renderscript.Sampler.Builder(mRS.mN);
                builder.setMinification(SamplerThunker.convertValue(mMin));
                builder.setMagnification(SamplerThunker.convertValue(mMag));
                builder.setWrapS(SamplerThunker.convertValue(mWrapS));
                builder.setWrapT(SamplerThunker.convertValue(mWrapT));
                builder.setAnisotropy(mAniso);
                Sampler sampler = builder.create();
                samplerthunker = new SamplerThunker(0, mRS);
                samplerthunker.mMin = mMin;
                samplerthunker.mMag = mMag;
                samplerthunker.mWrapS = mWrapS;
                samplerthunker.mWrapT = mWrapT;
                samplerthunker.mWrapR = mWrapR;
                samplerthunker.mAniso = mAniso;
                samplerthunker.mN = sampler;
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
            return samplerthunker;
        }

        public void setAnisotropy(float f)
        {
            if(f >= 0.0F)
            {
                mAniso = f;
                return;
            } else
            {
                throw new IllegalArgumentException("Invalid value");
            }
        }

        public void setMagnification(Sampler.Value value)
        {
            if(value == Sampler.Value.NEAREST || value == Sampler.Value.LINEAR)
            {
                mMag = value;
                return;
            } else
            {
                throw new IllegalArgumentException("Invalid value");
            }
        }

        public void setMinification(Sampler.Value value)
        {
            if(value == Sampler.Value.NEAREST || value == Sampler.Value.LINEAR || value == Sampler.Value.LINEAR_MIP_LINEAR || value == Sampler.Value.LINEAR_MIP_NEAREST)
            {
                mMin = value;
                return;
            } else
            {
                throw new IllegalArgumentException("Invalid value");
            }
        }

        public void setWrapS(Sampler.Value value)
        {
            if(value == Sampler.Value.WRAP || value == Sampler.Value.CLAMP || value == Sampler.Value.MIRRORED_REPEAT)
            {
                mWrapS = value;
                return;
            } else
            {
                throw new IllegalArgumentException("Invalid value");
            }
        }

        public void setWrapT(Sampler.Value value)
        {
            if(value == Sampler.Value.WRAP || value == Sampler.Value.CLAMP || value == Sampler.Value.MIRRORED_REPEAT)
            {
                mWrapT = value;
                return;
            } else
            {
                throw new IllegalArgumentException("Invalid value");
            }
        }

        float mAniso;
        Sampler.Value mMag;
        Sampler.Value mMin;
        RenderScriptThunker mRS;
        Sampler.Value mWrapR;
        Sampler.Value mWrapS;
        Sampler.Value mWrapT;

        public Builder(RenderScriptThunker renderscriptthunker)
        {
            mRS = renderscriptthunker;
            mMin = Sampler.Value.NEAREST;
            mMag = Sampler.Value.NEAREST;
            mWrapS = Sampler.Value.WRAP;
            mWrapT = Sampler.Value.WRAP;
            mWrapR = Sampler.Value.WRAP;
        }
    }


    protected SamplerThunker(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    static android.renderscript.Sampler.Value convertValue(Sampler.Value value)
    {
        class _cls1
        {

            static final int $SwitchMap$android$support$v8$renderscript$Sampler$Value[];

            static 
            {
                $SwitchMap$android$support$v8$renderscript$Sampler$Value = new int[Sampler.Value.values().length];
                NoSuchFieldError nosuchfielderror6;
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Sampler$Value[Sampler.Value.NEAREST.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Sampler$Value[Sampler.Value.LINEAR.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Sampler$Value[Sampler.Value.LINEAR_MIP_LINEAR.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Sampler$Value[Sampler.Value.LINEAR_MIP_NEAREST.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Sampler$Value[Sampler.Value.WRAP.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Sampler$Value[Sampler.Value.CLAMP.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                $SwitchMap$android$support$v8$renderscript$Sampler$Value[Sampler.Value.MIRRORED_REPEAT.ordinal()] = 7;
_L2:
                return;
                nosuchfielderror6;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.android.support.v8.renderscript.Sampler.Value[value.ordinal()];
        JVM INSTR tableswitch 1 7: default 52
    //                   1 56
    //                   2 63
    //                   3 70
    //                   4 77
    //                   5 84
    //                   6 91
    //                   7 98;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        android.renderscript.Sampler.Value value1 = null;
_L10:
        return value1;
_L2:
        value1 = android.renderscript.Sampler.Value.NEAREST;
        continue; /* Loop/switch isn't completed */
_L3:
        value1 = android.renderscript.Sampler.Value.LINEAR;
        continue; /* Loop/switch isn't completed */
_L4:
        value1 = android.renderscript.Sampler.Value.LINEAR_MIP_LINEAR;
        continue; /* Loop/switch isn't completed */
_L5:
        value1 = android.renderscript.Sampler.Value.LINEAR_MIP_NEAREST;
        continue; /* Loop/switch isn't completed */
_L6:
        value1 = android.renderscript.Sampler.Value.WRAP;
        continue; /* Loop/switch isn't completed */
_L7:
        value1 = android.renderscript.Sampler.Value.CLAMP;
        continue; /* Loop/switch isn't completed */
_L8:
        value1 = android.renderscript.Sampler.Value.MIRRORED_REPEAT;
        if(true) goto _L10; else goto _L9
_L9:
    }

    BaseObj getNObj()
    {
        return mN;
    }

    Sampler mN;
}
