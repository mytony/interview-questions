// 2 一段视频中有一些可以放广告的位置，长度不同，有一堆可以选的广告，长度也不同，问怎么放最多和最长的广告DP 广告调度
   
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
      
      // int[] m = {5,10};
  	  int m = 23;
      int[] A = {1,2,6,3,4,2};
      int[] V = {1,2,6,3,4,2};
      int res = backPackII2(m,A,V);

      System.out.println(res);
    }

    public static int backPackII2(int m, int[] A, int V[]) {
        int[][] res = new int[2][m+1];
        res[0][0] = 0;
        for (int i=1; i<=A.length; i++) {
            for (int j=0; j<=m; j++) {
                if (j - A[i-1] < 0)
                    res[i%2][j] = res[(i-1)%2][j];
                else{
                    res[i%2][j] = Math.max(res[(i-1)%2][j], res[(i-1)%2][j-A[i-1]]+V[i-1]);
                }
            }
        }

        return res[A.length%2][m];
    }
    
    public static int backPackII(int m, int[] A,int V[]) {
        // write your code here
        int[] res = new int[m+1];
        res[0] = 0;
        for (int i=0; i<A.length; i++) {
            for (int j=m; j>=A[i]; j--) {
                res[j] = Math.max(res[j], res[j-A[i]]+V[i]);
            }
        }
        return res[m];
    }
}


//01背包 () 
            // for i: 0..N
            //   for v: m..cost[i]
            //       f[v] = max(f[v],f[v-cost[i]]+w[i])
            
//完全背包(coin change)
            // for i: 0..N
            //   for v: cost[i]..m
            //       f[v] = max(f[v],f[v-cost[i]]+w[i])
            
            
            
