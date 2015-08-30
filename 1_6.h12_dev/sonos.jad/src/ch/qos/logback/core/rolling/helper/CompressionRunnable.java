// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.rolling.helper;


// Referenced classes of package ch.qos.logback.core.rolling.helper:
//            Compressor

public class CompressionRunnable
    implements Runnable
{

    public CompressionRunnable(Compressor compressor1, String s, String s1, String s2)
    {
        compressor = compressor1;
        nameOfFile2Compress = s;
        nameOfCompressedFile = s1;
        innerEntryName = s2;
    }

    public void run()
    {
        compressor.compress(nameOfFile2Compress, nameOfCompressedFile, innerEntryName);
    }

    final Compressor compressor;
    final String innerEntryName;
    final String nameOfCompressedFile;
    final String nameOfFile2Compress;
}
