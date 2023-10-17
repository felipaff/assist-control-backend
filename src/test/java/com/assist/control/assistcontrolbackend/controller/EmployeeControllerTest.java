package com.assist.control.assistcontrolbackend.controller;

import com.assist.control.assistcontrolbackend.model.ContractType;
import com.assist.control.assistcontrolbackend.model.Employee;
import com.assist.control.assistcontrolbackend.model.Position;
import com.assist.control.assistcontrolbackend.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateEmployee() throws Exception {
        // Create an Employee object for testing
        Position position = new Position(1L, "Manager"); // Replace with your logic to create a position
        ContractType contractType = new ContractType(1L, "Full Time"); // Replace with your logic to create a contract type

        Employee newEmployee = new Employee();
        newEmployee.setName("New Employee");
        newEmployee.setPosition(position);
        newEmployee.setContractType(contractType);

        // Set up the mock behavior to return the created object
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(newEmployee);

        // Convert the object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String newEmployeeJSON = objectMapper.writeValueAsString(newEmployee);

        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newEmployeeJSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(0))
                .andExpect(jsonPath("name").value("New Employee"));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        // Create an updated Employee object
        Position position = new Position(2L, "Supervisor"); // Replace with your logic to create an updated position
        ContractType contractType = new ContractType(2L, "Part Time"); // Replace with your logic to create an updated contract type

        Employee updatedEmployee = new Employee(1L, "Updated Employee", position, contractType);

        // Set up the mock behavior to update the employee
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee(1L, "Employee 1", new Position(1L, "Manager"), new ContractType(1L, "Full Time"))));
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(updatedEmployee);

        // Convert the object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String updatedEmployeeJSON = objectMapper.writeValueAsString(updatedEmployee);

        mockMvc.perform(put("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("Updated Employee"))
                .andExpect(jsonPath("position.id").value(2)) // Verify that the position has been updated
                .andExpect(jsonPath("contractType.id").value(2)); // Verify that the contract type has been updated
    }

    @Test
    public void testListEmployeeById() throws Exception {
        // Set up the mock behavior to return an employee when findById is called
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee(1L, "Employee 1", new Position(1L, "Manager"), new ContractType(1L, "Full Time"))));

        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("Employee 1"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        // Set up the mock behavior to return an employee when findById is called
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee(1L, "Employee 1", new Position(1L, "Manager"), new ContractType(1L, "Full Time"))));

        mockMvc.perform(delete("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("deleted").value(true));
    }

    @Test
    public void testListEmployees() throws Exception {
        // Set up the mock behavior to return a list of employees when findAll is called
        List<Employee> employeeList = Arrays.asList(
                new Employee(1L, "Employee 1", new Position(1L, "Manager"), new ContractType(1L, "Full Time")),
                new Employee(2L, "Employee 2", new Position(2L, "Supervisor"), new ContractType(2L, "Part Time"))
        );
        when(employeeRepository.findAll()).thenReturn(employeeList);

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))) // Verify that there are two elements in the list
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].name").value("Employee 1"))
                .andExpect(jsonPath("[1].id").value(2))
                .andExpect(jsonPath("[1].name").value("Employee 2"));
    }
}