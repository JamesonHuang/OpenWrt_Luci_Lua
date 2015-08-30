// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.content.res.Resources;
import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            RenderScriptThunker, AllocationThunker, ExceptionThunker, ElementThunker, 
//            FieldPacker, BaseObj, Allocation, Element

class ScriptCThunker extends ScriptC
{

    protected ScriptCThunker(RenderScriptThunker renderscriptthunker, Resources resources, int i)
    {
        super(renderscriptthunker.mN, resources, i);
    }

    void thunkBindAllocation(Allocation allocation, int i)
    {
        android.renderscript.Allocation allocation1 = null;
        if(allocation != null)
            allocation1 = ((AllocationThunker)allocation).mN;
        try
        {
            bindAllocation(allocation1, i);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    android.renderscript.Script.FieldID thunkCreateFieldID(int i, Element element)
    {
        android.renderscript.Script.FieldID fieldid;
        try
        {
            fieldid = createFieldID(i, ((ElementThunker)element).getNObj());
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return fieldid;
    }

    android.renderscript.Script.KernelID thunkCreateKernelID(int i, int j, Element element, Element element1)
    {
        android.renderscript.Element element2 = null;
        android.renderscript.Element element3 = null;
        if(element != null)
            element2 = ((ElementThunker)element).mN;
        if(element1 != null)
            element3 = ((ElementThunker)element1).mN;
        android.renderscript.Script.KernelID kernelid;
        try
        {
            kernelid = createKernelID(i, j, element2, element3);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return kernelid;
    }

    void thunkForEach(int i, Allocation allocation, Allocation allocation1, android.support.v8.renderscript.FieldPacker fieldpacker)
    {
        android.renderscript.Allocation allocation2;
        android.renderscript.Allocation allocation3;
        FieldPacker fieldpacker1;
        allocation2 = null;
        allocation3 = null;
        fieldpacker1 = null;
        if(allocation != null)
            allocation2 = ((AllocationThunker)allocation).mN;
        if(allocation1 != null)
            allocation3 = ((AllocationThunker)allocation1).mN;
        if(fieldpacker == null)
            break MISSING_BLOCK_LABEL_54;
        fieldpacker1 = new FieldPacker(fieldpacker.getData());
        forEach(i, allocation2, allocation3, fieldpacker1);
        return;
        RSRuntimeException rsruntimeexception;
        rsruntimeexception;
        throw ExceptionThunker.convertException(rsruntimeexception);
    }

    void thunkForEach(int i, Allocation allocation, Allocation allocation1, android.support.v8.renderscript.FieldPacker fieldpacker, Script.LaunchOptions launchoptions)
    {
        android.renderscript.Script.LaunchOptions launchoptions1;
        launchoptions1 = null;
        if(launchoptions == null)
            break MISSING_BLOCK_LABEL_93;
        android.renderscript.Script.LaunchOptions launchoptions2 = new android.renderscript.Script.LaunchOptions();
        if(launchoptions.getXEnd() > 0)
            launchoptions2.setX(launchoptions.getXStart(), launchoptions.getXEnd());
        if(launchoptions.getYEnd() > 0)
            launchoptions2.setY(launchoptions.getYStart(), launchoptions.getYEnd());
        if(launchoptions.getZEnd() > 0)
            launchoptions2.setZ(launchoptions.getZStart(), launchoptions.getZEnd());
        launchoptions1 = launchoptions2;
        android.renderscript.Allocation allocation2;
        android.renderscript.Allocation allocation3;
        FieldPacker fieldpacker1;
        allocation2 = null;
        allocation3 = null;
        fieldpacker1 = null;
        if(allocation == null)
            break MISSING_BLOCK_LABEL_115;
        allocation2 = ((AllocationThunker)allocation).mN;
        if(allocation1 != null)
            allocation3 = ((AllocationThunker)allocation1).mN;
        if(fieldpacker != null)
            fieldpacker1 = new FieldPacker(fieldpacker.getData());
        forEach(i, allocation2, allocation3, fieldpacker1, launchoptions1);
        return;
        RSRuntimeException rsruntimeexception;
        rsruntimeexception;
_L2:
        throw ExceptionThunker.convertException(rsruntimeexception);
        rsruntimeexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void thunkInvoke(int i)
    {
        try
        {
            invoke(i);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    void thunkInvoke(int i, android.support.v8.renderscript.FieldPacker fieldpacker)
    {
        try
        {
            invoke(i, new FieldPacker(fieldpacker.getData()));
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    void thunkSetTimeZone(String s)
    {
        try
        {
            setTimeZone(s);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    void thunkSetVar(int i, double d)
    {
        try
        {
            setVar(i, d);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    void thunkSetVar(int i, float f)
    {
        try
        {
            setVar(i, f);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    void thunkSetVar(int i, int j)
    {
        try
        {
            setVar(i, j);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    void thunkSetVar(int i, long l)
    {
        try
        {
            setVar(i, l);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    void thunkSetVar(int i, BaseObj baseobj)
    {
        if(baseobj != null)
            break MISSING_BLOCK_LABEL_19;
        setVar(i, 0);
_L1:
        return;
        RSRuntimeException rsruntimeexception1;
        rsruntimeexception1;
        throw ExceptionThunker.convertException(rsruntimeexception1);
        try
        {
            setVar(i, baseobj.getNObj());
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
          goto _L1
    }

    void thunkSetVar(int i, android.support.v8.renderscript.FieldPacker fieldpacker)
    {
        try
        {
            setVar(i, new FieldPacker(fieldpacker.getData()));
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    void thunkSetVar(int i, android.support.v8.renderscript.FieldPacker fieldpacker, Element element, int ai[])
    {
        try
        {
            setVar(i, new FieldPacker(fieldpacker.getData()), ((ElementThunker)element).mN, ai);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    void thunkSetVar(int i, boolean flag)
    {
        try
        {
            setVar(i, flag);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    private static final String TAG = "ScriptC";
}
