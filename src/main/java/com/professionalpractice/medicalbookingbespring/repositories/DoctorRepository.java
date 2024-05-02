package com.professionalpractice.medicalbookingbespring.repositories;

import com.professionalpractice.medicalbookingbespring.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
