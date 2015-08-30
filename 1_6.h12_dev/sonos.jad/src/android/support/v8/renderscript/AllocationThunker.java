// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            Allocation, RenderScriptThunker, TypeThunker, ExceptionThunker, 
//            ElementThunker, Element, BaseObj, Type, 
//            FieldPacker, RenderScript

class AllocationThunker extends android.support.v8.renderscript.Allocation
{

    AllocationThunker(RenderScript renderscript, Type type, int i, Allocation allocation)
    {
        super(0, renderscript, type, i);
        mType = type;
        mUsage = i;
        mN = allocation;
    }

    static android.renderscript.Allocation.MipmapControl convertMipmapControl(Allocation.MipmapControl mipmapcontrol)
    {
        class _cls1
        {

            static final int $SwitchMap$android$support$v8$renderscript$Allocation$MipmapControl[];

            static 
            {
                $SwitchMap$android$support$v8$renderscript$Allocation$MipmapControl = new int[Allocation.MipmapControl.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Allocation$MipmapControl[Allocation.MipmapControl.MIPMAP_NONE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Allocation$MipmapControl[Allocation.MipmapControl.MIPMAP_FULL.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$android$support$v8$renderscript$Allocation$MipmapControl[Allocation.MipmapControl.MIPMAP_ON_SYNC_TO_TEXTURE.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.android.support.v8.renderscript.Allocation.MipmapControl[mipmapcontrol.ordinal()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 40
    //                   2 47
    //                   3 54;
           goto _L1 _L2 _L3 _L4
_L1:
        android.renderscript.Allocation.MipmapControl mipmapcontrol1 = null;
_L6:
        return mipmapcontrol1;
_L2:
        mipmapcontrol1 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE;
        continue; /* Loop/switch isn't completed */
_L3:
        mipmapcontrol1 = android.renderscript.Allocation.MipmapControl.MIPMAP_FULL;
        continue; /* Loop/switch isn't completed */
_L4:
        mipmapcontrol1 = android.renderscript.Allocation.MipmapControl.MIPMAP_ON_SYNC_TO_TEXTURE;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static android.support.v8.renderscript.Allocation createCubemapFromBitmap(RenderScript renderscript, Bitmap bitmap, Allocation.MipmapControl mipmapcontrol, int i)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        AllocationThunker allocationthunker;
        try
        {
            Allocation allocation = Allocation.createCubemapFromBitmap(renderscriptthunker.mN, bitmap, convertMipmapControl(mipmapcontrol), i);
            allocationthunker = new AllocationThunker(renderscript, new TypeThunker(renderscript, allocation.getType()), i, allocation);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return allocationthunker;
    }

    public static android.support.v8.renderscript.Allocation createCubemapFromCubeFaces(RenderScript renderscript, Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Allocation.MipmapControl mipmapcontrol, 
            int i)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        AllocationThunker allocationthunker;
        try
        {
            Allocation allocation = Allocation.createCubemapFromCubeFaces(renderscriptthunker.mN, bitmap, bitmap1, bitmap2, bitmap3, bitmap4, bitmap5, convertMipmapControl(mipmapcontrol), i);
            allocationthunker = new AllocationThunker(renderscript, new TypeThunker(renderscript, allocation.getType()), i, allocation);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return allocationthunker;
    }

    public static android.support.v8.renderscript.Allocation createFromBitmap(RenderScript renderscript, Bitmap bitmap, Allocation.MipmapControl mipmapcontrol, int i)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        AllocationThunker allocationthunker;
        try
        {
            Allocation allocation = Allocation.createFromBitmap(renderscriptthunker.mN, bitmap, convertMipmapControl(mipmapcontrol), i);
            allocationthunker = new AllocationThunker(renderscript, new TypeThunker(renderscript, allocation.getType()), i, allocation);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return allocationthunker;
    }

    public static android.support.v8.renderscript.Allocation createFromBitmapResource(RenderScript renderscript, Resources resources, int i, Allocation.MipmapControl mipmapcontrol, int j)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        AllocationThunker allocationthunker;
        try
        {
            Allocation allocation = Allocation.createFromBitmapResource(renderscriptthunker.mN, resources, i, convertMipmapControl(mipmapcontrol), j);
            allocationthunker = new AllocationThunker(renderscript, new TypeThunker(renderscript, allocation.getType()), j, allocation);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return allocationthunker;
    }

    public static android.support.v8.renderscript.Allocation createFromString(RenderScript renderscript, String s, int i)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        AllocationThunker allocationthunker;
        try
        {
            Allocation allocation = Allocation.createFromString(renderscriptthunker.mN, s, i);
            allocationthunker = new AllocationThunker(renderscript, new TypeThunker(renderscript, allocation.getType()), i, allocation);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return allocationthunker;
    }

    public static android.support.v8.renderscript.Allocation createSized(RenderScript renderscript, android.support.v8.renderscript.Element element, int i, int j)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        ElementThunker _tmp = (ElementThunker)element;
        AllocationThunker allocationthunker;
        try
        {
            Allocation allocation = Allocation.createSized(renderscriptthunker.mN, (Element)element.getNObj(), i, j);
            allocationthunker = new AllocationThunker(renderscript, new TypeThunker(renderscript, allocation.getType()), j, allocation);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return allocationthunker;
    }

    public static android.support.v8.renderscript.Allocation createTyped(RenderScript renderscript, Type type, Allocation.MipmapControl mipmapcontrol, int i)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        TypeThunker typethunker = (TypeThunker)type;
        AllocationThunker allocationthunker;
        try
        {
            allocationthunker = new AllocationThunker(renderscript, type, i, Allocation.createTyped(renderscriptthunker.mN, typethunker.mN, convertMipmapControl(mipmapcontrol), i));
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return allocationthunker;
    }

    public void copy1DRangeFrom(int i, int j, android.support.v8.renderscript.Allocation allocation, int k)
    {
        try
        {
            AllocationThunker allocationthunker = (AllocationThunker)allocation;
            mN.copy1DRangeFrom(i, j, allocationthunker.mN, k);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy1DRangeFrom(int i, int j, byte abyte0[])
    {
        try
        {
            mN.copy1DRangeFrom(i, j, abyte0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy1DRangeFrom(int i, int j, float af[])
    {
        try
        {
            mN.copy1DRangeFrom(i, j, af);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy1DRangeFrom(int i, int j, int ai[])
    {
        try
        {
            mN.copy1DRangeFrom(i, j, ai);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy1DRangeFrom(int i, int j, short aword0[])
    {
        try
        {
            mN.copy1DRangeFrom(i, j, aword0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy1DRangeFromUnchecked(int i, int j, byte abyte0[])
    {
        try
        {
            mN.copy1DRangeFromUnchecked(i, j, abyte0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy1DRangeFromUnchecked(int i, int j, float af[])
    {
        try
        {
            mN.copy1DRangeFromUnchecked(i, j, af);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy1DRangeFromUnchecked(int i, int j, int ai[])
    {
        try
        {
            mN.copy1DRangeFromUnchecked(i, j, ai);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy1DRangeFromUnchecked(int i, int j, short aword0[])
    {
        try
        {
            mN.copy1DRangeFromUnchecked(i, j, aword0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, android.support.v8.renderscript.Allocation allocation, int i1, int j1)
    {
        try
        {
            AllocationThunker allocationthunker = (AllocationThunker)allocation;
            mN.copy2DRangeFrom(i, j, k, l, allocationthunker.mN, i1, j1);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, byte abyte0[])
    {
        try
        {
            mN.copy2DRangeFrom(i, j, k, l, abyte0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, float af[])
    {
        try
        {
            mN.copy2DRangeFrom(i, j, k, l, af);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, int ai[])
    {
        try
        {
            mN.copy2DRangeFrom(i, j, k, l, ai);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, short aword0[])
    {
        try
        {
            mN.copy2DRangeFrom(i, j, k, l, aword0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copy2DRangeFrom(int i, int j, Bitmap bitmap)
    {
        try
        {
            mN.copy2DRangeFrom(i, j, bitmap);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFrom(Bitmap bitmap)
    {
        try
        {
            mN.copyFrom(bitmap);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFrom(android.support.v8.renderscript.Allocation allocation)
    {
        AllocationThunker allocationthunker = (AllocationThunker)allocation;
        try
        {
            mN.copyFrom(allocationthunker.mN);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFrom(byte abyte0[])
    {
        try
        {
            mN.copyFrom(abyte0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFrom(float af[])
    {
        try
        {
            mN.copyFrom(af);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFrom(int ai[])
    {
        try
        {
            mN.copyFrom(ai);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFrom(android.support.v8.renderscript.BaseObj abaseobj[])
    {
        if(abaseobj != null)
        {
            BaseObj abaseobj1[] = new BaseObj[abaseobj.length];
            for(int i = 0; i < abaseobj.length; i++)
                abaseobj1[i] = abaseobj[i].getNObj();

            try
            {
                mN.copyFrom(abaseobj1);
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
        }
    }

    public void copyFrom(short aword0[])
    {
        try
        {
            mN.copyFrom(aword0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFromUnchecked(byte abyte0[])
    {
        try
        {
            mN.copyFromUnchecked(abyte0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFromUnchecked(float af[])
    {
        try
        {
            mN.copyFromUnchecked(af);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFromUnchecked(int ai[])
    {
        try
        {
            mN.copyFromUnchecked(ai);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyFromUnchecked(short aword0[])
    {
        try
        {
            mN.copyFromUnchecked(aword0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyTo(Bitmap bitmap)
    {
        try
        {
            mN.copyTo(bitmap);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyTo(byte abyte0[])
    {
        try
        {
            mN.copyTo(abyte0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyTo(float af[])
    {
        try
        {
            mN.copyTo(af);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyTo(int ai[])
    {
        try
        {
            mN.copyTo(ai);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void copyTo(short aword0[])
    {
        try
        {
            mN.copyTo(aword0);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void generateMipmaps()
    {
        try
        {
            mN.generateMipmaps();
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public int getBytesSize()
    {
        int i;
        try
        {
            i = mN.getBytesSize();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return i;
    }

    public android.support.v8.renderscript.Element getElement()
    {
        return getType().getElement();
    }

    Allocation getNObj()
    {
        return mN;
    }

    volatile BaseObj getNObj()
    {
        return getNObj();
    }

    public Type getType()
    {
        return TypeThunker.find(mN.getType());
    }

    public int getUsage()
    {
        int i;
        try
        {
            i = mN.getUsage();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return i;
    }

    public void ioReceive()
    {
        try
        {
            mN.ioReceive();
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void ioSend()
    {
        try
        {
            mN.ioSend();
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setFromFieldPacker(int i, int j, android.support.v8.renderscript.FieldPacker fieldpacker)
    {
        try
        {
            FieldPacker fieldpacker1 = new FieldPacker(fieldpacker.getData());
            mN.setFromFieldPacker(i, j, fieldpacker1);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void setFromFieldPacker(int i, android.support.v8.renderscript.FieldPacker fieldpacker)
    {
        try
        {
            FieldPacker fieldpacker1 = new FieldPacker(fieldpacker.getData());
            mN.setFromFieldPacker(i, fieldpacker1);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    public void syncAll(int i)
    {
        try
        {
            mN.syncAll(i);
            return;
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
    }

    static android.graphics.BitmapFactory.Options mBitmapOptions;
    Allocation mN;

    static 
    {
        mBitmapOptions = new android.graphics.BitmapFactory.Options();
        mBitmapOptions.inScaled = false;
    }
}
