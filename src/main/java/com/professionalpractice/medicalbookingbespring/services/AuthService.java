package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.LoginDto;
import com.professionalpractice.medicalbookingbespring.dtos.UserDto;

public interface AuthService {

    String login (LoginDto body) throws Exception;

}
