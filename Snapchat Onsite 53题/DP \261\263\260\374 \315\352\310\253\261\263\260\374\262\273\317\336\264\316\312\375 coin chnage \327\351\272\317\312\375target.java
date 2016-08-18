import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

// 从 A[]中拿数 组合成target的总次数
class Solution {
  public static void main(String[] args) {
      int[] A={1,2,3};
      int res = validcomb(A,4);
      System.out.println(res);
    }
  
 public static int validcomb(int[] input, int target) {
     int [] dp = new int[target+1];
    //  初始化 
   
   dp[0] = 1;
   // for(int i = 1; i<=target; i++){
   //  dp[i] = 0;  
   // }
   
   
     //可重复
     for(int j = 1; j<=target;j++){
       for(int i:input){
          if( j>=i ){
            dp[j]+=dp[j-i];
          }
     }
   }

   // 不可重复
       for(int i : input){
          for(int j = 1; j<=target;j++){
          if( j>=i ){
            dp[j]+=dp[j-i];
          }
     }
   }

   return dp[target];
    
}

 public int coinChange(int[] coins, int amount) {
        //http://blog.csdn.net/you12345678901234567/article/details/8130804
        if (amount == 0) return 0;
        int dp[] = new int[amount + 1];
        dp[0]=0;

        for (int i = 1; i <= amount; i++)
            dp[i] = Integer.MAX_VALUE;              
            
        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i <= amount; i++) {
               if (i - coins[j] >=0 && dp[i-coins[j]]!=Integer.MAX_VALUE)
               dp[i] = Math.min(dp[i-coins[j]]+1, dp[i]);
                    //last = j;
            }
            // coin[amount+1]---存储相应值下应该找的硬币面额。
            // 循环求面额 就可以输出找钱策略
            //coin[i]=last; 
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
  
  }
}