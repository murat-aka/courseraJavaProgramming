
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
            totalBirths ++;
            if (rec.get(1).equals("M")) {
                totalBoys ++;
            }
            else {
                totalGirls ++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob1900.csv");
        totalBirths(fr);
    }
    
    
    public int getRank(int year, String name, String gender){
        int rank = -1;
        int count = 1;
        
        FileResource fr = new FileResource("data/yob"+Integer.toString(year)+".csv");
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
        //System.out.println(getRank(1960, "Emily", "F"));
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    
    public String getName(int year, int rank, String gender){
        int count = 1;
        
        String name="NO NAME";
        FileResource fr = new FileResource("data/yob"+Integer.toString(year)+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            
            if (rec.get(1).equals(gender) && count==rank) {
                name = rec.get(0);
            }
            if(rec.get(1).equals(gender))count++;
        }
        
        return name;
    }
    
    
    public void testGetName(){
        
        //System.out.println(getName(1980,350,"F"));
        System.out.println(getName(1982,450,"M"));
        
    }
    
    
    public void whatIsNameInYear(String name,int year, int newYear, String gender){
        
        int rank = getRank(year,name,gender);
        System.out.println(name+" born in "+year+" would be "+getName(newYear,rank,gender)+ " if she was born in "+newYear);
    }
    
    public void testWhatIsNameInYear(){
        //whatIsNameInYear("Susan",1972, 2014, "F");
        whatIsNameInYear("Owen",1974, 2014, "M");
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
        
        //System.out.println(yearOfHighestRank("Genevieve","F"));
        System.out.println(yearOfHighestRank("Mich","M"));
    }
    
    public double getAvarageRank(String name, String gender){
        
        double avarageRank = -1.0;
        int temp=0;
        int count = 1;
        
        String filename="";
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            
            filename = f.getPath().substring(f.getPath().indexOf("yob")+3,f.getPath().indexOf("yob")+7);
            //System.out.println(filename);
            int currentRank = getRank(Integer.parseInt(filename),name,gender);
            //System.out.println(currentRank);
            
          
            if(currentRank != -1 ){
               temp += currentRank; 
               avarageRank=(double)temp/count;
               System.out.println(temp);
               count++;
            }
        }
        
        
        return avarageRank;
        
    }
    
    
    public void testGetAvarageRank(){
        
        //System.out.println(getAvarageRank("Susan","F"));
        //System.out.println(getAvarageRank("Jacob","M"));
        System.out.println(getAvarageRank("Robert","M"));
    }
    
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        
        int totalNumOfBirths=0;
        
        int rank = getRank(year, name, gender);
        
        //System.out.println(rank);
        
        FileResource fr = new FileResource("data/yob"+Integer.toString(year)+".csv");
         
        for (CSVRecord rec : fr.getCSVParser(false)) {
           
           // System.out.println(rec.get(2));
            if (rank < 2)break;
               
            
            
            if(rec.get(1).equals(gender))
            {totalNumOfBirths += Integer.parseInt(rec.get(2));
              rank--;
            }
        }
        
        return totalNumOfBirths;
    }
       
    
    
    public void testGetTotalBirthsRankedHigher(){
        
        //System.out.println(getTotalBirthsRankedHigher(2012,"Noah","M"));
        //System.out.println(getTotalBirthsRankedHigher(1990,"Emily","F"));
         System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
    
    

}


