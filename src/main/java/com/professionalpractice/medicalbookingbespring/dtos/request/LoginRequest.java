package com.professionalpractice.medicalbookingbespring.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Yêu cầu nhập email")
    @Email(message = "Yêu cầu nhập đúng định dạng email")
    String email;

    @NotBlank(message = "Yêu cầu nhập mật khẩu")
    String password;
}
