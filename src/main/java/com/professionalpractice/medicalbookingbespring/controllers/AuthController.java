package com.professionalpractice.medicalbookingbespring.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.dtos.request.LoginRequest;
import com.professionalpractice.medicalbookingbespring.dtos.request.UserRequest;
import com.professionalpractice.medicalbookingbespring.dtos.response.LoginResponse;
import com.professionalpractice.medicalbookingbespring.services.AuthService;
import com.professionalpractice.medicalbookingbespring.services.UserService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestApiV1
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequest userRequest,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return CustomResponse.error(HttpStatus.BAD_REQUEST, errorMessages);
        }
        UserDto user = userService.createUser(userRequest);
        return CustomResponse.success(HttpStatus.CREATED, "Đăng ký thành công", user);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return CustomResponse.error(HttpStatus.BAD_REQUEST, errorMessages);
        }
        LoginResponse loginResponseDto = authService.login(loginRequest);
        return CustomResponse.success(loginResponseDto);
    }

    @GetMapping("/auth/token")
    public ResponseEntity<?> loginWithToken() {
        LoginResponse loginResponseDto = authService.loginWithToken();
        return CustomResponse.success(loginResponseDto);
    }
}
