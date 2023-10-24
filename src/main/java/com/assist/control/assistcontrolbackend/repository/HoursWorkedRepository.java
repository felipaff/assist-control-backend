package com.assist.control.assistcontrolbackend.repository;

import com.assist.control.assistcontrolbackend.model.HoursWorked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoursWorkedRepository extends JpaRepository<HoursWorked, Long> {
    List<HoursWorked> findByEmployee(Long employeeId);
}
