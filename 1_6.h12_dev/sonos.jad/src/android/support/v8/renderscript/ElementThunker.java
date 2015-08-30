// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import android.renderscript.*;

// Referenced classes of package android.support.v8.renderscript:
//            Element, RenderScriptThunker, ExceptionThunker, RenderScript

class ElementThunker extends android.support.v8.renderscript.Element
{
    static class BuilderThunker
    {

        public void add(android.support.v8.renderscript.Element element, String s, int i)
        {
            ElementThunker elementthunker = (ElementThunker)element;
            try
            {
                mN.add(elementthunker.mN, s, i);
                return;
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
        }

        public android.support.v8.renderscript.Element create(RenderScript renderscript)
        {
            ElementThunker elementthunker;
            try
            {
                elementthunker = new ElementThunker(renderscript, mN.create());
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
            return elementthunker;
        }

        android.renderscript.Element.Builder mN;

        public BuilderThunker(RenderScript renderscript)
        {
            RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
            try
            {
                mN = new android.renderscript.Element.Builder(renderscriptthunker.mN);
                return;
            }
            catch(RSRuntimeException rsruntimeexception)
            {
                throw ExceptionThunker.convertException(rsruntimeexception);
            }
        }
    }


    ElementThunker(RenderScript renderscript, Element element)
    {
        super(0, renderscript);
        mN = element;
    }

    static android.renderscript.Element.DataKind convertKind(Element.DataKind datakind)
    {
        class _cls1
        {

            static final int $SwitchMap$android$support$v8$renderscript$Element$DataKind[];
            static final int $SwitchMap$android$support$v8$renderscript$Element$DataType[];

            static 
            {
                $SwitchMap$android$support$v8$renderscript$Element$DataType = new int[Element.DataType.values().length];
                NoSuchFieldError nosuchfielderror25;
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.NONE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.FLOAT_32.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.FLOAT_64.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.SIGNED_8.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.SIGNED_16.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.SIGNED_32.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.SIGNED_64.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.UNSIGNED_8.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.UNSIGNED_16.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.UNSIGNED_32.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.UNSIGNED_64.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.BOOLEAN.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.MATRIX_4X4.ordinal()] = 13;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.MATRIX_3X3.ordinal()] = 14;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.MATRIX_2X2.ordinal()] = 15;
                }
                catch(NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.RS_ELEMENT.ordinal()] = 16;
                }
                catch(NoSuchFieldError nosuchfielderror15) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.RS_TYPE.ordinal()] = 17;
                }
                catch(NoSuchFieldError nosuchfielderror16) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.RS_ALLOCATION.ordinal()] = 18;
                }
                catch(NoSuchFieldError nosuchfielderror17) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.RS_SAMPLER.ordinal()] = 19;
                }
                catch(NoSuchFieldError nosuchfielderror18) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataType[Element.DataType.RS_SCRIPT.ordinal()] = 20;
                }
                catch(NoSuchFieldError nosuchfielderror19) { }
                $SwitchMap$android$support$v8$renderscript$Element$DataKind = new int[Element.DataKind.values().length];
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataKind[Element.DataKind.USER.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror20) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataKind[Element.DataKind.PIXEL_L.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror21) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataKind[Element.DataKind.PIXEL_A.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror22) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataKind[Element.DataKind.PIXEL_LA.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror23) { }
                try
                {
                    $SwitchMap$android$support$v8$renderscript$Element$DataKind[Element.DataKind.PIXEL_RGB.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror24) { }
                $SwitchMap$android$support$v8$renderscript$Element$DataKind[Element.DataKind.PIXEL_RGBA.ordinal()] = 6;
_L2:
                return;
                nosuchfielderror25;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.android.support.v8.renderscript.Element.DataKind[datakind.ordinal()];
        JVM INSTR tableswitch 1 6: default 48
    //                   1 52
    //                   2 59
    //                   3 66
    //                   4 73
    //                   5 80
    //                   6 87;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        android.renderscript.Element.DataKind datakind1 = null;
_L9:
        return datakind1;
_L2:
        datakind1 = android.renderscript.Element.DataKind.USER;
        continue; /* Loop/switch isn't completed */
_L3:
        datakind1 = android.renderscript.Element.DataKind.PIXEL_L;
        continue; /* Loop/switch isn't completed */
_L4:
        datakind1 = android.renderscript.Element.DataKind.PIXEL_A;
        continue; /* Loop/switch isn't completed */
_L5:
        datakind1 = android.renderscript.Element.DataKind.PIXEL_LA;
        continue; /* Loop/switch isn't completed */
_L6:
        datakind1 = android.renderscript.Element.DataKind.PIXEL_RGB;
        continue; /* Loop/switch isn't completed */
_L7:
        datakind1 = android.renderscript.Element.DataKind.PIXEL_RGBA;
        if(true) goto _L9; else goto _L8
_L8:
    }

    static android.renderscript.Element.DataType convertType(Element.DataType datatype)
    {
        _cls1..SwitchMap.android.support.v8.renderscript.Element.DataType[datatype.ordinal()];
        JVM INSTR tableswitch 1 20: default 104
    //                   1 108
    //                   2 115
    //                   3 122
    //                   4 129
    //                   5 136
    //                   6 143
    //                   7 150
    //                   8 157
    //                   9 164
    //                   10 171
    //                   11 178
    //                   12 185
    //                   13 192
    //                   14 199
    //                   15 206
    //                   16 213
    //                   17 220
    //                   18 227
    //                   19 234
    //                   20 241;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21
_L1:
        android.renderscript.Element.DataType datatype1 = null;
_L23:
        return datatype1;
_L2:
        datatype1 = android.renderscript.Element.DataType.NONE;
        continue; /* Loop/switch isn't completed */
_L3:
        datatype1 = android.renderscript.Element.DataType.FLOAT_32;
        continue; /* Loop/switch isn't completed */
_L4:
        datatype1 = android.renderscript.Element.DataType.FLOAT_64;
        continue; /* Loop/switch isn't completed */
_L5:
        datatype1 = android.renderscript.Element.DataType.SIGNED_8;
        continue; /* Loop/switch isn't completed */
_L6:
        datatype1 = android.renderscript.Element.DataType.SIGNED_16;
        continue; /* Loop/switch isn't completed */
_L7:
        datatype1 = android.renderscript.Element.DataType.SIGNED_32;
        continue; /* Loop/switch isn't completed */
_L8:
        datatype1 = android.renderscript.Element.DataType.SIGNED_64;
        continue; /* Loop/switch isn't completed */
_L9:
        datatype1 = android.renderscript.Element.DataType.UNSIGNED_8;
        continue; /* Loop/switch isn't completed */
_L10:
        datatype1 = android.renderscript.Element.DataType.UNSIGNED_16;
        continue; /* Loop/switch isn't completed */
_L11:
        datatype1 = android.renderscript.Element.DataType.UNSIGNED_32;
        continue; /* Loop/switch isn't completed */
_L12:
        datatype1 = android.renderscript.Element.DataType.UNSIGNED_64;
        continue; /* Loop/switch isn't completed */
_L13:
        datatype1 = android.renderscript.Element.DataType.BOOLEAN;
        continue; /* Loop/switch isn't completed */
_L14:
        datatype1 = android.renderscript.Element.DataType.MATRIX_4X4;
        continue; /* Loop/switch isn't completed */
_L15:
        datatype1 = android.renderscript.Element.DataType.MATRIX_3X3;
        continue; /* Loop/switch isn't completed */
_L16:
        datatype1 = android.renderscript.Element.DataType.MATRIX_2X2;
        continue; /* Loop/switch isn't completed */
_L17:
        datatype1 = android.renderscript.Element.DataType.RS_ELEMENT;
        continue; /* Loop/switch isn't completed */
_L18:
        datatype1 = android.renderscript.Element.DataType.RS_TYPE;
        continue; /* Loop/switch isn't completed */
_L19:
        datatype1 = android.renderscript.Element.DataType.RS_ALLOCATION;
        continue; /* Loop/switch isn't completed */
_L20:
        datatype1 = android.renderscript.Element.DataType.RS_SAMPLER;
        continue; /* Loop/switch isn't completed */
_L21:
        datatype1 = android.renderscript.Element.DataType.RS_SCRIPT;
        if(true) goto _L23; else goto _L22
_L22:
    }

    static android.support.v8.renderscript.Element create(RenderScript renderscript, Element.DataType datatype)
    {
        RenderScriptThunker renderscriptthunker;
        Element element;
        renderscriptthunker = (RenderScriptThunker)renderscript;
        element = null;
        _cls1..SwitchMap.android.support.v8.renderscript.Element.DataType[datatype.ordinal()];
        JVM INSTR tableswitch 2 20: default 104
    //                   2 114
    //                   3 125
    //                   4 136
    //                   5 147
    //                   6 158
    //                   7 169
    //                   8 180
    //                   9 191
    //                   10 202
    //                   11 213
    //                   12 224
    //                   13 235
    //                   14 246
    //                   15 257
    //                   16 268
    //                   17 279
    //                   18 290
    //                   19 301
    //                   20 312;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L1:
        return new ElementThunker(renderscript, element);
_L2:
        element = Element.F32(renderscriptthunker.mN);
          goto _L1
_L3:
        element = Element.F64(renderscriptthunker.mN);
          goto _L1
_L4:
        element = Element.I8(renderscriptthunker.mN);
          goto _L1
_L5:
        element = Element.I16(renderscriptthunker.mN);
          goto _L1
_L6:
        element = Element.I32(renderscriptthunker.mN);
          goto _L1
_L7:
        element = Element.I64(renderscriptthunker.mN);
          goto _L1
_L8:
        element = Element.U8(renderscriptthunker.mN);
          goto _L1
_L9:
        element = Element.U16(renderscriptthunker.mN);
          goto _L1
_L10:
        element = Element.U32(renderscriptthunker.mN);
          goto _L1
_L11:
        element = Element.U64(renderscriptthunker.mN);
          goto _L1
_L12:
        element = Element.BOOLEAN(renderscriptthunker.mN);
          goto _L1
_L13:
        element = Element.MATRIX_4X4(renderscriptthunker.mN);
          goto _L1
_L14:
        element = Element.MATRIX_3X3(renderscriptthunker.mN);
          goto _L1
_L15:
        element = Element.MATRIX_2X2(renderscriptthunker.mN);
          goto _L1
_L16:
        element = Element.ELEMENT(renderscriptthunker.mN);
          goto _L1
_L17:
        element = Element.TYPE(renderscriptthunker.mN);
          goto _L1
_L18:
        element = Element.ALLOCATION(renderscriptthunker.mN);
          goto _L1
_L19:
        element = Element.SAMPLER(renderscriptthunker.mN);
          goto _L1
_L20:
        Element element1 = Element.SCRIPT(renderscriptthunker.mN);
        element = element1;
          goto _L1
        RSRuntimeException rsruntimeexception;
        rsruntimeexception;
        element;
        throw ExceptionThunker.convertException(rsruntimeexception);
    }

    public static android.support.v8.renderscript.Element createPixel(RenderScript renderscript, Element.DataType datatype, Element.DataKind datakind)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        ElementThunker elementthunker;
        try
        {
            elementthunker = new ElementThunker(renderscript, Element.createPixel(renderscriptthunker.mN, convertType(datatype), convertKind(datakind)));
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return elementthunker;
    }

    public static android.support.v8.renderscript.Element createVector(RenderScript renderscript, Element.DataType datatype, int i)
    {
        RenderScriptThunker renderscriptthunker = (RenderScriptThunker)renderscript;
        ElementThunker elementthunker;
        try
        {
            elementthunker = new ElementThunker(renderscript, Element.createVector(renderscriptthunker.mN, convertType(datatype), i));
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return elementthunker;
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

    public Element.DataKind getDataKind()
    {
        return mKind;
    }

    public Element.DataType getDataType()
    {
        return mType;
    }

    volatile BaseObj getNObj()
    {
        return getNObj();
    }

    Element getNObj()
    {
        return mN;
    }

    public android.support.v8.renderscript.Element getSubElement(int i)
    {
        ElementThunker elementthunker;
        try
        {
            elementthunker = new ElementThunker(mRS, mN.getSubElement(i));
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return elementthunker;
    }

    public int getSubElementArraySize(int i)
    {
        int j;
        try
        {
            j = mN.getSubElementArraySize(i);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return j;
    }

    public int getSubElementCount()
    {
        int i;
        try
        {
            i = mN.getSubElementCount();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return i;
    }

    public String getSubElementName(int i)
    {
        String s;
        try
        {
            s = mN.getSubElementName(i);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return s;
    }

    public int getSubElementOffsetBytes(int i)
    {
        int j;
        try
        {
            j = mN.getSubElementOffsetBytes(i);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return j;
    }

    public int getVectorSize()
    {
        int i;
        try
        {
            i = mN.getVectorSize();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return i;
    }

    public boolean isCompatible(android.support.v8.renderscript.Element element)
    {
        ElementThunker elementthunker = (ElementThunker)element;
        boolean flag;
        try
        {
            flag = elementthunker.mN.isCompatible(mN);
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return flag;
    }

    public boolean isComplex()
    {
        boolean flag;
        try
        {
            flag = mN.isComplex();
        }
        catch(RSRuntimeException rsruntimeexception)
        {
            throw ExceptionThunker.convertException(rsruntimeexception);
        }
        return flag;
    }

    Element mN;
}
