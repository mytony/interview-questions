// Edit distance substring
import java.util.*;
public class Solution {
  
    public static void main(String[] args) {

      System.out.println(isOneEditDistance("abec","dddeabcxdegs"));
    }

  public static boolean isOneEditDistance(String s, String t) {
     int lens = s.length();
     for (int j = 0; j <= t.length()-lens + 1; j++) {
      // candidate substring p in t with length = s.length + 1; 	
      	String p = t.substring(j, Math.min(j + lens + 1, t.length())); 
      	int lenp = p.length(); 
      	boolean oneDiff=false;  
      	for (int i = 0; i < Math.min(lens,lenp); i++) { 
        	if (s.charAt(i) != p.charAt(i)) {       		
            	if(  s.substring(i+1).equals(p.substring(i+1, Math.min(lens,lenp)))          				    
                 ||s.substring(  i).equals(p.substring(i+1, Math.min(lens+1,lenp)))
                 ||s.substring(i+1).equals(p.substring(i, lens-1))) return true;
                oneDiff = true;
                break;
        	}
      	}
      	// every char is same, at most oneedit dis
      	if(!oneDiff) return true; 
     }       
    return false;   
    }
}