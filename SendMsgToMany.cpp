/*
给我看了一个snapchat给好友群发消息的功能，可以任意选中和删除想要投递消息的好友，
并显示群发好友的list(按先后选中的顺序)，设计一个数据结构，实现 toggle(String username);
getList(); LZ给的hashmap + doubly linked list的设计，类比LRU cache

How to implement doubly linked list in C++?
http://stackoverflow.com/questions/19484515/doubly-linked-list-implementation-with-pointers-c
*/

#include <iostream>
#include <vector>
#include <cmath>
#include <numeric>
#include <unordered_map>
using namespace std;

struct User {
	string name;
	User *prev;
	User *next;
};

// Doubly linked list
class LinkedList {
public:
	LinkedList() {
		head = tail = NULL;
	}

	// Add new element at the back and return its pointer
	User* addAtBack(string element) {
		User *node = new User();
		node->name = element;

		if (head) {
			tail->next = node;
			node->prev = tail;
			tail = tail->next;
		} else {
			head = tail = node;
		}

		return node;
	}

	// Remove an element by its pointer
	void remove(User* node) {
		if (head == node && tail == node) {
			head = tail = NULL;
		} else if (head == node) {
			head = node->next;
			head->prev = NULL;
		} else if (tail == node) {
			tail = node->prev;
			tail->next = NULL;
		} else {
			node->prev->next = node->next;
		}

		delete node;
	}

	// Print the list
	void print() {
		User* run = head;
		while (run) {
			cout << run->name << " ";
			run = run->next;
		}
		cout << endl;
	}
private:
	User *head;
	User *tail;
};

class ChooseUser {
	/*
	I use a doubly linked list to record the choosen users and the order
	also use a map to store a user and its node pointer if user is choosen
	*/
public:
	void toggle(string username) {
		cout << "Toggle " + username << endl;
		if (map.find(username) == map.end()) {
			map[username] = ll.addAtBack(username);
		} else {
			ll.remove(map[username]);
			map.erase(username);
		}
	}

	void getList() {
		cout << "Print the list: ";
		ll.print();
	}
private:
	LinkedList ll;
	unordered_map<string, User*> map;
};

int main() {
	ChooseUser obj;
	obj.toggle("Apple");
	obj.toggle("Ball");
	obj.getList();
	obj.toggle("Apple");
	obj.toggle("Apple");
	obj.getList();
	return 0;
}