// Leetcode 253. Meeting Room II
// Given an array of meeting time intervals consisting of start and end times [[s1,e1], [s2,e2],...] (si < ei), 
// find the minimum number of conference rooms required.

struct Interval {
	int start;
	int end;
};
static bool CompareByStartTime(Interval &in1, Interval &in2) {
	return in1.start < in2.start;
}
int minMeetingRooms(vector<Interval>& intervals) {
	if (intervals.size() <= 1) return intervals.size();
	// sort by start time
	sort(intervals.begin(), intervals.end(), CompareByStartTime);
	// min heap to store overlap(required) rooms end time
	priority_queue<int, vector<int>, greater<int> > heap;
	heap.push(intervals[0].end);
	for (int i = 1; i < intervals.size(); i++) {
		int end = heap.top();
		if (intervals[i].start >= end) { // not overlap
			heap.pop();
			heap.push(intervals[i].end);
		}
		else
			heap.push(intervals[i].end); // overlap
	}
	return heap.size();
}
