import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {
    private int cacheCapacity; // a variable to store the size of the cache that i will read from the user

    // The cache is implemented using the  LinkedHashMap, because it  maintains the order of the data inserted.
    // When a new item is added to the cache and the cache reaches the full capacity, the least recently used item will be removed.
    // by the LRU algorithm
    private Map<Integer, Integer> CPUcache; // this is the LinkedHashMap that will act as our cache

    public Cache(int capacity)
    {
        this.cacheCapacity = capacity;
        this.CPUcache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true)
        {
            // i overrided the  removeEldestEntry method in the LinkedHashMap so when it reaches its full capacity it will
            // automatically remove the least recent one
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> Eldest)
            {
                return size() > capacity;
            }
        };
    }

    public Map<Integer, Integer> getCPUcache() {
        return CPUcache;
    }

    public int retrieveData(int givenKey)
    {
        if (CPUcache.containsKey(givenKey))
        {
            // If the key is founded  in the cache, then we return its value
            return CPUcache.get(givenKey);
        }
        else
        {
            // If the key is not founded  in the cache, we  return -1
            return -1;
        }
    }

    public void insertData(int givenKey, int givenValue)
    {
        // Add the new  key-value pair to our  cache
        CPUcache.put(givenKey, givenValue);
    }
}
