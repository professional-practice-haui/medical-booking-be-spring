package com.professionalpractice.medicalbookingbespring.dtos.request;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String fullName;

    String address;

    String phoneNumber;

    String gender;

    String dateOfBirth;

    String password;

    Boolean isLocked;

    List<String> roles;
}
