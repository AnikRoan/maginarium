package com.aimaginarium.convertors;

import com.aimaginarium.dtos.UserGalleryDto;
import com.aimaginarium.models.UserGallery;
import org.springframework.stereotype.Component;

@Component
public class UserGalleryConvertor {

    public static UserGalleryDto convertToDto(UserGallery userGallery) {
        return UserGalleryDto.builder()
                .id(userGallery.getId())
                .title(userGallery.getTitle())
                .userId(userGallery.getUserId())
                .createdAt(userGallery.getCreatedAt())
                .build();
    }
    public static UserGallery convertToEntity(UserGalleryDto userGalleryDto) {
        return UserGallery.builder()
                .id(userGalleryDto.getId())
                .title(userGalleryDto.getTitle())
                .createdAt(userGalleryDto.getCreatedAt())
                .build();
    }
    public UserGallery convertUpdateToEntity(UserGallery userGallery, UserGalleryDto userGalleryDto) {
        userGallery.setTitle(userGalleryDto.getTitle());
        return userGallery;

    }
}
