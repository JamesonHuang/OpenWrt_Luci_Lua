// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;


// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsic, RenderScript, RenderScriptThunker, ScriptIntrinsicBlurThunker, 
//            Element, RSIllegalArgumentException, Allocation

public class ScriptIntrinsicBlur extends ScriptIntrinsic
{

    protected ScriptIntrinsicBlur(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public static ScriptIntrinsicBlur create(RenderScript renderscript, Element element)
    {
        Object obj;
        if(RenderScript.isNative)
        {
            RenderScriptThunker _tmp = (RenderScriptThunker)renderscript;
            obj = ScriptIntrinsicBlurThunker.create(renderscript, element);
        } else
        {
            if(!element.isCompatible(Element.U8_4(renderscript)) && !element.isCompatible(Element.U8(renderscript)))
                throw new RSIllegalArgumentException("Unsuported element type.");
            obj = new ScriptIntrinsicBlur(renderscript.nScriptIntrinsicCreate(5, element.getID(renderscript)), renderscript);
            ((ScriptIntrinsicBlur) (obj)).setRadius(5F);
        }
        return ((ScriptIntrinsicBlur) (obj));
    }

    public void forEach(Allocation allocation)
    {
        forEach(0, null, allocation, null);
    }

    public Script.FieldID getFieldID_Input()
    {
        return createFieldID(1, null);
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 2, null, null);
    }

    public void setInput(Allocation allocation)
    {
        mInput = allocation;
        setVar(1, allocation);
    }

    public void setRadius(float f)
    {
        if(f <= 0.0F || f > 25F)
        {
            throw new RSIllegalArgumentException("Radius out of range (0 < r <= 25).");
        } else
        {
            setVar(0, f);
            return;
        }
    }

    private Allocation mInput;
    private final float mValues[] = new float[9];
}
