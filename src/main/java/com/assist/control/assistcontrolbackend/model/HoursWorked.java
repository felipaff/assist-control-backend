package com.assist.control.assistcontrolbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "hours_worked")
public class HoursWorked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private Long employee;

    @Column(name = "date_worked")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dateWorked;

    @Column(name = "hours")
    private double hours;

    @Column(name = "extra_hours")
    private double extraHours;

    public double getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(double extraHours) {
        this.extraHours = extraHours;
    }
    public double getHours() {
        return hours;
    }

    public void setHours(double extraHours) {
        this.hours = extraHours;
    }

    public void setEmployee(Long employeeId) {
        this.employee = employeeId;
    }
}
