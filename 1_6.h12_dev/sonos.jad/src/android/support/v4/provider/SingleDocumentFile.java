// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;

// Referenced classes of package android.support.v4.provider:
//            DocumentFile, DocumentsContractApi19

class SingleDocumentFile extends DocumentFile
{

    SingleDocumentFile(DocumentFile documentfile, Context context, Uri uri)
    {
        super(documentfile);
        mContext = context;
        mUri = uri;
    }

    public boolean canRead()
    {
        return DocumentsContractApi19.canRead(mContext, mUri);
    }

    public boolean canWrite()
    {
        return DocumentsContractApi19.canWrite(mContext, mUri);
    }

    public DocumentFile createDirectory(String s)
    {
        throw new UnsupportedOperationException();
    }

    public DocumentFile createFile(String s, String s1)
    {
        throw new UnsupportedOperationException();
    }

    public boolean delete()
    {
        return DocumentsContractApi19.delete(mContext, mUri);
    }

    public boolean exists()
    {
        return DocumentsContractApi19.exists(mContext, mUri);
    }

    public String getName()
    {
        return DocumentsContractApi19.getName(mContext, mUri);
    }

    public String getType()
    {
        return DocumentsContractApi19.getType(mContext, mUri);
    }

    public Uri getUri()
    {
        return mUri;
    }

    public boolean isDirectory()
    {
        return DocumentsContractApi19.isDirectory(mContext, mUri);
    }

    public boolean isFile()
    {
        return DocumentsContractApi19.isFile(mContext, mUri);
    }

    public long lastModified()
    {
        return DocumentsContractApi19.lastModified(mContext, mUri);
    }

    public long length()
    {
        return DocumentsContractApi19.length(mContext, mUri);
    }

    public DocumentFile[] listFiles()
    {
        throw new UnsupportedOperationException();
    }

    public boolean renameTo(String s)
    {
        throw new UnsupportedOperationException();
    }

    private Context mContext;
    private Uri mUri;
}
