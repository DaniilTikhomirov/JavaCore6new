package com.core.javacore6;

import com.core.javacore6.exemples.EmployeeAlreadyAddedException;
import com.core.javacore6.exemples.EmployeeNotFoundException;
import com.core.javacore6.exemples.EmployeeStorageIsFullException;
import com.core.javacore6.exemples.WrongEmployee;
import com.core.javacore6.models.Employee;
import com.core.javacore6.services.EmployeeService;
import com.core.javacore6.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeServiceImplTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp(){
        employeeService = new EmployeeServiceImpl();
    }


    @Test
    public void EmployeeServiceAddTest(){
        employeeService.addEmployee("Test", "Test", 1, 25000);
        Assertions.assertEquals(employeeService.getEmployeeList().getFirst(),
                new Employee("Test", "Test", 1, 25000));
    }

    @Test
    public void EmployeeServiceAddTestAddedException(){
        employeeService.addEmployee("Test", "Test", 1, 25000);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee("Test", "Test", 1, 25000));
    }

    @Test
    public void EmployeeServiceAddTestEmployeeException(){
        Assertions.assertThrows(WrongEmployee.class,
                () -> employeeService.addEmployee("Te34st", "Test", 1, 25000));
    }

    @Test
    public void EmployeeServiceAddTestSizeException(){
        employeeService.addEmployee("Testq", "Test", 1, 25000);
        employeeService.addEmployee("Testw", "Test", 1, 25000);
        employeeService.addEmployee("Teste", "Test", 1, 25000);
        employeeService.addEmployee("Testr", "Test", 1, 25000);
        employeeService.addEmployee("Testt", "Test", 1, 25000);
        Assertions.assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.addEmployee("Test", "Test", 1, 25000));
    }

    @Test
    public void EmployeeServiceFindEmployeeTest(){
        employeeService.addEmployee("Test", "Test", 1, 25000);
        Assertions.assertEquals(employeeService.findEmployee("Test", "Test"),
                new Employee("Test", "Test", 1, 25000));
    }

    @Test
    public void EmployeeServiceFindEmployeeErrorTest(){
        employeeService.addEmployee("Test", "Test", 1, 25000);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.findEmployee("Testy", "Test"));
    }

    @Test
    public void findIndexEmployeeTest(){
        employeeService.addEmployee("Test", "Test", 1, 25000);
        employeeService.addEmployee("Testq", "Test", 1, 25000);
        Assertions.assertEquals(employeeService.findIndexEmployee("Testq", "Test"), 1);
    }

    @Test
    public void findIndexEmployeeErrorTest(){
        employeeService.addEmployee("Test", "Test", 1, 25000);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.findIndexEmployee("Testy", "Test"));
    }


    @Test
    public void delEmployeeTest(){
        employeeService.addEmployee("Test", "Test", 1, 25000);
        int len = employeeService.getEmployeeList().size();
        Assertions.assertEquals(employeeService.delEmployee("Test", "Test"),
                new Employee("Test", "Test", 1, 25000));
        Assertions.assertEquals(len - 1, employeeService.getEmployeeList().size());
    }





}
