/*
Have to compiled by c++11: g++ -std=c++11

第二面，一个中年白人manager。一上来先问了问why snapchat, what's your favoriate parts of internshop in Facebook。之后做题，给定一些输入如

		Employee,Manager,ItemsSold
		Alice,,5
		Bob,Alice,3
		Carol,Bob,2
		David,Bob,3
		Eve,Alice,2
		Ferris,Eve,1
		要求打印出这个样子
		Alice 16
		    Bob 8
		        Carol 2
		        David 3
		    Eve 3
		        Ferris 1
楼主先自己设计数据结构，和第一面那个基本一样，只是多了一个int num来记录数量，根据输入构建树，注意这里每条记录给定的顺序是随机的，所以可能先出来David,Bob,3然后才是Bob,Alice,3。不注意的话可能会有小bug报错。然后postorder算出所有node的child的数量和，然后update自己的，之后preorder打印。写的时候有个小bug，改正之后通过。follow up要求打印成这个样子

		Alice 16
		|-Bob 8
		| |-Carol 2
		| \_David 3
		\_Eve 3
		  \_Ferris 1
		  
然后楼主就跪在这里了，怎么改都是有bug，最后也没改出来。最后面试官说，你的大方向是对的，只是你API设计的不够好，你如果把parent需要打印的prefix也传递给child，打印起来就非常方便了。楼主终于醒悟，刷题太多导致思维僵固了，另外这一面全程要coding并且编译运行。
*/

#include <iostream>
#include <vector>
using namespace std;
#include <unordered_map>

struct Person {
	string name;
	int itemsSold;
	vector<Person*> subordinates;

	Person(string name) {
		this->name = name;
		itemsSold = 0;
	}
};

int dfs(Person *root) {
	int sum = root->itemsSold;
	for (Person *e: root->subordinates) {
		sum += dfs(e);
	}
	root->itemsSold = sum;
	return sum;
}

void print(Person *root, int level) {
	if (level != -1) {
		for (int i = 0; i < 4 * level; i++) { cout << ' '; }
		cout << root->name << " " << root->itemsSold << endl;
	}
	for (Person* e: root->subordinates) {
		print(e, level + 1);
	}
}

// This type of printing assumes there is only one top manager
void print2(Person *root, int level, string prefix) {
	/*
		Alice 16
		|-Bob 8
		| |-Carol 2
		| \_David 3
		\_Eve 3
		  \_Ferris 1
	*/

	// Print
	cout << prefix;
	cout << root->name << " " << root->itemsSold << endl;

	// Go deeper
	int size = root->subordinates.size();
	string newPrefix = prefix;
	int len = prefix.size();
	if (len != 0) {
		if (prefix[len-1] == '-') {
			newPrefix[len-1] = ' ';
		} else if (prefix[len-1] == '_') {
			newPrefix[len-1] = ' ';
			newPrefix[len-2] = ' ';
		}
		
	}

	for (int i = 0; i < size; i++) {
		if (i == size - 1) {
			print2(root->subordinates[i], level+1, newPrefix + "\\_");
		} else {
			print2(root->subordinates[i], level+1, newPrefix + "|-");
		}
	}
}

// Input format: Employee,Manager,ItemsSold
void process(vector<vector<string> >& input) {
	Person root("root");
	unordered_map<string, Person*> employees;

	// Read input and build the tree
	for (auto employee: input) {
		string emp = employee[0], mgr = employee[1], items = employee[2];
		Person *person;
		if (employees.find(emp) == employees.end()) {
			person = new Person(emp);
			employees[emp] = person;
		} else { // his/her employee appears before, so the name already in DB
			person = employees[emp];
		}
		person->itemsSold = stoi(items);
		

		// Check whether the manager appear before or not
		if (mgr.empty()) { // no manager above the employee
			root.subordinates.push_back(person);
		} else {
			if (employees.find(mgr) == employees.end()) {
				Person *manager = new Person(mgr);
				manager->subordinates.push_back(person);
				employees[mgr] = manager;
			} else {
				employees[mgr]->subordinates.push_back(person);
			}
		}
	}

	// Go through the entire tree by DFS and update the number of items sold
	dfs(&root);

	// Draw the graph
	// print(&root, 0);
	print2(root.subordinates[0], 0, ""); // assume only one top manager
}

int main() {
	vector<vector<string> > input;
	input.push_back({"Bob","Alice","3"});
	input.push_back({"Eve","Alice","2"});
	input.push_back({"Alice","","5"});
	input.push_back({"Carol","Bob","2"});
	input.push_back({"David","Bob","3"});
	input.push_back({"Ferris","Eve","1"});
	process(input);
	return 0;
}
