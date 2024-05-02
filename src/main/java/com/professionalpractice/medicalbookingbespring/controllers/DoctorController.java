package com.professionalpractice.medicalbookingbespring.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.DoctorDTO;
import com.professionalpractice.medicalbookingbespring.entities.Doctor;
import com.professionalpractice.medicalbookingbespring.services.DoctorService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestApiV1
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/doctors")
    public ResponseEntity<?> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorService.createDoctor(doctorDTO);
        return CustomResponse.success(doctor);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<?>> getDoctors() {
        List<Doctor> doctors = doctorService.getDoctors();
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/doctors/{doctorId}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long doctorId,
            @RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorService.updateDoctor(doctorId, doctorDTO);
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("/doctors/{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok("Xóa thành công");
    }
}
