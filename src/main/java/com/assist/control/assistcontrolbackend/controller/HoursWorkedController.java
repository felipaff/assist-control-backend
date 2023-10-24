package com.assist.control.assistcontrolbackend.controller;

import com.assist.control.assistcontrolbackend.model.HoursWorked;
import com.assist.control.assistcontrolbackend.service.HoursWorkedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class HoursWorkedController {

    @Autowired
    private HoursWorkedService hoursWorkedService;

    @GetMapping("/hours-worked/{id}")
    public List<HoursWorked> getHoursWorkedByEmployeeId(@PathVariable Long id) {
        List<HoursWorked> hoursWorkedList = hoursWorkedService.getHoursWorkedByEmployeeId(id);
        return hoursWorkedList;
    }

    @PostMapping("/hours-worked")
    public ResponseEntity<HoursWorked> createHoursWorked(@RequestBody HoursWorked hoursWorked, @RequestParam Long employeeId) {
        return new ResponseEntity<>(hoursWorkedService.createHoursWorked(hoursWorked, employeeId), HttpStatus.CREATED);
    }

    @PutMapping("/hours-worked/{id}")
    public ResponseEntity<HoursWorked> updateHoursWorked(@PathVariable Long id, @RequestBody HoursWorked updatedHoursWorked) {
        return new ResponseEntity<>(hoursWorkedService.updateHoursWorked(id, updatedHoursWorked), HttpStatus.OK);
    }

    @DeleteMapping("/hours-worked/{id}")
    public ResponseEntity<Void> deleteHoursWorked(@PathVariable Long id) {
        hoursWorkedService.deleteHoursWorked(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
