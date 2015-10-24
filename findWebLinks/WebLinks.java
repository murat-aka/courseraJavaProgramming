import edu.duke.*;

/**
 * Write a description of WebLinks here.
 * 
 * Write a program that reads the lines from the 
 * file at this URL location,
 * http://www.dukelearntoprogram.com/course2/data/manylinks.html,
 * and prints each URL on the page that is a link to youtube.com.
 * 
 * @author (murat aka) 
 * @version (24/10/2015)
 */
public class WebLinks {

    public void findLink(){
		URLResource rs = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html"); 
		for (String word : rs.words()) {
		    
		    int find = word.toLowerCase().indexOf("youtube.com");
			if(find!=-1){
			    System.out.println(word);
			    int start = word.indexOf("\"", 0);
			    int stop = word.lastIndexOf("\"", word.length());
			    
			    System.out.println(start);
			    System.out.println(stop);
			    String URL = word.substring(start, stop+1);
			    
			    System.out.println(URL);
			 }
			//System.out.println(word);
		}
	}
    
}
