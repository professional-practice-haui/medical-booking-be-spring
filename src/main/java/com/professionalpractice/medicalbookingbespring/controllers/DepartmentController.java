package com.professionalpractice.medicalbookingbespring.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.DepartmentDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.DepartmentRequest;
import com.professionalpractice.medicalbookingbespring.dtos.response.PaginationResponse;
import com.professionalpractice.medicalbookingbespring.services.CloudinaryService;
import com.professionalpractice.medicalbookingbespring.services.DepartmentService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestApiV1
public class DepartmentController {

    private final DepartmentService departmentService;

    private final CloudinaryService cloudinaryService;

    @PostMapping("/departments")
    public ResponseEntity<?> createDepartment(@ModelAttribute DepartmentRequest departmentRequest,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        if (imageFile != null) {
            String imageUrl = cloudinaryService.uploadImage(imageFile);
            departmentRequest.setImageUrl(imageUrl);
        }

        DepartmentDTO department = departmentService.createDepartment(departmentRequest);
        return CustomResponse.success(HttpStatus.CREATED, "Tạo chuyên khoa thành công", department);
    }

    @GetMapping("/departments")
    public ResponseEntity<?> getDepartments(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(
                page - 1,
                limit,
                Sort.by("id").ascending());
        Page<DepartmentDTO> departmentPage = departmentService.getDepartments(pageRequest);
        long totalPages = departmentPage.getTotalElements();
        List<DepartmentDTO> departments = departmentPage.getContent();
        return CustomResponse.success(new PaginationResponse(page, limit, totalPages, departments));
    }

    @PutMapping("/departments/{departmentId}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long departmentId,
            @ModelAttribute DepartmentRequest departmentRequest,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        if (imageFile != null) {
            String imageUrl = cloudinaryService.uploadImage(imageFile);
            departmentRequest.setImageUrl(imageUrl);
        }

        DepartmentDTO department = departmentService.updateDepartment(departmentId, departmentRequest);
        return CustomResponse.success("Cập nhật thành công", department);
    }

    @DeleteMapping("/departments/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return CustomResponse.success("Xóa thành công");
    }
}
