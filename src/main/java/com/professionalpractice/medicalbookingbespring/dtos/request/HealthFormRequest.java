package com.professionalpractice.medicalbookingbespring.dtos.request;

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
public class HealthFormRequest {

    Long userId;

    String namePatient;

    String email;

    String phoneNumber;

    String address;

    Long shift;

    String reason;

    String cccdUrl;

    String bhytUrl;

    String deniedReason;

    Integer status;
}
