#include<iostream>
#include<map>
using namespace std;

class XMLMap {
    public:
        XMLMap() {
            close = 1;
        }
        int close;
        string content;
        map<string, vector<XMLMap>> map;
};

void build(XMLMap& parent) {
    XMLNode node = toknizer.Next();
    string type = node.type;
    string content = node.content;
    if (type == "open") {
        XMLMap child = XMLMap();
        parent.map[content].push_back(child);
    }
    if (type == "inner") {
        parent.content = content;
    }
    if (type == "close") {
        parent.close--;
    }
}

int main()
{
}
