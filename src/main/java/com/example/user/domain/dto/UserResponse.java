package com.example.user.domain.dto;

import com.example.user.domain.entity.FriendShip;
import com.example.user.domain.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class UserResponse {
    private Long user_seq;
    private String email;
    private String password;
    private String name;
    private String profileImgPath;

    private List<FriendShipDto> friendShip;

    public UserResponse(User user) {
        this.user_seq = user.getUser_seq();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.profileImgPath = user.getProfileImgPath();
        this.friendShip = user.getFriendShip() != null ?
                user.getFriendShip()
                        .stream()
                        .map(FriendShipDto::new)
                        .toList()
                : new ArrayList<>();

        ;
    }
    @Getter
    @AllArgsConstructor
    class FriendShipDto {
        private Long friendShip_seq;
        private Long user_seq;
        private Long user_friend_seq;
        private LocalDateTime created_at;
        private String name;
        private String profileImgPath;

        public FriendShipDto(FriendShip friendShip) {
            this.friendShip_seq = friendShip.getFriendShip_seq();
            this.user_seq = friendShip.getUser().getUser_seq();
            this.user_friend_seq = friendShip.getUser_friend_seq();
            this.created_at = friendShip.getCreated_at();
            this.name = friendShip.getFriendName();
            this.profileImgPath = friendShip.getFriendProfileImgPath();
        }
    }


}
