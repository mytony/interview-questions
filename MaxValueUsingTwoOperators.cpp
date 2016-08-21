/*
然后他就另外出了一个找最大值的题。给一个数组[1,1,2,1]，
然后用+ * （）三个操作求出这个数组的最大值，这个题返回6。 很简单，DP解决。
我开始写了个用res[i][j] 表示的solution，然后写完代码，测了几个用例，都过了。
然后他问如果数组里面有负数和0咋办呢。 我说维护两个表max[i][j] 和 min[i][j]。
然后他很满意。我说其实还有复杂度更低的方法，可以用一位数组做。
他说不用了，这个方法已经够好了，不要求写那么复杂。

http://www.1point3acres.com/bbs/thread-129739-1-1.html

第四轮， 一堆数，中间插入*，+或者括号，求最大值，dp就可以， 
但是如果数字全部是正整数的话，只用通过判断1的个数是奇数还是偶数来求最大值，
0(n)就可以了
merge n个排序过的数组，优先队列或者divide and merge都可以
简单的regax判断是否match

http://www.lostscroll.com/max-value-using-and/
http://stackoverflow.com/questions/3781962/building-an-expression-with-maximum-value
*/
#include <iostream>
#include <vector>
using namespace std;

int maxValue(vector<int> nums) {
	int n = nums.size();
	// dp(i,j) is the max expression value of numbers from i to j
	vector<vector<int> > dp(n, vector<int>(n, 0));

	// build i = j base cases 
	for (int i = 0; i < n; i++) {
		dp[i][i] = nums[i];
	}

	// build other cases
	// O(i,j) = MAX( O(i,k) {+|*} O(k+1,j) ) for k in [i,j)
	for (int d = 1; d < n; d++) { // d = j - i, 從差1做到差n-1
		for (int i = 0; d + i < n; i++) { // i
			int maxVal = INT_MIN, j = i + d;
			for (int k = i; k < j; k++) { // O(i,k) op O(k+1,j)
				maxVal = max(maxVal, dp[i][k]*dp[k+1][j]);
				maxVal = max(maxVal, dp[i][k]+dp[k+1][j]);
			}
			dp[i][j] = maxVal;
		}
	}

	return dp[0][n-1];
}

int main(){
	cout << maxValue({1,2,1,1}) << endl;
	cout << maxValue({2,5,1,2,1,2,1,1}) << endl;
	cout << maxValue({1,2,3,4,5}) << endl;

	return 0;
}
