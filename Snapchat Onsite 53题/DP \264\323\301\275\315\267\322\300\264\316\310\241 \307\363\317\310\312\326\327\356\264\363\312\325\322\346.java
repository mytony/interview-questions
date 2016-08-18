// DP
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */

    public static void main(String[] args) {

      int res = firstWillWin(new int[]{1,2,3,1,1,1}, true);
      System.out.println(res);
    }

   public static int firstWillWin(int[] values, boolean select) {
        // write your code here
        int n = values.length;
        int [][]dp = new int[n ][n];
        boolean [][]hash =new boolean[n ][n ];
  
        return select ? Optimal(0,values.length - 1, dp, hash, values) : Greedy(0,values.length - 1, dp, hash, values);
    }
    public static int Optimal(int left, int right, int [][]dp, boolean [][]hash, int []values) {
        
        if(hash[left][right])   
            return dp[left][right];
        
        hash[left][right] = true;
              
        if(left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if(left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int  pick_left = Math.min(Optimal(left + 2, right, dp, hash, values), Optimal(left + 1, right - 1, dp, hash, values)) + values[left];
            int  pick_right = Math.min(Optimal(left, right - 2, dp, hash, values), Optimal(left + 1, right - 1, dp, hash, values)) + values[right];
            dp[left][right] = Math.max(pick_left, pick_right);    
        }
        
        return dp[left][right];   
    }

        public static int Greedy(int left, int right, int [][]dp, boolean [][]hash, int []values) {
        
        if(hash[left][right])   
            return dp[left][right];   
        hash[left][right] = true;   
        if(left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if(left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int  pick_left = (values[left+1]>values[right]? Greedy(left + 2, right, dp, hash, values) : Greedy(left + 1, right - 1, dp, hash, values)) + values[left];
            int  pick_right =(values[left]>values[right-1]? Greedy(left + 1, right - 1, dp, hash, values): Greedy(left, right - 2, dp, hash, values)) + values[right];
            dp[left][right] = Math.max(pick_left, pick_right);    
        }
        
        return dp[left][right];   
    }
}
