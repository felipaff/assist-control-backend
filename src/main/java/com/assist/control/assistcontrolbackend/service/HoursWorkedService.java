package com.assist.control.assistcontrolbackend.service;

import com.assist.control.assistcontrolbackend.model.HoursWorked;
import com.assist.control.assistcontrolbackend.repository.HoursWorkedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoursWorkedService {

    private final HoursWorkedRepository hoursWorkedRepository;

    @Autowired
    public HoursWorkedService(HoursWorkedRepository hoursWorkedRepository) {
        this.hoursWorkedRepository = hoursWorkedRepository;
    }


    public List<HoursWorked> getAllHoursWorked() {
        return hoursWorkedRepository.findAll();
    }

    public HoursWorked getHoursWorkedById(Long id) {
        return hoursWorkedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HoursWorked not found with id " + id));
    }

    public HoursWorked createHoursWorked(HoursWorked hoursWorked, Long employeeId) {
        // Assigns the employee's ID to HoursWorked
        hoursWorked.setEmployee(employeeId);

        // Saves the HoursWorked entity to the database
        return hoursWorkedRepository.save(hoursWorked);
    }

    public List<HoursWorked> getHoursWorkedByEmployeeId(Long employeeId) {
        List<HoursWorked> hoursWorkedList = hoursWorkedRepository.findByEmployee(employeeId);
        return hoursWorkedList;
    }


    public HoursWorked updateHoursWorked(Long id, HoursWorked updatedHoursWorked) {
        HoursWorked existingHoursWorked = getHoursWorkedById(id);
        //Update the fields of the entity with the values provided in updatedHoursWorked
        existingHoursWorked.setExtraHours(updatedHoursWorked.getExtraHours());
        existingHoursWorked.setHours(updatedHoursWorked.getHours());

        return hoursWorkedRepository.save(existingHoursWorked);
    }

    public void deleteHoursWorked(Long id) {
        hoursWorkedRepository.deleteById(id);
    }
}