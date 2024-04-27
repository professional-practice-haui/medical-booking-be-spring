package com.professionalpractice.medicalbookingbespring.services;

import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
//    User createUser(CreateUserRequest createUserRequest);
//    List<UserDto> getUsers();

    Page<User> getUsers(PageRequest pageRequest);

    UserDto createUser(User userBody);

    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);


}
