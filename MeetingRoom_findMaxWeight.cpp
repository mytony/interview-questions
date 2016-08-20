// Meeting room followup 每個meeting有權重weight, 現在只有一個room找出最大的weight和
static bool compareByEndTime(Interval &in1, Interval &in2) {
	return in1.end < in2.end;
}
int maxMeetingRoomWeights(vector<Interval>& intervals) {
	if (intervals.empty()) return;
	int n = intervals.size();
	// sort by end time
	sort(intervals.begin(), intervals.end(), compareByEndTime);
	// construct p[i] is the first end time that is compatible with interval i
	vector<int> p(n, -1);
	for (int i = n-1; i >= 0; i--) {
		for (int j = i-1; j >= 0; j--) {
			if (intervals[j].end < intervals[i].start)
				p[i] = j;
		}
	}
	// opt[i] is the max weight sum of 1~i
	vector<int> opt(n, 0);
	opt[0] = intervals[0].weight;
	for (int i = 1; i < n; i++) {
		// if choose i -> add its weight and opt(p(i))
		int weight = (p[i] != -1) ? intervals[i].weight+opt[p[i]] : intervals[i].weight;
		opt[i] = max(opt[i-1], weight);
	}
	return opt[n-1];
}

int main(int argc, char *argv[])
{
  Interval in1, in2, in3;
	in1.start = 1;
	in1.end = 4;
	in2.start = 3;
	in2.end = 6;
	in3.start = 7;
	in3.end = 9;
	vector<Interval> ins({in1, in2, in3});
	// printMeetingRooms(ins);
}
