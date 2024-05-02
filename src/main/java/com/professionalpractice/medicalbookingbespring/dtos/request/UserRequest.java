package com.professionalpractice.medicalbookingbespring.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {


    String fullName;

    @NotBlank(message = "Yêu cầu nhập email")
    @Email(message = "Yêu cầu nhập đúng định dạng email")
    String email;

    String address;

    String phoneNumber;

    String gender;

    String dateOfBirth;

    @NotBlank(message = "Yêu cầu nhập mật khẩu")
    String password;

    Boolean isLocked;

    String avatar;

    List<String> roles;
}
