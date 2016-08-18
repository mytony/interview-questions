import java.util.*;
class Solution {
   public static void main(String[] args) {
       double[] request = new double[]{0.8,1.2,1.3,1.5,1.7,1.9,2.1};
       for(double time: request){
           System.out.println(time+""+limiter(time));
       }
       
   }
   static Deque<Double> q  = new LinkedList<>();
   public static boolean limiter(double time){
       if(q.size()<5||(time-q.peekFirst())>1.0){
          q.add(time);
          if(q.size()>5)
            q.pollFirst();
          return true;
       }else{
          return false;
       }
   }
       
 }
