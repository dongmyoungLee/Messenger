package com.example.user.controller;

import com.example.user.domain.dto.UserResponse;
import com.example.user.domain.entity.User;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/friendShips/{user_seq}")
    public Optional<UserResponse> findMyFriendShips(@PathVariable Long user_seq) {
        return userService.findMyFriendShips(user_seq);
    }
}
