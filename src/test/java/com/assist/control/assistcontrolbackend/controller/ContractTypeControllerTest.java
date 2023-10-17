package com.assist.control.assistcontrolbackend.controller;

import com.assist.control.assistcontrolbackend.model.ContractType;
import com.assist.control.assistcontrolbackend.repository.ContractTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContractTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractTypeRepository contractTypeRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testListContractTypeById() throws Exception {
        // Set up the mock behavior to return a contract type when findById is called
        when(contractTypeRepository.findById(1L)).thenReturn(Optional.of(new ContractType(1L, "Full time employee")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contractTypes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("Full time employee"));
    }

    @Test
    public void testDeleteContractType() throws Exception {
        // Set up the mock behavior to return a contract type when findById is called
        when(contractTypeRepository.findById(1L)).thenReturn(Optional.of(new ContractType(1L, "Full time employee")));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/contractTypes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("deleted").value(true));
    }

    @Test
    public void testListContractTypes() throws Exception {
        // Set up the mock behavior to return a list of contract types when findAll is called
        List<ContractType> contractTypeList = Arrays.asList(
                new ContractType(1L, "Full time employee"),
                new ContractType(2L, "Half time employee")
        );
        when(contractTypeRepository.findAll()).thenReturn(contractTypeList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contractTypes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))) // Verify that there are two elements in the list
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].name").value("Full time employee"))
                .andExpect(jsonPath("[1].id").value(2))
                .andExpect(jsonPath("[1].name").value("Half time employee"));
    }
}