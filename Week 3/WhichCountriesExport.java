
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class WhichCountriesExport {
    
    public static String countryInfo(CSVParser parser, String country)
    {
        for (CSVRecord record:parser)
        {
            if (record.get("Country").equals(country))
            {
                return (country+":"+record.get("Exports")+":"+record.get("Value (dollars)"));
            }
            
        }
        return "NOT FOUND";
    }
    
    public static void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2)
    {
        for (CSVRecord record : parser)
        {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2))
            {
                System.out.println (record.get("Country"));
                
            }
            
            
        }
        
    }
    
    public static int numberOfExporters (CSVParser parser, String exportItem)
    {
        int count=0;
        for (CSVRecord record: parser)
        {
            if(record.get("Exports").contains(exportItem))
            {
                count++;
                System.out.println(record.get("Country"));
            }
        }
        return count;
    }   
    
    public static void bigExporters (CSVParser parser, String amount)
    {
        if (amount.charAt(0)!='$')
        {
            System.out.println("Invalid Amount!");
        }
        for (CSVRecord record: parser)
        {
            String compare=record.get("Value (dollars)");
            if (compare.compareTo(amount)<0)
            {
                System.out.println(record.get("Country")+" "+compare);
            }
        }
        
    }
    
    
    public static void test()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //System.out.println(countryInfo(parser,"Nauru"));
        
        //listExportersTwoProducts(parser, "cotton", "flowers");
        
        //parser = fr.getCSVParser();
        //System.out.println(numberOfExporters(parser,"cocoa"));
        
        //parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    

}
