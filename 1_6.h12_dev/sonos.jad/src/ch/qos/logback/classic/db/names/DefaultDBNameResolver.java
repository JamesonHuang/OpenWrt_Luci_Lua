// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.db.names;


// Referenced classes of package ch.qos.logback.classic.db.names:
//            DBNameResolver

public class DefaultDBNameResolver
    implements DBNameResolver
{

    public DefaultDBNameResolver()
    {
    }

    public String getColumnName(Enum enum)
    {
        return enum.toString().toLowerCase();
    }

    public String getTableName(Enum enum)
    {
        return enum.toString().toLowerCase();
    }
}
