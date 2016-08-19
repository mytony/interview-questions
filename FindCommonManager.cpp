#include <iostream>
#include <vector>
using namespace std;

class Employee {
public:
	string name;
	vector<Employee*> directReportList;
	Employee(string name) {
		this->name = name;
	}
};

Employee* findCommonManager(Employee* manager, Employee* emp1, Employee* emp2) {
	// if one of emp is manager then return manager
	if (!manager || manager->name == emp1->name || manager->name == emp2->name) 
		return manager;
	vector<Employee*> reporters;
	for (Employee* emp : manager->directReportList) {
		Employee* rootEmp = findCommonManager(emp, emp1, emp2);
		if (rootEmp) reporters.push_back(rootEmp);
	}
	if (reporters.size() == 2) return manager;
	else if (reporters.size() == 1) return reporters[0];
	else return NULL;
}

int main() {
	Employee e1("A");
	Employee e2("B");
	Employee e3("C");
	Employee e4("D");
	Employee e5("E");
	Employee e6("F");
	Employee e7("G");
	e1.directReportList.push_back(&e2);
	e1.directReportList.push_back(&e3);
	e1.directReportList.push_back(&e4);
	e3.directReportList.push_back(&e5);
	e4.directReportList.push_back(&e6);
	e4.directReportList.push_back(&e7);
	Employee *result = findCommonManager(&e1, &e2, &e7);
	cout << result->name << endl;
	return 0;
}