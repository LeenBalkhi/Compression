import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class ShannonFano {

    //path/file
    LinkedList<ShannonFanoNode> tree = new LinkedList<>();
    File file;
    public void buildFrequencyTableImage() throws IOException {
        //creates first node

        BufferedImage image = ImageIO.read(file);

     //   Reading the image as a byte array
        WritableRaster raster = image .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
        byte [] b = data.getData();
        ShannonFanoNode newNode = new ShannonFanoNode();
        for(int i=0 ; i < b.length; i++)
        {
           if(newNode.frequency.containsKey(b[i])){
              int repetion= newNode.frequency.get(b[i]);

               newNode.frequency.put(b[i],repetion+1);
           }
           else{
               newNode.frequency.put(b[i],1);
           }

        }
        tree.add(newNode);
    }
    public void code()
    {
        //apply shannon fano code (build tree )
    }
    public LinkedList<ShannonFanoNode> splitnode(ShannonFanoNode node)
    {
        LinkedList<ShannonFanoNode> list = new LinkedList<>();
        int sum =0;
        for(Map.Entry<Byte , Integer> frequency: node.frequency.entrySet())
        {
            sum+=frequency.getValue();
        }
        node.repetition = sum;

        ShannonFanoNode Firstnode = new ShannonFanoNode();
        for(Map.Entry<Byte , Integer> frequency: node.frequency.entrySet())
        {

            sum+=frequency.getValue();
            Firstnode.frequency.put(frequency.getKey(), frequency.getValue());
            if(sum >= node.repetition/2)
            {
                list.add(Firstnode);
                break;
            }
        }
        return list;
    }
    //encode
}
