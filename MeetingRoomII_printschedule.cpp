// Meeting room II變形
// 印出schedule
struct Interval {
	int start;
	int end;
};
static bool compareByStartTime(Interval &in1, Interval &in2) {
	return in1.start < in2.start;
}
struct compare {
	bool operator()(const pair<int, int> p1, const pair<int, int> p2) {
		return p1.second > p2.second;
	}
};
void printMeetingRooms(vector<Interval>& intervals) {
	if (intervals.empty()) return;
	// sort by start time
	sort(intervals.begin(), intervals.end(), compareByStartTime);
	// min heap to store (room# and end time)
	priority_queue<pair<int, int>, vector<pair<int, int>>, compare> minHeap;
	minHeap.emplace(0, intervals[0].end);
	// result
	vector<vector<Interval>> res;
	res.push_back({intervals[0]});
	for (int i = 1; i < intervals.size(); i++) {
		int room = minHeap.top().first;
		int end = minHeap.top().second;
		if (intervals[i].start > end) { // not overlap, use the same room
			minHeap.pop();
			minHeap.emplace(room, intervals[i].end);
			res[room].push_back(intervals[i]);
		}
		else { // overlap, open a new room
			int newRoom = res.size();
			minHeap.emplace(newRoom, intervals[i].end);
			res.push_back({intervals[i]});
			// cout << intervals[i].start << "-" << intervals[i].end << endl;
		}
	}
	// print out
	for (int i = 0; i < res.size(); i++) {
		cout << "Room " << i+1 << ":";
		for (auto in : res[i]) {
			cout << "[" << in.start << "," << in.end << "] "; 
		}
		cout << endl;
	}
}
