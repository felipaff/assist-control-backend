package com.assist.control.assistcontrolbackend.controller;

import com.assist.control.assistcontrolbackend.exception.ResourceNotFoundException;
import com.assist.control.assistcontrolbackend.model.ContractType;
import com.assist.control.assistcontrolbackend.repository.ContractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ContractTypeController {

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @GetMapping("/contractTypes")
    public List<ContractType> listContractTypes() {
        return contractTypeRepository.findAll();
    }

    @PostMapping("/contractTypes")
    public ContractType saveContractType(@RequestBody ContractType contractType) {
        return contractTypeRepository.save(contractType);
    }

    @GetMapping("/contractTypes/{id}")
    public ResponseEntity<ContractType> listContractTypeById(@PathVariable Long id) {
        ContractType contractType = contractTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContractType id: " + id + " doesn't exist"));
        return ResponseEntity.ok(contractType);
    }

    @PutMapping("/contractTypes/{id}")
    public ResponseEntity<ContractType> updateContractType(@PathVariable Long id, @RequestBody ContractType contractTypeRequest) {
        ContractType contractType = contractTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContractType id: " + id + " doesn't exist"));

        contractType.setName(contractTypeRequest.getName());

        ContractType updatedContractType = contractTypeRepository.save(contractType);
        return ResponseEntity.ok(updatedContractType);
    }

    @DeleteMapping("/contractTypes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteContractType(@PathVariable Long id) {
        ContractType contractType = contractTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContractType id: " + id + " doesn't exist"));

        contractTypeRepository.delete(contractType);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}