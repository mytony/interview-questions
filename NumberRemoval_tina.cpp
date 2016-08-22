// number removal
/* 给一个数组，元素是0-9的int，remove掉其中n个数，使剩下的数在原顺序的情况下最小。
e.g. 7129，去掉两个数字，最小为12。 57294，去掉两个数字，剩下的最小为294。复杂度 O(length + n) 
*/
int minValueByNumberRemoval(vector<int> nums, int n) {
	// 把最小的放最前面，所以從最前面看步數(2)+1個數找最小放到最前面eg. 7129, 712內1最小所以把1前面都刪掉
	// 刪除n個
	if (n > nums.size()) return 0;
	int steps = n;
	int res = 0;
	int min = INT_MAX;
	int i = 0;

	while (steps != 0) {
		min = *min_element(nums.begin()+i, nums.begin()+i+steps+1);
		//if (min == nums[i]) // skip
		if (i == nums.size()-steps) {
			// last one delete
			nums.erase(nums.begin()+i, nums.begin()+i+steps);
			break;
		} 
		else {
			// delete nums before min
			for (int j = i; nums[j] != min; j++) {
				if (nums[j] == min) break;
				else {
					// cout << "erase:" << nums[j] << endl;
					nums.erase(nums.begin()+j);
					steps--;
					j = i-1;
				}
			}
		}
		i++;
	}

	for (int num : nums) {
		res = res*10 + num;
	}
	return res;
}
