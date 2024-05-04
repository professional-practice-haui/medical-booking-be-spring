package com.professionalpractice.medicalbookingbespring.services;

public interface EmailService {
    void sendMessage(String to, String subject, String text);
}
