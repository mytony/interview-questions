import java.util.*;
public class Solution {
  
    public static void main(String[] args) {
      int[][]board = {{0,0,0,0},{0,0,1,0},{0,0,0,0},{0,0,0,0}};
     
      System.out.println( uniquePathsWithObstacles(board));
      printAllPath(board,0,0,new ArrayList<>());
    }

public static void printAllPath(int[][] obstacleGrid, int x, int y, ArrayList<String>path){
    if(x==obstacleGrid.length-1 && y ==obstacleGrid[0].length-1)
        {
            path.add(x+","+y);  
            System.out.println(path);
            path.remove(path.size()-1);
            return;
        }
     path.add(x+","+y); 
    if(x+1<obstacleGrid.length&&obstacleGrid[x+1][y]!=1)
        printAllPath(obstacleGrid, x+1, y, path);

    if(y+1<obstacleGrid[0].length&&obstacleGrid[x][y+1]!=1)
        printAllPath(obstacleGrid, x, y+1, path);
    path.remove(path.size()-1);
}

public static int uniquePaths(int m, int n) {
        // write your code here 
        if (m == 0 || n == 0) {
            return 0;
        }
        
        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            sum[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            sum[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
            }
        }
        return sum[m - 1][n - 1];
    }

public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m == 0 || n == 0) {
            return 0;
        }
        
        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0]==1) break;
            sum[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i]==1) break;
            sum[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                 if (obstacleGrid[i][j]==1) 
                    sum[i][j]=0;
                 else
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
            }
        }
        return sum[m - 1][n - 1];
    }
}