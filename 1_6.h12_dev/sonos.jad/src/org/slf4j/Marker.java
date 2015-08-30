// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.slf4j;

import java.io.Serializable;
import java.util.Iterator;

public interface Marker
    extends Serializable
{

    public abstract void add(Marker marker);

    public abstract boolean contains(String s);

    public abstract boolean contains(Marker marker);

    public abstract boolean equals(Object obj);

    public abstract String getName();

    public abstract boolean hasChildren();

    public abstract boolean hasReferences();

    public abstract int hashCode();

    public abstract Iterator iterator();

    public abstract boolean remove(Marker marker);

    public static final String ANY_MARKER = "*";
    public static final String ANY_NON_NULL_MARKER = "+";
}
