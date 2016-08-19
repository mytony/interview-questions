#include <iostream>
#include <vector>

// IP Filter
/*就是根据计算机网络里面那个CIDR然后来做IP address filtering。比如给定一些rule: "7.0.0.0/8", 
那么所有前8位是7的address应该全部被filter掉。楼主用hashset来存rule然后用一些位运算的方法来做filter。
*/
class TrieNode {
public:
	bool isEnd; // eg. 到8位就要設成true
	TrieNode *children[2]; // 0 or 1
	TrieNode() : isEnd(false) {
		memset(children, NULL, sizeof(TrieNode*) * 2);
	}
};
class ipFilter {
public:
	ipFilter() {
		root = new TrieNode();
	}
	// put rule into trie node
	void setRule(string rule) {
		vector<int> nums;
		int endBit = 0;
		int num = 0;
		bool bSlash = false;
		// get rule!
		for (int i = 0; i < rule.size(); i++) {

			if (isdigit(rule[i]) && !bSlash) {
				num = num*10 + rule[i]-'0';
			}
			else if (rule[i] == '.') {
				nums.push_back(num);
			}
			else if (rule[i] == '/') {
				nums.push_back(num);
				bSlash = true; // prepare to have endBit
			}
			else if (isdigit(rule[i]) && bSlash) {
				endBit = endBit*10 + rule[i]-'0';
			}
		}

		// put into trie node
		int unitBits = 7; // every 8 bits reset
		int totalBits = 0;
		TrieNode* run = root;

		while (totalBits != endBit) {

			int num = nums[totalBits/8];
			int currBit = (num & (1 << unitBits)) == 0 ? 0:1 ;
			// create trienode path
			if (!run->children[currBit])
				run->children[currBit] = new TrieNode();
			run = run->children[currBit];
			totalBits++;
			unitBits--;
			if (unitBits < 0) unitBits = 7;
		}
		run->isEnd = true;
	}

	bool isFiltered(vector<int> ipUnits) { // eg.7.0.0.0

		TrieNode* run = root;
		for (int pos = 0; pos < ipUnits.size(); pos++) {
			int num = ipUnits[pos];
			// check 8 bits of num
			for (int i = 7; i >= 0; i--) {
				int bit = (num & (1 << i)) == 0 ? 0:1 ;
				if (!run)
					return false;
				if (run->isEnd) // is with the rule-> filtered!
					return true;
				else 
					run = run->children[bit];		
			}
		}
		return false;
	}

private:
	TrieNode *root;
};

int main(int argc, char *argv[])
{
	int array[] = {6,0,0,0};
	vector<int> v(begin(array), end(array));

	ipFilter obj;
	obj.setRule("7.0.0.0/8");
	bool isFilter = obj.isFiltered(v);
	cout << isFilter << endl;
}
