package com.professionalpractice.medicalbookingbespring.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.dtos.request.UserRequest;
import com.professionalpractice.medicalbookingbespring.entities.User;

@Service
public interface UserService {
    Page<UserDto> getUsers(PageRequest pageRequest);

    UserDto createUser(User userBody);

    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);

    UserDto updateUserProfile(String userEmail, UserRequest userRequest);

    UserDto updateUserById(Long id, UserRequest userRequest);

    UserDto lockUserById(Long id);
}
