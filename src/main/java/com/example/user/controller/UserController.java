package com.example.user.controller;

import com.example.user.domain.dto.LoginResponse;
import com.example.user.domain.dto.UserResponse;
import com.example.user.domain.entity.User;
import com.example.user.domain.request.LoginRequest;
import com.example.user.domain.request.SignupRequest;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/friendShips/{user_seq}")
    public Optional<UserResponse> findMyFriendShips(@PathVariable Long user_seq) {
        return userService.findMyFriendShips(user_seq);
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "/kakao/index";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "/kakao/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest signupRequest,ModelAndView mav) {
        userService.signup(signupRequest);
        return "/kakao/index";
    }
    @GetMapping("/send-verification-email")
    public ResponseEntity<String> sendVerificationEmail(@RequestParam("email") String email) {
        String verificationCode = userService.generateVerificationCode();
        boolean emailSent = userService.sendVerificationEmail(email, verificationCode);

        if (emailSent) {
            return ResponseEntity.ok("이메일 인증번호 전송 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 인증번호 전송 실패");
        }
    }

}
