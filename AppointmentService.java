package com.abc.smartclinic.service;

import com.abc.smartclinic.entity.Appointment;
import com.abc.smartclinic.entity.Doctor;
import com.abc.smartclinic.entity.Patient;
import com.abc.smartclinic.repository.AppointmentRepository;
import com.abc.smartclinic.repository.DoctorRepository;
import com.abc.smartclinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime when){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Appointment ap = new Appointment();
        ap.setDoctor(doctor);
        ap.setPatient(patient);
        ap.setAppointmentTime(when);
        return appointmentRepository.save(ap);
    }

    public List<Appointment> getAppointmentsForDoctorOnDate(Long doctorId, LocalDate date){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        LocalDateTime from = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime to = LocalDateTime.of(date, LocalTime.MAX);
        return appointmentRepository.findByDoctorAndAppointmentTimeBetween(doctor, from, to);
    }
}
