
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class BabyNames {
    
    public static void printNames()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser)
        {
            System.out.println("Name: "+record.get(0)+" ; Gender: "+record.get(1)+ " ; Number of births: "+record.get(2));
            
        }
        
    }

    public static void totalBirths(FileResource fr)
    {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int girlsNames = 0;
        int boysNames = 0;
        CSVParser parser = fr.getCSVParser(false);

        for(CSVRecord record : parser) {
            int numBorn = Integer.parseInt(record.get(2));
            String gender = record.get(1);
            totalBirths += numBorn;
            if(gender.equals("M")) {
                totalBoys += numBorn;
                boysNames++;
            } else {
                totalGirls += numBorn;
                girlsNames++;
            }
        }

        System.out.println("Total: " + totalBirths);
        System.out.println("Boys: " + totalBoys);
        System.out.println("Girls: " + totalGirls);
        System.out.println("Boys Names: " + boysNames);
        System.out.println("Girls Names: " + girlsNames);
    }
    
    public static void testTotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public static int getRank(int year, String name, String gender)
    {
        int rank = -1;
        int count=0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            String year_name=""+year;
            if (f.getName().contains(year_name))
            {
                FileResource fr = new FileResource(f);
                CSVParser parser = fr.getCSVParser(false);
                for (CSVRecord record : parser)
                {
                    if (record.get(1).equals(gender))
                    {
                        count=count+1;
                        if (record.get(0).equals(name))
                        {
                            rank=count;
                            return rank;
                        }
                    }
                }
            }
            }   
    return rank;
    }
    
    public static String getName(int year, int rank, String gender)
    {
        int count=0;
        String name="";
        DirectoryResource dr = new DirectoryResource();
        outer:for (File f : dr.selectedFiles())
        {
            if (f.getName().contains(""+year))
            {
                FileResource fr = new FileResource(f);
                CSVParser parser = fr.getCSVParser(false);
                for (CSVRecord record : parser)
                {
                    if (record.get(1).equals(gender))
                    {
                        count=count+1;
                        if (count==rank)
                        {
                            name=name+record.get(0);
                            break outer;
                        }
                    }
                }
            }
        }
        
        return name;
    }
    
    public static void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank = getRank(year,name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println("In year "+year+", the rank of the name "+name+" was: "+rank);
        System.out.println("In year "+newYear+", the name at rank "+rank+" is: "+newName);
    }
    
    public static int yearOfHighestRank (String name, String gender)
    {
        int highRank=0;
        int highRankYear=0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            int currYear=Integer.parseInt(f.getName().substring(3,7));
            int currRank=getRank(currYear,name,gender);
            
            if (highRank==0)
            {
                highRank=currRank;
                highRankYear=currYear;
            }
            else if(currRank<highRank)
            {
                highRank=currRank;
                highRankYear=currYear;
            }
        }
        if (highRankYear==0)
        {
            return -1;
        }
        return highRank;
    }
    
    public static double getAverageRank(String name, String gender)
    {
        double avgRank=0.0;
        int count=0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            count++;
            int currYear=Integer.parseInt(f.getName().substring(3,7));
            int currRank=getRank(currYear,name,gender);
            avgRank=avgRank+currRank;
        }
        if (count==0)
        {
            return -1.0;
        }
        else
        {
            return (avgRank/count);
        }
    }
    
    public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int numBorn = 0;
		long rank = getRank(year, name, gender);
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			int currBorn = Integer.parseInt(record.get(2));
			String currGender = record.get(1);
			long currRank = record.getRecordNumber();
			if(gender.equals(currGender) && rank > currRank) {
				numBorn += currBorn;
			}
		}
		return numBorn;
	}
    
    public static void main()
    {
        //System.out.println("Rank is: "+getRank(1971,"Frank","M"));
        //System.out.println("Rank is: "+getName(2012,1,"F"));
        //whatIsNameInYear("Isabella",2012,2014,"F");
        //System.out.println(yearOfHighestRank("Mason","M"));
        System.out.println(getAverageRank("Robert","M"));
    }
}
