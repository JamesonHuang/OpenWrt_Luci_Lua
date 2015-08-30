// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.debug.hv;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import android.view.*;
import com.sonos.acr.util.SLog;
import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ViewServer
    implements Runnable
{
    private class ViewServerWorker
        implements Runnable, WindowListener
    {

        private View findWindow(int i)
        {
            if(i != -1) goto _L2; else goto _L1
_L1:
            mWindowsLock.readLock().lock();
            View view2 = mFocusedWindow;
            View view;
            view = view2;
            mWindowsLock.readLock().unlock();
_L4:
            return view;
            Exception exception1;
            exception1;
            mWindowsLock.readLock().unlock();
            throw exception1;
_L2:
            mWindowsLock.readLock().lock();
            View view1;
            Iterator iterator = mWindows.entrySet().iterator();
            java.util.Map.Entry entry;
            do
            {
                if(!iterator.hasNext())
                    break MISSING_BLOCK_LABEL_161;
                entry = (java.util.Map.Entry)iterator.next();
            } while(System.identityHashCode(entry.getKey()) != i);
            view1 = (View)entry.getKey();
            mWindowsLock.readLock().unlock();
            view = view1;
            continue; /* Loop/switch isn't completed */
            mWindowsLock.readLock().unlock();
            view = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            mWindowsLock.readLock().unlock();
            throw exception;
        }

        private boolean getFocusedWindow(Socket socket)
        {
            boolean flag;
            BufferedWriter bufferedwriter;
            flag = true;
            bufferedwriter = null;
            BufferedWriter bufferedwriter1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 8192);
            mFocusLock.readLock().lock();
            View view = mFocusedWindow;
            mFocusLock.readLock().unlock();
            if(view == null)
                break MISSING_BLOCK_LABEL_143;
            mWindowsLock.readLock().lock();
            String s = (String)mWindows.get(mFocusedWindow);
            mWindowsLock.readLock().unlock();
            bufferedwriter1.write(Integer.toHexString(System.identityHashCode(view)));
            bufferedwriter1.write(32);
            bufferedwriter1.append(s);
            bufferedwriter1.write(10);
            bufferedwriter1.flush();
            Exception exception;
            Exception exception2;
            Exception exception3;
            if(bufferedwriter1 != null)
                try
                {
                    bufferedwriter1.close();
                }
                catch(IOException ioexception2)
                {
                    flag = false;
                }
            return flag;
            exception2;
            try
            {
                mFocusLock.readLock().unlock();
                throw exception2;
            }
            catch(Exception exception1)
            {
                bufferedwriter = bufferedwriter1;
            }
_L4:
            flag = false;
            if(bufferedwriter != null)
                try
                {
                    bufferedwriter.close();
                }
                catch(IOException ioexception1)
                {
                    flag = false;
                }
            if(true)
                break MISSING_BLOCK_LABEL_165;
            exception3;
            mWindowsLock.readLock().unlock();
            throw exception3;
            exception;
            bufferedwriter = bufferedwriter1;
_L2:
            if(bufferedwriter != null)
                try
                {
                    bufferedwriter.close();
                }
                catch(IOException ioexception) { }
            throw exception;
            exception;
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception4;
            exception4;
            if(true) goto _L4; else goto _L3
_L3:
        }

        private boolean listWindows(Socket socket)
        {
            boolean flag;
            BufferedWriter bufferedwriter;
            flag = true;
            bufferedwriter = null;
            BufferedWriter bufferedwriter1;
            mWindowsLock.readLock().lock();
            bufferedwriter1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 8192);
            for(Iterator iterator = mWindows.entrySet().iterator(); iterator.hasNext(); bufferedwriter1.write(10))
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                bufferedwriter1.write(Integer.toHexString(System.identityHashCode(entry.getKey())));
                bufferedwriter1.write(32);
                bufferedwriter1.append((CharSequence)entry.getValue());
            }

              goto _L1
            Exception exception2;
            exception2;
            bufferedwriter = bufferedwriter1;
