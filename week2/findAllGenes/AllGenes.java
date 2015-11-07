
/**
 * Write a description of AllGenes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllGenes {

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
    
    
    
public void printAll(String dna){
    
    int start = 0;
    while(true){
        int loc = dna.toLowerCase().indexOf("atg",start);
        if(loc == -1){
            break;
        }
        System.out.println("starts at "+loc);
        int end = findStopIndex(dna.toLowerCase(),loc+3);
        System.out.println("stops at "+end);
        
        if(end!=dna.length()){
            String string = dna.substring(loc,end+3);
            System.out.println(string);
        }
        start = loc +3;
    }
}


 public void testFinder(){
    
    printAll("ATGAAATGAAAA");
    
    System.out.println("\n");
    
    printAll("ccatgccctaataaatgtctgtaatgtaga");
    
    System.out.println("\n");
    
    printAll("CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA")
    ;
}


}





