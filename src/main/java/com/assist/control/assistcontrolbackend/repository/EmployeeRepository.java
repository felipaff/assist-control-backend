package com.assist.control.assistcontrolbackend.repository;

import com.assist.control.assistcontrolbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Method to find employees by Position ID and Contract Type ID
    List<Employee> findByPositionIdAndContractTypeId(Long positionId, Long contractTypeId);

    // Method to find employees by Position ID
    List<Employee> findByPositionId(Long positionId);

    // Method to find employees by Contract Type ID
    List<Employee> findByContractTypeId(Long contractTypeId);
}
