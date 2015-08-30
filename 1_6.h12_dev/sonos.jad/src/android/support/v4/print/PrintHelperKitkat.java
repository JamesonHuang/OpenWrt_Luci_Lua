// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.print;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.print.*;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import java.io.*;

class PrintHelperKitkat
{
    public static interface OnPrintFinishCallback
    {

        public abstract void onFinish();
    }


    PrintHelperKitkat(Context context)
    {
        mDecodeOptions = null;
        mScaleMode = 2;
        mColorMode = 2;
        mOrientation = 1;
        mContext = context;
    }

    private Matrix getMatrix(int i, int j, RectF rectf, int k)
    {
        Matrix matrix = new Matrix();
        float f = rectf.width() / (float)i;
        float f1;
        if(k == 2)
            f1 = Math.max(f, rectf.height() / (float)j);
        else
            f1 = Math.min(f, rectf.height() / (float)j);
        matrix.postScale(f1, f1);
        matrix.postTranslate((rectf.width() - f1 * (float)i) / 2.0F, (rectf.height() - f1 * (float)j) / 2.0F);
        return matrix;
    }

    private Bitmap loadBitmap(Uri uri, android.graphics.BitmapFactory.Options options)
        throws FileNotFoundException
    {
        InputStream inputstream;
        if(uri == null || mContext == null)
            throw new IllegalArgumentException("bad argument to loadBitmap");
        inputstream = null;
        Bitmap bitmap;
        inputstream = mContext.getContentResolver().openInputStream(uri);
        bitmap = BitmapFactory.decodeStream(inputstream, null, options);
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception1)
            {
                Log.w("PrintHelperKitkat", "close fail ", ioexception1);
            }
        return bitmap;
        Exception exception;
        exception;
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception)
            {
                Log.w("PrintHelperKitkat", "close fail ", ioexception);
            }
        throw exception;
    }

    private Bitmap loadConstrainedBitmap(Uri uri, int i)
        throws FileNotFoundException
    {
        Bitmap bitmap;
        int j;
        int k;
        bitmap = null;
        if(i <= 0 || uri == null || mContext == null)
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        loadBitmap(uri, options);
        j = options.outWidth;
        k = options.outHeight;
        if(j > 0 && k > 0) goto _L2; else goto _L1
_L1:
        return bitmap;
_L2:
        android.graphics.BitmapFactory.Options options1;
        int l = Math.max(j, k);
        int i1;
        for(i1 = 1; l > i; i1 <<= 1)
            l >>>= 1;

        if(i1 <= 0 || Math.min(j, k) / i1 <= 0)
            continue; /* Loop/switch isn't completed */
        synchronized(mLock)
        {
            mDecodeOptions = new android.graphics.BitmapFactory.Options();
            mDecodeOptions.inMutable = true;
            mDecodeOptions.inSampleSize = i1;
            options1 = mDecodeOptions;
        }
        Bitmap bitmap1 = loadBitmap(uri, options1);
        bitmap = bitmap1;
        Object obj2 = mLock;
        obj2;
        JVM INSTR monitorenter ;
        mDecodeOptions = null;
        if(true) goto _L1; else goto _L3
_L3:
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Exception exception1;
        exception1;
        synchronized(mLock)
        {
            mDecodeOptions = null;
        }
        throw exception1;
        exception2;
        obj1;
        JVM INSTR monitorexit ;
        throw exception2;
    }

    public int getColorMode()
    {
        return mColorMode;
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public int getScaleMode()
    {
        return mScaleMode;
    }

    public void printBitmap(final String jobName, final Bitmap bitmap, final OnPrintFinishCallback callback)
    {
        if(bitmap != null)
        {
            final int fittingMode = mScaleMode;
            PrintManager printmanager = (PrintManager)mContext.getSystemService("print");
            android.print.PrintAttributes.MediaSize mediasize = android.print.PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
            if(bitmap.getWidth() > bitmap.getHeight())
                mediasize = android.print.PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
            PrintAttributes printattributes = (new android.print.PrintAttributes.Builder()).setMediaSize(mediasize).setColorMode(mColorMode).build();
            printmanager.print(jobName, new PrintDocumentAdapter() {

                public void onFinish()
                {
                    if(callback != null)
                        callback.onFinish();
                }

                public void onLayout(PrintAttributes printattributes1, PrintAttributes printattributes2, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.LayoutResultCallback layoutresultcallback, Bundle bundle)
                {
                    boolean flag = true;
                    mAttributes = printattributes2;
                    android.print.PrintDocumentInfo printdocumentinfo = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(flag).setPageCount(flag).build();
                    if(printattributes2.equals(printattributes1))
                        flag = false;
                    layoutresultcallback.onLayoutFinished(printdocumentinfo, flag);
                }

                public void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.WriteResultCallback writeresultcallback)
                {
                    PrintedPdfDocument printedpdfdocument = new PrintedPdfDocument(mContext, mAttributes);
                    android.graphics.pdf.PdfDocument.Page page = printedpdfdocument.startPage(1);
                    RectF rectf = new RectF(page.getInfo().getContentRect());
                    Matrix matrix = getMatrix(bitmap.getWidth(), bitmap.getHeight(), rectf, fittingMode);
                    page.getCanvas().drawBitmap(bitmap, matrix, null);
                    printedpdfdocument.finishPage(page);
                    printedpdfdocument.writeTo(new FileOutputStream(parcelfiledescriptor.getFileDescriptor()));
                    PageRange apagerange1[] = new PageRange[1];
                    apagerange1[0] = PageRange.ALL_PAGES;
                    writeresultcallback.onWriteFinished(apagerange1);
_L1:
                    if(printedpdfdocument != null)
                        printedpdfdocument.close();
                    if(parcelfiledescriptor == null)
                        break MISSING_BLOCK_LABEL_150;
                    parcelfiledescriptor.close();
_L2:
                    return;
                    IOException ioexception1;
                    ioexception1;
                    Log.e("PrintHelperKitkat", "Error writing printed content", ioexception1);
                    writeresultcallback.onWriteFailed(null);
                      goto _L1
                    Exception exception;
                    exception;
                    if(printedpdfdocument != null)
                        printedpdfdocument.close();
                    IOException ioexception2;
                    if(parcelfiledescriptor != null)
                        try
                        {
                            parcelfiledescriptor.close();
                        }
                        catch(IOException ioexception) { }
                    throw exception;
                    ioexception2;
                      goto _L2
                }

                private PrintAttributes mAttributes;
                final PrintHelperKitkat this$0;
                final Bitmap val$bitmap;
                final OnPrintFinishCallback val$callback;
                final int val$fittingMode;
                final String val$jobName;

            
            {
                this$0 = PrintHelperKitkat.this;
                jobName = s;
                bitmap = bitmap1;
                fittingMode = i;
                callback = onprintfinishcallback;
                super();
            }
            }
, printattributes);
        }
    }

    public void printBitmap(final String jobName, final Uri imageFile, final OnPrintFinishCallback callback)
        throws FileNotFoundException
    {
        PrintDocumentAdapter printdocumentadapter;
        PrintManager printmanager;
        android.print.PrintAttributes.Builder builder;
        printdocumentadapter = new PrintDocumentAdapter() {

            private void cancelLoad()
            {
                Object obj = mLock;
                obj;
                JVM INSTR monitorenter ;
                if(mDecodeOptions != null)
                {
                    mDecodeOptions.requestCancelDecode();
                    mDecodeOptions = null;
                }
                return;
            }

            public void onFinish()
            {
                super.onFinish();
                cancelLoad();
                if(mLoadBitmap != null)
                    mLoadBitmap.cancel(true);
                if(callback != null)
                    callback.onFinish();
            }

            public void onLayout(final PrintAttributes oldPrintAttributes, final PrintAttributes newPrintAttributes, final CancellationSignal cancellationSignal, final android.print.PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle)
            {
                boolean flag = true;
                mAttributes = newPrintAttributes;
                if(cancellationSignal.isCanceled())
                    layoutResultCallback.onLayoutCancelled();
                else
                if(mBitmap != null)
                {
                    android.print.PrintDocumentInfo printdocumentinfo = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(flag).setPageCount(flag).build();
                    if(newPrintAttributes.equals(oldPrintAttributes))
                        flag = false;
                    layoutResultCallback.onLayoutFinished(printdocumentinfo, flag);
                } else
                {
                    mLoadBitmap = (new AsyncTask() {

                        protected transient Bitmap doInBackground(Uri auri[])
                        {
                            Bitmap bitmap1 = loadConstrainedBitmap(imageFile, 3500);
                            Bitmap bitmap = bitmap1;
_L2:
                            return bitmap;
                            FileNotFoundException filenotfoundexception;
                            filenotfoundexception;
                            bitmap = null;
                            if(true) goto _L2; else goto _L1
_L1:
                        }

                        protected volatile Object doInBackground(Object aobj[])
                        {
                            return doInBackground((Uri[])aobj);
                        }

                        protected void onCancelled(Bitmap bitmap)
                        {
                            layoutResultCallback.onLayoutCancelled();
                            mLoadBitmap = null;
                        }

                        protected volatile void onCancelled(Object obj)
                        {
                            onCancelled((Bitmap)obj);
                        }

                        protected void onPostExecute(Bitmap bitmap)
                        {
                            boolean flag1 = true;
                            super.onPostExecute(bitmap);
                            mBitmap = bitmap;
                            if(bitmap != null)
                            {
                                android.print.PrintDocumentInfo printdocumentinfo1 = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(flag1).setPageCount(flag1).build();
                                if(newPrintAttributes.equals(oldPrintAttributes))
                                    flag1 = false;
                                layoutResultCallback.onLayoutFinished(printdocumentinfo1, flag1);
                            } else
                            {
                                layoutResultCallback.onLayoutFailed(null);
                            }
                            mLoadBitmap = null;
                        }

                        protected volatile void onPostExecute(Object obj)
                        {
                            onPostExecute((Bitmap)obj);
                        }

                        protected void onPreExecute()
                        {
                            cancellationSignal.setOnCancelListener(new android.os.CancellationSignal.OnCancelListener() {

                                public void onCancel()
                                {
                                    cancelLoad();
                                    cancel(false);
                                }

                                final _cls1 this$2;

                        
                        {
                            this$2 = _cls1.this;
                            super();
                        }
                            }
);
                        }

                        final _cls2 this$1;
                        final CancellationSignal val$cancellationSignal;
                        final android.print.PrintDocumentAdapter.LayoutResultCallback val$layoutResultCallback;
                        final PrintAttributes val$newPrintAttributes;
                        final PrintAttributes val$oldPrintAttributes;

                    
                    {
                        this$1 = _cls2.this;
                        cancellationSignal = cancellationsignal;
                        newPrintAttributes = printattributes;
                        oldPrintAttributes = printattributes1;
                        layoutResultCallback = layoutresultcallback;
                        super();
                    }
                    }
).execute(new Uri[0]);
                }
            }

            public void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.WriteResultCallback writeresultcallback)
            {
                PrintedPdfDocument printedpdfdocument = new PrintedPdfDocument(mContext, mAttributes);
                android.graphics.pdf.PdfDocument.Page page = printedpdfdocument.startPage(1);
                RectF rectf = new RectF(page.getInfo().getContentRect());
                Matrix matrix = getMatrix(mBitmap.getWidth(), mBitmap.getHeight(), rectf, fittingMode);
                page.getCanvas().drawBitmap(mBitmap, matrix, null);
                printedpdfdocument.finishPage(page);
                printedpdfdocument.writeTo(new FileOutputStream(parcelfiledescriptor.getFileDescriptor()));
                PageRange apagerange1[] = new PageRange[1];
                apagerange1[0] = PageRange.ALL_PAGES;
                writeresultcallback.onWriteFinished(apagerange1);
_L1:
                if(printedpdfdocument != null)
                    printedpdfdocument.close();
                if(parcelfiledescriptor == null)
                    break MISSING_BLOCK_LABEL_150;
                parcelfiledescriptor.close();
_L2:
                return;
                IOException ioexception1;
                ioexception1;
                Log.e("PrintHelperKitkat", "Error writing printed content", ioexception1);
                writeresultcallback.onWriteFailed(null);
                  goto _L1
                Exception exception;
                exception;
                if(printedpdfdocument != null)
                    printedpdfdocument.close();
                IOException ioexception2;
                if(parcelfiledescriptor != null)
                    try
                    {
                        parcelfiledescriptor.close();
                    }
                    catch(IOException ioexception) { }
                throw exception;
                ioexception2;
                  goto _L2
            }

            private PrintAttributes mAttributes;
            Bitmap mBitmap;
            AsyncTask mLoadBitmap;
            final PrintHelperKitkat this$0;
            final OnPrintFinishCallback val$callback;
            final int val$fittingMode;
            final Uri val$imageFile;
            final String val$jobName;


            
            {
                this$0 = PrintHelperKitkat.this;
                jobName = s;
                imageFile = uri;
                callback = onprintfinishcallback;
                fittingMode = i;
                super();
                mBitmap = null;
            }
        }
;
        printmanager = (PrintManager)mContext.getSystemService("print");
        builder = new android.print.PrintAttributes.Builder();
        builder.setColorMode(mColorMode);
        if(mOrientation != 1) goto _L2; else goto _L1
_L1:
        builder.setMediaSize(android.print.PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
_L4:
        printmanager.print(jobName, printdocumentadapter, builder.build());
        return;
_L2:
        if(mOrientation == 2)
            builder.setMediaSize(android.print.PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setColorMode(int i)
    {
        mColorMode = i;
    }

    public void setOrientation(int i)
    {
        mOrientation = i;
    }

    public void setScaleMode(int i)
    {
        mScaleMode = i;
    }

    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelperKitkat";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode;
    final Context mContext;
    android.graphics.BitmapFactory.Options mDecodeOptions;
    private final Object mLock = new Object();
    int mOrientation;
    int mScaleMode;



}
