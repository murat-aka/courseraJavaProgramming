
/**
 * Write a description of Gene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gene {

    
    public String findProtein(String dna) {
		int start = dna.indexOf("atg");
		if (start == -1) {
			return "";
		}
		int stop = dna.indexOf("tag", start+3);
		if ((stop - start) % 3 == 0) {
			return dna.substring(start, stop+3);
		}
		
		stop = dna.indexOf("tga", start+3);
		if ((stop - start) % 3 == 0) {
			return dna.substring(start, stop+3);
		}
		
		stop = dna.indexOf("taa", start+3);
		if ((stop - start) % 3 == 0) {
			return dna.substring(start, stop+3);
		}
		
		
		else {
			return "-1";
		}
	}
	
	
	public String stopCodon(String dna) {
		
		int stop = dna.indexOf("tag");
		if (stop != -1) {
			return "tag";
		}
		
		stop = dna.indexOf("tga");
		if (stop != -1) {
			return "tga";
		}
		
		stop = dna.indexOf("taa");
		if (stop != -1) {
			return "taa";
		}
		
		else {
			return "";
		}
	}
	
	public void testing() {
		//String a = "cccatggggtttaaataataataggagagagagagagagttt";
		//String ap = "atggggtttaaataataatag";
		//String a = "atgcctag";
		//String ap = "";
		//String a = "ATGCCCTAG";
		//String ap = "ATGCCCTAG";
		//String a="ataaactatgttttaaatgt";
		//String a="AATGCTAGTTTAAATCTGA";
		String a="AAATGCCCTAACTAGATTGAAACC";
		//String a= "acatgataacctaag";
		String result = findProtein(a.toLowerCase());
		if (true) {
			
			System.out.println("found gene: " + result + " length " + result.length());
			System.out.println("stop codon: " + stopCodon(result));
		}
		else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			
		}
    
    
}
}