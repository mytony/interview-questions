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

    int[]a = {3,3,3,3};
      
    boolean res = game(a);
    System.out.println(res);  
  }
 
  private static boolean game(int[] nums){
    if(nums==null)  return false;
    Arrays.sort(nums);
    HashSet<Integer> res=new HashSet<Integer>();
    return gameHelper(nums,new boolean[nums.length],new ArrayList<Integer>());
    
  }
  private static boolean gameHelper(int[] nums,boolean[] visited,List<Integer> elem){
        
    if(elem.size()==nums.length){
       for(int x:cal(elem,0,elem.size()-1))
           if(x==24) return true;
    }
    
    for(int i=0;i<nums.length;i++){
       if(visited[i]||i>0&&nums[i]==nums[i-1]&&!visited[i-1])
         continue;
       elem.add(nums[i]);
       visited[i]=true;
       if (gameHelper(nums,visited,elem)) return true;
       elem.remove(elem.size()-1);
       visited[i]=false;
    }
    return false;
  }
  
  private static List<Integer> cal(List<Integer> elem, int begin,int end){
     List<Integer> res=new ArrayList<Integer>();
     if(begin==end){
       res.add(elem.get(begin));
       return res;
     }                
     for(int i=begin;i<end;i++){
        List<Integer> left=cal(elem,begin,i);
        List<Integer> right=cal(elem,i+1,end);
        for(int x:left)
          for(int y:right){
             res.add(x+y);
             res.add(x-y);
             res.add(x*y);
             if(y!=0)                                 
               res.add(x/y);
          }
       }
     return res;
     }


   public static List<Integer> diffWaysToCompute(String input) {
    List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                String a = input.substring(0, i);
                String b = input.substring(i + 1);
                List<Integer> al = diffWaysToCompute(a);
                List<Integer> bl = diffWaysToCompute(b);
                for (int x : al) {
                    for (int y : bl) {
                        if (c == '-') {
                            res.add(x - y);
                        } else if (c == '+') {
                            res.add(x + y);
                        } else if (c == '*') {
                            res.add(x * y);
                        }
                    }
                }
            }
        }
        if (res.size() == 0) res.add(Integer.valueOf(input));
        return res;
    }
   
  
  
}
