package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.DoctorDTO;
import com.professionalpractice.medicalbookingbespring.entities.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(DoctorDTO doctorDTO);

    List<Doctor> getDoctors();

    Doctor updateDoctor(Long doctorId, DoctorDTO doctorDTO);

    void deleteDoctor(Long doctorId);
}
