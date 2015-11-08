
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    
    
    public void tester(){
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser,"Nauru"));
        //listExportersTwoProducts(parser, "fish", "nuts");
        //System.out.println(numberOfExporters(parser,"sugar" ));
        bigExporters(parser,"$999,999,999,999");
        
    }
    
    
    public String countryInfo(CSVParser parser, String country){
        
        for (CSVRecord record : parser){
            
            if(record.get("Country").equals(country)){
                //System.out.println("FOUND");
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)") ;
            }
            
            //System.out.println(country + " ");
            //System.out.println(record.get("Country") + " ");
			//System.out.print(record.get("Name") + " ");
			//System.out.print(record.get("Favorite Color") + " ");
			//System.out.println(record.get("Favorite Food"));
		}
		
		return "NOT FOUND!";
    }
    
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        
        for (CSVRecord record : parser){
        
          if(record.get("Exports").contains(exportItem1)  &&  record.get("Exports").contains(exportItem2) ){
              
              System.out.println(record.get("Country"));
              
          }
        
        
       }
    }
    
    
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        
        int count=0;
        
        for (CSVRecord record : parser){
        
          if(record.get("Exports").contains(exportItem) ){
              
              count++;
              
          }
        
        
        }
        
        return count;
    }
    
    
    
    public void bigExporters(CSVParser parser, String amount){
        
        for (CSVRecord record : parser){
        
          if(record.get("Value (dollars)").length() > amount.length()){
              
             
              System.out.println(record.get("Country") +" " + record.get("Value (dollars)"));
          }
        
        
        }
        
    }

}
