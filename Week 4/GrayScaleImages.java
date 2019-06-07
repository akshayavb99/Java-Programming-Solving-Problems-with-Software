
/**
 * Write a description of GrayScaleImages here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class GrayScaleImages 
{
    public static ImageResource makeGray(ImageResource inImage)
    {
        //Make a blank image of same size as inImage
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        
        //for each pixel in outImage
        for (Pixel pi : outImage.pixels())
        {
            //look at corresponding pixel in inImage
            Pixel inPi=inImage.getPixel(pi.getX(),pi.getY());
            
            //compute the avreage of red,green,blue of inPi
            int avgPi=(inPi.getRed()+inPi.getGreen()+inPi.getBlue())/3;
            
            //set outImage pixels
            pi.setRed(avgPi);
            pi.setGreen(avgPi);
            pi.setBlue(avgPi);
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
            ImageResource gray = makeGray(ir);
            gray.draw();
            String ir_name=ir.getFileName();
            String new_ir_name="gray-"+ir_name;
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
