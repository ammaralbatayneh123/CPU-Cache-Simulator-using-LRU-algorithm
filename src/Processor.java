import java.util.Random;

public class Processor {
    private final Cache cache; // declaring our cache in the processor
    private final MainMemory mainMemory; // declaring the main memory in the processor
    private final Random randomKeyGenerator; // declaring a random variable  that will store a random key to retrieve the random data
    private int cacheHits;
    private int cacheMiss;

    public Processor(Cache cache, MainMemory mainMemory)
    {
        this.cache = cache;
        this.mainMemory = mainMemory;
        this.randomKeyGenerator = new Random();
        cacheHits = 0;
        cacheMiss = 0;
    }

    public Cache getCache(){
        return cache;
    }

    public int getCacheHits() {
        return cacheHits;
    }

    public int getCacheMiss() {
        return cacheMiss;
    }

    public void requestData()
    {
        // here we generated our  random key we can specify the bound for the random class generator
        // so we can generate key with higher values
        int Randomkey =  randomKeyGenerator.nextInt(1000000);

        // printing the generated key
        System.out.println("generated  random  key =  " + Randomkey);

        // here we are trying to get the value from the cache using the generated key
        int valueRetrieved = cache.retrieveData(Randomkey);

        // printing the value that we got from the cache
        //System.out.println("value from the cache = " + valueRetrieved);

        System.out.println();

        if (valueRetrieved == -1)
        {

            System.out.println("value from the cache = " + valueRetrieved);
            cacheMiss++;

            // If the value is not found in the cache, we get it from main memory and then we add it to the cache
            valueRetrieved = mainMemory.getDataFromMemory(Randomkey);

            if (valueRetrieved == -1)
            {
                System.out.println("The data is not found in the memory so we cant add it to the cache");
                System.out.println();
            }
            else
            {
                cache.insertData(Randomkey, valueRetrieved);

                // Print the value that was retrieved
                System.out.println("the value that is retrieved from the memory is "+valueRetrieved);

                System.out.println();

                System.out.println("What happened here is when we requested the value that is associated with \n" +
                        "key " + Randomkey + " from the cache we did not get any hit so we requested it from the main \n" +
                        "memory and then we added it to the cache");

                System.out.println();
            }

        }
        else
        {
            System.out.println("value from the cache = " + valueRetrieved);
            cacheHits++;

            System.out.println();

            System.out.println("Here we founded the value directly from the cache and then we returned it so no need to \n" +
                    "get it from the memory ");

            System.out.println();
        }
    }
}
