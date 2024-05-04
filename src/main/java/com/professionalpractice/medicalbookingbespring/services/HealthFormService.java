package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.HealthFormDTO;
import com.professionalpractice.medicalbookingbespring.dtos.ShiftDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.HealthFormRequest;
import com.professionalpractice.medicalbookingbespring.dtos.request.ShiftRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface HealthFormService {

    HealthFormDTO createHealthForm(HealthFormRequest healthFormRequest);

    Page<HealthFormDTO> getHealthFormByUserId(Long userId,PageRequest pageRequest);

    Page<HealthFormDTO> getHealthForms(PageRequest pageRequest);

    HealthFormDTO updateHealthForm(Long healthFormId, HealthFormRequest healthFormRequest);

    void deleteHealthFormById(Long healthFormId);
}
