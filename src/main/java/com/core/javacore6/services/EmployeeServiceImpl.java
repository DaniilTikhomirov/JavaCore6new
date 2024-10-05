package com.core.javacore6.services;

import com.core.javacore6.models.Employee;
import com.core.javacore6.exemples.EmployeeAlreadyAddedException;
import com.core.javacore6.exemples.EmployeeNotFoundException;
import com.core.javacore6.exemples.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    private final int maxEmployees = 5;


    public int getMaxEmployees() {
        return maxEmployees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employeeList.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException("превышен лимит количества сотрудников в фирме");
        }

        if (containsEmployee(employee)) {
            throw new EmployeeAlreadyAddedException("поппытка добавить существуещего сотруднка");
        }

        employeeList.add(employee);
        return employee;


    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        return employeeList.stream().
                filter(e -> (e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))).
                findAny().
                orElseThrow(() -> new EmployeeNotFoundException("сотрудник не был найден"));
    }


    @Override
    public int findIndexEmployee(String firstName, String lastName) {
        /*
        можно ли как то это написать через StreamApi?
        идей как релализовать именно этот методот нет
         */

        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            if (employee.getFirstName().equals(firstName) &&
                    employee.getLastName().equals(lastName)) {
                return i;
            }
        }
        throw new EmployeeNotFoundException("сотрудник не был найден");
    }

    @Override
    public Employee delEmployee(String firstName, String lastName) {
        int index = findIndexEmployee(firstName, lastName);
        Employee e = employeeList.get(index);
        employeeList.remove(index);
        return e;

    }

    @Override
    public boolean containsEmployee(Employee employee) {
        for (Employee e : employeeList) {
            if (e.equals(employee)) {
                return true;
            }
        }
        return false;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

}
