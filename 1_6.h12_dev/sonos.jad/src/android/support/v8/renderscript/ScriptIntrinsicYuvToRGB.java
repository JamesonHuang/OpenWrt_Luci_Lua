// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;


// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsic, RenderScript, RenderScriptThunker, ScriptIntrinsicYuvToRGBThunker, 
//            Element, Allocation

public class ScriptIntrinsicYuvToRGB extends ScriptIntrinsic
{

    ScriptIntrinsicYuvToRGB(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public static ScriptIntrinsicYuvToRGB create(RenderScript renderscript, Element element)
    {
        Object obj;
        if(RenderScript.isNative)
        {
            RenderScriptThunker _tmp = (RenderScriptThunker)renderscript;
            obj = ScriptIntrinsicYuvToRGBThunker.create(renderscript, element);
        } else
        {
            obj = new ScriptIntrinsicYuvToRGB(renderscript.nScriptIntrinsicCreate(6, element.getID(renderscript)), renderscript);
        }
        return ((ScriptIntrinsicYuvToRGB) (obj));
    }

    public void forEach(Allocation allocation)
    {
        forEach(0, null, allocation, null);
    }

    public Script.FieldID getFieldID_Input()
    {
        return createFieldID(0, null);
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 2, null, null);
    }

    public void setInput(Allocation allocation)
    {
        mInput = allocation;
        setVar(0, allocation);
    }

    private Allocation mInput;
}
