
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene (String dna)
    {
        int startIndex=dna.indexOf("ATG");
        if (startIndex==-1)
        {
            return "";
        }
        int stopIndex=dna.indexOf("TAA",startIndex+3);
        if (stopIndex==-1)
        {
           return ""; 
        }
        if ((stopIndex-startIndex)%3!=0)
        {
            return "";
        }
        else
        {
            return dna.substring(startIndex,stopIndex+3);
            
        }
    }
    
    public void testSimpleGene(){
        System.out.println("\ndna: AAATGCCCTAACTAGATTAAGAAACC");
        System.out.println("gene is: "+findSimpleGene("AAATGCCCTAACTAGATTAAGAAACC"));
        System.out.println("\ndna: GGATGAAATGGGTAAHHH");
        System.out.println("gene is: "+findSimpleGene("GGATGAAATGGGTAAHHH"));
        System.out.println("\ndna: GGATGAAATGGGTHHH");
        System.out.println("gene is: "+findSimpleGene("GGATGAAATGGGTHHH"));
        System.out.println("\ndna: GGATGAAATGGGTHHHAGDTAA");
        System.out.println("gene is: "+findSimpleGene("GGATGAAATGGGTHHHAGDTAA"));
        
    }
    
    public static void main(String args[])
    {
        Part1 p = new Part1();
        p.testSimpleGene();
        
    }

}
