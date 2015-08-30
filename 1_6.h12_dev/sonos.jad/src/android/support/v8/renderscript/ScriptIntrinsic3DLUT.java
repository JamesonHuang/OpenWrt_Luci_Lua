// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;


// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsic, RenderScript, RenderScriptThunker, ScriptIntrinsic3DLUTThunker, 
//            Element, RSIllegalArgumentException, Allocation, Type

public class ScriptIntrinsic3DLUT extends ScriptIntrinsic
{

    protected ScriptIntrinsic3DLUT(int i, RenderScript renderscript, Element element)
    {
        super(i, renderscript);
        mElement = element;
    }

    public static ScriptIntrinsic3DLUT create(RenderScript renderscript, Element element)
    {
        Object obj;
        if(RenderScript.isNative)
        {
            RenderScriptThunker _tmp = (RenderScriptThunker)renderscript;
            obj = ScriptIntrinsic3DLUTThunker.create(renderscript, element);
        } else
        {
            int i = renderscript.nScriptIntrinsicCreate(8, element.getID(renderscript));
            if(!element.isCompatible(Element.U8_4(renderscript)))
                throw new RSIllegalArgumentException("Element must be compatible with uchar4.");
            obj = new ScriptIntrinsic3DLUT(i, renderscript, element);
        }
        return ((ScriptIntrinsic3DLUT) (obj));
    }

    public void forEach(Allocation allocation, Allocation allocation1)
    {
        forEach(0, allocation, allocation1, null);
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 3, null, null);
    }

    public void setLUT(Allocation allocation)
    {
        Type type = allocation.getType();
        if(type.getZ() == 0)
            throw new RSIllegalArgumentException("LUT must be 3d.");
        if(!type.getElement().isCompatible(mElement))
        {
            throw new RSIllegalArgumentException("LUT element type must match.");
        } else
        {
            mLUT = allocation;
            setVar(0, mLUT);
            return;
        }
    }

    private Element mElement;
    private Allocation mLUT;
}
