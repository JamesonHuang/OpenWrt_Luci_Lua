// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;


// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsic, RenderScript, RenderScriptThunker, ScriptIntrinsicConvolve3x3Thunker, 
//            Element, RSIllegalArgumentException, FieldPacker, Allocation

public class ScriptIntrinsicConvolve3x3 extends ScriptIntrinsic
{

    ScriptIntrinsicConvolve3x3(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public static ScriptIntrinsicConvolve3x3 create(RenderScript renderscript, Element element)
    {
        Object obj;
        if(RenderScript.isNative)
        {
            RenderScriptThunker _tmp = (RenderScriptThunker)renderscript;
            obj = ScriptIntrinsicConvolve3x3Thunker.create(renderscript, element);
        } else
        {
            float af[] = new float[9];
            af[0] = 0.0F;
            af[1] = 0.0F;
            af[2] = 0.0F;
            af[3] = 0.0F;
            af[4] = 1.0F;
            af[5] = 0.0F;
            af[6] = 0.0F;
            af[7] = 0.0F;
            af[8] = 0.0F;
            if(!element.isCompatible(Element.U8_4(renderscript)))
                throw new RSIllegalArgumentException("Unsuported element type.");
            obj = new ScriptIntrinsicConvolve3x3(renderscript.nScriptIntrinsicCreate(1, element.getID(renderscript)), renderscript);
            ((ScriptIntrinsicConvolve3x3) (obj)).setCoefficients(af);
        }
        return ((ScriptIntrinsicConvolve3x3) (obj));
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

    public void setCoefficients(float af[])
    {
        FieldPacker fieldpacker = new FieldPacker(36);
        for(int i = 0; i < mValues.length; i++)
        {
            mValues[i] = af[i];
            fieldpacker.addF32(mValues[i]);
        }

        setVar(0, fieldpacker);
    }

    public void setInput(Allocation allocation)
    {
        mInput = allocation;
        setVar(1, allocation);
    }

    private Allocation mInput;
    private final float mValues[] = new float[9];
}
