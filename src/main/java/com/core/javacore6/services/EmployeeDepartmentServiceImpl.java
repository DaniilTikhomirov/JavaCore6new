package com.core.javacore6.services;


import com.core.javacore6.exemples.EmployeeNotFoundException;
import com.core.javacore6.models.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeDepartmentServiceImpl implements EmployeeDepartmentService {
    EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public List<Employee> getEmployeesForDepartment(int department) {
        List<Employee> employees = employeeService.getEmployeeList();
        return employees.stream().
                filter(employee -> employee != null && employee.getDepartment() == department).toList();
    }

    private double minSalary(List<Employee> employees) {
        return employees.stream().min(Comparator.comparingDouble(Employee::getSalary)).
                orElseThrow(() -> new EmployeeNotFoundException("не найден сотрудник")).getSalary();
    }

    private double maxSalary(List<Employee> employees) {
        return employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).
                orElseThrow(() -> new EmployeeNotFoundException("не найден сотрудник")).getSalary();
    }


    @Override
    public double minSalaryForDepartment(int department) {
        List<Employee> employee = getEmployeesForDepartment(department);
        return minSalary(employee);
    }

    @Override
    public double maxSalaryForDepartment(int department) {
        List<Employee> employee = getEmployeesForDepartment(department);
        return maxSalary(employee);
    }

    @Override
    public List<Employee> getEmployeesSortDepartment() {
        List<Employee> employees = employeeService.getEmployeeList();
        return employees.stream().sorted(Comparator.comparingDouble(Employee::getDepartment)).toList();
    }


}
