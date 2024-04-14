package com.professionalpractice.medicalbookingbespring.services.impl;

import com.professionalpractice.medicalbookingbespring.dtos.LoginDto;
import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.entities.User;
import com.professionalpractice.medicalbookingbespring.exceptions.BadRequestException;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.exceptions.UnauthorizedException;
import com.professionalpractice.medicalbookingbespring.repositories.UserRepository;
import com.professionalpractice.medicalbookingbespring.security.CustomUserDetails;
import com.professionalpractice.medicalbookingbespring.security.JwtTokenUtil;
import com.professionalpractice.medicalbookingbespring.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(LoginDto body) throws Exception {
        User user = userRepository.findByEmail(body.getEmail())
                .orElseThrow(() -> new BadRequestException("Email hoặc mật khẩu không chính xác"));

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        if(!passwordEncoder.matches(body.getPassword(),user.getPassword())){
            throw new BadRequestException("Email hoặc mật khẩu không chính xác");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword(), customUserDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(user);

    }


}
