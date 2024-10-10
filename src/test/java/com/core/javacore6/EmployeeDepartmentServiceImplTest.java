package com.core.javacore6;

import com.core.javacore6.exemples.EmployeeNotFoundException;
import com.core.javacore6.models.Employee;
import com.core.javacore6.services.EmployeeDepartmentService;
import com.core.javacore6.services.EmployeeDepartmentServiceImpl;
import com.core.javacore6.services.EmployeeService;
import com.core.javacore6.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeeDepartmentServiceImplTest {
    List<Employee> employees;

    @BeforeEach
    public void setUp() {
        employees = new ArrayList<>();
        employees.add(new Employee("Testq", "Test", 1, 25000));
        employees.add(new Employee("Testw", "Test", 1, 15000));
        employees.add(new Employee("Teste", "Test", 2, 25000));
        employees.add(new Employee("Testr", "Test", 2, 15000));
        employees.add(new Employee("Testt", "Test", 3, 25000));
        employees.add(new Employee("Testy", "Test", 3, 15000));
    }

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeDepartmentServiceImpl employeeDepartmentService;

    @ParameterizedTest
    @MethodSource("paramForSumTest")
    public void sumForDepartmentTest(List<Employee> employees, int answer) {

        when(employeeService.getEmployeeList()).thenReturn(employees);

        Assertions.assertEquals(employeeDepartmentService.sumForDepartment(1), answer);
    }

    @Test
    public void sortedForDepartmentTest() {
        when(employeeService.getEmployeeList()).thenReturn(employees);

        Assertions.assertEquals(employeeDepartmentService.getEmployeesSortDepartment().keySet(), Set.of(1, 2, 3));
    }

    @Test
    public void maxForDepartmentTest() {
        when(employeeService.getEmployeeList()).thenReturn(employees);

        Assertions.assertEquals(employeeDepartmentService.maxSalaryForDepartment(2), 25000);
    }

    @Test
    public void maxForDepartmentNullTest() {
        when(employeeService.getEmployeeList()).thenReturn(List.of());

        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeDepartmentService.maxSalaryForDepartment(2));
    }

    @Test
    public void minForDepartmentTest() {
        when(employeeService.getEmployeeList()).thenReturn(employees);

        Assertions.assertEquals(employeeDepartmentService.minSalaryForDepartment(2), 15000);
    }

    @Test
    public void minForDepartmentNullTest() {
        when(employeeService.getEmployeeList()).thenReturn(List.of());

        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeDepartmentService.minSalaryForDepartment(2));
    }

    @Test
    public void getEmployeesForDepartmentTest() {
        when(employeeService.getEmployeeList()).thenReturn(employees);

        Assertions.assertEquals(employeeDepartmentService.getEmployeesForDepartment(2),
                List.of(new Employee("Teste", "Test", 2, 25000),
        new Employee("Testr", "Test", 2, 15000)));
    }


    public static Stream<Arguments> paramForSumTest() {
        return Stream.of(
                Arguments.of(List.of(new Employee("Testq", "Test", 1, 25000),
                        new Employee("Testw", "Test", 1, 15000),
                        new Employee("Teste", "Test", 2, 25000),
                        new Employee("Testr", "Test", 2, 15000),
                        new Employee("Testt", "Test", 3, 25000),
                        new Employee("Testy", "Test", 3, 15000)), 40000),
                Arguments.of(List.of(), 0)
        );
    }


}

