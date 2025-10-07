package com.abc.smartclinic.service;

import com.abc.smartclinic.entity.Doctor;
import com.abc.smartclinic.repository.DoctorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) { this.doctorRepository = doctorRepository; }

    public Optional<Doctor> findById(Long id){ return doctorRepository.findById(id); }

    public List<LocalDateTime> getAvailableSlotsForDate(Long doctorId, LocalDate date){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        List<LocalDateTime> slots = new ArrayList<>();
        for(LocalTime t : doctor.getAvailableTimes()){
            slots.add(LocalDateTime.of(date, t));
        }
        return slots;
    }

    public ResponseEntity<Map<String, Object>> authenticateDoctor(String email, String password){
        return doctorRepository.findByEmail(email)
                .filter(d -> Objects.equals(d.getPassword(), password))
                .map(d -> ResponseEntity.ok(Map.of("status","OK","doctorId", d.getId(),"speciality", d.getSpeciality())))
                .orElse(ResponseEntity.status(401).body(Map.of("status","ERROR","message","Invalid credentials")));
    }
}
