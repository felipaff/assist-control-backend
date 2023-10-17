package com.assist.control.assistcontrolbackend.controller;

import com.assist.control.assistcontrolbackend.exception.ResourceNotFoundException;
import com.assist.control.assistcontrolbackend.model.Position;
import com.assist.control.assistcontrolbackend.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping("/positions")
    public List<Position> listPositions() {
        return positionRepository.findAll();
    }

    @PostMapping("/positions")
    public Position savePosition(@RequestBody Position position) {
        return positionRepository.save(position);
    }

    @GetMapping("/positions/{id}")
    public ResponseEntity<Position> listPositionById(@PathVariable Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position id: " + id + " doesn't exist"));
        return ResponseEntity.ok(position);
    }

    @PutMapping("/positions/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable Long id, @RequestBody Position positionRequest) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position id: " + id + " doesn't exist"));

        position.setName(positionRequest.getName());

        Position updatedPosition = positionRepository.save(position);
        return ResponseEntity.ok(updatedPosition);
    }

    @DeleteMapping("/positions/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePosition(@PathVariable Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position id: " + id + " doesn't exist"));

        positionRepository.delete(position);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}