package com.abc.smartclinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email @Column(unique = true)
    private String email;

    @NotBlank
    private String speciality;

    @NotBlank
    private String password;

    @ElementCollection
    @CollectionTable(name = "doctor_available_times", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "time_slot")
    private List<LocalTime> availableTimes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSpeciality() { return speciality; }
    public void setSpeciality(String speciality) { this.speciality = speciality; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<LocalTime> getAvailableTimes() { return availableTimes; }
    public void setAvailableTimes(List<LocalTime> availableTimes) { this.availableTimes = availableTimes; }
}
