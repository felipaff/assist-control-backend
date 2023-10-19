package com.assist.control.assistcontrolbackend.controller;

import com.assist.control.assistcontrolbackend.exception.ResourceNotFoundException;
import com.assist.control.assistcontrolbackend.model.Employee;
import com.assist.control.assistcontrolbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping("/employees")
    public List<Employee> listEmployees(
            @RequestParam(name = "positionId", required = false) Long positionId,
            @RequestParam(name = "contractTypeId", required = false) Long contractTypeId) {
        if (positionId != null && contractTypeId != null) {
            return employeeRepository.findByPositionIdAndContractTypeId(positionId, contractTypeId);
        } else if (positionId != null) {
            return employeeRepository.findByPositionId(positionId);
        } else if (contractTypeId != null) {
            return employeeRepository.findByContractTypeId(contractTypeId);
        } else {
            return employeeRepository.findAll();
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> listEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee id : "+ id + "doesn't exist"));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeRequest){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee id : "+ id + "doesn't exist"));

        employee.setName(employeeRequest.getName());
        employee.setPosition(employeeRequest.getPosition());
        employee.setContractType(employeeRequest.getContractType());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee id : "+ id + "doesn't exist"));

        employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

