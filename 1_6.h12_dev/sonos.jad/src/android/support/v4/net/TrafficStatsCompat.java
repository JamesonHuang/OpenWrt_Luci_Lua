// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.net;

import java.net.Socket;
import java.net.SocketException;

// Referenced classes of package android.support.v4.net:
//            TrafficStatsCompatIcs

public class TrafficStatsCompat
{
    static class IcsTrafficStatsCompatImpl
        implements TrafficStatsCompatImpl
    {

        public void clearThreadStatsTag()
        {
            TrafficStatsCompatIcs.clearThreadStatsTag();
        }

        public int getThreadStatsTag()
        {
            return TrafficStatsCompatIcs.getThreadStatsTag();
        }

        public void incrementOperationCount(int i)
        {
            TrafficStatsCompatIcs.incrementOperationCount(i);
        }

        public void incrementOperationCount(int i, int j)
        {
            TrafficStatsCompatIcs.incrementOperationCount(i, j);
        }

        public void setThreadStatsTag(int i)
        {
            TrafficStatsCompatIcs.setThreadStatsTag(i);
        }

        public void tagSocket(Socket socket)
            throws SocketException
        {
            TrafficStatsCompatIcs.tagSocket(socket);
        }

        public void untagSocket(Socket socket)
            throws SocketException
        {
            TrafficStatsCompatIcs.untagSocket(socket);
        }

        IcsTrafficStatsCompatImpl()
        {
        }
    }

    static class BaseTrafficStatsCompatImpl
        implements TrafficStatsCompatImpl
    {
        private static class SocketTags
        {

            public int statsTag;

            private SocketTags()
            {
                statsTag = -1;
            }

        }


        public void clearThreadStatsTag()
        {
            ((SocketTags)mThreadSocketTags.get()).statsTag = -1;
        }

        public int getThreadStatsTag()
        {
            return ((SocketTags)mThreadSocketTags.get()).statsTag;
        }

        public void incrementOperationCount(int i)
        {
        }

        public void incrementOperationCount(int i, int j)
        {
        }

        public void setThreadStatsTag(int i)
        {
            ((SocketTags)mThreadSocketTags.get()).statsTag = i;
        }

        public void tagSocket(Socket socket)
        {
        }

        public void untagSocket(Socket socket)
        {
        }

        private ThreadLocal mThreadSocketTags;

        BaseTrafficStatsCompatImpl()
        {
            mThreadSocketTags = new ThreadLocal() {

                protected SocketTags initialValue()
                {
                    return new SocketTags();
                }

                protected volatile Object initialValue()
                {
                    return initialValue();
                }

                final BaseTrafficStatsCompatImpl this$0;

                
                {
                    this$0 = BaseTrafficStatsCompatImpl.this;
                    super();
                }
            }
;
        }
    }

    static interface TrafficStatsCompatImpl
    {

        public abstract void clearThreadStatsTag();

        public abstract int getThreadStatsTag();

        public abstract void incrementOperationCount(int i);

        public abstract void incrementOperationCount(int i, int j);

        public abstract void setThreadStatsTag(int i);

        public abstract void tagSocket(Socket socket)
            throws SocketException;

        public abstract void untagSocket(Socket socket)
            throws SocketException;
    }


    public TrafficStatsCompat()
    {
    }

    public static void clearThreadStatsTag()
    {
        IMPL.clearThreadStatsTag();
    }

    public static int getThreadStatsTag()
    {
        return IMPL.getThreadStatsTag();
    }

    public static void incrementOperationCount(int i)
    {
        IMPL.incrementOperationCount(i);
    }

    public static void incrementOperationCount(int i, int j)
    {
        IMPL.incrementOperationCount(i, j);
    }

    public static void setThreadStatsTag(int i)
    {
        IMPL.setThreadStatsTag(i);
    }

    public static void tagSocket(Socket socket)
        throws SocketException
    {
        IMPL.tagSocket(socket);
    }

    public static void untagSocket(Socket socket)
        throws SocketException
    {
        IMPL.untagSocket(socket);
    }

    private static final TrafficStatsCompatImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new IcsTrafficStatsCompatImpl();
        else
            IMPL = new BaseTrafficStatsCompatImpl();
    }
}
