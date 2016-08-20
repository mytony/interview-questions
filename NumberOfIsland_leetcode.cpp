#include <iostream>
#include <unordered_set>
#include <unordered_map>
#include <vector>
#include <queue>
#include <set>
#include <map>
#include <stack>
#include <cmath>
#include <numeric>

using namespace std;

// Number of Island
class Island {
public:
	int numIslands(vector<vector<char>>& grid) {
		int rows = grid.size();
		int cols = grid[0].size();
		// answer of number of islands
		int res = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (grid[row][col] == '1') {
					// DFS(grid, row, col);
					BFS(grid, row, col);
					res++;
				}
			}
		}
		return res;
	}

private:
	void visit(vector<vector<char>>& grid, int x, int y, queue<int> &q) {
		if (x < 0 || y < 0 || x >= grid.size() || y >= grid[0].size() || grid[x][y] != '1')
			return;
		grid[x][y] = '0';
		q.push(x * grid[0].size() + y);
	}
	// BFS
	void BFS(vector<vector<char>>& grid, int x, int y) {
		grid[x][y] = '0';

		int cols = grid[0].size();
		queue<int> q;
		q.push(x*cols+y);

 		while (!q.empty()) {
 			int num = q.front();
 			q.pop();
 			int row = num/cols;
 			int col = num%cols;
 			visit(grid, row+1, col, q);
 			visit(grid, row, col+1, q);
 			visit(grid, row-1, col, q);
 			visit(grid, row, col-1, q);
 		}
	}
	// DFS 
	void DFS(vector<vector<char>>& grid, int x, int y) {
		grid[x][y] = '0';
		if (x+1 < grid.size() && grid[x+1][y] == '1')
			DFS(grid, x+1, y);

		if (y+1 < grid[0].size() && grid[x][y+1] == '1')
			DFS(grid, x, y+1);

		if (x-1 >= 0 && grid[x-1][y] == '1')
			DFS(grid, x-1, y);

		if (y-1 >= 0 && grid[x][y-1] == '1')
			DFS(grid, x, y-1);
	}	
};
