// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;
import java.util.HashMap;

// Referenced classes of package android.support.v8.renderscript:
//            Type, ElementThunker, ExceptionThunker, RenderScriptThunker, 
//            RenderScript, Element

class TypeThunker extends android.support.v8.renderscript.Type
{

    TypeThunker(RenderScript renderscript, Type type)
    {
        super(0, renderscript);
        mN = type;
        try
        {
            internalCalc();
            mElement = new ElementThunker(renderscript, type.getElement());
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        synchronized(mMap)
        {
            mMap.put(mN, this);
        }
        return;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static android.support.v8.renderscript.Type create(RenderScript renderscript, Element element, int i, int j, int k, boolean flag, boolean flag1, int l)
    {
        ElementThunker elementthunker = (ElementThunker)element;
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        TypeThunker typethunker;
        try
        {
            android.renderscript.Type.Builder builder = new android.renderscript.Type.Builder(renderscriptthunker.mN, elementthunker.mN);
            if(i > 0)
                builder.setX(i);
            if(j > 0)
                builder.setY(j);
            if(k > 0)
                builder.setZ(k);
            if(flag)
                builder.setMipmaps(flag);
            if(flag1)
                builder.setFaces(flag1);
            if(l > 0)
                builder.setYuvFormat(l);
            typethunker = new TypeThunker(renderscript, builder.create());
            typethunker.internalCalc();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return typethunker;
    }

    static android.support.v8.renderscript.Type find(Type type)
    {
        return (android.support.v8.renderscript.Type)mMap.get(type);
    }

    volatile BaseObj getNObj()
    {
        return getNObj();
    }

    Type getNObj()
    {
        return mN;
    }

    void internalCalc()
    {
        mDimX = mN.getX();
        mDimY = mN.getY();
        mDimZ = mN.getZ();
        mDimFaces = mN.hasFaces();
        mDimMipmaps = mN.hasMipmaps();
        mDimYuv = mN.getYuv();
        calcElementCount();
    }

    static HashMap mMap = new HashMap();
    Type mN;

}
