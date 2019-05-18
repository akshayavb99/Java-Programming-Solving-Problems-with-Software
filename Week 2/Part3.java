
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        if(stringb.indexOf(stringa) > -1 && stringb.indexOf(stringa,stringb.indexOf(stringa)+stringa.length()) > -1)
            return true;            
        return false;       
    }
    
    public void testing(){
        System.out.println("twoOccurrences(\"by\",\"A story by Abby Long\")"+
        twoOccurrences("by", "A story by Abby Long"));
        System.out.println("twoOccurrences(\"atg\",\"ctgtatgta\")"+
        twoOccurrences("atg","ctgtatgta"));        
        System.out.println("the part of the string after an in banana is "+
        lastPart("an","banana"));
        System.out.println("the part of the string after zoo in forest is "+
        lastPart("zoo","forest"));
        
    }
    public String lastPart(String stringa, String stringb){
        String result = stringb;
        int stringaIndex = stringb.indexOf(stringa);
        int stringaLength = stringa.length();
        
        if(stringaIndex > -1){
            result = stringb.substring(stringaIndex+stringaLength);
        }
        
        return result;
    }
    
    public static void main(String[] args){
        Part3 p = new Part3();
        p.testing();

    
    }
}