package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.request.LoginRequest;
import com.professionalpractice.medicalbookingbespring.dtos.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest body);

    LoginResponse loginWithToken();
}