_L7:
            flag = false;
            mWindowsLock.readLock().unlock();
            IOException ioexception2;
            if(bufferedwriter != null)
                try
                {
                    bufferedwriter.close();
                }
                catch(IOException ioexception)
                {
                    flag = false;
                }
_L3:
            return flag;
_L1:
            bufferedwriter1.write("DONE.\n");
            bufferedwriter1.flush();
            mWindowsLock.readLock().unlock();
            if(bufferedwriter1 != null)
                try
                {
                    bufferedwriter1.close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception2)
                {
                    flag = false;
                }
            if(true) goto _L3; else goto _L2
_L2:
            Exception exception1;
            exception1;
_L5:
            mWindowsLock.readLock().unlock();
            if(bufferedwriter != null)
                try
                {
                    bufferedwriter.close();
                }
                catch(IOException ioexception1) { }
            throw exception1;
            exception1;
            bufferedwriter = bufferedwriter1;
            if(true) goto _L5; else goto _L4
_L4:
            Exception exception;
            exception;
            if(true) goto _L7; else goto _L6
_L6:
        }

        private boolean windowCommand(Socket socket, String s, String s1)
        {
            boolean flag;
            BufferedWriter bufferedwriter;
            flag = true;
            bufferedwriter = null;
            int j;
            int i = s1.indexOf(' ');
            if(i == -1)
                i = s1.length();
            j = (int)Long.parseLong(s1.substring(0, i), 16);
            if(i >= s1.length())
                break MISSING_BLOCK_LABEL_91;
            int k = i + 1;
            s1 = s1.substring(k);
_L1:
            View view = findWindow(j);
            {
                if(view != null)
                    break MISSING_BLOCK_LABEL_97;
                boolean flag1 = false;
                Exception exception;
                IOException ioexception;
                Exception exception1;
                IOException ioexception1;
                Class aclass[];
                Method method;
                Object aobj[];
                BufferedWriter bufferedwriter1;
                IOException ioexception2;
                if(false)
                    try
                    {
                        null.close();
                    }
                    catch(IOException ioexception3) { }
            }
            return flag1;
            s1 = "";
              goto _L1
            aclass = new Class[4];
            aclass[0] = android/view/View;
            aclass[1] = java/lang/String;
            aclass[2] = java/lang/String;
            aclass[3] = java/io/OutputStream;
            method = android/view/ViewDebug.getDeclaredMethod("dispatchCommand", aclass);
            method.setAccessible(true);
            aobj = new Object[4];
            aobj[0] = view;
            aobj[1] = s;
            aobj[2] = s1;
            aobj[3] = new UncloseableOuputStream(socket.getOutputStream());
            method.invoke(null, aobj);
            if(socket.isOutputShutdown())
                break MISSING_BLOCK_LABEL_233;
            bufferedwriter1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedwriter1.write("DONE\n");
            bufferedwriter1.flush();
            bufferedwriter = bufferedwriter1;
            if(bufferedwriter != null)
                try
                {
                    bufferedwriter.close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception2)
                {
                    flag = false;
                }
_L2:
            flag1 = flag;
            break MISSING_BLOCK_LABEL_88;
            exception1;
_L5:
            SLog.w("LocalViewServer", (new StringBuilder()).append("Could not send command ").append(s).append(" with parameters ").append(s1).toString(), exception1);
            flag = false;
            if(bufferedwriter != null)
                try
                {
                    bufferedwriter.close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception1)
                {
                    flag = false;
                }
              goto _L2
            exception;
_L4:
            if(bufferedwriter != null)
                try
                {
                    bufferedwriter.close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception) { }
            throw exception;
            exception;
            bufferedwriter = bufferedwriter1;
            if(true) goto _L4; else goto _L3
_L3:
            exception1;
            bufferedwriter = bufferedwriter1;
              goto _L5
        }

        private boolean windowManagerAutolistLoop()
        {
            BufferedWriter bufferedwriter;
            addWindowListener(this);
            bufferedwriter = null;
            BufferedWriter bufferedwriter1 = new BufferedWriter(new OutputStreamWriter(mClient.getOutputStream()));
_L4:
            if(Thread.interrupted()) goto _L2; else goto _L1
_L1:
            boolean flag;
            boolean flag1;
            flag = false;
            flag1 = false;
            Object aobj[] = mLock;
            aobj;
            JVM INSTR monitorenter ;
            while(!mNeedWindowListUpdate && !mNeedFocusedWindowUpdate) 
                ((Object) (mLock)).wait();
              goto _L3
            Exception exception2;
            exception2;
            throw exception2;
            Exception exception1;
            exception1;
            bufferedwriter = bufferedwriter1;
_L7:
            SLog.w("LocalViewServer", "Connection error: ", exception1);
            Exception exception;
            IOException ioexception2;
            if(bufferedwriter != null)
                try
                {
                    bufferedwriter.close();
                }
                catch(IOException ioexception1) { }
            removeWindowListener(this);
            return true;
_L3:
            if(mNeedWindowListUpdate)
            {
                mNeedWindowListUpdate = false;
                flag = true;
            }
            if(mNeedFocusedWindowUpdate)
            {
                mNeedFocusedWindowUpdate = false;
                flag1 = true;
            }
            aobj;
            JVM INSTR monitorexit ;
            if(!flag)
                break MISSING_BLOCK_LABEL_166;
            bufferedwriter1.write("LIST UPDATE\n");
            bufferedwriter1.flush();
            if(flag1)
            {
                bufferedwriter1.write("FOCUS UPDATE\n");
                bufferedwriter1.flush();
            }
              goto _L4
            exception;
            bufferedwriter = bufferedwriter1;
_L6:
            if(bufferedwriter != null)
                try
                {
                    bufferedwriter.close();
                }
                catch(IOException ioexception) { }
            removeWindowListener(this);
            throw exception;
_L2:
            if(bufferedwriter1 != null)
                try
                {
                    bufferedwriter1.close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception2) { }
            removeWindowListener(this);
            break MISSING_BLOCK_LABEL_115;
            exception;
            if(true) goto _L6; else goto _L5
_L5:
            exception1;
              goto _L7
        }

        public void focusChanged()
        {
            Object aobj[] = mLock;
            aobj;
            JVM INSTR monitorenter ;
            mNeedFocusedWindowUpdate = true;
            ((Object) (mLock)).notifyAll();
            return;
        }

        public void run()
        {
            BufferedReader bufferedreader = null;
            BufferedReader bufferedreader1 = new BufferedReader(new InputStreamReader(mClient.getInputStream()), 1024);
            String s;
            int i;
            s = bufferedreader1.readLine();
            i = s.indexOf(' ');
            if(i != -1) goto _L2; else goto _L1
_L1:
            String s1;
            String s2;
            s1 = s;
            s2 = "";
_L5:
            if(!"PROTOCOL".equalsIgnoreCase(s1)) goto _L4; else goto _L3
_L3:
            boolean flag1 = ViewServer.writeValue(mClient, "4");
_L8:
            if(!flag1)
                SLog.w("LocalViewServer", (new StringBuilder()).append("An error occurred with the command: ").append(s1).toString());
            boolean flag;
            if(bufferedreader1 != null)
                try
                {
                    bufferedreader1.close();
                }
                catch(IOException ioexception6)
                {
                    ioexception6.printStackTrace();
                }
            if(mClient == null)
                break MISSING_BLOCK_LABEL_133;
            mClient.close();
_L15:
            return;
_L2:
            s1 = s.substring(0, i);
            s2 = s.substring(i + 1);
              goto _L5
_L4:
            if(!"SERVER".equalsIgnoreCase(s1)) goto _L7; else goto _L6
_L6:
            flag1 = ViewServer.writeValue(mClient, "4");
              goto _L8
_L7:
            if(!"LIST".equalsIgnoreCase(s1)) goto _L10; else goto _L9
_L9:
            flag1 = listWindows(mClient);
              goto _L8
_L10:
            if(!"GET_FOCUS".equalsIgnoreCase(s1)) goto _L12; else goto _L11
_L11:
            flag1 = getFocusedWindow(mClient);
              goto _L8
_L12:
            if(!"AUTOLIST".equalsIgnoreCase(s1)) goto _L14; else goto _L13
_L13:
            flag1 = windowManagerAutolistLoop();
              goto _L8
_L14:
            flag = windowCommand(mClient, s1, s2);
            flag1 = flag;
              goto _L8
            IOException ioexception5;
            ioexception5;
            ioexception5.printStackTrace();
              goto _L15
            IOException ioexception;
            ioexception;
_L18:
            SLog.w("LocalViewServer", "Connection error: ", ioexception);
            if(bufferedreader != null)
                try
                {
                    bufferedreader.close();
                }
                catch(IOException ioexception4)
                {
                    ioexception4.printStackTrace();
                }
            if(mClient != null)
                try
                {
                    mClient.close();
                }
                catch(IOException ioexception3)
                {
                    ioexception3.printStackTrace();
                }
              goto _L15
            Exception exception;
            exception;
_L17:
            if(bufferedreader != null)
                try
                {
                    bufferedreader.close();
                }
                catch(IOException ioexception2)
                {
                    ioexception2.printStackTrace();
                }
            if(mClient != null)
                try
                {
                    mClient.close();
                }
                catch(IOException ioexception1)
                {
                    ioexception1.printStackTrace();
                }
            throw exception;
            exception;
            bufferedreader = bufferedreader1;
            if(true) goto _L17; else goto _L16
_L16:
            ioexception;
            bufferedreader = bufferedreader1;
              goto _L18
        }

        public void windowsChanged()
        {
            Object aobj[] = mLock;
            aobj;
            JVM INSTR monitorenter ;
            mNeedWindowListUpdate = true;
            ((Object) (mLock)).notifyAll();
            return;
        }

        private Socket mClient;
        private final Object mLock[] = new Object[0];
        private boolean mNeedFocusedWindowUpdate;
        private boolean mNeedWindowListUpdate;
        final ViewServer this$0;

        public ViewServerWorker(Socket socket)
        {
            this$0 = ViewServer.this;
            super();
            mClient = socket;
            mNeedWindowListUpdate = false;
            mNeedFocusedWindowUpdate = false;
        }
    }

    private static class NoopViewServer extends ViewServer
    {

        public void addWindow(Activity activity)
        {
        }

        public void addWindow(View view, String s)
        {
        }

        public boolean isRunning()
        {
            return false;
        }

        public void removeWindow(Activity activity)
        {
        }

        public void removeWindow(View view)
        {
        }

        public void run()
        {
        }

        public void setFocusedWindow(Activity activity)
        {
        }

        public void setFocusedWindow(View view)
        {
        }

        public boolean start()
            throws IOException
        {
            return false;
        }

        public boolean stop()
        {
            return false;
        }

        private NoopViewServer()
        {
        }

    }

    private static class UncloseableOuputStream extends OutputStream
    {

        public void close()
            throws IOException
        {
        }

        public boolean equals(Object obj)
        {
            return mStream.equals(obj);
        }

        public void flush()
            throws IOException
        {
            mStream.flush();
        }

        public int hashCode()
        {
            return mStream.hashCode();
        }

        public String toString()
        {
            return mStream.toString();
        }

        public void write(int i)
            throws IOException
        {
            mStream.write(i);
        }

        public void write(byte abyte0[])
            throws IOException
        {
            mStream.write(abyte0);
        }

        public void write(byte abyte0[], int i, int j)
            throws IOException
        {
            mStream.write(abyte0, i, j);
        }

        private final OutputStream mStream;

        UncloseableOuputStream(OutputStream outputstream)
        {
            mStream = outputstream;
        }
    }

    private static interface WindowListener
    {

        public abstract void focusChanged();

        public abstract void windowsChanged();
    }


    private ViewServer()
    {
        mListeners = new ArrayList();
        mWindows = new HashMap();
        mWindowsLock = new ReentrantReadWriteLock();
        mFocusLock = new ReentrantReadWriteLock();
        mPort = -1;
    }

    private ViewServer(int i)
    {
        mListeners = new ArrayList();
        mWindows = new HashMap();
        mWindowsLock = new ReentrantReadWriteLock();
        mFocusLock = new ReentrantReadWriteLock();
        mPort = i;
    }


    private void addWindowListener(WindowListener windowlistener)
    {
        if(!mListeners.contains(windowlistener))
            mListeners.add(windowlistener);
    }

    private void fireFocusChangedEvent()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((WindowListener)iterator.next()).focusChanged());
    }

    private void fireWindowsChangedEvent()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((WindowListener)iterator.next()).windowsChanged());
    }

    public static ViewServer get(Context context)
    {
        ApplicationInfo applicationinfo = context.getApplicationInfo();
        if("user".equals(Build.TYPE) && (2 & applicationinfo.flags) != 0)
        {
            if(sServer == null)
                sServer = new ViewServer(4939);
            if(!sServer.isRunning())
                try
                {
                    sServer.start();
                }
                catch(IOException ioexception)
                {
                    SLog.d("LocalViewServer", "Error:", ioexception);
                }
        } else
        {
            sServer = new NoopViewServer();
        }
        return sServer;
    }

    private void removeWindowListener(WindowListener windowlistener)
    {
        mListeners.remove(windowlistener);
    }

    private static boolean writeValue(Socket socket, String s)
    {
        BufferedWriter bufferedwriter = null;
        BufferedWriter bufferedwriter1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 8192);
        bufferedwriter1.write(s);
        bufferedwriter1.write("\n");
        bufferedwriter1.flush();
        boolean flag;
        flag = true;
        if(bufferedwriter1 != null)
            try
            {
                bufferedwriter1.close();
            }
            catch(IOException ioexception2)
            {
                flag = false;
            }
