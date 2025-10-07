package com.abc.smartclinic.repository;

import com.abc.smartclinic.entity.Appointment;
import com.abc.smartclinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndAppointmentTimeBetween(Doctor doctor, LocalDateTime from, LocalDateTime to);
}
