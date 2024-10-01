package com.core.javacore6.services;

import com.core.javacore6.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);
    int findIndexEmployee(String firstName, String lastName);
    Employee delEmployee(String firstName, String lastName);
    boolean containsEmployee(Employee employee);
    List<Employee> getEmployeeList();
    int getMaxEmployees();
}
