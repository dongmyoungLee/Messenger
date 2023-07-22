package com.example.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class InfoResponse {
    private Long user_seq;
    private String name;
    private String profileImgPath;
}
