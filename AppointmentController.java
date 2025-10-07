package com.abc.smartclinic.controller;

import com.abc.smartclinic.entity.Appointment;
import com.abc.smartclinic.service.AppointmentService;
import com.abc.smartclinic.service.TokenService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final TokenService tokenService;

    public AppointmentController(AppointmentService appointmentService, TokenService tokenService) {
        this.appointmentService = appointmentService;
        this.tokenService = tokenService;
    }

    @PostMapping("/book")
    public ResponseEntity<?> book(@RequestHeader("Authorization") String auth,
                                  @RequestParam Long doctorId,
                                  @RequestParam Long patientId,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime when){
        if(auth == null || !auth.startsWith("Bearer ") || !tokenService.validate(auth.substring(7))){
            return ResponseEntity.status(401).body(Map.of("status","ERROR","message","Invalid or missing token"));
        }
        Appointment a = appointmentService.bookAppointment(doctorId, patientId, when);
        return ResponseEntity.ok(Map.of("status","OK","appointmentId", a.getId()));
    }

    @GetMapping("/doctor-day")
    public ResponseEntity<?> doctorDay(@RequestHeader("Authorization") String auth,
                                       @RequestParam Long doctorId,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        if(auth == null || !auth.startsWith("Bearer ") || !tokenService.validate(auth.substring(7))){
            return ResponseEntity.status(401).body(Map.of("status","ERROR","message","Invalid or missing token"));
        }
        List<Appointment> list = appointmentService.getAppointmentsForDoctorOnDate(doctorId, date);
        return ResponseEntity.ok(Map.of("status","OK","count", list.size(), "items", list));
    }
}
