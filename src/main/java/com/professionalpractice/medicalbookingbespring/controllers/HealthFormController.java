package com.professionalpractice.medicalbookingbespring.controllers;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.HealthFormDTO;
import com.professionalpractice.medicalbookingbespring.dtos.ShiftDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.HealthFormRequest;
import com.professionalpractice.medicalbookingbespring.dtos.request.ShiftRequest;
import com.professionalpractice.medicalbookingbespring.dtos.response.PaginationResponse;
import com.professionalpractice.medicalbookingbespring.services.HealthFormService;
import com.professionalpractice.medicalbookingbespring.services.ShiftService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestApiV1
public class HealthFormController {

    private final HealthFormService healthFormService;

    @PostMapping("/healthForms")
    public ResponseEntity<?> createHealthForms(@RequestBody HealthFormRequest healthFormRequest) {
        HealthFormDTO healthForm = healthFormService.createHealthForm(healthFormRequest);
        return CustomResponse.success(HttpStatus.CREATED,"Tạo ca làm việc thành công",healthForm);
    }

    @GetMapping("/healthForms")
    public ResponseEntity<?> getHealthForms(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(
            page-1, limit,
            Sort.by("id").ascending());
        Page<HealthFormDTO> healthFormPage = healthFormService.getHealthForms(pageRequest);
        long totalPages = healthFormPage.getTotalElements();
        List<HealthFormDTO> healthForms = healthFormPage.getContent();
        return CustomResponse.success(new PaginationResponse(page, limit, totalPages, healthForms));
    }

    @GetMapping("/healthForms/{userId}")
    public ResponseEntity<?> getHealthFormsByUserId(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int limit,
                                       @PathVariable Long userId) {
        PageRequest pageRequest = PageRequest.of(
            page-1, limit,
            Sort.by("id").ascending());
        Page<HealthFormDTO> healthFormPage = healthFormService.getHealthFormByUserId(userId,pageRequest);
        long totalPages = healthFormPage.getTotalElements();
        List<HealthFormDTO> healthForms = healthFormPage.getContent();
        return CustomResponse.success(new PaginationResponse(page, limit, totalPages, healthForms));
    }

    @PutMapping("/healthForms/{healthFormId}")
    public ResponseEntity<?> updateHealthForm(@PathVariable Long healthFormId,
                                         @RequestBody HealthFormRequest healthFormRequest) {
        HealthFormDTO healthForm = healthFormService.updateHealthForm(healthFormId, healthFormRequest);
        return CustomResponse.success("Cập nhật thành công");
    }

    @DeleteMapping("/healthForms/{healthFormId}")
    public ResponseEntity<?> deleteHealthForm(@PathVariable Long healthFormId) {
        healthFormService.deleteHealthFormById(healthFormId);
        return CustomResponse.success("Xóa thành công");
    }
}
