package com.professionalpractice.medicalbookingbespring.controllers;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.UserDTO;
import com.professionalpractice.medicalbookingbespring.dtos.request.UserRequest;
import com.professionalpractice.medicalbookingbespring.dtos.response.PaginationResponse;
import com.professionalpractice.medicalbookingbespring.services.UserService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestApiV1
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(
            page, limit,
            Sort.by("id").ascending());
        Page<UserDTO> userPage = userService.getUsers(pageRequest);

        long totalPages = userPage.getTotalElements();
        List<UserDTO> users = userPage.getContent();
        return CustomResponse.success(new PaginationResponse(page, limit, totalPages, users));

    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        UserDTO user = userService.createUser(userRequest);
        return CustomResponse.success(user);
    }

    @PutMapping("/users/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserRequest userRequest) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDTO updatedUser = userService.updateUserProfile(userEmail, userRequest);

        return CustomResponse.success("Cập nhật thành công", updatedUser);
    }

    @PatchMapping("/users/lock/{id}")
    public ResponseEntity<?> updateLock(@PathVariable Long id) {
        UserDTO updatedUser = userService.lockUserById(id);

        String message = updatedUser.getIsLocked() ? "Khoá thành công" : "Mở khoá thành công";

        return CustomResponse.success(message, updatedUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);

        return CustomResponse.success(user);
    }

//    @PutMapping("/users/{id}")
//    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserRequest userRequest) {
//        UserDTO updatedUser = userService.updateUserById(id, userRequest);
//
//        return CustomResponse.success("Cập nhật thành công", updatedUser);
//    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);

        return CustomResponse.success("Xóa thành công");
    }
}
