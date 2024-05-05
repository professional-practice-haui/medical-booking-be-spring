package com.professionalpractice.medicalbookingbespring.repositories;

import com.professionalpractice.medicalbookingbespring.entities.HealthForm;
import com.professionalpractice.medicalbookingbespring.entities.Shift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthFormRepository extends JpaRepository<HealthForm, Long>{

    @Query("SELECT u FROM HealthForm u")
    Page<HealthForm> queryHealthForm(Pageable pageable);

    @Query("SELECT u FROM HealthForm u WHERE u.user.id = ?1")
    Page<HealthForm> queryHealthForm(Long id, Pageable pageable);

    int countByShiftId(Long shiftId);
}
