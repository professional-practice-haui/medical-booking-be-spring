package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.DoctorDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.DoctorRequest;
import com.professionalpractice.medicalbookingbespring.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DoctorService {

    DoctorDTO createDoctor(DoctorRequest doctorRequest);

    Page<DoctorDTO> getDoctors(PageRequest pageRequest);

    DoctorDTO updateDoctor(Long doctorId, DoctorRequest doctorRequest);

    void deleteDoctor(Long doctorId);
}
