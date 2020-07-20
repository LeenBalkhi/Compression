import java.util.LinkedHashMap;
import java.util.Map;

public class ShannonFanoNode {

    int weight;
    public Map<Byte , Integer> frequency = new LinkedHashMap<>();
    int repetition;
    //could be converted to a priority queue with overriding compareable to compare between int values (number of repetition)
    ShannonFanoNode parent = new ShannonFanoNode();
}
