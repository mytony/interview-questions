// Tina was practicing the interview question for Snapchat
// These are two of them
// 1. Given a board with value of 0 or 1, 0 is road that we can pass
//    1 is wall that we cannot pass. Find the shortest distance from
//    start point to end point.
// 2. It is follow-up of 1. Now the wall can be break with extra cost 1
//    Walk a step costs you cost 1. Find the minimum cost of path from
//    start to the end.
// These are links which are helpful:
// 	http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
// 	http://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-using-set-in-stl/
// Same questions:
// 	http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=197683
#include <vector>
#include <iostream>
#include <queue>
#include <functional>
#include <set>
using namespace std;

struct Point {
	int x, y;
	Point(int a, int b) {
		x = a;
		y = b;
	}
};

// find shortest distance of start to goal
int shortestDistance(vector<vector<int> > &maze, int x1, int y1, int x2, int y2) {
	queue<Point*> queue;
	queue.push(new Point(x1, y1));
	// maze[x1][y1] = 2; // marked as visited
	int dist = 0;
	int m = maze.size();
	int n = maze[0].size();

	while (!queue.empty()) {
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			Point *p = queue.front();
			queue.pop();
			int x = p->x;
			int y = p->y;
			delete(p);
			if (x == x2 && y == y2) return dist;
			// if (maze[x][y] != 0) continue;
			maze[x][y] = 2; // marked as visited
			// left
			if (y > 0 && maze[x][y-1] == 0) {
				queue.push(new Point(x, y - 1));
			}
			// right
			if (y + 1 < n && maze[x][y+1] == 0) {
				queue.push(new Point(x, y + 1));
			}
			// up
			if (x > 0 && maze[x-1][y] == 0) {
				queue.push(new Point(x - 1, y));
			}
			// down
			if (x + 1 < m && maze[x+1][y] == 0) {
				queue.push(new Point(x + 1, y));
			}
		}
		dist++;
	}
	return -1;
}

// The walls can be breaked with additional cost 1
// Walk one step with cost 1
int minCostWithBreakingWalls(vector<vector<int> > &maze, int x1, int y1, int x2, int y2) {
	int m = maze.size();
	int n = maze[0].size();

	// Store the distances between vertices in sptSet and 
	// ones not in the sptSet
	// We do not use priority queue to implement because the std 
	// priority queue do not support the operations we need for Dijkstra
	// such as decrease key and delete.
	set<pair<int, Point*> > setds;

	// Smallest costs from start point to every points
	vector<vector<int> > cost (m, vector<int> (n, INT_MAX));

	cost[x1][y1] = 0;
	setds.insert(make_pair(0, new Point(x1, y1)));

	while (!setds.empty()) {
		// Extract the minimum distance of vertex
		pair<int, Point*> tmp = *(setds.begin());
		setds.erase(setds.begin());

		int x = tmp.second->x;
		int y = tmp.second->y;

		// four directions that x, y will move: up, down, left, right
		int x_move[4] = {-1, 1, 0, 0};
		int y_move[4] = {0, 0, -1, 1};

		for (int i = 0; i < 4; i++) {
			int nx = x + x_move[i];
			int ny = y + y_move[i];

			if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
				continue;
			}

			// If there is a shorter path
			int c = (maze[nx][ny] == 1) ? 2 : 1;
			if (cost[nx][ny] > cost[x][y] + c) {
				// Means it is in our set, 
				// so we need to remove and update
				if (cost[nx][ny] != INT_MAX) {
					setds.erase(setds.find(make_pair(cost[nx][ny], new Point(nx, ny))));
				}

				// Update the cost
				cost[nx][ny] = cost[x][y] + c;
				setds.insert(make_pair(cost[nx][ny], new Point(nx, ny)));
			}
		}
	}

	return cost[x2][y2];
}

int main() {
	vector<vector<int> > maze (3, vector<int>(3, 0));
	maze[0][1] = 1;
	maze[2][2] = 1;
	// int ans = shortestDistance(maze, 0, 0, 2, 2);
	int ans = minCostWithBreakingWalls(maze, 0, 0, 1, 1);
	cout << ans << endl;
	return 0;
}
