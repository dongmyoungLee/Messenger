package com.example.user.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_seq;
    private String email;
    private String password;
    private String name;
    private String profileImgPath;

    @OneToMany(mappedBy = "user")
    private List<FriendShip> friendShip;

    public User(String email, String password, String name, String profileImgPath) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImgPath = profileImgPath;
    }
}
