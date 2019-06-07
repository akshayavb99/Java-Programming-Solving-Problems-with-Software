
/**
 * Write a description of ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;
public class ImageInversion {
    
    public static ImageResource imageInvert(ImageResource inImage)
    {
        //Make a blank image of same size as inImage
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        
        //for each pixel in outImage
        for (Pixel pi : outImage.pixels())
        {
            //look at corresponding pixel in inImage
            Pixel inPi=inImage.getPixel(pi.getX(),pi.getY());
            
            //get RBG values of original pixel
            int red=inPi.getRed();
            int green=inPi.getGreen();
            int blue=inPi.getBlue();
            
            //set outImage pixels
            pi.setRed(255-red);
            pi.setGreen(255-green);
            pi.setBlue(255-blue);
        }
        //return the final output
        return outImage;
    }
    
    public static void selectAndConvert()
    {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            ImageResource ir = new ImageResource(f);
            ImageResource gray = imageInvert(ir);
            gray.draw();
            String ir_name=ir.getFileName();
            String new_ir_name="inverted-"+ir_name;
            gray.setFileName(new_ir_name);
            gray.save();
        }
    }
    
    public static void main()
    {
        //ImageResource ir = new ImageResource();
        //ImageResource gray=makeGray(ir);
        //gray.draw();
        selectAndConvert();
    }

}
