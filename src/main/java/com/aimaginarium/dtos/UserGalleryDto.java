package com.aimaginarium.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGalleryDto {

    private Long id;
    private String title;
    private Integer userId;
    private LocalDateTime createdAt;
    private UserDto userDto;
    private List<PictureDto> pictures;

}
