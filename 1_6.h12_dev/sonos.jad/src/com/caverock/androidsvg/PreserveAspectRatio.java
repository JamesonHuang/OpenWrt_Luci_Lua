// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.caverock.androidsvg;


public class PreserveAspectRatio
{
    public static final class Scale extends Enum
    {

        public static Scale valueOf(String s)
        {
            return (Scale)Enum.valueOf(com/caverock/androidsvg/PreserveAspectRatio$Scale, s);
        }

        public static Scale[] values()
        {
            return (Scale[])$VALUES.clone();
        }

        private static final Scale $VALUES[];
        public static final Scale Meet;
        public static final Scale Slice;

        static 
        {
            Meet = new Scale("Meet", 0);
            Slice = new Scale("Slice", 1);
            Scale ascale[] = new Scale[2];
            ascale[0] = Meet;
            ascale[1] = Slice;
            $VALUES = ascale;
        }

        private Scale(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Alignment extends Enum
    {

        public static Alignment valueOf(String s)
        {
            return (Alignment)Enum.valueOf(com/caverock/androidsvg/PreserveAspectRatio$Alignment, s);
        }

        public static Alignment[] values()
        {
            return (Alignment[])$VALUES.clone();
        }

        private static final Alignment $VALUES[];
        public static final Alignment None;
        public static final Alignment XMaxYMax;
        public static final Alignment XMaxYMid;
        public static final Alignment XMaxYMin;
        public static final Alignment XMidYMax;
        public static final Alignment XMidYMid;
        public static final Alignment XMidYMin;
        public static final Alignment XMinYMax;
        public static final Alignment XMinYMid;
        public static final Alignment XMinYMin;

        static 
        {
            None = new Alignment("None", 0);
            XMinYMin = new Alignment("XMinYMin", 1);
            XMidYMin = new Alignment("XMidYMin", 2);
            XMaxYMin = new Alignment("XMaxYMin", 3);
            XMinYMid = new Alignment("XMinYMid", 4);
            XMidYMid = new Alignment("XMidYMid", 5);
            XMaxYMid = new Alignment("XMaxYMid", 6);
            XMinYMax = new Alignment("XMinYMax", 7);
            XMidYMax = new Alignment("XMidYMax", 8);
            XMaxYMax = new Alignment("XMaxYMax", 9);
            Alignment aalignment[] = new Alignment[10];
            aalignment[0] = None;
            aalignment[1] = XMinYMin;
            aalignment[2] = XMidYMin;
            aalignment[3] = XMaxYMin;
            aalignment[4] = XMinYMid;
            aalignment[5] = XMidYMid;
            aalignment[6] = XMaxYMid;
            aalignment[7] = XMinYMax;
            aalignment[8] = XMidYMax;
            aalignment[9] = XMaxYMax;
            $VALUES = aalignment;
        }

        private Alignment(String s, int i)
        {
            super(s, i);
        }
    }


    public PreserveAspectRatio(Alignment alignment1, Scale scale1)
    {
        alignment = alignment1;
        scale = scale1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null)
            flag = false;
        else
        if(getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            PreserveAspectRatio preserveaspectratio = (PreserveAspectRatio)obj;
            if(alignment != preserveaspectratio.alignment)
                flag = false;
            else
            if(scale != preserveaspectratio.scale)
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public Alignment getAlignment()
    {
        return alignment;
    }

    public Scale getScale()
    {
        return scale;
    }

    public static final PreserveAspectRatio BOTTOM;
    public static final PreserveAspectRatio END;
    public static final PreserveAspectRatio FULLSCREEN;
    public static final PreserveAspectRatio FULLSCREEN_START;
    public static final PreserveAspectRatio LETTERBOX;
    public static final PreserveAspectRatio START;
    public static final PreserveAspectRatio STRETCH;
    public static final PreserveAspectRatio TOP;
    public static final PreserveAspectRatio UNSCALED = new PreserveAspectRatio(null, null);
    private Alignment alignment;
    private Scale scale;

    static 
    {
        STRETCH = new PreserveAspectRatio(Alignment.None, null);
        LETTERBOX = new PreserveAspectRatio(Alignment.XMidYMid, Scale.Meet);
        START = new PreserveAspectRatio(Alignment.XMinYMin, Scale.Meet);
        END = new PreserveAspectRatio(Alignment.XMaxYMax, Scale.Meet);
        TOP = new PreserveAspectRatio(Alignment.XMidYMin, Scale.Meet);
        BOTTOM = new PreserveAspectRatio(Alignment.XMidYMax, Scale.Meet);
        FULLSCREEN = new PreserveAspectRatio(Alignment.XMidYMid, Scale.Slice);
        FULLSCREEN_START = new PreserveAspectRatio(Alignment.XMinYMin, Scale.Slice);
    }
}
