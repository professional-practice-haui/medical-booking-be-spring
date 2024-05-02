package com.professionalpractice.medicalbookingbespring.services.impl;

import com.professionalpractice.medicalbookingbespring.dtos.DepartmentDTO;
import com.professionalpractice.medicalbookingbespring.entities.Department;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.DepartmentRepository;
import com.professionalpractice.medicalbookingbespring.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(DepartmentDTO departmentDTO) {
        Department department = Department.builder()
            .name(departmentDTO.getName())
            .nameLeader(departmentDTO.getNameLeader())
            .description(departmentDTO.getDescription())
            .build();
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new NotFoundException("Chuyên khoa này không tồn tại"));
        department.setName(departmentDTO.getName());
        department.setNameLeader(departmentDTO.getNameLeader());
        department.setDescription(departmentDTO.getDescription());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
