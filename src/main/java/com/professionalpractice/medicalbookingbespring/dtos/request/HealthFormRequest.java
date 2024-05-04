package com.professionalpractice.medicalbookingbespring.dtos.request;

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
public class HealthFormRequest {

    Long userId;

    String namePatient;

    String email;

    String phoneNumber;

    Long shift;

    String reason;

    String cccd;

    String bhyt;

    String deniedReason;
}
