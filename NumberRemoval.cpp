// number removal
/* 
给一个数组，元素是0-9的int，remove掉其中n个数，使剩下的数在原顺序的情况下最小。
e.g. 7129，去掉两个数字，最小为12。 57294，去掉两个数字，剩下的最小为294。复杂度 O(length + n) 
*/
/* 
My idea is to keep selecting the smallest digit for the front
---這題我遇到的困難---
如果這題不是讓你回傳個數字，而是去修改原來的陣列，該怎麼刪了一些範圍後，
那些位置還可以繼續走？
再來如果為了加快刪除的速度改用list，要給erase()一個範圍時，iterator加上int會出錯
這樣我該怎麼指定範圍？

*/

#include <iostream>
#include <vector>
using namespace std;

int minValueByNumberRemoval(vector<int> &nums, int n) {
	int size = nums.size();
	if (n > size) { return -1; }
	if (n == size) { return 0; }

	// 把最小的放最前面，所以從最前面看步數(n)+1個數找最小放到最前面eg. 7129, 712內1最小所以把1前面都刪掉
	int pos = 0, res = 0;
	while (n > 0) {
		// When there are just n digits left, remove them all
		if (n == size - pos) {return res;}

		// find the index of the smallest digit in [pos,pos+n]
		// and remove the digits from pos to minIndex-1
		int minIndex = pos;
		for (int i = pos+1; i <= pos+n && i < size; i++) {
			if (nums[i] < nums[minIndex]) {
				minIndex = i;
			}
		}

		res = res * 10 + nums[minIndex];
		n -= (minIndex - pos);
		pos = minIndex + 1;
	}

	// We already remove n digits, now there are digits left
	for (int i = pos; i < size; i++) {
		res = res * 10 + nums[i];
	}

	return res;
}

int main() {
	vector<int> nums = {9,1,8,2,3,4};
	cout << minValueByNumberRemoval(nums, 2) << endl;
}
