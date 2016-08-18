// DP 走K步 从A到B 

import java.util.*;
public class Solution {
  
    public static void main(String[] args) {
      
      int result = pathNum(new Point(0,0),new Point(2,2),3,3);
      System.out.println(result);
    }
    // Implement regular expression matching with support for '.' and '*'.
    public static int pathNum(Point A,Point B, int size, int k) {
    	int[][][]dp = new int[size][size][2];
    	dp[A.x][A.y][0] = 1;
    	int i;
    	// O(M*N*K) //
    	for(i = 1; i<=k ; i++){
    		for(int m = 0; m < size; m++){
    			for(int n = 0; n < size; n++){
    				dp[m][n][i%2] = sumNeighbor(dp,i,m,n,size); 
    			}	
    		}
    	}
    	return dp[B.x][B.y][k%2];
		
	}
	

	public static int sumNeighbor(int[][][]dp, int i, int x, int y, int size){
		int[] dx = {1, 1,1,-1,-1,-1,0, 0 };
		int[] dy = {1,-1,0, 1,-1, 0,1,-1 };
		int sum = 0;
		for(int k = 0; k<8; k++){
			int newX = x + dx[k];
			int newY = y + dy[k];
            
			if(newX>=0 && newX<size && newY>=0 && newY<size){
				sum += dp[newX][newY][(i-1)%2];
			}
		}
		return sum;
	}
	 static class Point{
    	int x;
    	int y;
    	Point(int x, int y){
    		this.x = x;
    		this.y = y;
    	}
    }

}