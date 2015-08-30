// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import java.io.File;

// Referenced classes of package android.support.v4.provider:
//            RawDocumentFile, SingleDocumentFile, TreeDocumentFile, DocumentsContractApi21, 
//            DocumentsContractApi19

public abstract class DocumentFile
{

    DocumentFile(DocumentFile documentfile)
    {
        mParent = documentfile;
    }

    public static DocumentFile fromFile(File file)
    {
        return new RawDocumentFile(null, file);
    }

    public static DocumentFile fromSingleUri(Context context, Uri uri)
    {
        SingleDocumentFile singledocumentfile;
        if(android.os.Build.VERSION.SDK_INT >= 19)
            singledocumentfile = new SingleDocumentFile(null, context, uri);
        else
            singledocumentfile = null;
        return singledocumentfile;
    }

    public static DocumentFile fromTreeUri(Context context, Uri uri)
    {
        TreeDocumentFile treedocumentfile;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            treedocumentfile = new TreeDocumentFile(null, context, DocumentsContractApi21.prepareTreeUri(uri));
        else
            treedocumentfile = null;
        return treedocumentfile;
    }

    public static boolean isDocumentUri(Context context, Uri uri)
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT >= 19)
            flag = DocumentsContractApi19.isDocumentUri(context, uri);
        else
            flag = false;
        return flag;
    }

    public abstract boolean canRead();

    public abstract boolean canWrite();

    public abstract DocumentFile createDirectory(String s);

    public abstract DocumentFile createFile(String s, String s1);

    public abstract boolean delete();

    public abstract boolean exists();

    public DocumentFile findFile(String s)
    {
        DocumentFile adocumentfile[];
        int i;
        int j;
        adocumentfile = listFiles();
        i = adocumentfile.length;
        j = 0;
_L3:
        DocumentFile documentfile;
        if(j >= i)
            break MISSING_BLOCK_LABEL_44;
        documentfile = adocumentfile[j];
        if(!s.equals(documentfile.getName())) goto _L2; else goto _L1
_L1:
        return documentfile;
_L2:
        j++;
          goto _L3
        documentfile = null;
          goto _L1
    }

    public abstract String getName();

    public DocumentFile getParentFile()
    {
        return mParent;
    }

    public abstract String getType();

    public abstract Uri getUri();

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract long lastModified();

    public abstract long length();

    public abstract DocumentFile[] listFiles();

    public abstract boolean renameTo(String s);

    static final String TAG = "DocumentFile";
    private final DocumentFile mParent;
}
