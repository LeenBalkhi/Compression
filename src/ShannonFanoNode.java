import java.util.LinkedHashMap;

public class ShannonFanoNode {

    int weight;
    LinkedHashMap<Byte , Integer> frequency = new LinkedHashMap<>();
    ShannonFanoNode parent = new ShannonFanoNode();
}
