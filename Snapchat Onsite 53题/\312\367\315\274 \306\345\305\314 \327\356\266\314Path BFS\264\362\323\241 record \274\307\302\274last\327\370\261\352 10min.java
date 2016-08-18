import java.util.*;
public class Solution {
  
    public static void main(String[] args) {
      int[][]board = {{0,1,1,1},{0,0,0,0},{1,1,1,0},{0,0,0,0}};
      shortest(board);
    }
    // Implement regular expression matching with support for '.' and '*'.
    public static void shortest(int[][]board) {
      int m = board.length;
      int n = board[0].length;
      Map<Integer,Integer> record = new HashMap<>();
      record.put(0,0);
      
      Queue<Integer> q = new LinkedList<>();

      q.offer(0);
      while(!q.isEmpty()){
        int pos = q.poll();
        int x = pos/m;
        int y = pos%m;
        if(x==m-1&&y==n-1) break;
        int[]dx = {0,0,1,-1};
        int[]dy = {1,-1,0,0};
        
        for(int i =0; i<4;i++){
          int nx = dx[i]+x;
          int ny = dy[i]+y;
          if(nx>=0&&nx<m && ny>=0&&ny<n && board[nx][ny]==0){
            int next = nx*n+ny;
            if(!record.containsKey(next)){
              q.offer(next);
              record.put(next,pos);
            }
          }  
        }
       
    }
      int last=m*n-1;
      List<String>res = new ArrayList<>();
      
      while(last!=0){
          int x = last/m;
          int y = last%n;
          res.add("->"+"["+x+","+y+"]");
          // last = record[last];
          last = record.get(last);
      }
      
      Collections.reverse(res);
      System.out.println(res);
}
}