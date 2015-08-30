// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import java.io.Serializable;

public interface PreSerializationTransformer
{

    public abstract Serializable transform(Object obj);
}
