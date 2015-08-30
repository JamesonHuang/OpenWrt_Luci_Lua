// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;


// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsic, RenderScript, RenderScriptThunker, ScriptIntrinsicConvolve5x5Thunker, 
//            Element, FieldPacker, Allocation

public class ScriptIntrinsicConvolve5x5 extends ScriptIntrinsic
{

    ScriptIntrinsicConvolve5x5(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public static ScriptIntrinsicConvolve5x5 create(RenderScript renderscript, Element element)
    {
        Object obj;
        if(RenderScript.isNative)
        {
            RenderScriptThunker _tmp = (RenderScriptThunker)renderscript;
            obj = ScriptIntrinsicConvolve5x5Thunker.create(renderscript, element);
        } else
        {
            obj = new ScriptIntrinsicConvolve5x5(renderscript.nScriptIntrinsicCreate(4, element.getID(renderscript)), renderscript);
        }
        return ((ScriptIntrinsicConvolve5x5) (obj));
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
        FieldPacker fieldpacker = new FieldPacker(100);
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
    private final float mValues[] = new float[25];
}
