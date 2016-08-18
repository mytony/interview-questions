class Solution {
  public static void main(String[] args) {
        int res = longestPalindrome("aaeaaac");
        System.out.println(res);
    }
  
 public static int longestPalindrome(String s) {
     int n = s.length();
     int [][] dp = new int[n][n];
     String result=null;
    //  初始化 
     for(int i =0;i<n-1;i++)
        dp[i][i+1] = s.charAt(i)==s.charAt(i+1)?0:1;
     
   for(int l = 2; l < n; l++){
         for(int i =0;i<n-l;i++){
             int j = i+l;
             
             if (s.charAt(i)==s.charAt(j))
                    dp[i][j] = dp[i+1][j-1];
             else
                {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;//删除或插入
                    dp[i][j] = Math.min(dp[i][j], dp[i+1][j-1] + 1);//替换
                }
         }
     }
     return dp[0][n-1];
     
}
}