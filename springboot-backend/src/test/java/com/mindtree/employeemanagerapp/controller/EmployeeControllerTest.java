package com.mindtree.employeemanagerapp.controller;


import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @Test
    void getAllEmployeesTest() {

        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmailId("test@gmail.com");
        employee.setFirstName("Test FirstName");
        employee.setLastName("Test LastName");
        employee.setId(Long.valueOf(1));

        Employee employee_two = new Employee();
        employee_two.setEmailId("test_Two@gmail.com");
        employee_two.setFirstName("Test FirstName Two");
        employee_two.setLastName("Test LastName Two");
        employee_two.setId(Long.valueOf(1));

        employeeList.add(employee);
        employeeList.add(employee_two);



        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeeList);
        ResponseEntity<?> responseEntity = employeeController.getAllEmployees();
        assertNotNull(responseEntity.getBody());
        assertEquals(202, responseEntity.getStatusCodeValue());
    }

    @Test
    void createEmployeeTest() {
        Employee employee = new Employee();
        employee.setEmailId("test@gmail.com");
        employee.setFirstName("Test FirstName");
        employee.setLastName("Test LastName");
        employee.setId(Long.valueOf(1));
        Mockito.when(employeeService.createEmployee((Employee)Mockito.any())).thenReturn(employee);
        ResponseEntity<?> responseEntity = employeeController.createEmployee(employee);
        assertNotNull(responseEntity.getBody());
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    void getEmployeeByIdTest() {
        Employee employee = new Employee();
        employee.setEmailId("test@gmail.com");
        employee.setFirstName("Test FirstName");
        employee.setLastName("Test LastName");
        employee.setId(Long.valueOf(1));
        Mockito.when(employeeService.getEmployeeById(Mockito.anyLong())).thenReturn(employee);
        ResponseEntity<?> responseEntity = employeeController.getEmployeeById(Long.valueOf(1));
        assertNotNull(responseEntity.getBody());
        assertEquals(202, responseEntity.getStatusCodeValue());
    }

    @Test
    void updateEmployeeTest() {
        Employee employee = new Employee();
        employee.setEmailId("test@gmail.com");
        employee.setFirstName("Test FirstName");
        employee.setLastName("Test LastName");
        employee.setId(Long.valueOf(1));
        Mockito.when(employeeService.updateEmployeeByIdWithNewEmployee(Mockito.anyLong(),Mockito.any())).thenReturn(employee);

        Employee employeeLatestDetails = new Employee();
        employeeLatestDetails.setFirstName("Latest First Name");
        employeeLatestDetails.setLastName("Latest Last Name");
        employeeLatestDetails.setEmailId("LatestEmail@gmail.com");

        ResponseEntity<?> responseEntity = employeeController.updateEmployee(Long.valueOf(1), employeeLatestDetails);
        assertNotNull(responseEntity.getBody());
        assertEquals(202, responseEntity.getStatusCodeValue());
    }

    @Test
    void deleteEmployeeTest() {
        Mockito.when(employeeService.deleteEmployee(Mockito.anyLong())).thenReturn(true);
        ResponseEntity<?> responseEntity = employeeController.deleteEmployee(Long.valueOf(1));
        assertNotNull(responseEntity.getBody());
        assertEquals(410, responseEntity.getStatusCodeValue());
    }
}
