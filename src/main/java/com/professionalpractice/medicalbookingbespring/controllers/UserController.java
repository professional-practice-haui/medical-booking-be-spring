package com.professionalpractice.medicalbookingbespring.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.dtos.request.UserRequest;
import com.professionalpractice.medicalbookingbespring.dtos.response.PaginationResponse;
import com.professionalpractice.medicalbookingbespring.services.CloudinaryService;
import com.professionalpractice.medicalbookingbespring.services.UserService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestApiV1
public class UserController {
    private final UserService userService;

    private final CloudinaryService cloudinaryService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(
                page - 1, limit,
                Sort.by("id").ascending());
        Page<UserDto> userPage = userService.getUsers(pageRequest);

        long totalPages = userPage.getTotalElements();
        List<UserDto> users = userPage.getContent();
        return CustomResponse.success(new PaginationResponse(page, limit, totalPages, users));
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        UserDto user = userService.createUser(userRequest);

        return CustomResponse.success(HttpStatus.CREATED, "Tạo người dùng mới thành công", user);
    }

    @PutMapping("/users/profile")
    public ResponseEntity<?> updateProfile(@ModelAttribute UserRequest userRequest,
            @RequestParam(value = "avatar", required = false) MultipartFile avatarFile) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (avatarFile != null) {
            String avatarUrl = cloudinaryService.uploadImage(avatarFile);
            userRequest.setAvatarUrl(avatarUrl);
        }

        UserDto updatedUser = userService.updateUserProfile(userEmail, userRequest);

        return CustomResponse.success("Cập nhật thành công", updatedUser);
    }

    @PatchMapping("/users/lock/{id}")
    public ResponseEntity<?> updateLock(@PathVariable Long id) {
        UserDto updatedUser = userService.lockUserById(id);

        String message = updatedUser.getIsLocked() == true ? "Khoá thành công" : "Mở khoá thành công";

        return CustomResponse.success(message, updatedUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);

        return CustomResponse.success(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        UserDto updatedUser = userService.updateUserById(id, userRequest);

        return CustomResponse.success("Cập nhật thành công", updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);

        return CustomResponse.success("Xóa thành công");
    }
}
