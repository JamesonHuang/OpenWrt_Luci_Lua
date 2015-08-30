// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsic3DLUT, RenderScriptThunker, ElementThunker, ExceptionThunker, 
//            AllocationThunker, RenderScript, Element, Allocation

class ScriptIntrinsic3DLUTThunker extends android.support.v8.renderscript.ScriptIntrinsic3DLUT
{

    private ScriptIntrinsic3DLUTThunker(int i, RenderScript renderscript, Element element)
    {
        super(i, renderscript, element);
    }

    public static ScriptIntrinsic3DLUTThunker create(RenderScript renderscript, Element element)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        ElementThunker elementthunker = (ElementThunker)element;
        ScriptIntrinsic3DLUTThunker scriptintrinsic3dlutthunker = new ScriptIntrinsic3DLUTThunker(0, renderscript, element);
        try
        {
            scriptintrinsic3dlutthunker.mN = ScriptIntrinsic3DLUT.create(renderscriptthunker.mN, elementthunker.getNObj());
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return scriptintrinsic3dlutthunker;
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

    ScriptIntrinsic3DLUT getNObj()
    {
        return mN;
    }

    public void setLUT(Allocation allocation)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        try
        {
            mN.setLUT(allocationthunker.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    ScriptIntrinsic3DLUT mN;
}
