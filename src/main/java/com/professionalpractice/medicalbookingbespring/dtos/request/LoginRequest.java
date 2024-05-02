package com.professionalpractice.medicalbookingbespring.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class LoginRequest {

    @NotBlank(message = "Yêu cầu nhập email")
    @Email(message = "Yêu cầu nhập đúng định dạng email")
    String email;

    @NotBlank(message = "Yêu cầu nhập mật khẩu")
    @Size(min = 7, message = "Mật khẩu cần nhiều hơn 7 kí tự")
    String password;
}
