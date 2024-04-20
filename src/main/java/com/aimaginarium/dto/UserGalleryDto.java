package com.aimaginarium.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserGalleryDto(Long id,
                             @NotEmpty(message = "Title must be filled") String title,
                             LocalDateTime createdAt,
                             List<PictureDto> pictures) {

}