_L2:
        return flag;
        Exception exception2;
        exception2;
_L5:
        flag = false;
        if(bufferedwriter != null)
            try
            {
                bufferedwriter.close();
            }
            catch(IOException ioexception)
            {
                flag = false;
            }
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception1;
        exception1;
_L4:
        if(bufferedwriter != null)
            try
            {
                bufferedwriter.close();
            }
            catch(IOException ioexception1) { }
        throw exception1;
        exception1;
        bufferedwriter = bufferedwriter1;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        bufferedwriter = bufferedwriter1;
          goto _L5
    }

    public void addWindow(Activity activity)
    {
        String s = activity.getTitle().toString();
        String s1;
        if(TextUtils.isEmpty(s))
            s1 = (new StringBuilder()).append(activity.getClass().getCanonicalName()).append("/0x").append(System.identityHashCode(activity)).toString();
        else
            s1 = (new StringBuilder()).append(s).append("(").append(activity.getClass().getCanonicalName()).append(")").toString();
        addWindow(activity.getWindow().getDecorView(), s1);
    }

    public void addWindow(View view, String s)
    {
        mWindowsLock.writeLock().lock();
        mWindows.put(view.getRootView(), s);
        mWindowsLock.writeLock().unlock();
        fireWindowsChangedEvent();
        return;
        Exception exception;
        exception;
        mWindowsLock.writeLock().unlock();
        throw exception;
    }

    public boolean isRunning()
    {
        boolean flag;
        if(mThread != null && mThread.isAlive())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void removeWindow(Activity activity)
    {
        removeWindow(activity.getWindow().getDecorView());
    }

    public void removeWindow(View view)
    {
        mWindowsLock.writeLock().lock();
        mWindows.remove(view.getRootView());
        mWindowsLock.writeLock().unlock();
        fireWindowsChangedEvent();
        return;
        Exception exception;
        exception;
        mWindowsLock.writeLock().unlock();
        throw exception;
    }

    public void run()
    {
_L2:
        if(Thread.currentThread() != mThread)
            break; /* Loop/switch isn't completed */
        Exception exception;
        Socket socket;
        socket = mServer.accept();
        if(mThreadPool != null)
        {
            mThreadPool.submit(new ViewServerWorker(socket));
            continue; /* Loop/switch isn't completed */
        }
        socket.close();
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        try
        {
            ioexception.printStackTrace();
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            SLog.w("LocalViewServer", "Connection error: ", exception);
        }
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setFocusedWindow(Activity activity)
    {
        setFocusedWindow(activity.getWindow().getDecorView());
    }

    public void setFocusedWindow(View view)
    {
        mFocusLock.writeLock().lock();
        if(view != null) goto _L2; else goto _L1
_L1:
        View view2 = null;
_L3:
        mFocusedWindow = view2;
        mFocusLock.writeLock().unlock();
        fireFocusChangedEvent();
        return;
_L2:
        View view1 = view.getRootView();
        view2 = view1;
          goto _L3
        Exception exception;
        exception;
        mFocusLock.writeLock().unlock();
        throw exception;
    }

    public boolean start()
        throws IOException
    {
        boolean flag;
        if(mThread != null)
        {
            flag = false;
        } else
        {
            int i = mPort;
            byte abyte0[] = new byte[4];
            abyte0[0] = 127;
            abyte0[1] = 0;
            abyte0[2] = 0;
            abyte0[3] = 1;
            mServer = new ServerSocket(i, 10, InetAddress.getByAddress(abyte0));
            mThread = new Thread(this, (new StringBuilder()).append("Local View Server [port=").append(mPort).append("]").toString());
            mThreadPool = Executors.newFixedThreadPool(10);
            mThread.start();
            flag = true;
        }
        return flag;
    }

    public boolean stop()
    {
        if(mThread == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        mThread.interrupt();
        if(mThreadPool != null)
            try
            {
                mThreadPool.shutdownNow();
            }
            catch(SecurityException securityexception)
            {
                SLog.w("LocalViewServer", "Could not stop all view server threads");
            }
        mThreadPool = null;
        mThread = null;
        mServer.close();
        mServer = null;
        flag = true;
_L4:
        return flag;
        IOException ioexception;
        ioexception;
        SLog.w("LocalViewServer", "Could not close the view server");
_L2:
        mWindowsLock.writeLock().lock();
        mWindows.clear();
        mWindowsLock.writeLock().unlock();
        mFocusLock.writeLock().lock();
        mFocusedWindow = null;
        mFocusLock.writeLock().unlock();
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        mWindowsLock.writeLock().unlock();
        throw exception;
        Exception exception1;
        exception1;
        mFocusLock.writeLock().unlock();
        throw exception1;
    }

    private static final String BUILD_TYPE_USER = "user";
    private static final String COMMAND_PROTOCOL_VERSION = "PROTOCOL";
    private static final String COMMAND_SERVER_VERSION = "SERVER";
    private static final String COMMAND_WINDOW_MANAGER_AUTOLIST = "AUTOLIST";
    private static final String COMMAND_WINDOW_MANAGER_GET_FOCUS = "GET_FOCUS";
    private static final String COMMAND_WINDOW_MANAGER_LIST = "LIST";
    private static final String LOG_TAG = "LocalViewServer";
    private static final String VALUE_PROTOCOL_VERSION = "4";
    private static final String VALUE_SERVER_VERSION = "4";
    private static final int VIEW_SERVER_DEFAULT_PORT = 4939;
    private static final int VIEW_SERVER_MAX_CONNECTIONS = 10;
    private static ViewServer sServer;
    private final ReentrantReadWriteLock mFocusLock;
    private View mFocusedWindow;
    private final ArrayList mListeners;
    private final int mPort;
    private ServerSocket mServer;
    private Thread mThread;
    private ExecutorService mThreadPool;
    private final HashMap mWindows;
    private final ReentrantReadWriteLock mWindowsLock;







}
