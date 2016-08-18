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
      int[] A={1,2,3};
      ArrayList<ArrayList<String>> res = totalNQueens(4);
      System.out.println(res);
    }
  
  public static ArrayList<ArrayList<String>> totalNQueens(int n) {  
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if(n<=0) return result;  
        int[] loc = new int[n];  
        dfs(loc,0,n,result);  
        return result;  
    }  
      
    public static void dfs(int[] loc, int cur, int n, ArrayList<ArrayList<String>> result){  
        if (cur==n) {  
            result.add(drawChessboard(loc));  
            return;  
        }  
        for(int i=0;i<n;i++){  
            loc[cur] = i;  
            if(isValid(loc,cur))  
                dfs(loc,cur+1,n, result);  
        }  
    }  
      
    public static boolean isValid(int[] loc, int cur){  
        for(int i=0;i<cur;i++){  
            if(loc[i]==loc[cur]||Math.abs(loc[i]-loc[cur])==(cur-i))  
                return false;  
        }  
        return true;  
    }
  
  
   private static ArrayList<String> drawChessboard(int[] cols) {
        ArrayList<String> chessboard = new ArrayList<String>();
        for (int i = 0; i < cols.length; i++) {
            chessboard.add(new String(""));
            for (int j = 0; j < cols.length; j++) {
                if (j == cols[i]) {
                    chessboard.set(i,chessboard.get(i)+"Q");
                } else {
                    chessboard.set(i,chessboard.get(i)+".");
                }
            }
        }
        
        return chessboard;
    }
  
  public static void rotate(int[] loc){
      int n = loc.length;
      //90
      ArrayList<Integer> loc90 = new ArrayList<Integer>(n);
      loc90 = Hori(R45(loc));
      
      //180
      ArrayList<Integer> loc180 = new ArrayList<Integer>(n);
      loc180 = L45(R45(loc));
      //270
      ArrayList<Integer> loc270 = new ArrayList<Integer>(n);
      loc270 = Verti(R45(loc));
      

      Set<int[]>map = new HashSet<>();
 
  }
  public static ArrayList<Integer>  R45(int[] loc){
    ArrayList<Integer> flip = new ArrayList<Integer>(loc.length);
    for(int i=0;i<loc.length;i++){
        flip.set(loc[i],i);
      }
    return flip;
  }
  public static ArrayList<Integer>  L45(ArrayList<Integer> loc){
     ArrayList<Integer> flip = new ArrayList<Integer>(loc.size());
    for(int i=0;i<loc.size();i++){
        flip.set(loc.size()-loc.get(i), loc.size() - i);
      }
    return flip;
  }
  public static ArrayList<Integer> Hori(ArrayList<Integer> loc){
     ArrayList<Integer> flip = new ArrayList<Integer>(loc.size());
    for(int i=0;i<loc.size();i++){
        flip.set(loc.size() - i - 1, loc.get(i));
      }
    return flip;
  }
  public static ArrayList<Integer> Verti(ArrayList<Integer> loc){
     ArrayList<Integer> flip = new ArrayList<Integer>(loc.size());
    for(int i=0;i<loc.size();i++){
        flip.set(i, loc.size() - loc.get(i) - 1);
      }
    return flip;
  }
  
}