// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;


public final class Pools
{
    public static class SynchronizedPool extends SimplePool
    {

        public Object acquire()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            Object obj1 = super.acquire();
            return obj1;
        }

        public boolean release(Object obj)
        {
            Object obj1 = mLock;
            obj1;
            JVM INSTR monitorenter ;
            boolean flag = super.release(obj);
            return flag;
        }

        private final Object mLock = new Object();

        public SynchronizedPool(int i)
        {
            super(i);
        }
    }

    public static class SimplePool
        implements Pool
    {

        private boolean isInPool(Object obj)
        {
            int i = 0;
_L3:
            if(i >= mPoolSize)
                break MISSING_BLOCK_LABEL_30;
            if(mPool[i] != obj) goto _L2; else goto _L1
_L1:
            boolean flag = true;
_L4:
            return flag;
_L2:
            i++;
              goto _L3
            flag = false;
              goto _L4
        }

        public Object acquire()
        {
            Object obj;
            if(mPoolSize > 0)
            {
                int i = -1 + mPoolSize;
                obj = mPool[i];
                mPool[i] = null;
                mPoolSize = -1 + mPoolSize;
            } else
            {
                obj = null;
            }
            return obj;
        }

        public boolean release(Object obj)
        {
            if(isInPool(obj))
                throw new IllegalStateException("Already in the pool!");
            boolean flag;
            if(mPoolSize < mPool.length)
            {
                mPool[mPoolSize] = obj;
                mPoolSize = 1 + mPoolSize;
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        private final Object mPool[];
        private int mPoolSize;

        public SimplePool(int i)
        {
            if(i <= 0)
            {
                throw new IllegalArgumentException("The max pool size must be > 0");
            } else
            {
                mPool = new Object[i];
                return;
            }
        }
    }

    public static interface Pool
    {

        public abstract Object acquire();

        public abstract boolean release(Object obj);
    }


    private Pools()
    {
    }
}
