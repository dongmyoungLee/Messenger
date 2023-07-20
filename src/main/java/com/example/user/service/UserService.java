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

    @Value("${spring.email.sender-email}")
    private String senderEmail;

    @Value("${spring.email.sender-password}")
    private String senderPassword;

    @Value("${spring.email.smtp.host}")
    private String host;

    @Value("${spring.email.smtp.port}")
    private int port;

    public Optional<UserResponse> findMyFriendShips(Long user_seq) {
        Optional<User> byId = userRepository.findById(user_seq);

        return byId.map(UserResponse::new);
    }

    public void signup(SignupRequest signupRequest) {
        User user = User.builder()
                .email(signupRequest.getEmail())
                .password(signupRequest.getPassword())
                .name(signupRequest.getName())
                .profileImgPath(signupRequest.getProfileImgPath())
                .build();

        String validNum = generateVerificationCode();

        try {
            userRepository.save(user);
            // 회원 가입 후 이메일 인증 메일 전송
            sendVerificationEmail(user.getEmail(), validNum);
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

    public boolean sendVerificationEmail(String email, String verificationCode) {
        String subject = "이메일 인증번호";
        String content = "인증번호: " + verificationCode;

        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            return true; // 메일 전송 성공 시 true 반환
        } catch (MessagingException e) {
            e.printStackTrace();
            return false; // 메일 전송 실패 시 false 반환
        }
    }

    public String generateVerificationCode() {
        int codeLength = 6;
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < codeLength; i++) {
            int digit = random.nextInt(10);
            codeBuilder.append(digit);
        }

        return codeBuilder.toString();
    }
}
