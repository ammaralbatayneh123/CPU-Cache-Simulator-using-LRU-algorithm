import java.util.LinkedHashMap;
import java.util.Map;

public class MainMemory {
    private Map<Integer, Integer> data; // here the map data structure will act as our main memory to store and retreive our values

    public MainMemory()
    {
        this.data = new LinkedHashMap<Integer, Integer>();
    }

    public int getDataFromMemory(int givenKey)
    {
        // Return the value for the given key  or -1 if the key is not found
        return data.getOrDefault(givenKey, -1);
    }

    public void insertDataToTheMemory(int givenKey, int givenValue)
    {
        // we added  the key-value pair to our  main memory
        System.out.println("Inserting " + givenValue +" to the main memory with key " + givenKey);
        System.out.println();
        data.put(givenKey, givenValue);
    }
}
