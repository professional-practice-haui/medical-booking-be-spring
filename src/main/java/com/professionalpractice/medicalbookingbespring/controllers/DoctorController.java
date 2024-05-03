package com.professionalpractice.medicalbookingbespring.controllers;

import java.util.List;

import com.professionalpractice.medicalbookingbespring.dtos.request.DoctorRequest;
import com.professionalpractice.medicalbookingbespring.dtos.response.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createDoctor(@RequestBody DoctorRequest doctorRequest) {
        DoctorDTO doctor = doctorService.createDoctor(doctorRequest);
        return CustomResponse.success(HttpStatus.CREATED,"Tạo bác sĩ mới thành công",doctor);
    }

    @GetMapping("/doctors")
    public ResponseEntity<?> getDoctors(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(
            page, limit,
            Sort.by("id").ascending());
        Page<DoctorDTO> doctorPage = doctorService.getDoctors(pageRequest);
        long totalPages = doctorPage.getTotalElements();
        List<DoctorDTO> doctors = doctorPage.getContent();
        return CustomResponse.success(new PaginationResponse(page, limit, totalPages, doctors));
    }

    @PutMapping("/doctors/{doctorId}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long doctorId,
            @RequestBody DoctorRequest doctorRequest) {
        DoctorDTO doctor = doctorService.updateDoctor(doctorId, doctorRequest);
        return CustomResponse.success("Cập nhật thành công",doctor);
    }

    @DeleteMapping("/doctors/{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
        return CustomResponse.success("Xóa thành công");
    }
}
