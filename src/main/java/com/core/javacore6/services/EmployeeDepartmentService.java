package com.core.javacore6.services;

import com.core.javacore6.models.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDepartmentService {

    Map<Integer, List<Employee>> getEmployeesSortDepartment();

    List<Employee> getEmployeesForDepartment(int department);

    double minSalaryForDepartment(int department);

    double maxSalaryForDepartment(int department);

    double sumForDepartment(int department);
}
