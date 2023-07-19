package com.example.user.service;

import com.example.user.domain.dto.UserResponse;
import com.example.user.domain.entity.FriendShip;
import com.example.user.domain.entity.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserResponse> findMyFriendShips(Long user_seq) {
        Optional<User> byId = userRepository.findById(user_seq);

        return byId.map(UserResponse::new);
    }
}
