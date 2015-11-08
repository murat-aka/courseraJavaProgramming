
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
                if(currentTemp!=-9999)smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
       
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
             
        for (CSVRecord currentRow : parser) {
            
            if(smallestSoFar == null)smallestSoFar = currentRow;
            
            if(currentRow.get("Humidity").equals("N/A"))continue;
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
    
    
    public CSVRecord lowestHumidityInManyFiles(){
        
        CSVRecord lowestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			// use method to compare two records
			
			if(lowestSoFar == null)lowestSoFar = currentRow;
			double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < lowestTemp) {
                //If so update largestSoFar to currentRow
                lowestSoFar = currentRow;
            }
		}
		//The largestSoFar is the answer
		return lowestSoFar;
        
    } 
    
    public void testLowestHumidityInManyFiles(){
		CSVRecord csv = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
	}
	
	
	public double averageTemperatureInFile(CSVParser parser){
	    
	    double sum=0;
	    double avarage =0;
	    int count = 1;
	   for (CSVRecord currentRow : parser) {
            // use method to compare two records
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum += currentTemp;
            avarage =sum/count;
            count++;
        }
	   
        return avarage;
	}
	
	public void  testAverageTemperatureInFile(){
	    
	    
	    FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
	    double avarage = averageTemperatureInFile(parser);
	    System.out.println("Average temperature in file is " + avarage);
	    
	}
	   
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value ){
        
        double sum=0;
	    double avarage =0;
	    int count = 1;
	   for (CSVRecord currentRow : parser) {
            // use method to compare two records
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if(currentHumidity>=value){
                sum += currentTemp;
                avarage =sum/count;
                count++;
            }
        }
	   
        return avarage;
    }
    
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
	    double avarage = averageTemperatureWithHighHumidityInFile(parser,80);
	    if(avarage==0)System.out.println("No temperatures with that humidity");
	     
	    else System.out.println("Average temperature when high Humidity is " + avarage);
        
    }
    
}
