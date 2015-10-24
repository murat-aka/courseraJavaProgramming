
/**
 * Write a description of AllGenes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllGenes {

    public int findStopIndex(String dna, int index){
        
        int stop1 = dna.indexOf("tga",index);
        if(stop1==-1 || (stop1-index%3 !=0)){
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
    
}
