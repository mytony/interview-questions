import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class Solution {
  public static void main(String[] args) {

    // boolean res = wordPatternMatch("abab","redblueredblue");
    boolean res = wordPatternMatch("aabb","xyzabcxzyabc");
    // boolean res = wordPatternMatch("aaaa","asdasdasdasd");
    System.out.println(res);
    
    
  }

   static Map<Character,String> map =new HashMap<>();
   static Set<String> set =new HashSet<>();
   public static boolean wordPatternMatch(String pattern, String str) {
    if(pattern.isEmpty()) return str.isEmpty();
    Character c = pattern.charAt(0);
    if(map.containsKey(c)){
        String value= map.get(c);
        if(str.length()<value.length() || !str.substring(0,value.length()).equals(value)) 
            return false;
        return wordPatternMatch(pattern.substring(1),str.substring(value.length()));
    }else{
        for(int i=1;i<=str.length();i++){
            if(set.contains(str.substring(0,i))) continue;
            map.put(pattern.charAt(0),str.substring(0,i));
            set.add(str.substring(0,i));
            if(wordPatternMatch(pattern.substring(1),str.substring(i))) return true;
            set.remove(str.substring(0,i));
            map.remove(pattern.charAt(0));
        }
    }
    return false;
    
    }
  
  
}
