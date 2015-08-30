// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.caverock.androidsvg;

import android.graphics.Bitmap;
import android.graphics.Typeface;

public abstract class SVGExternalFileResolver
{

    public SVGExternalFileResolver()
    {
    }

    public boolean isFormatSupported(String s)
    {
        return false;
    }

    public Typeface resolveFont(String s, int i, String s1)
    {
        return null;
    }

    public Bitmap resolveImage(String s)
    {
        return null;
    }
}
