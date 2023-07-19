package com.example.user.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "friendShips")
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class FriendShip {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendShip_seq;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    private Long user_friend_seq;

    private String friendName;
    private String friendProfileImgPath;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_at;

}
