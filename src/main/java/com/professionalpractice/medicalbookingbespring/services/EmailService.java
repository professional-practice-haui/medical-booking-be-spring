package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.HealthFormDTO;

public interface EmailService {
    void sendMessage(String to, String subject, String content);

    void sendHealthFormConfirmation(HealthFormDTO healthFormDto);

    void sendHealthFormRejection(HealthFormDTO healthFormDto);
}
