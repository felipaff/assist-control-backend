package com.assist.control.assistcontrolbackend.repository;

import com.assist.control.assistcontrolbackend.model.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {
}
