// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.spi;

import java.util.*;

// Referenced classes of package ch.qos.logback.core.spi:
//            ComponentTracker

public abstract class AbstractComponentTracker
    implements ComponentTracker
{
    private static class Entry
    {

        public boolean equals(Object obj)
        {
            boolean flag = true;
            if(this != obj) goto _L2; else goto _L1
_L1:
            return flag;
_L2:
            if(obj == null)
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            if(getClass() != obj.getClass())
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            Entry entry = (Entry)obj;
            if(key == null)
            {
                if(entry.key != null)
                {
                    flag = false;
                    continue; /* Loop/switch isn't completed */
                }
            } else
            if(!key.equals(entry.key))
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            if(component == null)
            {
                if(entry.component != null)
                    flag = false;
            } else
            if(!component.equals(entry.component))
                flag = false;
            if(true) goto _L1; else goto _L3
_L3:
        }

        public int hashCode()
        {
            return key.hashCode();
        }

        public void setTimestamp(long l)
        {
            timestamp = l;
        }

        public String toString()
        {
            return (new StringBuilder()).append("(").append(key).append(", ").append(component).append(")").toString();
        }

        Object component;
        String key;
        long timestamp;

        Entry(String s, Object obj, long l)
        {
            key = s;
            component = obj;
            timestamp = l;
        }
    }

    private static interface RemovalPredicator
    {

        public abstract boolean isSlatedForRemoval(Entry entry, long l);
    }


    public AbstractComponentTracker()
    {
        maxComponents = 0x7fffffff;
        timeout = 0x1b7740L;
        liveMap = new LinkedHashMap(32, 0.75F, true);
        lingerersMap = new LinkedHashMap(16, 0.75F, true);
        lastCheck = 0L;
        byExcedent = new RemovalPredicator() {

            public boolean isSlatedForRemoval(Entry entry, long l)
            {
                boolean flag;
                if(liveMap.size() > maxComponents)
                    flag = true;
                else
                    flag = false;
                return flag;
            }

            final AbstractComponentTracker this$0;

            
            {
                this$0 = AbstractComponentTracker.this;
                super();
            }
        }
;
        byTimeout = new RemovalPredicator() {

            public boolean isSlatedForRemoval(Entry entry, long l)
            {
                return isEntryStale(entry, l);
            }

            final AbstractComponentTracker this$0;

            
            {
                this$0 = AbstractComponentTracker.this;
                super();
            }
        }
;
        byLingering = new RemovalPredicator() {

            public boolean isSlatedForRemoval(Entry entry, long l)
            {
                return isEntryDoneLingering(entry, l);
            }

            final AbstractComponentTracker this$0;

            
            {
                this$0 = AbstractComponentTracker.this;
                super();
            }
        }
;
    }

    private void genericStaleComponentRemover(LinkedHashMap linkedhashmap, long l, RemovalPredicator removalpredicator)
    {
        Iterator iterator = linkedhashmap.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Entry entry = (Entry)((java.util.Map.Entry)iterator.next()).getValue();
            if(!removalpredicator.isSlatedForRemoval(entry, l))
                break;
            iterator.remove();
            processPriorToRemoval(entry.component);
        } while(true);
    }

    private Entry getFromEitherMap(String s)
    {
        Entry entry = (Entry)liveMap.get(s);
        if(entry == null)
            entry = (Entry)lingerersMap.get(s);
        return entry;
    }

    private boolean isEntryDoneLingering(Entry entry, long l)
    {
        boolean flag;
        if(10000L + entry.timestamp < l)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean isEntryStale(Entry entry, long l)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_3;
        if(!isComponentStale(entry.component) && entry.timestamp + timeout >= l)
            flag = false;
        return flag;
    }

    private boolean isTooSoonForRemovalIteration(long l)
    {
        boolean flag;
        if(1000L + lastCheck > l)
        {
            flag = true;
        } else
        {
            lastCheck = l;
            flag = false;
        }
        return flag;
    }

    private void removeExcedentComponents()
    {
        genericStaleComponentRemover(liveMap, 0L, byExcedent);
    }

    private void removeStaleComponentsFromLingerersMap(long l)
    {
        genericStaleComponentRemover(lingerersMap, l, byLingering);
    }

    private void removeStaleComponentsFromMainMap(long l)
    {
        genericStaleComponentRemover(liveMap, l, byTimeout);
    }

    public Collection allComponents()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = liveMap.values().iterator(); iterator.hasNext(); arraylist.add(((Entry)iterator.next()).component));
        for(Iterator iterator1 = lingerersMap.values().iterator(); iterator1.hasNext(); arraylist.add(((Entry)iterator1.next()).component));
        return arraylist;
    }

    public Set allKeys()
    {
        HashSet hashset = new HashSet(liveMap.keySet());
        hashset.addAll(lingerersMap.keySet());
        return hashset;
    }

    protected abstract Object buildComponent(String s);

    public void endOfLife(String s)
    {
        Entry entry = (Entry)liveMap.remove(s);
        if(entry != null)
            lingerersMap.put(s, entry);
    }

    /**
     * @deprecated Method find is deprecated
     */

    public Object find(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Entry entry = getFromEitherMap(s);
        if(entry != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return obj;
_L2:
        obj = entry.component;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public int getComponentCount()
    {
        return liveMap.size() + lingerersMap.size();
    }

    public int getMaxComponents()
    {
        return maxComponents;
    }

    /**
     * @deprecated Method getOrCreate is deprecated
     */

    public Object getOrCreate(String s, long l)
    {
        this;
        JVM INSTR monitorenter ;
        Entry entry;
        entry = getFromEitherMap(s);
        if(entry != null)
            break MISSING_BLOCK_LABEL_53;
        entry = new Entry(s, buildComponent(s), l);
        liveMap.put(s, entry);
_L1:
        Object obj = entry.component;
        this;
        JVM INSTR monitorexit ;
        return obj;
        entry.setTimestamp(l);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public long getTimeout()
    {
        return timeout;
    }

    protected abstract boolean isComponentStale(Object obj);

    protected abstract void processPriorToRemoval(Object obj);

    /**
     * @deprecated Method removeStaleComponents is deprecated
     */

    public void removeStaleComponents(long l)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isTooSoonForRemovalIteration(l);
        if(!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        removeExcedentComponents();
        removeStaleComponentsFromMainMap(l);
        removeStaleComponentsFromLingerersMap(l);
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void setMaxComponents(int i)
    {
        maxComponents = i;
    }

    public void setTimeout(long l)
    {
        timeout = l;
    }

    private static final boolean ACCESS_ORDERED = true;
    public static final long LINGERING_TIMEOUT = 10000L;
    public static final long WAIT_BETWEEN_SUCCESSIVE_REMOVAL_ITERATIONS = 1000L;
    private RemovalPredicator byExcedent;
    private RemovalPredicator byLingering;
    private RemovalPredicator byTimeout;
    long lastCheck;
    LinkedHashMap lingerersMap;
    LinkedHashMap liveMap;
    protected int maxComponents;
    protected long timeout;


}
