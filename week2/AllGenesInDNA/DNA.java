
/**
 * Write a description of DNA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import edu.duke.*;

public class DNA {



    public int findStopIndex(String dna, int index){
        
        int stop1 = dna.indexOf("tga",index);
        if(stop1==-1 || (stop1-index)%3 !=0){
            stop1 = dna.length();
        }
        
        int stop2 = dna.indexOf("taa",index);
        if(stop2 == -1 || (stop2-index)%3 !=0){
            stop2 = dna.length();            
        }
        
        int stop3 = dna.indexOf("tag",index);
        if(stop3 == -1 || (stop3-index)%3 !=0){
            stop3 = dna.length();            
        }
        
        return Math.min(stop1,Math.min(stop2,stop3));
        
    }
    
    
    
public StorageResource storeAll(String dna){
    
    
    StorageResource store = new StorageResource();
    
    int start = 0;
    while(true){
        int loc = dna.toLowerCase().indexOf("atg",start);
        if(loc == -1){
            break;
        }
       // System.out.println("starts at "+loc);
        int end = findStopIndex(dna.toLowerCase(),loc+3);
       // System.out.println("stops at "+end);
        
        if(end!=dna.length()){
            String string = dna.substring(loc,end+3);
            System.out.println(string);
            store.add(string);
        }
        start = loc +3;
    }
    
    return store;
}


 public void testFinder(){
    
    storeAll("ATGAAATGAAAA");
    
    System.out.println("\n");
    
    storeAll("ccatgccctaataaatgtctgtaatgtaga");
    
    System.out.println("\n");
    
    storeAll("CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA");
}

public void testStorageFinder() {
        
        
        FileResource fr = new FileResource("dna/brca1line.fa");
        
            
        String s = fr.asString();
        
        //storeAll(s);
        printGenes(storeAll(s));
            
            
            
        
	}


public void cgRatio(String dna){
    
    
    int numOfCs = 0;
    int numOfGs = 0;
    
    numOfCs = findCorG("c",dna);
    numOfGs = findCorG("g",dna);

    int total = 0;
    
    total = numOfGs + numOfCs;
    
    double ratio = total / dna.length() ;
    
    //System.out.println(numOfCs);
    //System.out.println(numOfGs);
    //System.out.println(ratio);
}


public int findCorG(String letter, String dna){
    
    int start = 0;
    int numberOf =0;
    
        while(true){
         
        
        int loc = dna.toLowerCase().indexOf(letter,start);
        if(loc == -1){
            break;
        }
        //System.out.println("starts at "+loc);
        numberOf++;
        start = loc +1;
    }
    
    return numberOf;
}


public void printGenes( StorageResource sr){
    
    int counterBigger60 =0;
    int counterBiggerCG = 0;
    //System.out.println(sr.size());
    for(String s : sr.data()){
        
        if(s.length() > 60){
            System.out.println(s);
            counterBigger60++;
            //System.out.println("biggerthan60: " + counterBigger60);
        }
        //System.out.println("biggerthan60: " + counterBigger60);
        
    int numOfCs = 0;
    int numOfGs = 0;
    
    numOfCs = findCorG("c",s);
    numOfGs = findCorG("g",s);

    int total = 0;
    
    total = numOfGs + numOfCs;
    
    float ratio = (float)total / s.length() ;
    
    
    if(ratio > 0.35){
        System.out.println(s);
        counterBiggerCG++;
        //System.out.println("biggerthan0.35: " + counterBiggerCG);
    }
    //System.out.println("biggerthan0.35: " + counterBiggerCG);
    
    }
    
    System.out.println("biggerthan60: " + counterBigger60);
}

}