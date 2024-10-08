package com.core.javacore6.services;


import com.core.javacore6.exemples.EmployeeNotFoundException;
import com.core.javacore6.models.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeDepartmentServiceImpl implements EmployeeDepartmentService {
    EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public List<Employee> getEmployeesForDepartment(int department) {
        return getStreamEmployeesForDepartment(department).toList();
    }

    private Stream<Employee> getStreamEmployeesForDepartment(int department) {
        List<Employee> employees = employeeService.getEmployeeList();
        return employees.stream().
                filter(employee -> employee != null && employee.getDepartment() == department);
    }

    private double minSalary(Stream<Employee> employees) {
        return employees.min(Comparator.comparingDouble(Employee::getSalary)).
                orElseThrow(() -> new EmployeeNotFoundException("не найден сотрудник")).getSalary();
    }

    private double maxSalary(Stream<Employee> employees) {
        return employees.max(Comparator.comparingDouble(Employee::getSalary)).
                orElseThrow(() -> new EmployeeNotFoundException("не найден сотрудник")).getSalary();
    }


    @Override
    public double minSalaryForDepartment(int department) {
        Stream<Employee> employee = getStreamEmployeesForDepartment(department);
        return minSalary(employee);
    }

    @Override
    public double maxSalaryForDepartment(int department) {
        Stream<Employee> employee = getStreamEmployeesForDepartment(department);
        return maxSalary(employee);
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesSortDepartment() {
        List<Employee> employees = employeeService.getEmployeeList();
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }


}
