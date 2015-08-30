// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.android;

import android.database.sqlite.*;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.db.SQLBuilder;
import ch.qos.logback.classic.db.names.DBNameResolver;
import ch.qos.logback.classic.db.names.DefaultDBNameResolver;
import ch.qos.logback.classic.spi.*;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.android.CommonPathUtil;
import java.io.File;
import java.sql.SQLException;
import java.util.*;

public class SQLiteAppender extends UnsynchronizedAppenderBase
{

    public SQLiteAppender()
    {
    }

    private String asStringTruncatedTo254(Object obj)
    {
        String s = null;
        if(obj != null)
            s = obj.toString();
        if(s != null && s.length() > 254)
            s = s.substring(0, 254);
        if(s == null)
            s = "";
        return s;
    }

    private void bindCallerData(SQLiteStatement sqlitestatement, StackTraceElement astacktraceelement[])
        throws SQLException
    {
        if(astacktraceelement != null && astacktraceelement.length > 0)
        {
            StackTraceElement stacktraceelement = astacktraceelement[0];
            if(stacktraceelement != null)
            {
                sqlitestatement.bindString(11, stacktraceelement.getFileName());
                sqlitestatement.bindString(12, stacktraceelement.getClassName());
                sqlitestatement.bindString(13, stacktraceelement.getMethodName());
                sqlitestatement.bindString(14, Integer.toString(stacktraceelement.getLineNumber()));
            }
        }
    }

    private void bindLoggingEvent(SQLiteStatement sqlitestatement, ILoggingEvent iloggingevent)
        throws SQLException
    {
        sqlitestatement.bindLong(1, iloggingevent.getTimeStamp());
        sqlitestatement.bindString(2, iloggingevent.getFormattedMessage());
        sqlitestatement.bindString(3, iloggingevent.getLoggerName());
        sqlitestatement.bindString(4, iloggingevent.getLevel().toString());
        sqlitestatement.bindString(5, iloggingevent.getThreadName());
        sqlitestatement.bindLong(6, computeReferenceMask(iloggingevent));
    }

    private void bindLoggingEventArguments(SQLiteStatement sqlitestatement, Object aobj[])
        throws SQLException
    {
        int i = 0;
        int j;
        if(aobj != null)
            j = aobj.length;
        else
            j = 0;
        for(; i < j && i < 4; i++)
            sqlitestatement.bindString(i + 7, asStringTruncatedTo254(aobj[i]));

    }

    private static short computeReferenceMask(ILoggingEvent iloggingevent)
    {
        short word0 = 0;
        int i;
        int j;
        if(iloggingevent.getMDCPropertyMap() != null)
            i = iloggingevent.getMDCPropertyMap().keySet().size();
        else
            i = 0;
        if(iloggingevent.getLoggerContextVO().getPropertyMap() != null)
            j = iloggingevent.getLoggerContextVO().getPropertyMap().size();
        else
            j = 0;
        if(i > 0 || j > 0)
            word0 = 1;
        if(iloggingevent.getThrowableProxy() != null)
            word0 |= 2;
        return word0;
    }

    private void insertException(SQLiteStatement sqlitestatement, String s, short word0, long l)
        throws SQLException
    {
        sqlitestatement.bindLong(1, l);
        sqlitestatement.bindLong(2, word0);
        sqlitestatement.bindString(3, s);
        sqlitestatement.executeInsert();
    }

