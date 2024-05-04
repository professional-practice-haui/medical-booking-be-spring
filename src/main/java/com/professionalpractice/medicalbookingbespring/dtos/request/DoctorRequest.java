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
public class DoctorRequest {

    Long id;

    String name;

    String gender;

    String degree;

    int experience;

    String imageUrl;

    String description;

    Long department;
}
