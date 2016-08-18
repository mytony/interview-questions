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

   Interval a = new Interval(0,2);
   Interval b = new Interval(1,4);
   Interval c = new Interval(2,3);
   Interval d = new Interval(2,5);
  
    Interval[] interval = {c,b,a,d};
    minMeetingRooms(interval);
    
  }
  private static class Meeting{
        int roomid;
        Interval interval;
        Meeting(int id, Interval interval){
            this.roomid = id;
            this.interval = interval;
        }
    }
  static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
  static Comparator<Interval> compStart = new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start-b.start;
            } 
        };
    
  static Comparator<Meeting> compEnd =  new Comparator<Meeting>(){
            public int compare(Meeting a, Meeting b){
                return a.interval.end-b.interval.end;
            } 
        };
  
  static void minMeetingRooms(Interval[] intervals) {
        if(intervals.length==0) return ;
       
       //sorting by start
       Arrays.sort(intervals,compStart);
       
       //heap sorting by end
       Queue<Meeting> pq = new PriorityQueue<>(compEnd);
       int count = 0;
       for(Interval interval:intervals){
         Meeting a;
         if(!pq.isEmpty()&&interval.start >= pq.peek().interval.end){
            a = new Meeting(pq.poll().roomid,interval);
         }else{
           count++;
           a = new Meeting(count,interval);
         }
         pq.offer(a); 
         System.out.println("meeting["+a.interval.start+","+a.interval.end+"]" + ": in Room "+a.roomid);
         
       }

       
       
  }
}

