// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;


public class CircularArray
{

    public CircularArray()
    {
        this(8);
    }

    public CircularArray(int i)
    {
        if(i <= 0)
            throw new IllegalArgumentException("capacity must be positive");
        int j = i;
        if(Integer.bitCount(i) != 1)
            j = 1 << 1 + Integer.highestOneBit(i);
        mCapacityBitmask = j - 1;
        mElements = (Object[])new Object[j];
    }

    private void doubleCapacity()
    {
        int i = mElements.length;
        int j = i - mHead;
        int k = i << 1;
        if(k < 0)
        {
            throw new RuntimeException("Too big");
        } else
        {
            Object aobj[] = new Object[k];
            System.arraycopy(((Object) (mElements)), mHead, ((Object) (aobj)), 0, j);
            System.arraycopy(((Object) (mElements)), 0, ((Object) (aobj)), j, mHead);
            mElements = (Object[])aobj;
            mHead = 0;
            mTail = i;
            mCapacityBitmask = k - 1;
            return;
        }
    }

    public final void addFirst(Object obj)
    {
        mHead = -1 + mHead & mCapacityBitmask;
        mElements[mHead] = obj;
        if(mHead == mTail)
            doubleCapacity();
    }

    public final void addLast(Object obj)
    {
        mElements[mTail] = obj;
        mTail = 1 + mTail & mCapacityBitmask;
        if(mTail == mHead)
            doubleCapacity();
    }

    public final Object get(int i)
    {
        if(i < 0 || i >= size())
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            int j = i + mHead & mCapacityBitmask;
            return mElements[j];
        }
    }

    public final Object getFirst()
    {
        if(mHead == mTail)
            throw new ArrayIndexOutOfBoundsException();
        else
            return mElements[mHead];
    }

    public final Object getLast()
    {
        if(mHead == mTail)
            throw new ArrayIndexOutOfBoundsException();
        else
            return mElements[-1 + mTail & mCapacityBitmask];
    }

    public final boolean isEmpty()
    {
        boolean flag;
        if(mHead == mTail)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final Object popFirst()
    {
        if(mHead == mTail)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            Object obj = mElements[mHead];
            mElements[mHead] = null;
            mHead = 1 + mHead & mCapacityBitmask;
            return obj;
        }
    }

    public final Object popLast()
    {
        if(mHead == mTail)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            int i = -1 + mTail & mCapacityBitmask;
            Object obj = mElements[i];
            mElements[i] = null;
            mTail = i;
            return obj;
        }
    }

    public final int size()
    {
        return mTail - mHead & mCapacityBitmask;
    }

    private int mCapacityBitmask;
    private Object mElements[];
    private int mHead;
    private int mTail;
}
