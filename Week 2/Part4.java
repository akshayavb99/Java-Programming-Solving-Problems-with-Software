import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void readUrl(String link){
        String str = "youtube.com";
        int beginUrl, endUrl;
        URLResource url = new URLResource(link);
        for(String w : url.words()){
           // check if youtube.com is in the word
           if(w.indexOf(str) > -1 || w.indexOf(str.toUpperCase()) > -1){
               // finding the double quotes
               beginUrl  = w.indexOf("\"");
               endUrl = w.lastIndexOf("\"");
               System.out.println(w.substring(beginUrl+1,endUrl));          
               
            }
        }    
    }
    public static void main(String[] args){
        Part4 p = new Part4();
        p.readUrl("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }

}