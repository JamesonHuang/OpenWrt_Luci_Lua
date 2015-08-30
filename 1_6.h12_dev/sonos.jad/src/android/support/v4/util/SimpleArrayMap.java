// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;

import java.util.Map;

// Referenced classes of package android.support.v4.util:
//            ContainerHelpers, ArrayMap

public class SimpleArrayMap
{

    public SimpleArrayMap()
    {
        mHashes = ContainerHelpers.EMPTY_INTS;
        mArray = ContainerHelpers.EMPTY_OBJECTS;
        mSize = 0;
    }

    public SimpleArrayMap(int i)
    {
        if(i == 0)
        {
            mHashes = ContainerHelpers.EMPTY_INTS;
            mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else
        {
            allocArrays(i);
        }
        mSize = 0;
    }

    public SimpleArrayMap(SimpleArrayMap simplearraymap)
    {
        this();
        if(simplearraymap != null)
            putAll(simplearraymap);
    }

    private void allocArrays(int i)
    {
        if(i != 8) goto _L2; else goto _L1
_L1:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if(mTwiceBaseCache == null) goto _L4; else goto _L3
_L3:
        Object aobj1[] = mTwiceBaseCache;
        mArray = aobj1;
        mTwiceBaseCache = (Object[])(Object[])aobj1[0];
        mHashes = (int[])(int[])aobj1[1];
        aobj1[1] = null;
        aobj1[0] = null;
        mTwiceBaseCacheSize = -1 + mTwiceBaseCacheSize;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
          goto _L5
_L4:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
_L6:
        mHashes = new int[i];
        mArray = new Object[i << 1];
        break; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        throw exception1;
_L2:
        if(i != 4)
            continue; /* Loop/switch isn't completed */
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if(mBaseCache != null)
        {
            Object aobj[] = mBaseCache;
            mArray = aobj;
            mBaseCache = (Object[])(Object[])aobj[0];
            mHashes = (int[])(int[])aobj[1];
            aobj[1] = null;
            aobj[0] = null;
            mBaseCacheSize = -1 + mBaseCacheSize;
            break; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_186;
        Exception exception;
        exception;
        throw exception;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static void freeArrays(int ai[], Object aobj[], int i)
    {
        if(ai.length != 8)
            break MISSING_BLOCK_LABEL_79;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if(mTwiceBaseCacheSize < 10)
        {
            aobj[0] = ((Object) (mTwiceBaseCache));
            aobj[1] = ai;
            for(int k = -1 + (i << 1); k >= 2; k--)
                aobj[k] = null;

            mTwiceBaseCache = aobj;
            mTwiceBaseCacheSize = 1 + mTwiceBaseCacheSize;
        }
        break MISSING_BLOCK_LABEL_155;
        if(ai.length != 4)
            break MISSING_BLOCK_LABEL_155;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if(mBaseCacheSize < 10)
        {
            aobj[0] = ((Object) (mBaseCache));
            aobj[1] = ai;
            for(int j = -1 + (i << 1); j >= 2; j--)
                aobj[j] = null;

            mBaseCache = aobj;
            mBaseCacheSize = 1 + mBaseCacheSize;
        }
    }

    public void clear()
    {
        if(mSize != 0)
        {
            freeArrays(mHashes, mArray, mSize);
            mHashes = ContainerHelpers.EMPTY_INTS;
            mArray = ContainerHelpers.EMPTY_OBJECTS;
            mSize = 0;
        }
    }

    public boolean containsKey(Object obj)
    {
        boolean flag;
        if(indexOfKey(obj) >= 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean containsValue(Object obj)
    {
        boolean flag;
        if(indexOfValue(obj) >= 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void ensureCapacity(int i)
    {
        if(mHashes.length < i)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(i);
            if(mSize > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, mSize);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, mSize << 1);
            }
            freeArrays(ai, aobj, mSize);
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj instanceof Map)
        {
            Map map = (Map)obj;
            if(size() != map.size())
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            int i = 0;
label0:
            do
            {
label1:
                {
                    boolean flag1;
                    try
                    {
                        if(i >= mSize)
                            continue; /* Loop/switch isn't completed */
                        Object obj1 = keyAt(i);
                        Object obj2 = valueAt(i);
                        Object obj3 = map.get(obj1);
                        if(obj2 == null)
                        {
                            if(obj3 != null || !map.containsKey(obj1))
                                break label0;
                            break label1;
                        }
                        flag1 = obj2.equals(obj3);
                    }
                    catch(NullPointerException nullpointerexception)
                    {
                        flag = false;
                        continue; /* Loop/switch isn't completed */
                    }
                    catch(ClassCastException classcastexception)
                    {
                        flag = false;
                        continue; /* Loop/switch isn't completed */
                    }
                    if(!flag1)
                    {
                        flag = false;
                        continue; /* Loop/switch isn't completed */
                    }
                }
                i++;
            } while(true);
        } else
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public Object get(Object obj)
    {
        int i = indexOfKey(obj);
        Object obj1;
        if(i >= 0)
            obj1 = mArray[1 + (i << 1)];
        else
            obj1 = null;
        return obj1;
    }

    public int hashCode()
    {
        int ai[] = mHashes;
        Object aobj[] = mArray;
        int i = 0;
        int j = 0;
        int k = 1;
        int l = mSize;
        while(j < l) 
        {
            Object obj = aobj[k];
            int i1 = ai[j];
            int j1;
            if(obj == null)
                j1 = 0;
            else
                j1 = obj.hashCode();
            i += j1 ^ i1;
            j++;
            k += 2;
        }
        return i;
    }

    int indexOf(Object obj, int i)
    {
        int j = mSize;
        if(j != 0) goto _L2; else goto _L1
_L1:
        int k = -1;
_L4:
        return k;
_L2:
        k = ContainerHelpers.binarySearch(mHashes, j, i);
        if(k < 0 || obj.equals(mArray[k << 1]))
            continue; /* Loop/switch isn't completed */
        int l = k + 1;
        do
        {
            if(l >= j || mHashes[l] != i)
                break;
            if(obj.equals(mArray[l << 1]))
            {
                k = l;
                continue; /* Loop/switch isn't completed */
            }
            l++;
        } while(true);
        int i1 = k - 1;
        do
        {
            if(i1 < 0 || mHashes[i1] != i)
                break;
            if(obj.equals(mArray[i1 << 1]))
            {
                k = i1;
                continue; /* Loop/switch isn't completed */
            }
            i1--;
        } while(true);
        k = ~l;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int indexOfKey(Object obj)
    {
        int i;
        if(obj == null)
            i = indexOfNull();
        else
            i = indexOf(obj, obj.hashCode());
        return i;
    }

    int indexOfNull()
    {
        int i = mSize;
        if(i != 0) goto _L2; else goto _L1
_L1:
        int j = -1;
_L4:
        return j;
_L2:
        j = ContainerHelpers.binarySearch(mHashes, i, 0);
        if(j < 0 || mArray[j << 1] == null)
            continue; /* Loop/switch isn't completed */
        int k = j + 1;
        do
        {
            if(k >= i || mHashes[k] != 0)
                break;
            if(mArray[k << 1] == null)
            {
                j = k;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        int l = j - 1;
        do
        {
            if(l < 0 || mHashes[l] != 0)
                break;
            if(mArray[l << 1] == null)
            {
                j = l;
                continue; /* Loop/switch isn't completed */
            }
            l--;
        } while(true);
        j = ~k;
        if(true) goto _L4; else goto _L3
_L3:
    }

    int indexOfValue(Object obj)
    {
        int i;
        Object aobj[];
        i = 2 * mSize;
        aobj = mArray;
        if(obj != null) goto _L2; else goto _L1
_L1:
        int l = 1;
_L5:
        if(l >= i)
            break MISSING_BLOCK_LABEL_82;
        if(aobj[l] != null) goto _L4; else goto _L3
_L3:
        int k = l >> 1;
_L6:
        return k;
_L4:
        l += 2;
          goto _L5
_L2:
        int j = 1;
_L7:
label0:
        {
            if(j >= i)
                break MISSING_BLOCK_LABEL_82;
            if(!obj.equals(aobj[j]))
                break label0;
            k = j >> 1;
        }
          goto _L6
        j += 2;
          goto _L7
        k = -1;
          goto _L6
    }

    public boolean isEmpty()
    {
        boolean flag;
        if(mSize <= 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Object keyAt(int i)
    {
        return mArray[i << 1];
    }

    public Object put(Object obj, Object obj1)
    {
        int i = 8;
        int j;
        int k;
        Object obj2;
        if(obj == null)
        {
            j = 0;
            k = indexOfNull();
        } else
        {
            j = obj.hashCode();
            k = indexOf(obj, j);
        }
        if(k >= 0)
        {
            int i1 = 1 + (k << 1);
            obj2 = mArray[i1];
            mArray[i1] = obj1;
        } else
        {
            int l = ~k;
            if(mSize >= mHashes.length)
            {
                int ai[];
                Object aobj[];
                if(mSize >= i)
                    i = mSize + (mSize >> 1);
                else
                if(mSize < 4)
                    i = 4;
                ai = mHashes;
                aobj = mArray;
                allocArrays(i);
                if(mHashes.length > 0)
                {
                    System.arraycopy(ai, 0, mHashes, 0, ai.length);
                    System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, aobj.length);
                }
                freeArrays(ai, aobj, mSize);
            }
            if(l < mSize)
            {
                System.arraycopy(mHashes, l, mHashes, l + 1, mSize - l);
                System.arraycopy(((Object) (mArray)), l << 1, ((Object) (mArray)), l + 1 << 1, mSize - l << 1);
            }
            mHashes[l] = j;
            mArray[l << 1] = obj;
            mArray[1 + (l << 1)] = obj1;
            mSize = 1 + mSize;
            obj2 = null;
        }
        return obj2;
    }

    public void putAll(SimpleArrayMap simplearraymap)
    {
        int i = simplearraymap.mSize;
        ensureCapacity(i + mSize);
        if(mSize == 0)
        {
            if(i > 0)
            {
                System.arraycopy(simplearraymap.mHashes, 0, mHashes, 0, i);
                System.arraycopy(((Object) (simplearraymap.mArray)), 0, ((Object) (mArray)), 0, i << 1);
                mSize = i;
            }
        } else
        {
            int j = 0;
            while(j < i) 
            {
                put(simplearraymap.keyAt(j), simplearraymap.valueAt(j));
                j++;
            }
        }
    }

    public Object remove(Object obj)
    {
        int i = indexOfKey(obj);
        Object obj1;
        if(i >= 0)
            obj1 = removeAt(i);
        else
            obj1 = null;
        return obj1;
    }

    public Object removeAt(int i)
    {
        int j;
        Object obj;
        j = 8;
        obj = mArray[1 + (i << 1)];
        if(mSize > 1) goto _L2; else goto _L1
_L1:
        freeArrays(mHashes, mArray, mSize);
        mHashes = ContainerHelpers.EMPTY_INTS;
        mArray = ContainerHelpers.EMPTY_OBJECTS;
        mSize = 0;
_L4:
        return obj;
_L2:
        if(mHashes.length > j && mSize < mHashes.length / 3)
        {
            if(mSize > j)
                j = mSize + (mSize >> 1);
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(j);
            mSize = -1 + mSize;
            if(i > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, i);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, i << 1);
            }
            if(i < mSize)
            {
                System.arraycopy(ai, i + 1, mHashes, i, mSize - i);
                System.arraycopy(((Object) (aobj)), i + 1 << 1, ((Object) (mArray)), i << 1, mSize - i << 1);
            }
        } else
        {
            mSize = -1 + mSize;
            if(i < mSize)
            {
                System.arraycopy(mHashes, i + 1, mHashes, i, mSize - i);
                System.arraycopy(((Object) (mArray)), i + 1 << 1, ((Object) (mArray)), i << 1, mSize - i << 1);
            }
            mArray[mSize << 1] = null;
            mArray[1 + (mSize << 1)] = null;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Object setValueAt(int i, Object obj)
    {
        int j = 1 + (i << 1);
        Object obj1 = mArray[j];
        mArray[j] = obj;
        return obj1;
    }

    public int size()
    {
        return mSize;
    }

    public String toString()
    {
        String s;
        if(isEmpty())
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
                Object obj = keyAt(i);
                Object obj1;
                if(obj != this)
                    stringbuilder.append(obj);
                else
                    stringbuilder.append("(this Map)");
                stringbuilder.append('=');
                obj1 = valueAt(i);
                if(obj1 != this)
                    stringbuilder.append(obj1);
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
        return mArray[1 + (i << 1)];
    }

    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    static Object mBaseCache[];
    static int mBaseCacheSize;
    static Object mTwiceBaseCache[];
    static int mTwiceBaseCacheSize;
    Object mArray[];
    int mHashes[];
    int mSize;
}
