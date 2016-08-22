// Meeting room followup 每個meeting有權重weight, 現在只有一個room找出最大的weight和
// 演算法課教過，講義是 CSCI570_Lecture_06_WK7_Dynamic_Programming_022515.pdf
// 其他網頁：
// 做法跟我們的一模一樣   http://www.geeksforgeeks.org/weighted-job-scheduling/
// 裡面也提到如果用linear search去找p[i]這樣總共會是O(n^2)，
// 但如果改成binary search可以進步到O(nlogn)

#include <iostream>
#include <vector>
using namespace std;

struct Interval {
	int start;
	int end;
	int weight;
};

static bool compareByEndTime(Interval &in1, Interval &in2) {
	return in1.end < in2.end;
}

int maxMeetingRoomWeights(vector<Interval>& intervals) {
	if (intervals.empty()) return 0;
	int n = intervals.size();
	
	// sort by end time
	sort(intervals.begin(), intervals.end(), compareByEndTime);
	
	// construct p[j] is the largest index i < j such that interval i & j are disjoint
	// -1 represents as none is compatible
	vector<int> p(n, -1);
	for (int i = n-1; i >= 0; i--) {
		for (int j = i-1; j >= 0; j--) {
			if (intervals[j].end <= intervals[i].start) {
				p[i] = j;
				break;
			}
		}
	}
	
	// opt[i] is the max weight sum of intervals 1~i
	vector<int> opt(n, 0);
	opt[0] = intervals[0].weight;
	for (int i = 1; i < n; i++) {
		// if choose i -> add its weight and opt(p(i))
		int weight = (p[i] != -1) ? intervals[i].weight+opt[p[i]] : intervals[i].weight;
		opt[i] = max(opt[i-1], weight);
	}
	for (int i = 0; i < n; i++) {
		cout << opt[i] << ' ';
	}
	cout << endl;
	return opt[n-1];
}

////////////////////
// 測試別人的寫法正不正確，他是照開始時間排序，O(i) is 1~i
// http://www.1point3acres.com/bbs/forum.php?mod=redirect&goto=findpost&ptid=172729&pid=2250708
static bool compareByStartTime(Interval &in1, Interval &in2) {
	return in1.start < in2.start;
}

// 我懷疑這不算是DP，因為他是用一個變數去紀錄曾出現的最大值
// 而且是往回配所有可能的dp，當下dp[i]也只考慮i加進去的情況
int test(vector<Interval>& intervals) {
	if (intervals.empty()) return 0;
	int n = intervals.size();
	
	// sort by start time
	sort(intervals.begin(), intervals.end(), compareByStartTime);

	vector<int> dp(n, 0);
	dp[0] = intervals[0].weight;
	int max = dp[0];
	for (int i = 1; i < n; i++) {
		dp[i] = intervals[i].weight; // 這行大概是要當前面j沒人匹配時，這樣算只拿j自己
		for (int j = 0; j < i; j++) {
			if (intervals[j].end <= intervals[i].start) {
				dp[i] = std::max(dp[i], dp[j] + intervals[i].weight);
			}
		}
		max = std::max(dp[i], max);
	}

	for (int i = 0; i < n; i++) {
		cout << dp[i] << ' ';
	}
	cout << endl;
	return max;
}

int main(int argc, char *argv[])
{
	Interval in1, in2, in3, in4, in5, in6, in7;
	in1.start = 1;
	in1.end = 5;
	in1.weight = 2;
	in2.start = 2;
	in2.end = 7;
	in2.weight = 5;
	in3.start = 4;
	in3.end = 8;
	in3.weight = 6;
	in4.start = 1;
	in4.end = 9;
	in4.weight = 8;
	in5.start = 3;
	in5.end = 10;
	in5.weight = 7;
	in6.start = 6;
	in6.end = 11;
	in6.weight = 3;
	in7.start = 8;
	in7.end = 12;
	in7.weight = 1;
	vector<Interval> ins({in1, in2, in3, in4, in5, in6, in7});

	cout << maxMeetingRoomWeights(ins) << endl;
	cout << test(ins) << endl;
	return 0;
}
