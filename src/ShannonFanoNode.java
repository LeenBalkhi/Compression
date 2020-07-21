import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class ShannonFanoNode {

    int weight;
    public TreeMap<Integer, Byte > frequency = new TreeMap<>();
    // treemap.subMap(start index, end index)
    int repetition;
    //could be converted to a priority queue with overriding compareable to compare between int values (number of repetition)
    ShannonFanoNode parent = new ShannonFanoNode();
    public void t()
    {
       // frequency.subMap(0,50);
    }
}
