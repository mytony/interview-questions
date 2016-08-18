DNA
import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class Abbr {
   public static void main(String[] args) {
       // TODO Auto-generated method stub
	   String s ="GAGAGAGAGAGA"; 
	   System.out.println( findRepeatedDnaSequences(s));
   
  }
   public static List<String> findRepeatedDnaSequences(String s) {
       Set<Integer> firstTime = new HashSet<Integer>();
       Set<Integer> secondTime = new HashSet<Integer>();
       List<String> list = new ArrayList<String>();
       char[] map = new char[20];
       int len = s.length();

       // Hashing function. We have only 4 letters which we can represent by 2 bits.
       map['A' - 'A'] = 0; // A = 00
       map['C' - 'A'] = 1; // B = 01
       map['G' - 'A'] = 2; // C = 10
       map['T' - 'A'] = 3; // D = 11
           
           int sequence = 0;
           for(int j=0; j<Math.min(len,10); j++)
              sequence = (sequence << 2) | map[s.charAt(j) - 'A'];
           
           firstTime.add(sequence);

       		int mask = (1 << 20) - 1;
       		for(int i=10; i< len; i++)
       		{
               sequence = (sequence << 2 &mask)| map[s.charAt(i) - 'A'];
			   if(!firstTime.add(sequence) && secondTime.add(sequence))
               		list.add(s.substring(i-9, i+1));
       		}

       		return list;
   }
}
