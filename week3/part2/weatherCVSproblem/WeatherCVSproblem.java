
/**
 * Write a description of WeatherCVSproblem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class WeatherCVSproblem {
    
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    
    public void testColdestHourInFile(){
        
        FileResource fr = new FileResource();
		CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    
        
    public void testFileWithColdestTemperature(){
        
		String filename = fileWithColdestTemperature();
		FileResource fr = new FileResource(filename);
		CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest day was in file "+ filename);
		System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
		System.out.println("All the Temperature on the coldest day were:");
		
		for (CSVRecord currentRow : fr.getCSVParser()) {
            // use method to compare two records
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF") );;
        }
	}
	
	
    public String  fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        CSVRecord coldestTemp;
        String filename="";
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
                
        for (File f : dr.selectedFiles()) {
            
            coldestTemp = coldestSoFar;
            FileResource fr = new FileResource(f);
            // use method to get smallest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
            if(!coldestSoFar.equals(coldestTemp))filename = f.getPath();
            
        }
        //The coldestSoFar is the answer
        return filename;
    }

    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < smallestTemp) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
       
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
             
        for (CSVRecord currentRow : parser) {
            
            if(smallestSoFar.equals(null))smallestSoFar = currentRow;
            
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double smallestHumidity = Double.parseDouble(smallestSoFar.get("Humidity"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentHumidity < smallestHumidity) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        
        return smallestSoFar;
    }
    
    public void  testLowestHumidityInFile(){
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
        
    }
    
}
