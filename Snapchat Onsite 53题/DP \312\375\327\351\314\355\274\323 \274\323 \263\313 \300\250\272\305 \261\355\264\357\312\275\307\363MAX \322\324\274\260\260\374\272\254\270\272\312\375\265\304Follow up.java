public class MaxValueAddingOperator {
    public int solve(int[] nums){
        int n = nums.length;
        int[] sum = new int[n + 1];
        // dp[i][j] 表示 前i 个数字，分成j份相乘，也就是说有j组括号
        int[][] dp = new int[n + 1][n + 1]; 
        // 初始化累加数组，还有不用乘号的情况
        
        for(int idx = 1; idx <= n; idx++){
            sum[idx] = sum[idx - 1] + nums[idx - 1];
            dp[idx][1] = sum[idx];
        }

        int max = dp[n][1];
        // O(n^3)
        // 对于总长为i的数字序列
        for(int i = 2; i <= n; i++){
            // 分成j组相乘
            for(int j = 2; j <= i; j++){
                // 从第k个数开始分成新的一份，也就是说乘上从第k到第i的和
                for(int k = j; k <= i; k++){
                    dp[i][j] = Math.max(dp[i][j],
                    	dp[k - 1][j - 1] * (sum[i] - sum[k-1]));
                }
                if(i == n && dp[n][j] > max){
                    max = dp[n][j];
                }
            }
        }
        return max;
    }

    public int solve2(int[] nums){
    int n = nums.length;
    int[] sum = new int[n + 1];
    int[][] dpMax = new int[n + 1][n + 1];
    int[][] dpMin = new int[n + 1][n + 1];

    // 初始化累加表
    for(int idx = 1; idx <= n; idx++){
        sum[idx] = sum[idx - 1] + nums[idx - 1];
        dpMax[idx][1] = sum[idx];
        dpMin[idx][1] = sum[idx];
    }
    int max = dpMax[n][1];

    for(int i = 2; i <= n; i++){
        for(int j = 2; j <= i; j++){
        	dpMax[i][j] = Integer.MIN_VALUE;
        	dpMin[i][j] = Integer.MAX_VALUE;
            for(int k = j; k <= i; k++){
                int partialSum = sum[i] - sum[k - 1];
                // 根据待会要乘的数的正负号，来判断我们乘的对象是最大表还是最小表

                dpMax[i][j] = Math.max(dpMax[i][j],Math.max(dpMax[k - 1][j-1] + partialSum,
                            (partialSum < 0 ?dpMin[k - 1][j - 1] : dpMax[k - 1][j - 1])* partialSum));
                dpMin[i][j] = Math.min(dpMin[i][j],Math.min(dpMin[k - 1][j-1] + partialSum,
                            (partialSum < 0 ?dpMax[k - 1][j - 1] : dpMin[k - 1][j - 1])* partialSum));
            }
            if(i == n && dpMax[n][j] > max){
                max = dpMax[n][j];
            }
        }
    }
    return max;
}

    
    public static void main(String[] args){
        MaxValueAddingOperator mvao = new MaxValueAddingOperator();
        int[] arr = {-9, -2, 6};
        int[] arr2 = {1, 1, 1, 1,1,1};
        System.out.println(mvao.solve2(arr));
        System.out.println(mvao.solve(arr2));
    }
}