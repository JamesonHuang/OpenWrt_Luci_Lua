// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsicLUT, RenderScriptThunker, ElementThunker, ExceptionThunker, 
//            AllocationThunker, RenderScript, Element, Allocation

class ScriptIntrinsicLUTThunker extends android.support.v8.renderscript.ScriptIntrinsicLUT
{

    private ScriptIntrinsicLUTThunker(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public static ScriptIntrinsicLUTThunker create(RenderScript renderscript, Element element)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        ElementThunker elementthunker = (ElementThunker)element;
        ScriptIntrinsicLUTThunker scriptintrinsiclutthunker = new ScriptIntrinsicLUTThunker(0, renderscript);
        try
        {
            scriptintrinsiclutthunker.mN = ScriptIntrinsicLUT.create(renderscriptthunker.mN, elementthunker.getNObj());
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return scriptintrinsiclutthunker;
    }

    public void forEach(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEach(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public Script.KernelID getKernelID()
    {
        Script.KernelID kernelid = createKernelID(0, 3, null, null);
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

    ScriptIntrinsicLUT getNObj()
    {
        return mN;
    }

    public void setAlpha(int i, int j)
    {
        try
        {
            mN.setAlpha(i, j);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setBlue(int i, int j)
    {
        try
        {
            mN.setBlue(i, j);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setGreen(int i, int j)
    {
        try
        {
            mN.setGreen(i, j);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setRed(int i, int j)
    {
        try
        {
            mN.setRed(i, j);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    ScriptIntrinsicLUT mN;
}
