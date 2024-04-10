package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.entities.User;
//import com.professionalpractice.medicalbookingbespring.validations.CreateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
//    User createUser(CreateUserRequest createUserRequest);
    List<UserDto> getUsers();

    UserDto createUser(User userBody);

    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);
}
