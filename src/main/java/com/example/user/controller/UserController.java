package com.example.user.controller;

import com.example.user.domain.dto.InfoResponse;
import com.example.user.domain.dto.LoginResponse;
import com.example.user.domain.dto.UserResponse;
import com.example.user.domain.entity.User;
import com.example.user.domain.request.LoginRequest;
import com.example.user.domain.request.SignupRequest;
import com.example.user.service.UserService;
import jakarta.servlet.http.HttpSession;
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
    @ResponseBody
    public Optional<UserResponse> findMyFriendShips(@PathVariable Long user_seq) {
        return userService.findMyFriendShips(user_seq);
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "/kakao/index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, HttpSession session, Model model) {
        LoginResponse login = userService.login(loginRequest);
        User user = userService.findByName(login.getName());

        if (login != null) {
            session.setAttribute("userName", login.getName());
            session.setAttribute("user_seq", user.getUser_seq());
            model.addAttribute("user_seq", user.getUser_seq());
            model.addAttribute("userName", login.getName());
            return "/kakao/friends";
        }

        return "/kakao/login";
    }

    @GetMapping("/info/{user_seq}")
    @ResponseBody
    public InfoResponse myInfo(@PathVariable Long user_seq) {
        User user = userService.findById(user_seq);
        InfoResponse infoResponse = new InfoResponse(user.getUser_seq(), user.getName(), user.getProfileImgPath());
        return infoResponse;
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

}
