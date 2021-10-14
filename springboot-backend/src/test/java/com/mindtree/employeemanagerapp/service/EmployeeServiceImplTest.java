package com.mindtree.employeemanagerapp.service;

import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.repository.EmployeeRepository;
import com.mindtree.employeemanagerapp.service.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    @Mock
    EmployeeRepository employeeRepository;

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

        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee>  list = employeeServiceImpl.getAllEmployees();
        assertEquals(2, list.size());

    }

    @Test
    void createEmployeeTest() {
        Employee employee = new Employee();
        employee.setEmailId("test@gmail.com");
        employee.setFirstName("Test FirstName");
        employee.setLastName("Test LastName");
        employee.setId(Long.valueOf(1));
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        Employee employeeActual = employeeServiceImpl.createEmployee(employee);
        assertEquals(employee.getEmailId(), employeeActual.getEmailId());
        assertEquals(employee.getFirstName(), employeeActual.getFirstName());
        assertEquals(employee.getLastName(), employeeActual.getLastName());
        assertEquals(employee.getId(), employeeActual.getId());
    }

    @Test
    void getEmployeeByIdTest() {
        Employee employee = new Employee();
        employee.setEmailId("test@gmail.com");
        employee.setFirstName("Test FirstName");
        employee.setLastName("Test LastName");
        employee.setId(Long.valueOf(1));
        Optional<Employee> employeeOptional = Optional.of(employee);
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(employeeOptional);
        Employee employeeActual = employeeServiceImpl.getEmployeeById(Long.valueOf(1));
        assertEquals(employee.getEmailId(), employeeActual.getEmailId());
        assertEquals(employee.getFirstName(), employeeActual.getFirstName());
        assertEquals(employee.getLastName(), employeeActual.getLastName());
        assertEquals(employee.getId(), employeeActual.getId());
    }

    @Test
    void updateEmployeeByIdWithNewEmployeeTest() {
        Employee employee = new Employee();
        employee.setEmailId("test@gmail.com");
        employee.setFirstName("Test FirstName");
        employee.setLastName("Test LastName");
        employee.setId(Long.valueOf(1));
        Optional<Employee> employeeOptional = Optional.of(employee);
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(employeeOptional);
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);

        Employee employeeLatestDetails = new Employee();
        employeeLatestDetails.setFirstName("Latest First Name");
        employeeLatestDetails.setLastName("Latest Last Name");
        employeeLatestDetails.setEmailId("LatestEmail@gmail.com");
        Employee employeeActual = employeeServiceImpl.updateEmployeeByIdWithNewEmployee(Long.valueOf(1), employeeLatestDetails);
        assertEquals(employeeLatestDetails.getEmailId(), employeeActual.getEmailId());
        assertEquals(employeeLatestDetails.getFirstName(), employeeActual.getFirstName());
        assertEquals(employeeLatestDetails.getLastName(), employeeActual.getLastName());
    }


    @Test
    void deleteEmployeeTest() {
        Employee employee = new Employee();
        employee.setEmailId("test@gmail.com");
        employee.setFirstName("Test FirstName");
        employee.setLastName("Test LastName");
        employee.setId(Long.valueOf(1));
        Optional<Employee> employeeOptional = Optional.of(employee);
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(employeeOptional);
        Mockito.doNothing().when(employeeRepository).delete((Mockito.any()));
        assertTrue(employeeServiceImpl.deleteEmployee(Long.valueOf(1)));
        Mockito.verify(employeeRepository).delete((Mockito.any()));
    }

}
