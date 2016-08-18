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

    List<List<Integer>> res = Amicable(66992);
    System.out.println(res);
  }
//   1 2 3 4 5 6 7 8 9 
// 1 1 1 1 1 1 1 1 1 1
// 2       2   2   2
// 3           3     3
// 4               
  public static List<List<Integer>> Amicable (int n) {
        // write your code here
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int[] sum= new int[n+1];
        for(int i=0; i<=n; i++){
          sum[i] = 1;
        }
        // O(nlogN)  n*(1/2+1/3+1/4....1/n)
         for (int i=2; i<=n/2; i++) {
            for (int j=2*i; j<=n; j+=i)
                sum[j] += i;
        }
      HashMap<Integer,Integer> hash = new HashMap<>();
      for(int i=0; i<=n; i++){
          if(hash.containsKey(i)) {
             if(hash.get(i)==sum[i]){
               List<Integer>list = new ArrayList<>();
               list.add(sum[i]);
               list.add(i);
               res.add(list);
             }
          }
          else{
            hash.put(sum[i],i);
           }
        }

    return res;
    
    
    }
  
  
}
