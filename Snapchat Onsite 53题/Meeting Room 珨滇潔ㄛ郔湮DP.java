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

   Interval a = new Interval(0,2,3);
   Interval b = new Interval(1,4,6);
   Interval c = new Interval(2,3,5);
   Interval d = new Interval(2,5,4);
  
    Interval[] interval = {a,b,c,d};
   int res = meetingroom(interval);
       System.out.println(res);
  }
   public static class Interval {
      int start;
      int end;
      int weight;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e, int w) { start = s; end = e; weight = w;}
  }

   static Comparator<Interval> comp = new Comparator<Interval>(){
        public int compare(Interval i1, Interval i2)
        {   
            if(i1.start!=i2.start) 
              return i1.start-i2.start;
            else  
              return i1.end-i2.end;
        }
    };
  
  public static int meetingroom (Interval[] meetings) {
        Arrays.sort(meetings,comp);
        int[] dp = new int[meetings.length];
        int max = 0;
        for(int i = 0; i < meetings.length; i++) {
            dp[i] = meetings[i].weight;
            for(int j = 0; j < i; j++) {
                if(meetings[j].end <= meetings[i].start) {
                    dp[i] = Math.max(dp[i], dp[j] + dp[i]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
  
  }
}

