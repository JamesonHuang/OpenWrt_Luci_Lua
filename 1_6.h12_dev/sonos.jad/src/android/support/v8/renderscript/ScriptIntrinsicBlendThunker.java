// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsicBlend, RenderScriptThunker, ElementThunker, ExceptionThunker, 
//            AllocationThunker, RenderScript, Element, Allocation

class ScriptIntrinsicBlendThunker extends android.support.v8.renderscript.ScriptIntrinsicBlend
{

    ScriptIntrinsicBlendThunker(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public static ScriptIntrinsicBlendThunker create(RenderScript renderscript, Element element)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        ElementThunker elementthunker = (ElementThunker)element;
        ScriptIntrinsicBlendThunker scriptintrinsicblendthunker = new ScriptIntrinsicBlendThunker(0, renderscript);
        try
        {
            scriptintrinsicblendthunker.mN = ScriptIntrinsicBlend.create(renderscriptthunker.mN, elementthunker.getNObj());
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return scriptintrinsicblendthunker;
    }

    public void forEachAdd(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachAdd(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachClear(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachClear(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachDst(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachDst(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachDstAtop(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachDstAtop(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachDstIn(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachDstIn(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachDstOut(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachDstOut(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachDstOver(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachDstOver(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachMultiply(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachMultiply(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachSrc(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachSrc(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachSrcAtop(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachSrcAtop(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachSrcIn(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachSrcIn(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachSrcOut(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachSrcOut(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachSrcOver(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachSrcOver(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachSubtract(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachSubtract(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void forEachXor(Allocation allocation, Allocation allocation1)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        AllocationThunker allocationthunker1 = (AllocationThunker)allocation1;
        try
        {
            mN.forEachXor(allocationthunker.getNObj(), allocationthunker1.getNObj());
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public Script.KernelID getKernelIDAdd()
    {
        Script.KernelID kernelid = createKernelID(34, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDAdd();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDClear()
    {
        Script.KernelID kernelid = createKernelID(0, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDClear();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDDst()
    {
        Script.KernelID kernelid = createKernelID(2, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDDst();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDDstAtop()
    {
        Script.KernelID kernelid = createKernelID(10, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDDstAtop();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDDstIn()
    {
        Script.KernelID kernelid = createKernelID(6, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDDstIn();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDDstOut()
    {
        Script.KernelID kernelid = createKernelID(8, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDDstOut();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDDstOver()
    {
        Script.KernelID kernelid = createKernelID(4, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDDstOver();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDMultiply()
    {
        Script.KernelID kernelid = createKernelID(14, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDMultiply();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDSrc()
    {
        Script.KernelID kernelid = createKernelID(1, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDSrc();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDSrcAtop()
    {
        Script.KernelID kernelid = createKernelID(9, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDSrcAtop();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDSrcIn()
    {
        Script.KernelID kernelid = createKernelID(5, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDSrcIn();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDSrcOut()
    {
        Script.KernelID kernelid = createKernelID(7, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDSrcOut();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDSrcOver()
    {
        Script.KernelID kernelid = createKernelID(3, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDSrcOver();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDSubtract()
    {
        Script.KernelID kernelid = createKernelID(35, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDSubtract();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    public Script.KernelID getKernelIDXor()
    {
        Script.KernelID kernelid = createKernelID(11, 3, null, null);
        try
        {
            kernelid.mN = mN.getKernelIDXor();
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

    ScriptIntrinsicBlend getNObj()
    {
        return mN;
    }

    ScriptIntrinsicBlend mN;
}
