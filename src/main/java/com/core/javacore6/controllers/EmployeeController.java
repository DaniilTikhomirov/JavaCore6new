package com.core.javacore6.controllers;

import com.core.javacore6.Employee;
import com.core.javacore6.exemples.EmployeeAlreadyAddedException;
import com.core.javacore6.exemples.EmployeeNotFoundException;
import com.core.javacore6.exemples.EmployeeStorageIsFullException;
import com.core.javacore6.services.EmployeeService;
import com.core.javacore6.services.EmployeeServiceImpl;
import com.core.javacore6.services.MajorPageService;
import com.core.javacore6.services.MajorPageServiceImpl;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    private final MajorPageService majorPageService;

    public EmployeeController() {
        employeeService = new EmployeeServiceImpl();
        majorPageService = new MajorPageServiceImpl();
    }

    @GetMapping
    public String major() {
        return majorPageService.getAllInfo();
    }

    @GetMapping("/employee")
    public List<Employee> allEmployee() {
        return employeeService.getEmployeeList();
    }

    @GetMapping("/employee/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String alreadyAddedException(EmployeeAlreadyAddedException e) {
        return "сотрудник уже был добавлен (" + e.getClass() + ")";
    }

    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String storageIsFullException(EmployeeStorageIsFullException e) {
        return "превышен лимит количества сотрудников в фирме (" + e.getClass() + ")";
    }

    @GetMapping("/employee/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        return employeeService.delEmployee(firstName, lastName);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String notFoundException(EmployeeNotFoundException e) {
        return "сотрудник не найден (" + e + ")";
    }

    @GetMapping("/employee/find")
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName) {

        return employeeService.getEmployeeList().
                get(employeeService.
                        findIndexEmployee(firstName,
                                lastName));
    }


}
