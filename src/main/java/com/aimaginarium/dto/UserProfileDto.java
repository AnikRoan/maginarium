package com.aimaginarium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {

    private Long id;
    private String fullName;
    private String login;
    private LocalDateTime createdAt;
    private UserDto userDto;
}
