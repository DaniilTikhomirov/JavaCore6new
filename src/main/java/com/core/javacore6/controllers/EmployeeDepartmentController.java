package com.core.javacore6.controllers;


import com.core.javacore6.exemples.EmployeeNotFoundException;
import com.core.javacore6.models.Employee;
import com.core.javacore6.services.EmployeeDepartmentService;
import com.core.javacore6.services.EmployeeDepartmentServiceImpl;
import com.core.javacore6.services.EmployeeService;
import com.core.javacore6.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class EmployeeDepartmentController {
    private final EmployeeDepartmentService edService;

    @Autowired
    public EmployeeDepartmentController(EmployeeService employeeService) {
        this.edService = new EmployeeDepartmentServiceImpl(employeeService);
    }

    @GetMapping("{id}/salary/max")
    public double maxSalary(@PathVariable int id) {
        return edService.maxSalaryForDepartment(id);
    }

    @GetMapping("{id}/salary/min")
    public double minSalary(@PathVariable int id) {
        return edService.minSalaryForDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> allFromDepartment() {
        return edService.getEmployeesSortDepartment();
    }

    @GetMapping("{id}/employees")
    public List<Employee> allFromDepartment(@PathVariable int id){
        return edService.getEmployeesForDepartment(id);
    }

    @GetMapping("{id}/salary/sum")
    public String sumFromDepartment(@PathVariable int id){
        return String.valueOf(edService.sumForDepartment(id));
    }



    @ExceptionHandler(EmployeeNotFoundException.class)
    public String notFoundException(EmployeeNotFoundException e) {
        return "сотрудник не найден (" + e + ")";
    }

}
