
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

	public void testHottestInDay () {
		FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
		CSVRecord largest = coldestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("TimeEST"));
	}

	public CSVRecord hottestInManyDays() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			// use method to compare two records
			largestSoFar = getSmallestOfTwo(currentRow, largestSoFar);
		}
		//The largestSoFar is the answer
		return largestSoFar;
	}

	public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
		//If largestSoFar is nothing
		if (largestSoFar == null) {
			largestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
			//Check if currentRow’s temperature > largestSoFar’s
			if (currentTemp > largestTemp) {
				//If so update largestSoFar to currentRow
				largestSoFar = currentRow;
			}
		}
		return largestSoFar;
	}

	public void testHottestInManyDays () {
		CSVRecord largest = hottestInManyDays();
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("DateUTC"));
	}

}
