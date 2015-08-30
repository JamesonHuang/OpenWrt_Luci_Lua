// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            ScriptIntrinsicColorMatrix, RenderScriptThunker, ElementThunker, ExceptionThunker, 
//            AllocationThunker, Matrix3f, Matrix4f, RenderScript, 
//            Element, Allocation

class ScriptIntrinsicColorMatrixThunker extends android.support.v8.renderscript.ScriptIntrinsicColorMatrix
{

    private ScriptIntrinsicColorMatrixThunker(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public static ScriptIntrinsicColorMatrixThunker create(RenderScript renderscript, Element element)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        ElementThunker elementthunker = (ElementThunker)element;
        ScriptIntrinsicColorMatrixThunker scriptintrinsiccolormatrixthunker = new ScriptIntrinsicColorMatrixThunker(0, renderscript);
        try
        {
            scriptintrinsiccolormatrixthunker.mN = ScriptIntrinsicColorMatrix.create(renderscriptthunker.mN, elementthunker.getNObj());
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return scriptintrinsiccolormatrixthunker;
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

    ScriptIntrinsicColorMatrix getNObj()
    {
        return mN;
    }

    public void setColorMatrix(android.support.v8.renderscript.Matrix3f matrix3f)
    {
        try
        {
            mN.setColorMatrix(new Matrix3f(matrix3f.getArray()));
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setColorMatrix(android.support.v8.renderscript.Matrix4f matrix4f)
    {
        try
        {
            mN.setColorMatrix(new Matrix4f(matrix4f.getArray()));
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setGreyscale()
    {
        try
        {
            mN.setGreyscale();
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setRGBtoYUV()
    {
        try
        {
            mN.setRGBtoYUV();
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setYUVtoRGB()
    {
        try
        {
            mN.setYUVtoRGB();
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    ScriptIntrinsicColorMatrix mN;
}
