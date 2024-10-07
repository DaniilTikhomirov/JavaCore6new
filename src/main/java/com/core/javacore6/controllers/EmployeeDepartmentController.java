package com.core.javacore6.controllers;


import com.core.javacore6.exemples.EmployeeNotFoundException;
import com.core.javacore6.models.Employee;
import com.core.javacore6.services.EmployeeDepartmentService;
import com.core.javacore6.services.EmployeeDepartmentServiceImpl;
import com.core.javacore6.services.EmployeeService;
import com.core.javacore6.services.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class EmployeeDepartmentController {
    private final EmployeeDepartmentService edService;

    public EmployeeDepartmentController() {
        this.edService = new EmployeeDepartmentServiceImpl();
    }

    @GetMapping("/max-salary")
    public double maxSalary(@RequestParam("departmentId") int departmentId) {
        return edService.maxSalaryForDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public double minSalary(@RequestParam("departmentId") int departmentId) {
        return edService.minSalaryForDepartment(departmentId);
    }

    @GetMapping("/all")
    public List<Employee> allFromDepartment(@RequestParam(value = "departmentId", required = false) String departmentId) {
        if (departmentId != null) {
            int department = Integer.parseInt(departmentId);
            return edService.getEmployeesForDepartment(department);
        }
        return edService.getEmployeesSortDepartment();

    }



    @ExceptionHandler(EmployeeNotFoundException.class)
    public String notFoundException(EmployeeNotFoundException e) {
        return "сотрудник не найден (" + e + ")";
    }

}
