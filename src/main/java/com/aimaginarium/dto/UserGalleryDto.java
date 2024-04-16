package com.aimaginarium.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserGalleryDto(Long id,
                             String title,
                             LocalDateTime createdAt,
                             List<PictureDto> pictures) {

}
