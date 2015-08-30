// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.db.names;


public interface DBNameResolver
{

    public abstract String getColumnName(Enum enum);

    public abstract String getTableName(Enum enum);
}
