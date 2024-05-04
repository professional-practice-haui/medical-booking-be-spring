package com.professionalpractice.medicalbookingbespring.dtos;

import com.professionalpractice.medicalbookingbespring.entities.Shift;
import com.professionalpractice.medicalbookingbespring.entities.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HealthFormDTO {

    Long id;

    User user;

    String namePatient;

    String email;

    String phoneNumber;

    Shift shift;

    String reason;

    String cccd;

    String bhyt;

    String deniedReason;
}
