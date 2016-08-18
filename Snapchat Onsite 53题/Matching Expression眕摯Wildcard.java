public class Solution {
  
    public static void main(String[] args) {
      System.out.println(isMatch("aa","a"));
      System.out.println(isMatch("aa","aa"));
      System.out.println(isMatch("aaa","aa"));
      System.out.println(isMatch("aa", "a*"));
      System.out.println(isMatch("aa", ".*"));
      System.out.println(isMatch("ab", ".*"));
      System.out.println(isMatch("aab", "c*a*b"));
      
      System.out.println("wildcard macthing");
      System.out.println(isMatch1("aa","a"));
      System.out.println(isMatch1("aa","aa"));
      System.out.println(isMatch1("aaa","aa"));
      System.out.println(isMatch1("aa", "*"));
      System.out.println(isMatch1("aa", "a*"));
      System.out.println(isMatch1("ab", "?*"));
      System.out.println(isMatch1("aab", "c*a*b"));

    }
    // Implement regular expression matching with support for '.' and '*'.
    public static boolean isMatch(String s, String p) {
        //base case
        if(p.length() == 0){
            return s.length() == 0;
        }
         
        //special case
        if(p.length() == 1){
            /*if the first char of s and the first char of p is not the same, 
            and the char of p is not '.', return false */
            if(s.length() != 1||((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.'))) return false;
            return true;
        }
         
        //case 1: when the second char of p is not '*', easy case.
        if(p.charAt(1) != '*'){
            if(s.length()==0||((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.'))) return false; 
            return isMatch(s.substring(1), p.substring(1));
        }
         
        //case 2: when the second char of p is '*', complex case.           
        //when the '*' stands for 0 preceding element
        if(isMatch(s, p.substring(2))) return true;
             
        /*when the '*' stands for 1 or more preceding element,
            try every possible number*/
        int i = 0;
        while(i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')){
             if(isMatch(s.substring(i+1), p.substring(2))) return true;
             i++;
         }
        return false;
        
    }

// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
     public static boolean isMatch1(String s, String p) {
        int s1 = 0, p1 = 0, match = 0, starIdx = -1;            
        while (s1 < s.length()){
            // advancing both pointers
            if (p1 < p.length()  && (p.charAt(p1) == '?' || s.charAt(s1) == p.charAt(p1))){
                s1++;
                p1++;
            }
            // * found, only advancing pattern poinstrter
            else if (p1 < p.length() && p.charAt(p1) == '*'){
                starIdx = p1;
                match = s1;
                p1++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p1 = starIdx + 1;
                match++;
                s1 = match;
            }
           //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p1 < p.length() && p.charAt(p1) == '*')
            p1++;

        return p1 == p.length();
    }
}