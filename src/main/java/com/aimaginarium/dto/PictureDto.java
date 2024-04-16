package com.aimaginarium.dto;

import lombok.*;

@Data
@Builder
public class PictureDto {

    private Long id;
    private String s3Link;
    private boolean privateFlag;
    private boolean deletedFlag;
    private PictureDetailsDto pictureDetailsDto;
    private UserGalleryDto userGalleryDto;


}






