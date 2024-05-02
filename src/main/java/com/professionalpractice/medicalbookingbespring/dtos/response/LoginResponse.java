package com.professionalpractice.medicalbookingbespring.dtos.response;

import com.professionalpractice.medicalbookingbespring.dtos.UserDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {

    UserDTO user;

    String token;
}
