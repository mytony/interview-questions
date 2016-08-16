#include<iostream>
#include<map>
using namespace std;

class xmlTreeNode {
public:
	//string tag; // {open, text, close}
	string name;// ex. <a>-> a
	string value; // foo?
	vector<xmlTreeNode*> children;
	xmlTreeNode(string t) {
		// tag = t;
	}
};
struct Token { 
	string name; // e.g. story, id, snaps 
	string tag; // {open, close, text} 三种type 
	token getNext() {
		return NULL;
	}
}; 

class xmlParser {
public:
	/*
	LZ的方法是用stack来存parent, 遇到open和text建新node, 并把新node加到stack顶部node的child list里面，
	如果是open就把新node压栈，遇到close就pop掉stack顶端的node 写完以后小哥的follow up是如果输入的token有问题，
	比如close tag和open tag不匹配，如何做verification
	*/
	xmlTreeNode* parser(Token tokenizer) {// 假設有tokenizer可以getNextToken()
		if (tokenizer.tag != "open") return NULL;// error!
		xmlTreeNode* root = new xmlTreeNode(tokenizer.name);
		stk.push(root);
		Token token = tokenizer.getNext();
		while (token) {
			if (token.tag == "open" || token.tag == "text") {
				xmlTreeNode* node = new xmlTreeNode(token.name);
				xmlTreeNode* parent = stk.top();
				parent->children.push_back(node);
				stk.push(node);
			}
			else if (token.tag == "close") {
				xmlTreeNode* topNode = stk.top();
				if (topNode.name == token.name) {
					stk.pop();
				}
				// else "Error! not match"
			}
			token = tokenizer.getNext();
		}
		return root;
	}
private:
	stack<xmlTreeNode*> stk;
};