// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsicBlur, RenderScriptThunker, ElementThunker, ExceptionThunker, 
//            AllocationThunker, RenderScript, Element, Allocation

class ScriptIntrinsicBlurThunker extends android.support.v8.renderscript.ScriptIntrinsicBlur
{

    protected ScriptIntrinsicBlurThunker(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public static ScriptIntrinsicBlurThunker create(RenderScript renderscript, Element element)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        ElementThunker elementthunker = (ElementThunker)element;
        ScriptIntrinsicBlurThunker scriptintrinsicblurthunker = new ScriptIntrinsicBlurThunker(0, renderscript);
        try
        {
            scriptintrinsicblurthunker.mN = ScriptIntrinsicBlur.create(renderscriptthunker.mN, elementthunker.getNObj());
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return scriptintrinsicblurthunker;
    }

    public void forEach(Allocation allocation)
    {
        AllocationThunker allocationthunker;
        allocationthunker = (AllocationThunker)allocation;
        if(allocationthunker == null)
            break MISSING_BLOCK_LABEL_20;
        mN.forEach(allocationthunker.getNObj());
        return;
        RSRuntimeException rsruntimeexception;
        rsruntimeexception;
        throw ExceptionThunker.convertException(rsruntimeexception);
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

    ScriptIntrinsicBlur getNObj()
    {
        return mN;
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

    public void setRadius(float f)
    {
        try
        {
            mN.setRadius(f);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    ScriptIntrinsicBlur mN;
}
