package com.professionalpractice.medicalbookingbespring.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentRequest {

    Long id;

    @NotBlank(message = "Yêu cầu nhập tên chuyên khoa")
    String name;

    String nameLeader;

    String description;
}
