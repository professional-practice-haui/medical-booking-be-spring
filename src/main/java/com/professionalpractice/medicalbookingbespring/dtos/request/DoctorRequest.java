package com.professionalpractice.medicalbookingbespring.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRequest {

    Long id;

    @NotBlank(message = "Yêu cầu nhập tên bác sĩ")
    String name;

    String gender;

    String degree;

    int experience;

    String avatar;

    String description;

    @NotBlank(message = "Yêu cầu nhập mã chuyên khoa")
    Long departmentId;
}
