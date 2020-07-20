import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main{

    public static void main (String[] args) throws IOException {

        LZW l = new LZW();
        l.setUpDictionary();
        File file = new File("C:\\Users\\Leen\\Desktop\\Multimedia\\Compression\\src\\Res\\ex3.jpg");
        l.encodeImage(file);
        /*
        1- compress an image and a text file
        2- compress a folder containing images and text files using both algorithms
        3- compress using JPEG lossy compression
        4- compare between compression ratio of used algorithms for an image compression
        5- decompress any file you have compressed
        */
    }
}
