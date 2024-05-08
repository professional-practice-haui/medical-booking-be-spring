package com.professionalpractice.medicalbookingbespring.dtos;

import com.professionalpractice.medicalbookingbespring.entities.Shift;

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
public class HealthFormDTO {

    Long id;

    UserDto user;

    String namePatient;

    String email;

    String phoneNumber;

    String address;

    Shift shift;

    String reason;

    String cccd;

    String bhyt;

    String deniedReason;

    Integer status;

    Integer acceptedNumber;
}
