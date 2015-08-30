// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.KeyEvent;

class KeyEventCompatHoneycomb
{

    KeyEventCompatHoneycomb()
    {
    }

    public static boolean metaStateHasModifiers(int i, int j)
    {
        return KeyEvent.metaStateHasModifiers(i, j);
    }

    public static boolean metaStateHasNoModifiers(int i)
    {
        return KeyEvent.metaStateHasNoModifiers(i);
    }

    public static int normalizeMetaState(int i)
    {
        return KeyEvent.normalizeMetaState(i);
    }
}
