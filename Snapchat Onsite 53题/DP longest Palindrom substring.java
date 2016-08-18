public class Solution {
    public static void main(String[] args){
        String s = longestPalindrome("abxyyxc");
        System.out.println(s);
    }
    public static  String longestPalindrome(String s) {
         int n = s.length();
     boolean [][] dp = new boolean[n][n];
     String result="";
     for(int l = 0; l < n; l++){
         for(int start =0;start<n-l;start++){
             int end = start+l;
             dp[start][end]=(end-start<3||dp[start+1][end-1])&&s.charAt(start)==s.charAt(end);
             if(dp[start][end] && (result==null||result.length()<end-start+1))
                result = s.substring(start,end+1);
         }
     }
     return result;
}
}