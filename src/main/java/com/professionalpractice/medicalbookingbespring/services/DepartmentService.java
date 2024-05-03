package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.DepartmentDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.DepartmentRequest;
import com.professionalpractice.medicalbookingbespring.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentRequest departmentRequest);

    Page<DepartmentDTO> getDepartments(PageRequest pageRequest);

    DepartmentDTO updateDepartment(Long departmentId, DepartmentRequest departmentRequest);

    void deleteDepartment(Long departmentId);

}
