package com.core.javacore6.services;

import com.core.javacore6.Employee;
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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException("превышен лимит количества сотрудников в фирме");
        }

        if (containsEmployee(employee)){
            throw new EmployeeAlreadyAddedException("поппытка добавить существуещего сотруднка");
        }

        employeeList.add(employee);
        return employee;


    }

    @Override
    public int findIndexEmployee(String firstName, String lastName) {

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
        employeeList.remove(index);
        return new Employee(firstName, lastName);

    }

    @Override
    public boolean containsEmployee(Employee employee) {
        for (Employee e : employeeList) {
            if(e.equals(employee)){
                return true;
            }
        }
        return false;
    }

}
