#include <iostream>
#include <string>
#include <stack>
using namespace std;

class Tokenizer {
public:
	Tokenizer(string input) {
		str = input;
		pos = 0;
		isEnd = false;
		n = str.size();
	}

	string getNextToken() {
		if (isEnd) { return ""; }

		size_t x = str.find_first_of("+-*/()", pos);
		if (x == pos) { // current sting is op or parenthesis
			if (x == n - 1) { isEnd = true; }

			pos++;
			return string(1, str[x]);
		} else { // current pos is a number
			if (x == string::npos) { isEnd = true; }

			size_t start = pos;
			pos = x;
			return str.substr(start, x - start);
		}
	}
private:
	string str;
	size_t pos;
	bool isEnd;
	int n;
};

double helper(string x, string y, string op) {
	double a = stod(x);
	double b = stod(y);

	if (op == "+") { return a + b; }
	else if (op == "-") { return a - b; }
	else if (op == "*") { return a * b; }
	else if (op == "/") { return a / b; }
	else { cout << "Error: no such operator" << endl; return 0; }
}

double calculate(string str) {
	Tokenizer tokenizer(str);
	stack<string> num;
	stack<string> op;
	string token;

	while (!(token = tokenizer.getNextToken()).empty()) {
		// cout << token << endl;
		string newNum = "";

		if (token == "(") {
			num.push(token);
		} else if (token == "+" || token == "-" ||
				   token == "*" || token == "/") {
			op.push(token);
		} else if (token == ")") {
			string number, sign;
			double result = 0;
			while (true) {
				number = num.top();
				num.pop();

				if (num.top() == "(") {
					num.pop();
					result += stod(number);
					// When pushing a new number, check * and /
					newNum = to_string(result);
					// num.push(to_string(result));
					break;
				}

				sign = op.top();
				op.pop();
				if (sign == "+") {
					result += stod(number);
				} else {
					result -= stod(number);
				}
			}
		} else { // number
			newNum = token;
		}

		// When we have a new number, 
		// check if last opeator is * or /, then do the math
		if (newNum != "") {
			// "(" case is to avoid 3*(1+2) when processing 1
			if (op.empty() || num.top() == "(") { num.push(newNum); }
			else {
				string opr = op.top();
				if (opr == "*" || opr == "/") {
					double result = helper(num.top(), newNum, opr);
					op.pop();
					num.pop();
					num.push(to_string(result));
				} else {
					num.push(newNum);
				}
			}
		}
	}

	double result = 0;
	while (!op.empty()) {
		string sign = op.top();
		op.pop();
		if (sign == "+") {
			result += stod(num.top());
		} else {
			result -= stod(num.top());
		}
		num.pop();
	}
	return result + stod(num.top());
}

int main() {
	cout << calculate("3*((2-(1+1))+4/(3/1*2))") << endl;
}
