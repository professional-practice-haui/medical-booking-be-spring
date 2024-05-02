package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.DepartmentDTO;
import com.professionalpractice.medicalbookingbespring.entities.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(DepartmentDTO departmentDTO);

    List<Department> getDepartments();

    Department updateDepartment(Long departmentId, DepartmentDTO departmentDTO);

    void deleteDepartment(Long departmentId);

}
