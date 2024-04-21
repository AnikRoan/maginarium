package com.aimaginarium.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

import static com.aimaginarium.exception.ErrorMessage.TITLE_NOT_EMPTY;

@Builder
public record UserGalleryDto(Long id,
                             @NotEmpty(message = TITLE_NOT_EMPTY) String title,
                             LocalDateTime createdAt,
                             List<PictureDto> pictures) {

}
