/*
#snapchat 题目是设计一个类来存放一个无向图的边和点，我大概用的hashset来存一个点的邻居这样，follow up是问图有多满的情况下用bitmap更加合适。要写main函数和简单testcase跑一下。我是一开始有点编译错误但是编译通过之后结果是对的。感觉只要是能跑通应该就没什么问题。面试小哥是camera组的。
*/

#include <algorithm>
#include <string>
#include <vector>
#include <iostream>
#include <unordered_map>
#include <unordered_set>
using namespace std;

class MyGraph
{
	// Translation table between name and serial number
	unordered_map<string, int> nameMap;
	unordered_map<int, string> numMap;

	// Next index
	int index = 0;

	// Edges
	vector<unordered_set<int> > edges;

public:
	void addVertex(string v);
	void addEdge(string v1, string v2);
	void printEdge();
};

void MyGraph::addVertex(string v) {
	if (nameMap.find(v) == nameMap.end()) {
		nameMap[v] = index;
		numMap[index] = v;
		index++;
		edges.push_back(unordered_set<int>());
	}
}

void MyGraph::addEdge(string v1, string v2) {
	addVertex(v1);
	addVertex(v2);
	int n1 = nameMap[v1];
	int n2 = nameMap[v2];

	// only store edge with the smaller value vertex to save space
	int head = min(n1, n2);
	int value = (head == n1) ? n2 : n1;
	edges[head].insert(value);
}

// Print all edges
void MyGraph::printEdge() {
	for (int i = 0; i < edges.size(); i++) {
		for (auto value: edges[i]) {
			cout << numMap[i] << ',' << numMap[value] << endl;
		}
	}
}

int main() {
	MyGraph g;
	g.addEdge("a", "b");
	g.addEdge("b", "c");
	g.addEdge("a", "d");
	g.printEdge();
	return 0;
}