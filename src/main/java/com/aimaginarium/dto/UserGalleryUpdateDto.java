package com.aimaginarium.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserGalleryUpdateDto(
        @NotNull(message = "ID must be filled")
        Long id,
        @NotNull(message = "Title must be filled")
        String title) {

}
