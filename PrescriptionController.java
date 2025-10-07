package com.abc.smartclinic.controller;

import com.abc.smartclinic.entity.Prescription;
import com.abc.smartclinic.repository.PrescriptionRepository;
import com.abc.smartclinic.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;
    private final TokenService tokenService;

    public PrescriptionController(PrescriptionRepository prescriptionRepository, TokenService tokenService) {
        this.prescriptionRepository = prescriptionRepository;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestHeader("Authorization") String auth, @Valid @RequestBody Prescription body){
        if(auth == null || !auth.startsWith("Bearer ") || !tokenService.validate(auth.substring(7))){
            return ResponseEntity.status(401).body(Map.of("status","ERROR","message","Invalid or missing token"));
        }
        Prescription saved = prescriptionRepository.save(body);
        return ResponseEntity.ok(Map.of("status","OK","id", saved.getId()));
    }
}
