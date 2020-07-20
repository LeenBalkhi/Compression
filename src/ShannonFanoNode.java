import java.util.LinkedHashMap;

public class ShannonFanoNode {

    int weight;
    LinkedHashMap<String , Double> frequency = new LinkedHashMap<>();
    ShannonFanoNode parent = new ShannonFanoNode();
}
