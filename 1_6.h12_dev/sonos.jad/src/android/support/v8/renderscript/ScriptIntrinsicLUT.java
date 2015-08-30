// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;


// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsic, Matrix4f, RenderScript, RenderScriptThunker, 
//            ScriptIntrinsicLUTThunker, Element, Allocation, RSIllegalArgumentException

public class ScriptIntrinsicLUT extends ScriptIntrinsic
{

    protected ScriptIntrinsicLUT(int i, RenderScript renderscript)
    {
        super(i, renderscript);
        mDirty = true;
    }

    public static ScriptIntrinsicLUT create(RenderScript renderscript, Element element)
    {
        Object obj;
        if(RenderScript.isNative)
        {
            RenderScriptThunker _tmp = (RenderScriptThunker)renderscript;
            obj = ScriptIntrinsicLUTThunker.create(renderscript, element);
        } else
        {
            obj = new ScriptIntrinsicLUT(renderscript.nScriptIntrinsicCreate(3, element.getID(renderscript)), renderscript);
            obj.mTables = Allocation.createSized(renderscript, Element.U8(renderscript), 1024);
            for(int i = 0; i < 256; i++)
            {
                ((ScriptIntrinsicLUT) (obj)).mCache[i] = (byte)i;
                ((ScriptIntrinsicLUT) (obj)).mCache[i + 256] = (byte)i;
                ((ScriptIntrinsicLUT) (obj)).mCache[i + 512] = (byte)i;
                ((ScriptIntrinsicLUT) (obj)).mCache[i + 768] = (byte)i;
            }

            ((ScriptIntrinsicLUT) (obj)).setVar(0, ((ScriptIntrinsicLUT) (obj)).mTables);
        }
        return ((ScriptIntrinsicLUT) (obj));
    }

    private void validate(int i, int j)
    {
        if(i < 0 || i > 255)
            throw new RSIllegalArgumentException("Index out of range (0-255).");
        if(j < 0 || j > 255)
            throw new RSIllegalArgumentException("Value out of range (0-255).");
        else
            return;
    }

    public void forEach(Allocation allocation, Allocation allocation1)
    {
        if(mDirty)
        {
            mDirty = false;
            mTables.copyFromUnchecked(mCache);
        }
        forEach(0, allocation, allocation1, null);
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 3, null, null);
    }

    public void setAlpha(int i, int j)
    {
        validate(i, j);
        mCache[i + 768] = (byte)j;
        mDirty = true;
    }

    public void setBlue(int i, int j)
    {
        validate(i, j);
        mCache[i + 512] = (byte)j;
        mDirty = true;
    }

    public void setGreen(int i, int j)
    {
        validate(i, j);
        mCache[i + 256] = (byte)j;
        mDirty = true;
    }

    public void setRed(int i, int j)
    {
        validate(i, j);
        mCache[i] = (byte)j;
        mDirty = true;
    }

    private final byte mCache[] = new byte[1024];
    private boolean mDirty;
    private final Matrix4f mMatrix = new Matrix4f();
    private Allocation mTables;
}
