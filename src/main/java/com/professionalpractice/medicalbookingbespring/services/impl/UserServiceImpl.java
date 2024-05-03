package com.professionalpractice.medicalbookingbespring.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.dtos.request.UserRequest;
import com.professionalpractice.medicalbookingbespring.entities.Role;
import com.professionalpractice.medicalbookingbespring.entities.User;
import com.professionalpractice.medicalbookingbespring.exceptions.BadRequestException;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.UserRepository;
import com.professionalpractice.medicalbookingbespring.services.UserService;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;
import com.professionalpractice.medicalbookingbespring.utils.RoleName;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDto> getUsers(PageRequest pageRequest) {

        Page<User> usersPage = userRepository.queryUsers(pageRequest);
        return usersPage.map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public UserDto createUser(UserRequest userRequest) {
        Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("Email đã tồn tại");
        }

        User newUser = new User();

        if (userRequest.getEmail() != null) {
            newUser.setEmail(userRequest.getEmail());
        }

        if (userRequest.getPassword() != null) {
            String hashPassword = passwordEncoder.encode(userRequest.getPassword());
            newUser.setPassword(hashPassword);
        }

        if (userRequest.getGender() == null) {
            newUser.setGender(GenderName.OTHER);
        }

        if (userRequest.getAddress() != null) {
            newUser.setAddress(userRequest.getAddress());
        }

        if (userRequest.getPhoneNumber() != null) {
            newUser.setPhoneNumber(userRequest.getPhoneNumber());
        }

        if (userRequest.getFullName() != null) {
            newUser.setFullName(userRequest.getFullName());
        }

        if (userRequest.getDateOfBirth() != null) {
            newUser.setDateOfBirth(LocalDate.parse(userRequest.getDateOfBirth()));
        }

        newUser.setRoles(new HashSet<>());
        newUser.getRoles().add(new Role(RoleName.USER));
        if (userRequest.getRoles() != null) {
            for (String roleName : userRequest.getRoles()) {
                newUser.getRoles().add(new Role(RoleName.valueOf(roleName)));
            }
        }

        newUser.setIsLocked(false);
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setLastModifiedDate(LocalDateTime.now());

        userRepository.save(newUser);
        return modelMapper.map(newUser, UserDto.class);
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

    @Override
    public UserDto updateUserProfile(String userEmail, UserRequest userRequest) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        if (userRequest.getFullName() != null) {
            user.setFullName(userRequest.getFullName());
        }
        if (userRequest.getGender() != null) {
            user.setGender(GenderName.valueOf(userRequest.getGender()));
        }
        if (userRequest.getDateOfBirth() != null) {
            user.setDateOfBirth(LocalDate.parse(userRequest.getDateOfBirth()));
        }
        if (userRequest.getAddress() != null) {
            user.setAddress(userRequest.getAddress());
        }
        if (userRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(userRequest.getPhoneNumber());
        }
        if (userRequest.getAvatarUrl() != null) {
            user.setAvatar(userRequest.getAvatarUrl());
        }

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUserById(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        if (userRequest.getFullName() != null) {
            user.setFullName(userRequest.getFullName());
        }
        if (userRequest.getGender() != null) {
            user.setGender(GenderName.valueOf(userRequest.getGender()));
        }
        if (userRequest.getDateOfBirth() != null) {
            user.setDateOfBirth(LocalDate.parse(userRequest.getDateOfBirth()));
        }
        if (userRequest.getAddress() != null) {
            user.setAddress(userRequest.getAddress());
        }
        if (userRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(userRequest.getPhoneNumber());
        }
        if (userRequest.getRoles() != null) {
            Set<Role> newRoles = new HashSet<Role>();
            for (String roleName : userRequest.getRoles()) {
                RoleName roleNameEnum = RoleName.valueOf(roleName);
                newRoles.add(new Role(roleNameEnum));
            }
            user.setRoles(newRoles);
        }
        if (userRequest.getIsLocked() != null) {
            user.setIsLocked(userRequest.getIsLocked());
        }
        if (userRequest.getPassword() != null) {
            String hashPassword = passwordEncoder.encode(userRequest.getPassword());
            user.setPassword(hashPassword);
        }

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto lockUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        user.setIsLocked(!user.getIsLocked());

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        userRepository.delete(user);
        return modelMapper.map(user, UserDto.class);
    }
}
