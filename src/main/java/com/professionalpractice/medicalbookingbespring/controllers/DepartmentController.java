package com.professionalpractice.medicalbookingbespring.controllers;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.DepartmentDTO;
import com.professionalpractice.medicalbookingbespring.entities.Department;
import com.professionalpractice.medicalbookingbespring.services.DepartmentService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestApiV1
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/departments")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department department = departmentService.createDepartment(departmentDTO);
        return CustomResponse.success(department);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<?>> getDepartments() {
        List<Department> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/departments/{departmentId}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long departmentId,
                                              @RequestBody DepartmentDTO departmentDTO) {
        Department department = departmentService.updateDepartment(departmentId, departmentDTO);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/departments/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Xóa thành công");
    }
}
