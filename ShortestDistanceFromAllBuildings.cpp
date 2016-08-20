// 317.	Shortest Distance from All Buildings
// http://www.cnblogs.com/grandyang/p/5297683.html
// 用BFS從各個building建立dist, sum統計全部的, 再用res去找minimum
int shortestDistance(vector<vector<int> >& grid) {
	vector<vector<int> > sum = grid;
	vector<vector<int> > dirs{ {1,0},{-1,0},{0,1},{0,-1} }; // four directions
	int m = grid.size(), n = grid[0].size();
	int currVal = 0; // this is for visited cell, every time -1, from 0,-1,-2...
	int res = INT_MAX;

	// iterate the grid
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			// it's a building
			if (grid[i][j] == 1) {
				// cout << i << "," << j << endl;

				res = INT_MAX;
				queue<pair<int, int> > q;
				q.push({i, j});
				// distance
				vector<vector<int> > dist = grid;
				
				while (!q.empty()) {
					pair<int, int> pos = q.front();
					q.pop();
					// search four directions
					for (int dir = 0; dir < dirs.size(); dir++) {
						int x = pos.first + dirs[dir][0];
						int y = pos.second + dirs[dir][1];

						if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == currVal) {
							--grid[x][y]; // as visited
							dist[x][y] = dist[pos.first][pos.second] + 1;
							sum[x][y] += dist[x][y] - 1; // -1 is because the building in grid is marked as 1
							q.push({x, y});
							res = min(res, sum[x][y]);
						}
					}
				}
				--currVal; // visited times(decreasing)
			}
		}
	}
	return res == INT_MAX ? -1:res;
}
