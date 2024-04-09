package com.aimaginarium.convertors;

import com.aimaginarium.dtos.UserProfileDto;
import com.aimaginarium.models.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileConvertor {

    public UserProfileDto convertToDto(UserProfile userProfile) {
        return UserProfileDto.builder()
                .id(userProfile.getId())
                .fullName(userProfile.getFullName())
                .login(userProfile.getLogin())
                .createdAt(userProfile.getCreatedAt())
                .build();
    }
    public UserProfile convertToEntity(UserProfileDto userProfileDto) {
        return UserProfile.builder()
                .id(userProfileDto.getId())
                .fullName(userProfileDto.getFullName())
                .login(userProfileDto.getLogin())
                .createdAt(userProfileDto.getCreatedAt())
                .build();
    }

    public UserProfile convertUpdateToEntity(UserProfile userProfile,UserProfileDto userProfileDto) {
        userProfile.setFullName(userProfileDto.getFullName());
        userProfile.setLogin(userProfileDto.getLogin());
        userProfile.setCreatedAt(userProfileDto.getCreatedAt());
        return userProfile;


    }
}
