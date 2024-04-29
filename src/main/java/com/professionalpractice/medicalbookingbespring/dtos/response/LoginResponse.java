package com.professionalpractice.medicalbookingbespring.dtos.response;

import com.professionalpractice.medicalbookingbespring.dtos.UserDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {

    UserDto user;

    String token;
}
