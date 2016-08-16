#include <string>
#include <vector>
#include <iostream>
using namespace std;

class XMLNode {
    string name;
    string value;
    vector<XMLNode*> children;
}

class XMLParser {
    XMLNode *root;
    stack<XMLNode*> stack;
    
}
