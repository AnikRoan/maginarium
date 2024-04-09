package com.aimaginarium.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureDto {

    private Long id;
    private String s3Link;
    private boolean isPrivate;
    private boolean isDeleted;
    private UserGalleryDto userGalleryDto;


}






