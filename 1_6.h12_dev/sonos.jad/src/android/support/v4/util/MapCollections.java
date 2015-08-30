// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.*;

// Referenced classes of package android.support.v4.util:
//            ContainerHelpers

abstract class MapCollections
{
    final class ValuesCollection
        implements Collection
    {

        public boolean add(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection collection)
        {
            throw new UnsupportedOperationException();
        }

        public void clear()
        {
            colClear();
        }

        public boolean contains(Object obj)
        {
            boolean flag;
            if(colIndexOfValue(obj) >= 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean containsAll(Collection collection)
        {
            Iterator iterator1 = collection.iterator();
_L4:
            if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
            if(contains(iterator1.next())) goto _L4; else goto _L3
_L3:
            boolean flag = false;
_L6:
            return flag;
_L2:
            flag = true;
            if(true) goto _L6; else goto _L5
_L5:
        }

        public boolean isEmpty()
        {
            boolean flag;
            if(colGetSize() == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public Iterator iterator()
        {
            return new ArrayIterator(1);
        }

        public boolean remove(Object obj)
        {
            int i = colIndexOfValue(obj);
            boolean flag;
            if(i >= 0)
            {
                colRemoveAt(i);
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public boolean removeAll(Collection collection)
        {
            int i = colGetSize();
            boolean flag = false;
            for(int j = 0; j < i; j++)
                if(collection.contains(colGetEntry(j, 1)))
                {
                    colRemoveAt(j);
                    j--;
                    i--;
                    flag = true;
                }

            return flag;
        }

        public boolean retainAll(Collection collection)
        {
            int i = colGetSize();
            boolean flag = false;
            for(int j = 0; j < i; j++)
                if(!collection.contains(colGetEntry(j, 1)))
                {
                    colRemoveAt(j);
                    j--;
                    i--;
                    flag = true;
                }

            return flag;
        }

        public int size()
        {
            return colGetSize();
        }

        public Object[] toArray()
        {
            return toArrayHelper(1);
        }

        public Object[] toArray(Object aobj[])
        {
            return toArrayHelper(aobj, 1);
        }

        final MapCollections this$0;

        ValuesCollection()
        {
            this$0 = MapCollections.this;
            super();
        }
    }

    final class KeySet
        implements Set
    {

        public boolean add(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection collection)
        {
            throw new UnsupportedOperationException();
        }

        public void clear()
        {
            colClear();
        }

        public boolean contains(Object obj)
        {
            boolean flag;
            if(colIndexOfKey(obj) >= 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean containsAll(Collection collection)
        {
            return MapCollections.containsAllHelper(colGetMap(), collection);
        }

        public boolean equals(Object obj)
        {
            return MapCollections.equalsSetHelper(this, obj);
        }

        public int hashCode()
        {
            int i = 0;
            int j = -1 + colGetSize();
            while(j >= 0) 
            {
                Object obj = colGetEntry(j, 0);
                int k;
                if(obj == null)
                    k = 0;
                else
                    k = obj.hashCode();
                i += k;
                j--;
            }
            return i;
        }

        public boolean isEmpty()
        {
            boolean flag;
            if(colGetSize() == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public Iterator iterator()
        {
            return new ArrayIterator(0);
        }

        public boolean remove(Object obj)
        {
            int i = colIndexOfKey(obj);
            boolean flag;
            if(i >= 0)
            {
                colRemoveAt(i);
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public boolean removeAll(Collection collection)
        {
            return MapCollections.removeAllHelper(colGetMap(), collection);
        }

        public boolean retainAll(Collection collection)
        {
            return MapCollections.retainAllHelper(colGetMap(), collection);
        }

        public int size()
        {
            return colGetSize();
        }

        public Object[] toArray()
        {
            return toArrayHelper(0);
        }

        public Object[] toArray(Object aobj[])
        {
            return toArrayHelper(aobj, 0);
        }

        final MapCollections this$0;

        KeySet()
        {
            this$0 = MapCollections.this;
            super();
        }
    }

    final class EntrySet
        implements Set
    {

        public volatile boolean add(Object obj)
        {
            return add((java.util.Map.Entry)obj);
        }

        public boolean add(java.util.Map.Entry entry)
        {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection collection)
        {
            int i = colGetSize();
            java.util.Map.Entry entry;
            for(Iterator iterator1 = collection.iterator(); iterator1.hasNext(); colPut(entry.getKey(), entry.getValue()))
                entry = (java.util.Map.Entry)iterator1.next();

            boolean flag;
            if(i != colGetSize())
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void clear()
        {
            colClear();
        }

        public boolean contains(Object obj)
        {
            boolean flag = false;
            if(obj instanceof java.util.Map.Entry) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            java.util.Map.Entry entry = (java.util.Map.Entry)obj;
            int i = colIndexOfKey(entry.getKey());
            if(i >= 0)
                flag = ContainerHelpers.equal(colGetEntry(i, 1), entry.getValue());
            if(true) goto _L1; else goto _L3
_L3:
        }

        public boolean containsAll(Collection collection)
        {
            Iterator iterator1 = collection.iterator();
_L4:
            if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
            if(contains(iterator1.next())) goto _L4; else goto _L3
_L3:
            boolean flag = false;
_L6:
            return flag;
_L2:
            flag = true;
            if(true) goto _L6; else goto _L5
_L5:
        }

        public boolean equals(Object obj)
        {
            return MapCollections.equalsSetHelper(this, obj);
        }

        public int hashCode()
        {
            int i = 0;
            int j = -1 + colGetSize();
            while(j >= 0) 
            {
                Object obj = colGetEntry(j, 0);
                Object obj1 = colGetEntry(j, 1);
                int k;
                int l;
                if(obj == null)
                    k = 0;
                else
                    k = obj.hashCode();
                if(obj1 == null)
                    l = 0;
                else
                    l = obj1.hashCode();
                i += l ^ k;
                j--;
            }
            return i;
        }

        public boolean isEmpty()
        {
            boolean flag;
            if(colGetSize() == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public Iterator iterator()
        {
            return new MapIterator();
        }

        public boolean remove(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection collection)
        {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection collection)
        {
            throw new UnsupportedOperationException();
        }

        public int size()
        {
            return colGetSize();
        }

        public Object[] toArray()
        {
            throw new UnsupportedOperationException();
        }

        public Object[] toArray(Object aobj[])
        {
            throw new UnsupportedOperationException();
        }

        final MapCollections this$0;

        EntrySet()
        {
            this$0 = MapCollections.this;
            super();
        }
    }

    final class MapIterator
        implements Iterator, java.util.Map.Entry
    {

        public final boolean equals(Object obj)
        {
            int i = 1;
            int j = 0;
            if(!mEntryValid)
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            if(obj instanceof java.util.Map.Entry)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)obj;
                if(!ContainerHelpers.equal(entry.getKey(), colGetEntry(mIndex, 0)) || !ContainerHelpers.equal(entry.getValue(), colGetEntry(mIndex, i)))
                    i = 0;
                j = i;
            }
            return j;
        }

        public Object getKey()
        {
            if(!mEntryValid)
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            else
                return colGetEntry(mIndex, 0);
        }

        public Object getValue()
        {
            if(!mEntryValid)
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            else
                return colGetEntry(mIndex, 1);
        }

        public boolean hasNext()
        {
            boolean flag;
            if(mIndex < mEnd)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public final int hashCode()
        {
            int i = 0;
            if(!mEntryValid)
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            Object obj = colGetEntry(mIndex, 0);
            Object obj1 = colGetEntry(mIndex, 1);
            int j;
            if(obj == null)
                j = 0;
            else
                j = obj.hashCode();
            if(obj1 != null)
                i = obj1.hashCode();
            return i ^ j;
        }

        public volatile Object next()
        {
            return next();
        }

        public java.util.Map.Entry next()
        {
            mIndex = 1 + mIndex;
            mEntryValid = true;
            return this;
        }

        public void remove()
        {
            if(!mEntryValid)
            {
                throw new IllegalStateException();
            } else
            {
                colRemoveAt(mIndex);
                mIndex = -1 + mIndex;
                mEnd = -1 + mEnd;
                mEntryValid = false;
                return;
            }
        }

        public Object setValue(Object obj)
        {
            if(!mEntryValid)
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            else
                return colSetValue(mIndex, obj);
        }

        public final String toString()
        {
            return (new StringBuilder()).append(getKey()).append("=").append(getValue()).toString();
        }

        int mEnd;
        boolean mEntryValid;
        int mIndex;
        final MapCollections this$0;

        MapIterator()
        {
            this$0 = MapCollections.this;
            super();
            mEntryValid = false;
            mEnd = -1 + colGetSize();
            mIndex = -1;
        }
    }

    final class ArrayIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            boolean flag;
            if(mIndex < mSize)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public Object next()
        {
            Object obj = colGetEntry(mIndex, mOffset);
            mIndex = 1 + mIndex;
            mCanRemove = true;
            return obj;
        }

        public void remove()
        {
            if(!mCanRemove)
            {
                throw new IllegalStateException();
            } else
            {
                mIndex = -1 + mIndex;
                mSize = -1 + mSize;
                mCanRemove = false;
                colRemoveAt(mIndex);
                return;
            }
        }

        boolean mCanRemove;
        int mIndex;
        final int mOffset;
        int mSize;
        final MapCollections this$0;

        ArrayIterator(int i)
        {
            this$0 = MapCollections.this;
            super();
            mCanRemove = false;
            mOffset = i;
            mSize = colGetSize();
        }
    }


    MapCollections()
    {
    }

    public static boolean containsAllHelper(Map map, Collection collection)
    {
        Iterator iterator = collection.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        if(map.containsKey(iterator.next())) goto _L4; else goto _L3
_L3:
        boolean flag = false;
_L6:
        return flag;
_L2:
        flag = true;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static boolean equalsSetHelper(Set set, Object obj)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = false;
        if(set != obj) goto _L2; else goto _L1
_L1:
        flag1 = flag;
_L8:
        return flag1;
_L2:
        Set set1;
        if(!(obj instanceof Set))
            continue; /* Loop/switch isn't completed */
        set1 = (Set)obj;
        if(set.size() != set1.size()) goto _L4; else goto _L3
_L3:
        boolean flag2 = set.containsAll(set1);
        if(!flag2) goto _L4; else goto _L5
_L5:
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if(true) goto _L5; else goto _L6
_L6:
        NullPointerException nullpointerexception;
        nullpointerexception;
        continue; /* Loop/switch isn't completed */
        ClassCastException classcastexception;
        classcastexception;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public static boolean removeAllHelper(Map map, Collection collection)
    {
        int i = map.size();
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); map.remove(iterator.next()));
        boolean flag;
        if(i != map.size())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean retainAllHelper(Map map, Collection collection)
    {
        int i = map.size();
        Iterator iterator = map.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(!collection.contains(iterator.next()))
                iterator.remove();
        } while(true);
        boolean flag;
        if(i != map.size())
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected abstract void colClear();

    protected abstract Object colGetEntry(int i, int j);

    protected abstract Map colGetMap();

    protected abstract int colGetSize();

    protected abstract int colIndexOfKey(Object obj);

    protected abstract int colIndexOfValue(Object obj);

    protected abstract void colPut(Object obj, Object obj1);

    protected abstract void colRemoveAt(int i);

    protected abstract Object colSetValue(int i, Object obj);

    public Set getEntrySet()
    {
        if(mEntrySet == null)
            mEntrySet = new EntrySet();
        return mEntrySet;
    }

    public Set getKeySet()
    {
        if(mKeySet == null)
            mKeySet = new KeySet();
        return mKeySet;
    }

    public Collection getValues()
    {
        if(mValues == null)
            mValues = new ValuesCollection();
        return mValues;
    }

    public Object[] toArrayHelper(int i)
    {
        int j = colGetSize();
        Object aobj[] = new Object[j];
        for(int k = 0; k < j; k++)
            aobj[k] = colGetEntry(k, i);

        return aobj;
    }

    public Object[] toArrayHelper(Object aobj[], int i)
    {
        int j = colGetSize();
        if(aobj.length < j)
            aobj = (Object[])(Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), j);
        for(int k = 0; k < j; k++)
            aobj[k] = colGetEntry(k, i);

        if(aobj.length > j)
            aobj[j] = null;
        return aobj;
    }

    EntrySet mEntrySet;
    KeySet mKeySet;
    ValuesCollection mValues;
}
