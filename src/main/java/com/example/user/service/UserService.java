package com.example.user.service;

import com.example.user.domain.dto.LoginResponse;
import com.example.user.domain.dto.UserResponse;
import com.example.user.domain.entity.User;
import com.example.user.domain.request.LoginRequest;
import com.example.user.domain.request.SignupRequest;
import com.example.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserResponse> findMyFriendShips(Long user_seq) {
        Optional<User> byId = userRepository.findById(user_seq);

        return byId.map(UserResponse::new);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findById(Long user_seq) {
        Optional<User> byId = userRepository.findById(user_seq);
        return byId.get();
    }

    public void signup(SignupRequest signupRequest) {
        User user = User.builder()
                .email(signupRequest.getEmail())
                .password(signupRequest.getPassword())
                .name(signupRequest.getName())
                .profileImgPath(signupRequest.getProfileImgPath())
                .build();

        try {
            userRepository.save(user);
            // 회원 가입 후 이메일 인증 메일 전송
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmailAndPassword(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        return new LoginResponse(user);
    }



}
