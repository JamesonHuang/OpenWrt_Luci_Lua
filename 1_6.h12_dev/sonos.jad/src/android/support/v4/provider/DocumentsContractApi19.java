// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;

class DocumentsContractApi19
{

    DocumentsContractApi19()
    {
    }

    public static boolean canRead(Context context, Uri uri)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        if(context.checkCallingOrSelfUriPermission(uri, 1) == 0 && !TextUtils.isEmpty(getRawType(context, uri)))
            flag = true;
        return flag;
    }

    public static boolean canWrite(Context context, Uri uri)
    {
        boolean flag = false;
        if(context.checkCallingOrSelfUriPermission(uri, 2) == 0) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        String s = getRawType(context, uri);
        int i = queryForInt(context, uri, "flags", 0);
        if(!TextUtils.isEmpty(s))
            if((i & 4) != 0)
                flag = true;
            else
            if("vnd.android.document/directory".equals(s) && (i & 8) != 0)
                flag = true;
            else
            if(!TextUtils.isEmpty(s) && (i & 2) != 0)
                flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static void closeQuietly(AutoCloseable autocloseable)
    {
        if(autocloseable == null)
            break MISSING_BLOCK_LABEL_10;
        autocloseable.close();
_L2:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        throw runtimeexception;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean delete(Context context, Uri uri)
    {
        return DocumentsContract.deleteDocument(context.getContentResolver(), uri);
    }

    public static boolean exists(Context context, Uri uri)
    {
        ContentResolver contentresolver;
        Cursor cursor;
        contentresolver = context.getContentResolver();
        cursor = null;
        int i;
        String as[] = new String[1];
        as[0] = "document_id";
        cursor = contentresolver.query(uri, as, null, null, null);
        i = cursor.getCount();
        boolean flag;
        if(i > 0)
            flag = true;
        else
            flag = false;
        closeQuietly(cursor);
_L2:
        return flag;
        Exception exception1;
        exception1;
        Log.w("DocumentFile", (new StringBuilder()).append("Failed query: ").append(exception1).toString());
        closeQuietly(cursor);
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        closeQuietly(cursor);
        throw exception;
    }

    public static String getName(Context context, Uri uri)
    {
        return queryForString(context, uri, "_display_name", null);
    }

    private static String getRawType(Context context, Uri uri)
    {
        return queryForString(context, uri, "mime_type", null);
    }

    public static String getType(Context context, Uri uri)
    {
        String s = getRawType(context, uri);
        if("vnd.android.document/directory".equals(s))
            s = null;
        return s;
    }

    public static boolean isDirectory(Context context, Uri uri)
    {
        return "vnd.android.document/directory".equals(getRawType(context, uri));
    }

    public static boolean isDocumentUri(Context context, Uri uri)
    {
        return DocumentsContract.isDocumentUri(context, uri);
    }

    public static boolean isFile(Context context, Uri uri)
    {
        String s = getRawType(context, uri);
        boolean flag;
        if("vnd.android.document/directory".equals(s) || TextUtils.isEmpty(s))
            flag = false;
        else
            flag = true;
        return flag;
    }

    public static long lastModified(Context context, Uri uri)
    {
        return queryForLong(context, uri, "last_modified", 0L);
    }

    public static long length(Context context, Uri uri)
    {
        return queryForLong(context, uri, "_size", 0L);
    }

    private static int queryForInt(Context context, Uri uri, String s, int i)
    {
        return (int)queryForLong(context, uri, s, i);
    }

    private static long queryForLong(Context context, Uri uri, String s, long l)
    {
        ContentResolver contentresolver;
        Cursor cursor;
        contentresolver = context.getContentResolver();
        cursor = null;
        String as[] = new String[1];
        as[0] = s;
        cursor = contentresolver.query(uri, as, null, null, null);
        if(!cursor.moveToFirst() || cursor.isNull(0)) goto _L2; else goto _L1
_L1:
        long l1 = cursor.getLong(0);
        l = l1;
        closeQuietly(cursor);
_L3:
        return l;
_L2:
        closeQuietly(cursor);
          goto _L3
        Exception exception1;
        exception1;
        Log.w("DocumentFile", (new StringBuilder()).append("Failed query: ").append(exception1).toString());
        closeQuietly(cursor);
          goto _L3
        Exception exception;
        exception;
        closeQuietly(cursor);
        throw exception;
    }

    private static String queryForString(Context context, Uri uri, String s, String s1)
    {
        ContentResolver contentresolver;
        Cursor cursor;
        contentresolver = context.getContentResolver();
        cursor = null;
        String as[] = new String[1];
        as[0] = s;
        cursor = contentresolver.query(uri, as, null, null, null);
        if(!cursor.moveToFirst() || cursor.isNull(0)) goto _L2; else goto _L1
_L1:
        String s2 = cursor.getString(0);
        s1 = s2;
        closeQuietly(cursor);
_L3:
        return s1;
_L2:
        closeQuietly(cursor);
          goto _L3
        Exception exception1;
        exception1;
        Log.w("DocumentFile", (new StringBuilder()).append("Failed query: ").append(exception1).toString());
        closeQuietly(cursor);
          goto _L3
        Exception exception;
        exception;
        closeQuietly(cursor);
        throw exception;
    }

    private static final String TAG = "DocumentFile";
}
