/*
DP question
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=187470
题目：A跟B在play game，从int[]里拿数字
A：拿第一个/最后一个，都有可能
B：greedy，总拿第一个/最后一个中最大的
A先开始，然后B，轮流，直到拿完
求A拿到的最大

举个例子：
{3, 7, 2, 1}
A: {3, 1} -- 4
B: {7, 2} -- 9. visit 1point3acres.com for more.

A: {1, 7} -- 8
B: {3, 2} -- 5
答案是8
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int play(vector<int> &nums) {
	int n = nums.size();
	vector<vector<int> > dp(n, vector<int>(n, 0));

	// base case 1: diagonal line
	for (int i = 0; i < n; i++) {
		dp[i][i] = nums[i];
	}
	// base case 2: index differ in 1
	for (int i = 1; i < n; i++) {
		dp[i-1][i] = max(nums[i-1], nums[i]);
	}

	for (int i = n - 3; i >= 0; i--) {
		for (int j = i + 2; j < n; j++) {
			int takeFirst = nums[i] + (nums[i+1] > nums[j] ? dp[i+2][j] : dp[i+1][j-1]);
			int takeSecond = nums[j] + (nums[i] > nums[j-1] ? dp[i+1][j-1] : dp[i][j-2]);
			dp[i][j] = max(takeFirst, takeSecond);
		}
	}
	return dp[0][n-1];
}

int main() {
	int myints[] = {3,7,2,1};
	vector<int> nums(myints, myints + sizeof(myints) / sizeof(int));
	cout << play(nums) << endl;
	return 0;
}