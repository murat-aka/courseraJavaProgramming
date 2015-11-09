
/**
 * Write a description of MiniProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class MiniProject {
    
    
    public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
			}
			else {
				totalGirls += numBorn;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource("data/yob2014.csv");
		totalBirths(fr);
	}
	
	
	public int getRank(int year, String name, String gender){
	    int rank = -1;
	    int count = 1;
	    
	    FileResource fr = new FileResource("testing/yob"+Integer.toString(year)+"short.csv");
		for (CSVRecord rec : fr.getCSVParser(false)) {
			
			
			if (rec.get(1).equals(gender) && rec.get(0).equals(name)) {
				rank = count;
			}
			if(rec.get(1).equals(gender))count++;
		}
		
		return rank;
	}
	
	public void testGetRank() {
		//FileResource fr = new FileResource();
		System.out.println(getRank(2012, "Mason", "F"));
	}
	
	
	public String getName(int year, int rank, String gender){
	    int count = 1;
	    
	    String name="NO NAME";
	    FileResource fr = new FileResource("testing/yob"+Integer.toString(year)+"short.csv");
		for (CSVRecord rec : fr.getCSVParser(false)) {
			
			
			if (rec.get(1).equals(gender) && count==rank) {
				name = rec.get(0);
			}
			if(rec.get(1).equals(gender))count++;
		}
		
	    return name;
	}
	
	
	public void testGetName(){
	    
	    System.out.println(getName(2012,20,"M"));
	    
	}
	
	
	public void whatIsNameInYear(String name,int year, int newYear, String gender){
	    
	    int rank = getRank(year,name,gender);
	    System.out.println(name+" born in "+year+" would be "+getName(newYear,rank,gender)+ " if she was born in "+newYear);
	}
	
	public void testWhatIsNameInYear(){
	    whatIsNameInYear("Isabella",2012, 2014, "F");
	}
	
	
	public int yearOfHighestRank(String name, String gender){
	    
	   
	    int yearOfHighestRank=-1;
	    int temp=0;
	    String filename="";
	    DirectoryResource dr = new DirectoryResource();
        // iterate over files
                
        for (File f : dr.selectedFiles()) {
            
            filename = f.getPath().substring(f.getPath().indexOf("yob")+3,f.getPath().indexOf("yob")+7);
            //System.out.println(filename);
            int currentRank = getRank(Integer.parseInt(filename),name,gender);
            //System.out.println(currentRank);
            
            if(currentRank != -1 && temp == 0){
                temp=currentRank;
                yearOfHighestRank=Integer.parseInt(filename);
                
            }
            if(currentRank<temp && currentRank != -1){
                
                temp = currentRank;
                yearOfHighestRank = Integer.parseInt(filename);
            }
            
        }
        //The coldestSoFar is the answer
        
	    
	    return yearOfHighestRank;
	}
	
	public void testYearOfHighestRank(){
	    
	    System.out.println(yearOfHighestRank("Mason","F"));
	}

}
