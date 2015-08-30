// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.db;

import ch.qos.logback.classic.db.names.ColumnName;
import ch.qos.logback.classic.db.names.DBNameResolver;
import ch.qos.logback.classic.db.names.TableName;

public class SQLBuilder
{

    public SQLBuilder()
    {
    }

    public static String buildCreateExceptionTableSQL(DBNameResolver dbnameresolver)
    {
        StringBuilder stringbuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringbuilder.append(dbnameresolver.getTableName(TableName.LOGGING_EVENT_EXCEPTION)).append(" (").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(" BIGINT NOT NULL, ").append(dbnameresolver.getColumnName(ColumnName.I)).append(" SMALLINT NOT NULL, ").append(dbnameresolver.getColumnName(ColumnName.TRACE_LINE)).append(" VARCHAR(254) NOT NULL, ").append("PRIMARY KEY (").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(", ").append(dbnameresolver.getColumnName(ColumnName.I)).append("), ").append("FOREIGN KEY (").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(") ").append("REFERENCES ").append(dbnameresolver.getTableName(TableName.LOGGING_EVENT)).append(" (").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(") ").append(")");
        return stringbuilder.toString();
    }

    public static String buildCreateLoggingEventTableSQL(DBNameResolver dbnameresolver)
    {
        StringBuilder stringbuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringbuilder.append(dbnameresolver.getTableName(TableName.LOGGING_EVENT)).append(" (").append(dbnameresolver.getColumnName(ColumnName.TIMESTMP)).append(" BIGINT NOT NULL, ").append(dbnameresolver.getColumnName(ColumnName.FORMATTED_MESSAGE)).append(" TEXT NOT NULL, ").append(dbnameresolver.getColumnName(ColumnName.LOGGER_NAME)).append(" VARCHAR(254) NOT NULL, ").append(dbnameresolver.getColumnName(ColumnName.LEVEL_STRING)).append(" VARCHAR(254) NOT NULL, ").append(dbnameresolver.getColumnName(ColumnName.THREAD_NAME)).append(" VARCHAR(254), ").append(dbnameresolver.getColumnName(ColumnName.REFERENCE_FLAG)).append(" SMALLINT, ").append(dbnameresolver.getColumnName(ColumnName.ARG0)).append(" VARCHAR(254), ").append(dbnameresolver.getColumnName(ColumnName.ARG1)).append(" VARCHAR(254), ").append(dbnameresolver.getColumnName(ColumnName.ARG2)).append(" VARCHAR(254), ").append(dbnameresolver.getColumnName(ColumnName.ARG3)).append(" VARCHAR(254), ").append(dbnameresolver.getColumnName(ColumnName.CALLER_FILENAME)).append(" VARCHAR(254), ").append(dbnameresolver.getColumnName(ColumnName.CALLER_CLASS)).append(" VARCHAR(254), ").append(dbnameresolver.getColumnName(ColumnName.CALLER_METHOD)).append(" VARCHAR(254), ").append(dbnameresolver.getColumnName(ColumnName.CALLER_LINE)).append(" CHAR(4), ").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT").append(")");
        return stringbuilder.toString();
    }

    public static String buildCreatePropertyTableSQL(DBNameResolver dbnameresolver)
    {
        StringBuilder stringbuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringbuilder.append(dbnameresolver.getTableName(TableName.LOGGING_EVENT_PROPERTY)).append(" (").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(" BIGINT NOT NULL, ").append(dbnameresolver.getColumnName(ColumnName.MAPPED_KEY)).append(" VARCHAR(254) NOT NULL, ").append(dbnameresolver.getColumnName(ColumnName.MAPPED_VALUE)).append(" VARCHAR(254) NOT NULL, ").append("PRIMARY KEY (").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(", ").append(dbnameresolver.getColumnName(ColumnName.MAPPED_KEY)).append("), ").append("FOREIGN KEY (").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(") ").append("REFERENCES ").append(dbnameresolver.getTableName(TableName.LOGGING_EVENT)).append(" (").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(") ").append(")");
        return stringbuilder.toString();
    }

    public static String buildInsertExceptionSQL(DBNameResolver dbnameresolver)
    {
        StringBuilder stringbuilder = new StringBuilder("INSERT INTO ");
        stringbuilder.append(dbnameresolver.getTableName(TableName.LOGGING_EVENT_EXCEPTION)).append(" (").append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(", ").append(dbnameresolver.getColumnName(ColumnName.I)).append(", ").append(dbnameresolver.getColumnName(ColumnName.TRACE_LINE)).append(") ").append("VALUES (?, ?, ?)");
        return stringbuilder.toString();
    }

    public static String buildInsertPropertiesSQL(DBNameResolver dbnameresolver)
    {
        StringBuilder stringbuilder = new StringBuilder("INSERT INTO ");
        stringbuilder.append(dbnameresolver.getTableName(TableName.LOGGING_EVENT_PROPERTY)).append(" (");
        stringbuilder.append(dbnameresolver.getColumnName(ColumnName.EVENT_ID)).append(", ");
        stringbuilder.append(dbnameresolver.getColumnName(ColumnName.MAPPED_KEY)).append(", ");
        stringbuilder.append(dbnameresolver.getColumnName(ColumnName.MAPPED_VALUE)).append(") ");
        stringbuilder.append("VALUES (?, ?, ?)");
        return stringbuilder.toString();
    }

    public static String buildInsertSQL(DBNameResolver dbnameresolver)
    {
        StringBuilder stringbuilder = new StringBuilder("INSERT INTO ");
        stringbuilder.append(dbnameresolver.getTableName(TableName.LOGGING_EVENT)).append(" (").append(dbnameresolver.getColumnName(ColumnName.TIMESTMP)).append(", ").append(dbnameresolver.getColumnName(ColumnName.FORMATTED_MESSAGE)).append(", ").append(dbnameresolver.getColumnName(ColumnName.LOGGER_NAME)).append(", ").append(dbnameresolver.getColumnName(ColumnName.LEVEL_STRING)).append(", ").append(dbnameresolver.getColumnName(ColumnName.THREAD_NAME)).append(", ").append(dbnameresolver.getColumnName(ColumnName.REFERENCE_FLAG)).append(", ").append(dbnameresolver.getColumnName(ColumnName.ARG0)).append(", ").append(dbnameresolver.getColumnName(ColumnName.ARG1)).append(", ").append(dbnameresolver.getColumnName(ColumnName.ARG2)).append(", ").append(dbnameresolver.getColumnName(ColumnName.ARG3)).append(", ").append(dbnameresolver.getColumnName(ColumnName.CALLER_FILENAME)).append(", ").append(dbnameresolver.getColumnName(ColumnName.CALLER_CLASS)).append(", ").append(dbnameresolver.getColumnName(ColumnName.CALLER_METHOD)).append(", ").append(dbnameresolver.getColumnName(ColumnName.CALLER_LINE)).append(") ").append("VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return stringbuilder.toString();
    }
}
