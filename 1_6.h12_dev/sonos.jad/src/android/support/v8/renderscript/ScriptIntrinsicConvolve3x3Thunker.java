// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsicConvolve3x3, RenderScriptThunker, ElementThunker, ExceptionThunker, 
//            AllocationThunker, RenderScript, Element, Allocation

class ScriptIntrinsicConvolve3x3Thunker extends android.support.v8.renderscript.ScriptIntrinsicConvolve3x3
{

    ScriptIntrinsicConvolve3x3Thunker(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public static ScriptIntrinsicConvolve3x3Thunker create(RenderScript renderscript, Element element)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        ElementThunker elementthunker = (ElementThunker)element;
        ScriptIntrinsicConvolve3x3Thunker scriptintrinsicconvolve3x3thunker = new ScriptIntrinsicConvolve3x3Thunker(0, renderscript);
        try
        {
            scriptintrinsicconvolve3x3thunker.mN = ScriptIntrinsicConvolve3x3.create(renderscriptthunker.mN, elementthunker.getNObj());
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return scriptintrinsicconvolve3x3thunker;
    }

    public void forEach(Allocation allocation)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        try
        {
            mN.forEach(allocationthunker.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public Script.FieldID getFieldID_Input()
    {
        Script.FieldID fieldid = createFieldID(1, null);
        try
        {
            fieldid.mN = mN.getFieldID_Input();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return fieldid;
    }

    public Script.KernelID getKernelID()
    {
        Script.KernelID kernelid = createKernelID(0, 2, null, null);
        try
        {
            kernelid.mN = mN.getKernelID();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    volatile BaseObj getNObj()
    {
        return getNObj();
    }

    volatile Script getNObj()
    {
        return getNObj();
    }

    ScriptIntrinsicConvolve3x3 getNObj()
    {
        return mN;
    }

    public void setCoefficients(float af[])
    {
        try
        {
            mN.setCoefficients(af);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setInput(Allocation allocation)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        try
        {
            mN.setInput(allocationthunker.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    ScriptIntrinsicConvolve3x3 mN;
}
