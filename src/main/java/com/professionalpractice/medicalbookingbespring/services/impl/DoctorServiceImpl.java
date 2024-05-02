package com.professionalpractice.medicalbookingbespring.services.impl;

import com.professionalpractice.medicalbookingbespring.dtos.DoctorDTO;
import com.professionalpractice.medicalbookingbespring.entities.Department;
import com.professionalpractice.medicalbookingbespring.entities.Doctor;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.DepartmentRepository;
import com.professionalpractice.medicalbookingbespring.repositories.DoctorRepository;
import com.professionalpractice.medicalbookingbespring.services.DoctorService;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public Doctor createDoctor(DoctorDTO doctorDTO) {
        Department exitstingDepartment = departmentRepository.findById(doctorDTO.getDepartmentId())
            .orElseThrow(() -> new NotFoundException("Không tìm thấy chuyên khoa này"));

        Doctor doctor = Doctor.builder()
            .name(doctorDTO.getName())
            .gender(checkGender(doctorDTO.getGender()))
            .degree(doctorDTO.getDegree())
            .experience(doctorDTO.getExperience())
            .avatar(doctorDTO.getAvatar())
            .description(doctorDTO.getDescription())
            .department(exitstingDepartment)
            .build();
        return doctorRepository.save(doctor);
    }


    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(Long doctorId, DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy bác sĩ này!"));
        Department department = departmentRepository.findById(doctorDTO.getDepartmentId())
            .orElseThrow(() -> new NotFoundException("Không tìm thấy chuyên khoa này!"));
        doctor.setName(doctorDTO.getName());
        doctor.setDepartment(department);
        doctor.setAvatar(doctorDTO.getAvatar());
        doctor.setDegree(doctorDTO.getDegree());
        doctor.setExperience(doctorDTO.getExperience());
        doctor.setGender(checkGender(doctorDTO.getGender()));
        doctor.setDescription(doctorDTO.getDescription());
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    private GenderName checkGender(String theGenderName) {
        for (GenderName genderName : GenderName.values()) {
            if (genderName.name().equals(theGenderName)) {
                return genderName;
            }
        }
        return null;
    }
}
