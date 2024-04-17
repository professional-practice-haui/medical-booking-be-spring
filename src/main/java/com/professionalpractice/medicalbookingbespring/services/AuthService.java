package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.LoginDto;

public interface AuthService {

    String login(LoginDto body);

}
