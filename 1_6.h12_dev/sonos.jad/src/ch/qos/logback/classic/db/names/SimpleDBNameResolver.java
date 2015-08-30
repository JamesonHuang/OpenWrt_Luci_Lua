// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.db.names;


// Referenced classes of package ch.qos.logback.classic.db.names:
//            DBNameResolver

public class SimpleDBNameResolver
    implements DBNameResolver
{

    public SimpleDBNameResolver()
    {
        tableNamePrefix = "";
        tableNameSuffix = "";
        columnNamePrefix = "";
        columnNameSuffix = "";
    }

    public String getColumnName(Enum enum)
    {
        return (new StringBuilder()).append(columnNamePrefix).append(enum.name().toLowerCase()).append(columnNameSuffix).toString();
    }

    public String getTableName(Enum enum)
    {
        return (new StringBuilder()).append(tableNamePrefix).append(enum.name().toLowerCase()).append(tableNameSuffix).toString();
    }

    public void setColumnNamePrefix(String s)
    {
        if(s == null)
            s = "";
        columnNamePrefix = s;
    }

    public void setColumnNameSuffix(String s)
    {
        if(s == null)
            s = "";
        columnNameSuffix = s;
    }

    public void setTableNamePrefix(String s)
    {
        if(s == null)
            s = "";
        tableNamePrefix = s;
    }

    public void setTableNameSuffix(String s)
    {
        if(s == null)
            s = "";
        tableNameSuffix = s;
    }

    private String columnNamePrefix;
    private String columnNameSuffix;
    private String tableNamePrefix;
    private String tableNameSuffix;
}
