/*
Given a number n, find all amicable pairs which the numbers of every pair
are smaller than n.

Time complexity: O(n ^ 1.5)

What is amicable pairs? 
	https://en.wikipedia.org/wiki/Amicable_numbers
*/
#include <iostream>
#include <vector>
#include <cmath>
#include <numeric>
using namespace std;
#include <unordered_map>

// Return a list of factors of n without itself
// Time: O(sqrt(n))
vector<int> factorization(int n) {
	vector<int> res;
	if (n == 1) { return res; }

	for (int i = 1; i <= sqrt(n); i++) {
		if (n % i == 0) {
			res.push_back(i);
		}
	}

	int end = res.size() - 1;
	for (int i = end; i > 0; i--) {
		int factor = n / res[i];

		// avoid duplicate sqrt factor when n is a perfect square
		// E.g. The 3 when n is 9
		if (factor != res[end]) {
			res.push_back(factor);
		}
	}
	return res;
}

// Return the sum of the proper divisors of a number
// Time: Don't know 
int sumProperDivisors(int n) {
	vector<int> factors = factorization(n);
	return accumulate(factors.begin(), factors.end(), 0);
}

// Return all amicable pairs which are smaller than n
// Time: O(n^1.5)
vector<pair<int, int> > amicablePairs(int n) {
	// Calculate the sum of every numbers under n, 
	// it forms a pair if the sums of each other match.

	// Using vector will cause Segmentation Fault when n is large list 100k
	// Reason: when sumProperDivisors(i) > my vector size and do below statement
	// if (sums[sums[i]] == i), it's out of bound
	vector<int> sums(n+1, 0);
	// unordered_map<int, int> sums;
	vector<pair<int, int> > res;

	// O(n)
	for (int i = 1; i <= n; i++) {
		// O(n^0.5)
		sums[i] = sumProperDivisors(i);

		// out of range
		if (sums[i] > n) { continue; }

		// amicable pairs need to be two different numbers
		if (i == sums[i]) { continue; } 

		if (sums[sums[i]] == i) {
			// found a new pair
			res.push_back(make_pair(sums[i], i));
			cout << sums[i] << "," << i << endl;
		}
	}

	return res;
}

// Return all amicable pairs which are smaller than n
// Time: O(nlogn) Faster!!!
vector<pair<int, int> > amicablePairs_faster(int n) {
	// Graph is from jmnjmnjmn's code
	// 0 1 2 3 4 5 6 7 8 9 
	// 1 1 1 1 1 1 1 1 1 1
	// 2       2   2   2
	// 3           3     3
	// 4               
	vector<int> sums(n+1, 1);
	vector<pair<int, int> > res;

	// O(nlogn) n*(1/2+1/3+1/4....1/n)
	for (int i = 2; i <= n/2; i++) {
		for (int j = 2 * i; j <= n; j += i) {
			sums[j] += i;
		}
	}

	for (int i = 1; i <= n; i++) {
		// out of range
		if (sums[i] > n) { continue; }
		// already met or same as itself
		if (sums[i] <= i) { continue; }

		if (i == sums[sums[i]]) {
			// found a new pair
			res.push_back(make_pair(i, sums[i]));
			cout << i << "," << sums[i]<< endl;
		}

	}

	return res;
}

int main() {
	amicablePairs_faster(10000000);
	return 0;
}
