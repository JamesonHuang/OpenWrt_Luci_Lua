// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.*;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.*;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

public class FileProvider extends ContentProvider
{
    static class SimplePathStrategy
        implements PathStrategy
    {

        public void addRoot(String s, File file)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("Name must not be empty");
            File file1;
            try
            {
                file1 = file.getCanonicalFile();
            }
            catch(IOException ioexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve canonical path for ").append(file).toString(), ioexception);
            }
            mRoots.put(s, file1);
        }

        public File getFileForUri(Uri uri)
        {
            String s = uri.getEncodedPath();
            int i = s.indexOf('/', 1);
            String s1 = Uri.decode(s.substring(1, i));
            String s2 = Uri.decode(s.substring(i + 1));
            File file = (File)mRoots.get(s1);
            if(file == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Unable to find configured root for ").append(uri).toString());
            File file1 = new File(file, s2);
            File file2;
            try
            {
                file2 = file1.getCanonicalFile();
            }
            catch(IOException ioexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve canonical path for ").append(file1).toString());
            }
            if(!file2.getPath().startsWith(file.getPath()))
                throw new SecurityException("Resolved path jumped beyond configured root");
            else
                return file2;
        }

        public Uri getUriForFile(File file)
        {
            String s;
            java.util.Map.Entry entry;
            Iterator iterator;
            try
            {
                s = file.getCanonicalPath();
            }
            catch(IOException ioexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve canonical path for ").append(file).toString());
            }
            entry = null;
            iterator = mRoots.entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator.next();
                String s4 = ((File)entry1.getValue()).getPath();
                if(s.startsWith(s4) && (entry == null || s4.length() > ((File)entry.getValue()).getPath().length()))
                    entry = entry1;
            } while(true);
            if(entry == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to find configured root that contains ").append(s).toString());
            String s1 = ((File)entry.getValue()).getPath();
            String s2;
            String s3;
            if(s1.endsWith("/"))
                s2 = s.substring(s1.length());
            else
                s2 = s.substring(1 + s1.length());
            s3 = (new StringBuilder()).append(Uri.encode((String)entry.getKey())).append('/').append(Uri.encode(s2, "/")).toString();
            return (new android.net.Uri.Builder()).scheme("content").authority(mAuthority).encodedPath(s3).build();
        }

        private final String mAuthority;
        private final HashMap mRoots = new HashMap();

        public SimplePathStrategy(String s)
        {
            mAuthority = s;
        }
    }

    static interface PathStrategy
    {

        public abstract File getFileForUri(Uri uri);

        public abstract Uri getUriForFile(File file);
    }


    public FileProvider()
    {
    }

    private static transient File buildPath(File file, String as[])
    {
        int i = as.length;
        int j = 0;
        File file1 = file;
        while(j < i) 
        {
            String s = as[j];
            File file2;
            if(s != null)
                file2 = new File(file1, s);
            else
                file2 = file1;
            j++;
            file1 = file2;
        }
        return file1;
    }

