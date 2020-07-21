import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;

public class LZW {

    File file ;
    Map<String, Integer> dictionary = new LinkedHashMap<>();
    int lastEntry = 256;
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
        WritableRaster raster = image.getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
        byte [] b = data.getData();
        String input = Base64.getEncoder().encodeToString(b);
        for(int i=0 ; i < b.length; i++)
        {
          //  System.out.print(b[i]);
        }
//        int width = image.getWidth();
//        int height = image.getHeight();
//        int [][] arr = new int[width][height];
//
//        for(int i =0 ; i < width ; i++)
//        {
//            for(int j=0; j< height ; j++)
//            {
//                arr[i][j] = image.getRGB(i, j);
//            }
//        }

        //try processing the uncompressed file as a string'
        // and the compressed output as a list of integers
        String s="";
        List <Integer> output = new ArrayList<>();

        for( char c: input.toCharArray())
        {
          String temp=s+c;
            if(dictionary.containsKey(temp))
            {
                s = temp;
            }
            else
            {
                output.add(dictionary.get(s));
                dictionary.put(temp, lastEntry+1);
                lastEntry++;
                s=""+c;
            }
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(output.size() * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        int [] intar = new int[output.size()];
        int index=0;

        for(Integer i : output)
        {
            intar[index] = i;
            System.out.println(intar[index]);

            index++;
        }
        intBuffer.put(intar);
        byte[] array = byteBuffer.array();

        OutputStream
                os
                = new FileOutputStream("src\\Res\\new.LZW");
        os.write(array);
        // Starts writing the bytes in it

     //   os.write(intar.toString());

        //   int [][] outputarr = new int [width][height];
     //   for( int i=0 ; i <)
//        for(int i =0 ; i < width ; i++)
//        {
//            for(int j=0; j< height ; j++)
//            {
//
//                if(dictionary.containsKey( s+ arr[i][j]))
//                {
//                    s= ""+ s+ dictionary.get(arr[i][j]);
//                }
//                else
//                {
//                    s="" +s+dictionary.get(arr[i][j]);
//                    outputarr[i][j] = arr[i][j];
//                    dictionary.put(s, lastEntry+1);
//                    s= ""+ dictionary.get(arr[i][j]);
//                    lastEntry = dictionary.size();
//                }
//            }
//        }
//        BufferedImage img = image;
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                img.setRGB(i, j , outputarr[i][j]);
//            }
//        }

     //   File outputfile = new File("src\\Res\\t.jpg");
     //   ImageIO.write(img, "jpg", outputfile);

    }

    public void decodeImage(File file) throws IOException
    {
        Scanner scanner = new Scanner(file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Byte> compressed = new ArrayList<>();
      //  int i = 0;
        String line;
        while((line = bufferedReader.readLine()) != null)
        {
            compressed.add(Byte.valueOf(line));
            System.out.println(line);
        }
        int dictSize = 256;
//        Map<Integer,String> dictionary = new HashMap<Integer,String>();
//        for (int i = 0; i < 256; i++)
//            dictionary.put(i, "" + (char)i);

        String w = "" + (char)(int)compressed.remove(0);
        StringBuffer result = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k).toString();
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result.append(entry);

            // Add w+entry[0] to the dictionary.
            dictionary.put( w + entry.charAt(0) , lastEntry++);

            w = entry;
        }
        byte [] out = result.toString().getBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(out);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "gif", new File("src\\Res\\output.gif") );
    //    System.out.println("image created");
        //result
    }
    //fill dictionary with values from ascii table and add a pseudo-eof (maybe flush character )
    //encoding function to be called three times:
    // 1- image
    // 2- text file
    // 3- folder containing 1 and 2
    // decoding function 1 or 2
    // apply both in case of files and folder including files

}
