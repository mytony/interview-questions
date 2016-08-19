class Employee {
public:
	string name;
	vector<Employee*> directReportList;
};
Employee* findCommonManager(Employee* manager, Employee* emp1, Employee* emp2) {
	// if one of emp is manager then return manager
	if (!manager || manager == emp1 || manager == emp2) 
		return manager;
	vector<Employee*> reporters;
	for (Employee* emp:manager->directReportList) {
		Employee* rootEmp = findCommonManager(emp, emp1, emp2);
		if (rootEmp) reporters.push_back(rootEmp);
	}
	if (reporters.size() >= 2) return manager;
	else return reporters[0];
}
