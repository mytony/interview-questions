#include <string>
#include <vector>
#include <iostream>
using namespace std;

class XMLNode {
    string name_;
    string value;
    vector<XMLNode*> children;
public:
    XMLNode(string name) {
        name_ = name;
    }
}

// Assume we are given a tokenizer. It's method getNext() returns a tag once at a time
// the tag has two fields: <name, tag>. 
// E.g. <a> = <a, "open">
// </b> = <b, "close">
// the 'foo' in <c> foo </c> = <foo, "text">
class XMLParser {
    /*
	LZ的方法是用stack来存parent, 遇到open和text建新node, 并把新node加到stack顶部node的child list里面，
	如果是open就把新node压栈，遇到close就pop掉stack顶端的node 写完以后小哥的follow up是如果输入的token有问题，
	比如close tag和open tag不匹配，如何做verification
	*/
    XMLNode *root;
    stack<XMLNode*> stack;
public:
    // return the root
    XMLNode* parse(Token tokenizer) {
        token = tokenizer.getNext();
        if (!token) { return NULL; }
        if (token.tag == "close") { return NULL; } // Error: the first tag is not a start tag
        root = new XMLNode(token.name);
        stack.push(root);
        
        token = tokenizer.getNext();
        while (token) {
            if (stack.empty()) { cout << "Error: there should be at least a tag left";}
            
            XMLNode *top = stack.top();
            if (token.tag == "open") {
                // Create a new node and place it under the previous tag
                XMLNode *node = new XMLNode(token.name);
                top->children.push_back(node);
                stack.push(node);
            } else if (token.tag == "close") {
                // the tags are not matched with each other
                if (top.name != token.name) { cout << "error"; }
                stack.pop();
            } else if (token.tag == "text") {
                top->value = text;
            }
            token = tokenizer.getNext();
        }
        
        return root;
    }
}
