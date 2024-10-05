package com.core.javacore6.services;

import com.core.javacore6.models.Employee;

import java.util.List;

public interface EmployeeDepartmentService {

    List<Employee> getEmployeesForDepartment(int department);

    double minSalaryForDepartment(int department);

    double maxSalaryForDepartment(int department);


    List<Employee> getEmployeesSortDepartment();
}
