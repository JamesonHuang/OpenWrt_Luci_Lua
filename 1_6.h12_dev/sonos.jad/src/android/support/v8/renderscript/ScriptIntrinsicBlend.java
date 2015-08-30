// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;


// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsic, Allocation, Element, RSIllegalArgumentException, 
//            RenderScript, RenderScriptThunker, ScriptIntrinsicBlendThunker

public class ScriptIntrinsicBlend extends ScriptIntrinsic
{

    ScriptIntrinsicBlend(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    private void blend(int i, Allocation allocation, Allocation allocation1)
    {
        if(!allocation.getElement().isCompatible(Element.U8_4(mRS)))
            throw new RSIllegalArgumentException("Input is not of expected format.");
        if(!allocation1.getElement().isCompatible(Element.U8_4(mRS)))
        {
            throw new RSIllegalArgumentException("Output is not of expected format.");
        } else
        {
            forEach(i, allocation, allocation1, null);
            return;
        }
    }

    public static ScriptIntrinsicBlend create(RenderScript renderscript, Element element)
    {
        Object obj;
        if(RenderScript.isNative)
        {
            RenderScriptThunker _tmp = (RenderScriptThunker)renderscript;
            obj = ScriptIntrinsicBlendThunker.create(renderscript, element);
        } else
        {
            obj = new ScriptIntrinsicBlend(renderscript.nScriptIntrinsicCreate(7, element.getID(renderscript)), renderscript);
        }
        return ((ScriptIntrinsicBlend) (obj));
    }

    public void forEachAdd(Allocation allocation, Allocation allocation1)
    {
        blend(34, allocation, allocation1);
    }

    public void forEachClear(Allocation allocation, Allocation allocation1)
    {
        blend(0, allocation, allocation1);
    }

    public void forEachDst(Allocation allocation, Allocation allocation1)
    {
    }

    public void forEachDstAtop(Allocation allocation, Allocation allocation1)
    {
        blend(10, allocation, allocation1);
    }

    public void forEachDstIn(Allocation allocation, Allocation allocation1)
    {
        blend(6, allocation, allocation1);
    }

    public void forEachDstOut(Allocation allocation, Allocation allocation1)
    {
        blend(8, allocation, allocation1);
    }

    public void forEachDstOver(Allocation allocation, Allocation allocation1)
    {
        blend(4, allocation, allocation1);
    }

    public void forEachMultiply(Allocation allocation, Allocation allocation1)
    {
        blend(14, allocation, allocation1);
    }

    public void forEachSrc(Allocation allocation, Allocation allocation1)
    {
        blend(1, allocation, allocation1);
    }

    public void forEachSrcAtop(Allocation allocation, Allocation allocation1)
    {
        blend(9, allocation, allocation1);
    }

    public void forEachSrcIn(Allocation allocation, Allocation allocation1)
    {
        blend(5, allocation, allocation1);
    }

    public void forEachSrcOut(Allocation allocation, Allocation allocation1)
    {
        blend(7, allocation, allocation1);
    }

    public void forEachSrcOver(Allocation allocation, Allocation allocation1)
    {
        blend(3, allocation, allocation1);
    }

    public void forEachSubtract(Allocation allocation, Allocation allocation1)
    {
        blend(35, allocation, allocation1);
    }

    public void forEachXor(Allocation allocation, Allocation allocation1)
    {
        blend(11, allocation, allocation1);
    }

    public Script.KernelID getKernelIDAdd()
    {
        return createKernelID(34, 3, null, null);
    }

    public Script.KernelID getKernelIDClear()
    {
        return createKernelID(0, 3, null, null);
    }

    public Script.KernelID getKernelIDDst()
    {
        return createKernelID(2, 3, null, null);
    }

    public Script.KernelID getKernelIDDstAtop()
    {
        return createKernelID(10, 3, null, null);
    }

    public Script.KernelID getKernelIDDstIn()
    {
        return createKernelID(6, 3, null, null);
    }

    public Script.KernelID getKernelIDDstOut()
    {
        return createKernelID(8, 3, null, null);
    }

    public Script.KernelID getKernelIDDstOver()
    {
        return createKernelID(4, 3, null, null);
    }

    public Script.KernelID getKernelIDMultiply()
    {
        return createKernelID(14, 3, null, null);
    }

    public Script.KernelID getKernelIDSrc()
    {
        return createKernelID(1, 3, null, null);
    }

    public Script.KernelID getKernelIDSrcAtop()
    {
        return createKernelID(9, 3, null, null);
    }

    public Script.KernelID getKernelIDSrcIn()
    {
        return createKernelID(5, 3, null, null);
    }

    public Script.KernelID getKernelIDSrcOut()
    {
        return createKernelID(7, 3, null, null);
    }

    public Script.KernelID getKernelIDSrcOver()
    {
        return createKernelID(3, 3, null, null);
    }

    public Script.KernelID getKernelIDSubtract()
    {
        return createKernelID(35, 3, null, null);
    }

    public Script.KernelID getKernelIDXor()
    {
        return createKernelID(11, 3, null, null);
    }
}
