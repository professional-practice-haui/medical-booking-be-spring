package com.professionalpractice.medicalbookingbespring.repositories;

import com.professionalpractice.medicalbookingbespring.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
