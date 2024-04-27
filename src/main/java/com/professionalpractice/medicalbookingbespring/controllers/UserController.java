package com.professionalpractice.medicalbookingbespring.controllers;

import com.professionalpractice.medicalbookingbespring.config.RestApiV1;
import com.professionalpractice.medicalbookingbespring.dtos.UserDto;
import com.professionalpractice.medicalbookingbespring.entities.User;
import com.professionalpractice.medicalbookingbespring.services.UserService;
import com.professionalpractice.medicalbookingbespring.utils.CustomResponse;
import com.professionalpractice.medicalbookingbespring.utils.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestApiV1
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(
            page, limit,
            Sort.by("id").ascending()
        );
        Page<User> userPage = userService.getUsers(pageRequest);

        long totalPages = userPage.getTotalElements();
        List<User> users = userPage.getContent();
        return CustomResponse.success(new PaginationResponse(page, limit, totalPages, users));

    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User userBody) {
        UserDto user = userService.createUser(userBody);

        return CustomResponse.success(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);

        return CustomResponse.success(user);
    }


}
