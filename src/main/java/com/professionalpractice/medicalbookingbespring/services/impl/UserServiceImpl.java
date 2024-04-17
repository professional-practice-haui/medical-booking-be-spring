package com.professionalpractice.medicalbookingbespring.services.impl;

import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.entities.User;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.UserRepository;
import com.professionalpractice.medicalbookingbespring.security.JwtTokenUtil;
import com.professionalpractice.medicalbookingbespring.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(modelMapper.map(user, UserDto.class));
        }
        return userDTOs;
    }

    @Override
    public UserDto createUser(User userBody) {
        Optional<User> user = userRepository.findByEmail(userBody.getEmail());
        if (user.isPresent()) {
            throw new DataIntegrityViolationException("Email đã tồn tại");
        }

        String hashPassword = BCrypt.hashpw(userBody.getPassword(), BCrypt.gensalt(10));
        userBody.setPassword(hashPassword);

        userBody.setRoles(Collections.singletonList("USER"));
        userBody.setCreatedDate(LocalDateTime.now());
        userBody.setLastModifiedDate(LocalDateTime.now());

        userRepository.save(userBody);
        return modelMapper.map(userBody, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        return modelMapper.map(user, UserDto.class);
    }


}