    private static Object[] copyOf(Object aobj[], int i)
    {
        Object aobj1[] = new Object[i];
        System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, i);
        return aobj1;
    }

    private static String[] copyOf(String as[], int i)
    {
        String as1[] = new String[i];
        System.arraycopy(as, 0, as1, 0, i);
        return as1;
    }

    private static PathStrategy getPathStrategy(Context context, String s)
    {
        HashMap hashmap = sCache;
        hashmap;
        JVM INSTR monitorenter ;
        PathStrategy pathstrategy = (PathStrategy)sCache.get(s);
        if(pathstrategy != null)
            break MISSING_BLOCK_LABEL_44;
        PathStrategy pathstrategy1 = parsePathStrategy(context, s);
        pathstrategy = pathstrategy1;
        sCache.put(s, pathstrategy);
        hashmap;
        JVM INSTR monitorexit ;
        return pathstrategy;
        IOException ioexception;
        ioexception;
        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", ioexception);
        Exception exception;
        exception;
        throw exception;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", xmlpullparserexception);
    }

    public static Uri getUriForFile(Context context, String s, File file)
    {
        return getPathStrategy(context, s).getUriForFile(file);
    }

    private static int modeToMode(String s)
    {
        int i;
        if("r".equals(s))
            i = 0x10000000;
        else
        if("w".equals(s) || "wt".equals(s))
            i = 0x2c000000;
        else
        if("wa".equals(s))
            i = 0x2a000000;
        else
        if("rw".equals(s))
            i = 0x38000000;
        else
        if("rwt".equals(s))
            i = 0x3c000000;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid mode: ").append(s).toString());
        return i;
    }

    private static PathStrategy parsePathStrategy(Context context, String s)
        throws IOException, XmlPullParserException
    {
        SimplePathStrategy simplepathstrategy;
        XmlResourceParser xmlresourceparser;
        simplepathstrategy = new SimplePathStrategy(s);
        xmlresourceparser = context.getPackageManager().resolveContentProvider(s, 128).loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if(xmlresourceparser == null)
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
_L7:
        int i = xmlresourceparser.next();
        if(i == 1) goto _L2; else goto _L1
_L1:
        String s1;
        String s2;
        String s3;
        File file;
        if(i != 2)
            continue; /* Loop/switch isn't completed */
        s1 = xmlresourceparser.getName();
        s2 = xmlresourceparser.getAttributeValue(null, "name");
        s3 = xmlresourceparser.getAttributeValue(null, "path");
        file = null;
        if(!"root-path".equals(s1)) goto _L4; else goto _L3
_L3:
        File file4 = DEVICE_ROOT;
        String as3[] = new String[1];
        as3[0] = s3;
        file = buildPath(file4, as3);
_L5:
        if(file != null)
            simplepathstrategy.addRoot(s2, file);
        continue; /* Loop/switch isn't completed */
_L4:
        if("files-path".equals(s1))
        {
            File file3 = context.getFilesDir();
            String as2[] = new String[1];
            as2[0] = s3;
            file = buildPath(file3, as2);
        } else
        if("cache-path".equals(s1))
        {
            File file2 = context.getCacheDir();
            String as1[] = new String[1];
            as1[0] = s3;
            file = buildPath(file2, as1);
        } else
        if("external-path".equals(s1))
        {
            File file1 = Environment.getExternalStorageDirectory();
            String as[] = new String[1];
            as[0] = s3;
            file = buildPath(file1, as);
        }
        if(true) goto _L5; else goto _L2
_L2:
        return simplepathstrategy;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void attachInfo(Context context, ProviderInfo providerinfo)
    {
        super.attachInfo(context, providerinfo);
        if(providerinfo.exported)
            throw new SecurityException("Provider must not be exported");
        if(!providerinfo.grantUriPermissions)
        {
            throw new SecurityException("Provider must grant uri permissions");
        } else
        {
            mStrategy = getPathStrategy(context, providerinfo.authority);
            return;
        }
    }

    public int delete(Uri uri, String s, String as[])
    {
        int i;
        if(mStrategy.getFileForUri(uri).delete())
            i = 1;
        else
            i = 0;
        return i;
    }

    public String getType(Uri uri)
    {
        File file;
        int i;
        file = mStrategy.getFileForUri(uri);
        i = file.getName().lastIndexOf('.');
        if(i < 0) goto _L2; else goto _L1
_L1:
        String s;
        String s1 = file.getName().substring(i + 1);
        s = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s1);
        if(s == null) goto _L2; else goto _L3
_L3:
        return s;
_L2:
        s = "application/octet-stream";
        if(true) goto _L3; else goto _L4
_L4:
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
    {
        throw new UnsupportedOperationException("No external inserts");
    }

    public boolean onCreate()
    {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String s)
        throws FileNotFoundException
    {
        return ParcelFileDescriptor.open(mStrategy.getFileForUri(uri), modeToMode(s));
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        File file = mStrategy.getFileForUri(uri);
        if(as == null)
            as = COLUMNS;
        String as2[] = new String[as.length];
        Object aobj[] = new Object[as.length];
        String as3[] = as;
        int i = as3.length;
        int j = 0;
        int k = 0;
        while(j < i) 
        {
            String s2 = as3[j];
            String as4[];
            Object aobj1[];
            MatrixCursor matrixcursor;
            int l;
            if("_display_name".equals(s2))
            {
                as2[k] = "_display_name";
                l = k + 1;
                aobj[k] = file.getName();
            } else
            if("_size".equals(s2))
            {
                as2[k] = "_size";
                l = k + 1;
                aobj[k] = Long.valueOf(file.length());
            } else
            {
                l = k;
            }
            j++;
            k = l;
        }
        as4 = copyOf(as2, k);
        aobj1 = copyOf(aobj, k);
        matrixcursor = new MatrixCursor(as4, 1);
        matrixcursor.addRow(aobj1);
        return matrixcursor;
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        throw new UnsupportedOperationException("No external updates");
    }

    private static final String ATTR_NAME = "name";
    private static final String ATTR_PATH = "path";
    private static final String COLUMNS[];
    private static final File DEVICE_ROOT = new File("/");
    private static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";
    private static final String TAG_CACHE_PATH = "cache-path";
    private static final String TAG_EXTERNAL = "external-path";
    private static final String TAG_FILES_PATH = "files-path";
    private static final String TAG_ROOT_PATH = "root-path";
    private static HashMap sCache = new HashMap();
    private PathStrategy mStrategy;

    static 
    {
        String as[] = new String[2];
        as[0] = "_display_name";
        as[1] = "_size";
        COLUMNS = as;
    }
}
