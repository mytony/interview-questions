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
       // TODO Auto-generated method stub
     int[]A={3,1,2,4,5,6};
     int[]res2 = twoSum(A,5);
     int[]res3 = threeSum(A,8);
     A=new int[]{7,3,1,2,4,5,6};
     int[]res4 = fourSum(A,13);
     
     
     System.out.println(res2[0]+" " + res2[1]);
     
     System.out.println(res3[0]+" " + res3[1] +" "+res3[2]);
     
      System.out.println(res4[0]+" " + res4[1] +" "+res4[2]+" "+res4[3]);
 
}
  static int[] twoSum(int[]A,int target){
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    int[] res = new int[2];
    for(int i=0;i<A.length;i++){
      if(map.containsKey(target - A[i])){
        res[0] = map.get(target-A[i]);
        res[1] = i;
        break;
      }
        
      map.put(A[i],i);  
    }
    return res;
    
  }

  static int[] threeSum(int[]A,int target){
    HashMap<Integer,int[]> map = new HashMap<Integer,int[]>();
    int[] res = new int[3];
    int[] pair = {0,1};
    map.put(A[0]+A[1],pair);
    for(int i=2;i<A.length;i++){
      if(map.containsKey(target - A[i])){
        pair = map.get(target-A[i]);
        res[0] = pair[0];
        res[1] = pair[1];
        res[2] = i;
        return res;
      }
      for(int j=0;j<i;j++){
        int[] pos = {j,i};
        map.put(A[j]+A[i],pos); 
      }
    }
    return res;
    
  }
  
   static int[] fourSum(int[]A,int target){
    HashMap<Integer,int[]> map = new HashMap<Integer,int[]>();
    int[] res = new int[4];
       
    map.put(A[0]+A[1],new int[]{0,1});
    map.put(A[2]+A[0],new int[]{2,0});
    map.put(A[2]+A[1],new int[]{2,1});
     
    for(int i=3;i<A.length;i++){
      for(int j=0;j<i;j++){
      if(map.containsKey(target - A[i]-A[j])){
        int[] pair = map.get(target-A[i]-A[j]);
        if(pair[0]!=j&&pair[1]!=j){
          res[0] = pair[0];
          res[1] = pair[1];
          res[2] = j;
          res[3] = i;
          return res;
        }
      }
      
        int[] pos = {j,i};
        map.put(A[j]+A[i],pos); 
      }
    }
    return res;
    
  }
  
  
}
   
  

