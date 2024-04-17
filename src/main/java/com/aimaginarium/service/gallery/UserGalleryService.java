package com.aimaginarium.service.gallery;

import com.aimaginarium.dto.UserGalleryDto;

public interface UserGalleryService {

    UserGalleryDto saveGallery(UserGalleryDto userGalleryDto, Long userId);

    UserGalleryDto updateGalleryTitle(Long id, String title);

    UserGalleryDto getGalleryById(Long id);

    UserGalleryDto getGalleryByUserId(Long userId);
}
