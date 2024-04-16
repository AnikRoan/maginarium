package com.aimaginarium.service;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.dto.UserGalleryRequestDto;
import com.aimaginarium.dto.UserGalleryUpdateDto;

public interface UserGalleryService {

    UserGalleryDto saveGallery(UserGalleryRequestDto userGalleryDto, Long userId);

    UserGalleryDto updateGalleryTitle(UserGalleryUpdateDto galleryDto);

    UserGalleryDto getGalleryById(Long id);

    UserGalleryDto getGalleryByUserId(Long userId);
}
