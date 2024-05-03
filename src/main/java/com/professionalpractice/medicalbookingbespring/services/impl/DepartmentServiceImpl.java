package com.professionalpractice.medicalbookingbespring.services.impl;

import com.professionalpractice.medicalbookingbespring.dtos.DepartmentDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.DepartmentRequest;
import com.professionalpractice.medicalbookingbespring.entities.Department;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.DepartmentRepository;
import com.professionalpractice.medicalbookingbespring.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Override
    public DepartmentDTO createDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
            .name(departmentRequest.getName())
            .nameLeader(departmentRequest.getNameLeader())
            .description(departmentRequest.getDescription())
            .build();
        Department saveDepartment = departmentRepository.save(department);
        return modelMapper.map(saveDepartment,DepartmentDTO.class);
    }

    @Override
    public Page<DepartmentDTO> getDepartments(PageRequest pageRequest) {

        Page<Department> departmentPage = departmentRepository.queryDepartments(pageRequest);
        return departmentPage.map(department -> modelMapper.map(department,DepartmentDTO.class));
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentRequest departmentRequest) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new NotFoundException("Chuyên khoa này không tồn tại"));
        department.setName(departmentRequest.getName());
        department.setNameLeader(departmentRequest.getNameLeader());
        department.setDescription(departmentRequest.getDescription());
        Department saveDepartment = departmentRepository.save(department);
        return modelMapper.map(saveDepartment,DepartmentDTO.class);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
