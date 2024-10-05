package com.core.javacore6.controllers;

import com.core.javacore6.models.Employee;
import com.core.javacore6.exemples.EmployeeAlreadyAddedException;
import com.core.javacore6.exemples.EmployeeNotFoundException;
import com.core.javacore6.exemples.EmployeeStorageIsFullException;
import com.core.javacore6.services.EmployeeService;
import com.core.javacore6.services.EmployeeServiceImpl;
import com.core.javacore6.services.MajorPageService;
import com.core.javacore6.services.MajorPageServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController() {
        employeeService = new EmployeeServiceImpl();
    }


    @GetMapping
    public List<Employee> allEmployee() {
        return employeeService.getEmployeeList();
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("department") int department,
                        @RequestParam("salary") double salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String alreadyAddedException(EmployeeAlreadyAddedException e) {
        return "сотрудник уже был добавлен (" + e.getClass() + ")";
    }

    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String storageIsFullException(EmployeeStorageIsFullException e) {
        return "превышен лимит количества сотрудников в фирме (" + e.getClass() + ")";
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        return employeeService.delEmployee(firstName, lastName);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String notFoundException(EmployeeNotFoundException e) {
        return "сотрудник не найден (" + e + ")";
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName) {

        return employeeService.getEmployeeList().
                get(employeeService.
                        findIndexEmployee(firstName,
                                lastName));
    }


}
