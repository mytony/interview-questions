/*
题目叫做function log parsing，我不太确定有没有leetcode原题，我是没有见过。
大概意思是，有一个log file是一个单线程计算机产生的，记录了这个计算机里每个程序的起始和终止时间。
这个log file里每行包括三个信息：程序名字，start/end，timestamp。比如这样一个例子：
f1 start 1
f1 start 2
f2 start 4
f2 end  8
f1 end  16
f1 end  32
f3 start 64
f3 end  128
需要输出每个程序占CPU的时间段，比如上面例子对应的输出：
f1: [1,4] [8,32]
f2: [4,8]
f3: [64,128]
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>
using namespace std;

// Function to print the result
void print(vector <vector<string> > &res) {
	// Format: f2: [4,8]
	for (auto v: res) {
		cout << v[0] << ": [" + v[1] + "," + v[2] + "]\n";
	}
}


// Assume the log is time ordering
void parse(vector <vector<string> > &logs) {
	int numAns = 0; // how many outputs so far
	stack <pair<string, string> > stack; // name, timestamp
	vector <vector<string> > res;

	// for each log
	for (auto log: logs) {
		if (log[1] == "start") {
			// If there's a previous thread which is running, 
			// we replace it and output the thread so far
			if (!stack.empty()) {
				pair<string, string> prev = stack.top();

				// E.g.
				// f1 start 1
				// f1 start 2
				if (prev.first == log[0]) continue;
				
				res.push_back({prev.first, prev.second, log[2]});
				numAns++;
			}

			// Put the current thread
			stack.push(make_pair(log[0], log[2]));
		} else if (log[1] == "end") {
			if (stack.empty()) {
				// E.g.
				// f1 end  16
				// f1 end  32
				if (res[numAns-1][0] == log[0]) {
					res[numAns-1][2] = log[2];
					continue;
				}
				cerr << "Error: no start log is matched\n";
			}

			pair<string, string> prev = stack.top();
			stack.pop();

			if (prev.first != log[0]) {
				cerr << "Error: not the same thread when a end log appears\n";
			}

			// Successfully match the starting thread, output
			res.push_back({log[0], prev.second, log[2]});
			numAns++;

			// Modify the start time of previous different thread that was replaced
			if (stack.empty()) continue;
			prev = stack.top();
			stack.pop();
			prev.second = log[2];
			stack.push(prev);
		} else {
			cerr << "Error: the type is neither start nor end\n";
		}
	}

	print(res);
}

int main() {
	vector< vector<string> > logInfo(8, vector<string>(3, ""));
	logInfo[0][0] = "f1";
	logInfo[0][1] = "start";
	logInfo[0][2] = "1";
	logInfo[1][0] = "f1";
	logInfo[1][1] = "start";
	logInfo[1][2] = "2";
	logInfo[2][0] = "f2";
	logInfo[2][1] = "start";
	logInfo[2][2] = "4";
	logInfo[3][0] = "f2";
	logInfo[3][1] = "end";
	logInfo[3][2] = "8";
	// f1 end  16
	logInfo[4][0] = "f1";
	logInfo[4][1] = "end";
	logInfo[4][2] = "16";
	logInfo[5][0] = "f1";
	logInfo[5][1] = "end";
	logInfo[5][2] = "32";
	// // f3 start 64
	logInfo[6][0] = "f3";
	logInfo[6][1] = "start";
	logInfo[6][2] = "64";
	logInfo[7][0] = "f3";
	logInfo[7][1] = "end";
	logInfo[7][2] = "128";
	
	parse(logInfo);
	return 0;
}