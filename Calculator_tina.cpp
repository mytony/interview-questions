/*
面经题 basic calculator +-*除 和数字 都是一位 没有空格 输入都是valid 所以不用考虑corner case 
follow up是 数字变成多位,然后再followup加上空格,follow up: 负数 follow up：如果可以有括号怎么做
227. Basic Calculator II // https://leetcode.com/problems/basic-calculator-ii/
*/
int calculate(string s) {
	// cout << s << endl;
	if (s.size() == 0) return 0;
	stack<int> nums;
	stack<char> ops;
	int sign = 1;
	char op;// operation
	int num = 0;
	int tmp = 0;
	int res = 0;
	for (int pos = 0; pos < s.size();) {
		char ch = s[pos];
		if (isdigit(ch)) {
			num = num*10 + ch-'0';
		}
		else {
			if (ch == '(') {
				int count = 1;
				int len = 0;
				int start = pos+1;
				pos++;
				// 找()pair
				while (count && pos < s.size()) {
					if (s[pos] == '(') count++;
					else if (s[pos] == ')') count--;
					len++;
					pos++;
				}
				pos--;
				num = calculate(s.substr(start, len-1));
			}

			// calc
			if (op == '*') {
				num = tmp*num;
				op = '0';
			}
			else if (op == '/') {
				num = tmp/num;
				op = '0';
			}

			// set operator
			if (ch == '*') {
				tmp = num;
				op = '*';
				num = 0;
			}
			else if (ch == '/') {
				tmp = num;
				op = '/';
				num = 0;
			}
			else if (ch == '-' || ch == '+') {
				res += sign*num;
				tmp = 0;
				op = ch;
				sign = (ch == '-')? -1:1;
				num = 0;
			}
		}
		pos++;
	}
	// last
	if (op == '*') {
		res += sign*(tmp*num);
	}
	else if (op == '/') {
		res += sign*(tmp/num);
	}
	else {
		res += sign*num;
	}
    return res;
}
