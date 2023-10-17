package com.assist.control.assistcontrolbackend.controller;

import com.assist.control.assistcontrolbackend.model.Position;
import com.assist.control.assistcontrolbackend.repository.PositionRepository;
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
public class PositionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PositionRepository positionRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testListPositionById() throws Exception {
        // Set up the mock behavior to return a position when findById is called
        when(positionRepository.findById(1L)).thenReturn(Optional.of(new Position(1L, "Manager")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/positions/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("Manager"));
    }

    @Test
    public void testDeletePosition() throws Exception {
        // Set up the mock behavior to return a position when findById is called
        when(positionRepository.findById(1L)).thenReturn(Optional.of(new Position(1L, "Manager")));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/positions/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("deleted").value(true));
    }

    @Test
    public void testListPositions() throws Exception {
        // Set up the mock behavior to return a list of positions when findAll is called
        List<Position> positionList = Arrays.asList(
                new Position(1L, "Manager"),
                new Position(2L, "Supervisor")
        );
        when(positionRepository.findAll()).thenReturn(positionList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/positions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))) // Verify that there are two elements in the list
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].name").value("Manager"))
                .andExpect(jsonPath("[1].id").value(2))
                .andExpect(jsonPath("[1].name").value("Supervisor"));
    }
}