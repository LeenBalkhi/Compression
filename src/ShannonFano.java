import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import java.util.TreeMap;

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

               newNode.frequency.put(repetion+1, b[i]);
           }
           else{
               newNode.frequency.put(1, b[i]);
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
        for(Map.Entry< Integer, Byte> frequency: node.frequency.entrySet())
        {
            sum+=frequency.getValue();
        }
        node.repetition = sum;

        ShannonFanoNode Firstnode = new ShannonFanoNode();
        int index=0;
        for(Map.Entry< Integer , Byte> frequency: node.frequency.entrySet())
        {

            sum+=frequency.getValue();
            Firstnode.frequency.put(frequency.getKey(), frequency.getValue());
            index++;
            if(sum >= node.repetition/2)
            {
                ShannonFanoNode temp = new ShannonFanoNode();
                //temp.frequency = Firstnode.frequency.subMap(0, 2);
                break;
            }
        }
        return list;
    }
    //encode
}
