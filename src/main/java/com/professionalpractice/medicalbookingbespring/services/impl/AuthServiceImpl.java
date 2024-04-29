package com.professionalpractice.medicalbookingbespring.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.dtos.request.LoginRequest;
import com.professionalpractice.medicalbookingbespring.dtos.response.LoginResponse;
import com.professionalpractice.medicalbookingbespring.entities.User;
import com.professionalpractice.medicalbookingbespring.exceptions.BadRequestException;
import com.professionalpractice.medicalbookingbespring.exceptions.UnauthorizedException;
import com.professionalpractice.medicalbookingbespring.repositories.UserRepository;
import com.professionalpractice.medicalbookingbespring.security.CustomUserDetails;
import com.professionalpractice.medicalbookingbespring.security.JwtTokenUtil;
import com.professionalpractice.medicalbookingbespring.services.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public LoginResponse login(LoginRequest body) {
        User user = userRepository.findByEmail(body.getEmail())
                .orElseThrow(() -> new BadRequestException("Email hoặc mật khẩu không chính xác"));

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        if (!passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            throw new BadRequestException("Email hoặc mật khẩu không chính xác");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                body.getEmail(), body.getPassword(), customUserDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        String token = jwtTokenUtil.generateToken(user);

        return new LoginResponse(userDto, token);
    }

    @Override
    public LoginResponse loginWithToken() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Xác thực thất bại hoặc người dùng chưa đăng nhập");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            User user = userDetails.getUser();

            UserDto userDto = modelMapper.map(user, UserDto.class);
            String token = jwtTokenUtil.generateToken(user);

            return new LoginResponse(userDto, token);
        } else {
            throw new UnauthorizedException("Người dùng không hợp lệ");
        }
    }
}
