package com.aimaginarium.dto;

import com.aimaginarium.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserGalleryRequestDto(
        @NotNull(message = "User must be filled") User user,
        @NotNull(message = "Title must be filled") String title) {

}
