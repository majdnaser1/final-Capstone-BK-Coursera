package com.abc.smartclinic.controller;

import com.abc.smartclinic.service.AppointmentService;
import com.abc.smartclinic.service.DoctorService;
import com.abc.smartclinic.service.TokenService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final TokenService tokenService;

    public DoctorController(DoctorService doctorService, AppointmentService appointmentService, TokenService tokenService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.tokenService = tokenService;
    }

    @GetMapping("/availability")
    public ResponseEntity<?> getAvailability(@RequestHeader(name="Authorization", required=false) String auth,
                                             @RequestParam Long doctorId,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        if(auth == null || !auth.startsWith("Bearer ") || !tokenService.validate(auth.substring(7))){
            return ResponseEntity.status(401).body(Map.of("status","ERROR","message","Invalid or missing token"));
        }
        return ResponseEntity.ok(Map.of("status","OK","doctorId", doctorId, "date", date, "slots", doctorService.getAvailableSlotsForDate(doctorId, date)));
    }
}
