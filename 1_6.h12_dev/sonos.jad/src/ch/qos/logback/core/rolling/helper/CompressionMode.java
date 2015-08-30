// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;


public final class CompressionMode extends Enum
{

    private CompressionMode(String s, int i)
    {
        super(s, i);
    }

    public static CompressionMode valueOf(String s)
    {
        return (CompressionMode)Enum.valueOf(ch/qos/logback/core/rolling/helper/CompressionMode, s);
    }

    public static CompressionMode[] values()
    {
        return (CompressionMode[])$VALUES.clone();
    }

    private static final CompressionMode $VALUES[];
    public static final CompressionMode GZ;
    public static final CompressionMode NONE;
    public static final CompressionMode ZIP;

    static 
    {
        NONE = new CompressionMode("NONE", 0);
        GZ = new CompressionMode("GZ", 1);
        ZIP = new CompressionMode("ZIP", 2);
        CompressionMode acompressionmode[] = new CompressionMode[3];
        acompressionmode[0] = NONE;
        acompressionmode[1] = GZ;
        acompressionmode[2] = ZIP;
        $VALUES = acompressionmode;
    }
}
