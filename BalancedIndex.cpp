/*
给一个数组，返回左边之和等于右边之和的那个index， 没有就返回-1，
比如{1,2,3,2,1}， 返回2，写了好多testcase，各种情况。。。。10分钟完事
*/

#include <iostream>
#include <vector>
using namespace std;

int balancedIndex(const vector<int> &nums) {
	int n = nums.size();
	if (n == 0) return -1;
	
	vector<int> sumFromLeft(n, 0);
	if (n > 0) {
		sumFromLeft[0] = nums[0];
	}

	for (int i = 1; i < n; i++) {
		sumFromLeft[i] = nums[i] + sumFromLeft[i-1];
	}

	int idx = n - 1;
	for (; idx >= 0; idx--) {
		if (sumFromLeft[idx] - nums[idx] == 
			sumFromLeft[n-1] - sumFromLeft[idx]) {
			return idx;
		}
	}
	return -1;
}

int main() {
	vector<int> nums = {1,2,3,2,1};
	cout << balancedIndex(nums) << endl;
	return 0;
}