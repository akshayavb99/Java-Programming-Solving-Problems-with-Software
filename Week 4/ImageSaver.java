
/**
 * Write a description of ImageSaver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class ImageSaver {
    
    public static void doSave()
    {
       DirectoryResource dr = new DirectoryResource();
       for (File f : dr.selectedFiles())
       {
           ImageResource ir = new ImageResource(f);
           String ir_name=ir.getFileName();
           String new_ir_name="copy-"+ir_name;
           ir.setFileName(new_ir_name);
           ir.draw();
           ir.save();
        }
    }

}
