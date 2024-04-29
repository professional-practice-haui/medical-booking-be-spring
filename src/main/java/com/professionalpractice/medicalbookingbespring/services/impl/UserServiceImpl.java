package com.professionalpractice.medicalbookingbespring.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
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

    @Override
    public Page<UserDto> getUsers(PageRequest pageRequest) {

        Page<User> usersPage = userRepository.queryUsers(pageRequest);
        return usersPage.map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public UserDto createUser(User userBody) {
        Optional<User> user = userRepository.findByEmail(userBody.getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("Email đã tồn tại");
        }

        String hashPassword = BCrypt.hashpw(userBody.getPassword(), BCrypt.gensalt(10));
        userBody.setPassword(hashPassword);

        if (userBody.getGender() == null) {
            userBody.setGender(GenderName.OTHER);
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
}
