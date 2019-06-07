
/**
 * Write a description of WeatherCheck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
public class WeatherCheck {
    
    public static CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldest=null;
        for (CSVRecord record: parser)
        {
            if (coldest==null)
            {
                coldest=record;
                continue;
            }
            double coldestNow = Double.parseDouble(coldest.get("TemperatureF"));
            double currTemp=Double.parseDouble(record.get("TemperatureF"));
            if (currTemp==-9999.0)
                continue;
            else
            {
                if (coldestNow>currTemp)
                {
                    coldest=record;
                }
            }
        }
        return coldest;
    }
    
    public static void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println(coldestRecord.get("TemperatureF")+" "+coldestRecord.get("TimeEST"));
    }

    public static String fileWithColdestTemperature()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestNow=null;
        String filename="";
        for (File f: dr.selectedFiles())
        {
            FileResource fr  = new FileResource(f);
            CSVRecord coldestTempFr=coldestHourInFile(fr.getCSVParser());
            if (coldestNow==null)
            {
                filename=f.getName();
                coldestNow=coldestTempFr;
            }
            else
            {
                double coldestTempNow=Double.parseDouble(coldestNow.get("TemperatureF"));
                double coldestInFile=Double.parseDouble(coldestTempFr.get("TemperatureF"));
                if (coldestInFile<coldestTempNow)
                {
                    coldestNow=coldestTempFr;
                    filename=f.getName();
                }
                
            }
        }
        System.out.println("Coldest Temperature was on "+filename+" and the temeprature was "+coldestNow.get("TemperatureF"));
        return filename;
    }
    
    public static void testFileWithColdestTemperature()
    {
        System.out.println(fileWithColdestTemperature());
        
    }
    
    public static CSVRecord lowestHumidityInFile (CSVParser parser)
    {
        CSVRecord lowest=null;
        for (CSVRecord record: parser)
        {
            if (lowest==null)
            {
                lowest=record;
                continue;
            }
            double lowestNow = Double.parseDouble(lowest.get("Humidity"));
            String humCurr=record.get("Humidity");
            if (humCurr.equals("N/A"))
                continue;
            else
            {   double currHum=Double.parseDouble(humCurr);
                if (lowestNow>currHum)
                {
                    lowest=record;
                }
            }
        }
        return lowest;
    }
    
    public static void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" on "+csv.get("DateUTC"));
    }
    
    public static CSVRecord lowestHumidityInManyFiles()
    {
        CSVRecord lowestHumdity = null;
		int currHumd;
		int lowestHumd;

		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord currRow = lowestHumidityInFile(parser);
			
			if(lowestHumdity == null) {
				lowestHumdity = currRow;
			} 
			else {
				int currTemp = Integer.parseInt(currRow.get("Humidity"));
				int lowestTemp = Integer.parseInt(lowestHumdity.get("Humidity"));
				if(currTemp < lowestTemp) {
					lowestHumdity = currRow;
				}

				else {
					if(currRow.get("Humidity") != "N/A" && lowestHumdity.get("Humidity") != "N/A") {
						currHumd = Integer.parseInt(currRow.get("Humidity"));
						lowestHumd = Integer.parseInt(lowestHumdity.get("Humidity"));
						
						if(currHumd < lowestHumd) {
							lowestHumdity = currRow;
						}
					}
				}
			}
		}
		return lowestHumdity;
    }
    
    public static void testLowestHumidityInManyFiles() 
    {
        CSVRecord lowestNow=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowestNow.get("TemperatureF")+" at "+lowestNow.get("DateUTC"));
    }
    
    public static double averageTemperatureInFile(CSVParser parser) {
		double num = 0.0;
		double sum = 0.0;

		for(CSVRecord record : parser) {
			double temp = Double.parseDouble(record.get("TemperatureF"));
			sum += temp;
			num++;
		}

		double average = sum / num;
		return average;
	}

	public static void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double avg = averageTemperatureInFile(parser);

		System.out.println("average temperature is " + avg);
	}

	public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double num = 0.0;
		double sum = 0.0;

		for(CSVRecord record : parser) {
			double temp = Double.parseDouble(record.get("TemperatureF"));
			int humidity = Integer.parseInt(record.get("Humidity"));
			if(humidity >= value) {
				sum += temp;
				num++;
			}
		}

		double average = sum / num;
		return average;
	}

	public static void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double avg = averageTemperatureWithHighHumidityInFile(parser, 80);

		if(!Double.isNaN(avg)) {
			System.out.println("average temperature is " + avg);
		} else {
			System.out.println("No Temperature was found");
		}
	}
}
