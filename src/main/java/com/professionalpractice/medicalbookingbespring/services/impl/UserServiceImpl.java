package com.professionalpractice.medicalbookingbespring.services.impl;

import com.professionalpractice.medicalbookingbespring.dtos.UserDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.UserRequest;
import com.professionalpractice.medicalbookingbespring.entities.Role;
import com.professionalpractice.medicalbookingbespring.entities.User;
import com.professionalpractice.medicalbookingbespring.exceptions.BadRequestException;
import com.professionalpractice.medicalbookingbespring.exceptions.NotFoundException;
import com.professionalpractice.medicalbookingbespring.repositories.RoleRepository;
import com.professionalpractice.medicalbookingbespring.repositories.UserRepository;
import com.professionalpractice.medicalbookingbespring.services.UserService;
import com.professionalpractice.medicalbookingbespring.utils.GenderName;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<UserDTO> getUsers(PageRequest pageRequest) {

        Page<User> usersPage = userRepository.queryUsers(pageRequest);
        return usersPage.map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO createUser(UserRequest userRequest) {
        Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("Email đã tồn tại");
        }
        String hashPassword = BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt(10));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dateOfBirth = LocalDate.parse(userRequest.getDateOfBirth(), formatter);
        // Chuyển đổi chuỗi thành LocalDate

        User theUser = User.builder()
            .fullName(userRequest.getFullName())
            .address(userRequest.getAddress())
            .isLocked(false)
            .avatar(userRequest.getAvatar())
            .password(hashPassword)
            .email(userRequest.getEmail())
            .phoneNumber(userRequest.getPhoneNumber())
            .gender(checkGender(userRequest.getGender()))
            .roles(convertRoles(userRequest.getRoles()))
            .dateOfBirth(dateOfBirth)
            .build();

        User saveUser = userRepository.save(theUser);
        return modelMapper.map(saveUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUserProfile(String userEmail, UserRequest userRequest) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dateOfBirth = LocalDate.parse(userRequest.getDateOfBirth(), formatter);
        User newUser = User.builder()
            .fullName(userRequest.getFullName())
            .gender(checkGender(userRequest.getGender()))
            .dateOfBirth(dateOfBirth)
            .address(userRequest.getAddress())
            .phoneNumber(userRequest.getPhoneNumber())
            .build();

        User saveUser = userRepository.save(newUser);
        return modelMapper.map(saveUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUserById(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        String hashPassword = BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt(10));

        if (userRequest.getDateOfBirth() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalDate dateOfBirth = LocalDate.parse(userRequest.getDateOfBirth(), formatter);

            User newUser = User.builder()
                .fullName(userRequest.getFullName())
                .address(userRequest.getAddress())
                .isLocked(false)
                .avatar(userRequest.getAvatar())
                .password(hashPassword)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .gender(checkGender(userRequest.getGender()))
                .roles(convertRoles(userRequest.getRoles()))
                .dateOfBirth(dateOfBirth)
                .build();
        }
    
        User newUser = User.builder()
            .fullName(userRequest.getFullName())
            .address(userRequest.getAddress())
            .isLocked(false)
            .avatar(userRequest.getAvatar())
            .password(hashPassword)
            .email(userRequest.getEmail())
            .phoneNumber(userRequest.getPhoneNumber())
            .gender(checkGender(userRequest.getGender()))
            .roles(convertRoles(userRequest.getRoles()))
            .build();

        User saveUser = userRepository.save(newUser);
        return modelMapper.map(saveUser, UserDTO.class);
    }

    @Override
    public UserDTO lockUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        user.setIsLocked(!user.getIsLocked());

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO deleteUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Người dùng không tìm thấy"));

        userRepository.delete(user);
        return modelMapper.map(user, UserDTO.class);
    }

    private GenderName checkGender(String theGenderName) {
        for (GenderName genderName : GenderName.values()) {
            if (genderName.name().equals(theGenderName)) {
                return genderName;
            }
        }
        return null;
    }

    private Set<Role> convertRoles(List<String> roles) {
        Set<Role> listRole = new HashSet<>();
        for (String role : roles) {
            Role existingRole = roleRepository.findByName(role);
            if (existingRole != null) {
                listRole.add(existingRole);
            } else {
                throw new NotFoundException("Quyền " + role + " không tồn tại");
            }
        }
        return listRole;
    }
}
