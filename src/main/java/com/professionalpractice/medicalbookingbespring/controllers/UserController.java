package com.professionalpractice.medicalbookingbespring.controllers;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.LoginDto;
import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.entities.User;
import com.professionalpractice.medicalbookingbespring.services.UserService;
//import com.professionalpractice.medicalbookingbespring.validations.CreateUserRequest;
//import jakarta.validation.Valid;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;
import com.professionalpractice.medicalbookingbespring.utils.RestData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestApiV1
public class UserController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<UserDto> userDTOs = userService.getUsers();

        return CustomResponse.success(userDTOs);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User userBody) {
        UserDto user = userService.createUser(userBody);

        return CustomResponse.success(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);

        return CustomResponse.success(user);
    }


}
