package com.professionalpractice.medicalbookingbespring.controllers;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.DoctorDTO;
import com.professionalpractice.medicalbookingbespring.entities.Doctor;
import com.professionalpractice.medicalbookingbespring.services.DoctorService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
