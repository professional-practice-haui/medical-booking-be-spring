package com.professionalpractice.medicalbookingbespring.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.professionalpractice.medicalbookingbespring.dtos.HealthFormDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.HealthFormRequest;

public interface HealthFormService {

    HealthFormDTO createHealthForm(HealthFormRequest healthFormRequest);

    Page<HealthFormDTO> getHealthFormByUserId(Long userId, PageRequest pageRequest);

    Page<HealthFormDTO> getHealthForms(PageRequest pageRequest);

    HealthFormDTO updateHealthForm(Long healthFormId, HealthFormRequest healthFormRequest);

    void deleteHealthFormById(Long healthFormId);

    Page<HealthFormDTO> getHistory(String userEmail, PageRequest pageRequest);

    Page<HealthFormDTO> getHealthFormByStatus(String status, PageRequest pageRequest);

    HealthFormDTO updateStatusOfHealthForm(Long heathFormId, HealthFormRequest healthFormRequest);
}
