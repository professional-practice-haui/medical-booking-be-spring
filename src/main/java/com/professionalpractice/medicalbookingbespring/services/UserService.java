package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.UserDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Page<UserDTO> getUsers(PageRequest pageRequest);

    UserDTO createUser(UserRequest userRequest);

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);

    UserDTO updateUserProfile(String userEmail, UserRequest userRequest);

    UserDTO updateUserById(Long id, UserRequest userRequest);

    UserDTO lockUserById(Long id);

    UserDTO deleteUserById(Long id);
}
