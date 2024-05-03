package com.professionalpractice.medicalbookingbespring.services.impl;

import com.professionalpractice.medicalbookingbespring.dtos.DoctorDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.DoctorRequest;
import com.professionalpractice.medicalbookingbespring.entities.Department;
import com.professionalpractice.medicalbookingbespring.entities.Doctor;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.DepartmentRepository;
import com.professionalpractice.medicalbookingbespring.repositories.DoctorRepository;
import com.professionalpractice.medicalbookingbespring.services.DoctorService;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Override
    public DoctorDTO createDoctor(DoctorRequest doctorRequest) {
        Department exitstingDepartment = departmentRepository.findById(doctorRequest.getDepartmentId())
            .orElseThrow(() -> new NotFoundException("Không tìm thấy chuyên khoa này"));

        Doctor doctor = Doctor.builder()
            .name(doctorRequest.getName())
            .gender(checkGender(doctorRequest.getGender()))
            .degree(doctorRequest.getDegree())
            .experience(doctorRequest.getExperience())
            .avatar(doctorRequest.getAvatar())
            .description(doctorRequest.getDescription())
            .department(exitstingDepartment)
            .build();
        Doctor saveDoctor = doctorRepository.save(doctor);
        return modelMapper.map(saveDoctor,DoctorDTO.class);
    }


    @Override
    public Page<DoctorDTO> getDoctors(PageRequest pageRequest) {
        Page<Doctor> doctorPage = doctorRepository.queryDoctors(pageRequest);
        return doctorPage.map(doctor -> modelMapper.map(doctor,DoctorDTO.class));
    }

    @Override
    public DoctorDTO updateDoctor(Long doctorId, DoctorRequest doctorRequest) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy bác sĩ này!"));
        Department department = departmentRepository.findById(doctorRequest.getDepartmentId())
            .orElseThrow(() -> new NotFoundException("Không tìm thấy chuyên khoa này!"));
        doctor.setName(doctorRequest.getName());
        doctor.setDepartment(department);
        doctor.setAvatar(doctorRequest.getAvatar());
        doctor.setDegree(doctorRequest.getDegree());
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setGender(checkGender(doctorRequest.getGender()));
        doctor.setDescription(doctorRequest.getDescription());
        Doctor saveDoctor = doctorRepository.save(doctor);
        return modelMapper.map(saveDoctor,DoctorDTO.class);
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
