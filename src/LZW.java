import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class LZW {

    File file ;
    Map<String, Integer> dictionary = new LinkedHashMap<>();
    int lastEntry = 128;
    public void setUpDictionary()
    {
        for(int i=0 ; i <  lastEntry; i++)
        {
            String s= ""+(char)i;
            dictionary.put(s, i);
        }
    }

    public void encodeImage(File file) throws IOException {
        this.file = file;

      BufferedImage  image = ImageIO.read(file);

      //Reading the image as a byte array
//        WritableRaster raster = image .getRaster();
//        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
//        byte [] b = data.getData();
//
//        for(int i=0 ; i < b.length; i++)
//        {
//            System.out.print(b[i]);
//        }
        int width = image.getWidth();
        int height = image.getHeight();
        int [][] arr = new int[width][height];

        for(int i =0 ; i < width ; i++)
        {
            for(int j=0; j< height ; j++)
            {
                arr[i][j] = image.getRGB(i, j);
            }
        }

        String s="";
        int [][] outputarr = new int [width][height];
        for(int i =0 ; i < width ; i++)
        {
            for(int j=0; j< height ; j++)
            {

                if(dictionary.containsKey( s+ arr[i][j]))
                {
                    s= ""+ s+ dictionary.get(arr[i][j]);
                }
                else
                {
                    s="" +s+dictionary.get(arr[i][j]);
                    outputarr[i][j] = arr[i][j];
                    dictionary.put(s, lastEntry+1);
                    s= ""+ dictionary.get(arr[i][j]);
                    lastEntry = dictionary.size();
                }
            }
        }
        BufferedImage img = image;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                img.setRGB(i, j , outputarr[i][j]);
            }
        }

        File outputfile = new File("src\\Res\\t.jpg");
        ImageIO.write(img, "jpg", outputfile);

    }
    //fill dictionary with values from ascii table and add a pseudo-eof (maybe flush character )
    //encoding function to be called three times:
    // 1- image
    // 2- text file
    // 3- folder containing 1 and 2
    // decoding function 1 or 2
    // apply both in case of files and folder including files

}
