/*
34. 给一个int的matrix，里面只有0和1，matrix表示i和j是朋友，如果是0表示两人不是朋友，
是朋友的分成一个组，问能分几组。比如1和2是朋友，3和他们都不是朋友，那么就是2组，return 2就可以。自己写test自己测

有點找 all spanning trees 的感覺
*/

#include <iostream>
#include <vector>
#include <stack>
using namespace std;

int group(vector< vector<int> >& friends) {
	int n = friends.size();
	int numGroup = 0;
	vector<int> visit(n, 0); // initially all non-visited(0)

	// Run through each person, then using DFS to reach his
	// whole network
	for (int i = 0; i < n; i++) {
		if (visit[i] == 0) {
			stack<int> conns;
			conns.push(i);
			numGroup++;

			while (!conns.empty()) {
				int person = conns.top(); conns.pop();
				visit[person] = 1;
				// push all friends of 'person' to check later list
				for (int j = 0; j < n; j++) {
					if (visit[j] == 0 && friends[person][j] == 1) {
						conns.push(j);
					}
				}
			}
		}
	}
	return numGroup;
}

int main() {
	vector<vector<int> > grid = {{0,1,0}, {1,0,0}, {0,0,0}};
	cout << group(grid) << endl;
	return 0;
}