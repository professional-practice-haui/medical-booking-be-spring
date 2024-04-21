package com.professionalpractice.medicalbookingbespring.services.impl;

import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.entities.Role;
import com.professionalpractice.medicalbookingbespring.entities.User;
import com.professionalpractice.medicalbookingbespring.exceptions.BadRequestException;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.UserRepository;
import com.professionalpractice.medicalbookingbespring.services.UserService;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;
import com.professionalpractice.medicalbookingbespring.utils.RoleName;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDTOs = new ArrayList<>();
        users.forEach(user -> userDTOs.add(modelMapper.map(user, UserDto.class)));

        return userDTOs;
    }

    @Override
    public UserDto createUser(User userBody) {
        Optional<User> user = userRepository.findByEmail(userBody.getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("Email đã tồn tại");
        }

        String hashPassword = BCrypt.hashpw(userBody.getPassword(), BCrypt.gensalt(10));
        userBody.setPassword(hashPassword);

        if (userBody.getGenderName() == null) {
            userBody.setGenderName(GenderName.OTHER);
        }

        if (userBody.getRoles() == null) {
            userBody.setRoles(new HashSet<>());
        }

        boolean hasUserRole = userBody.getRoles().stream().anyMatch(role -> role.getRoleName().equals("USER"));
        if (!hasUserRole) {
            userBody.getRoles().add(new Role(RoleName.USER));
        }

        userBody.setIsLocked(false);
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
