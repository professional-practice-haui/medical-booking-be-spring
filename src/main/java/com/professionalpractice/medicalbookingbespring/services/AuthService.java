package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.LoginDto;
import com.professionalpractice.medicalbookingbespring.dtos.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginDto body);

    LoginResponseDto loginWithToken();
}
