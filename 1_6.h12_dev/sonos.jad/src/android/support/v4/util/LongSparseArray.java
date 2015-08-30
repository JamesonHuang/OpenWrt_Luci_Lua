// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;


// Referenced classes of package android.support.v4.util:
//            ContainerHelpers

public class LongSparseArray
    implements Cloneable
{

    public LongSparseArray()
    {
        this(10);
    }

    public LongSparseArray(int i)
    {
        mGarbage = false;
        if(i == 0)
        {
            mKeys = ContainerHelpers.EMPTY_LONGS;
            mValues = ContainerHelpers.EMPTY_OBJECTS;
        } else
        {
            int j = ContainerHelpers.idealLongArraySize(i);
            mKeys = new long[j];
            mValues = new Object[j];
        }
        mSize = 0;
    }

    private void gc()
    {
        int i = mSize;
        int j = 0;
        long al[] = mKeys;
        Object aobj[] = mValues;
        for(int k = 0; k < i; k++)
        {
            Object obj = aobj[k];
            if(obj == DELETED)
                continue;
            if(k != j)
            {
                al[j] = al[k];
                aobj[j] = obj;
                aobj[k] = null;
            }
            j++;
        }

        mGarbage = false;
        mSize = j;
    }

    public void append(long l, Object obj)
    {
        if(mSize != 0 && l <= mKeys[-1 + mSize])
        {
            put(l, obj);
        } else
        {
            if(mGarbage && mSize >= mKeys.length)
                gc();
            int i = mSize;
            if(i >= mKeys.length)
            {
                int j = ContainerHelpers.idealLongArraySize(i + 1);
                long al[] = new long[j];
                Object aobj[] = new Object[j];
                System.arraycopy(mKeys, 0, al, 0, mKeys.length);
                System.arraycopy(((Object) (mValues)), 0, ((Object) (aobj)), 0, mValues.length);
                mKeys = al;
                mValues = aobj;
            }
            mKeys[i] = l;
            mValues[i] = obj;
            mSize = i + 1;
        }
    }

    public void clear()
    {
        int i = mSize;
        Object aobj[] = mValues;
        for(int j = 0; j < i; j++)
            aobj[j] = null;

        mSize = 0;
        mGarbage = false;
    }

    public LongSparseArray clone()
    {
        LongSparseArray longsparsearray = null;
        try
        {
            longsparsearray = (LongSparseArray)super.clone();
            longsparsearray.mKeys = (long[])mKeys.clone();
            longsparsearray.mValues = (Object[])((Object []) (mValues)).clone();
        }
        catch(CloneNotSupportedException clonenotsupportedexception) { }
        return longsparsearray;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void delete(long l)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if(i >= 0 && mValues[i] != DELETED)
        {
            mValues[i] = DELETED;
            mGarbage = true;
        }
    }

    public Object get(long l)
    {
        return get(l, null);
    }

    public Object get(long l, Object obj)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if(i >= 0 && mValues[i] != DELETED)
            obj = mValues[i];
        return obj;
    }

    public int indexOfKey(long l)
    {
        if(mGarbage)
            gc();
        return ContainerHelpers.binarySearch(mKeys, mSize, l);
    }

    public int indexOfValue(Object obj)
    {
        int i;
        if(mGarbage)
            gc();
        i = 0;
_L3:
        if(i >= mSize)
            break MISSING_BLOCK_LABEL_39;
        if(mValues[i] != obj) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        i++;
          goto _L3
        i = -1;
          goto _L1
    }

    public long keyAt(int i)
    {
        if(mGarbage)
            gc();
        return mKeys[i];
    }

    public void put(long l, Object obj)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if(i >= 0)
        {
            mValues[i] = obj;
        } else
        {
            int j = ~i;
            if(j < mSize && mValues[j] == DELETED)
            {
                mKeys[j] = l;
                mValues[j] = obj;
            } else
            {
                if(mGarbage && mSize >= mKeys.length)
                {
                    gc();
                    j = -1 ^ ContainerHelpers.binarySearch(mKeys, mSize, l);
                }
                if(mSize >= mKeys.length)
                {
                    int k = ContainerHelpers.idealLongArraySize(1 + mSize);
                    long al[] = new long[k];
                    Object aobj[] = new Object[k];
                    System.arraycopy(mKeys, 0, al, 0, mKeys.length);
                    System.arraycopy(((Object) (mValues)), 0, ((Object) (aobj)), 0, mValues.length);
                    mKeys = al;
                    mValues = aobj;
                }
                if(mSize - j != 0)
                {
                    System.arraycopy(mKeys, j, mKeys, j + 1, mSize - j);
                    System.arraycopy(((Object) (mValues)), j, ((Object) (mValues)), j + 1, mSize - j);
                }
                mKeys[j] = l;
                mValues[j] = obj;
                mSize = 1 + mSize;
            }
        }
    }

    public void remove(long l)
    {
        delete(l);
    }

    public void removeAt(int i)
    {
        if(mValues[i] != DELETED)
        {
            mValues[i] = DELETED;
            mGarbage = true;
        }
    }

    public void setValueAt(int i, Object obj)
    {
        if(mGarbage)
            gc();
        mValues[i] = obj;
    }

    public int size()
    {
        if(mGarbage)
            gc();
        return mSize;
    }

    public String toString()
    {
        String s;
        if(size() <= 0)
        {
            s = "{}";
        } else
        {
            StringBuilder stringbuilder = new StringBuilder(28 * mSize);
            stringbuilder.append('{');
            int i = 0;
            while(i < mSize) 
            {
                if(i > 0)
                    stringbuilder.append(", ");
                stringbuilder.append(keyAt(i));
                stringbuilder.append('=');
                Object obj = valueAt(i);
                if(obj != this)
                    stringbuilder.append(obj);
                else
                    stringbuilder.append("(this Map)");
                i++;
            }
            stringbuilder.append('}');
            s = stringbuilder.toString();
        }
        return s;
    }

    public Object valueAt(int i)
    {
        if(mGarbage)
            gc();
        return mValues[i];
    }

    private static final Object DELETED = new Object();
    private boolean mGarbage;
    private long mKeys[];
    private int mSize;
    private Object mValues[];

}