    private void insertProperties(Map map, long l)
        throws SQLException
    {
        SQLiteStatement sqlitestatement;
        if(map.size() <= 0)
            break MISSING_BLOCK_LABEL_120;
        sqlitestatement = db.compileStatement(insertPropertiesSQL);
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); sqlitestatement.executeInsert())
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            sqlitestatement.bindLong(1, l);
            sqlitestatement.bindString(2, (String)entry.getKey());
            sqlitestatement.bindString(3, (String)entry.getValue());
        }

        break MISSING_BLOCK_LABEL_115;
        Exception exception;
        exception;
        sqlitestatement.close();
        throw exception;
        sqlitestatement.close();
    }

    private void insertThrowable(IThrowableProxy ithrowableproxy, long l)
        throws SQLException
    {
        SQLiteStatement sqlitestatement;
        int i;
        sqlitestatement = db.compileStatement(insertExceptionSQL);
        i = 0;
_L2:
        if(ithrowableproxy == null)
            break; /* Loop/switch isn't completed */
        IThrowableProxy ithrowableproxy1;
        StringBuilder stringbuilder = new StringBuilder();
        ThrowableProxyUtil.subjoinFirstLine(stringbuilder, ithrowableproxy);
        String s = stringbuilder.toString();
        short word0 = (short)(i + 1);
        insertException(sqlitestatement, s, i, l);
        int j = ithrowableproxy.getCommonFrames();
        ch.qos.logback.classic.spi.StackTraceElementProxy astacktraceelementproxy[] = ithrowableproxy.getStackTraceElementProxyArray();
        i = word0;
        for(int k = 0; k < astacktraceelementproxy.length - j;)
        {
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append('\t');
            ThrowableProxyUtil.subjoinSTEP(stringbuilder1, astacktraceelementproxy[k]);
            String s1 = stringbuilder1.toString();
            short word1 = (short)(i + 1);
            insertException(sqlitestatement, s1, i, l);
            k++;
            i = word1;
        }

        if(j > 0)
        {
            StringBuilder stringbuilder2 = new StringBuilder();
            stringbuilder2.append('\t').append("... ").append(j).append(" common frames omitted");
            String s2 = stringbuilder2.toString();
            short word2 = (short)(i + 1);
            insertException(sqlitestatement, s2, i, l);
            i = word2;
        }
        ithrowableproxy1 = ithrowableproxy.getCause();
        ithrowableproxy = ithrowableproxy1;
        if(true) goto _L2; else goto _L1
_L1:
        sqlitestatement.close();
        return;
        Exception exception;
        exception;
        sqlitestatement.close();
        throw exception;
    }

    private Map mergePropertyMaps(ILoggingEvent iloggingevent)
    {
        HashMap hashmap = new HashMap();
        Map map = iloggingevent.getLoggerContextVO().getPropertyMap();
        if(map != null)
            hashmap.putAll(map);
        Map map1 = iloggingevent.getMDCPropertyMap();
        if(map1 != null)
            hashmap.putAll(map1);
        return hashmap;
    }

    private void secondarySubAppend(ILoggingEvent iloggingevent, long l)
        throws SQLException
    {
        insertProperties(mergePropertyMaps(iloggingevent), l);
        if(iloggingevent.getThrowableProxy() != null)
            insertThrowable(iloggingevent.getThrowableProxy(), l);
    }

    private long subAppend(ILoggingEvent iloggingevent, SQLiteStatement sqlitestatement)
        throws SQLException
    {
        long l;
        bindLoggingEvent(sqlitestatement, iloggingevent);
        bindLoggingEventArguments(sqlitestatement, iloggingevent.getArgumentArray());
        bindCallerData(sqlitestatement, iloggingevent.getCallerData());
        l = -1L;
        long l1 = sqlitestatement.executeInsert();
        l = l1;
_L2:
        return l;
        SQLiteException sqliteexception;
        sqliteexception;
        addWarn("Failed to insert loggingEvent", sqliteexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void append(ILoggingEvent iloggingevent)
    {
        if(!isStarted())
            break MISSING_BLOCK_LABEL_116;
        SQLiteStatement sqlitestatement = db.compileStatement(insertSQL);
        db.beginTransaction();
        long l = subAppend(iloggingevent, sqlitestatement);
        if(l != -1L)
        {
            secondarySubAppend(iloggingevent, l);
            db.setTransactionSuccessful();
        }
        if(db.inTransaction())
            db.endTransaction();
        sqlitestatement.close();
        break MISSING_BLOCK_LABEL_116;
        Exception exception;
        exception;
        if(db.inTransaction())
            db.endTransaction();
        sqlitestatement.close();
        throw exception;
        Throwable throwable;
        throwable;
        addError("Cannot append event", throwable);
    }

    public volatile void append(Object obj)
    {
        append((ILoggingEvent)obj);
    }

    protected void finalize()
        throws Throwable
    {
        db.close();
    }

    public void setDbNameResolver(DBNameResolver dbnameresolver)
    {
        dbNameResolver = dbnameresolver;
    }

    public void start()
    {
        String s;
        boolean flag;
        s = null;
        flag = true;
        started = false;
        if(getContext() != null)
            s = getContext().getProperty("PACKAGE_NAME");
        if(s != null && s.length() != 0) goto _L2; else goto _L1
_L1:
        addError("Cannot create database without package name");
_L4:
        return;
_L2:
        try
        {
            File file = new File(CommonPathUtil.getDatabaseDirectoryPath(s), "logback.db");
            file.getParentFile().mkdirs();
            db = SQLiteDatabase.openOrCreateDatabase(file.getPath(), null);
        }
        catch(SQLiteException sqliteexception)
        {
            addError("Cannot open database", sqliteexception);
            flag = false;
        }
        if(flag)
        {
            if(dbNameResolver == null)
                dbNameResolver = new DefaultDBNameResolver();
            insertExceptionSQL = SQLBuilder.buildInsertExceptionSQL(dbNameResolver);
            insertPropertiesSQL = SQLBuilder.buildInsertPropertiesSQL(dbNameResolver);
            insertSQL = SQLBuilder.buildInsertSQL(dbNameResolver);
            try
            {
                db.execSQL(SQLBuilder.buildCreateLoggingEventTableSQL(dbNameResolver));
                db.execSQL(SQLBuilder.buildCreatePropertyTableSQL(dbNameResolver));
                db.execSQL(SQLBuilder.buildCreateExceptionTableSQL(dbNameResolver));
                super.start();
                started = true;
            }
            catch(SQLiteException sqliteexception1)
            {
                addError("Cannot create database tables", sqliteexception1);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void stop()
    {
        db.close();
    }

    private static final int ARG0_INDEX = 7;
    private static final int CALLER_CLASS_INDEX = 12;
    private static final int CALLER_FILENAME_INDEX = 11;
    private static final int CALLER_LINE_INDEX = 14;
    private static final int CALLER_METHOD_INDEX = 13;
    private static final short EXCEPTION_EXISTS = 2;
    private static final int FORMATTED_MESSAGE_INDEX = 2;
    private static final int LEVEL_STRING_INDEX = 4;
    private static final int LOGGER_NAME_INDEX = 3;
    private static final short PROPERTIES_EXIST = 1;
    private static final int REFERENCE_FLAG_INDEX = 6;
    private static final int THREAD_NAME_INDEX = 5;
    private static final int TIMESTMP_INDEX = 1;
    private SQLiteDatabase db;
    private DBNameResolver dbNameResolver;
    private String insertExceptionSQL;
    private String insertPropertiesSQL;
    private String insertSQL;
}
