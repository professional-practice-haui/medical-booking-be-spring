package com.professionalpractice.medicalbookingbespring.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.professionalpractice.medicalbookingbespring.entities.HealthForm;

@Repository
public interface HealthFormRepository extends JpaRepository<HealthForm, Long>, JpaSpecificationExecutor<HealthForm> {

    @Query("SELECT u FROM HealthForm u")
    Page<HealthForm> queryHealthForm(Pageable pageable);

    @Query("SELECT u FROM HealthForm u WHERE u.user.id = ?1")
    Page<HealthForm> queryHealthForm(Long id, Pageable pageable);

    @Query("SELECT u FROM HealthForm u WHERE u.status = ?1")
    Page<HealthForm> queryHealthFormByStatus(Integer status, Pageable pageable);

    @Query("SELECT COUNT(u) FROM HealthForm u WHERE u.shift.id = ?1 AND u.acceptedNumber = 1")
    int countByShiftId(Long shiftId);
}
